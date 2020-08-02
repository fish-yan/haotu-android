package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.ImagePathBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.PhotoSelectHelper;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.google.gson.Gson;
import com.jph.takephoto.model.TImage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class EquInfromationActivity extends BaseActivity {
    @Bind(R.id.iv_eqback)
    ImageView iv_eqback;
    @Bind(R.id.et_equname)
    EditText et_equname;



    String phoneurl;
    @Override
    protected int getLayoutRes() {
        return R.layout.actvity_eqinformation;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }
    @OnClick({R.id.iv_right_title_bar,R.id.btn_regist})
    public void onClick(View view){
        int viewid=view.getId();
        if(R.id.iv_right_title_bar==viewid){
            new PhotoSelectHelper(EquInfromationActivity.this,0).setCrop(true).show();
         }else if(R.id.btn_regist==viewid){
            if(TextUtils.isEmpty(et_equname.getText().toString().trim())){
                ToastUtil.show("请输入装备品牌信息");
                return;
            }
            if(TextUtils.isEmpty(phoneurl)){
                ToastUtil.show("请选择图片");
                return;
            }
             Intent intent=new Intent();
            intent.putExtra("eqname",et_equname.getText().toString().trim());
            intent.putExtra("equrl",phoneurl);
            setResult(999,intent);
            finish();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null && resultCode == RESULT_OK) {
            if (data.getBooleanExtra(Constant.Config.SUCCESS, false)) {
                final ArrayList<TImage> images = (ArrayList<TImage>) data.getSerializableExtra("images");
//                if (getActivity() != null) {
//                    GlideUtil.loadImage(getActivity(), images.get(0).getPath(), civAvatar);
//                }
                OkHttpClient client = new OkHttpClient();
                File file = new File(images.get(0).getPath());
                RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), file);

                final RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("file", file.getName(), fileBody)
//                        .addFormDataPart("imagetype", "image/png")
                        .addFormDataPart("isLogo",1+"")
                        .addFormDataPart("user_token",SPUtils.getStringData(Constant.Config.TOKEN))
                        .build();
                Request request = new Request.Builder()
                        .url(Constant.API.YFM_BASE_URL+"api/v1.0/file/uploadSingle")
//                        .url(Constant.API.YFM_BASE_URL+"api/v1.0/user/editUserInfo")
                        .post(requestBody)
                        .build();

//                client.newCall(request).execute(new Callback<ImagePathBean>());
                client.newCall(request).enqueue(new okhttp3.Callback() {
                    @Override
                    public void onFailure(okhttp3.Call call, IOException e) {
                        ToastUtil.show(call.toString());
                    }

                    @Override
                    public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                        Gson gs=new Gson();
                        final ImagePathBean imap=  gs.fromJson(response.body().string(),ImagePathBean.class);
                        EquInfromationActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                GlideUtil.loadImage(EquInfromationActivity.this, imap.getData(), iv_eqback);
                                phoneurl= imap.getData();
//                                RongIM.getInstance().refreshUserInfoCache(new UserInfo(SPUtils.getStringData(Constant.Config.USER_ID), SPUtils.getStringData(Constant.Config.NICK_NAME), Uri.parse(imap.getData())));
//                                ChageHeader(imap.getData());
                                //                                ToastUtil.show(imap.getMsg());
                            }
                        });
                    }
                });
            }
        }


    }

}
