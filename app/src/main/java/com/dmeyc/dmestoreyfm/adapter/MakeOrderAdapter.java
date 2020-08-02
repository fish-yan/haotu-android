package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.CarListBean;
import com.dmeyc.dmestoreyfm.ui.CouponsActivity;
import com.dmeyc.dmestoreyfm.ui.MakeOrderActivity;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CountView;
import com.dmeyc.dmestoreyfm.wedgit.PriceView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by jockie on 2017/11/27
 * Email:jockie911@gmail.com
 */

public class MakeOrderAdapter extends CommonAdapter<List<CarListBean.DataBean.CartItemsBean>> {

    public MakeOrderAdapter(Context context, int layoutId, List<List<CarListBean.DataBean.CartItemsBean>> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, final List<CarListBean.DataBean.CartItemsBean> cartItemsBeen, int position) {
        TextView itemTvBrand = holder.getView(R.id.item_tv_brand);
        PriceView itemTvTotalPrice = holder.getView(R.id.item_tv_total_price);
        itemTvBrand.setText(cartItemsBeen.get(0).getProductInfo().getName());

        RelativeLayout relCouple = holder.getView(R.id.rel_couple);
        relCouple.setVisibility((position == getItemCount()) ? View.VISIBLE : View.GONE);
        TextView tvCoupon = holder.getView(R.id.tv_coupon);
//        tvCoupon.setText("已减" + mDatas.get(getItemCount() - 1).get(0).getCouponPrice() + "元");
        tvCoupon.setText("已减" + mDatas.get(0).get(0).getCouponPrice() + "元");
        int itemTotalPrice = 0;
        for (CarListBean.DataBean.CartItemsBean itemsBean : cartItemsBeen) {
            itemTotalPrice += itemsBean.getProductInfo().getPrice() * itemsBean.getQuantity();
        }
        itemTvTotalPrice.setPrice(String.valueOf(itemTotalPrice));

        RecyclerView itemRecycleView = holder.getView(R.id.recycleview);
        itemRecycleView.setLayoutManager(new LinearLayoutManager(mContext));

        itemRecycleView.setAdapter(new CommonAdapter<CarListBean.DataBean.CartItemsBean>(mContext,R.layout.item_rv_shop_car_item,cartItemsBeen) {

            @Override
            protected void convert(ViewHolder holder, final CarListBean.DataBean.CartItemsBean itemCar, int position) {
                TextView itemTvTitle = holder.getView(R.id.item_tv_title);
                PriceView itemTvPrice = holder.getView(R.id.item_tv_priceview);
                final CountView itemTvCount = holder.getView(R.id.item_tv_count);

                itemTvTitle.setText(itemCar.getProductInfo().getProductInfo());
                itemTvPrice.setPrice(String.valueOf(itemCar.getProductInfo().getPrice()));
                itemTvCount.setCount(String.valueOf(itemCar.getQuantity()));

                CheckBox checkBox = holder.getView(R.id.item_cb_item);
                checkBox.setVisibility(View.GONE);

                ImageView imagePic = holder.getView(R.id.item_iv_cover);
                GlideUtil.loadImage(mContext,itemCar.getProductInfo().getImage(),imagePic);
                TextView itemTvCustom = holder.getView(R.id.item_tv_custom);
                String produce="";
                if(itemCar.getProductInfo().isIsCustom()){
                    for (int i=0;i<itemCar.getCustomProductList().size();i++){
                        if(i!=itemCar.getCustomProductList().size()-1){
                            produce+=itemCar.getCustomProductList().get(i).getName()+",";
                        }else {
                            produce+=itemCar.getCustomProductList().get(i).getName();
                        }
                    }
                }
                itemTvCustom.setText(itemCar.getProductInfo().isIsCustom()? "定制" +itemCar.getProductInfo().getColor()
                        + "," + itemCar.getProductInfo().getSize() + ","+produce : itemCar.getProductInfo().getColor()
                        + "," + itemCar.getProductInfo().getSize());
            }
        });

        relCouple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CouponsActivity.class);
                intent.putExtra("price",((MakeOrderActivity)mContext).getTotalPrice());
                ((MakeOrderActivity)mContext).startActivityForResult(intent,1);
            }
        });
    }

    public void setCouple(double price) {
//        mDatas.get(getItemCount() - 1).get(0).setCouponPrice(price);
        mDatas.get(0).get(0).setCouponPrice(price);
        ToastUtil.show(mDatas.get(0).get(0).getCouponPrice() + "");
        notifyDataSetChanged();
    }
}
