package com.dmeyc.dmestoreyfm.ui;

import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.utils.Util;

import butterknife.Bind;

public class AboutUsActivity extends BaseActivity {

    @Bind(R.id.tv_version)
    TextView tvVersion;
    @Override
    protected int getLayoutRes() {
        return R.layout.ac_about_us;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        tvTitle.setText("关于我们");
        tvVersion.setText("V" + Util.getLocalVersionName(this));
    }
}
