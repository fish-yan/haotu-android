package com.dmeyc.dmestoreyfm.newui.menu.setting.accountsave;

import com.dmeyc.dmestoreyfm.bean.response.AccountInfoBean;
import com.dmeyc.dmestoreyfm.newbase.IBaseView;

/**
 * create by cxg on 2019/11/24
 */
interface IAccountSaveView extends IBaseView {
    void httpRequestSucc(AccountInfoBean.DataBean data);
}
