package com.dmeyc.dmestoreyfm.newui.home.groupaction.actionpay;

import com.dmeyc.dmestoreyfm.bean.WXSubmitBean;
import com.dmeyc.dmestoreyfm.bean.response.PayDetailBean;
import com.dmeyc.dmestoreyfm.newbase.IBaseView;

import java.util.Map;

/**
 * create by cxg on 2019/12/2
 */
public interface IActionPayView extends IBaseView {
    Map<String,String> getParams();

    void getDataSucc(PayDetailBean.DataBean data);

    Map<String,String> getPayParams();

    void getOrderSucc(WXSubmitBean.DataBean payInfo);
}
