package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.CarListBean;
import com.dmeyc.dmestoreyfm.bean.common.CommonBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.ShopCarActivity;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.wedgit.AmountView;
import com.dmeyc.dmestoreyfm.wedgit.CountView;
import com.dmeyc.dmestoreyfm.wedgit.CustomDialog;
import com.dmeyc.dmestoreyfm.wedgit.PriceView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jockie on 2017/11/27
 * Email:jockie911@gmail.com
 */

public class ShopCarAdapter extends BaseRvAdapter<List<CarListBean.DataBean.CartItemsBean>> {

    private ShopCarActivity shopCarActivity;
    private boolean isCanEdit;  //编辑数量的view是否可见
    private OnStatusChangeLister lister;

    public ShopCarAdapter(Context context, int layoutId, List<List<CarListBean.DataBean.CartItemsBean>> datas) {
        super(context, layoutId, datas);
    }

    public ShopCarAdapter(Context context, int layoutId, List<List<CarListBean.DataBean.CartItemsBean>> datas, ShopCarActivity shopCarActivity) {
        this(context, layoutId, datas);
        this.shopCarActivity = shopCarActivity;
    }

    @Override
    protected void convert(ViewHolder holder, final List<CarListBean.DataBean.CartItemsBean> cartItemsBean, final int position) {
        TextView itemTvBrand = holder.getView(R.id.item_tv_brand);
        itemTvBrand.setText(cartItemsBean.get(0).getProductInfo().getBrandDesigner());

        RecyclerView itemRecycleView = holder.getView(R.id.recycleview);
        itemRecycleView.setLayoutManager(new LinearLayoutManager(mContext));

        final CheckBox itemCb = holder.getView(R.id.item_cb);
        itemCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (CarListBean.DataBean.CartItemsBean itemCar : cartItemsBean) {
                    itemCar.setChecked(itemCb.isChecked());
                    reload();
                }
            }
        });
        boolean itemALlChecked = true;
        for (CarListBean.DataBean.CartItemsBean itemCar : cartItemsBean) {
            if(!itemCar.isChecked()){
                itemALlChecked = false;
                break;
            }
        }
        itemCb.setChecked(itemALlChecked);

        itemRecycleView.setAdapter(new CommonAdapter<CarListBean.DataBean.CartItemsBean>(mContext,R.layout.item_rv_shop_car_item,cartItemsBean) {

            @Override
            public void convert(ViewHolder holder, CarListBean.DataBean.CartItemsBean cartItemsBean) {
                super.convert(holder, cartItemsBean);
                setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, RecyclerView.ViewHolder holder, final int pos) {
                        new CustomDialog(mContext).builder()
                                .setMsg("确定删除该件商品吗?")
                                .setNegativeButton("",null)
                                .setPositiveButton("", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        RestClient.getNovate(mContext).get(Constant.API.DELETE_SHOPCAR, new ParamMap.Build()
                                                .addParams("cartItemIds",mDatas.get(pos).getId()).build(), new DmeycBaseSubscriber<CommonBean>() {
                                            @Override
                                            public void onSuccess(CommonBean bean) {
                                                shopCarActivity.requestData();
                                            }
                                        });
                                    }
                                }).show();
                    }

                    @Override
                    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                        return false;
                    }
                });
            }

            @Override
            protected void convert(ViewHolder holder, final CarListBean.DataBean.CartItemsBean itemCar, int position) {
                TextView itemTvTitle = holder.getView(R.id.item_tv_title);
                PriceView itemTvPrice = holder.getView(R.id.item_tv_priceview);
                final CountView itemTvCount = holder.getView(R.id.item_tv_count);
                final AmountView amountView = holder.getView(R.id.item_amount_view);
                amountView.setVisibility(isCanEdit ? View.VISIBLE : View.INVISIBLE);
                itemTvTitle.setVisibility(isCanEdit ? View.INVISIBLE : View.VISIBLE);
                ImageView imagePic = holder.getView(R.id.item_iv_cover);
                GlideUtil.loadImage(mContext,itemCar.getProductInfo().getImage(),imagePic);
                TextView itemTvCustom = holder.getView(R.id.item_tv_custom);
//                itemTvCustom.setText(TextUtils.isEmpty(itemCar.getProductInfo().getImage()) ? "定制" : itemCar.getProductInfo().getColor()
//                + "," + itemCar.getProductInfo().getSize() + "," + itemCar.getProductInfo().getMaterial());
                String produce="";
                if(itemCar.getCustomProductList()!=null){
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
                amountView.setCurCount(itemCar.getQuantity());
                amountView.setMaxCount(itemCar.getProductInfo().isIsCustom() ? Integer.MAX_VALUE : itemCar.getProductInfo().getAvailableStock());
                amountView.setOnCountChangeLister(new AmountView.OnCountChangeLister() {
                    @Override
                    public void countChange(int mCurCount, boolean isAdd) {
                        itemTvCount.setCount(mCurCount);
                        itemCar.setQuantity(mCurCount);
                        RestClient.getNovate(mContext).get(Constant.API.UPDATE_CAR_COUNT, new ParamMap.Build()
                                .addParams("cartItemId",itemCar.getId())
                                .addParams("quantity",amountView.getCurCount()).build(), new DmeycBaseSubscriber<CommonBean>() {
                            @Override
                            public void onSuccess(CommonBean bean) {

                            }
                        });
                        reload();
                    }
                });

                itemTvTitle.setText(itemCar.getProductInfo().getName());
                itemTvPrice.setPrice(itemCar.getProductInfo().getPrice());
                itemTvCount.setCount(itemCar.getQuantity());

                final CheckBox itemCBItem = holder.getView(R.id.item_cb_item);
                itemCBItem.setChecked(itemCar.isChecked());
                itemCBItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemCar.setChecked(itemCBItem.isChecked());
                        reload();
                    }
                });
            }
        });
    }

    public void reload(){
        notifyDataSetChanged();
        if(lister != null){
            boolean isAllCheck = true;
            int totalPrice = 0;
            for (List<CarListBean.DataBean.CartItemsBean> mData : mDatas) {
                for (CarListBean.DataBean.CartItemsBean itemCar : mData) {
                    if(!itemCar.isChecked()){
                        isAllCheck = false;
                    }else{
                        totalPrice += itemCar.getQuantity() * itemCar.getProductInfo().getPrice();
                    }
                }
            }
            lister.statusChangeLister(isAllCheck,totalPrice);
        }
    }

    /**
     * 让每个item处于可以编辑的状态
     */
    public void editItemCount(boolean isCanEdit){
        this.isCanEdit = isCanEdit;
        notifyDataSetChanged();
    }

    public void setAllChecked(boolean isAllChecked) {
        for (List<CarListBean.DataBean.CartItemsBean> mData : mDatas) {
            for (CarListBean.DataBean.CartItemsBean itemCar : mData) {
                itemCar.setChecked(isAllChecked);
                reload();
            }
        }
    }

    public void setOnStatusChangeLister(OnStatusChangeLister lister){
        this.lister = lister;
    }

    public interface OnStatusChangeLister{
        void statusChangeLister(boolean isAllChecked,int totalPrice);
    }

    public ArrayList<ArrayList<CarListBean.DataBean.CartItemsBean>> getSelectedData(){
        ArrayList<ArrayList<CarListBean.DataBean.CartItemsBean>> list = new ArrayList<>();
        for (List<CarListBean.DataBean.CartItemsBean> data : mDatas) {
            ArrayList<CarListBean.DataBean.CartItemsBean> templist = new ArrayList<>();
            for (CarListBean.DataBean.CartItemsBean cartItemsBean : data) {
                if(cartItemsBean.isChecked()){
                    templist.add(cartItemsBean);
                }
            }
            if(templist.size() > 0)
                list.add(templist);
        }
        return list;
    }
}
