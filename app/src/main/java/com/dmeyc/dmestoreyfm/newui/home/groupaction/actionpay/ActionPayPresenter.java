package com.dmeyc.dmestoreyfm.newui.home.groupaction.actionpay;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.bean.WXSubmitBean;
import com.dmeyc.dmestoreyfm.bean.response.PayDetailBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;
import com.dmeyc.dmestoreyfm.ui.SubmitOrderActivity;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * create by cxg on 2019/12/2
 */
public class ActionPayPresenter extends BasePresenter<IActionPayView> {
    public void httpRequestData() {
        subscriber = new CustomSubscriber<PayDetailBean>(mView,mGuid) {
            @Override
            public void onSuccess(PayDetailBean bean) {
                mView.getDataSucc(bean.getData());
            }
        };
        RetrofitService.getInstance().payDetail(mView.getParams(),subscriber);
    }

    public void httpRequestOrder() {
        subscriber = new CustomSubscriber<WXSubmitBean>(mView,mGuid) {
            @Override
            public void onSuccess(WXSubmitBean bean) {
                mView.getOrderSucc(bean.getData());
            }
        };
        RetrofitService.getInstance().signUpActivity1(mView.getPayParams(),subscriber);
    }

}
