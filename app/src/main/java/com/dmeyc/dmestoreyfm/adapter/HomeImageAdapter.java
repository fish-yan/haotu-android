package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;

import java.util.List;

/**
 * create by cxg on 2019/12/15
 */
public class HomeImageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private Context mContext;

    public HomeImageAdapter(Context context) {
        super(R.layout.adapter_home_image);
        mContext = context;
    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    @Override
    protected void convert(BaseViewHolder helper, String item) {
        if (helper == null || item == null) {
            return;
        }
        GlideUtil.loadImageCache(mContext, item, ((ImageView) helper.getView(R.id.imageView)));
    }
}
