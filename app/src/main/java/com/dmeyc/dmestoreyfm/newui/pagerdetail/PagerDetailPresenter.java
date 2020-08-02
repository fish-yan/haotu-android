package com.dmeyc.dmestoreyfm.newui.pagerdetail;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.bean.response.AccountInfoBean;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;

import java.util.HashMap;

/**
 * create by cxg on 2019/12/1
 */
public class PagerDetailPresenter extends BasePresenter<IPagerDetailView> {
    public void httpRequestData() {
        subscriber = new CustomSubscriber<AccountInfoBean>(mView,mGuid) {
            @Override
            public void onSuccess(AccountInfoBean bean) {
                if(bean.getData()!=null){
                    mView.httpRequestSucc(bean.getData());
                }
            }
        };
        RetrofitService.getInstance().queryUserInfo(new HashMap<String,String>(),subscriber);
    }
}