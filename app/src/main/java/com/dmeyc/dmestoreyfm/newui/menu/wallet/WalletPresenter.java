package com.dmeyc.dmestoreyfm.newui.menu.wallet;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.bean.response.AccountBean;
import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;
import com.dmeyc.dmestoreyfm.newbase.IBaseView;

import java.util.HashMap;

/**
 * create by cxg on 2019/11/25
 */
public class WalletPresenter extends BasePresenter<IWalletView> {
    public void httpRequestData() {
        subscriber = new CustomSubscriber<AccountBean>(mView,mGuid) {
            @Override
            public void onSuccess(AccountBean bean) {
                AccountBean.DataBean data = bean.getData();
                if (data!=null){
                    mView.httpRequestSucc(data);
                }
            }
        };
        RetrofitService.getInstance().getAccount(new HashMap<String,String>(),subscriber);
    }

    public void withdraw() {
        subscriber = new CustomSubscriber<BaseRespBean>(mView,mGuid) {
            @Override
            public void onSuccess(BaseRespBean o) {
                mView.withdrawSucc();
            }
        };
        RetrofitService.getInstance().withdraw(mView.getWithdrawParams(),subscriber);
    }
}
