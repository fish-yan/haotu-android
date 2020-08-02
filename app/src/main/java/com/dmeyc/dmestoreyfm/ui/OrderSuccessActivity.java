package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.view.View;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.constant.Constant;

import butterknife.OnClick;

public class OrderSuccessActivity extends BaseActivity {

    @Override
    protected int getLayoutRes() {
        return R.layout.ac_order_success;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        tvTitle.setText("支付成功");
    }

    @OnClick({R.id.tv_order_detail,R.id.tv_go_shop})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_order_detail:
                startActivity(new Intent(this,OrderDetailActivity.class));
                break;
            case R.id.tv_go_shop:
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(Constant.Config.POSITION,1);
                startActivity(intent);
                break;
        }
    }
}
