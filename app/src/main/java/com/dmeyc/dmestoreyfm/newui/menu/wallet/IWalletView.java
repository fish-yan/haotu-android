package com.dmeyc.dmestoreyfm.newui.menu.wallet;

import com.dmeyc.dmestoreyfm.bean.response.AccountBean;
import com.dmeyc.dmestoreyfm.newbase.IBaseView;

import java.util.Map;

/**
 * create by cxg on 2019/11/25
 */
public interface IWalletView extends IBaseView {

    void httpRequestSucc(AccountBean.DataBean totalAmount);

    Map<String,String> getWithdrawParams();

    void withdrawSucc();
}
