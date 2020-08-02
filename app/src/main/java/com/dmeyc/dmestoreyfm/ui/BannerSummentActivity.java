package com.dmeyc.dmestoreyfm.ui;

import android.view.View;
import android.widget.ImageView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;

import butterknife.Bind;

public class BannerSummentActivity extends BaseActivity {
    @Bind(R.id.iv_bake)
    ImageView iv_bake;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_bannersummet;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        iv_bake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
