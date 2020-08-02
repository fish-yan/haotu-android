package com.dmeyc.dmestoreyfm.video.newbrand;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.fragment.home.BrandFragment;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;

import butterknife.Bind;

public class NewBrandActivity extends BaseActivity {

    public static void newIntent(Activity activity,String title){
        Intent intent = new Intent(activity,NewBrandActivity.class);
        intent.putExtra("title",title);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutRes(){
        return R.layout.ac_brand;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
    @Override
    protected void initData() {
        String title = getIntent().getStringExtra("title");

        NewBrandFragment newBrandFragment = new NewBrandFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title",title);
        newBrandFragment.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container,newBrandFragment)   // 此处的R.id.fragment_container是要盛放fragment的父容器
                .commit();
    }

    @Override
    protected boolean isContainTitle() {
        return false;
    }
}
