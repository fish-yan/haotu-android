package com.dmeyc.dmestoreyfm.newui.home.groupaction.actionpay;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.adapter.PaySafeAdapter;
import com.dmeyc.dmestoreyfm.bean.WXSubmitBean;
import com.dmeyc.dmestoreyfm.bean.response.PayDetailBean;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.event.MyEvent;
import com.dmeyc.dmestoreyfm.event.RefreshEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseMvpActivity;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.utils.WxPayUtil;
import com.dmeyc.dmestoreyfm.wedgit.CustomItemView1;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * create by cxg on 2019/12/2
 */
public class ActionPayActivity extends BaseMvpActivity<IActionPayView, ActionPayPresenter> implements IActionPayView, CustomItemView1.OnTextChangeListener {

    private String mManPay;
    private String mWomenPay;

    @Bind(R.id.civ1_man_pay)
    CustomItemView1 mCiv1ManPay;
    @Bind(R.id.civ1_woman_pay)
    CustomItemView1 mCiv1WomanPay;
    @Bind(R.id.tv_total_pay)
    TextView mTvTotalPay;
    @Bind(R.id.tv_confirm)
    TextView mTvConfirm;

    @Bind(R.id.rv_safe)
    RecyclerView mRvSafe;

    private String mActivityId;
    private PaySafeAdapter mAdapter;
    private List<PayDetailBean.DataBean.PayListBean> mPayList;

    public static void newInstance(Context context, String activityId) {
        Intent intent = new Intent(context, ActionPayActivity.class);
        intent.putExtra(ExtraKey.ACTIVITY_ID, activityId);
        context.startActivity(intent);
    }

    @Override
    protected boolean enableEventBus() {
        return true;
    }

    @Override
    protected ActionPayPresenter createPresenter() {
        return new ActionPayPresenter();

    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int setContentView() {
        return R.layout.activity_action_pay;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        setTitle("报名支付");
        mActivityId = getIntent().getStringExtra(ExtraKey.ACTIVITY_ID);

        mCiv1ManPay.setListener(this);
        mCiv1WomanPay.setListener(this);

        mAdapter = new PaySafeAdapter(new PaySafeAdapter.ICallBack() {

            @Override
            public void onTextCountChanger() {
                onTextChange(-1);
            }
        });
        mRvSafe.setAdapter(mAdapter);
        mRvSafe.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.bindToRecyclerView(mRvSafe);
    }

    @Override
    protected void initData() {
        mPresenter.httpRequestData();
    }

    @Override
    public void onTextChange(int nowCount) {
        if (TextUtils.isEmpty(mManPay) || TextUtils.isEmpty(mWomenPay)) {
            ToastUtil.show("数据异常");
            return;
        }

        BigDecimal manPay = new BigDecimal(mManPay);
        BigDecimal manCount = new BigDecimal(mCiv1ManPay.getCount());
        BigDecimal womenPay = new BigDecimal(mWomenPay);
        BigDecimal womenCount = new BigDecimal(mCiv1WomanPay.getCount());

        BigDecimal safe = new BigDecimal(0);
        List<PayDetailBean.DataBean.SafeListBean> datas = mAdapter.getData();
        if (datas != null) {
            for (PayDetailBean.DataBean.SafeListBean bean : datas) {
                safe.add((new BigDecimal(bean.getExt()).multiply(new BigDecimal(bean.getBuyCount()))));
            }
        }
        mTvTotalPay.setText(manPay.multiply(manCount).add(womenPay.multiply(womenCount)).add(safe).toString());
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("activity_id", mActivityId);
        return params;
    }

    @Override
    public void getDataSucc(PayDetailBean.DataBean data) {
        mManPay = data.getManPice();
        mWomenPay = data.getWomanPrice();
        mPayList = data.getPayList();
        mCiv1ManPay.setTip(data.getManPice());
        mCiv1WomanPay.setTip(data.getWomanPrice());
        mAdapter.replaceData(data.getSafeList());
    }

    @Override
    public Map<String, String> getPayParams() {
        Map<String, String> params = new HashMap<>();
        params.put("activity_id", mActivityId);
        params.put("payType", "1");
        params.put("totalAmount", mTvTotalPay.getText().toString());
        params.put("manNumber", mCiv1ManPay.getCount() + "");
        params.put("womanNumber", mCiv1WomanPay.getCount() + "");
        StringBuffer sb = new StringBuffer();
        List<PayDetailBean.DataBean.SafeListBean> data = mAdapter.getData();
        if (data != null && data.size() > 0) {
            for (PayDetailBean.DataBean.SafeListBean item : data) {
                if (item.getBuyCount() > 0) {
                    sb.append(item.getItemCode() + ":" + item.getBuyCount() + ",");
                }
            }
        }
        if (sb.length() > 0) {
            params.put("safeStr", sb.substring(0, sb.length() - 1));
        }

        return params;
    }

    @Override
    public void getOrderSucc(WXSubmitBean.DataBean payInfo) {
        if ("0.00".equals(mTvTotalPay.getText().toString()) && payInfo == null) {
            paySuccess();
        } else {
            WxPayUtil.prePay(this, payInfo.getPayInfo());
        }
    }

    @OnClick({R.id.tv_confirm})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_confirm:

                if (mCiv1ManPay.getCount() == 0 && mCiv1WomanPay.getCount() == 0) {
                    ToastUtil.show("报名人数至少一人");
                    return;
                }

                mPresenter.httpRequestOrder();
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPaySuccess(MyEvent.WxPaySuccess event) {
        paySuccess();
    }

    private void paySuccess() {
        EventBus.getDefault().post(new RefreshEvent.ActionDetailActivity());
        finish();
    }
}
