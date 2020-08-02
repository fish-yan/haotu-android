package com.dmeyc.dmestoreyfm.newui.release.goods;

import com.dmeyc.dmestoreyfm.newbase.IBaseView;

import java.util.Map;

/**
 * create by cxg on 2019/11/30
 */
public interface IReleaseGoodsView extends IBaseView {

    void httpDataSucc();

    Map<String,String> getParams();
}
