package com.dmeyc.dmestoreyfm.ui;

import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;

import butterknife.Bind;

public class NoticeShowActivity  extends BaseActivity {
    @Bind(R.id.tv_noteice)
    TextView tv_noteice;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_noticeshow;
    }
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        tv_noteice.setText(getIntent().getStringExtra("notice"));
    }
}
