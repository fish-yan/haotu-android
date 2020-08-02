package com.dmeyc.dmestoreyfm.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.CustomLazyViewPagerAdapter;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.bean.HotDataBean;
import com.dmeyc.dmestoreyfm.bean.ProjectTypeBean;
import com.dmeyc.dmestoreyfm.bean.YFMLoginBean;
import com.dmeyc.dmestoreyfm.bean.common.CommonBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.BannerClickActivity;
import com.dmeyc.dmestoreyfm.ui.BannerSummentActivity;
import com.dmeyc.dmestoreyfm.ui.BigActionActivity;
import com.dmeyc.dmestoreyfm.ui.CommInActivity;
import com.dmeyc.dmestoreyfm.ui.NewsChannelActivity;
import com.dmeyc.dmestoreyfm.ui.SportActivity;
import com.dmeyc.dmestoreyfm.ui.YFMLoginActivity;
import com.dmeyc.dmestoreyfm.ui.YFMSettingActivity;
import com.dmeyc.dmestoreyfm.utils.DataClass;
import com.dmeyc.dmestoreyfm.utils.DateUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ScreemUtil;
import com.dmeyc.dmestoreyfm.utils.ScreenUtil;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.dmeyc.dmestoreyfm.wedgit.CommonPopupWindow;
import com.dmeyc.dmestoreyfm.wedgit.GlideImageLoader;
import com.dmeyc.dmestoreyfm.wedgit.MyTimePickerDialog;
import com.dmeyc.dmestoreyfm.wedgit.PickerView;
import com.dmeyc.dmestoreyfm.wedgit.PickerViewType;
import com.dmeyc.dmestoreyfm.wedgit.PopupMenu;
import com.dmeyc.dmestoreyfm.wedgit.flowview.AutoFlowLayout;
import com.tamic.novate.Throwable;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jockie on 2017/11/3
 * Email:jockie911@gmail.com
 */

@SuppressLint("ValidFragment")
public class ActionFragment extends BaseFragment  {


    @Bind(R.id.tv_todaybllo)
    TextView tv_todaybllo;
    @Bind(R.id.tv_nextdaybell)
    TextView tv_nextdaybell;
    @Bind(R.id.tv_twonextdaybell)
    TextView tv_twonextdaybell;
    @Bind(R.id.tv_threenextdaybello)
    TextView tv_threenextdaybello;
    @Bind(R.id.tv_choice)
    TextView tv_choice;
    @Bind(R.id.banner)
    Banner banner;

    protected List<String> mTitleLists; //导航条文字List
    protected List<BaseFragment> mFragmentLists;    //导航fragment list
     private TextView tv_time,tv_type;


    private ViewPager actionview_pager;
    private TabLayout actiontabs;
//    private ImageView actionadd_channel_iv;
    private LinearLayout ll_winnd;
    private View vuew_line;

    int viewpagerpos=0;
    ArrayList<String> popdata=new ArrayList<>();
    ArrayList<String>banners=new ArrayList<>();
    TextView cityname;
    public ActionFragment(TextView cityname){
        this.cityname=cityname;
    }
    @Override
    protected int getLayoutRes() {
        return R.layout.item_rv_action;
    }
    @Override
    protected void initData() {
        startSingleLocation();
        getBanner(0);

        now = Calendar.getInstance();
        tv_today=(TextView)mRootView.findViewById(R.id.tv_today);
        tv_nextday=(TextView) mRootView.findViewById(R.id.tv_nextday);
        tv_twonextday=(TextView) mRootView.findViewById(R.id.tv_twonextday);
        tv_threenextday=(TextView) mRootView.findViewById(R.id.tv_threenextday);
        tv_today.setTextColor(getActivity().getResources().getColor(R.color.homedayselect));
        tv_todaybllo.setTextColor(getActivity().getResources().getColor(R.color.homedayselect));
        TextPaint tp = tv_todaybllo.getPaint();
        tp.setFakeBoldText(true);
        TextPaint tp_tv_today = tv_today.getPaint();
        tp_tv_today.setFakeBoldText(true);
//        System.out.print()+"3333333333");
        if(DateUtil.isLastDayOfMonth(new Date(System.currentTimeMillis()))){
            tv_today.setText(now.get(Calendar.MONTH)+1+"月"+(now.get(Calendar.DAY_OF_MONTH))+"日");
            tv_nextday.setText(now.get(Calendar.MONTH)+2+"月"+(1)+"日");
            tv_twonextday.setText(now.get(Calendar.MONTH)+2+"月"+(2)+"日");
            tv_threenextday.setText(now.get(Calendar.MONTH)+2+"月"+(3)+"日");
        }else {
            Calendar cal=Calendar.getInstance();
//            cal.add(Calendar.DATE,+1);
            cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 1);
            Date time=cal.getTime();
            if(DateUtil.isLastDayOfMonth(time)){
                tv_today.setText(now.get(Calendar.MONTH)+1+"月"+(now.get(Calendar.DAY_OF_MONTH))+"日");
                tv_nextday.setText(now.get(Calendar.MONTH)+1+"月"+(now.get(Calendar.DAY_OF_MONTH)+1)+"日");
                tv_twonextday.setText(now.get(Calendar.MONTH)+2+"月"+(1)+"日");
                tv_threenextday.setText(now.get(Calendar.MONTH)+2+"月"+(2)+"日");
            }else {
                Calendar cal2=Calendar.getInstance();
//                cal.add(Calendar.DATE,+2);
                cal2.set(Calendar.DATE, cal2.get(Calendar.DATE) + 2);
                Date time2=cal2.getTime();
                if(DateUtil.isLastDayOfMonth(time2)){
                    tv_today.setText(now.get(Calendar.MONTH)+1+"月"+(now.get(Calendar.DAY_OF_MONTH))+"日");
                    tv_nextday.setText(now.get(Calendar.MONTH)+1+"月"+(now.get(Calendar.DAY_OF_MONTH)+1)+"日");
                    tv_twonextday.setText(now.get(Calendar.MONTH)+1+"月"+(now.get(Calendar.DAY_OF_MONTH)+2)+"日");
                    tv_threenextday.setText(now.get(Calendar.MONTH)+2+"月"+(1)+"日");
                }else {
                    tv_today.setText(now.get(Calendar.MONTH)+1+"月"+(now.get(Calendar.DAY_OF_MONTH))+"日");
                    tv_nextday.setText(now.get(Calendar.MONTH)+1+"月"+(now.get(Calendar.DAY_OF_MONTH)+1)+"日");
                    tv_twonextday.setText(now.get(Calendar.MONTH)+1+"月"+(now.get(Calendar.DAY_OF_MONTH)+2)+"日");
                    tv_threenextday.setText(now.get(Calendar.MONTH)+1+"月"+(now.get(Calendar.DAY_OF_MONTH)+3)+"日");
                }
            }

        }
        SPUtils.setValue(Constant.Config.PROJECTID,"");
        SPUtils.setValue(Constant.Config.CHECKDAY,"");
        SPUtils.setValue(Constant.Config.CHECKHOR,"");
        vuew_line=(View) mRootView.findViewById(R.id.vuew_line);
        ll_winnd=mRootView.findViewById(R.id.ll_winnd);
        tv_time=(TextView) mRootView.findViewById(R.id.tv_time);
//        tv_time.setOnClickListener(this);
        tv_type=(TextView) mRootView.findViewById(R.id.tv_type);
//        tv_type.setOnClickListener(this);
        actiontabs = (TabLayout) mRootView.findViewById(R.id.actiontabs); //使用bind 会出现空指针
        actiontabs.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(actiontabs,0,0);
            }
        });
        actionview_pager = (ViewPager) mRootView.findViewById(R.id.actionview_pager); //使用bind 会出现空指针
