package com.dmeyc.dmestoreyfm.present;

import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.OrderDetailBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.OrderDetailActivity;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

/**
 * Created by jockie on 2018/6/1
 * Email:jockie911@gmail.com
 */

public class OrderDetailPresenter extends BasePresenter<IOrderDetailView,OrderDetailActivity> {

    public void getOrderData() {
        RestClient.getNovate(mActivity.get()).get(Constant.API.ORDER_DETAIL, new ParamMap.Build()
                .addParams("orderIds", mBaseView.getOrderId())
                .build(), new DmeycBaseSubscriber<OrderDetailBean>(mActivity.get()) {
            @Override
            public void onSuccess(OrderDetailBean bean) {
                mBaseView.requestDataSuccess(bean.getData());
            }
        });
    }

    public void leftSubmitClick() {
        switch (mBaseView.getLeftTvContent()){
            case "取消订单":
                mBaseView.requestOrderCancle();
                break;
            case "取消退货":
                mBaseView.requestOrderCancleGoods();
                break;
            case "申请退款":
                mBaseView.requestOrderPayBack();
                break;
        }
    }

    public void rightSubmitClick() {
        switch (mBaseView.getRightTvContent()){
            case "填写物流":
                mBaseView.writeLogistics();
                break;
            case "提醒发货":
                mBaseView.showMsg("提醒发货成功");
                break;
            case "付款":
                mBaseView.pay();
                break;
            case "确认收货":
                mBaseView.makeSureGoods();
                break;
            case "晒单":
                mBaseView.toEvaluate();
                break;
        }
    }

    public void contactMerchant(int serverId, String serverTitle) {
        RongIM.getInstance().startConversation(mActivity.get(), Conversation.ConversationType.PRIVATE,String.valueOf(serverId),serverTitle);
    }

    public void setStatus() {
        if(mBaseView.getRefundCode() != 0 ){
            mBaseView.setOrderRefund();
        }else{
            switch (mBaseView.getOrderStatus()){
                case Constant.OrderStatus.WAIT_PAY:
                   mBaseView.setWaitForPay();
                    break;
                case Constant.OrderStatus.COMMITTED:
                    mBaseView.setCommitted();
                    break;
                case Constant.OrderStatus.STAY:
                    mBaseView.setStay();
                    break;
                case Constant.OrderStatus.EVALUATE:
                   mBaseView.setEvaluate();
                   break;
                case Constant.OrderStatus.FINISH:
                   mBaseView.setFinish();
                    break;
            }
        }
    }
}
