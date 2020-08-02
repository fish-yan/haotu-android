package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.common.BrandDesignerBean;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.makeramen.roundedimageview.RoundedImageView;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by jockie on 2018/2/2
 * Email:jockie911@gmail.com
 */

public class WishBrandAdapter extends BaseRvAdapter<BrandDesignerBean> {

    /**
     * @param context
     * @param layoutId
     * @param datas    不能为null
     */
    public WishBrandAdapter(final Context context, int layoutId, List<BrandDesignerBean> datas) {
        super(context, layoutId, datas);
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Util.startBrandDesignDetailActivity(context,getItem(position).getType(),getItem(position).getId(),getItem(position).getId());
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    @Override
    protected void convert(ViewHolder holder, BrandDesignerBean bean, int position) {
        RoundedImageView roundImageView = holder.getView(R.id.item_roundiv);
        roundImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        TextView itemTvName = holder.getView(R.id.item_tv_name);
        TextView itemTvFollow = holder.getView(R.id.item_tv_follow);

        GlideUtil.loadImage(mContext,bean.getHeading(),roundImageView);
        itemTvName.setText(bean.getName());

        itemTvFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