//        actionadd_channel_iv = (ImageView) mRootView.findViewById(R.id.actionadd_channel_iv); //使用bind 会出现空指针
//
//        actionadd_channel_iv.setOnClickListener(this);
        mTitleLists = new ArrayList<>();
        mFragmentLists = new ArrayList<>();
        getTitleList(mTitleLists);
        getFragmentLists(mFragmentLists);
//        actionData();
        actiontabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                View view = tab.getCustomView();
                if (null == view) {
                    tab.setCustomView(R.layout.custom_tab_layout_text);
                }
                updateTabTextView(tab, true);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                if (null == view) {
                    tab.setCustomView(R.layout.custom_tab_layout_text);
                }
                updateTabTextView(tab, false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        FragmentManager fm =getActivity().getSupportFragmentManager();
        actionview_pager.setAdapter(new CustomLazyViewPagerAdapter(fm,mFragmentLists,mTitleLists));
        actiontabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        actiontabs.setupWithViewPager(actionview_pager);

        actionview_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                viewpagerpos=position;
                SPUtils.setValue(Constant.Config.PROJECTID,"");
                SPUtils.setValue(Constant.Config.CHECKDAY,"");
                SPUtils.setValue(Constant.Config.CHECKHOR,"");
                getBanner(position);
                if(0==position||1==position){
                    tv_today.setTextColor(getActivity().getResources().getColor(R.color.homedayselect));
                    tv_todaybllo.setTextColor(getActivity().getResources().getColor(R.color.homedayselect));
                    tv_nextdaybell.setTextColor(getActivity().getResources().getColor(R.color.gray_deep));
                    tv_twonextdaybell.setTextColor(getActivity().getResources().getColor(R.color.gray_deep));
                    tv_threenextdaybello.setTextColor(getActivity().getResources().getColor(R.color.gray_deep));
                    tv_nextday.setTextColor(getActivity().getResources().getColor(R.color.gray_deep));
                    tv_twonextday.setTextColor(getActivity().getResources().getColor(R.color.gray_deep));
                    tv_threenextday.setTextColor(getActivity().getResources().getColor(R.color.gray_deep));

                    TextPaint tp = tv_todaybllo.getPaint();
                    tp.setFakeBoldText(true);
                    TextPaint tp_tv_today = tv_today.getPaint();
                    tp_tv_today.setFakeBoldText(true);
                    TextPaint tp_tv_nextday = tv_nextday.getPaint();
                    tp_tv_nextday.setFakeBoldText(false);
                    TextPaint tp_tv_nextdaybell= tv_nextdaybell.getPaint();
                    tp_tv_nextdaybell.setFakeBoldText(false);
                    TextPaint tp_tv_twonextday = tv_twonextday.getPaint();
                    tp_tv_twonextday.setFakeBoldText(false);
                    TextPaint tp_tv_twonextdaybell= tv_twonextdaybell.getPaint();
                    tp_tv_twonextdaybell.setFakeBoldText(false);
                    TextPaint tp_tv_threenextday = tv_threenextday.getPaint();
                    tp_tv_threenextday.setFakeBoldText(false);
                    TextPaint tp_tv_threenextdaybello = tv_threenextdaybello.getPaint();
                    tp_tv_threenextdaybello.setFakeBoldText(false);

                    tv_today.setVisibility(View.VISIBLE);
                    tv_nextday.setVisibility(View.VISIBLE);
                    tv_twonextday.setVisibility(View.VISIBLE);
                    tv_threenextday.setVisibility(View.VISIBLE);
                    tv_todaybllo.setText("今天");
                    tv_nextdaybell.setText("明天");
                    tv_twonextdaybell.setText("后天");
                    tv_threenextdaybello.setText("两天后");

                    if(DateUtil.isLastDayOfMonth(new Date(System.currentTimeMillis()))){
                        tv_today.setText(now.get(Calendar.MONTH)+1+"月"+(now.get(Calendar.DAY_OF_MONTH))+"日");
                        tv_nextday.setText(now.get(Calendar.MONTH)+2+"月"+(1)+"日");
                        tv_twonextday.setText(now.get(Calendar.MONTH)+2+"月"+(2)+"日");
                        tv_threenextday.setText(now.get(Calendar.MONTH)+2+"月"+(3)+"日");
                    }else {
                        Calendar cal=Calendar.getInstance();
                        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 1);
                        Date time=cal.getTime();
                        if(DateUtil.isLastDayOfMonth(time)){
                            tv_today.setText(now.get(Calendar.MONTH)+1+"月"+(now.get(Calendar.DAY_OF_MONTH))+"日");
                            tv_nextday.setText(now.get(Calendar.MONTH)+1+"月"+(now.get(Calendar.DAY_OF_MONTH)+1)+"日");
                            tv_twonextday.setText(now.get(Calendar.MONTH)+2+"月"+(1)+"日");
                            tv_threenextday.setText(now.get(Calendar.MONTH)+2+"月"+(2)+"日");
                        }else {
                            Calendar cal2=Calendar.getInstance();
//                cal.add(Calendar.DATE,+2);
                            cal2.set(Calendar.DATE, cal2.get(Calendar.DATE) + 2);
                            Date time2=cal2.getTime();
                            if(DateUtil.isLastDayOfMonth(time2)){
                                tv_today.setText(now.get(Calendar.MONTH)+1+"月"+(now.get(Calendar.DAY_OF_MONTH))+"日");
                                tv_nextday.setText(now.get(Calendar.MONTH)+1+"月"+(now.get(Calendar.DAY_OF_MONTH)+1)+"日");
                                tv_twonextday.setText(now.get(Calendar.MONTH)+1+"月"+(now.get(Calendar.DAY_OF_MONTH)+2)+"日");
                                tv_threenextday.setText(now.get(Calendar.MONTH)+2+"月"+(1)+"日");
                            }else {
                                tv_today.setText(now.get(Calendar.MONTH)+1+"月"+(now.get(Calendar.DAY_OF_MONTH))+"日");
                                tv_nextday.setText(now.get(Calendar.MONTH)+1+"月"+(now.get(Calendar.DAY_OF_MONTH)+1)+"日");
                                tv_twonextday.setText(now.get(Calendar.MONTH)+1+"月"+(now.get(Calendar.DAY_OF_MONTH)+2)+"日");
                                tv_threenextday.setText(now.get(Calendar.MONTH)+1+"月"+(now.get(Calendar.DAY_OF_MONTH)+3)+"日");
                            }
                        }
                    }
                }else {

                    tv_today.setVisibility(View.GONE);
                    tv_nextday.setVisibility(View.GONE);
                    tv_twonextday.setVisibility(View.GONE);
                    tv_threenextday.setVisibility(View.GONE);
                    tv_todaybllo.setText("本周");
                    tv_nextdaybell.setText("第2周");
                    tv_twonextdaybell.setText("第3周");
                    tv_threenextdaybello.setText("第4周");
//                    tv_todaybllo.setText(now.get(Calendar.MONTH)+1+"月");
//                    tv_nextdaybell.setText(now.get(Calendar.MONTH)+2+"月");
//                    tv_twonextdaybell.setText(now.get(Calendar.MONTH)+3+"月");
//                    tv_threenextdaybello.setText(now.get(Calendar.MONTH)+4+"月");
                }
                ActionItemFragemnt al=   (ActionItemFragemnt) mFragmentLists.get(actiontabs.getSelectedTabPosition());
                al.notifyData(viewpagerpos,1);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    protected void getTitleList(List<String> mTitleLists) {
        mTitleLists.add("羽毛球");
//        mTitleLists.add("校园");
        mTitleLists.add("运动教社");
        mTitleLists.add("合作商户");
//        mTitleLists.add("其他");
    }
    ActionItemFragemnt atf;
    protected void getFragmentLists(List<BaseFragment> mFragmentLists) {
for (int i=0;i<mTitleLists.size();i++){
    atf=  new ActionItemFragemnt(i);
    mFragmentLists.add(atf);
}
    }

    @Override
    protected void initData(View view) {

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

@OnClick({R.id.ll_threenextday,R.id.tv_type,R.id.tv_time,R.id.ll_tody,R.id.ll_nextday,R.id.ll_twonextday,R.id.tv_choice})
    public void onClick(View v) {
        int viewid=v.getId();
        if(viewid==R.id.tv_type){
        }else if(viewid==R.id.tv_time){
        }else if(R.id.ll_threenextday==viewid){
            chectday=3;
            SPUtils.setValue(Constant.Config.PROJECTID,"");

            if(viewpagerpos==0||viewpagerpos==1){//||viewpagerpos==1||viewpagerpos==2
                Calendar cal2=Calendar.getInstance();
                cal2.set(Calendar.DATE, cal2.get(Calendar.DATE) + 2);
                Date time2=cal2.getTime();
                if(DateUtil.isLastDayOfMonth(time2)){
                    SPUtils.setValue(Constant.Config.CHECKDAY,now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+2)+"-"+"0"+(1));
                }else {
                    if(((now.get(Calendar.DAY_OF_MONTH)+3)+"").length()==1){
                        SPUtils.setValue(Constant.Config.CHECKDAY,now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1)+"-"+"0"+(now.get(Calendar.DAY_OF_MONTH)+3));

                    }else {
                        SPUtils.setValue(Constant.Config.CHECKDAY,now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1)+"-"+(now.get(Calendar.DAY_OF_MONTH)+3));

                    }
                }
            }else {
                SPUtils.setValue(Constant.Config.CHECKHOR,"4"+"_"+"0");
//                        SPUtils.setValue(Constant.Config.CHECKDAY,now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+4)+"-"+"01");

            }

            tv_today.setTextColor(getActivity().getResources().getColor(R.color.gray_deep));
            tv_nextday.setTextColor(getActivity().getResources().getColor(R.color.gray_deep));
            tv_twonextday.setTextColor(getActivity().getResources().getColor(R.color.gray_deep));
            tv_threenextday.setTextColor(getActivity().getResources().getColor(R.color.homedayselect));


             tv_todaybllo.setTextColor(getActivity().getResources().getColor(R.color.gray_deep));
             tv_nextdaybell.setTextColor(getActivity().getResources().getColor(R.color.gray_deep));
         tv_twonextdaybell.setTextColor(getActivity().getResources().getColor(R.color.gray_deep));
         tv_threenextdaybello.setTextColor(getActivity().getResources().getColor(R.color.homedayselect));

            TextPaint tp = tv_todaybllo.getPaint();
            tp.setFakeBoldText(false);
            TextPaint tp_tv_today = tv_today.getPaint();
            tp_tv_today.setFakeBoldText(false);
            TextPaint tp_tv_nextday = tv_nextday.getPaint();
            tp_tv_nextday.setFakeBoldText(false);
            TextPaint tp_tv_nextdaybell= tv_nextdaybell.getPaint();
            tp_tv_nextdaybell.setFakeBoldText(false);
            TextPaint tp_tv_twonextday = tv_twonextday.getPaint();
            tp_tv_twonextday.setFakeBoldText(false);
            TextPaint tp_tv_twonextdaybell= tv_twonextdaybell.getPaint();
            tp_tv_twonextdaybell.setFakeBoldText(false);
            TextPaint tp_tv_threenextday = tv_threenextday.getPaint();
            tp_tv_threenextday.setFakeBoldText(true);
            TextPaint tp_tv_threenextdaybello = tv_threenextdaybello.getPaint();
            tp_tv_threenextdaybello.setFakeBoldText(true);
            ActionItemFragemnt al=   (ActionItemFragemnt) mFragmentLists.get(actiontabs.getSelectedTabPosition());
