package com.dmeyc.dmestoreyfm.present;

import com.dmeyc.dmestoreyfm.base.IBaseView;
import com.dmeyc.dmestoreyfm.bean.SelectInfo;

/**
 * Created by jockie on 2018/5/29
 * Email:jockie911@gmail.com
 */

public interface IMakeOrderView extends IBaseView{

    boolean isDirectBuy(); //是否是直接下订单购买  true直接下订单购买  false 从购物车购买

    boolean isCustom();

    int getCouponId(); //使用优惠券id

    int getReceiverId(); //使用地址id

    int getProductId();  //直接购买 商品id

    String getCartItemIds();

    void showMsg(String msg);

    SelectInfo getSelectInfo();

    String getCustoms();
}
