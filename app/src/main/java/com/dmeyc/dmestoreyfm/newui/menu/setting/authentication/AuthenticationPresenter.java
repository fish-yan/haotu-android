package com.dmeyc.dmestoreyfm.newui.menu.setting.authentication;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;

/**
 * create by cxg on 2019/11/24
 */
public class AuthenticationPresenter extends BasePresenter<IAuthenticationView> {
    public void httpRequestData() {
        subscriber = new CustomSubscriber<BaseRespBean>(mView,mGuid) {
            @Override
            public void onSuccess(BaseRespBean o) {
                mView.httpRequestSucc();
            }
        };
        RetrofitService.getInstance().savedIdNo(mView.getParams(),subscriber);
    }
}
