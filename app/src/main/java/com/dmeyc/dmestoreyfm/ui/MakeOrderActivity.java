package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.MakeOrderAdapter;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.bean.AddressBean;
import com.dmeyc.dmestoreyfm.bean.CarListBean;
import com.dmeyc.dmestoreyfm.bean.CouponBean;
import com.dmeyc.dmestoreyfm.bean.SelectInfo;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.present.IMakeOrderView;
import com.dmeyc.dmestoreyfm.present.MakeOrderPersenter;
import com.dmeyc.dmestoreyfm.utils.SnackBarUtil;
import com.dmeyc.dmestoreyfm.wedgit.PriceView;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MakeOrderActivity extends BaseActivity<MakeOrderPersenter> implements View.OnClickListener,IMakeOrderView {

    @Bind(R.id.recycleview)
    RecyclerView mRecycleView;
    @Bind(R.id.tv_total_price)
    PriceView tvTotalPrice;
    private TextView headTvName;
    private TextView headAddAdress;
    private TextView headTvAdress;
    private RelativeLayout headRel;
    private ArrayList<CarListBean.DataBean.CartItemsBean> item;
    private List<List<CarListBean.DataBean.CartItemsBean>> mData = new ArrayList<>();

    public final static int ORDER_FROM_PRODUCT = 0;
    public final static int ORDER_FROM_CAR = 1;
    private int receiverId;
    private MakeOrderAdapter adapter;
    private int mCouponId;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_make_order;
    }

    @Override
    protected MakeOrderPersenter initPresenter() {
        return new MakeOrderPersenter();
    }

    @Override
    protected void initData() {
        tvTitle.setText("确认订单");
        item = getIntent().getParcelableArrayListExtra("item");

        //get data
        ArrayList<CarListBean.DataBean.CartItemsBean> target;
        for (int i = 0; i <= item.get(item.size() - 1).getTempTargetPostion(); i++) {
            target = new ArrayList<>();
            for (CarListBean.DataBean.CartItemsBean bean : item) {
                if(i == bean.getTempTargetPostion()){
                    target.add(bean);
                }
            }
            mData.add(target);
        }

        tvTotalPrice.setPrice(String.valueOf(getTotalPrice()));
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MakeOrderAdapter(this, R.layout.item_rv_make_order,mData);

        HeaderAndFooterWrapper wrapper = new HeaderAndFooterWrapper(adapter);
        View headview = View.inflate(this, R.layout.head_make_order_address, null);
        wrapper.addHeaderView(headview);
        initHeadView(headview);

        mRecycleView.setAdapter(wrapper);
    }

    private void initHeadView(View headview) {
        headAddAdress =  headview.findViewById(R.id.head_tv_add_address);
        headTvName =  headview.findViewById(R.id.head_tv_name);
        headTvAdress =  headview.findViewById(R.id.head_tv_address);
        headRel =  headview.findViewById(R.id.head_rel);

        headAddAdress.setOnClickListener(this);
        headRel.setOnClickListener(this);
    }

    @OnClick({R.id.tv_pay})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.head_tv_add_address:
            case R.id.head_rel:
                Intent intent = new Intent(this, ManageAddressActivity.class);
                intent.putExtra(Constant.Config.IS_FOR_RESULT,true);
                startActivityForResult(intent,0);
                break;
            case R.id.tv_pay:
                mPresenter.pay();
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null && resultCode == RESULT_OK){
            if(requestCode == 0){
                AddressBean.DataBean.ReceiverBean item = data.getParcelableExtra("item");
                headAddAdress.setVisibility(View.INVISIBLE);
                headRel.setVisibility(View.VISIBLE);
                receiverId = item.getId();
                headTvName.setText(item.getReceiverPeople() + " " + item.getMobile());
                headTvAdress.setText(item.getArea() + " " + item.getAddress());
            }else if(requestCode == 1){
                CouponBean.DataBean item = data.getParcelableExtra("item");
                mCouponId = item.getId();
                adapter.setCouple(item.getPrice());
                tvTotalPrice.setPrice(String.valueOf(getTotalPrice() - item.getPrice()));
            }
        }
    }

    public double getTotalPrice(){
        double totalPrice = 0;
        for (CarListBean.DataBean.CartItemsBean mData : item) {
            totalPrice += mData.getProductInfo().getPrice() * mData.getQuantity();
        }
        return totalPrice;
    }

    @Override
    public boolean isDirectBuy() {
        return getIntent().getIntExtra("order", 0) == ORDER_FROM_PRODUCT;
    }

    @Override
    public boolean isCustom() {
        return getIntent().getBooleanExtra("isCustom",false);
    }

    @Override
    public int getCouponId() {
        return mCouponId;
    }

    @Override
    public int getReceiverId() {
        return receiverId;
    }

    @Override
    public int getProductId() {
        return getIntent().getIntExtra("productId",0);
    }

    @Override
    public void showMsg(String msg) {
        SnackBarUtil.showShortSnackbar(tvTitle,msg);
    }

    @Override
    public String getCartItemIds() {
        String  cartItemIds = "";
        for (int i = 0; i < item.size(); i++) {
            cartItemIds += String.valueOf(item.get(i).getId()) + ((i == item.size() - 1) ? "" : ",");
        }
        return cartItemIds;
    }

    @Override
    public SelectInfo getSelectInfo() {
        return getIntent().getParcelableExtra("selectinfo");
    }

    @Override
    public String getCustoms() {
        return getIntent().getStringExtra("customs");
    }
}
