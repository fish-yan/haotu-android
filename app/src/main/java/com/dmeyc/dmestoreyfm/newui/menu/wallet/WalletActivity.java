package com.dmeyc.dmestoreyfm.newui.menu.wallet;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.HaseBandCardListBean;
import com.dmeyc.dmestoreyfm.bean.response.AccountBean;
import com.dmeyc.dmestoreyfm.config.CommonConfig;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.newbase.BaseMvpActivity;
import com.dmeyc.dmestoreyfm.newui.menu.wallet.bankcard.BankCardActivity;
import com.dmeyc.dmestoreyfm.ui.BillActivity;
import com.dmeyc.dmestoreyfm.ui.CreditCarActivity;
import com.dmeyc.dmestoreyfm.ui.WithDrawActivity;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * create by cxg on 2019/11/25
 */
public class WalletActivity extends BaseMvpActivity<IWalletView, WalletPresenter> implements IWalletView {

    @Bind(R.id.tv_balance)
    TextView mTvBalance;

    @Bind(R.id.tv_withdraw)
    TextView mTvWithdraw;

    private AccountBean.DataBean mBean;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, WalletActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected WalletPresenter createPresenter() {
        return new WalletPresenter();
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int setContentView() {
        return R.layout.activity_wallet;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        setTitle("钱包");
    }

    @Override
    protected void initData() {
        if (CommonConfig.isCanWithDraw()) {
            mTvWithdraw.setVisibility(View.VISIBLE);
            mPresenter.httpRequestData();
        }
    }

    @OnClick({R.id.tv_withdraw, R.id.civ_bill,
            R.id.civ_bank_card})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_withdraw:
//                mPresenter.withdraw();
                checkCard();
                break;
            case R.id.civ_bill:
                Intent intent = new Intent(this, BillActivity.class);
//                if ("非游客") type = client ，else null  todo
                intent.putExtra("type", "");
                startActivity(intent);
                break;
            case R.id.civ_bank_card:
                BankCardActivity.startActivity(this);
                break;
            default:
                break;
        }
    }

    @Override
    public void httpRequestSucc(AccountBean.DataBean bean) {
        mBean = bean;
        mTvBalance.setText(bean.getAvaliableAmount());
    }

    @Override
    public Map<String, String> getWithdrawParams() {
        Map<String, String> params = new HashMap<String, String>();
        // TODO: 2019/12/7
//        params.put("bankCradId",);
//        params.put("amount",);
        return params;
    }

    @Override
    public void withdrawSucc() {
        // 提现成功刷新页面
        mPresenter.httpRequestData();
    }

    public void checkCard() {
        RestClient.getYfmNovate(this).post(Constant.API.YFM_QUERYBANKLIST, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<HaseBandCardListBean>() {
            @Override
            public void onSuccess(HaseBandCardListBean bean) {
                if (bean.getData().size() == 0) {
                    StyledDialog.buildIosAlert(WalletActivity.this, "您还未绑定银行卡是否绑定", "", new MyDialogListener() {
                        @Override
                        public void onFirst() {
                            startActivity(new Intent(WalletActivity.this, CreditCarActivity.class));
                        }

                        @Override
                        public void onSecond() {
                        }
                    }).setBtnText("是", "否").show();
                } else {
                    startActivity(new Intent(WalletActivity.this, WithDrawActivity.class)
                            .putExtra("totlalamount", mBean.getTotalAmount()).
                                    putExtra("avaliablamount", mBean.getAvaliableAmount()).putExtra("fizilable", mBean.getFrozenAmount()));
                }
            }
        });
    }
}