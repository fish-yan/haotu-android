package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.YFMLoginBean;
import com.dmeyc.dmestoreyfm.bean.common.CommonBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.dmeyc.dmestoreyfm.wedgit.TimerTaskTextView;
import com.tamic.novate.Throwable;

import butterknife.Bind;
import butterknife.OnClick;

public class PhoneLoginActivity extends BaseActivity {
    @Bind(R.id.et_phone)
    EditText et_phone;
    @Bind(R.id.timertasktextview)
    TimerTaskTextView timerTaskTextView;
    @Bind(R.id.et_code)
    EditText et_code;
    @Bind(R.id.btn_regist)
    Button btn_regist;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_phonelogin;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if(!TextUtils.isEmpty(et_code.getText().toString())&&!TextUtils.isEmpty(et_phone.getText().toString())){
            btn_regist.setAlpha(1f);
            btn_regist.setClickable(true);
        }else {
            btn_regist.setAlpha(0.5f);
            btn_regist.setClickable(false);
        }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        et_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!TextUtils.isEmpty(et_code.getText().toString())&&!TextUtils.isEmpty(et_phone.getText().toString())){
                    btn_regist.setAlpha(1f);
                    btn_regist.setClickable(true);
                }else {
                    btn_regist.setAlpha(0.5f);
                    btn_regist.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    String type="1";
    @OnClick({R.id.btn_regist,R.id.timertasktextview})
    public void onClick(View view){
        int viewid=view.getId();
        if(R.id.btn_regist==viewid){
            if(!checkPhoneNum(et_phone.getText().toString().trim())){
                ToastUtil.show("请输入正确手机号");
                return;
            }
            if (et_code.getText().toString().trim().length() <= 999 && et_code.getText().toString().trim().length() > 10000) {
                ToastUtil.show("请输入正确验证码");
                return;
            }

            RestClient.getYfmNovate(PhoneLoginActivity.this).post(Constant.API.YFM_USER_FASTLOGIN, new ParamMap.Build()
                    .addParams("phone_no", et_phone.getText().toString().trim())
                    .addParams("validate_code", et_code.getText().toString().trim())
                    .build(), new DmeycBaseSubscriber<YFMLoginBean>(PhoneLoginActivity.this) {
                @Override
                public void onSuccess(final YFMLoginBean bean) {
                    Util. savaYFMUserInfo(bean);
//                    if(TextUtils.isEmpty(SPUtils.getStringData(Constant.Config.IDENITY))){
                        type="1";
//                    }else {
//                        type=SPUtils.getStringData(Constant.Config.IDENITY);
//                    }
                    if("0".equals(type)){
                        Intent intent=new Intent(PhoneLoginActivity.this,BMainActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        SPUtils.savaStringData(Constant.Config.IDENITY,"1");
                        Intent intent=new Intent(PhoneLoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

//                    startActivity(new Intent(PhoneLoginActivity.this,MainActivity.class));
                }
                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    ToastUtil.show(e.getMessage());
                }
            });


        }else if(R.id.timertasktextview==viewid){
            sendCode();
        }
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
    /**
     * 手机号正则校验
     *
     * @param phoneNum
     * @return
     */
    private boolean checkPhoneNum(String phoneNum) {
        String telRegex = "[1][35678]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(phoneNum)) return false;
        else return phoneNum.matches(telRegex);

    }
}
