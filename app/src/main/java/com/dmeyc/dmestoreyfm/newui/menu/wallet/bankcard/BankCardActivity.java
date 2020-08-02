package com.dmeyc.dmestoreyfm.newui.menu.wallet.bankcard;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.BankCardAdapter;
import com.dmeyc.dmestoreyfm.bean.BankListBean;
import com.dmeyc.dmestoreyfm.config.CommonConfig;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.constant.SPKey;
import com.dmeyc.dmestoreyfm.event.RefreshEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseMvpActivity;
import com.dmeyc.dmestoreyfm.newui.common.CommentRequestHelper;
import com.dmeyc.dmestoreyfm.newui.menu.setting.authentication.AuthenticationActivity;
import com.dmeyc.dmestoreyfm.newui.menu.wallet.addbankcard.AddBankCardActivity;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * create by cxg on 2019/11/25
 */
public class BankCardActivity extends BaseMvpActivity<IBankCardView, BankCardPresenter> implements IBankCardView {

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Bind(R.id.tv_total_card)
    TextView mTvTotalCard;
    private BankCardAdapter mAdapter;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, BankCardActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected BankCardPresenter createPresenter() {
        return new BankCardPresenter();
    }

    @Override
    protected boolean enableEventBus() {
        return true;
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int setContentView() {
        return R.layout.activity_bank_card;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        setTitle("我的银行卡");
        mAdapter = new BankCardAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.bindToRecyclerView(mRecyclerView);
    }

    @Override
    protected void initData() {
        mPresenter.getBandCardList();
    }

    @OnClick({R.id.iv_add_card})
    public void click(View view) {
        if ( !CommonConfig.isGroupOwner(SPUtils.getStringData(SPKey.AUTHENTICATION_RESULT+ SPUtils.getStringData(Constant.Config.USER_ID)))) {
            CommentRequestHelper.httpAuthentication(new CommentRequestHelper.CallBackAdapter() {
                @Override
                public void onSuccess(String string) {
                    if (CommonConfig.isGroupOwner(string)) {
                        AddBankCardActivity.startActivity(BankCardActivity.this);
                    } else {
                        AuthenticationActivity.startActivity(BankCardActivity.this);
                        ToastUtil.show("请先实名认证");
                    }
                }
            });
        } else {
            AddBankCardActivity.startActivity(this);
        }
    }


    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        return params;
    }

    @Override
    public void httpDataResp(List<BankListBean.DataBean> data) {
        mAdapter.replaceData(data);
        mTvTotalCard.setText("共" + data.size() + "张");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(RefreshEvent.BankCard event) {
        mPresenter.getBandCardList();
    }
}
