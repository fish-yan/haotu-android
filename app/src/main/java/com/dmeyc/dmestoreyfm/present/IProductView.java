package com.dmeyc.dmestoreyfm.present;

import com.dmeyc.dmestoreyfm.base.IBaseView;
import com.dmeyc.dmestoreyfm.bean.GoodDetailBean;

/**
 * Created by jockie on 2018/4/27
 * Email:jockie911@gmail.com
 */

public interface IProductView extends IBaseView {

    void openCommonDialog(); //标准套码

    void openCustomDialog();    //定制套码

    void openTailorDialog(); //纯定制

    int switchType();   // 返回类型

    boolean isCustom(); //是否是定制商品

    void startConvasation(String productId,String targetConvasationId); //启动会话聊天

    void resultFollowProduct(boolean isFollow,String resultText); //添加商品关注或者取消关注

    void requestDataSuccess(GoodDetailBean resultBean);

    void requestDataError();

    int getBrandType();

    int getBrandId();

    void attendBrandResult(boolean isAttend);
}
