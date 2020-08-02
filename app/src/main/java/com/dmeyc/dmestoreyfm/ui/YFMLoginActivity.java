package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.LoginBean;
import com.dmeyc.dmestoreyfm.bean.YFMLoginBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GsonTools;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.tamic.novate.Throwable;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import butterknife.Bind;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import okhttp3.ResponseBody;

public class YFMLoginActivity extends BaseActivity implements View.OnClickListener {
    private Button btn_login,btn_regist;
    private ImageView iv_log_chepass,iv_log_chephone;
    private EditText et_phone,et_pass;
    private RelativeLayout rl_log_passshap,rl_log_phoneshap;
    private TextView tv_forget_psw;

    @Bind(R.id.iv_qq)
    ImageView iv_qq;
    @Bind(R.id.iv_weixin)
    ImageView iv_weixin;
    @Bind(R.id.iv_phonelogin)
    ImageView iv_phonelogin;
    private UMShareAPI umShareAPI;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_yfmlogin;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        umShareAPI = UMShareAPI.get(this);
//        if(Util.isLogin()){
//            startActivity(new Intent(YFMLoginActivity.this,MainActivity.class));
//        }
        et_phone=(EditText) findViewById(R.id.et_phone);
        et_pass=(EditText) findViewById(R.id.et_pass);
        btn_regist=(Button) findViewById(R.id.btn_regist);
        btn_regist.setOnClickListener(this);
        btn_login=(Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

        tv_forget_psw=(TextView) findViewById(R.id.tv_forget_psw);
        tv_forget_psw.setOnClickListener(this);

        et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!TextUtils.isEmpty(et_pass.getText().toString())&&!TextUtils.isEmpty(et_phone.getText().toString())){
                    btn_login.setAlpha(1f);
                    btn_login.setClickable(true);
                }else {
                    btn_login.setAlpha(0.5f);
                    btn_login.setClickable(false);
                  }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        et_pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!TextUtils.isEmpty(et_pass.getText().toString())&&!TextUtils.isEmpty(et_phone.getText().toString())){
                    btn_login.setAlpha(1f);
                    btn_login.setClickable(true);
                }else {
                    btn_login.setAlpha(0.5f);
                    btn_login.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        iv_qq.setOnClickListener(this);
        iv_weixin.setOnClickListener(this);
        iv_phonelogin.setOnClickListener(this);
        rl_log_passshap=(RelativeLayout) findViewById(R.id.rl_log_passshap);
        rl_log_phoneshap=(RelativeLayout) findViewById(R.id.rl_log_phoneshap);

        iv_log_chephone=(ImageView) findViewById(R.id.iv_log_chephone);
        iv_log_chepass=(ImageView) findViewById(R.id.iv_log_chepass);


        et_phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    iv_log_chephone.setBackground(getResources().getDrawable(R.drawable.phone_check));
                    iv_log_chepass.setBackground(getResources().getDrawable(R.drawable.log_passuncheck));

                    rl_log_passshap.setBackground(getResources().getDrawable(R.drawable.shap_loguncheck));
                    rl_log_phoneshap.setBackground(getResources().getDrawable(R.drawable.shap_check));
                }else {
                    iv_log_chephone.setBackground(getResources().getDrawable(R.drawable.log_phoneuncheck));
                    iv_log_chepass.setBackground(getResources().getDrawable(R.drawable.pass_check));

                    rl_log_passshap.setBackground(getResources().getDrawable(R.drawable.shap_check));
                    rl_log_phoneshap.setBackground(getResources().getDrawable(R.drawable.shap_loguncheck));
                }
            }
        });
        et_pass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    iv_log_chephone.setBackground(getResources().getDrawable(R.drawable.log_phoneuncheck));
                    iv_log_chepass.setBackground(getResources().getDrawable(R.drawable.pass_check));

                    rl_log_passshap.setBackground(getResources().getDrawable(R.drawable.shap_check));
                    rl_log_phoneshap.setBackground(getResources().getDrawable(R.drawable.shap_loguncheck));
                }else {
                    iv_log_chephone.setBackground(getResources().getDrawable(R.drawable.phone_check));
                    iv_log_chepass.setBackground(getResources().getDrawable(R.drawable.log_passuncheck));

                    rl_log_passshap.setBackground(getResources().getDrawable(R.drawable.shap_loguncheck));
                    rl_log_phoneshap.setBackground(getResources().getDrawable(R.drawable.shap_check));
                }
            }
        });
    }
          String type="";
    private void toLogin() {
        if(!checkPhoneNum(et_phone.getText().toString().trim())){
            ToastUtil.show("请输入正确手机号");
            return;
        }
        if(et_pass.getText().toString().trim().length()<6){
            Toast.makeText(this,"至少六位密码",Toast.LENGTH_LONG).show();
            return;
        }

        RestClient.getYfmNovate(YFMLoginActivity.this).post(Constant.API.YFM_USER_LOGIN, new ParamMap.Build()
                .addParams("phone_no", et_phone.getText().toString().trim())
                .addParams("password", et_pass.getText().toString().trim())
               .build(), new DmeycBaseSubscriber<YFMLoginBean>(YFMLoginActivity.this) {
            @Override
            public void onSuccess(final YFMLoginBean bean) {
               Util. savaYFMUserInfo(bean);
//                if(TextUtils.isEmpty(SPUtils.getStringData(Constant.Config.IDENITY))){
                    type="1";
//                }else {
//                    type=SPUtils.getStringData(Constant.Config.IDENITY);
//                }
                if("0".equals(type)){
                    Intent intent=new Intent(YFMLoginActivity.this,BMainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    SPUtils.savaStringData(Constant.Config.IDENITY,"1");
                    Intent intent=new Intent(YFMLoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }


                }
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                ToastUtil.show(e.getMessage());
            }
        });

    }
   int logtype=-1;
    YFMLoginBean yfmLoginBean;
    @Override
    public void onClick(View v) {
        int viewid=v.getId();
        if(viewid==R.id.btn_login){
            toLogin();
//            startActivity(new Intent(YFMLoginActivity.this,MainActivity.class));
        }else if(viewid==R.id.btn_regist){
            startActivity(new Intent(YFMLoginActivity.this,RegistActivity.class));
        }else if(R.id.tv_forget_psw==viewid){
            startActivity(new Intent(YFMLoginActivity.this,ForgetPasswordActivity.class));
        }else if(R.id.iv_weixin==viewid){
            logtype=1;
            umShareAPI.getPlatformInfo(this, SHARE_MEDIA.WEIXIN,umAuthListener);
        }else if(R.id.iv_qq==viewid){
            logtype=2;
            umShareAPI.getPlatformInfo(this, SHARE_MEDIA.QQ, umAuthListener);
        }else if(R.id.iv_phonelogin==viewid){
            startActivity(new Intent(YFMLoginActivity.this,PhoneLoginActivity.class));
        }
    }

    private boolean checkPhoneNum(String phoneNum){
        String telRegex = "[1][35678]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(phoneNum)) return false;
        else return phoneNum.matches(telRegex);
    }

    public UMAuthListener umAuthListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, final Map<String, String> data) {
//            ToastUtil.show("fffffff");
//            if(platform.ordinal() != SHARE_MEDIA.WEIXIN.ordinal()||platform.ordinal() != SHARE_MEDIA.QQ.ordinal()){
//                return;
//            }
//            if(){}
            if(platform.ordinal() == SHARE_MEDIA.WEIXIN.ordinal()){
                RestClient.getYfmNovate(YFMLoginActivity.this).post(Constant.API.YFM_THERIDBUND, new ParamMap.Build()
                        .addParams("openId", data.get("uid"))
                        .addParams("loginType",logtype)
                        .addParams("headIcon",data.get("iconurl"))
                        .build(), new DmeycBaseSubscriber<YFMLoginBean>(YFMLoginActivity.this) {
                    @Override
                    public void onSuccess(YFMLoginBean bean) {
                        yfmLoginBean=bean;
                        hasBunder(bean.getData().getIsBinding(),data);
                    }
                });
            }else if(platform.ordinal() == SHARE_MEDIA.QQ.ordinal()){
                RestClient.getYfmNovate(YFMLoginActivity.this).post(Constant.API.YFM_THERIDBUND, new ParamMap.Build()
                        .addParams("openId", data.get("openid"))
                        .addParams("loginType",logtype)
                        .addParams("headIcon",data.get("profile_image_url"))
                        .build(), new DmeycBaseSubscriber<YFMLoginBean>(YFMLoginActivity.this) {
                    @Override
                    public void onSuccess(YFMLoginBean bean) {
                        yfmLoginBean=bean;
                        hasBunder(bean.getData().getIsBinding(),data);
                    }
                });
            }
        }
        @Override
        public void onError(SHARE_MEDIA share_media, int i, java.lang.Throwable throwable) {
        ToastUtil.show("登录失败换种方式登录");
         }

