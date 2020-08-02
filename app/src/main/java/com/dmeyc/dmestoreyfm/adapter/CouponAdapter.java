package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.CouponBean;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by jockie on 2018/3/5
 * Email:jockie911@gmail.com
 */

public class CouponAdapter extends BaseRvAdapter<CouponBean.DataBean>{

    private double minCanUsePrice;

    /**
     * @param context
     * @param layoutId
     * @param datas    不能为null
     */
    public CouponAdapter(Context context, int layoutId, List<CouponBean.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, CouponBean.DataBean dataBean, int position) {
        RelativeLayout itemRelRoot = holder.getView(R.id.item_rel_root);
        TextView itemTvTitle = holder.getView(R.id.item_tv_title);
        TextView itemTvSubtitle = holder.getView(R.id.item_tv_subtitle);
        TextView itemTvDate = holder.getView(R.id.item_tv_date);
        TextView itemTvPrice = holder.getView(R.id.item_tv_price);
        TextView itemTvYuan = holder.getView(R.id.item_tv_yuan);

        itemTvTitle.setText(dataBean.getName());
        itemTvSubtitle.setText(dataBean.getIntroduction());
        itemTvDate.setText("有效期:" + dataBean.getBeginDate() + "-" + dataBean.getEndDate());
        itemTvPrice.setText(String.valueOf(dataBean.getPrice()));

        itemRelRoot.setBackgroundResource(isCanUse(dataBean.getMinimumPrice()) ? R.drawable.discounts_img : R.drawable.discounts_img_gray);
        itemTvYuan.setTextColor(mContext.getResources().getColor(isCanUse(dataBean.getMinimumPrice()) ? R.color.color_1a1a1a : R.color.gray));
        itemTvTitle.setTextColor(mContext.getResources().getColor(isCanUse(dataBean.getMinimumPrice()) ? R.color.color_1a1a1a : R.color.gray));
        itemTvPrice.setTextColor(mContext.getResources().getColor(isCanUse(dataBean.getMinimumPrice()) ? R.color.indicator_selected_color : R.color.gray));
        itemRelRoot.setClickable(!isCanUse(dataBean.getMinimumPrice()));
    }

    public void setMinUsePrice(double minCanUsePrice) {
        this.minCanUsePrice = minCanUsePrice;
    }

    private boolean isCanUse(double inputPrice){
        if (minCanUsePrice == 0)
            return true;
        return minCanUsePrice >= inputPrice;
    }
}
