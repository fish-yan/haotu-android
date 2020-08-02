package com.dmeyc.dmestoreyfm.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dmeyc.dmestoreyfm.bean.response.GoodsBean;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.wedgit.GoodsTagView;

/**
 * create by cxg on 2019/12/9
 */
public class GoodsAdapter extends BaseQuickAdapter<GoodsBean.DataBean, BaseViewHolder> {
    public GoodsAdapter() {
        super(R.layout.adapter_goods);
    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    @Override
    protected void convert(BaseViewHolder helper, GoodsBean.DataBean item) {
        if (helper == null || item == null) {
            return;
        }

        GlideUtil.loadImage(mContext,item.getImg(), (ImageView) helper.getView(R.id.iv_icon));
        helper.setText(R.id.tv_title,item.getName())
        .setText(R.id.tv_price,item.getPrice()+"");
        ((GoodsTagView) helper.getView(R.id.goodsTagView)).setTags(item.getTag());
    }
}