//            al.notifyData(pktabs.getSelectedTabPosition(),1);
            al.notifyData(1,1);
        }else if(R.id.ll_tody==viewid){
            chectday=0;
            SPUtils.setValue(Constant.Config.PROJECTID,"");
            if(viewpagerpos==0||viewpagerpos==1){//||viewpagerpos==1||viewpagerpos==2
                SPUtils.setValue(Constant.Config.CHECKDAY,now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1)+"-"+"0"+(now.get(Calendar.DAY_OF_MONTH)));
            }else {
//                SPUtils.setValue(Constant.Config.CHECKDAY,now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1)+"-"+"01");
                SPUtils.setValue(Constant.Config.CHECKHOR,"1"+"_"+"0");
            }

            tv_today.setTextColor(getActivity().getResources().getColor(R.color.homedayselect));
            tv_nextday.setTextColor(getActivity().getResources().getColor(R.color.gray_deep));
            tv_twonextday.setTextColor(getActivity().getResources().getColor(R.color.gray_deep));
            tv_threenextday.setTextColor(getActivity().getResources().getColor(R.color.gray_deep));
            TextPaint tp = tv_todaybllo.getPaint();
            tp.setFakeBoldText(true);
            TextPaint tp_tv_today = tv_today.getPaint();
            tp_tv_today.setFakeBoldText(true);
            TextPaint tp_tv_nextday = tv_nextday.getPaint();
            tp_tv_nextday.setFakeBoldText(false);
            TextPaint tp_tv_nextdaybell= tv_nextdaybell.getPaint();
            tp_tv_nextdaybell.setFakeBoldText(false);
            TextPaint tp_tv_twonextday = tv_twonextday.getPaint();
            tp_tv_twonextday.setFakeBoldText(false);
            TextPaint tp_tv_twonextdaybell= tv_twonextdaybell.getPaint();
            tp_tv_twonextdaybell.setFakeBoldText(false);
            TextPaint tp_tv_threenextday = tv_threenextday.getPaint();
            tp_tv_threenextday.setFakeBoldText(false);
            TextPaint tp_tv_threenextdaybello = tv_threenextdaybello.getPaint();
            tp_tv_threenextdaybello.setFakeBoldText(false);

            ActionItemFragemnt al=   (ActionItemFragemnt) mFragmentLists.get(actiontabs.getSelectedTabPosition());
