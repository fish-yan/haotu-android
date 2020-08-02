package com.dmeyc.dmestoreyfm.newui.login;

import com.dmeyc.dmestoreyfm.bean.YFMLoginBean;
import com.dmeyc.dmestoreyfm.newbase.IBaseView;
import com.dmeyc.dmestoreyfm.newui.update.UpdateResultBean;

import java.util.Map;

/**
 * create by cxg on 2019/12/13
 */
public interface ILoginView extends IBaseView {
    void getCodeSucc();

    Map<String, String> getCodeParams();

    Map<String, String> getParams();

    void doLoginSucc(YFMLoginBean data);

    void unBindThirdPart();

    void bindThirdPart(YFMLoginBean bean);

    Map<String, String> getUpdateVersionParams();
    void getUpdateVerSucc(UpdateResultBean bean);
}
