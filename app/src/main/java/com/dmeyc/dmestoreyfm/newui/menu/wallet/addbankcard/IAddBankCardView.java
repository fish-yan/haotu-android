package com.dmeyc.dmestoreyfm.newui.menu.wallet.addbankcard;

import com.dmeyc.dmestoreyfm.bean.ProjectTypeBean;
import com.dmeyc.dmestoreyfm.newbase.IBaseView;

import java.util.List;
import java.util.Map;

/**
 * create by cxg on 2019/11/25
 */
public interface IAddBankCardView extends IBaseView {
    Map<String,String> getCodeParams();
    void getCodeSucc();
    Map<String,String> getParams();
    void addBankCardSucc();

    void getBandListSucc(List<ProjectTypeBean.DataBean> data);
}
