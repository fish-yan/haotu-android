package com.dmeyc.dmestoreyfm.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.dmeyc.dmestoreyfm.newbase.BaseFragment;

import java.util.List;

/**
 * Created by jockie on 2017/9/14
 * Email:jockie911@gmail.com
 */

public class CustomLazyViewPagerNewAdapter extends FragmentStatePagerAdapter {

    private List<BaseFragment> mFragmentLists;
    private List<String> mTabTitleLists;

    public CustomLazyViewPagerNewAdapter(FragmentManager fm, List<BaseFragment> mFragmentLists) {
        super(fm);
        this.mFragmentLists = mFragmentLists;
    }

    public CustomLazyViewPagerNewAdapter(FragmentManager fm, List<BaseFragment> mFragmentLists, List<String> tabTitleLists) {
        super(fm);
        this.mFragmentLists = mFragmentLists;
        this.mTabTitleLists = tabTitleLists;
    }

    @Override
    public int getCount() {
        return mFragmentLists == null ? 0 : mFragmentLists.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(mTabTitleLists != null && mTabTitleLists.size() != 0)
            return mTabTitleLists.get(position % mTabTitleLists.size());
        return super.getPageTitle(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentLists.get(position);
    }
}
