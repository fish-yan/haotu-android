package com.dmeyc.dmestoreyfm.newui.login.bindthirdpart;

import com.dmeyc.dmestoreyfm.bean.YFMLoginBean;
import com.dmeyc.dmestoreyfm.newbase.IBaseView;

import java.util.Map;

/**
 * create by cxg on 2019/12/13
 */
public interface IBindThirdPartView extends IBaseView {
    Map<String ,String > getParams();

    void httpDataSucc(YFMLoginBean bean);
}
