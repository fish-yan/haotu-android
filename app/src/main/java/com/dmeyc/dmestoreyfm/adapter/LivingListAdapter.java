package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.response.LivingListBean;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;

/**
 * create by cxg on 2019/12/30
 */
public class LivingListAdapter extends BaseQuickAdapter<LivingListBean.DataBean.ImageListBean, BaseViewHolder> {
    private Context mContext;

    public LivingListAdapter(Context context) {
        super(R.layout.adapter_living_list);
        mContext = context;
    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    @Override
    protected void convert(BaseViewHolder helper, LivingListBean.DataBean.ImageListBean item) {
        if (helper == null || item == null) {
            return;
        }
        GlideUtil.loacImageCenterCrop(mContext, item.getThumbnail(), (ImageView) helper.getView(R.id.iv_bg));
        if ("1".equals(item.getCandelete())){
            helper.addOnClickListener(R.id.tv_delete);
            helper.getView(R.id.tv_delete).setVisibility(View.VISIBLE);
        }else {
            helper.getView(R.id.tv_delete).setVisibility(View.GONE);
        }
        helper.addOnClickListener(R.id.iv_like);
        setLikeImage(item, (ImageView) helper.getView(R.id.iv_like));

    }

    private void setLikeImage(LivingListBean.DataBean.ImageListBean item, ImageView imageView) {
        if ("1".equals(item.getIsLiked())) {
            imageView.setImageResource(R.drawable.icon_like_like);
        } else {
            imageView.setImageResource(R.drawable.icon_like_normal);
        }
    }
}
