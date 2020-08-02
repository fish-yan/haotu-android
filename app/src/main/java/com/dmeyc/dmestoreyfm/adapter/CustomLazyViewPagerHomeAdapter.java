package com.dmeyc.dmestoreyfm.adapter;





import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class CustomLazyViewPagerHomeAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentLists;
    private List<String> mTabTitleLists;

    public CustomLazyViewPagerHomeAdapter(FragmentManager fm, List<Fragment> mFragmentLists) {
        super(fm);
        this.mFragmentLists = mFragmentLists;
    }

    public CustomLazyViewPagerHomeAdapter(FragmentManager fm, List<Fragment> mFragmentLists, List<String> tabTitleLists) {
        super(fm);
        this.mFragmentLists = mFragmentLists;
        this.mTabTitleLists = tabTitleLists;
    }

//    @Override
//    protected Fragment getItem(ViewGroup left, int position) {
//    }

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

