package com.dmeyc.dmestoreyfm.newui.home.search;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.bean.response.SearchHistoryBean;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;

import java.util.HashMap;

/**
 * create by cxg on 2019/12/2
 */
public class SearchHistoryPresenter extends BasePresenter<ISearchHistoryView> {
    public void httpRequestData() {
        subscriber = new CustomSubscriber<SearchHistoryBean>(mView,mGuid) {
            @Override
            public void onSuccess(SearchHistoryBean bean) {
                mView.httpDataSucc(bean.getData());
            }
        };
        RetrofitService.getInstance().searchHotKey(new HashMap<String,String>(),subscriber);
    }
}
