package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.bean.LoginBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.present.SmsPresenter;
import com.dmeyc.dmestoreyfm.utils.GsonTools;
import com.dmeyc.dmestoreyfm.utils.SnackBarUtil;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.dmeyc.dmestoreyfm.wedgit.TimerTaskTextView;
import com.tamic.novate.Throwable;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import okhttp3.ResponseBody;

public class LoginActivity extends BaseActivity<SmsPresenter> {

    @Bind(R.id.iv_left_title_bar)
    ImageView ivLeftTitleBar;
    @Bind(R.id.iv_right_title_bar)
    ImageView ivRightTitleBar;
    @Bind(R.id.et_mobile)
    EditText etMobile;
    @Bind(R.id.et_code)
    EditText etCode;
    @Bind(R.id.timertasktextview)
    TimerTaskTextView timerTaskTextView;
    private UMShareAPI umShareAPI;

    @Override
    protected int getLayoutRes() {
        return R.layout.ac_login;
    }

    @Override
    protected SmsPresenter initPresenter() {
        return new SmsPresenter();
    }

    @Override
    protected void initData() {
        ivLeftTitleBar.setVisibility(View.INVISIBLE);
        ivRightTitleBar.setImageResource(R.drawable.ic_close_black);
        umShareAPI = UMShareAPI.get(this);

        timerTaskTextView.setStatus(true);
    }

    @OnClick({R.id.iv_right_title_bar,R.id.iv_weixin,R.id.iv_qq,R.id.iv_weibo,R.id.tv_login,R.id.timertasktextview})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_right_title_bar:
                finish();
                break;
            case R.id.iv_weixin:
                umShareAPI.getPlatformInfo(this, SHARE_MEDIA.WEIXIN,umAuthListener);
                break;
            case R.id.iv_qq:
                umShareAPI.getPlatformInfo(this, SHARE_MEDIA.QQ, umAuthListener);
                break;
            case R.id.iv_weibo:
                umShareAPI.getPlatformInfo(this, SHARE_MEDIA.SINA,umAuthListener);
                break;
            case R.id.tv_login:
                mPresenter.checkSmsCode(this,etMobile.getText().toString(),etCode.getText().toString(),SmsPresenter.SMS_LOGIN_CODE , new SmsPresenter.OnSmsCheckListener() {
                    @Override
                    public void onSuccess() {
                        RestClient.getNovate(LoginActivity.this).post(Constant.API.USER_LOGIN, new ParamMap.Build().addParams(Constant.Config.MOBILE, etMobile.getText().toString()).build(), new DmeycBaseSubscriber<LoginBean>(LoginActivity.this) {
                            @Override
                            public void onSuccess(final LoginBean bean) {
                                Util.savaUserInfo(bean);


                                RongIM.connect(bean.getData().getServiceToken(), new RongIMClient.ConnectCallback() {
                                    @Override
                                    public void onTokenIncorrect() {
//                                        ToastUtil.show("启动服务失败");
                                    }

                                    @Override
                                    public void onSuccess(String s) {
                                        finish();
                                    }

                                    @Override
                                    public void onError(RongIMClient.ErrorCode errorCode) {
//                                        ToastUtil.show("启动服务失败");
                                    }
                                });
                            }
                        });
                    }

                    @Override
                    public void onFailure(String errMsg) {
                        SnackBarUtil.showShortSnackbar(ivLeftTitleBar,errMsg);
                    }
                });
                break;
            case R.id.timertasktextview: //点击开始发送验证码
                mPresenter.sendSmsCode(this,etMobile.getText().toString(),SmsPresenter.SMS_LOGIN_CODE ,new SmsPresenter.OnSmsSendLisener() {
                    @Override
                    public void onSuccess() {
                        timerTaskTextView.startTimer();
                    }

                    @Override
                    public void onFailure(String errMsg) {
                        SnackBarUtil.showShortSnackbar(ivLeftTitleBar,errMsg);
                    }
                });
                break;
        }
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
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            if(platform.ordinal() != SHARE_MEDIA.WEIXIN.ordinal()) return;

            RestClient.getNovate(LoginActivity.this).post(Constant.API.USER_LOGIN, new ParamMap.Build()
                            .addParams("openId", data.get("uid"))
                            .addParams("avatar",data.get("iconurl"))
                            .addParams("nickname",data.get("name"))
                            .addParams("sex",data.get("gender"))
                            .addParams("type", Constant.Config.WEIXIN_THIRD)
//                            .addParams("mobile",etMobile.getText().toString())
                            .build(),
                    new DmeycBaseSubscriber(LoginActivity.this){
                        @Override
                        public void onNext(ResponseBody t) {
                            try {
                                JSONObject object = new JSONObject(t.string());
                                if(object.has("code")){
                                    int code = object.getInt("code");
                                    switch (code){
                                        case 200:
                                    /*Class<K> kClass = (Class<K>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                                    K k = GsonTools.changeGsonToBean(object.toString(), kClass);
                                    onSuccess(k);*/
                                            if(object.toString().length() < 50){
                                                ToastUtil.show("去绑定手机号");
                                                Intent intent = new Intent(LoginActivity.this, BindMobileActivity.class);
                                                intent.putExtra(Constant.Config.TYPE,BindMobileActivity.TYPE_FIRSTBIND);
                                                startActivity(intent);
                                                finish();
                                            }else{
                                                LoginBean loginBean = GsonTools.changeGsonToBean(object.toString(), LoginBean.class);
                                                Util.savaUserInfo(loginBean);
                                                finish();
                                            }
                                            break;
                                        case 0: //java后台自定义异常
                                            if(object.has("msg")) {
                                                Throwable e = new Throwable(new java.lang.Throwable(object.getString("msg")), 0,object.getString("msg"));
                                                onError(e);
                                            }
                                            break;
                                        case 300: //java后台定义的错误
                                            if(object.has("msg")) {
                                                Throwable e = new Throwable(new java.lang.Throwable(object.getString("msg")), 300,object.getString("msg"));
                                                onError(e);
                                            }
                                            break;
                                    }
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onSuccess(Object bean) {

                        }
                    });
 }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, java.lang.Throwable throwable) {

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
            Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };
}
