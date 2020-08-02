package com.dmeyc.dmestoreyfm.base;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

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

public abstract class BaseTabFragment extends BaseFragment{

//    @Bind(R.id.tablayout)
    protected TabLayout mTabLayout;
//    @Bind(R.id.viewpager)
    protected ViewPager mViewPager; //使用bind可能会为null

    protected List<String> mTitleLists; //导航条文字List
    protected List<BaseFragment> mFragmentLists;    //导航fragment list

    @Override
    protected void initData() {
        mTitleLists = new ArrayList<>();
        mFragmentLists = new ArrayList<>();
        getTitleList(mTitleLists);
        getFragmentLists(mFragmentLists);

        mTabLayout = (TabLayout) mRootView.findViewById(R.id.tablayout);
        mViewPager = (ViewPager) mRootView.findViewById(R.id.viewpager);
        mTabLayout.setVisibility(View.INVISIBLE);
        mViewPager.setAdapter(new CustomLazyViewPagerAdapter(getChildFragmentManager(),mFragmentLists,mTitleLists));
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
        reSetTabBlockWidth();
        mTabLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mTabLayout.setVisibility(View.VISIBLE);
            }
        },200);
    }

    /**
     * 导航条长度形态改变,适应字体长度或者等分, 详情见Util
     */
    protected void reSetTabBlockWidth() {
        Util.reflex(mTabLayout,DensityUtil.dip2px(75));
    }

    /**
     * tab title list
     * @param mTitleLists
     */
    protected abstract void getTitleList(List<String> mTitleLists);

    /**
     * fragment list
     * @param mFragmentLists
     */
    protected abstract void getFragmentLists(List<BaseFragment> mFragmentLists);

    public void setCurrentItem(int position){
        setCurrentItem(position,false);
    }

    /**
     * 选中viewpager的位置
     * @param position
     * @param isSmoothMove
     */
    public void setCurrentItem(int position,boolean isSmoothMove){
        if(mViewPager != null)
            mViewPager.setCurrentItem(position,isSmoothMove);
    }


}
