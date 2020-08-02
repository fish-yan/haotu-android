package com.dmeyc.dmestoreyfm.newui.menu.apply.group;

import com.dmeyc.dmestoreyfm.newbase.IBaseView;

import java.util.Map;

/**
 * create by cxg on 2019/11/24
 */
public interface IApplyGroupView extends IBaseView {
    Map<String,String> getParams();
    void httpRequestDataSucc();

}
