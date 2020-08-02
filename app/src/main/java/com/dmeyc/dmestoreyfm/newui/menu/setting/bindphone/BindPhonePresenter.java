package com.dmeyc.dmestoreyfm.newui.menu.setting.bindphone;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;

/**
 * create by cxg on 2019/11/24
 */
public class BindPhonePresenter extends BasePresenter<IBIndPhoneView> {
    public void bindPhoneNo() {
        subscriber = new CustomSubscriber<BaseRespBean>(mView,mGuid) {
            @Override
            public void onSuccess(BaseRespBean bean) {
                mView.bindPhoneSucc();
            }
        };

        RetrofitService.getInstance().editUserInfo(mView.getParams(),subscriber);
    }

    public void getCode(){
        subscriber = new CustomSubscriber<BaseRespBean>(mView,mGuid) {
            @Override
            public void onSuccess(BaseRespBean bean) {
                mView.getCodeSucc();
            }
        };

        RetrofitService.getInstance().getCode(mView.getCodeParams(),subscriber);
    }
}
