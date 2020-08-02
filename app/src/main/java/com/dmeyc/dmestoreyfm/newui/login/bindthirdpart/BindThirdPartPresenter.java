package com.dmeyc.dmestoreyfm.newui.login.bindthirdpart;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.bean.YFMLoginBean;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;

/**
 * create by cxg on 2019/12/13
 */
public class BindThirdPartPresenter extends BasePresenter<IBindThirdPartView> {
    public void httpRequestData() {
        subscriber = new CustomSubscriber<YFMLoginBean>(mView,mGuid) {
            @Override
            public void onSuccess(YFMLoginBean bean) {
                mView.httpDataSucc(bean);
            }
        };
        RetrofitService.getInstance().thirdPartLogin(mView.getParams(),subscriber);
    }
}
