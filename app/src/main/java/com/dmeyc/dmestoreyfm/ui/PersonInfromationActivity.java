package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.ImagePathBean;
import com.dmeyc.dmestoreyfm.bean.PublishActionAfterBean;
import com.dmeyc.dmestoreyfm.bean.TrueNameBean;
import com.dmeyc.dmestoreyfm.bean.common.CommonBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.newui.login.LoginActivity;
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
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class PersonInfromationActivity extends BaseActivity {

//    @Bind(R.id.cv_personheader)
    CircleImageView cv_personheader;
//    @Bind(R.id.tv_nickname)
    TextView tv_nickname;
//    @Bind(R.id.tv_sex)
    TextView tv_sex;
//    @Bind(R.id.tv_birthday)
    TextView tv_birthday;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_personinfromation;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        cv_personheader=mRootView.findViewById(R.id.cv_personheader);
        tv_nickname=mRootView.findViewById(R.id.tv_nickname);
        tv_sex=mRootView.findViewById(R.id.tv_sex);
        tv_birthday=mRootView.findViewById(R.id.tv_birthday);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getInfo();
    }

    @OnClick({R.id.cv_personheader,R.id.ll_nickname,R.id.ll_bithday,R.id.ll_sex_seting,R.id.tv_outlog})
    public void onClick(View view){
        int vewid=view.getId();
        if(R.id.cv_personheader==vewid){
            new PhotoSelectHelper(PersonInfromationActivity.this,0).setCrop(true).show();
        }else if(R.id.ll_nickname==vewid){
startActivity(new Intent(PersonInfromationActivity.this,NickNameChangeActivity.class).putExtra("nickname",trueNameBean.getData().getNick_name()).putExtra("header",header));
        }else if(R.id.ll_bithday==vewid){
            startActivity(new Intent(PersonInfromationActivity.this,BirthDayChangeActivity.class).putExtra("birth",trueNameBean.getData().getBirthday()));
        }else if(R.id.ll_sex_seting==vewid){
            startActivity(new Intent(PersonInfromationActivity.this,SexChageActivity.class).putExtra("sex",trueNameBean.getData().getSex()));
        }else if(R.id.tv_outlog==vewid){
            outLogin();
        }
    }


    private void outLogin() {
        RestClient.getYfmNovate(this).post(Constant.API.YFM_USER_OUTLOG, new ParamMap.Build()
                .addParams("user_token",SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<PublishActionAfterBean>() {
            @Override
            public void onSuccess(PublishActionAfterBean bean) {
                ToastUtil.show(bean.getMsg());
//                RongIM.getInstance().disconnect();
                RongIM.getInstance().logout();
                SPUtils.savaBooleanData(Constant.Config.ISLOGIN,false);
                startActivity(new Intent(PersonInfromationActivity.this, LoginActivity.class));
                finish();
            }
            @Override
            public void onError(Throwable e) {
            }
        });
    }
String header;
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
                client.newCall(request).enqueue(new okhttp3.Callback() {
                    @Override
                    public void onFailure(okhttp3.Call call, IOException e) {
                        ToastUtil.show(call.toString());
                    }
                    @Override
                    public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                        Gson gs=new Gson();
                        final ImagePathBean imap=  gs.fromJson(response.body().string(),ImagePathBean.class);
                        PersonInfromationActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run(){
                                GlideUtil.loadImage(PersonInfromationActivity.this, imap.getData(), cv_personheader);
                                header = imap.getData();
                                RongIM.getInstance().refreshUserInfoCache(new UserInfo(SPUtils.getStringData(Constant.Config.USER_ID), SPUtils.getStringData(Constant.Config.NICK_NAME), Uri.parse(imap.getData())));
                                       ChageHeader(imap.getData());
                            }
                        });
                    }
                });

//                File dir = new File(images.get(0).getPath());
//                Uri uri = Uri.parse(images.get(0).getPath());
//                File file = new File(images.get(0).getPath());
//                List<Uri> uris = new ArrayList<>();
//                uris.add(uri);
//                Call<ImagePathBean> myCall = mMyService.getPic(file);
//                RestClient.getYfmNovate((Context) getActivity()).uploadImage(Constant.API.YFM_UPLOAD_HEADING, new File(images.get(0).getPath()), new DmeycBaseSubscriber<CommonBean>() {
//                File file = new File(images.get(0).getPath());


//                Map<String, Map<String, RequestBody>> params = new HashMap<>();
//                HttpParameterBuilder bulider = HttpParameterBuilder.newBuilder().addParameter("file", file);
////                Map<String, RequestBody> bulider = HttpParameterBuilder.newBuilder().addParameter("file", file);
//                Map<String, RequestBody>  builderparm=   bulider.addParameter("user_token", Constant.TEST_USERTOKEN).bulider();
//                params.put("file",bulider);

//                Map<String, Map<String, RequestBody>> params = new HashMap<>();
//                File file = new File(images.get(0).getPath());
//                Map<String, RequestBody> bulider = HttpParameterBuilder.newBuilder().addParameter("file", file).addParameter("user_token", "token").bulider();

//                Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.API.YFM_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
//                NetInter service = retrofit.create(NetInter.class);
//                Call<ImagePathBean> myCall = service.getPic(builderparm);
//                myCall.enqueue(new Callback<ImagePathBean>() {
//                    @Override
//                    public void onResponse(Call<ImagePathBean> call, Response<ImagePathBean> response) {
//                        ToastUtil.show("chengg");
//                    }
//                    @Override
//                    public void onFailure(Call<ImagePathBean> call, java.lang.Throwable t) {
//                        ToastUtil.show("shibai");
//                    }
//                });

            }
        }/*else if(resultCode==222){
            tv_name.setText(data.getStringExtra("name_nake"));
        }*/
    }
    TrueNameBean trueNameBean;
    public void getInfo(){

        RestClient.getYfmNovate(this).post(Constant.API.YFM_GETCHECKNAME, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<TrueNameBean>() {
            @Override
            public void onSuccess(TrueNameBean bean) {
                trueNameBean=bean;
                GlideUtil.loadImage(PersonInfromationActivity.this,bean.getData().getUser_logo(),cv_personheader);
                header=bean.getData().getUser_logo();
                tv_nickname.setText(bean.getData().getNick_name());
                if("1".equals(bean.getData().getSex())){
                    tv_sex.setText("男");
                }else {
                    tv_sex.setText("女");
                }
                tv_birthday.setText(bean.getData().getBirthday());
            }
            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void ChageHeader(String heaerd){
        RestClient.getYfmNovate(this).post(Constant.API.YFM_CHANGETRUENAME, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("userLogo", heaerd)
                .build(), new DmeycBaseSubscriber<TrueNameBean>() {
            @Override
            public void onSuccess(TrueNameBean bean) {
//                trueNameBean=bean;
//                et_idenity.setText(bean.getData().getIdNo()+"");
//                et_name.setText(bean.getData().getName());
//                et_phone.setText(bean.getData().getPhoneNO());
                  ToastUtil.show(bean.getMsg());
//                finish();
            }
            @Override
            public void onError(Throwable e) {

            }
        });

    }
}
