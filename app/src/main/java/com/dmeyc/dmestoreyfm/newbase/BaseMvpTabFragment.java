package com.dmeyc.dmestoreyfm.newbase;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.CustomLazyViewPagerNewAdapter;
import com.dmeyc.dmestoreyfm.utils.DensityUtil;
import com.dmeyc.dmestoreyfm.utils.Util;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMvpTabFragment<V,P extends BasePresenter<V>> extends BaseMVPFragment<V,P>{

    protected TabLayout mTabLayout;
    protected ViewPager mViewPager; //使用bind可能会为null

    protected List<String> mTitleLists; //导航条文字List
    protected List<BaseFragment> mFragmentLists;    //导航fragment list

    @Override
    protected void initViews(View mRootView) {
        mTitleLists = new ArrayList<>();
        mFragmentLists = new ArrayList<>();
        getTitleList(mTitleLists);
        getFragmentLists(mFragmentLists);

        mTabLayout = (TabLayout) mRootView.findViewById(R.id.tablayout);
        mViewPager = (ViewPager) mRootView.findViewById(R.id.viewpager);
        mTabLayout.setVisibility(View.INVISIBLE);
        mViewPager.setAdapter(new CustomLazyViewPagerNewAdapter(getChildFragmentManager(),mFragmentLists,mTitleLists));
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(mFragmentLists.size());
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
        Util.reflex(mTabLayout, DensityUtil.dip2px(15));
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

    protected void reset(){
        if (mTitleLists==null || mFragmentLists==null){
            return;
        }
        mTitleLists.clear();
        mFragmentLists.clear();
        getTitleList(mTitleLists);
        getFragmentLists(mFragmentLists);
        mViewPager.setAdapter(new CustomLazyViewPagerNewAdapter(getChildFragmentManager(),mFragmentLists,mTitleLists));
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(mFragmentLists.size());
    }

}