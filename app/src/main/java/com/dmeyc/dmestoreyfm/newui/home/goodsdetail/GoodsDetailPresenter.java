package com.dmeyc.dmestoreyfm.newui.home.goodsdetail;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.bean.response.GoodsDetailBean;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;

/**
 * create by cxg on 2020/1/1
 */
public class GoodsDetailPresenter extends BasePresenter<IGoodsDetailView> {
    public void httpRequestData() {
        subscriber = new CustomSubscriber<GoodsDetailBean>(mView,mGuid) {
            @Override
            public void onSuccess(GoodsDetailBean o) {
                mView.httpDataSucc(o.getData());
            }
        };
        RetrofitService.getInstance().getCommodityById(mView.getParams(),subscriber);
    }
}
