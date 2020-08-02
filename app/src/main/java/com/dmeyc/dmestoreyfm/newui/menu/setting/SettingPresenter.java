package com.dmeyc.dmestoreyfm.newui.menu.setting;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;

import java.util.HashMap;

/**
 * create by cxg on 2019/11/24
 */
public class SettingPresenter extends BasePresenter<ISettingView> {
    public void logout() {
        subscriber = new CustomSubscriber<BaseRespBean>(mView,mGuid) {

            @Override
            public void onSuccess(BaseRespBean bean) {
                mView.logoutSucc(bean.getMsg());
            }
        };
        RetrofitService.getInstance().logout(new HashMap<String,String>(),subscriber);
    }
}
