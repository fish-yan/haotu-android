package com.dmeyc.dmestoreyfm.newbase;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.CustomLazyViewPagerNewAdapter;
import com.dmeyc.dmestoreyfm.utils.DensityUtil;
import com.dmeyc.dmestoreyfm.utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * create by cxg on 2019/11/29
 */
public abstract class BaseTabNewActivity<V,P  extends BasePresenter<V>> extends BaseMvpActivity<V,P> {
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

        mTabLayout = findViewById(R.id.tablayout);
        mViewPager = findViewById(R.id.viewpager);
        mViewPager.setAdapter(new CustomLazyViewPagerNewAdapter(getSupportFragmentManager(),mFragmentLists,mTitleLists));
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(mTitleLists.size());
        reSetTabBlockWidth();
    }

    protected void reSetTabBlockWidth() {
        Util.reflex(mTabLayout, DensityUtil.dip2px(40));
    }

    protected abstract void getFragmentLists(List<BaseFragment> mFragmentLists);

    protected abstract void getTitleList(List<String> mTitleLists);
}
