package com.dmeyc.dmestoreyfm.newui.menu.policy;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.api.exception.ApiException;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;
import com.dmeyc.dmestoreyfm.ui.ProdListBean;

/**
 * create by cxg on 2019/11/29
 */
public class PolicyPresenter extends BasePresenter<IPolicyView> {
    public void httpRequest() {
        subscriber = new CustomSubscriber<ProdListBean>(mView,mGuid) {
            @Override
            public void onSuccess(ProdListBean bean) {
                mView.getDataListSucc(bean.getData());
            }
            @Override
            public void onError(ApiException ex) {
                super.onError(ex);
                mView.onCloseRefresh();
            }
        };
        RetrofitService.getInstance().getSaveList(mView.getParams(),subscriber);
    }
}
