package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.view.View;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;

import butterknife.OnClick;

public class BigActionActivity extends BaseActivity {
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_bigaction;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }
    @OnClick({R.id.btn_login,R.id.tv_left_title_bar})
    public void onClick(View view){
        int viewid=view.getId();
        if(R.id.btn_login==viewid){

startActivity(new Intent(BigActionActivity.this,BigActionEducationActivity.class));
        }else if(R.id.tv_left_title_bar==viewid){
            finish();
        }

    }
}
