package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.OrderItemAdapter;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.bean.common.CommonBean;
import com.dmeyc.dmestoreyfm.bean.common.OrderBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.dialog.PayDialog;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.present.IOrderDetailView;
import com.dmeyc.dmestoreyfm.present.OrderDetailPresenter;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.SnackBarUtil;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.dmeyc.dmestoreyfm.wedgit.CustomDialog;
import com.dmeyc.dmestoreyfm.wedgit.PriceView;
import com.dmeyc.dmestoreyfm.wedgit.TextWithTimerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class OrderDetailActivity extends BaseActivity<OrderDetailPresenter> implements IOrderDetailView {

    @Bind(R.id.tv_order_title)
    TextView tvOrderTitle;
    @Bind(R.id.tv_order_make_time)
    TextView tvOrderMakeTime;
    @Bind(R.id.tv_order_pay_time)
    TextView tvOrdrePayTime;
    @Bind(R.id.tv_order_send_time)
    TextView tvOrderSendTime;

    @Bind(R.id.tv_left_submit)
    TextView tvLeftSubmit;
    @Bind(R.id.tv_right_submit)
    TextView tvRightSubmit;

    @Bind(R.id.item_tv_yunfei_priceview)
    PriceView itemYunFeiPriceView;
    @Bind(R.id.item_tv_coupon_priceview)
    PriceView itemCoupinPriceView;
    @Bind(R.id.item_tv_total_priceview)
    PriceView itemTotalPriceView;

    @Bind(R.id.tv_order_right_status)
    TextView tvRightStatus;
    @Bind(R.id.textTimerView)
    TextWithTimerView textTimerView;

    @Bind(R.id.rel_bottom)
    RelativeLayout relBottom;
    @Bind(R.id.v_bottom)
    View vBottom;

    @Bind(R.id.head_tv_name)
    TextView tvHeadTVName;
    @Bind(R.id.head_tv_address)
    TextView tvHeadTvAddress;

    @Bind(R.id.tv_order_id)
    TextView tvOrderId;
    @Bind(R.id.recycleview)
    RecyclerView mRecycleView;

    @Bind(R.id.tv_one_tuikuan)
    TextView tv_one_tuikuan;
    private OrderBean orderBean;

    @Override
    protected int getLayoutRes() {
        return R.layout.ac_order_detail;
    }

    @Override
    protected OrderDetailPresenter initPresenter() {
        return new OrderDetailPresenter();
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_order_right_status, R.id.tv_left_submit, R.id.tv_right_submit, R.id.tv_contact_merchant})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_order_right_status:
                if (tvRightStatus.getText().toString().equals("查看物流状态")) {
                    Intent intent = new Intent(this, LogisticsActivity.class);
                    intent.putExtra("deliveryCode", orderBean.getDeliveryCode());
                    intent.putExtra("deliveryNumber", orderBean.getDeliveryNumber());
                    startActivity(intent);
                } else if (tvRightStatus.getText().toString().equals("退货处理中")) {
                    startActivity(new Intent(this, BackOrderDetailActivity.class));
                } else if (tvRightStatus.getText().toString().equals("已完成")) {
//                    startActivity(new Intent(this,LogisticsActivity.class));
                }
                break;
            case R.id.tv_left_submit:
                mPresenter.leftSubmitClick();
                break;
            case R.id.tv_right_submit:
                mPresenter.rightSubmitClick();
                break;
            case R.id.tv_contact_merchant: //联系商家
                if (orderBean != null)
                    mPresenter.contactMerchant(orderBean.getBrandDesignerId(), orderBean.getBrandDesigner());
                break;
           /* case R.id.ll_goods:
                if(orderBean != null){
                    Util.startProductActivity(this, + orderBean.getGoods());
                }else{
                    SnackBarUtil.showShortSnackbar(tvTitle,"请稍后再试");
                }
                break;*/
        }
    }

    @Override
    public void requestDataSuccess(List<List<OrderBean>> data) {
        if (Util.objEmpty(data)) {
            return;
        }
        orderBean = data.get(0).get(0);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        List<OrderBean> itemDatas = new ArrayList<>();
        for (List<OrderBean> been : data) {
            itemDatas.addAll(been);
        }
        mRecycleView.setAdapter(new OrderItemAdapter(this, R.layout.item_order_part, itemDatas));

        itemYunFeiPriceView.setPrice(orderBean.getFreight());
        itemCoupinPriceView.setPrice(0);
        itemTotalPriceView.setPrice(orderBean.getRealAmount());
        tvHeadTVName.setText(orderBean.getReceiverPeople() + " " + orderBean.getPhone());
        tvHeadTvAddress.setText(orderBean.getAreaName() + " " + orderBean.getAddress());
        tvOrderId.setText("订单编号:" + orderBean.getSn());
        tvOrderMakeTime.setText("下单时间:" + orderBean.getCreateDate());

        mPresenter.setStatus();

        if (orderBean.getOrderStatus() == Constant.OrderStatus.STAY) {
            tv_one_tuikuan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    requestOrderPayBack();
                }
            });
        }
    }

    /**
     * 剩余时间的毫秒数
     *
     * @param expire
     * @return
     */
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private long getRemindTime(String expire) {
        long time = 1;
        try {
            time = sdf.parse(expire).getTime() - System.currentTimeMillis();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    @Override
    public String getLeftTvContent() {
        return tvLeftSubmit.getText().toString();
    }

    @Override
    public String getRightTvContent() {
        return tvRightSubmit.getText().toString();
    }

    @Override
    public void requestOrderCancle() {
        new CustomDialog(this)
                .builder()
                .showTitle()
                .setMsg("确定要取消订单吗?")
                .setNegativeButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RestClient.getNovate(OrderDetailActivity.this).post(Constant.API.ORDER_CANCLE, new ParamMap.Build()
                                .addParams("orderIds", getOrderId()).build(), new DmeycBaseSubscriber<CommonBean>(OrderDetailActivity.this) {
                            @Override
                            public void onSuccess(CommonBean bean) {
                                showMsg("订单取消成功");
                                mPresenter.getOrderData();
                            }
                        });
                    }
                })
                .setPositiveButton("取消", R.color.indicator_selected_color, null)
                .show();
    }

    @Override
    public void requestOrderCancleGoods() {
        new CustomDialog(this)
                .builder()
                .showTitle()
                .setMsg("确定要取消退货吗?")
                .setNegativeButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RestClient.getNovate(OrderDetailActivity.this).post(Constant.API.ORDER_RETURN_FUND_PRICE, new ParamMap.Build()
                                .addParams("orderIds", getOrderId())
                                .addParams("orderItemId", orderBean.getOrderItemId())
                                .addParams("status", orderBean.getOrderStatus()).build(), new DmeycBaseSubscriber<CommonBean>(OrderDetailActivity.this) {
                            @Override
                            public void onSuccess(CommonBean bean) {
                                showMsg("取消退货成功");
                                mPresenter.getOrderData();
                            }
                        });
                    }
                })
                .setPositiveButton("取消", R.color.indicator_selected_color, null)
                .show();
    }

    @Override
    public void requestOrderPayBack() {
        new CustomDialog(this)
                .builder()
                .showTitle()
                .setMsg("确定要申请退款吗?")
                .setNegativeButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(OrderDetailActivity.this, ApplyPayBackActivity.class);
                        intent.putExtra("maxMoney", orderBean.getRealAmount());
                        intent.putExtra("orderId", orderBean.getId());
                        intent.putExtra("orderItemId", orderBean.getOrderItemId());
                        intent.putExtra("status", orderBean.getOrderItemStatus());
                        startActivity(intent);
                    }
                })
                .setPositiveButton("取消", R.color.indicator_selected_color, null)
                .show();
    }

    @Override
    public void writeLogistics() {
        Intent intent = new Intent(this, BackLogisticsActivity.class);
        intent.putExtra("orderItemId", orderBean.getOrderItemId());
        startActivity(intent);
    }

    @Override
    public void pay() {
        if (orderBean != null) {
            new PayDialog(this, String.valueOf(getOrderId())).show();
        } else {
            SnackBarUtil.showShortSnackbar(tvTitle, "请稍后再试");
        }
    }

    @Override
    public void makeSureGoods() {

    }

    @Override
    public int getRefundCode() {
        return orderBean.getOrderItemRefundCode();
    }

    @Override
    public int getOrderStatus() {
        return orderBean.getOrderStatus();
    }

    @Override
    public void setWaitForPay() {
        tvOrderTitle.setText("待付款");
        tvLeftSubmit.setText("取消订单");
        tvRightSubmit.setText("付款");
        tvRightStatus.setVisibility(View.GONE);
        textTimerView.setLeftContent("剩余").setRightContent("自动关闭").setRemindTime(getRemindTime(orderBean.getExpire()));
    }

    @Override
    public void setCommitted() {
        tvOrderTitle.setText("待发货");
        tvOrdrePayTime.setVisibility(View.VISIBLE);
        tvLeftSubmit.setText("申请退款");
        tvRightSubmit.setText("提醒发货");
        tvRightStatus.setVisibility(View.GONE);
        textTimerView.setLeftContent("预计七天内发货，请耐心等待");
    }

    @Override
    public void setStay() {
        tvOrderTitle.setText("待收货");
        tvOrdrePayTime.setVisibility(View.VISIBLE);
        tvOrderSendTime.setVisibility(View.VISIBLE);
        tvLeftSubmit.setText("退货");
        tvRightSubmit.setText("确认收货");
        tvRightStatus.setText("查看物流状态");
        textTimerView.setLeftContent("卖家已发货,还剩").setRightContent("自动确认收货").setRemindTime(getRemindTime(orderBean.getAutomaticReceivingDate()));

    }

    @Override
    public void setFinish() {
        tvOrderTitle.setText("已评价");
        tvOrdrePayTime.setVisibility(View.GONE);
        tvOrderSendTime.setVisibility(View.GONE);
        textTimerView.setVisibility(View.GONE);
        tvLeftSubmit.setVisibility(View.GONE);
        tvRightSubmit.setVisibility(View.GONE);
    }


    @Override
    public void setEvaluate() {
        tvOrderTitle.setText("交易成功");
        tvOrdrePayTime.setVisibility(View.VISIBLE);
        tvOrderSendTime.setVisibility(View.VISIBLE);
        tvLeftSubmit.setVisibility(View.GONE);
        tvRightSubmit.setText("晒单");
        tvRightStatus.setText("查看物流状态");
    }

    @Override
    public void setOrderRefund() {
        tvOrderTitle.setText("退货进度");
        tvOrdrePayTime.setVisibility(View.VISIBLE);
        tvOrderSendTime.setVisibility(View.VISIBLE);

//      //        0. 原始状态, 1 申请退款, 2 卖家拒绝, 3 卖家同意(此时买家需在6天之内寄出货品), 4 取消退款,
////                5 卖家同意买家已填写好物流单号填物流单号(此时卖家需在寄出之日10日内确认收货)
////        6 卖家打款完毕
////        7 买家确认收货, 退款关闭8,
////                9 (8 - 卖家拒绝一次二次申请, 9 - 买家取消一次二次申请)
////        10 二次取消退款
////        11 异常订单状态码
////        12 卖家收到货之后拒绝打款
////        13 款项到账, 交易完毕
        int type = orderBean.getOrderItemRefundCode();
        tvRightStatus.setText("退货处理中");
        if (type == 1) {
            tvOrderTitle.setText("请等待商家处理");
            tvLeftSubmit.setText("取消退货");
            tvRightSubmit.setVisibility(View.GONE);
            textTimerView.setLeftContent("还剩").setRemindTime(getRemindTime(orderBean.getAgreeTimeRefundTime()));
        } else if (type == 2) {
            tv_one_tuikuan.setVisibility(View.VISIBLE);
            tvOrderTitle.setText("商家已拒绝");
            tvLeftSubmit.setText("申请退款");
            tvRightSubmit.setVisibility(View.GONE);
            textTimerView.setLeftContent("卖家已拒绝您的退款申请，请重新申请");
        } else if (type == 3) {
            tvOrderTitle.setText("请退货给商家");
            tvLeftSubmit.setText("取消退货");
            tvRightSubmit.setText("填写物流");
            textTimerView.setLeftContent("卖家已经同意您的退货申请，请填写快递单号退回");
        } else if (type == 4) {
            if (orderBean.getOrderStatus() == Constant.OrderStatus.STAY) {
                tv_one_tuikuan.setVisibility(View.VISIBLE);
            }
            tvOrderTitle.setText("买家取消退款");
//            tvLeftSubmit.setText("取消退货");
            tvRightSubmit.setText("提醒发货");
            tvLeftSubmit.setText("申请退款");
//            textTimerView.setLeftContent("卖家多次拒绝您的退款申请，请联系人工客服处理");
        } else if (type == 5) {
            tvOrderTitle.setText("退款成功");
            textTimerView.setLeftContent("退款资金预计1-10个工作日原路退回支付账户");
            tvLeftSubmit.setText("再次购买");
            tvRightSubmit.setVisibility(View.GONE);
            tvRightStatus.setText("已完成");
            relBottom.setVisibility(View.GONE);
            vBottom.setVisibility(View.GONE);
        } else if (type == 6) {
            tvOrderTitle.setText("退款成功");
            textTimerView.setLeftContent("卖家打款完毕");
            tvLeftSubmit.setText("再次购买");
            tvRightSubmit.setVisibility(View.GONE);
            tvRightStatus.setText("已完成");
            relBottom.setVisibility(View.GONE);
            vBottom.setVisibility(View.GONE);
        } else if (type == 7) {
            tvOrderTitle.setText("确认收货");
            textTimerView.setLeftContent("退款关闭");
            tvLeftSubmit.setText("再次购买");
            tvRightSubmit.setVisibility(View.GONE);
            tvRightStatus.setText("已完成");
            relBottom.setVisibility(View.GONE);
            vBottom.setVisibility(View.GONE);
        } else if (type == 8) {
            tvOrderTitle.setText("二次申请中");
            tvLeftSubmit.setVisibility(View.GONE);
            tvRightSubmit.setVisibility(View.GONE);
            textTimerView.setLeftContent("您申请退款中");
        } else if (type == 9) {
            tvOrderTitle.setText("二次申请中");
            tvLeftSubmit.setVisibility(View.GONE);
            tvRightSubmit.setVisibility(View.GONE);
            textTimerView.setLeftContent("您申请退款中");
        } else if (type == 10) {
            tvOrderTitle.setText("待收货");
            tvLeftSubmit.setVisibility(View.GONE);
            tvRightSubmit.setVisibility(View.VISIBLE);
            textTimerView.setLeftContent("提醒发货");
        } else if (type == 11) {
            tvOrderTitle.setText("订单异常");
            tvLeftSubmit.setVisibility(View.GONE);
            tvRightSubmit.setVisibility(View.GONE);
            textTimerView.setLeftContent("请打电话联系客服");
        } else if (type == 12) {
            tvOrderTitle.setText("卖家收货");
            tvLeftSubmit.setVisibility(View.GONE);
            tvRightSubmit.setVisibility(View.GONE);
            textTimerView.setLeftContent("卖家收到货之后拒绝打款，请联系客服");
        } else if (type == 13) {
            tvOrderTitle.setText("款项到账");
            tvLeftSubmit.setVisibility(View.GONE);
            tvRightSubmit.setVisibility(View.GONE);
            textTimerView.setLeftContent("款项到账, 交易完毕");
        }
    }

    @Override
    public int getOrderId() {
        int mOrderId = getIntent().getIntExtra(Constant.Config.ORDER_ID, 0);
        if (mOrderId == 0)
            mOrderId = Integer.parseInt(SPUtils.getStringData(Constant.Config.ORDER_ID, ""));
        return mOrderId;
    }

    @Override
    public void showMsg(String msg) {
        SnackBarUtil.showShortSnackbar(tvTitle, msg);
    }

    @Override
    public void toEvaluate() {
        Intent intent = new Intent();
        intent.putExtra("img", orderBean.getThumbnail());
        intent.putExtra("title", orderBean.getName());
        intent.putExtra("size", orderBean.getSize() + "," + orderBean.getColor());
        intent.putExtra("price", String.valueOf(orderBean.getPrice()));
        intent.putExtra("quantity", orderBean.getQuantity());
        intent.putExtra("orderId", orderBean.getId());
        intent.putExtra("orderItemId", orderBean.getOrderItemId());
        intent.putExtra("goods", orderBean.getGoods());

        intent.setClass(OrderDetailActivity.this, EvaluateActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getOrderData();
    }
}
