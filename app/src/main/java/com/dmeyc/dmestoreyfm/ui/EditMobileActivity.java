package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.bean.event.EditMobileEvent;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.present.SmsPresenter;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.SnackBarUtil;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.dmeyc.dmestoreyfm.wedgit.TimerTaskTextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.OnClick;

import static com.dmeyc.dmestoreyfm.R.id.timertasktextview;

public class EditMobileActivity extends BaseActivity<SmsPresenter> implements TextWatcher {

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
        return R.layout.ac_edit_mobile;
    }

    @Override
    protected SmsPresenter initPresenter() {
        return new SmsPresenter();
    }

    @Override
    protected void initData() {
        etmobile.setText(Util.getBlurNumber(SPUtils.getStringData(Constant.Config.MOBILE)));
        timerTaskTextView.setStatus(true);
        etCode.addTextChangedListener(this);
        EventBus.getDefault().register(this);
    }

    @OnClick({timertasktextview,R.id.tv_submit})
    public void onClick(View view){
        switch (view.getId()){
            case timertasktextview:
                mPresenter.sendSmsCode(this, etmobile.getText().toString(),SmsPresenter.SMS_IMFORTIOM_UPDATE_CODE,new SmsPresenter.OnSmsSendLisener() {
                    @Override
                    public void onSuccess() {
                        timerTaskTextView.startTimer();
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
                        Intent intent = new Intent(EditMobileActivity.this, BindMobileActivity.class);
                        intent.putExtra(Constant.Config.TYPE,BindMobileActivity.TYPE_EDITBIND);
                        startActivity(intent);
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
     * 相机拍照callback
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void takePhotoCallBack(EditMobileEvent event){
        finish();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if(etCode.getText().length() == Constant.Config.SMS_CODE_LENGTH){
            tvSubmit.setClickable(true);
            tvSubmit.setBackgroundResource(R.drawable.shape_1radius_1a);
        }else{
            tvSubmit.setClickable(false);
            tvSubmit.setBackgroundResource(R.drawable.shape_1radius_c5);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }
}
