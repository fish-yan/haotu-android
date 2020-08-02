package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.ShopCarAdapter;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.CarListBean;
import com.dmeyc.dmestoreyfm.bean.event.BagEvent;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.dmeyc.dmestoreyfm.wedgit.PriceView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class ShopCarActivity extends BaseActivity {

    @Bind(R.id.recycleview)
    RecyclerView mRecycleView;
    @Bind(R.id.cb_all)
    CheckBox cbAll;
    @Bind(R.id.tv_total_price)
    PriceView tvTotalPrice;
    @Bind(R.id.ll_empty)
    LinearLayout llEmpty;
    @Bind(R.id.ll_content)
    LinearLayout llContent;
    private ShopCarAdapter adapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.ac_shop_car;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        tvTitle.setText("购物车");
        tvRightTitle.setText("编辑");

        mRecycleView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ShopCarAdapter(ShopCarActivity.this, R.layout.item_rv_shop_car, new ArrayList(),this);
        mRecycleView.setAdapter(adapter);

        adapter.setOnStatusChangeLister(new ShopCarAdapter.OnStatusChangeLister() {
            @Override
            public void statusChangeLister(boolean isAllChecked, int totalPrice) {
                cbAll.setChecked(isAllChecked);
                tvTotalPrice.setPrice(String.valueOf(totalPrice));
            }
        });
        requestData();
    }

    public void requestData() {
        EventBus.getDefault().post(new BagEvent());
        RestClient.getNovate(this).get(Constant.API.SHOW_SHOPCAR, new ParamMap.Build().addParams("userId",Util.getUserId()).build(), new DmeycBaseSubscriber<CarListBean>(this) {
            @Override
            public void onSuccess(CarListBean bean) {
                if(bean.getData().getCartItems() == null || bean.getData().getCartItems().size() == 0 ){
                    llEmpty.setVisibility(View.VISIBLE);
                    llContent.setVisibility(View.GONE);
                }else{
                    llContent.setVisibility(View.VISIBLE);
                    adapter.clear();
                    adapter.addData(bean.getData().getCartItems());
                }
            }
        });
    }

    @OnClick({R.id.tv_right_title_bar,R.id.cb_all,R.id.tv_pay})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_right_title_bar:   //编辑
                if("编辑".equals(tvRightTitle.getText().toString())){ //编辑状态
                    tvRightTitle.setText("完成");
                    adapter.editItemCount(true);
                }else{                                                //完成状态
                    tvRightTitle.setText("编辑");
                    adapter.editItemCount(false);
                }
                break;
            case R.id.cb_all:
                adapter.setAllChecked(cbAll.isChecked());
                break;
            case R.id.tv_pay:
                if(TextUtils.equals("0",tvTotalPrice.getPrice())){
                    ToastUtil.show("请先选择");
                }else{
                    ArrayList<ArrayList<CarListBean.DataBean.CartItemsBean>> selectedData = adapter.getSelectedData();
                    Intent intent = new Intent(this, MakeOrderActivity.class);

                    ArrayList<CarListBean.DataBean.CartItemsBean> mData = new ArrayList<>();
                    int startPositon = 0;
                    for (ArrayList<CarListBean.DataBean.CartItemsBean> list : selectedData) {
                        for (CarListBean.DataBean.CartItemsBean itemsBean : list) {
                            itemsBean.setTempTargetPostion(startPositon);
                            mData.add(itemsBean);
                        }
                        startPositon ++;
                    }
                    intent.putParcelableArrayListExtra("item", mData);
                    intent.putExtra("order",MakeOrderActivity.ORDER_FROM_CAR);
                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestData();
    }
}
