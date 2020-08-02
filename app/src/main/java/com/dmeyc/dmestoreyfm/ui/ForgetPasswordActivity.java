package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.PublishActionAfterBean;
import com.dmeyc.dmestoreyfm.bean.YFMLoginBean;
import com.dmeyc.dmestoreyfm.bean.common.CommonBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.TimerTaskTextView;
import com.tamic.novate.Throwable;

public class ForgetPasswordActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_phone,et_code,et_pass,et_passagain;
    private ImageView iv_log_chephone,iv_log_checode,iv_log_chepass,iv_log_chepassagain;
    private RelativeLayout rl_log_phoneshap,rl_log_codeshap,rl_log_passshap,rl_log_passagainshap;

//    private TextView tv_get_sms_code;
    private Button btn_foget;
    TimerTaskTextView timerTaskTextView;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_forgetpassword;
    }
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        timerTaskTextView=findViewById(R.id.timertasktextview);
        timerTaskTextView.setOnClickListener(this);

        btn_foget=(Button) findViewById(R.id.btn_foget);
        btn_foget.setOnClickListener(this);
        et_phone=(EditText) findViewById(R.id.et_phone);
        et_phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    rl_log_phoneshap.setBackground(getResources().getDrawable(R.drawable.shap_check));
                    iv_log_chephone.setBackground(getResources().getDrawable(R.drawable.phone_check));
//                    iv_log_chepass.setBackground(getResources().getDrawable(R.drawable.shap_loguncheck));
//                    iv_log_chepassagain.setBackground(getResources().getDrawable(R.drawable.shap_loguncheck));
                }else {
                    rl_log_phoneshap.setBackground(getResources().getDrawable(R.drawable.shap_loguncheck));
                    iv_log_chephone.setBackground(getResources().getDrawable(R.drawable.log_phoneuncheck));
//                    iv_log_chepass.setBackground(getResources().getDrawable(R.drawable.shap_loguncheck));
//                    iv_log_chepassagain.setBackground(getResources().getDrawable(R.drawable.shap_loguncheck));
                }
            }
        });
        et_code=(EditText) findViewById(R.id.et_code);
        et_code.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    rl_log_codeshap.setBackground(getResources().getDrawable(R.drawable.shap_check));
                    iv_log_checode.setBackground(getResources().getDrawable(R.drawable.check_code));
//
                }else {
                    rl_log_codeshap.setBackground(getResources().getDrawable(R.drawable.shap_loguncheck));
                    iv_log_checode.setBackground(getResources().getDrawable(R.drawable.log_checkagain));
                }
            }
        });
        et_pass=(EditText) findViewById(R.id.et_pass);
        et_pass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    rl_log_passshap.setBackground(getResources().getDrawable(R.drawable.shap_check));
                    iv_log_chepass.setBackground(getResources().getDrawable(R.drawable.pass_check));
                }else {
                    rl_log_passshap.setBackground(getResources().getDrawable(R.drawable.shap_loguncheck));
                    iv_log_chepass.setBackground(getResources().getDrawable(R.drawable.log_passuncheck));
                }
            }
        });
        et_passagain=(EditText) findViewById(R.id.et_passagain);
        et_passagain.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    rl_log_passagainshap.setBackground(getResources().getDrawable(R.drawable.shap_check));
                    iv_log_chepassagain.setBackground(getResources().getDrawable(R.drawable.pass_again));
                }else {
                    rl_log_passagainshap.setBackground(getResources().getDrawable(R.drawable.shap_loguncheck));
                    iv_log_chepassagain.setBackground(getResources().getDrawable(R.drawable.log_passagain));
                }
            }
        });
        iv_log_chephone=findViewById(R.id.iv_log_chephone);
        iv_log_checode=findViewById(R.id.iv_log_checode);
        iv_log_chepass=findViewById(R.id.iv_log_chepass);
        iv_log_chepassagain=findViewById(R.id.iv_log_chepassagain);

        rl_log_phoneshap=(RelativeLayout)findViewById(R.id.rl_log_phoneshap);
        rl_log_codeshap=(RelativeLayout)findViewById(R.id.rl_log_codeshap);
        rl_log_passshap=(RelativeLayout)findViewById(R.id.rl_log_passshap);
        rl_log_passagainshap=(RelativeLayout)findViewById(R.id.rl_log_passagainshap);
//        tv_get_sms_code=(TextView) findViewById(R.id.tv_get_sms_code);
//        tv_get_sms_code.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int viewid=v.getId();
        if(R.id.timertasktextview==viewid){
            sendCode();
        }else if(R.id.btn_foget==viewid){
            forgetPass();
        }
    }

    private void forgetPass() {

        if(!checkPhoneNum(et_phone.getText().toString().trim())){
            ToastUtil.show("请输入正确手机号");
            return;
        }
        if (et_code.getText().toString().trim().length() <= 999 && et_code.getText().toString().trim().length() > 10000) {
            ToastUtil.show("请输入正确验证码");
            return;
        }

        if(et_pass.getText().toString().trim().length()<6){
            Toast.makeText(this,"至少六位密码",Toast.LENGTH_LONG).show();
            return;
        }
        if(!et_pass.getText().toString().trim().equals(et_passagain.getText().toString().trim())){
            Toast.makeText(this,"两次密码不一致",Toast.LENGTH_LONG).show();
            return;
        }
        RestClient.getYfmNovate(ForgetPasswordActivity.this).post(Constant.API.YFM_USER_FORGETPASS, new ParamMap.Build()
                .addParams("phone_no", et_phone.getText().toString().trim())
                .addParams("validate_code", et_code.getText().toString().trim())
                .addParams("password", et_pass.getText().toString().trim())
                .addParams("confirm_password", et_passagain.getText().toString().trim())
                .build(), new DmeycBaseSubscriber<PublishActionAfterBean>(ForgetPasswordActivity.this) {
            @Override
            public void onSuccess(final PublishActionAfterBean bean) {
                Toast.makeText(ForgetPasswordActivity.this,bean.getMsg(),Toast.LENGTH_LONG).show();
                finish();
            }
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                ToastUtil.show(e.getMessage());
            }
        });

    }

    /**
     * 手机号正则校验
     * @param phoneNum
     * @return
     */
    private boolean checkPhoneNum(String phoneNum){

        String telRegex = "[1][35678]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(phoneNum)) return false;
        else return phoneNum.matches(telRegex);
    }

    private void sendCode() {

        if(!checkPhoneNum(et_phone.getText().toString().trim())){
            ToastUtil.show("请输入正确手机号");
            return;
        }
        RestClient.getYfmNovate(this).post(Constant.API.YFM_USER_SEND_CODE, new ParamMap.Build()
                .addParams("phone_no", et_phone.getText().toString().trim())
                .build(), new DmeycBaseSubscriber<CommonBean>() {
            @Override
            public void onSuccess(CommonBean bean) {
                ToastUtil.show("验证码已发送注意查收");
                timerTaskTextView.startTimer();
            }
            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
