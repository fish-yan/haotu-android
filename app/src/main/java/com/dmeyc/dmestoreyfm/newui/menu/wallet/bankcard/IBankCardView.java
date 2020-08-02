package com.dmeyc.dmestoreyfm.newui.menu.wallet.bankcard;

import com.dmeyc.dmestoreyfm.bean.BankListBean;
import com.dmeyc.dmestoreyfm.newbase.IBaseView;

import java.util.List;
import java.util.Map;

/**
 * create by cxg on 2019/11/25
 */
public interface IBankCardView extends IBaseView {
    Map<String,String> getParams();
    void httpDataResp(List<BankListBean.DataBean> data);
}
