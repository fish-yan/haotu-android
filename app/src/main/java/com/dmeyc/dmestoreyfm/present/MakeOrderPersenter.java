package com.dmeyc.dmestoreyfm.present;

import android.text.TextUtils;

import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.SelectInfo;
import com.dmeyc.dmestoreyfm.bean.common.CommonBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.dialog.PayDialog;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.MakeOrderActivity;

/**
 * Created by jockie on 2018/5/29
 * Email:jockie911@gmail.com
 */

public class MakeOrderPersenter extends BasePresenter<IMakeOrderView,MakeOrderActivity> {

    public void pay() {
        if(mBaseView.getReceiverId() == 0){
            mBaseView.showMsg("请先添加收货地址");
            return;
        }
        ParamMap.Build build = new ParamMap.Build();
        if(!mBaseView.isDirectBuy()){
            build.addParams("cartItemIds",mBaseView.getCartItemIds());
        }else{
            if(mBaseView.isCustom()){
                SelectInfo selectInfo = mBaseView.getSelectInfo();
                if(selectInfo.isTailorRecord()){
                    build.addParams("measureId",selectInfo.getTailorRecordId());
                }else{
                    build.addParams("sizeCustom",selectInfo.getSizeInfo());
                }

                if(!TextUtils.isEmpty(mBaseView.getCustoms()))
                    build.addParams("customs",mBaseView.getCustoms());
            }
            build.addParams("productId",mBaseView.getProductId());
        }
        build.addParams("receiverId",mBaseView.getReceiverId());
        if(mBaseView.getCouponId() != 0)
            build.addParams("coupon",mBaseView.getCouponId());
        RestClient.getNovate(mActivity.get()).get(Constant.API.ORDER_CREATE, build.build(), new DmeycBaseSubscriber<CommonBean>(mActivity.get()) {
            @Override
            public void onSuccess(CommonBean bean) {
                new PayDialog(mActivity.get(),(String) bean.getData()).show();
            }
        });
    }
}
