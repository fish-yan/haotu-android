package com.dmeyc.dmestoreyfm.newui.menu.wallet.bankcard;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.bean.BankListBean;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * create by cxg on 2019/11/25
 */
public class BankCardPresenter extends BasePresenter<IBankCardView> {
    public void getBandCardList() {
        subscriber = new CustomSubscriber<BankListBean>(mView,mGuid) {
            @Override
            public void onSuccess(BankListBean bean) {
                List<BankListBean.DataBean> data = bean.getData();
                if (data == null){
                    data = new ArrayList<>();
                }
                mView.httpDataResp(data);
            }
        };
        RetrofitService.getInstance().getBandCardList(mView.getParams(),subscriber);
    }
}