//            al.notifyData(pktabs.getSelectedTabPosition(),1);
            al.notifyData(1,1);

            tv_todaybllo.setTextColor(getActivity().getResources().getColor(R.color.homedayselect));
            tv_nextdaybell.setTextColor(getActivity().getResources().getColor(R.color.gray_deep));
            tv_twonextdaybell.setTextColor(getActivity().getResources().getColor(R.color.gray_deep));
            tv_threenextdaybello.setTextColor(getActivity().getResources().getColor(R.color.gray_deep));
        }else if(R.id.ll_nextday==viewid){
            chectday=1;
            SPUtils.setValue(Constant.Config.PROJECTID,"");
            if(viewpagerpos==0||viewpagerpos==1){//||viewpagerpos==1||viewpagerpos==2
                if(DateUtil.isLastDayOfMonth(new Date(System.currentTimeMillis()))){
                    SPUtils.setValue(Constant.Config.CHECKDAY,now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+2)+"-"+"0"+(1));
                }else {
                    if(((now.get(Calendar.DAY_OF_MONTH)+1)+"").length()==1){
                        SPUtils.setValue(Constant.Config.CHECKDAY,now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1)+"-"+"0"+(now.get(Calendar.DAY_OF_MONTH)+1));
                    }else {
                        SPUtils.setValue(Constant.Config.CHECKDAY,now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1)+"-"+(now.get(Calendar.DAY_OF_MONTH)+1));
                    }

                }
            }else {
                SPUtils.setValue(Constant.Config.CHECKHOR,"2"+"_"+"0");
//                SPUtils.setValue(Constant.Config.CHECKDAY,now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+2)+"-"+"01");
            }



//            SPUtils.setValue(Constant.Config.CHECKDAY,now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1)+"-"+(now.get(Calendar.DAY_OF_MONTH)+1));
            tv_today.setTextColor(getActivity().getResources().getColor(R.color.gray_deep));
            tv_nextday.setTextColor(getActivity().getResources().getColor(R.color.homedayselect));
            tv_twonextday.setTextColor(getActivity().getResources().getColor(R.color.gray_deep));
            tv_threenextday.setTextColor(getActivity().getResources().getColor(R.color.gray_deep));

            tv_todaybllo.setTextColor(getActivity().getResources().getColor(R.color.gray_deep));
            tv_nextdaybell.setTextColor(getActivity().getResources().getColor(R.color.homedayselect));
            tv_twonextdaybell.setTextColor(getActivity().getResources().getColor(R.color.gray_deep));
            tv_threenextdaybello.setTextColor(getActivity().getResources().getColor(R.color.gray_deep));

            TextPaint tp = tv_todaybllo.getPaint();
            tp.setFakeBoldText(false);
            TextPaint tp_tv_today = tv_today.getPaint();
            tp_tv_today.setFakeBoldText(false);
            TextPaint tp_tv_nextday = tv_nextday.getPaint();
            tp_tv_nextday.setFakeBoldText(true);
            TextPaint tp_tv_nextdaybell= tv_nextdaybell.getPaint();
            tp_tv_nextdaybell.setFakeBoldText(true);
            TextPaint tp_tv_twonextday = tv_twonextday.getPaint();
            tp_tv_twonextday.setFakeBoldText(false);
            TextPaint tp_tv_twonextdaybell= tv_twonextdaybell.getPaint();
            tp_tv_twonextdaybell.setFakeBoldText(false);
            TextPaint tp_tv_threenextday = tv_threenextday.getPaint();
            tp_tv_threenextday.setFakeBoldText(false);
            TextPaint tp_tv_threenextdaybello = tv_threenextdaybello.getPaint();
            tp_tv_threenextdaybello.setFakeBoldText(false);


            ActionItemFragemnt al=   (ActionItemFragemnt) mFragmentLists.get(actiontabs.getSelectedTabPosition());
//            al.notifyData(pktabs.getSelectedTabPosition(),1);
            al.notifyData(1,1);
        }else if(R.id.ll_twonextday==viewid){
            chectday=2;
            SPUtils.setValue(Constant.Config.PROJECTID,"");

            if(viewpagerpos==0||viewpagerpos==1){//||viewpagerpos==1||viewpagerpos==2

                Calendar cal=Calendar.getInstance();
//            cal.add(Calendar.DATE,+1);
                cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 1);
                Date time=cal.getTime();
                if(DateUtil.isLastDayOfMonth(time)){
                    SPUtils.setValue(Constant.Config.CHECKDAY,now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+2)+"-"+"0"+(1));
                }else {
                    if(((now.get(Calendar.DAY_OF_MONTH)+2)+"").length()==1){
                        SPUtils.setValue(Constant.Config.CHECKDAY,now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1)+"-"+"0"+(now.get(Calendar.DAY_OF_MONTH)+2));

                    }else {
                        SPUtils.setValue(Constant.Config.CHECKDAY,now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1)+"-"+(now.get(Calendar.DAY_OF_MONTH)+2));

                    }
                }
            }else {
                SPUtils.setValue(Constant.Config.CHECKHOR,"3"+"_"+"0");
//                SPUtils.setValue(Constant.Config.CHECKDAY,now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+2)+"-"+"01");
            }

//            SPUtils.setValue(Constant.Config.CHECKDAY,now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1)+"-"+(now.get(Calendar.DAY_OF_MONTH)+2));
            tv_today.setTextColor(getActivity().getResources().getColor(R.color.gray_deep));
            tv_nextday.setTextColor(getActivity().getResources().getColor(R.color.gray_deep));
            tv_twonextday.setTextColor(getActivity().getResources().getColor(R.color.homedayselect));
            tv_threenextday.setTextColor(getActivity().getResources().getColor(R.color.gray_deep));

            tv_todaybllo.setTextColor(getActivity().getResources().getColor(R.color.gray_deep));
            tv_nextdaybell.setTextColor(getActivity().getResources().getColor(R.color.gray_deep));
            tv_twonextdaybell.setTextColor(getActivity().getResources().getColor(R.color.homedayselect));
            tv_threenextdaybello.setTextColor(getActivity().getResources().getColor(R.color.gray_deep));

            TextPaint tp = tv_todaybllo.getPaint();
            tp.setFakeBoldText(false);
            TextPaint tp_tv_today = tv_today.getPaint();
            tp_tv_today.setFakeBoldText(false);
            TextPaint tp_tv_nextday = tv_nextday.getPaint();
            tp_tv_nextday.setFakeBoldText(false);
            TextPaint tp_tv_nextdaybell= tv_nextdaybell.getPaint();
            tp_tv_nextdaybell.setFakeBoldText(false);
            TextPaint tp_tv_twonextday = tv_twonextday.getPaint();
            tp_tv_twonextday.setFakeBoldText(true);
            TextPaint tp_tv_twonextdaybell= tv_twonextdaybell.getPaint();
            tp_tv_twonextdaybell.setFakeBoldText(true);
            TextPaint tp_tv_threenextday = tv_threenextday.getPaint();
            tp_tv_threenextday.setFakeBoldText(false);
            TextPaint tp_tv_threenextdaybello = tv_threenextdaybello.getPaint();
            tp_tv_threenextdaybello.setFakeBoldText(false);

            ActionItemFragemnt al=   (ActionItemFragemnt) mFragmentLists.get(actiontabs.getSelectedTabPosition());
