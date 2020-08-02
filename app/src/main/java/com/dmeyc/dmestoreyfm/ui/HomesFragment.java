package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.CustomLazyViewPagerAdapter;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.fragment.ActionFragment;
import com.dmeyc.dmestoreyfm.fragment.ActivityItemFragment;
import com.dmeyc.dmestoreyfm.fragment.home.HomeLookFragment;
import com.dmeyc.dmestoreyfm.ui.show.SpecialFragment;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jockie on 2017/11/3
 * Email:jockie911@gmail.com
 */

public class HomesFragment extends BaseFragment implements View.OnClickListener {

    private ViewPager view_pager;
    private TabLayout tabs;
    private ImageView add_channel_iv;
    private   ActivityItemFragment aim;
     private TextView tv_pk,tv_action;
     private FrameLayout fl_tab;

    FragmentTransaction fragmentTransaction;
    Fragment fragment;
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_homes;
    }
    protected List<String> mTitleLists; //导航条文字List
    protected List<BaseFragment> mFragmentLists;    //导航fragment list
    @Override
    protected void initData() {
        tabs = (TabLayout) mRootView.findViewById(R.id.tabs); //使用bind 会出现空指针

        tabs.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(tabs,20,0);
            }
        });
        view_pager = (ViewPager) mRootView.findViewById(R.id.view_pager); //使用bind 会出现空指针
        add_channel_iv = (ImageView) mRootView.findViewById(R.id.add_channel_iv); //使用bind 会出现空指针

//        tv_pk = (TextView) mRootView.findViewById(R.id.tv_pk); //使用bind 会出现空指针
//        tv_action = (TextView) mRootView.findViewById(R.id.tv_action); //使用bind 会出现空指针
//        fl_tab=mRootView.findViewById(R.id.fl_tab);

//        fragment= new ActionFragment();
//        FragmentManager fragmentManager =getFragmentManager();
//        fragmentTransaction=fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.fl_tab,fragment);
//        fragmentTransaction.commit();


//        tv_pk.setOnClickListener(this);
//        tv_action.setOnClickListener(this);
        add_channel_iv.setOnClickListener(this);
        mTitleLists = new ArrayList<>();
        mFragmentLists = new ArrayList<>();
        getTitleList(mTitleLists);
        getFragmentLists(mFragmentLists);


        FragmentManager fm =getActivity().getSupportFragmentManager();
        view_pager.setAdapter(new CustomLazyViewPagerAdapter(fm,mFragmentLists,mTitleLists));
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabs.setupWithViewPager(view_pager);
//        reSetTabBlockWidth();
    }

    protected void getTitleList(List<String> mTitleLists) {
        mTitleLists.add("全部");
        mTitleLists.add("品牌");
        mTitleLists.add("全部");
        mTitleLists.add("品牌");
        mTitleLists.add("全部");
        mTitleLists.add("品牌");
        mTitleLists.add("全部");
        mTitleLists.add("品牌");
        mTitleLists.add("全部");
        mTitleLists.add("品牌");
        mTitleLists.add("全部");
        mTitleLists.add("品牌");
//        mTitleLists.add("专题");
    }
    protected void getFragmentLists(List<BaseFragment> mFragmentLists) {
//        cf=new CategoryFragment(getStoryId());
//        mFragmentLists.add(cf);
//        bsf= new BrandStoreFragment(getStoryId());
//        mFragmentLists.add(bsf);
//        aim=   new ActivityItemFragment(0);
//        mFragmentLists.add(aim);
        mFragmentLists.add(new HomeLookFragment());
        mFragmentLists.add(new SpecialFragment());
        mFragmentLists.add(new HomeLookFragment());
        mFragmentLists.add(new SpecialFragment());
        mFragmentLists.add(new HomeLookFragment());
        mFragmentLists.add(new SpecialFragment());
        mFragmentLists.add(new HomeLookFragment());
        mFragmentLists.add(new SpecialFragment());
    }

    @Override
    protected void initData(View view) {

    }

    @Override
    public void onClick(View v) {
        int viewid=v.getId();
        if(viewid==R.id.add_channel_iv){
//            ToastUtil.show("你好哈哈哈哈");
            Intent intent = new Intent(getActivity(), NewsChannelActivity.class);
            startActivity(intent);
        }else if(viewid==R.id.tv_pk){
//            mFragmentLists.remove(0);
//            mFragmentLists.add(0,new HomeLookFragment());
//            ToastUtil.show("PK");
            fragmentTransaction.hide(fragment).commit();
//            fragment=new PKFragment();
            fragmentTransaction.replace(R.id.fl_tab,fragment);
//            fragmentTransaction.commit();
//            aim.invilidate(1);
        }else if(viewid==R.id.tv_action){
//            ToastUtil.show("huodong");
            fragmentTransaction.hide(fragment).commit();
//            fragment=new ActionFragment();
            fragmentTransaction.replace(R.id.fl_tab,fragment);
//            aim.invilidate(0);

//        fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();
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
//
}
