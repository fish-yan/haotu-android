package com.dmeyc.dmestoreyfm.dialog;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.dmeyc.dmestoreyfm.bean.AttrBean;
import com.dmeyc.dmestoreyfm.bean.CarListBean;
import com.dmeyc.dmestoreyfm.bean.common.CommonBean;
import com.dmeyc.dmestoreyfm.bean.common.GoodsBean;
import com.dmeyc.dmestoreyfm.bean.event.BagEvent;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.MakeOrderActivity;
import com.dmeyc.dmestoreyfm.ui.PersonalTailorActivity;
import com.dmeyc.dmestoreyfm.utils.Util;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jockie on 2018/5/25
 * Email:jockie911@gmail.com
 */

public class DialogPresenter {
  int type=-1;

    private IDialogView mBaseView;

    public DialogPresenter(IDialogView mBaseView) {
        this.mBaseView = mBaseView;
    }

    /**
     * 加入购物车
     * @param context
     */
    public void addCar(final Context context){
        if(!checkPreStatus()) return;
        // TODO add car
        final ParamMap.Build build = new ParamMap.Build();
        build.addParams("quantity",mBaseView.getQuantity());
        build.addParams("goods",mBaseView.getProductId());
        build.addParams("isCustom",mBaseView.isCuston());
        for (Map.Entry<String, String> entry : mBaseView.checkPreStatus().entrySet()) {
            build.addParams(entry.getKey(),entry.getValue());
        }
        RestClient.getNovate(context).get(Constant.API.WATCH_PRODUCT_DETAIL, build.build(), new DmeycBaseSubscriber<AttrBean>(context) {
            @Override
            public void onSuccess(AttrBean bean) {
                type=1;
                addCarTailor(context,bean.getData().getProduct().getId());
            }
        });
    }

    /**
     * 详细定制加入购物车
     * @param context
     */
    public void addCarTailor(final Context context,final int productId){
        ParamMap.Build b = new ParamMap.Build();
//        if(mBaseView.isCuston()){
            if(type!=1){
      b.addParams("isCustom", mBaseView.isCuston());
//            if(mBaseView.getSelectSizeInfo().isTailorRecord()){
//                b.addParams("measureId",mBaseView.getSelectSizeInfo().getTailorRecordId());
//            }else{
                b.addParams("sizeCustom",mBaseView.getSelectSizeInfo().getSizeInfo());
//            }
            if(!TextUtils.isEmpty(mBaseView.getCustoms()))
                b.addParams("customs",mBaseView.getCustoms());
//        }
            }else {
               b .addParams("isCustom", false);
            }
        RestClient.getNovate(context).post(Constant.API.ADD_SHOPCAR,b
                .addParams("productId", productId)
                .addParams("quantity",mBaseView.getQuantity())
               .addParams("userId",Util.getUserId())
                .build(), new DmeycBaseSubscriber<CommonBean>() {
            @Override
            public void onSuccess(CommonBean bean) {
                EventBus.getDefault().post(new BagEvent());
                mBaseView.showMsg("加入购物车成功");
                mBaseView.dialogDismiss();
            }
        });
    }
    private boolean checkPreStatus() {
        for (Map.Entry<String, String> entry : mBaseView.checkPreStatus().entrySet()) {
            if(TextUtils.isEmpty(entry.getValue())){
                mBaseView.showMsg("请选择商品" + entry.getKey());
                return false;
            }
        }
        return true;
    }
    /**
     * 立刻购买
     * @param context
     * @param goodsBean
     */
    public void buy(final Context context, final GoodsBean goodsBean){
        if(!checkPreStatus()) return;
        ParamMap.Build build = new ParamMap.Build();
        build.addParams("quantity",mBaseView.getQuantity());
        build.addParams("goods",mBaseView.getProductId());
        build.addParams("isCustom",mBaseView.isCuston());
        for (Map.Entry<String, String> entry : mBaseView.checkPreStatus().entrySet()) {
            build.addParams(entry.getKey(),entry.getValue());
        }
        RestClient.getNovate(context).get(Constant.API.WATCH_PRODUCT_DETAIL, build.build(), new DmeycBaseSubscriber<AttrBean>(context) {
            @Override
            public void onSuccess(AttrBean bean) {
                buyTailor(context,goodsBean,bean.getData().getProduct().getId());
            }
        });
    }

