package com.dmeyc.dmestoreyfm.newui.menu.apply.coach;

import com.dmeyc.dmestoreyfm.newbase.IBaseView;

import java.util.Map;

/**
 * create by cxg on 2019/11/30
 */
public interface IApplyCoachView extends IBaseView {
    Map<String,String> getImageParams();

    Map<String, String> getParams();

    void httpRequestDataSucc();
}
