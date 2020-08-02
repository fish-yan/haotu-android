package com.dmeyc.dmestoreyfm.ui;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.common.CommonBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SnackBarUtil;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.MoveEditText;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class ApplyPayBackActivity extends BaseActivity implements TextWatcher {

    @Bind(R.id.tv_reason_back)
    TextView tvReasonBack;
    @Bind(R.id.met_phone)
    MoveEditText metPhone;
    @Bind(R.id.met_contact)
    MoveEditText metContact;
    @Bind(R.id.et_backmoney)
    EditText etBackMoney;
    @Bind(R.id.tv_max_backmoney)
    TextView tvMaxBackMoney;
    private List<String> list;
    private double mMaxMoney; // 可以退款的最大金额数 不超过订单的实际支付价格,前段简单检测，实际由后台控制

    @Override
    protected int getLayoutRes() {
        return R.layout.ac_apply_pay_back;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        tvTitle.setText("申请退款");

        list = Arrays.asList(getResources().getStringArray(R.array.reason_order_back));

        mMaxMoney = getIntent().getDoubleExtra("maxMoney", 0);
        tvMaxBackMoney.setText("最大退款金额: " + mMaxMoney);
        etBackMoney.addTextChangedListener(this);
    }

    @OnClick({R.id.tv_reason_back,R.id.tv_submit})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_reason_back:
                OptionsPickerView pvOptions = new  OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                        tvReasonBack.setText(list.get(options1));
                    }
                })
                        .setSubmitText("确定")//确定按钮文字
                        .setCancelText("关闭")//取消按钮文字
                        .setSubCalSize(14)//确定和取消文字大小
                        .setSubmitColor(Color.parseColor("#007aff"))//确定按钮文字颜色
                        .setCancelColor(Color.parseColor("#1a1a1a"))//取消按钮文字颜色
                        .setTitleBgColor(0xFFFAFAFA)//标题背景颜色 Night mode
                        .setBgColor(0xFFFFFFFF)//滚轮背景颜色 Night mode
                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                        .setDividerColor(Color.parseColor("#dfdfdf"))
                        .build();
                pvOptions.setPicker(list);//添加数据源
                pvOptions.show();
                break;
            case R.id.tv_submit:
                if(isCanUpload()){
                    applyBack();
                }
                break;
        }
    }


    /**
     * 申请退款
     */
    private void applyBack(){
        RestClient.getNovate(this).post(Constant.API.ORDER_RETURN_FUND, new ParamMap.Build()
                .addParams("orderId", getIntent().getIntExtra("orderId",0))
                .addParams("orderItemId", getIntent().getIntExtra("orderItemId",0))
                .addParams("status", getIntent().getIntExtra("status",0)) //TODO 修改status
                .addParams("refundReason", tvReasonBack.getText().toString())
                .addParams("refundAmount", etBackMoney.getText().toString())
                .addParams("refundPeople", metContact.getText().toString())
                .addParams("refundPhone", metPhone.getText().toString()).build(), new DmeycBaseSubscriber<CommonBean>(this) {

            @Override
            public void onSuccess(CommonBean bean) {
                ToastUtil.show("商家已收到您的申请退款申请,请等待商家处理");
                finish();
            }
        });
    }

    private boolean isCanUpload(){
        if(TextUtils.isEmpty(tvReasonBack.getText().toString())){
            SnackBarUtil.showShortSnackbar(tvTitle,"请选择退款原因");
            return false;
        }
        if(TextUtils.isEmpty(etBackMoney.getText().toString())){
            SnackBarUtil.showShortSnackbar(tvTitle,"请输入退款金额");
            return false;
        }
        if(Double.parseDouble(etBackMoney.getText().toString()) == 0){
            SnackBarUtil.showShortSnackbar(tvTitle,"请输入正确的退款金额");
            return false;
        }
        if(TextUtils.isEmpty(metContact.getText().toString())){
            SnackBarUtil.showShortSnackbar(tvTitle,"请输入退款联系人姓名");
            return false;
        }
        if(TextUtils.isEmpty(metPhone.getText().toString())){
            SnackBarUtil.showShortSnackbar(tvTitle,"请输入退款联系人电话");
            return false;
        }
        return true;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if(!TextUtils.isEmpty(s) && Double.parseDouble(s.toString()) > mMaxMoney){
            SnackBarUtil.showShortSnackbar(tvTitle,"不允许超过最大退款金额");
            etBackMoney.setText(String.valueOf(mMaxMoney));
            etBackMoney.setSelection(String.valueOf(mMaxMoney).length());
        }
    }
}
