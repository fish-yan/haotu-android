package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.CommSettingBean;
import com.dmeyc.dmestoreyfm.bean.ImagePathBean;
import com.dmeyc.dmestoreyfm.bean.TeachCourseBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.PhotoSelectHelper;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.google.gson.Gson;
import com.jph.takephoto.model.TImage;
import com.tamic.novate.Throwable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class CommSettingActivity extends BaseActivity {
    @Bind(R.id.civ_avatar)
    CircleImageView civ_avatar;
    CommSettingBean commSettingBean;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_commsetting;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        setData();
    }

    private void setData() {

        RestClient.getYfmNovate(this).post(Constant.API.YFM_COMMSETING, new ParamMap.Build()
                        .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                        .addParams("group_id", getIntent().getIntExtra("groupid",-1)).build(),

                new DmeycBaseSubscriber<CommSettingBean>(this) {

                    @Override
                    public void onSuccess(final CommSettingBean bean) {
                        commSettingBean=bean;
//                        ToastUtil.show(bean.getMsg());
                        GlideUtil.loadImage(CommSettingActivity.this,bean.getData().getGroup_logo(),civ_avatar);
                    }
                    @Override
                    public void onError(Throwable e) {
//                mBaseView.requestDataError();
                    }
                });
    }
    @OnClick({R.id.ll_changeadv,R.id.ll_changeaddres,R.id.ll_changeintro,R.id.ll_changername,R.id.ll_log})
    public void onClick(View view){
        int viewid=view.getId();
        if(R.id.ll_changername==viewid){
//            SPUtils.savaStringData(Constant.Config.COMMNAME,commSettingBean.getData().getGroup_name());
            startActivity(new Intent(CommSettingActivity.this,VipWareActivity.class)
                    .putExtra("group_id", commSettingBean.getData().getGroup_id())
                    .putExtra("changetext", commSettingBean.getData().getGroup_name())
                    .putExtra("type","name"));
        }else if(R.id.ll_changeadv==viewid){
//            SPUtils.savaStringData(Constant.Config.COMMAVD,commSettingBean.getData().getNotice());
            startActivity(new Intent(CommSettingActivity.this,VipWareActivity.class)
                    .putExtra("group_id", commSettingBean.getData().getGroup_id())
                    .putExtra("changetext", commSettingBean.getData().getNotice())
                    .putExtra("type","adv"));
        }else if(R.id.ll_changeaddres==viewid){
//            SPUtils.savaStringData(Constant.Config.COMMADRESS,commSettingBean.getData().getActivity_venue_address());
            startActivity(new Intent(CommSettingActivity.this,VipWareActivity.class)
                    .putExtra("group_id", commSettingBean.getData().getGroup_id())
                    .putExtra("changetext", commSettingBean.getData().getActivity_venue_address())
                    .putExtra("type","add"));
        }else if(R.id.ll_changeintro==viewid){
//            SPUtils.savaStringData(Constant.Config.COMMINTRO,commSettingBean.getData().getRemark());
            startActivity(new Intent(CommSettingActivity.this,VipWareActivity.class)
                    .putExtra("group_id", commSettingBean.getData().getGroup_id())
                    .putExtra("changetext", commSettingBean.getData().getRemark())
                    .putExtra("type","intro"));
        }else if(R.id.ll_log==viewid){
            new PhotoSelectHelper(this,0).setCrop(true).show();
        }

    }

    ArrayList<TImage> images;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null && resultCode == RESULT_OK){
            if(data.getBooleanExtra(Constant.Config.SUCCESS,false)){
                images = (ArrayList<TImage>) data.getSerializableExtra("images");
                GlideUtil.loadImage(this,images.get(0).getPath(),civ_avatar);
                uplode();
            }
        }
    }

    private void uplode() {

//        RestClient.getYfmNovate(this).post(Constant.API.YFM_COMMHEAD, new ParamMap.Build()
//                        .addParams("user_token", Constant.TEST_USERTOKEN)
//                        .addParams("group_id", getIntent().getIntExtra("groupid",-1))
//                        .addParams("imageList", Constant.TEST_USERTOKEN)
//                        .addParams("image_type","1")
//                        .build(),
//
//                new DmeycBaseSubscriber<CommSettingBean>(this) {
//
//                    @Override
//                    public void onSuccess(final CommSettingBean bean) {
//                        commSettingBean=bean;
//                        ToastUtil.show(bean.getMsg());
//                        GlideUtil.loadImage(CommSettingActivity.this,bean.getData().getGroup_logo(),civ_avatar);
//                    }
//                    @Override
//                    public void onError(Throwable e) {
////                mBaseView.requestDataError();
//                    }
//                });

        OkHttpClient client = new OkHttpClient();
        File file = new File(images.get(0).getPath());
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), file);

        final RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("imageList", file.getName(), fileBody)
                        .addFormDataPart("image_type", "1")
                .addFormDataPart("group_id", getIntent().getIntExtra("groupid",-1)+"")
                .addFormDataPart("user_token",SPUtils.getStringData(Constant.Config.TOKEN))
                .build();

        Request request = new Request.Builder()
                .url(Constant.API.YFM_BASE_URL+Constant.API.YFM_COMMHEAD)
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
//                Gson gs=new Gson();
//                final ImagePathBean imap=  gs.fromJson(response.body().string(),ImagePathBean.class);
               System.out.print(response.body().string()+"ddddddddddddddd");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        GlideUtil.loadImage(CommSettingActivity.this, images.get(0).getPath(), civ_avatar);
//                        submitApply(imap.getData());
//                        ToastUtil.show(imap.getMsg());
                    }
                });
            }
        });

    }
}
