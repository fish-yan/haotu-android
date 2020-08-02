package com.dmeyc.dmestoreyfm.ui;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;

import butterknife.Bind;

public class NoNetWorkActivity extends BaseActivity{

    @Bind(R.id.ll_father_no_newwork)
    LinearLayout ll_father_no_newwork;
    @Bind(R.id.text_rush)
    TextView text_rush;

    @Override
    protected int getLayoutRes() {
        return R.layout.base_no_network;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        text_rush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