//            al.notifyData(pktabs.getSelectedTabPosition(),1);
            al.notifyData(1,1);
        }else if(R.id.tv_choice==viewid){
             toChoice();
          }
    }
    private CommonPopupWindow window;
    private ImageView btn_login;
    private String chexhorandtype="00:00";
//    private String chexendhorandtype="00:00";

    PickerViewType minute_pvchoice;
    PickerView second_pvchoice;
    GridView lv_type;
    private TextView tv_diatime,tv_diaproject;
    private LinearLayout ll_statime;
    private String itemid;
    TimaAndTypeAdapter timaAndTypeAdapter;
    private void initPopupWindow(final String type) {
        DisplayMetrics metrics=new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        window=new CommonPopupWindow(getActivity(), R.layout.pop_typechoice, ViewGroup.LayoutParams.MATCH_PARENT,ScreenUtil.dp2px(getActivity(),300)) {
            @Override
            protected void initView() {
                View view=getContentView();
                btn_login=(ImageView)view.findViewById(R.id.btn_login);
                minute_pvchoice = (PickerViewType) view.findViewById(R.id.minute_pv);
                second_pvchoice = (PickerView)view. findViewById(R.id.second_pv);
                lv_type=(GridView)view.findViewById(R.id.lv_type);
                tv_diatime=(TextView) view.findViewById(R.id.tv_diatime);
                tv_diaproject=(TextView) view.findViewById(R.id.tv_diaproject);
                ll_statime=(LinearLayout) view.findViewById(R.id.ll_statime);
//                ll_endtime=(LinearLayout) view.findViewById(R.id.ll_endtime);


                lv_type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        pos=i;
                        timaAndTypeAdapter.notifyDataSetChanged();
                        itemid=pty.getData().get(i).getItemCode();
                    }
                });
                timaAndTypeAdapter =new TimaAndTypeAdapter();
                lv_type.setAdapter(timaAndTypeAdapter);

//                lv_type.setAdapter(new BaseAdapter() {
//                    @Override
//                    public int getCount() {
//                        return 10;
//                    }
//
//                    @Override
//                    public Object getItem(int i) {
//                        return null;
//                    }
//
//                    @Override
//                    public long getItemId(int i) {
//                        return 0;
//                    }
//                    ProjectViewHoder viewHoder;
//                    @Override
//                    public View getView(int i, View view, ViewGroup viewGroup) {
//                        if(view==null){
//                            view=getLayoutInflater().inflate(R.layout.adapter_projecttype,null);
//                            viewHoder=new ProjectViewHoder(view);
//                            view.setTag(viewHoder);
//                        }else {
//                            viewHoder=(ProjectViewHoder)  view.getTag();
//                        }
//                        return view;
//                    }
//                });
                List<String> data = new ArrayList<String>();
//                List<String> seconds = new ArrayList<String>();
                for (int i = 0; i < 24; i++)
                {
                    data.add(i < 10 ? "0" + i+":00" : "" + i+":00");
                }

//                for (int i = 0; i < 24; i++)
//                {
//                    seconds.add(i < 10 ? "0" + i+":00" : "" + i+":00");
//                }
                minute_pvchoice.setData(data);
                minute_pvchoice.setOnSelectListener(new PickerView.onSelectListener() {

                    @Override
                    public void onSelect(String text)
                    {
                        chexhorandtype=text;
                    }
                });
//                second_pvchoice.setData(seconds);
//                second_pvchoice.setOnSelectListener(new PickerView.onSelectListener() {
//                    @Override
//                    public void onSelect(String text)
//                    {
//                        chexendhorandtype=text;
//                    }
//                });
            }
            @Override
            protected void initEvent() {
                btn_login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(window!=null&&window.getPopupWindow().isShowing()){

                            String star= chexhorandtype.split(":")[0];
//                            String end= chexendhorandtype.split(":")[0];
//                            if(Integer.parseInt(star)>Integer.parseInt(end)){
//                                ToastUtil.show("结束时间大于开始时间");
//                                return;
//                            }
                            SPUtils.setValue(Constant.Config.CHECKHOR,chexhorandtype+":00");
                            SPUtils.setValue(Constant.Config.PROJECTID,itemid);
//                            SPUtils.setValue(Constant.Config.CHECKENDHOR,chexendhorandtype+":00");
                            ActionItemFragemnt al=   (ActionItemFragemnt) mFragmentLists.get(actiontabs.getSelectedTabPosition());
                            al.notifyData(1,1);


                            window.getPopupWindow().dismiss();
                        }
                    }
                });
                if(type.equals("other")){
                    tv_diatime.setVisibility(View.GONE);
                    tv_diaproject.setTextColor(getActivity().getResources().getColor(R.color.homedayselect));
                    ll_statime.setVisibility(View.GONE);

                }else {
                    tv_diatime.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            tv_diatime.setTextColor(getActivity().getResources().getColor(R.color.homedayselect));
                            tv_diaproject.setTextColor(getActivity().getResources().getColor(R.color.gray_deep));
                            lv_type.setVisibility(View.GONE);
//                        ll_endtime.setVisibility(View.VISIBLE);
                            ll_statime.setVisibility(View.VISIBLE);
                        }
                    });
                    tv_diaproject.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            tv_diaproject.setTextColor(getActivity().getResources().getColor(R.color.homedayselect));
                            tv_diatime.setTextColor(getActivity().getResources().getColor(R.color.gray_deep));
                            lv_type.setVisibility(View.VISIBLE);
//                        ll_endtime.setVisibility(View.GONE);
                            ll_statime.setVisibility(View.GONE);
                        }
                    });
                }
            }
            @Override
            protected void initWindow() {
                super.initWindow();
                PopupWindow instance=getPopupWindow();
                instance.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp=getActivity().getWindow().getAttributes();
                        lp.alpha=1f;
                        SPUtils.setValue(Constant.Config.CHECKHOR,"");
                        SPUtils.setValue(Constant.Config.CHECKENDHOR,"");
                        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        getActivity().getWindow().setAttributes(lp);
                    }
                });
            }
        };
    }

    PickerView minute_pv;
//    PickerView second_pv;
    TextView btn_sub,tv_dissmiss,tv_placechoice;
    Calendar now;
    TextView tv_today,tv_nextday,tv_twonextday,tv_threenextday;
    int  chectday=0;
    String chexhor="00:00";
