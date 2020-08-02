package com.dmeyc.dmestoreyfm.present;

import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.GoodDetailBean;
import com.dmeyc.dmestoreyfm.bean.common.CommonBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.ProductActivity;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.tamic.novate.Throwable;

/**
 * Created by jockie on 2018/4/27
 * Email:jockie911@gmail.com
 */

public class ProductPresent extends BasePresenter<IProductView,ProductActivity>{

    /**
     * 商品详情顶部左侧点击事件
     */
    public void bottomLeftClick(){
        if(!Util.checkLoginStatus(mActivity.get()))
            return;
        switch (mBaseView.switchType()){
            case ProductActivity.TYPE_COMMON:
                mBaseView.openCommonDialog();
                break;
            case ProductActivity.TYPE_CUSTOM:
                mBaseView.openCustomDialog();
                break;
            case ProductActivity.TYPE_TAILOR:

                break;
        }
    }

    /**
     * 商品详情顶部右侧点击事件
     */
    public void bottomRightClick(){
//        if(!Util.checkLoginStatus(mActivity.get()))
//            return;
           switch (mBaseView.switchType()){
               case ProductActivity.TYPE_COMMON:
                   mBaseView.openCommonDialog();
                   break;
               case ProductActivity.TYPE_CUSTOM:
                   mBaseView.openTailorDialog();
                   break;
               case ProductActivity.TYPE_TAILOR:
                   mBaseView.openTailorDialog();
                   break;
           }
    }

    /**
     * 根据商品id启动会话聊天,以后可能涉及到单家多客服
     * @param mProductId
     */
    public void startConvasation(int mProductId) {
        mBaseView.startConvasation(String.valueOf(mProductId),String.valueOf(mProductId));
    }

    /**
     * 添加商品到关注和取消商品关注
     * @param mProductId
     */
    public void addProductToFollow(int mProductId) {
        if(Util.checkLoginStatus(mActivity.get())){
            RestClient.getNovate(mActivity.get()).post(Constant.API.ATTEND_PRODUCT, new ParamMap.Build()
                    .addParams("goodsId", mProductId)
                    .addParams("key", SPUtils.getStringData(Constant.Config.MOBILE))
                    .addParams("value", SPUtils.getStringData(Constant.Config.TOKEN))
                    .build(), new DmeycBaseSubscriber<CommonBean>(mActivity.get()) {
                @Override
                public void onSuccess(CommonBean bean) {
                    Object data = bean.getData();
                    if(data instanceof Boolean){
                        Boolean aBoolean = (Boolean) data;
                        mBaseView.resultFollowProduct(aBoolean,aBoolean ? "收藏成功" : "取消收藏成功");
                    }
                }
            });
        }
    }
    /**
     * 获取商品详情的数据
     * @param mProductId
     */
    public void requestProductData(int mProductId) {
        RestClient.getNovate(mActivity.get()).get(Constant.API.PRODUCT_DETAIL, new ParamMap.Build().addParams(Constant.Config.ID, mProductId).build(), new DmeycBaseSubscriber<GoodDetailBean>(mActivity.get()) {

            @Override
            public void onSuccess(final GoodDetailBean bean) {
               mBaseView.requestDataSuccess(bean);
            }

            @Override
            public void onError(Throwable e) {
                mBaseView.requestDataError();
            }
        });
    }
    public void attendBrand() {
        RestClient.getNovate(mActivity.get()).post(Constant.API.ATTEND_DESIGNER, new ParamMap.Build()
//                .addParams("type", mBaseView.getBrandType())
                .addParams("userId", Util.getUserId())
                .addParams("type", 1)
                .addParams("attend", mBaseView.getBrandId())
               .build(), new DmeycBaseSubscriber<CommonBean>() {
            @Override
            public void onSuccess(CommonBean bean) {
                if(bean.getData() instanceof Boolean){
                    mBaseView.attendBrandResult((boolean)bean.getData());
                }
            }
        });
    }
}
