package com.dmeyc.dmestoreyfm.present;

import com.dmeyc.dmestoreyfm.base.IBaseView;
import com.dmeyc.dmestoreyfm.bean.common.OrderBean;

import java.util.List;

/**
 * Created by jockie on 2018/6/1
 * Email:jockie911@gmail.com
 */

public interface IOrderDetailView extends IBaseView{

    int getOrderId();

    void requestDataSuccess(List<List<OrderBean>> data);

    void showMsg(String msg);

    String getLeftTvContent();

    String getRightTvContent();

    void requestOrderCancle();

    void requestOrderCancleGoods();

    void requestOrderPayBack();

    void writeLogistics();

    void pay();

    void makeSureGoods();

    int getRefundCode();

    int getOrderStatus();

    void setWaitForPay();

    void setCommitted();

    void setStay();

    void setEvaluate();

    void setOrderRefund();

    void setFinish();

    void toEvaluate();
}
