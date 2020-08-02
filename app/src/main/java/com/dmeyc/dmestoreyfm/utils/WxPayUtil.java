package com.dmeyc.dmestoreyfm.utils;

import android.content.Context;

import com.dmeyc.dmestoreyfm.bean.WXSubmitBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.ui.SubmitOrderActivity;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * create by cxg on 2019/12/22
 */
public class WxPayUtil {
    /**
     * 根据订单orderId去获取预生成订单详情
     */
    public static void prePay(Context context, WXSubmitBean.DataBean.PayInfoBean data) {
//        if(payMethod == Constant.Config.PAY_WX){
//            RestClient.getNovate(SubmitOrderActivity.this, Constant.API.BASE_URL).get(Constant.API.PAY_WX, new ParamMap.Build().addParams("orderIds", orderId).build(), new DmeycBaseSubscriber<WXPayBean>(mContext) {
//                @Override
//                public void onSuccess(WXPayBean bean) {
//                    SPUtils.savaStringData(Constant.Config.ORDER_ID,orderId.contains(",") ? orderId.split(",")[0] : orderId);

        IWXAPI msgApi = WXAPIFactory.createWXAPI(context, null);
        msgApi.registerApp(Constant.Key.WEIXIN_APPID);

        PayReq request = new PayReq();
        request.appId = Constant.Key.WEIXIN_APPID;
        request.partnerId = data.getPartnerid();
        request.prepayId = data.getPrepayid();
//                    request.packageValue = data.getPackageX();
        request.packageValue = "Sign=WXPay";
        request.nonceStr = data.getNonceStr();
        request.timeStamp = String.valueOf(data.getTimeStamp());
        request.sign = data.getSign();
        msgApi.sendReq(request);
    }
}
