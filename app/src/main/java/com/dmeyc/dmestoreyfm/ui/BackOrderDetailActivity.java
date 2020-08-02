package com.dmeyc.dmestoreyfm.ui;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;

public class BackOrderDetailActivity extends BaseActivity {

    @Override
    protected int getLayoutRes() {
        return R.layout.ac_back_order_detail;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        tvTitle.setText("退货进度");
    }
}
