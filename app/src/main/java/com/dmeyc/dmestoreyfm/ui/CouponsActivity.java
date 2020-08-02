package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.CouponAdapter;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.CouponBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class CouponsActivity extends BaseActivity {
    @Bind(R.id.recycleview)
    RecyclerView mRecycleView;
    @Bind(R.id.et_exchange)
    EditText etExchange;
    private CouponAdapter couponAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.ac_coupons;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        final double minCanUsePrice = getIntent().getDoubleExtra("price", 0); //最小使用条件
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));

        couponAdapter = new CouponAdapter(this, R.layout.item_rv_coupons, new ArrayList());
        //TODO SHOW_COUPON
        mRecycleView.setAdapter(couponAdapter);

        RestClient.getNovate(this).get(Constant.API.SHOW_COUPON, new ParamMap.Build().build(), new DmeycBaseSubscriber<CouponBean>(this) {
            @Override
            public void onSuccess(CouponBean bean) {
                couponAdapter.setMinUsePrice(minCanUsePrice);
                couponAdapter.addData(bean.getData());
            }
        });
        couponAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if(minCanUsePrice > 0){
                    CouponBean.DataBean item = couponAdapter.getItem(position);
                    Intent intent = new Intent();
                    intent.putExtra("item",item);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    @OnClick({R.id.tv_exchange})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_exchange:
                ToastUtil.show(TextUtils.isEmpty(etExchange.getText().toString()) ? "请输入兑换码" : "兑换失败");
                break;
        }
    }
}
