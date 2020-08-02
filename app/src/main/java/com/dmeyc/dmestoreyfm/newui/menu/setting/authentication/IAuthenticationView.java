package com.dmeyc.dmestoreyfm.newui.menu.setting.authentication;

import com.dmeyc.dmestoreyfm.newbase.IBaseView;

import java.util.Map;

/**
 * create by cxg on 2019/11/24
 */
public interface IAuthenticationView extends IBaseView {
    Map<String,String> getParams();

    void httpRequestSucc();
}
