package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.CommperInBean;
import com.dmeyc.dmestoreyfm.bean.ImagePathBean;
import com.dmeyc.dmestoreyfm.bean.TeachInBean;
import com.dmeyc.dmestoreyfm.bean.common.CommonBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
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
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class TeachNextActivity extends BaseActivity {
    TeachInBean teachInBean;

    @Bind(R.id.ll_front)
    ImageView ll_front;
    @Bind(R.id.ll_revers)
    ImageView ll_revers;
    @Bind(R.id.ll_certificate)
    ImageView ll_certificate;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_teachnext;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        Intent intent = this.getIntent();
        teachInBean =(TeachInBean)intent.getSerializableExtra("teachin");
    }
    int type=0;
    String photo1;
    String photo2;
    String photo3;
    String photo4;
    @OnClick({R.id.submit,R.id.ll_certificate,R.id.ll_revers,R.id.ll_front})
    public void onClick(View view){
        int viewid=view.getId();
        if(R.id.submit==viewid){
            submitAll();
        }else if(viewid==R.id.ll_certificate){
            type=4;
            new PhotoSelectHelper(this,1).setCrop(true).show();
        }else if(viewid==R.id.ll_revers){
            type=3;
            new PhotoSelectHelper(this,1).setCrop(true).show();
        }else if(viewid==R.id.ll_front){
            type=2;
            new PhotoSelectHelper(this,1).setCrop(true).show();
        }
    }



    public void submitAll(){

//                if(TextUtils.isEmpty(photo1)) {
//            ToastUtil.show("请选择教练头像");
//            return;
//        }
        if(TextUtils.isEmpty(photo3)) {
            ToastUtil.show("请填选择身份证正面照片");
            return;
        }
        if(TextUtils.isEmpty(photo4)) {
            ToastUtil.show("请选择身份证反面照片");
            return;
        }
             if(TextUtils.isEmpty(photo2)) {
            ToastUtil.show("请上传教练证件");
            return;
        }

                RestClient.getYfmNovate(this).post(Constant.API.YFM_TEACHIN, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("coach_sfz_zm",photo2)
                .addParams("coach_sfz_fm",photo3)
                .addParams("group_name",teachInBean.getClubname())
//                .addParams("project_type", 1)
                .addParams("project_type",teachInBean.getProjecttype())
                .addParams("province",teachInBean.getProvince())
                .addParams("city",teachInBean.getCity())
                .addParams("area", teachInBean.getArea())
                .addParams("activity_venue_address",teachInBean.getDetailadress())
                .addParams("remark",teachInBean.getBacktext())
                .addParams("coach_image_list",photo4)
                .build(), new DmeycBaseSubscriber<CommonBean>() {
            @Override
            public void onSuccess(CommonBean bean) {
                ToastUtil.show(bean.getMsg());
                finish();
            }
        });
    }


    private void uplodeteach(String filepath) {
        OkHttpClient client = new OkHttpClient();
        File file = new File(filepath);
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), file);


        MultipartBody.Builder mb= new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), fileBody)
                .addFormDataPart("user_token",SPUtils.getStringData(Constant.Config.TOKEN));
        if(1==type){
            mb.addFormDataPart("isLogo",1+"");
        }else {
            mb.addFormDataPart("isLogo",0+"");
        }
        final RequestBody requestBody = mb.build();

        Request request = new Request.Builder()
                .url(Constant.API.YFM_BASE_URL+"api/v1.0/file/uploadSingle")
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
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        GlideUtil.loadImage(TeachInActivity.this, imap.getData(), iv_roundmage);
                        if(1==type){
//                            photo1=imap.getData();
                        }else if(2==type){
                            photo2=imap.getData();
                        } else if (3==type) {
                            photo3=imap.getData();
                        }else if(4==type){
                            photo4=imap.getData();
                        }
                        ToastUtil.show(imap.getMsg());
                    }
                });

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if(data != null && resultCode == RESULT_OK&&type==1){
//            if(data.getBooleanExtra(Constant.Config.SUCCESS,false)){
//                ArrayList<TImage> images = (ArrayList<TImage>) data.getSerializableExtra("images");
//                GlideUtil.loadImage(this,images.get(0).getPath(),iv_roundmage);
////                photo1=images.get(0).getPath();
//                uplodeteach(images.get(0).getPath());
//            }
//        }
        if(data != null && resultCode == RESULT_OK&&type==2){
            if(data.getBooleanExtra(Constant.Config.SUCCESS,false)){
                ArrayList<TImage> images = (ArrayList<TImage>) data.getSerializableExtra("images");
                GlideUtil.loadImage(this,images.get(0).getPath(),ll_front);
//                iv_front.setVisibility(View.GONE);
//                photo2=images.get(0).getPath();
                uplodeteach(images.get(0).getPath());
            }
        }
        if(data != null && resultCode == RESULT_OK&&type==3){
            if(data.getBooleanExtra(Constant.Config.SUCCESS,false)){
                ArrayList<TImage> images = (ArrayList<TImage>) data.getSerializableExtra("images");
                GlideUtil.loadImage(this,images.get(0).getPath(),ll_revers);
//                iv_reviers.setVisibility(View.GONE);
//                photo3=images.get(0).getPath();
                uplodeteach(images.get(0).getPath());
            }
        }
        if(data != null && resultCode == RESULT_OK&&type==4){
            if(data.getBooleanExtra(Constant.Config.SUCCESS,false)){
                ArrayList<TImage> images = (ArrayList<TImage>) data.getSerializableExtra("images");
                GlideUtil.loadImage(this,images.get(0).getPath(),ll_certificate);
//                iv_certificate.setVisibility(View.GONE);
//                photo4=images.get(0).getPath();
                uplodeteach(images.get(0).getPath());
            }
        }
    }


}