//    String chexsecond="00:00";
    private void initTimePopupWindow() {
        // get the height of screen
        DisplayMetrics metrics=new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        window=new CommonPopupWindow(getActivity(), R.layout.pop_time,ViewGroup.LayoutParams.MATCH_PARENT, 500) {
        window=new CommonPopupWindow(getActivity(), R.layout.pop_choice,ScreenUtil.dp2px(getActivity(),300), ScreenUtil.dp2px(getActivity(),250)) {
            @Override
            protected void initView() {
                View view=getContentView();
//                now  = Calendar.getInstance();
//                tv_today=(TextView) view.findViewById(R.id.tv_today);
//                tv_nextday=(TextView) view.findViewById(R.id.tv_nextday);
//                tv_twonextday=(TextView) view.findViewById(R.id.tv_twonextday);
//                tv_threenextday=(TextView) view.findViewById(R.id.tv_threenextday);
//                tv_today.setText(now.get(Calendar.MONTH)+1+"月"+(now.get(Calendar.DAY_OF_MONTH))+"日");
//                tv_nextday.setText(now.get(Calendar.MONTH)+1+"月"+(now.get(Calendar.DAY_OF_MONTH)+1)+"日");
//                tv_twonextday.setText(now.get(Calendar.MONTH)+1+"月"+(now.get(Calendar.DAY_OF_MONTH)+2)+"日");
//                tv_threenextday.setText(now.get(Calendar.MONTH)+1+"月"+(now.get(Calendar.DAY_OF_MONTH)+3)+"日");
                tv_placechoice=view.findViewById(R.id.tv_placechoice);
                tv_placechoice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tv_placechoice.setVisibility(View.GONE);
                        minute_pv.setVisibility(View.VISIBLE);
                    }
                });
                btn_sub=(TextView) view.findViewById(R.id.btn_sub);
                tv_dissmiss=view.findViewById(R.id.tv_dissmiss);
                minute_pv = (PickerView) view.findViewById(R.id.minute_pv);
//                second_pv = (PickerView)view. findViewById(R.id.second_pv);
                List<String> data = new ArrayList<String>();
//                List<String> seconds = new ArrayList<String>();
                for (int i = 0; i < 24; i++)
                {
                    data.add(i < 10 ? "0" + i+":00" : "" + i+":00");
                }

//                for (int i = 0; i < 24; i++)
//                {
//                    seconds.add(i < 10 ? "0" + i+":00" : "" + i+":00");
////                    seconds.add(i < 1 ? "0" + 0 : "" + 30);
//                }
                minute_pv.setData(data);
                minute_pv.setOnSelectListener(new PickerView.onSelectListener()
                {

                    @Override
                    public void onSelect(String text)
                    {
                        chexhor=text;
//                        Toast.makeText(getActivity(), "选择了 " + text + " 时",
//                                Toast.LENGTH_SHORT).show();
                    }
                });
//                second_pv.setData(seconds);
//                second_pv.setOnSelectListener(new PickerView.onSelectListener() {
//                    @Override
//                    public void onSelect(String text)
//                    {
//                        chexsecond=text;
////                        Toast.makeText(getActivity(), "选择了 " + text + " 分",
////                                Toast.LENGTH_SHORT).show();
//                    }
//                        });
            }

            @Override
            protected void initWindow() {
                super.initWindow();
                PopupWindow instance=getPopupWindow();
                instance.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp=getActivity().getWindow().getAttributes();
                        lp.alpha=1f;

                        SPUtils.setValue(Constant.Config.CHECKHOR,"");
                        SPUtils.setValue(Constant.Config.CHECKENDHOR,"");
                        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        getActivity().getWindow().setAttributes(lp);
                    }
                });
            }
            @Override
            protected void initEvent() {
                btn_sub.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(window!=null&&window.getPopupWindow().isShowing()){
                           String star= chexhor.split(":")[0];
//                            String end= chexsecond.split(":")[0];
//                            if(Integer.parseInt(star)>Integer.parseInt(end)){
//                                ToastUtil.show("结束时间大于开始时间");
//                                     return;
//                            }
                            SPUtils.setValue(Constant.Config.CHECKHOR,chexhor+":00");
//                            SPUtils.setValue(Constant.Config.CHECKENDHOR,chexsecond+":00");
                            ActionItemFragemnt al=   (ActionItemFragemnt) mFragmentLists.get(actiontabs.getSelectedTabPosition());
                            al.notifyData(1,1);
//                            if(0==chectday){
//                                SPUtils.setValue(Constant.Config.CHECKDAY,now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1)+"-"+now.get(Calendar.DAY_OF_MONTH));
//                                SPUtils.setValue(Constant.Config.CHECKHOR,chexhor);
//                                SPUtils.setValue(Constant.Config.CHECKDAY,now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1)+"-"+now.get(Calendar.DAY_OF_MONTH));
//                                SPUtils.setValue(Constant.Config.CHECKHOR,chexhor+":"+chexsecond+":00");
//                            }else {
//                                SPUtils.setValue(Constant.Config.CHECKDAY,now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1)+"-"+(now.get(Calendar.DAY_OF_MONTH)+chectday));
//                                SPUtils.setValue(Constant.Config.CHECKHOR,chexhor);
//                                SPUtils.setValue(Constant.Config.CHECKDAY,now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1)+"-"+(now.get(Calendar.DAY_OF_MONTH)+chectday));
//                                SPUtils.setValue(Constant.Config.CHECKHOR,chexhor+":"+chexsecond+":00");
//                            }
                            window.getPopupWindow().dismiss();
                        }
                    }
                });
                tv_dissmiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        window.getPopupWindow().dismiss();
                    }
                });
