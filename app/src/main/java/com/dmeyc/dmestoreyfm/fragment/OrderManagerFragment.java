package com.dmeyc.dmestoreyfm.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.OrderManagerAdapter;
import com.dmeyc.dmestoreyfm.base.BaseRefreshFragment;
import com.dmeyc.dmestoreyfm.bean.OrderListBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.ui.OrderDetailActivity;
import com.dmeyc.dmestoreyfm.utils.GsonTools;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jockie on 2017/9/12
 * Email:jockie911@gmail.com
 */

@SuppressLint("ValidFragment")
public class OrderManagerFragment extends BaseRefreshFragment<OrderListBean, OrderManagerAdapter> {


    private int mCurPosition = Constant.OrderStatus.ALL;

    @Bind(R.id.iv_empty)
    ImageView ivEmpty;
    @Bind(R.id.tv_empty)
    TextView tvEmpty;
    @Bind(R.id.ll_empty)
    LinearLayout llEmpty;
    @Bind(R.id.smartRl)
    SmartRefreshLayout smartRl;

    public OrderManagerFragment(int curPosition) {
        super();
        this.mCurPosition = curPosition;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_order_manager;
    }

    @Override
    protected void initData() {
        super.initData();
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
//                intent.putExtra("status",adapter.getItem(position).get(0).get(0).getOrderStatus());
//                intent.putExtra(Constant.Config.ORDER_ID,adapter.getOrderId(position));
                intent.putExtra(Constant.Config.ORDER_ID, adapter.getItem(position).get(0).get(0).getId());
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    @Override
    protected void initData(View view) {

    }

    @Override
    protected OrderManagerAdapter getAdapter() {
        return new OrderManagerAdapter(getContext(), R.layout.item_rv_order, new ArrayList(), mCurPosition);
    }

    @Override
    protected OrderListBean parseData(String string) {
        return GsonTools.changeGsonToBean(string, OrderListBean.class);
    }

    @Override
    protected void setData(OrderListBean bean, boolean isRefresh) {
        if (bean.getData() == null || Util.objEmpty(bean.getData().getListOrder())) {
            smartRl.setVisibility(View.GONE);
            llEmpty.setVisibility(View.VISIBLE);
            switch (mCurPosition) {
                case Constant.OrderStatus.ALL:
                    ivEmpty.setImageResource(R.drawable.daizhifu_img);
                    tvEmpty.setText("您暂时没有订单");
                    break;
                case Constant.OrderStatus.WAIT_PAY:
                    ivEmpty.setImageResource(R.drawable.daizhifu_img);
                    tvEmpty.setText("您暂时还没有待支付的订单");
                    break;
                case Constant.OrderStatus.COMMITTED:
                    ivEmpty.setImageResource(R.drawable.daishouhuo_img);
                    tvEmpty.setText("您暂时还没有待发货的订单");
                    break;
                case Constant.OrderStatus.STAY:
                    ivEmpty.setImageResource(R.drawable.shouhuo_img);
                    tvEmpty.setText("您暂时还没有待收货的订单");
                    break;
                case Constant.OrderStatus.EVALUATE:
                    ivEmpty.setImageResource(R.drawable.pinglun_img);
                    tvEmpty.setText("您暂时还没有待评价的订单");
                    break;
                case Constant.OrderStatus.BACK:
                    tvEmpty.setText("您暂时还没有退货的订单");
                    break;
            }
            adapter.clear();
        } else {
            smartRl.setVisibility(View.VISIBLE);
            llEmpty.setVisibility(View.GONE);
            adapter.addData(bean.getData().getListOrder(), isRefresh);
        }
    }

    @Override
    protected String getUrl() {
        return Constant.API.ORDER_MANAGER;
    }

    @Override
    protected Map<String, Object> getParamMap() {
        ParamMap.Build build = new ParamMap.Build();
        if (Constant.OrderStatus.ALL != mCurPosition &&Constant.OrderStatus.BACK != mCurPosition)
            build.addParams("status", mCurPosition);
        if(Constant.OrderStatus.BACK == mCurPosition){
            build.addParams("refundCode","0");
        }
        return build.build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
