package com.dmeyc.dmestoreyfm.video.shpodetail;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.utils.ScreenUtil;
import com.dmeyc.dmestoreyfm.wedgit.UnitUtil;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class TopBannerAdapter extends CommonAdapter<ShopDetailModel.DataBean.ImagesBean> {

    public TopBannerAdapter(Context context, int layoutId, List<ShopDetailModel.DataBean.ImagesBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, ShopDetailModel.DataBean.ImagesBean s, int position) {
        LinearLayout bg = holder.getConvertView().findViewById(R.id.bg);
        DisplayMetrics dm = new DisplayMetrics();
        int width = dm.widthPixels;
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams((((Activity) mContext).getWindowManager().getDefaultDisplay().getWidth() - ScreenUtil.dp2px(mContext, 31f)), FrameLayout.LayoutParams.MATCH_PARENT);
        params.setMargins((int) UnitUtil.dp2Px(mContext, 8f), 0, 0, 0);//设置边距
        bg.setLayoutParams(params);
        ImageView ivBanner = holder.getConvertView().findViewById(R.id.ivBanner);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.drawable.image_default);
        Glide.with(mContext).load(s.getUrl()).apply(options).into(ivBanner);


    }
}
