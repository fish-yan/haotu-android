package com.dmeyc.dmestoreyfm.newui.pagerdetail;

import com.dmeyc.dmestoreyfm.bean.response.AccountInfoBean;
import com.dmeyc.dmestoreyfm.newbase.IBaseView;

/**
 * create by cxg on 2019/12/1
 */
public interface IPagerDetailView extends IBaseView {
    void httpRequestSucc(AccountInfoBean.DataBean data);
}
