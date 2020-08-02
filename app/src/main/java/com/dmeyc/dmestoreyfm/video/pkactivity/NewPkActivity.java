package com.dmeyc.dmestoreyfm.video.pkactivity;

import android.app.Activity;
import android.content.Intent;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.fragment.PKFragment;

public class NewPkActivity extends BaseActivity {

    public static void newIntent(Activity activity){
        Intent intent = new Intent(activity , NewPkActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.ac_brand;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected boolean isContainTitle() {
        return false;
    }

    @Override
    protected void initData() {
        PKFragment newBrandFragment = new PKFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container,newBrandFragment)   // 此处的R.id.fragment_container是要盛放fragment的父容器
                .commit();
    }
}
