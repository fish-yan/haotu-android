package com.dmeyc.dmestoreyfm.video.merchantentry.register;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.BMainActivity;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.video.merchantentry.claim.ClaimActivity;
import com.dmeyc.dmestoreyfm.video.systeminfo.ReadMsgModel;
import com.dmeyc.dmestoreyfm.wedgit.CommDialog;
import com.tamic.novate.Throwable;

import butterknife.Bind;
import butterknife.OnClick;

public class MerchantentryRegisterActivity extends BaseActivity {

    @Bind(R.id.ll_mobile_parent)
    LinearLayout ll_mobile_parent;

    @Bind(R.id.ll_code_parent)
    LinearLayout ll_code_parent;

    @Bind(R.id.tv_title)
    TextView tv_title;

    @Bind(R.id.edit_mobile)
    EditText edit_mobile;

    @Bind(R.id.edit_code)
    EditText edit_code;

    @Bind(R.id.tv_get_mobile_code)
    TextView tv_get_mobile_code;

    @Bind(R.id.btn_to_sure)
    TextView btn_to_sure;


    @OnClick(R.id.tv_get_mobile_code)
    void nMobileCodeClick() {
        // 获取验证码
        String mobile = edit_mobile.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)) {
            ToastUtil.show("手机号不能为空");
        } else {
            sendVaildCode(mobile);
        }
    }


    @OnClick(R.id.btn_to_sure)
    void toRegister() {
        String mobile = edit_mobile.getText().toString().trim();
        String code = edit_code.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)) {
            ToastUtil.show("手机号不能为空");
            return;
        }
        if (TextUtils.isEmpty(code)) {
            ToastUtil.show("验证码不能为空");
            return;
        }
        regist(mobile, code);
    }

    public static void newIntent(Activity activity) {
        Intent intent = new Intent(activity, MerchantentryRegisterActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.ac_merchantentry_register;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        tv_title.setText("注册");
        tv_get_mobile_code.setEnabled(false);
        setTextWatcher();
    }

    /**
     * 发送验证码
     *
     * @param mobile
     */
    private void sendVaildCode(String mobile) {

        RestClient.getYfmNovate(this).post(Constant.API.YFM_BASE_URL + "/api/v1.0/user/sendValidCode?phone_no=" + mobile, new ParamMap.Build()
//                .addParams("phone_no ", mobile)
                .build(), new DmeycBaseSubscriber<ReadMsgModel>() {
            @Override
            public void onSuccess(ReadMsgModel gensign) {
                if (gensign.getCode()==200) {
                    timeCountDown();
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }

    /**
     * 第一步注册
     *
     * @param mobile
     * @param code
     */
    private void regist(String mobile, String code) {
//
        RestClient.getYfmNovate(this).post(Constant.API.YFM_BASE_URL + "api/v2.0/business/addBusiness?phone_no=" + mobile + "&user_token=" + SPUtils.getStringData(Constant.Config.TOKEN)
                        + "&phone_code=" + code,
                new ParamMap.Build()
                        .build(),
                new DmeycBaseSubscriber<MerchantentryRegistModel>() {
                    @Override
                    public void onSuccess(MerchantentryRegistModel gensign) {
                        if (gensign.getData().getIsBusiness() == 1) {
                            builder = new CommDialog.Builder(MerchantentryRegisterActivity.this);
                            showTwoButtonDialog("您已经是商家用户，切换身份进入商户端吗？", "取消", "切换身份", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mDialog.dismiss();
                                    //这里写自定义处理XXX
                                }
                            }, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mDialog.dismiss();
                                    startActivity(new Intent(MerchantentryRegisterActivity.this, BMainActivity.class));
                                    finish();
                                }
                            });
                        } else {
                            ClaimActivity.newIntent(MerchantentryRegisterActivity.this, gensign.getData().getBusinessToken());
                            finish();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    /**
     * 设置输入框的变化
     */
    private void setTextWatcher() {
        edit_mobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String s = edit_mobile.getText().toString().trim();
                String c = edit_code.getText().toString().trim();
                if (!TextUtils.isEmpty(s)) {
                    ll_mobile_parent.setSelected(true);
                    tv_get_mobile_code.setTextColor(Color.parseColor("#32AAFF"));
                    tv_get_mobile_code.setEnabled(true);
                } else {
                    ll_mobile_parent.setSelected(false);
                    tv_get_mobile_code.setTextColor(Color.parseColor("#cccccc"));
                    tv_get_mobile_code.setEnabled(false);
                }
                if (!TextUtils.isEmpty(s) && !TextUtils.isEmpty(c)) {
                    btn_to_sure.setSelected(true);
                    btn_to_sure.setEnabled(true);
                } else {
                    btn_to_sure.setSelected(false);
                    btn_to_sure.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        edit_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String s = edit_code.getText().toString().trim();
                String c = edit_mobile.getText().toString().trim();
                if (!TextUtils.isEmpty(s)) {
                    ll_code_parent.setSelected(true);
                } else {
                    ll_code_parent.setSelected(false);
                }
                if (!TextUtils.isEmpty(s) && !TextUtils.isEmpty(c)) {
                    btn_to_sure.setSelected(true);
                    btn_to_sure.setEnabled(true);
                } else {
                    btn_to_sure.setSelected(false);
                    btn_to_sure.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    /**
     * 获取验证码倒计时
     * 倒计时60秒，一次1秒
     */
    private void timeCountDown() {
        new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tv_get_mobile_code.setEnabled(false);
                tv_get_mobile_code.setTextColor(Color.parseColor("#cccccc"));
                tv_get_mobile_code.setText(String.format(getResources().getString(R.string.tm_login_count_down_format), millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                tv_get_mobile_code.setEnabled(true);
                tv_get_mobile_code.setTextColor(Color.parseColor("#32aaff"));
                tv_get_mobile_code.setText("重新获取");
            }
        }.start();
    }

    private CommDialog.Builder builder;
    private CommDialog mDialog;
    private void showTwoButtonDialog(String alertText, String confirmText, String cancelText, View.OnClickListener conFirmListener, View.OnClickListener cancelListener) {
        mDialog = builder.setMessage(alertText)
                .setPositiveButton(confirmText, conFirmListener)
                .setNegativeButton(cancelText, cancelListener)
                .createTwoButtonDialog();
        mDialog.show();
    }

}
