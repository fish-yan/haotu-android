package com.dmeyc.dmestoreyfm.newui.menu.club;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.api.exception.ApiException;
import com.dmeyc.dmestoreyfm.bean.response.MyClubBean;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;

/**
 * create by cxg on 2019/11/28
 */
public class MyClubPresenter extends BasePresenter<IMyClubView> {

    public void httpRequestData() {
        subscriber = new CustomSubscriber<MyClubBean>(mView,mGuid) {
            @Override
            public void onSuccess(MyClubBean bean) {
                mView.getDataListSucc(bean.getData());
            }
            @Override
            public void onError(ApiException ex) {
                super.onError(ex);
                mView.onCloseRefresh();
            }
        };
        RetrofitService.getInstance().queryMyGroup(mView.getParams(),subscriber);
    }
}