    /**
     * 详细定制直接购买
     * @param context
     * @param goodsBean
     * @param productId
     */
    public void buyTailor(final Context context, final GoodsBean goodsBean,final int productId){
        ArrayList<CarListBean.DataBean.CartItemsBean> list = new ArrayList<>();
        CarListBean.DataBean.CartItemsBean bean = new CarListBean.DataBean.CartItemsBean();

        List<CarListBean.DataBean.CartItemsBean.CustomProductListBean> customProductList =new ArrayList<CarListBean.DataBean.CartItemsBean.CustomProductListBean>();
        CarListBean.DataBean.CartItemsBean.CustomProductListBean clb=null;
        bean.setQuantity(mBaseView.getQuantity());
        CarListBean.DataBean.CartItemsBean.ProductInfoBean productInfoBean = new CarListBean.DataBean.CartItemsBean.ProductInfoBean();
        GoodsBean goods = goodsBean;
        productInfoBean.setName(goods.getName());
        productInfoBean.setPrice(goods.getPrice());
        productInfoBean.setImage(goods.getImage());
        productInfoBean.setGoods(goods.getId());
        productInfoBean.setIsCustom(mBaseView.isCuston());
        productInfoBean.setColor(mBaseView.getClickColor());
        productInfoBean.setSize(mBaseView.getClickSize());
        if(mBaseView.isCuston()){

            String menual=  mBaseView.getTailNme();
            if(mBaseView.isCuston()&&!TextUtils.isEmpty(menual)){
                String tailname[]=  menual.split(",");
                for (int i=0;i<tailname.length;i++){
                    if(!TextUtils.isEmpty(tailname[i])){
                        clb= new CarListBean.DataBean.CartItemsBean.CustomProductListBean();
                        clb.setName(tailname[i]);
                        customProductList.add(clb);
                    }
                }

                bean.setCustomProductList(customProductList);
            }
        }
        bean.setProductInfo(productInfoBean);
        list.add(bean);

        Intent intent = new Intent(context, MakeOrderActivity.class);
        intent.putParcelableArrayListExtra("item", list);
        intent.putExtra("productId",productId);
        intent.putExtra("isCustom",mBaseView.isCuston());
        if(mBaseView.isCuston()){
            intent.putExtra("selectinfo",mBaseView.getSelectSizeInfo());
        }
        if(!TextUtils.isEmpty(mBaseView.getCustoms()))
            intent.putExtra("customs",mBaseView.getCustoms());

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        mBaseView.dialogDismiss();
    }

    /**
     * 细节定制
     * @param context
     */
    public void moreTailor(final Context context, final GoodsBean goodsBean){
        if(!checkPreStatus()) return;
        ParamMap.Build build = new ParamMap.Build();
        build.addParams("quantity",mBaseView.getQuantity());
        build.addParams("goods",mBaseView.getProductId());
        build.addParams("isCustom",mBaseView.isCuston());
//        if(mBaseView.getSelectSizeInfo().isTailorRecord()){
//            build.addParams("measureId",mBaseView.getSelectSizeInfo().getTailorRecordId());
//        }else{
//            build.addParams("sizeCustom",mBaseView.getSelectSizeInfo().getSizeInfo());
//        }
        for (Map.Entry<String, String> entry : mBaseView.checkPreStatus().entrySet()) {
            build.addParams(entry.getKey(),entry.getValue());
        }
        RestClient.getNovate(context).get(Constant.API.WATCH_PRODUCT_DETAIL, build.build(), new DmeycBaseSubscriber<AttrBean>(context) {
            @Override
            public void onSuccess(AttrBean bean) {
//                Intent intent = new Intent(context, DetailTailorActicity.class);
//                intent.putExtra("goodsBean", goodsBean);
//                intent.putExtra("productId",bean.getData().getProduct().getId());
//                intent.putExtra("quantity",mBaseView.getQuantity());
//
//                intent.putExtra("selectinfo",mBaseView.getSelectSizeInfo());
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);

                Intent intent = new Intent(context, PersonalTailorActivity.class);
                intent.putExtra("goodsBean", goodsBean);
                intent.putExtra("productId",bean.getData().getProduct().getId());
                intent.putExtra("quantity",mBaseView.getQuantity());

                intent.putExtra("selectinfo",mBaseView.getSelectSizeInfo());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                mBaseView.dialogDismiss();
            }
        });
    }

    /**
     * 获取详情
     * @param context
     */
    public void requestDetail(Context context) {
        RestClient.getNovate(context).get(Constant.API.WATCH_PRODUCT_DETAIL, new ParamMap.Build()
                .addParams("goods",mBaseView.getProductId())
                .addParams("isCustom",mBaseView.isCuston()).build(), new DmeycBaseSubscriber<AttrBean>(context) {
            @Override

            public void onSuccess(AttrBean bean) {
                mBaseView.requestDataSuccess(bean.getData().getAttributeDetails());
            }
        });
    }
}
