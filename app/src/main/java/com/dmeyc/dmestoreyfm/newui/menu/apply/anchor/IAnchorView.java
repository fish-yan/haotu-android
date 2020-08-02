package com.dmeyc.dmestoreyfm.newui.menu.apply.anchor;

import com.dmeyc.dmestoreyfm.newbase.IBaseView;

import java.util.Map;

/**
 * create by cxg on 2019/11/30
 */
public interface IAnchorView extends IBaseView {
    void httpRequestSucc();

    Map<String,String> getParams();
    Map<String,String> getImageParams();
}
