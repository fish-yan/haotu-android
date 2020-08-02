package com.dmeyc.dmestoreyfm.newui.pagerdetail.other;

import com.dmeyc.dmestoreyfm.bean.response.AccountInfoBean;
import com.dmeyc.dmestoreyfm.newbase.IBaseView;

import java.util.Map;

/**
 * create by cxg on 2019/12/19
 */
public interface IOtherDetailView extends IBaseView {
    Map<String, String> getParams();

    void httpRequestSucc(AccountInfoBean.DataBean data);
}