//                tv_today.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        chectday=0;
//                        tv_today.setTextColor(getActivity().getResources().getColor(R.color.home_lab));
//                        tv_nextday.setTextColor(getActivity().getResources().getColor(R.color.black));
//                        tv_twonextday.setTextColor(getActivity().getResources().getColor(R.color.black));
//                        tv_threenextday.setTextColor(getActivity().getResources().getColor(R.color.black));
//                    }
//                });
//                tv_nextday.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        chectday=1;
//                        tv_today.setTextColor(getActivity().getResources().getColor(R.color.black));
//                        tv_nextday.setTextColor(getActivity().getResources().getColor(R.color.home_lab));
//                        tv_twonextday.setTextColor(getActivity().getResources().getColor(R.color.black));
//                        tv_threenextday.setTextColor(getActivity().getResources().getColor(R.color.black));
//                    }
//                });
//                tv_twonextday.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        chectday=2;
//                        tv_today.setTextColor(getActivity().getResources().getColor(R.color.black));
//                        tv_nextday.setTextColor(getActivity().getResources().getColor(R.color.black));
//                        tv_twonextday.setTextColor(getActivity().getResources().getColor(R.color.home_lab));
//                        tv_threenextday.setTextColor(getActivity().getResources().getColor(R.color.black));
//                    }
//                });
//                tv_threenextday.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        chectday=3;
//                        tv_today.setTextColor(getActivity().getResources().getColor(R.color.black));
//                        tv_nextday.setTextColor(getActivity().getResources().getColor(R.color.black));
//                        tv_twonextday.setTextColor(getActivity().getResources().getColor(R.color.black));
//                        tv_threenextday.setTextColor(getActivity().getResources().getColor(R.color.home_lab));
//                    }
//                });
            }
    };

    }
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new MyAMapLocationListener();
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    /**
     * 启动单次客户端定位
     */
    void startSingleLocation(){

        mLocationClient = new AMapLocationClient(getContext());
        //设置定位回调监听

        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(false);
        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为true，允许模拟位置
        mLocationOption.setMockEnable(false);
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //启动定位
        mLocationClient.setLocationListener(mLocationListener);
        mLocationClient.startLocation();


    }

    /**
     * 单次客户端的定位监听
     */
    AMapLocationListener locationSingleListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
          ToastUtil.show(location.getCity());

        }
    };



    class MyAMapLocationListener implements AMapLocationListener{
        @Override
        public void onLocationChanged(final AMapLocation amapLocation) {


            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //定位成功回调信息，设置相关消息

//                    ToastUtil.show("chengg");
                    System.out.print("111"+amapLocation.getCity()+"22"+amapLocation.getLongitude()+"444444444444"+amapLocation.getLongitude());
                    if(TextUtils.isEmpty(SPUtils.getStringData(Constant.Config.CURRENT_CITY))){
                        SPUtils.savaStringData(Constant.Config.CURRENT_CITY,amapLocation.getCity());
                        DataClass.LocationCity=amapLocation.getCity();
                        SPUtils.savaStringData(Constant.Config.LONGTUDE,amapLocation.getLongitude()+"");
                        cityname.setText(amapLocation.getCity());
                        SPUtils.savaStringData(Constant.Config.LATITUDE,amapLocation.getLatitude()+"");
                    }


//                    }
//                    amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
//                    amapLocation.getLatitude();//获取纬度
//                    amapLocation.getLongitude();//获取经度
//                    amapLocation.getAccuracy();//获取精度信息
//                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    Date date = new Date(amapLocation.getTime());
//                    df.format(date);//定位时间
//                    amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
//                    amapLocation.getCountry();//国家信息
//                    amapLocation.getProvince();//省信息
//                    amapLocation.getCity();//城市信息
//                    amapLocation.getDistrict();//城区信息
//                    amapLocation.getStreet();//街道信息
//                    amapLocation.getStreetNum();//街道门牌号信息
//                    amapLocation.getCityCode();//城市编码
//                    amapLocation.getAdCode();//地区编码
//                    amapLocation.getAOIName();//获取当前定位点的AOI信息
                } else {
//                    ToastUtil.show("shibai");
                    //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError","location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
if(TextUtils.isEmpty(SPUtils.getStringData(Constant.Config.CURRENT_CITY))){
    SPUtils.savaStringData(Constant.Config.CURRENT_CITY,"上海市");
    DataClass.LocationCity="上海市";
    cityname.setText("上海市");
    SPUtils.savaStringData(Constant.Config.LONGTUDE,121.520050000000+"");
    SPUtils.savaStringData(Constant.Config.LATITUDE,31.228833000000+"");
}


                }
            }

        }
    }

    @SuppressLint("NewApi")
    private void updateTabTextView(TabLayout.Tab tab, boolean isSelect) {

        if (isSelect) {
            //选中加粗
            TextView textView = tab.getCustomView().findViewById(android.R.id.text1);
//            TextView tabSelect = (TextView) tab.getCustomView().findViewById(R.id.tab_item_textview);
//            textView.setCompoundDrawables(null,null,getActivity().getDrawable(R.drawable.gengduo),null);
//            textView.setCompoundDrawablesWithIntrinsicBounds(null,null,getActivity().getDrawable(R.drawable.gengduogree),null);
            textView.setTextColor(getActivity().getResources().getColor(R.color.homedayselect));
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            textView.setText(tab.getText());
        } else {
            TextView tabUnSelect = tab.getCustomView().findViewById(android.R.id.text1);
            tabUnSelect.setTextColor(getActivity().getResources().getColor(R.color.homedayselect));
//            tabUnSelect.setCompoundDrawablesWithIntrinsicBounds(null,null,getActivity().getDrawable(R.drawable.gengduogree),null);
            tabUnSelect.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            tabUnSelect.setText(tab.getText());
        }
    }

    Dialog dialog;
    public void toChoice(){
        if(0==viewpagerpos){
            initTimePopupWindow();
            PopupWindow win=window.getPopupWindow();
            win.setAnimationStyle(R.style.animAlpha);
            window.showAtLocation(tv_twonextdaybell, Gravity.CENTER,0,ScreenUtil.dp2px(getActivity(),60));
//            window.showAsDropDown(tv_twonextdaybell,  0, 50);
            WindowManager.LayoutParams lp=getActivity().getWindow().getAttributes();
            lp.alpha=0.5f;
            win.setFocusable(true);
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            getActivity().getWindow().setAttributes(lp);
        }else if(1==viewpagerpos) {
            getProgectType();
            window=null;
        }else if(2==viewpagerpos){
            window=null;
            getoterproject();
        }
    }

