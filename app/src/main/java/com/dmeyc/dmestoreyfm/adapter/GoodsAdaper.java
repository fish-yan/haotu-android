package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseApp;
import com.dmeyc.dmestoreyfm.bean.common.GoodsBean;
import com.dmeyc.dmestoreyfm.utils.DensityUtil;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.dmeyc.dmestoreyfm.wedgit.PriceView;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by jockie on 2018/1/8
 * Email:jockie911@gmail.com
 */

public class GoodsAdaper extends BaseRvAdapter<GoodsBean> {

    private boolean isWithHead;

    /**
     * @param context
     * @param layoutId
     * @param datas    不能为null
     */
    public GoodsAdaper(Context context, int layoutId, List<GoodsBean> datas) {
        super(context, layoutId, datas);
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Util.startProductActivity(mContext, mDatas.get(isWithHead ? position - 1 : position));
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    public GoodsAdaper(Context context, int layoutId, List<GoodsBean> datas, boolean isWithHead) {
        this(context, layoutId, datas);
        this.isWithHead = isWithHead;
    }

    @Override
    protected void convert(ViewHolder holder, GoodsBean goodsBean, int position) {

        TextView textView = holder.getView(R.id.tv_category);
        LinearLayout itemLlRoot = holder.getView(R.id.item_ll_root);
        LinearLayout ll_good = holder.getView(R.id.ll_good);
        ll_good.setVisibility(View.VISIBLE);
        int itemWidth = (BaseApp.getWidth() - DensityUtil.dip2px(50)) / 2;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(itemWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        itemLlRoot.setLayoutParams(params);

        PriceView itemTvPrice = holder.getView(R.id.item_tv_priceview);
        TextView itemTvBrand = holder.getView(R.id.item_tv_brand);
        TextView itemTvName = holder.getView(R.id.item_tv_name);
        ImageView itemIvCoverPic = holder.getView(R.id.item_iv_cover_pic);
        ImageView ivIstailor = holder.getView(R.id.item_iv_istailor);

//        new RelativeLayout.LayoutParams();
        String size = "";
        for (int i = 0; i < goodsBean.getSizeList().size(); i++) {
            size = size + goodsBean.getSizeList().get(i)+" ";
        }

        itemTvBrand.setText(goodsBean.getName());
        itemTvPrice.setPrice(goodsBean.getPrice());
        itemTvName.setText(size);
        GlideUtil.loadImage(mContext, goodsBean.getImage(), itemIvCoverPic);
        ivIstailor.setVisibility(goodsBean.isIsCustom() == 0 ? View.INVISIBLE : View.VISIBLE);
    }
}
