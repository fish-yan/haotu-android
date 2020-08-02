package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.CustomLazyViewPagerAdapter;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.fragment.ActionItemFragemnt;
import com.dmeyc.dmestoreyfm.fragment.AppointmentFragemnt;
import com.dmeyc.dmestoreyfm.fragment.CalculateFragment;
import com.dmeyc.dmestoreyfm.fragment.CommFragment;
import com.dmeyc.dmestoreyfm.fragment.CommPKFragment;
import com.dmeyc.dmestoreyfm.fragment.CourPushFragment;
import com.dmeyc.dmestoreyfm.fragment.ProdFragmen;
import com.dmeyc.dmestoreyfm.fragment.home.BrandFragment;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ScreenUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class ProdRecordActivity extends BaseActivity {

    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.viewpager)
    ViewPager viewpager;


//
//    @Bind(R.id.ll_all)
//    LinearLayout ll_all;
//    @Bind(R.id.ll_comm)
//    LinearLayout ll_comm;
//    @Bind(R.id.ll_myself)
//    LinearLayout ll_myself;
@Bind(R.id.circleone)
ImageView circleone;
    @Bind(R.id.iv_cicletwo)
    ImageView iv_cicletwo;
    @Bind(R.id.iv_ciclethree)
    CircleImageView iv_ciclethree;

    protected List<String> mTitleLists; //导航条文字List
    protected List<BaseFragment> mFragmentLists;    //导航fragment list
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_prodrecord;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        mTitleLists = new ArrayList<>();
        mFragmentLists = new ArrayList<>();
        GlideUtil.loadImage(this,SPUtils.getStringData(Constant.Config.AVATAR),iv_ciclethree);
        getTitleList(mTitleLists);
        getFragmentLists(mFragmentLists);
        tablayout.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(tablayout, 0, 0);
            }
        });
        FragmentManager fm = getSupportFragmentManager();
        viewpager.setAdapter(new CustomLazyViewPagerAdapter(fm, mFragmentLists, mTitleLists));
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tablayout.setupWithViewPager(viewpager);

        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {


                 if(0==position){
                     LinearLayout.LayoutParams ll=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                     ll.height=ScreenUtil.dp2px(ProdRecordActivity.this,60);
                     ll.width=ScreenUtil.dp2px(ProdRecordActivity.this,60);
                     ll.leftMargin=ScreenUtil.dp2px(ProdRecordActivity.this,10);
                     circleone.setLayoutParams(ll);
                     LinearLayout.LayoutParams lltwo=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                     lltwo.height=ScreenUtil.dp2px(ProdRecordActivity.this,50);
                     lltwo.width=ScreenUtil.dp2px(ProdRecordActivity.this,50);
                     lltwo.leftMargin=ScreenUtil.dp2px(ProdRecordActivity.this,10);
                     iv_cicletwo.setLayoutParams(lltwo);
                     LinearLayout.LayoutParams llthree=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                     llthree.leftMargin=ScreenUtil.dp2px(ProdRecordActivity.this,10);
                     llthree.height=ScreenUtil.dp2px(ProdRecordActivity.this,50);
                     llthree.width=ScreenUtil.dp2px(ProdRecordActivity.this,50);
                     iv_ciclethree.setLayoutParams(llthree);
                 }else if(1==position){
                     LinearLayout.LayoutParams ll=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                     ll.height=ScreenUtil.dp2px(ProdRecordActivity.this,60);
                     ll.width=ScreenUtil.dp2px(ProdRecordActivity.this,60);
                     ll.leftMargin=ScreenUtil.dp2px(ProdRecordActivity.this,10);
                     iv_cicletwo.setLayoutParams(ll);
                     LinearLayout.LayoutParams lltwo=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                     lltwo.height=ScreenUtil.dp2px(ProdRecordActivity.this,50);
                     lltwo.width=ScreenUtil.dp2px(ProdRecordActivity.this,50);
                     lltwo.leftMargin=ScreenUtil.dp2px(ProdRecordActivity.this,10);
                     circleone.setLayoutParams(lltwo);
                     LinearLayout.LayoutParams llthree=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                     llthree.height=ScreenUtil.dp2px(ProdRecordActivity.this,50);
                     llthree.width=ScreenUtil.dp2px(ProdRecordActivity.this,50);
                     llthree.leftMargin=ScreenUtil.dp2px(ProdRecordActivity.this,10);
                     iv_ciclethree.setLayoutParams(llthree);
                 }else {
                     LinearLayout.LayoutParams ll=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                     ll.height=ScreenUtil.dp2px(ProdRecordActivity.this,60);
                     ll.width=ScreenUtil.dp2px(ProdRecordActivity.this,60);
                     ll.leftMargin=ScreenUtil.dp2px(ProdRecordActivity.this,20);
                     iv_ciclethree.setLayoutParams(ll);
                     LinearLayout.LayoutParams lltwo=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                     lltwo.height=ScreenUtil.dp2px(ProdRecordActivity.this,50);
                     lltwo.width=ScreenUtil.dp2px(ProdRecordActivity.this,50);
                     lltwo.leftMargin=ScreenUtil.dp2px(ProdRecordActivity.this,10);
                     iv_cicletwo.setLayoutParams(lltwo);
                     LinearLayout.LayoutParams llthree=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                     llthree.height=ScreenUtil.dp2px(ProdRecordActivity.this,50);
                     llthree.width=ScreenUtil.dp2px(ProdRecordActivity.this,50);
                     llthree.leftMargin=ScreenUtil.dp2px(ProdRecordActivity.this,10);
                     circleone.setLayoutParams(llthree);
                 }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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
            child.setPadding(0, 0, 20, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }



    protected void getTitleList(List<String> mTitleLists) {
            mTitleLists.add("全部");
//            mTitleLists.add("责任险");
//            mTitleLists.add("我自己");
    }
    protected void getFragmentLists(List<BaseFragment> mFragmentLists) {

        for (int i=0;i<mTitleLists.size();i++){
            ProdFragmen   atf=  new ProdFragmen(i);
            mFragmentLists.add(atf);
        }
    }


    @OnClick(R.id.tv_right_title_bar)
    public void onClick(View view) {
        int viewid = view.getId();
        if(viewid==R.id.tv_right_title_bar){
        startActivity(new Intent(ProdRecordActivity.this,HistoryProdActivity.class));
        }
    }
}
