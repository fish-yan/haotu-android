package com.dmeyc.dmestoreyfm.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.common.OrderBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.ui.BrandDetailActivity;
import com.dmeyc.dmestoreyfm.ui.OrderDetailActivity;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.dmeyc.dmestoreyfm.wedgit.CountView;
import com.dmeyc.dmestoreyfm.wedgit.PriceView;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by jockie on 2018/3/8
 * Email:jockie911@gmail.com
 */

public class OrderItemAdapter extends BaseRvAdapter<OrderBean> {

    /**
     * @param context
     * @param layoutId
     * @param datas    不能为null
     */
    public OrderItemAdapter(Context context, int layoutId, List<OrderBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, final OrderBean orderBean, int position) {
        RelativeLayout rl_to_detail = holder.getView(R.id.rl_to_detail);
        PriceView itemTvPrice = holder.getView(R.id.item_priceview);
        itemTvPrice.setPrice(orderBean.getPrice());

        TextView tvBrandName = holder.getView(R.id.item_tv_brand);
        tvBrandName.setText("品牌/设计师  " + orderBean.getBrandDesigner());
        ImageView ivCover = holder.getView(R.id.item_iv_cover);
        GlideUtil.loadImage(mContext, orderBean.getThumbnail(), ivCover);
        holder.setText(R.id.item_tv_title, orderBean.getName());
        TextView tvCustom = holder.getView(R.id.item_tv_custom);
        tvCustom.setText(TextUtils.isEmpty(orderBean.getSize()) ? orderBean.getCustomNames() : orderBean.getSize() + "," + orderBean.getColor());

        RelativeLayout itemRelRoot = holder.getView(R.id.rei_item_root);
        itemRelRoot.setVisibility(isShowTitle(position) ? View.VISIBLE : View.GONE);
        CountView countView = holder.getView(R.id.item_countview);
        countView.setCount(orderBean.getQuantity());

        TextView itemTvStatus = holder.getView(R.id.item_tv_status);    //当前订单状态
        switch (orderBean.getOrderStatus()) {
            case Constant.OrderStatus.WAIT_PAY:
                itemTvStatus.setText("待付款");
                itemTvStatus.setTextColor(mContext.getResources().getColor(R.color.indicator_selected_color));
                break;
            case Constant.OrderStatus.COMMITTED:
                setItemTvStatus(orderBean.getOrderItemRefundCode(), itemTvStatus);
                break;
            case Constant.OrderStatus.STAY:
                itemTvStatus.setText("待收货");
                itemTvStatus.setTextColor(mContext.getResources().getColor(R.color.indicator_selected_color));
                break;
            case Constant.OrderStatus.EVALUATE:
                itemTvStatus.setText("已完成");
                itemTvStatus.setTextColor(mContext.getResources().getColor(R.color.gray));
                break;
            case Constant.OrderStatus.BACK:
                itemTvStatus.setText(orderBean.getType() == 1 ? "申请退款中" : orderBean.getType() == 2 ? "退款处理中" : orderBean.getType() == 3 ? "商家第一次拒绝退款" : orderBean.getType() == 4 ? "商家第二次拒绝退款" : "退款成功");
                itemTvStatus.setTextColor(mContext.getResources().getColor(R.color.gray));
                break;
        }
        holder.setOnClickListener(R.id.item_iv_cover, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.startProductActivity(mContext, orderBean.getGoods());
            }
        });
        holder.setOnClickListener(R.id.item_tv_brand, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, BrandDetailActivity.class);
                intent.putExtra(Constant.Config.TYPE, Constant.Config.TYPE_BRAND);
//                intent.putExtra(Constant.Config.STORY_ID,orderBean.gets);
                intent.putExtra(Constant.Config.ID, orderBean.getBrandDesignerId());
                if (!(mContext instanceof Activity))
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
        rl_to_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, OrderDetailActivity.class);
//                    intent.putExtra("status",orderbean.getOrderStatus());
//                    intent.putExtra("type",orderbean.getType());
                intent.putExtra(Constant.Config.ORDER_ID, orderBean.getId());
                mContext.startActivity(intent);
            }
        });
    }

    private void setItemTvStatus(int orderItemRefundCode, TextView itemTvStatus) {
//        0.原始状态,1申请退款,2卖家拒绝,3卖家同意(此时买家需在6天之内寄出货品),4取消退款,
//                5卖家同意买家已填写好物流单号填物流单号(此时卖家需在寄出之日10日内确认收货)
//        6卖家打款完毕 7买家确认收货,退款关闭
//        8,9(8-卖家拒绝一次二次申请,9-买家取消一次二次申请)
//        10二次取消退款 11异常订单状态码 12卖家收到货之后拒绝打款 13款项到账,交易完毕
        if (orderItemRefundCode == 0) {
            itemTvStatus.setText("待发货");
            itemTvStatus.setTextColor(mContext.getResources().getColor(R.color.indicator_selected_color));
        } else if (orderItemRefundCode == 1) {
            itemTvStatus.setText("申请退款");
        } else if (orderItemRefundCode == 2) {
            itemTvStatus.setText("卖家拒绝");
        } else if (orderItemRefundCode == 3) {
            itemTvStatus.setText("卖家同意");
        } else if (orderItemRefundCode == 4) {
            itemTvStatus.setText("待发货");
        } else if (orderItemRefundCode == 5) {
            itemTvStatus.setText("卖家同意");
        } else if (orderItemRefundCode == 6) {
            itemTvStatus.setText("卖家打款完毕");
        } else if (orderItemRefundCode == 7) {
            itemTvStatus.setText("买家确认收货");
        } else if (orderItemRefundCode == 8) {
            itemTvStatus.setText("二次申请中");
        } else if (orderItemRefundCode == 9) {
            itemTvStatus.setText("二次申请中");
        } else if (orderItemRefundCode == 10) {
            itemTvStatus.setText("待发货");
        } else if (orderItemRefundCode == 11) {
            itemTvStatus.setText("异常订单");
        } else if (orderItemRefundCode == 12) {
            itemTvStatus.setText("拒绝打款");
        } else if (orderItemRefundCode == 13) {
            itemTvStatus.setText("款项到账");
        }
        if (!itemTvStatus.getText().toString().equals("") && !itemTvStatus.getText().toString().equals("待发货")) {
            itemTvStatus.setTextColor(mContext.getResources().getColor(R.color.indicator_normal_color));
        }
    }

    /**
     * 是否展示标题
     *
     * @param pos
     * @return
     */
    private boolean isShowTitle(int pos) {
        if (pos == 0)
            return true;
        return this.mDatas.get(pos).getId() != this.mDatas.get(pos - 1).getId();
    }
}
