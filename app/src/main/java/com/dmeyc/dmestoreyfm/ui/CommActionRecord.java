package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.CustomLazyViewPagerAdapter;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.ActionRecordListBean;
import com.dmeyc.dmestoreyfm.bean.common.CommonBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.fragment.CommFragment;
import com.dmeyc.dmestoreyfm.fragment.CommPKFragment;
import com.dmeyc.dmestoreyfm.fragment.CourPushFragment;
import com.dmeyc.dmestoreyfm.fragment.MoneyInFragment;
import com.dmeyc.dmestoreyfm.fragment.MoneyOutFragment;
import com.dmeyc.dmestoreyfm.fragment.NoScrollViewPager;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.tamic.novate.Throwable;

import java.util.ArrayList;
import java.util.List;

public class CommActionRecord extends BaseActivity implements View.OnClickListener {
    protected List<String> mTitleLists; //导航条文字List
    protected List<BaseFragment> mFragmentLists;    //导航fragment list
    private ImageView iv_right_title_bar;

LinearLayout ll_tab;
    //    @Bind(R.id.tablayout)
    TabLayout mTabLayout;
TextView tv_title;
    //    @Bind(R.id.viewpager)
    NoScrollViewPager mViewPager;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_commrecord;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
    @Override
    protected void initData() {
        mTitleLists = new ArrayList<>();
        mFragmentLists = new ArrayList<>();

        mTabLayout = (TabLayout) findViewById(R.id.detail_tablayout);
        mViewPager = (NoScrollViewPager)findViewById(R.id.detai_viewpager);
        tv_title = (TextView) findViewById(R.id.tv_title);
        ll_tab=(LinearLayout) findViewById(R.id.ll_tab);

        iv_right_title_bar=(ImageView) findViewById(R.id.iv_right_title_bar);
        iv_right_title_bar.setOnClickListener(this);
//        if("0".equals(getIntent().getStringExtra("isMycomm"))){
//            iv_right_title_bar.setVisibility(View.VISIBLE);
//        }
            if(getIntent().getStringExtra("grouptype").equals("3")){
                tv_title.setText("课程");
                ll_tab.setVisibility(View.GONE);
                getTitleList(mTitleLists);
                getFragmentLists(mFragmentLists);
            }else {
                tv_title.setText("活动");
                ll_tab.setVisibility(View.VISIBLE);
                getTitleList(mTitleLists);
                getFragmentLists(mFragmentLists);
            }

        FragmentManager fm =getSupportFragmentManager();
        mViewPager.setAdapter(new CustomLazyViewPagerAdapter(fm,mFragmentLists,mTitleLists));
//        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
//        mViewPager.setOffscreenPageLimit(2);
    }



    @Override
    public void onClick(View view) {
        int vierid=view.getId();
        if(R.id.iv_right_title_bar==vierid){
            Intent intent = new Intent();
            intent.putExtra("groupid",getIntent().getIntExtra("groupid",-1));
            intent.setClass(this, PublishActivity.class);
//        intent.setClass(getContext(), ReleaseActivity.class);
            startActivity(intent);
        }
    }

    public class ViewHorder{
        private TextView tv_title,tv_projecttype,tv_time,tv_losandwin;
        private CircleImageView civ_avatar;
        public ViewHorder(View view){
            civ_avatar=(CircleImageView)view.findViewById(R.id.civ_avatar);
            tv_title=(TextView) view.findViewById(R.id.tv_title);
            tv_projecttype=(TextView) view.findViewById(R.id.tv_projecttype);
            tv_time=(TextView) view.findViewById(R.id.tv_time);
            tv_losandwin=(TextView) view.findViewById(R.id.tv_losandwin);
        }
    }
    protected void getTitleList(List<String> mTitleLists) {
        if(getIntent().getStringExtra("grouptype").equals("3")){
            mTitleLists.add("活动");
        }else {
            mTitleLists.add("活动");
            mTitleLists.add("PK赛");
        }

    }
    protected void getFragmentLists(List<BaseFragment> mFragmentLists) {
        if(getIntent().getStringExtra("grouptype").equals("3")){
            mFragmentLists.add(new CourPushFragment(getIntent().getIntExtra("groupid",-1),0));
        }else {
            mFragmentLists.add(new CommFragment(getIntent().getIntExtra("groupid",-1),0));
            mFragmentLists.add(new CommPKFragment(getIntent().getIntExtra("groupid",-1),1));
        }
    }
}
