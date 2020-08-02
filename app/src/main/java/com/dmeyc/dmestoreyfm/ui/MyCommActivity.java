package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.CustomLazyViewPagerAdapter;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.MyCommListBean;
import com.dmeyc.dmestoreyfm.bean.common.CommonBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.fragment.MyAttentCommFragment;
import com.dmeyc.dmestoreyfm.fragment.MyCommFragment;
import com.dmeyc.dmestoreyfm.fragment.MyJoinCommFragment;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.tamic.novate.Throwable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MyCommActivity extends BaseActivity {
    protected List<String> mTitleLists; //导航条文字List
    protected List<BaseFragment> mFragmentLists;    //导航fragment list


    private TabLayout tablayout;
    private ViewPager viewpager;
    private TextView tv_right_title_bar;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_mycomm;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        tv_right_title_bar=findViewById(R.id.tv_right_title_bar);
        tablayout=findViewById(R.id.tablayout);
        tablayout.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(tablayout,20,0);
            }
        });
        viewpager = (ViewPager) mRootView.findViewById(R.id.viewpager); //使用bind 会出现空指针
        mTitleLists = new ArrayList<>();
        mFragmentLists = new ArrayList<>();
        getData();
    }
MyCommListBean dataBean;
    private void getData() {

        RestClient.getYfmNovate(this).post(Constant.API.YFM_MYCOMMlist, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("status", "1")
                .build(), new DmeycBaseSubscriber<MyCommListBean>() {
            @Override
            public void onSuccess(final MyCommListBean bean) {
//                ToastUtil.show(bean.getMsg());
                dataBean=bean;
                getTitleList(mTitleLists);
                getFragmentLists(mFragmentLists);
                FragmentManager fm =getSupportFragmentManager();
                viewpager.setAdapter(new CustomLazyViewPagerAdapter(fm,mFragmentLists,mTitleLists));
                tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
                tablayout.setupWithViewPager(viewpager);
//                ((MyCommFragment) (mFragmentLists.get(0))).setData(0,bean.getData());
//                viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//                    @Override
//                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                        ((MyCommFragment) (mFragmentLists.get(position))).setData(position,bean.getData());
////                        ((MyCommFragment) (mFragmentLists.get(position))).notifiy(position);
//                    }
//
//                    @Override
//                    public void onPageSelected(int position) {
//
//                    }
//
//                    @Override
//                    public void onPageScrollStateChanged(int state) {
//
//                    }
//                });

            }
            @Override
            public void onError(Throwable e) {

            }
        });

    }

    protected void getTitleList(List<String> mTitleLists) {
        mTitleLists.add("我创建的群");
        mTitleLists.add("我关注的群");
        mTitleLists.add("我加入的群");
    }
    MyCommFragment mcf;
    protected void getFragmentLists(List<BaseFragment> mFragmentLists) {
//        for (int i=0;i<mTitleLists.size();i++){
//            mcf=;
//            mcf.add
//             mFragmentLists.add( new MyCommFragment(dataBean));
//             mFragmentLists.add(new MyAttentCommFragment(dataBean));
//             mFragmentLists.add(new MyJoinCommFragment(dataBean));
//        }
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
    @OnClick(R.id.tv_right_title_bar)
    public void onClick(View view){
        int ivewid=view.getId();
        if(R.id.tv_right_title_bar==ivewid){
          startActivity(new Intent(MyCommActivity.this,CommCheckActivity.class));
        }
    }
}
