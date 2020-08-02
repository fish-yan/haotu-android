package com.dmeyc.dmestoreyfm.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.fragment.home.HomeFragment;
import com.dmeyc.dmestoreyfm.ui.MyAppointActivity;
import com.dmeyc.dmestoreyfm.utils.DensityUtil;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.utils.Util;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AppointmentFragemnt extends HomeFragment {


    @Bind(R.id.view_divide_shadow)
    View shadowDivideView;
    @Bind(R.id.tv_catory)
    TextView tv_catory;

    @Bind(R.id.iv_shopcar)
    ImageView iv_shopcar;
    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.viewpager)
    NoScrollViewPager viewpager;


    List<BaseFragment> FragmentLists;
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_appointment;
    }
    @Override
    protected void getTitleList(List<String> mTitleLists) {
        mTitleLists.add("教练");
        mTitleLists.add("场馆");
        isShowTitle(tv_catory);
    }

    protected void isShowTitle(TextView view) {
        view.setVisibility(View.GONE);
    }

    @Override
    protected void getFragmentLists(List<BaseFragment> mFragmentLists) {
//        mFragmentLists.add(new FindFragment());
//        mFragmentLists.add(new ConcernFragment());

        mFragmentLists.add(new TeachFragment());
        mFragmentLists.add(new PlaceFragment());
        FragmentLists=mFragmentLists;
    }

    @Override
    protected void reSetTabBlockWidth() {
        Util.reflex(mTabLayout, DensityUtil.dip2px(20));
    }
    public void setShadowViewVisbiablty(boolean visbiablty) {
        shadowDivideView.setVisibility(visbiablty ? View.VISIBLE : View.GONE);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getActivity().getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                 if(0==position){
                     TeachFragment teachFragment=(TeachFragment)  FragmentLists.get(0);
                     teachFragment.notice();
                 }else {
                     PlaceFragment place=(PlaceFragment)  FragmentLists.get(1);
                     place.notice();
                 }
//                ToastUtil.show(position+"33333333");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



      return rootView;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    //    @OnClick(R.id.ll_toRelease)
//    public void onViewClicked() {
//        Intent intent = new Intent();
//        intent.setClass(getContext(), ReleaseActivity.class);
//        getActivity().startActivity(intent);
//    }
    @OnClick(R.id.iv_shopcar)
    public void onClick() {
        rightIconClick();
    }
    public void rightIconClick(){
//        Intent intent = new Intent();
//        intent.setClass(getContext(), ReleaseActivity.class);
//        getActivity().startActivity(intent);
        Intent intent = new Intent();
        intent.setClass(getContext(), MyAppointActivity.class);
        getActivity().startActivity(intent);

    }

    private void updateTabTextView(TabLayout.Tab tab, boolean isSelect) {

        if (isSelect) {
            //选中加粗
            TextView textView = tab.getCustomView().findViewById(android.R.id.text1);
//            TextView tabSelect = (TextView) tab.getCustomView().findViewById(R.id.tab_item_textview);
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            textView.setTextColor(getActivity().getResources().getColor(R.color.white));
//            textView.setTextSize(20);
            textView.setText(tab.getText());
        } else {
            TextView tabUnSelect = tab.getCustomView().findViewById(android.R.id.text1);
            tabUnSelect.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            tabUnSelect.setTextColor(getActivity().getResources().getColor(R.color.white));
//            tabUnSelect.setTextSize(18);
            tabUnSelect.setText(tab.getText());
        }
    }

    public void notappoin(){

//        if(0==tablayout.getSelectedTabPosition()){
//            TeachFragment teachFragment=(TeachFragment)  FragmentLists.get(0);
//            teachFragment.notice();
//        }else {
//            PlaceFragment place=(PlaceFragment)  FragmentLists.get(1);
//            place.notice();
//        }
    }
}
