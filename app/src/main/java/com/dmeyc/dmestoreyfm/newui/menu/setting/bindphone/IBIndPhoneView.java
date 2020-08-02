package com.dmeyc.dmestoreyfm.newui.menu.setting.bindphone;

import com.dmeyc.dmestoreyfm.newbase.IBaseView;

import java.util.Map;

/**
 * create by cxg on 2019/11/24
 */
public interface IBIndPhoneView extends IBaseView {
    Map<String,String> getCodeParams();
    void getCodeSucc();
    Map<String,String> getParams();
    void bindPhoneSucc();
}