class ProjectViewHoder{
        TextView tv_projecttype;
        public ProjectViewHoder(View view){
            tv_projecttype=view.findViewById(R.id.tv_projecttype);
        }
}

    ProjectTypeBean pty;
    ArrayList <String> ar=new ArrayList<>();
    private void getProgectType() {
        RestClient.getYfmNovate(getActivity()).post(Constant.API.YFM_PROJECTLIST, new ParamMap.Build()
                .addParams("key","PROJECT_TYPE_COACH")
                .addParams("user_token",SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<ProjectTypeBean>() {
            @Override
            public void onSuccess(final ProjectTypeBean bean) {
                pty=bean;

                initPopupWindow("nomoer");
                PopupWindow win=window.getPopupWindow();
                win.setAnimationStyle(R.style.animAlpha);

                window.showAsDropDown(tv_nextdaybell,  50, 50);
                WindowManager.LayoutParams lp=getActivity().getWindow().getAttributes();
                lp.alpha=0.5f;

                win.setFocusable(true);
                getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getActivity().getWindow().setAttributes(lp);
            }
            @Override
            public void onError(Throwable e) {

            }
        });
    }
    ProjectTypeBean ptys;
public void getoterproject(){

    RestClient.getYfmNovate(getActivity()).post(Constant.API.YFM_PROJECTLIST, new ParamMap.Build()
            .addParams("key","PROJECT_TYPE_COMMERCIAL")
            .addParams("user_token",SPUtils.getStringData(Constant.Config.TOKEN))
            .build(), new DmeycBaseSubscriber<ProjectTypeBean>() {
        @Override
        public void onSuccess(final ProjectTypeBean bean) {
            ptys=bean;
            initOterPopupWindow("other");
            PopupWindow win=window.getPopupWindow();
            win.setAnimationStyle(R.style.animAlpha);
            window.showAsDropDown(tv_nextdaybell,  50, 50);
            WindowManager.LayoutParams lp=getActivity().getWindow().getAttributes();
            lp.alpha=0.5f;
            win.setFocusable(true);
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            getActivity().getWindow().setAttributes(lp);


        }
        @Override
        public void onError(Throwable e) {

        }
    });

}

private GridView lv_other;
    OtherAdapter otherAdapter;
    int pos=-1;
    private void initOterPopupWindow(final String type) {
        DisplayMetrics metrics=new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        window=new CommonPopupWindow(getActivity(), R.layout.pop_other, ViewGroup.LayoutParams.MATCH_PARENT,ScreenUtil.dp2px(getActivity(),250)) {
            @Override
            protected void initView() {
                View view=getContentView();
                btn_login=(ImageView) view.findViewById(R.id.btn_login);
                minute_pvchoice = (PickerViewType) view.findViewById(R.id.minute_pv);
                second_pvchoice = (PickerView)view. findViewById(R.id.second_pv);
                lv_other=(GridView)view.findViewById(R.id.lv_other);
                otherAdapter  =new OtherAdapter();
                lv_other.setAdapter(otherAdapter);


                lv_other.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        pos=i;
                        otherAdapter.notifyDataSetChanged();
                        itemid=ptys.getData().get(i).getItemCode();
                    }
                });

                List<String> data = new ArrayList<String>();
//                List<String> seconds = new ArrayList<String>();
//                for (int i = 1; i < 7; i++)
//                {
                    data.add("星期一");
                data.add("星期二");
                data.add("星期三");
                data.add("星期四");
                data.add("星期五");
                data.add("星期六");
                data.add("星期日");
//                    data.add(i < 10 ? "0" + i+":00" : "" + i+":00");
//                }


                minute_pvchoice.setData(data);
                minute_pvchoice.setOnSelectListener(new PickerView.onSelectListener() {

                    @Override
                    public void onSelect(String text)
                    {
                        chexhorandtype=text;
                    }
                });
            }
            @Override
            protected void initEvent() {
                btn_login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(window!=null&&window.getPopupWindow().isShowing()){

//                            String star= chexhorandtype.split(":")[0];
//                            String end= chexendhorandtype.split(":")[0];
//                            if(Integer.parseInt(star)>Integer.parseInt(end)){
//                                ToastUtil.show("结束时间大于开始时间");
//                                return;
//                            }
                            if("星期一".equals(chexhorandtype)){
                                SPUtils.setValue(Constant.Config.CHECKHOR,chectday+1+"_0");
                            }else if("星期二".equals(chexhorandtype)){
                                SPUtils.setValue(Constant.Config.CHECKHOR,chectday+1+"_1");
                            }else if("星期三".equals(chexhorandtype)){
                                SPUtils.setValue(Constant.Config.CHECKHOR,chectday+1+"_2");
                            }else if("星期四".equals(chexhorandtype)){
                                SPUtils.setValue(Constant.Config.CHECKHOR,chectday+1+"_3");
                            }else if("星期五".equals(chexhorandtype)){
                                SPUtils.setValue(Constant.Config.CHECKHOR,chectday+1+"_4");
                            }else if("星期六".equals(chexhorandtype)){
                                SPUtils.setValue(Constant.Config.CHECKHOR,chectday+1+"_5");
                            }else {
                                SPUtils.setValue(Constant.Config.CHECKHOR,chectday+1+"_6");
                            }


                            SPUtils.setValue(Constant.Config.PROJECTID,itemid);
//                            SPUtils.setValue(Constant.Config.CHECKENDHOR,chexendhorandtype+":00");
                            ActionItemFragemnt al=   (ActionItemFragemnt) mFragmentLists.get(actiontabs.getSelectedTabPosition());
                            al.notifyData(1,1);


                            window.getPopupWindow().dismiss();
                        }
                    }
                });
            }
            @Override
            protected void initWindow() {
                super.initWindow();
                PopupWindow instance=getPopupWindow();
                instance.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp=getActivity().getWindow().getAttributes();
                        lp.alpha=1f;
                        SPUtils.setValue(Constant.Config.CHECKHOR,"");
                        SPUtils.setValue(Constant.Config.CHECKENDHOR,"");
                        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        getActivity().getWindow().setAttributes(lp);
                    }
                });
            }
        };
    }



public class OtherAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            return ptys.getData().size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }
        ProjectViewHoder viewHoder;
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view==null){
                view=getLayoutInflater().inflate(R.layout.adapter_projecttype,null);
                viewHoder=new ProjectViewHoder(view);
                view.setTag(viewHoder);
            }else {
                viewHoder=(ProjectViewHoder)  view.getTag();
            }
            if(pos==i){
                viewHoder.tv_projecttype.setBackgroundResource(R.drawable.shap_projectselect);
            }else {
                viewHoder.tv_projecttype.setBackgroundResource(R.drawable.shap_check);
            }
            viewHoder.tv_projecttype.setText(ptys.getData().get(i).getItemName());

            return view;
        }
       }

       public class TimaAndTypeAdapter extends BaseAdapter{


           @Override
           public int getCount() {
               return pty.getData().size();
           }

           @Override
           public Object getItem(int i) {
               return null;
           }

           @Override
           public long getItemId(int i) {
               return 0;
           }
           ProjectViewHoder viewHoder;
           @Override
           public View getView(int i, View view, ViewGroup viewGroup) {
               if(view==null){
                   view=getLayoutInflater().inflate(R.layout.adapter_projecttype,null);
                   viewHoder=new ProjectViewHoder(view);
                   view.setTag(viewHoder);
               }else {
                   viewHoder=(ProjectViewHoder)  view.getTag();
               }
               if(pos==i){
                   viewHoder.tv_projecttype.setBackgroundResource(R.drawable.shap_projectselect);
               }else {
                   viewHoder.tv_projecttype.setBackgroundResource(R.drawable.shap_check);
               }

               viewHoder.tv_projecttype.setText(pty.getData().get(i).getItemName());

               return view;
           }

       }

       public void refreshCity(){
           ActionItemFragemnt al=   (ActionItemFragemnt) mFragmentLists.get(actiontabs.getSelectedTabPosition());
           al.notifyData(actiontabs.getSelectedTabPosition(),1);
       }
    public void refreshSearch(String keyworld){
        ActionItemFragemnt al=   (ActionItemFragemnt) mFragmentLists.get(actiontabs.getSelectedTabPosition());
        al.notifyData(actiontabs.getSelectedTabPosition(),2,keyworld);
    }

       public void getBanner(int poss){
           ParamMap.Build PB=   new ParamMap.Build();
           if(0==poss){
               PB.addParams("key", "HOME_ROTATION_BADMINTON");
           }else if(1==poss){
               PB.addParams("key", "HOME_ROTATION_COMMERCIAL");
           }else if(2==poss){
               PB.addParams("key", "HOME_ROTATION_COACH");
           }
           RestClient.getYfmNovate(getActivity()).post(Constant.API.YFM_GETBANNER,
                   PB.addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                            .build(),
                   new DmeycBaseSubscriber<ProjectTypeBean>(getActivity()){

                        @Override
                        public void onSuccess(ProjectTypeBean bean) {
                            banners.clear();
                            for (int i=0;i<bean.getData().size();i++){
                                banners.add(bean.getData().get(i).getItemName());
                            }
                            banner.setImageLoader(new GlideImageLoader());
                            banner.setImages(banners);
                            banner.start();
                          banner.setOnBannerClickListener(new OnBannerClickListener() {
                              @Override
                              public void OnBannerClick(int position) {
                                  if(2==position){
                                      startActivity(new Intent(getActivity(),BigActionActivity.class));
//                                      startActivity(new Intent(getActivity(),BannerSummentActivity.class));
                                  }else if(1 == position) {
                                      startActivity(new Intent(getActivity(),BannerClickActivity.class));
                                  }
                              }
                          });
                        }
                    });
       }
}
