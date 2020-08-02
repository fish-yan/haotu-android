package com.dmeyc.dmestoreyfm.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dmeyc.dmestoreyfm.R;

/**
 * create by cxg on 2020/1/1
 */
public class GoodsTagAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public GoodsTagAdapter() {
        super(R.layout.adapter_goods_tag);
    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    @Override
    protected void convert(BaseViewHolder helper, String item) {
        if (helper ==null || item == null){
            return;
        }
        helper.setText(R.id.tv_tag,item);

    }
}
