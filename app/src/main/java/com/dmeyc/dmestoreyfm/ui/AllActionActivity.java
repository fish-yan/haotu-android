package com.dmeyc.dmestoreyfm.ui;

import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.CustomLazyViewPagerAdapter;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.YFMUserBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.fragment.ActionItemFragemnt;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class AllActionActivity extends BaseActivity {

    protected List<String> mTitleLists; //导航条文字List
    protected List<BaseFragment> mFragmentLists;    //导航fragment list
    private ViewPager viewpageraction;
    private TabLayout tablayoutaction;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_allaction;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

        tablayoutaction = (TabLayout) mRootView.findViewById(R.id.tablayoutaction); //使用bind 会出现空指针
        tablayoutaction.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(tablayoutaction,0,0);
            }
        });
        viewpageraction = (ViewPager) mRootView.findViewById(R.id.viewpageraction); //使用bind 会出现空指针

        mTitleLists = new ArrayList<>();
        mFragmentLists = new ArrayList<>();
        getTitleList(mTitleLists);
        getFragmentLists(mFragmentLists);

        FragmentManager fm =getSupportFragmentManager();
        viewpageraction.setAdapter(new CustomLazyViewPagerAdapter(fm,mFragmentLists,mTitleLists));
        tablayoutaction.setTabMode(TabLayout.MODE_SCROLLABLE);
        tablayoutaction.setupWithViewPager(viewpageraction);

        String actiontype=getIntent().getStringExtra("actiontype");
         if("1".equals(actiontype)){
            viewpageraction.setCurrentItem(0);
        }else if("2".equals(actiontype)){
            viewpageraction.setCurrentItem(1);
        }else if("3".equals(actiontype)){
            viewpageraction.setCurrentItem(2);
        }else {
            viewpageraction.setCurrentItem(3);
        }

    }
    protected void getTitleList(List<String> mTitleLists) {
        mTitleLists.add("全部");
//        mTitleLists.add("校园");
        mTitleLists.add("未开始");
        mTitleLists.add("进行中");
        mTitleLists.add("已结束");
    }
    AllActionFragment atf;
    protected void getFragmentLists(List<BaseFragment> mFragmentLists) {
        for (int i=0;i<mTitleLists.size();i++){
            atf=  new AllActionFragment(i);
            mFragmentLists.add(atf);
        }
    }
    public void setIndicator (TabLayout tabs,int leftDip,int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

}
