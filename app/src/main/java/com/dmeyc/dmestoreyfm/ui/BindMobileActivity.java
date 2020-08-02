package com.dmeyc.dmestoreyfm.ui;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.bean.LoginBean;
import com.dmeyc.dmestoreyfm.bean.event.EditMobileEvent;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.present.SmsPresenter;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.SnackBarUtil;
import com.dmeyc.dmestoreyfm.wedgit.TimerTaskTextView;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.OnClick;

public class BindMobileActivity extends BaseActivity<SmsPresenter> implements TextWatcher {

    public final static int TYPE_EDITBIND = 1;  //修改手机号绑定
    public final static int TYPE_FIRSTBIND = 2; //三方登录首次绑定手机号

    @Bind(R.id.et_mobile)
    EditText etmobile;
    @Bind(R.id.et_code)
    EditText etCode;
    @Bind(R.id.tv_submit)
    TextView tvSubmit;
    @Bind(R.id.timertasktextview)
    TimerTaskTextView timerTaskTextView;
    @Override
    protected int getLayoutRes() {
        return R.layout.ac_bind_mobile;
    }

    @Override
    protected SmsPresenter initPresenter() {
        return new SmsPresenter();
    }

    @Override
    protected void initData() {
        timerTaskTextView.setStatus(true);
        etCode.addTextChangedListener(this);
    }

    @OnClick({R.id.timertasktextview,R.id.tv_submit})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.timertasktextview:
                if(getType() == TYPE_EDITBIND && TextUtils.equals(etmobile.getText().toString(),SPUtils.getStringData(Constant.Config.MOBILE))){
                    SnackBarUtil.showShortSnackbar(etCode,"新输入的手机号不可以和之前的相同");
                    return;
                }
                mPresenter.sendSmsCode(this, etmobile.getText().toString(), SmsPresenter.SMS_IMFORTIOM_UPDATE_CODE,new SmsPresenter.OnSmsSendLisener() {
                    @Override
                    public void onSuccess() {
                        timerTaskTextView.startTimer();
                        SnackBarUtil.showShortSnackbar(etmobile,"验证码发送成功");
                    }

                    @Override
                    public void onFailure(String errMsg) {
                        SnackBarUtil.showShortSnackbar(etmobile,errMsg);
                    }
                });
                break;
            case R.id.tv_submit:
                mPresenter.checkSmsCode(this, etmobile.getText().toString(), etCode.getText().toString(), SmsPresenter.SMS_IMFORTIOM_UPDATE_CODE, new SmsPresenter.OnSmsCheckListener() {
                    @Override
                    public void onSuccess() {
                        if(getType() == TYPE_EDITBIND){ //修改手机号成功
                            SPUtils.savaStringData(Constant.Config.MOBILE,etmobile.getText().toString());
                            EventBus.getDefault().post(new EditMobileEvent(etmobile.getText().toString()));
                            finish();
                        }else if(getType() == TYPE_FIRSTBIND){ //三方首次登陆 绑定成功
                            RestClient.getNovate(BindMobileActivity.this).post(Constant.API.USER_LOGIN, new ParamMap.Build().addParams(Constant.Config.MOBILE, etmobile.getText().toString()).build(), new DmeycBaseSubscriber<LoginBean>(BindMobileActivity.this) {
                                @Override
                                public void onSuccess(LoginBean bean) {
                                    SPUtils.savaStringData(Constant.Config.TOKEN,bean.getData().getToken().getValue());
                                    SPUtils.savaStringData(Constant.Config.USER_ID,String.valueOf(bean.getData().getUser().getUserId()));
                                    SPUtils.savaStringData(Constant.Config.MOBILE,bean.getData().getUser().getMobile());
                                    SPUtils.savaBooleanData(Constant.Config.ISLOGIN,true);
                                    finish();
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(String errMsg) {
                        SnackBarUtil.showShortSnackbar(etmobile,errMsg);
                    }
                });
                break;
        }
    }

    /**
     * 获取type类型
     * @return
     */
    public int getType(){
        return getIntent().getIntExtra(Constant.Config.TYPE,TYPE_FIRSTBIND);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if(etCode.getText().length() == Constant.Config.SMS_CODE_LENGTH && etmobile.getText().length() == 11){
            tvSubmit.setClickable(true);
            tvSubmit.setBackgroundResource(R.drawable.shape_1radius_1a);
        }else{
            tvSubmit.setClickable(false);
            tvSubmit.setBackgroundResource(R.drawable.shape_1radius_c5);
        }
    }
}
