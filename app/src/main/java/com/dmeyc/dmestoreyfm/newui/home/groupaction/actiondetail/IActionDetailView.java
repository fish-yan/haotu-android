package com.dmeyc.dmestoreyfm.newui.home.groupaction.actiondetail;

import com.dmeyc.dmestoreyfm.bean.response.ActivityDetailBean;
import com.dmeyc.dmestoreyfm.newbase.IBaseView;

import java.util.Map;

/**
 * create by cxg on 2019/12/1
 */
public interface IActionDetailView extends IBaseView {
    Map<String,String> getParams();

    void httpDataSucc(ActivityDetailBean.DataBean bean);

    void getShareUrlSucc(String data);
}