//        /**
//         * @desc 授权失败的回调
//         * @param platform 平台名称
//         * @param action 行为序号，开发者用不上
//         * @param t 错误原因
//         */
//        @Override
//        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
//            Toast.makeText(LoginActivity.this, "失败：" + t.getMessage(),Toast.LENGTH_LONG).show();
//        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(YFMLoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };


    public void hasBunder(String isband, Map<String, String> data){

        if("0".equals(isband)){
            Intent intent=  new Intent(YFMLoginActivity.this,YfmBundTherActiivty.class);
//            Bundle bundle=new Bundle();
//            bundle.p
            intent.putExtra("paramap",(Serializable) data);
            intent.putExtra("type",logtype);
                 startActivity(intent);
        }else {
            Util.savaYFMUserInfo(yfmLoginBean);

//            if(TextUtils.isEmpty(SPUtils.getStringData(Constant.Config.IDENITY))){
//                type="1";
//            }else {
//                type=SPUtils.getStringData(Constant.Config.IDENITY);
//            }
//            if("0".equals(type)){
//                Intent intent=new Intent(YFMLoginActivity.this,BMainActivity.class);
//                startActivity(intent);
//                finish();
//            }else {
//                Intent intent=new Intent(YFMLoginActivity.this,MainActivity.class);
//                startActivity(intent);
//                finish();
//            }


            startActivity(new Intent(YFMLoginActivity.this,MainActivity.class));
        }
//
//                    RestClient.getNovate(YFMLoginActivity.this).post(Constant.API.USER_LOGIN, new ParamMap.Build()
//                            .addParams("openId", data.get("uid"))
//                            .addParams("avatar",data.get("iconurl"))
//                            .addParams("nickname",data.get("name"))
//                            .addParams("sex",data.get("gender"))
//                            .addParams("type", Constant.Config.WEIXIN_THIRD)
////                            .addParams("mobile",etMobile.getText().toString())
//                            .build(),
//                    new DmeycBaseSubscriber(YFMLoginActivity.this){
//                        @Override
//                        public void onNext(ResponseBody t) {
//                            try {
//                                JSONObject object = new JSONObject(t.string());
//                                if(object.has("code")){
//                                    int code = object.getInt("code");
//                                    switch (code){
//                                        case 200:
//                                    /*Class<K> kClass = (Class<K>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//                                    K k = GsonTools.changeGsonToBean(object.toString(), kClass);
//                                    onSuccess(k);*/
//                                            if(object.toString().length() < 50){
//                                                ToastUtil.show("去绑定手机号");
//                                                Intent intent = new Intent(YFMLoginActivity.this, BindMobileActivity.class);
//                                                intent.putExtra(Constant.Config.TYPE,BindMobileActivity.TYPE_FIRSTBIND);
//                                                startActivity(intent);
//                                                finish();
//                                            }else{
//                                                LoginBean loginBean = GsonTools.changeGsonToBean(object.toString(), LoginBean.class);
//                                                Util.savaUserInfo(loginBean);
//                                                finish();
//                                            }
//                                            break;
//                                        case 0: //java后台自定义异常
//                                            if(object.has("msg")) {
//                                                Throwable e = new Throwable(new java.lang.Throwable(object.getString("msg")), 0,object.getString("msg"));
//                                                onError(e);
//                                            }
//                                            break;
//                                        case 300: //java后台定义的错误
//                                            if(object.has("msg")) {
//                                                Throwable e = new Throwable(new java.lang.Throwable(object.getString("msg")), 300,object.getString("msg"));
//                                                onError(e);
//                                            }
//                                            break;
//                                    }
//                                }
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                        @Override
//                        public void onSuccess(Object bean) {
//
//                        }
//                    });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }

}
