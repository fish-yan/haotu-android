package com.dmeyc.dmestoreyfm.newui.home.goodsdetail;

import com.dmeyc.dmestoreyfm.bean.response.GoodsDetailBean;
import com.dmeyc.dmestoreyfm.newbase.IBaseView;

import java.util.Map;

/**
 * create by cxg on 2020/1/1
 */
public interface IGoodsDetailView extends IBaseView {
    Map<String, String> getParams();

    void httpDataSucc(GoodsDetailBean.DataBean bean);
}
