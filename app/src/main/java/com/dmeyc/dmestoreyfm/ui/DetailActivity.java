package com.dmeyc.dmestoreyfm.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.CustomLazyViewPagerAdapter;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.fragment.MoneyInFragment;
import com.dmeyc.dmestoreyfm.fragment.MoneyOutFragment;
import com.dmeyc.dmestoreyfm.fragment.NoScrollViewPager;
import com.dmeyc.dmestoreyfm.fragment.brand.BrandDesignFragment;
import com.dmeyc.dmestoreyfm.ui.show.SpecialFragment;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends BaseActivity {
    protected List<String> mTitleLists; //导航条文字List
    protected List<BaseFragment> mFragmentLists;    //导航fragment list

//    @Bind(R.id.tablayout)
    TabLayout mTabLayout;

//    @Bind(R.id.viewpager)
    NoScrollViewPager mViewPager;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_detail;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

        mTitleLists = new ArrayList<>();
        mFragmentLists = new ArrayList<>();
        getTitleList(mTitleLists);
        getFragmentLists(mFragmentLists);

        mTabLayout = (TabLayout) findViewById(R.id.detail_tablayout);
        mViewPager = (NoScrollViewPager)findViewById(R.id.detai_viewpager);


        FragmentManager fm =getSupportFragmentManager();
        mViewPager.setAdapter(new CustomLazyViewPagerAdapter(fm,mFragmentLists,mTitleLists));
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
//        mTabLayout.setVisibility(View.INVISIBLE);
    }

    protected void getTitleList(List<String> mTitleLists) {
        mTitleLists.add("支出");
        mTitleLists.add("收入");
    }
    protected void getFragmentLists(List<BaseFragment> mFragmentLists) {
        mFragmentLists.add(new MoneyOutFragment(getIntent().getIntExtra("groupid",-1)));
        mFragmentLists.add(new MoneyInFragment(getIntent().getIntExtra("groupid",-1)));
//        mFragmentLists.add(new HomeLookFragment());
//        mFragmentLists.add(new SpecialFragment());
    }

}
