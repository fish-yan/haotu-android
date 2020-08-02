package com.dmeyc.dmestoreyfm.base;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.CustomLazyViewPagerAdapter;
import com.dmeyc.dmestoreyfm.utils.DensityUtil;
import com.dmeyc.dmestoreyfm.utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jockie on 2017/11/29
 * Email:jockie911@gmail.com
 */

public abstract class BaseTabActivity extends BaseActivity {

    protected TabLayout mTabLayout;
    protected ViewPager mViewPager;

    protected List<String> mTitleLists;
    protected List<BaseFragment> mFragmentLists;

    @Override
    protected void initData() {
        mTitleLists = new ArrayList<>();
        mFragmentLists = new ArrayList<>();
        getTitleList(mTitleLists);
        getFragmentLists(mFragmentLists);

        mTabLayout =  mRootView.findViewById(R.id.tablayout);
        mViewPager =  mRootView.findViewById(R.id.viewpager);
        mViewPager.setAdapter(new CustomLazyViewPagerAdapter(getSupportFragmentManager(),mFragmentLists,mTitleLists));
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
        reSetTabBlockWidth();
    }

    protected void reSetTabBlockWidth() {
        Util.reflex(mTabLayout, DensityUtil.dip2px(40));
    }

    protected abstract void getFragmentLists(List<BaseFragment> mFragmentLists);

    protected abstract void getTitleList(List<String> mTitleLists);
}
