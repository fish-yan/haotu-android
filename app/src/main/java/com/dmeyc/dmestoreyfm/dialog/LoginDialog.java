package com.dmeyc.dmestoreyfm.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.LoginBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.present.SmsPresenter;
import com.dmeyc.dmestoreyfm.ui.LoginActivity;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.dmeyc.dmestoreyfm.wedgit.DeleteEditttext;
import com.dmeyc.dmestoreyfm.wedgit.TimerTaskTextView;
import com.dmeyc.dmestoreyfm.wedgit.VerificationCodeInput;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;


/**
 * Created by jockie on 2017/11/7
 * Email:jockie911@gmail.com
 */

public class LoginDialog extends Dialog {

    @Bind(R.id.codeinput)
    VerificationCodeInput verificationCodeInput;
    @Bind(R.id.deleteEdittext)
    DeleteEditttext deleteEdittext;
    @Bind(R.id.tv_next_step)
    TextView tvNextStep;

    @Bind(R.id.ll_root1)
    LinearLayout llRoot1;
    @Bind(R.id.ll_root2)
    LinearLayout llRoot2;
    @Bind(R.id.tv_dialog_title)
    TextView tvDialogTitle;
    @Bind(R.id.iv_back)
    ImageView ivBack;

    @Bind(R.id.timertasktextview)
    TimerTaskTextView timerTaskTextView;
    @Bind(R.id.tv_default_phone)
    TextView tvDefaultPhone;
    private SmsPresenter mPresenter;

    public LoginDialog(@NonNull Context context) {
        this(context,R.style.dialog_style_bottom2top);
    }

    public LoginDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_login);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        mPresenter = new SmsPresenter();
        tvNextStep.setClickable(false);
        if(deleteEdittext != null){
            deleteEdittext.setFocusable(true);
            deleteEdittext.setFocusableInTouchMode(true);
            deleteEdittext.requestFocus();
            InputMethodManager inputManager = (InputMethodManager) deleteEdittext
                    .getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(deleteEdittext, 0);
        }
        verificationCodeInput.setOnCompleteListener(new VerificationCodeInput.OnCompleteListener() {
            @Override
            public void onComplete(String content) {
                submitCode(deleteEdittext.getRelPhoneNum(),content);
            }
        });
        deleteEdittext.setOnCompleteListener(new DeleteEditttext.OnCompleteListener() {
            @Override
            public void onComplete(boolean isComplete, String phoneNum) {
                tvNextStep.setBackgroundColor(Color.parseColor(isComplete ? "#1a1a1a" : "#c5c5c5"));
                tvNextStep.setClickable(isComplete);
            }
        });
    }

    @OnClick({R.id.tv_next_step,R.id.iv_back,R.id.iv_cancle,R.id.timertasktextview,R.id.tv_other_login})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_next_step:
                sendCode();
                break;
            case R.id.iv_back:
                llRoot2.setVisibility(View.INVISIBLE);
                llRoot1.setVisibility(View.VISIBLE);
                tvDialogTitle.setText("登录");
                ivBack.setVisibility(View.INVISIBLE);
                verificationCodeInput.reSet();

                timerTaskTextView.pauseTimer();
                break;
            case R.id.iv_cancle:
                dismiss();
                break;
            case R.id.timertasktextview:
                sendCode();
                break;
            case R.id.tv_other_login:
                Intent intent = new Intent(getContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);
                dismiss();
                break;
        }
    }

    private void sendCode() {
        mPresenter.sendSmsCode(getContext(),deleteEdittext.getRelPhoneNum(), SmsPresenter.SMS_LOGIN_CODE ,new SmsPresenter.OnSmsSendLisener() {
            @Override
            public void onSuccess() {
                llRoot1.setVisibility(View.INVISIBLE);
                llRoot2.setVisibility(View.VISIBLE);
                tvDialogTitle.setText("输入验证码");
                ivBack.setVisibility(View.VISIBLE);
                tvDefaultPhone.setText(deleteEdittext.getPhoneNum());
                timerTaskTextView.startTimer();
            }

            @Override
            public void onFailure(String errMsg) {
                ToastUtil.show(errMsg);
            }
        });
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ButterKnife.unbind(this);
    }

    public void submitCode(final String phone, String code) {
        mPresenter.checkSmsCode(getContext(),phone,code,SmsPresenter.SMS_LOGIN_CODE , new SmsPresenter.OnSmsCheckListener() {
            @Override
            public void onSuccess() {
                RestClient.getNovate(getContext()).post(Constant.API.USER_LOGIN, new ParamMap.Build().addParams(Constant.Config.MOBILE, phone).build(), new DmeycBaseSubscriber<LoginBean>(getContext()) {
                    @Override
                    public void onSuccess(LoginBean bean) {
                        Util.savaUserInfo(bean);
                        dismiss();
                        RongIM.connect(bean.getData().getServiceToken(), new RongIMClient.ConnectCallback() {
                            @Override
                            public void onTokenIncorrect() {
                                        ToastUtil.show("启动服务失败");
                            }

                            @Override
                            public void onSuccess(String s) {

                            }

                            @Override
                            public void onError(RongIMClient.ErrorCode errorCode) {
                                        ToastUtil.show("启动服务失败");
                            }
                        });

                    }
                });
            }

            @Override
            public void onFailure(String errMsg) {
                verificationCodeInput.shake();
            }
        });
    }
}
