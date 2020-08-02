package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.common.CommonBean;
import com.dmeyc.dmestoreyfm.bean.common.OrderBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.dialog.PayDialog;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.ApplyPayBackActivity;
import com.dmeyc.dmestoreyfm.ui.EvaluateActivity;
import com.dmeyc.dmestoreyfm.ui.OrderDetailActivity;
import com.dmeyc.dmestoreyfm.ui.order.LogisticsActivity_2;
import com.dmeyc.dmestoreyfm.utils.SnackBarUtil;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CustomDialog;
import com.dmeyc.dmestoreyfm.wedgit.PriceView;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jockie on 2018/2/1
 * Email:jockie911@gmail.com
 */

public class OrderManagerAdapter extends BaseRvAdapter<List<List<OrderBean>>> {

    private int mCurPosition;

    /**
     * @param context
     * @param layoutId
     * @param datas        不能为null
     * @param mCurPosition
     */
    public OrderManagerAdapter(Context context, int layoutId, List<List<List<OrderBean>>> datas, int mCurPosition) {
        super(context, layoutId, datas);
        this.mCurPosition = mCurPosition;
    }

    @Override
    protected void convert(final ViewHolder holder, final List<List<OrderBean>> orderItem, final int position) {
        final TextView itemTvRight = holder.getView(R.id.item_tv_right);
        final TextView itemTvLeft = holder.getView(R.id.item_tv_left);
        final TextView item_tv_one = holder.getView(R.id.item_tv_one);

        LinearLayout llBottom = holder.getView(R.id.ll_bottom);

        TextView tvHeadStatus = holder.getView(R.id.tv_head_status);

        LinearLayout llHeadView = holder.getView(R.id.item_ll_head);
        View vHeadDivide = holder.getView(R.id.item_divide_head);

        View itemVDivide = holder.getView(R.id.item_v_divide);
        View item_v_one = holder.getView(R.id.item_v_one);

        llHeadView.setVisibility(position == 0 && orderItem.get(0).get(0).getOrderStatus() != Constant.OrderStatus.BACK ? View.VISIBLE : View.GONE);
        vHeadDivide.setVisibility(position == 0 ? View.VISIBLE : View.GONE);

        int totalCount = 0;
        double totalPrice = 0;
        double totalRealPrice = 0;
        double couponPrice = 0;
        for (List<OrderBean> been : orderItem) {
            totalCount += been.get(0).getTotalQuantity();
            totalPrice += been.get(0).getAmount();
            totalRealPrice += been.get(0).getRealAmount();
            couponPrice += been.get(0).getDiscountAmount();
        }
        TextView itemTotalCount = holder.getView(R.id.tv_item_count);
        itemTotalCount.setText("共" + totalCount + "件商品");
        PriceView itemTvTotalPrice = holder.getView(R.id.item_total_priceview);

        RelativeLayout relCoupon = holder.getView(R.id.rel_coupon);
        RelativeLayout relRealPay = holder.getView(R.id.rel_realpay);
        PriceView pvCoupon = holder.getView(R.id.pv_coupon);
        PriceView pvRealPay = holder.getView(R.id.pv_realpay);
        if (couponPrice > 0) {

        }
        relCoupon.setVisibility(couponPrice > 0 ? View.VISIBLE : View.GONE);
        relRealPay.setVisibility(couponPrice > 0 ? View.VISIBLE : View.GONE);
        pvCoupon.setPrice(Math.ceil(couponPrice - 0.5d));
        pvRealPay.setPrice(totalRealPrice);
        itemTvTotalPrice.setPrice(totalPrice);

        switch (mCurPosition) {
            case Constant.OrderStatus.ALL:
                tvHeadStatus.setText("全部");
                break;
            case Constant.OrderStatus.WAIT_PAY:
                tvHeadStatus.setText("待付款");
                break;
            case Constant.OrderStatus.COMMITTED:
                tvHeadStatus.setText("待发货");
                break;
            case Constant.OrderStatus.STAY:
                tvHeadStatus.setText("待收货");
                break;
            case Constant.OrderStatus.EVALUATE:
                tvHeadStatus.setText("已完成");
                break;
            case Constant.OrderStatus.BACK:
                tvHeadStatus.setText("退款");
                itemTvLeft.setVisibility(View.GONE);
                itemTvRight.setText("查看详情");
                break;
        }
        switch (orderItem.get(0).get(0).getOrderStatus()) {
            case Constant.OrderStatus.WAIT_PAY:
                itemTvRight.setText("付款");
                itemTvLeft.setText("取消订单");
                itemTvLeft.setVisibility(View.VISIBLE);
                break;
            case Constant.OrderStatus.COMMITTED:
                itemTvLeft.setText("申请退款");
                itemTvRight.setText("提醒发货");
                itemTvLeft.setVisibility(View.VISIBLE);
                setTextViewRefundCode(orderItem, position,itemTvLeft, itemTvRight);
                break;
            case Constant.OrderStatus.STAY:
                itemTvLeft.setText("查看物流");
                itemTvRight.setText("确定收货");
                itemTvLeft.setVisibility(View.VISIBLE);
                setTextViewRefundCode(orderItem, position, itemTvLeft, itemTvRight);
                if(orderItem.get(0).get(0).getOrderRefundCode() == 0 || orderItem.get(0).get(0).getOrderRefundCode() == 4){
                    item_tv_one.setVisibility(View.VISIBLE);
                    item_v_one.setVisibility(View.VISIBLE);
                }
                break;
            case Constant.OrderStatus.EVALUATE:
                itemTvLeft.setVisibility(View.GONE);
                itemTvRight.setText("晒单");
                break;
            case Constant.OrderStatus.BACK:
                itemTvLeft.setVisibility(View.GONE);
                itemTvRight.setText("查看详情");
                tvHeadStatus.setVisibility(View.GONE);
                break;
            case Constant.OrderStatus.FINISH:
                itemTvLeft.setVisibility(View.GONE);
                itemTvRight.setText("已评价");
                itemTvRight.setTextColor(mContext.getResources().getColor(R.color.indicator_normal_color));
                tvHeadStatus.setVisibility(View.GONE);
                break;
        }
        llBottom.setVisibility(orderItem.get(0).get(0).getOrderStatus() == -1 ? View.GONE : View.VISIBLE);

        itemVDivide.setVisibility(orderItem.get(0).get(0).getOrderStatus() == Constant.OrderStatus.EVALUATE || orderItem.get(0).get(0).getOrderStatus() == Constant.OrderStatus.BACK ? View.GONE : View.VISIBLE);
        itemTvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemTvLeft.getText().equals("查看物流")) {
                    Intent intent = new Intent(mContext, LogisticsActivity_2.class);
                    intent.putExtra("deliveryCode",orderItem.get(0).get(0).getDeliveryCode());
                    intent.putExtra("deliveryNumber",orderItem.get(0).get(0).getDeliveryNumber());
                    intent.putExtra("orderIds",orderItem.get(0).get(0).getId());
                    mContext.startActivity(intent);
                } else if (itemTvLeft.getText().equals("取消订单")) {
                    new CustomDialog(mContext)
                            .builder()
                            .showTitle()
                            .setMsg("确定要取消订单吗?")
                            .setNegativeButton("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    RestClient.getNovate(mContext).post(Constant.API.ORDER_CANCLE, new ParamMap.Build()
                                            .addParams("orderIds", getOrderId(position)).build(), new DmeycBaseSubscriber<CommonBean>(mContext) {
                                        @Override
                                        public void onSuccess(CommonBean bean) {
                                            SnackBarUtil.showShortSnackbar(itemTvLeft, "订单取消成功");
                                            mDatas.remove(position);
                                            notifyDataSetChanged();
                                        }
                                    });
                                }
                            })
                            .setPositiveButton("取消", R.color.indicator_selected_color, null)
                            .show();
                } else if (itemTvLeft.getText().equals("申请退款")) {
                    new CustomDialog(mContext)
                            .builder()
                            .showTitle()
                            .setMsg("确定要申请退款吗?")
                            .setNegativeButton("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(mContext, ApplyPayBackActivity.class);
                                    intent.putExtra("maxMoney", getItem(position).get(0).get(0).getRealAmount());
                                    intent.putExtra("orderId", getItem(position).get(0).get(0).getId());
                                    intent.putExtra("orderItemId", getItem(position).get(0).get(0).getOrderItemId());
                                    intent.putExtra("status", getItem(position).get(0).get(0).getOrderItemStatus());
                                    mContext.startActivity(intent);
                                }
                            })
                            .setPositiveButton("取消", R.color.indicator_selected_color, null)
                            .show();

                } else {
                    ToastUtil.show(itemTvLeft.getText().toString());
                }

            }
        });
        itemTvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemTvRight.getText().equals("付款")) {
                    new PayDialog(mContext, getOrderId(position)).show();
                } else if (itemTvRight.getText().equals("查看详情")) {
                    Intent intent = new Intent(mContext, OrderDetailActivity.class);
//                    intent.putExtra("status",orderbean.getOrderStatus());
//                    intent.putExtra("type",orderbean.getType());
                    intent.putExtra(Constant.Config.ORDER_ID, getItem(position).get(0).get(0).getId());
                    mContext.startActivity(intent);
                } else if (itemTvRight.getText().equals("晒单")) {
                    Intent intent = new Intent();
                    intent.putExtra("img", getItem(position).get(0).get(0).getThumbnail());
                    intent.putExtra("title", getItem(position).get(0).get(0).getName());
                    intent.putExtra("size", getItem(position).get(0).get(0).getSize()+","+getItem(position).get(0).get(0).getColor());
                    intent.putExtra("price", String.valueOf(getItem(position).get(0).get(0).getPrice()));
                    intent.putExtra("quantity", getItem(position).get(0).get(0).getQuantity());
                    intent.putExtra("orderId", getItem(position).get(0).get(0).getId());
                    intent.putExtra("orderItemId", getItem(position).get(0).get(0).getOrderItemId());
                    intent.putExtra("goods", getItem(position).get(0).get(0).getGoods());

                    intent.setClass(mContext, EvaluateActivity.class);
                    mContext.startActivity(intent);
                } else if (itemTvRight.getText().equals("提醒发货")) {
                    ToastUtil.show("已经提醒商家,商家会尽快发货");
                } else if(itemTvRight.getText().equals("确定收货")){
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("orderId",getItem(position).get(0).get(0).getId());
                    map.put("orderItemId",getItem(position).get(0).get(0).getOrderItemId());
                    map.put("status",getItem(position).get(0).get(0).getOrderStatus());
                    RestClient.getNovate(mContext).get(Constant.API.ORDER_CONFIRM_RECEIVE_GOODS, map, new DmeycBaseSubscriber<Object>() {
                        @Override
                        public void onSuccess(Object bean) {
                               ToastUtil.show("确定成功");
                        }
                    });
                }
            }
        });
        item_tv_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CustomDialog(mContext)
                        .builder()
                        .showTitle()
                        .setMsg("确定要申请退款吗?")
                        .setNegativeButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mContext, ApplyPayBackActivity.class);
                                intent.putExtra("maxMoney", getItem(position).get(0).get(0).getRealAmount());
                                intent.putExtra("orderId", getItem(position).get(0).get(0).getId());
                                intent.putExtra("orderItemId", getItem(position).get(0).get(0).getOrderItemId());
                                intent.putExtra("status", getItem(position).get(0).get(0).getOrderItemStatus());
                                mContext.startActivity(intent);
                            }
                        })
                        .setPositiveButton("取消", R.color.indicator_selected_color, null)
                        .show();
            }
        });

        RecyclerView itemRecycleView = holder.getView(R.id.recycleview);
        itemRecycleView.setLayoutManager(new LinearLayoutManager(mContext));
        List<OrderBean> itemDatas = new ArrayList<>();
        for (List<OrderBean> been : orderItem) {
            itemDatas.addAll(been);
        }
        itemRecycleView.setAdapter(new OrderItemAdapter(mContext, R.layout.item_order_part, itemDatas));
    }

    /**
     * 获取orderid 逗号隔开
     *
     * @param position
     * @return
     */
    public String getOrderId(int position) {
        String ids = "";
        for (int i = 0; i < getItem(position).size(); i++) {
            ids += getItem(position).get(i).get(0).getId() + (i == getItem(position).size() - 1 ? "" : ",");
        }
        return ids;
    }

    private void setTextViewRefundCode(final List<List<OrderBean>> orderItem, final int position
            , TextView itemTvLeft, TextView itemTvRight) {
//        0. 原始状态, 1 申请退款, 2 卖家拒绝, 3 卖家同意(此时买家需在6天之内寄出货品), 4 取消退款,
//                5 卖家同意买家已填写好物流单号填物流单号(此时卖家需在寄出之日10日内确认收货)
//        6 卖家打款完毕
//        7 买家确认收货, 退款关闭8,
//                9 (8 - 卖家拒绝一次二次申请, 9 - 买家取消一次二次申请)
//        10 二次取消退款
//        11 异常订单状态码
//        12 卖家收到货之后拒绝打款
//        13 款项到账, 交易完毕
//        if (orderItem.get(0).get(0).getOrderRefundCode() == 1 || orderItem.get(0).get(0).getOrderRefundCode() == 3
//                || orderItem.get(0).get(0).getOrderRefundCode() == 3 || orderItem.get(0).get(0).getOrderRefundCode() == 5
//                || orderItem.get(0).get(0).getOrderRefundCode() == 6 || orderItem.get(0).get(0).getOrderRefundCode() == 7
//                || orderItem.get(0).get(0).getOrderRefundCode() == 12 || orderItem.get(0).get(0).getOrderRefundCode() == 13) {
//            itemTvLeft.setVisibility(View.GONE);
//            itemTvRight.setVisibility(View.VISIBLE);
//            itemTvLeft.setText("查看详情");
//        }
        if(orderItem.get(0).get(0).getOrderRefundCode() != 0 && orderItem.get(0).get(0).getOrderRefundCode() != 4){
            itemTvLeft.setVisibility(View.GONE);
            itemTvRight.setVisibility(View.VISIBLE);
            itemTvRight.setText("查看详情");
        }
    }
}
