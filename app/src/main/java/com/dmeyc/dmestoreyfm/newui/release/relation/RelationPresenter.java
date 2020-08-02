package com.dmeyc.dmestoreyfm.newui.release.relation;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.api.exception.ApiException;
import com.dmeyc.dmestoreyfm.bean.response.RelationBean;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;

/**
 * create by cxg on 2019/11/30
 */
public class RelationPresenter extends BasePresenter<IRelationView> {
    public void httpRequestData() {
        subscriber = new CustomSubscriber<RelationBean>(mView,mGuid) {
            @Override
            public void onSuccess(RelationBean bean) {
                mView.getDataListSucc(bean.getData());
            }
            @Override
            public void onError(ApiException ex) {
                super.onError(ex);
                mView.onCloseRefresh();
            }
        };
        RetrofitService.getInstance().listLinkedGroups(mView.getParams(),subscriber);
    }
}
