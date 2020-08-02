package com.dmeyc.dmestoreyfm.newui.pagerdetail.other;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.bean.response.AccountInfoBean;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;
import com.dmeyc.dmestoreyfm.newbase.IBaseView;

import java.util.HashMap;

/**
 * create by cxg on 2019/12/19
 */
public class OtherDetailPresenter extends BasePresenter<IOtherDetailView> {
    public void httpRequestData() {
        subscriber = new CustomSubscriber<AccountInfoBean>(mView,mGuid) {
            @Override
            public void onSuccess(AccountInfoBean bean) {
                if(bean.getData()!=null){
                    mView.httpRequestSucc(bean.getData());
                }
            }
        };
        RetrofitService.getInstance().queryUserInfo(mView.getParams(),subscriber);
    }
}
