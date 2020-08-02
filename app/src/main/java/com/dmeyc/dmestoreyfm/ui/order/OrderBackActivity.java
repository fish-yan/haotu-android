package com.dmeyc.dmestoreyfm.ui.order;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.fragment.OrderManagerFragment;

public class OrderBackActivity extends BaseActivity {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_order_back;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        tvTitle.setText("退货/售后");
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new OrderManagerFragment(Constant.OrderStatus.BACK)).commit();
    }
}
