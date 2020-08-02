package com.dmeyc.dmestoreyfm.fragment;

import android.annotation.SuppressLint;
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
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.bean.PkDataBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.BannerClickActivity;
import com.dmeyc.dmestoreyfm.ui.BannerSummentActivity;
import com.dmeyc.dmestoreyfm.ui.BigActionActivity;
import com.dmeyc.dmestoreyfm.ui.HeroRankActivity;
import com.dmeyc.dmestoreyfm.ui.NewsChannelActivity;
import com.dmeyc.dmestoreyfm.ui.PkingActivity;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.dmeyc.dmestoreyfm.wedgit.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jockie on 2017/11/3
 * Email:jockie911@gmail.com
 */

@SuppressLint("ValidFragment")
public class PKFragment extends BaseFragment implements View.OnClickListener {

    protected List<String> mTitleLists; //导航条文字List
    protected List<BaseFragment> mFragmentLists;    //导航fragment list

    private ViewPager pkview_pager;
    private TabLayout pktabs;

    @Bind(R.id.cv_pkone)
    CircleImageView cv_pkone;
    @Bind(R.id.cv_two)
    CircleImageView cv_pktwo;
    @Bind(R.id.cv_herthree)
    CircleImageView cv_herthree;
    @Bind(R.id.cv_hertwo)
    CircleImageView cv_hertwo;
    @Bind(R.id.cv_herone)
    CircleImageView cv_herone;

    TextView tv_hero;
    TextView tv_pk;
    @Bind(R.id.pk_twoname)
    TextView pk_twoname;
    @Bind(R.id.pk_onename)
    TextView pk_onename;
    @Bind(R.id.tv_heroone)
    TextView tv_heroone;
    @Bind(R.id.tv_herotwo)
    TextView tv_herotwo;
    @Bind(R.id.tv_herothree)
    TextView tv_herothree;

    TextView tv_chalange;
    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.ll_pking)
    LinearLayout ll_pking;
    @Bind(R.id.ll_hero)
    LinearLayout ll_hero;

    @OnClick(R.id.go_back)
    void toFinishClick(){
        getActivity().finish();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.item_rv_pk;
    }

    @Override
    protected void initData() {
        pktabs = (TabLayout) mRootView.findViewById(R.id.pktabs); //使用bind 会出现空指针
        pktabs.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(pktabs, 20, 0);
            }
        });
        pkview_pager = (ViewPager) mRootView.findViewById(R.id.pkview_pager); //使用bind 会出现空指针
//        pkadd_channel_iv = (ImageView) mRootView.findViewById(R.id.pkadd_channel_iv); //使用bind 会出现空指针
        tv_hero = (TextView) mRootView.findViewById(R.id.tv_hero);
        tv_pk = (TextView) mRootView.findViewById(R.id.tv_pk);
        tv_chalange = (TextView) mRootView.findViewById(R.id.tv_chalange);
        tv_pk.setTextColor(getActivity().getResources().getColor(R.color.black));

        tv_chalange.setOnClickListener(this);
        tv_pk.setOnClickListener(this);
        tv_hero.setOnClickListener(this);
//        pkadd_channel_iv.setOnClickListener(this);
        mTitleLists = new ArrayList<>();
        mFragmentLists = new ArrayList<>();
        getTitleList(mTitleLists);
        getFragmentLists(mFragmentLists);

        FragmentManager fm = getActivity().getSupportFragmentManager();
        pkview_pager.setAdapter(new CustomLazyViewPagerAdapter(fm, mFragmentLists, mTitleLists));
        pktabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        pktabs.setupWithViewPager(pkview_pager);
        ll_pking.setOnClickListener(this);
        ll_hero.setOnClickListener(this);
    }

    @Override
    protected void initData(View view) {
        getBanner();
    }

    protected void getTitleList(List<String> mTitleLists) {
        mTitleLists.add("羽毛球");
//        mTitleLists.add("校园");
//        mTitleLists.add("跑步");
//        mTitleLists.add("户外");
//        mTitleLists.add("教社");
//        mTitleLists.add("专题");
    }

    ActivityItemFragment aitem;

    protected void getFragmentLists(List<BaseFragment> mFragmentLists) {
//        cf=new CategoryFragment(getStoryId());
//        mFragmentLists.add(cf);
//        bsf= new BrandStoreFragment(getStoryId());
//        mFragmentLists.add(bsf);
//        mFragmentLists.add(new ActivityItemFragment());
//        mFragmentLists.add(new SpecialFragment());
//        mFragmentLists.add(new HomeLookFragment());
//        mFragmentLists.add(new SpecialFragment());
//        mFragmentLists.add(new HomeLookFragment());
//        for (int i=0;i<mTitleLists.size();i++){
        aitem = new ActivityItemFragment(type, R.id.tv_hero);
        mFragmentLists.add(aitem);
//        }

    }


    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
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

    int type = 1;

    @Override
    public void onClick(View v) {
        int viewid = v.getId();
        if (viewid == R.id.add_channel_iv) {
//            ToastUtil.show("你好哈哈哈哈");
            Intent intent = new Intent(getActivity(), NewsChannelActivity.class);
            startActivity(intent);
        } else if (R.id.tv_hero == viewid) {
            type = 3;
            tv_hero.setTextColor(getActivity().getResources().getColor(R.color.black));
            tv_pk.setTextColor(getActivity().getResources().getColor(R.color.gray));
            tv_chalange.setTextColor(getActivity().getResources().getColor(R.color.gray));
            ActivityItemFragment al = (ActivityItemFragment) mFragmentLists.get(pktabs.getSelectedTabPosition());
//            al.notifyData(pktabs.getSelectedTabPosition(),1);
            al.notifyData(1, 2, type);
        } else if (R.id.tv_pk == viewid) {
            type = 2;
            tv_hero.setTextColor(getActivity().getResources().getColor(R.color.gray));
            tv_pk.setTextColor(getActivity().getResources().getColor(R.color.black));
            tv_chalange.setTextColor(getActivity().getResources().getColor(R.color.gray));
            ActivityItemFragment al = (ActivityItemFragment) mFragmentLists.get(pktabs.getSelectedTabPosition());
//            al.notifyData(pktabs.getSelectedTabPosition(),1);
            al.notifyData(1, 1, type);
        } else if (R.id.tv_chalange == viewid) {
            type = 1;
            tv_hero.setTextColor(getActivity().getResources().getColor(R.color.gray));
            tv_pk.setTextColor(getActivity().getResources().getColor(R.color.gray));
            tv_chalange.setTextColor(getActivity().getResources().getColor(R.color.black));
            ActivityItemFragment al = (ActivityItemFragment) mFragmentLists.get(pktabs.getSelectedTabPosition());
//            al.notifyData(pktabs.getSelectedTabPosition(),1);
            al.notifyData(1, 0, type);
        } else if (R.id.ll_pking == viewid) {
            startActivity(new Intent(getActivity(), PkingActivity.class));
        } else if (R.id.ll_hero == viewid) {
            startActivity(new Intent(getActivity(), HeroRankActivity.class));
        }
    }

    public void refreshCity() {
        ActivityItemFragment al = (ActivityItemFragment) mFragmentLists.get(pktabs.getSelectedTabPosition());
        al.notifyData(1, 1, 2);
    }

    ArrayList<String> banners = new ArrayList<>();

    public void getBanner() {

        ParamMap.Build PB = new ParamMap.Build();
        PB.addParams("longitude", SPUtils.getStringData(Constant.Config.LONGTUDE))
                .addParams("latitude", SPUtils.getStringData(Constant.Config.LATITUDE));
        RestClient.getYfmNovate(getActivity()).post(Constant.API.YFM_GETPKBANNER,
                PB.addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                        .build(),
                new DmeycBaseSubscriber<PkDataBean>(getActivity()) {

                    @Override
                    public void onSuccess(PkDataBean bean) {
                        banners.clear();
                        for (int i = 0; i < bean.getData().getRotationList().size(); i++) {
                            banners.add(bean.getData().getRotationList().get(i).getItemName());
                        }
                        banner.setImageLoader(new GlideImageLoader());
                        banner.setImages(banners);
                        banner.start();
                        banner.setOnBannerClickListener(new OnBannerClickListener() {
                            @Override
                            public void OnBannerClick(int position) {
                                if(2==position){
                                    startActivity(new Intent(getActivity(), BigActionActivity.class));
                                } else  if(1==position) {
                                    startActivity(new Intent(getActivity(),BannerClickActivity.class));
                                }

//                                if (1 == position) {
//                                    startActivity(new Intent(getActivity(), BannerSummentActivity.class));
//                                } else {
//                                    startActivity(new Intent(getActivity(), BannerClickActivity.class));
//                                }
                            }
                        });

                        if (bean.getData().getPkActivity() != null) {
                            GlideUtil.loadImage(getActivity(), bean.getData().getPkActivity().getGroup_a_logo(), cv_pkone);
                            GlideUtil.loadImage(getActivity(), bean.getData().getPkActivity().getGroup_b_logo(), cv_pktwo);
                            pk_onename.setText(bean.getData().getPkActivity().getGroup_a_name());
                            pk_twoname.setText(bean.getData().getPkActivity().getGroup_b_name());
                        }
                        if (bean.getData().getHerosList() != null) {
                            if (bean.getData().getHerosList().size() >= 3) {
                                GlideUtil.loadImage(getActivity(), bean.getData().getHerosList().get(0).getImg_url(), cv_herone);
                                GlideUtil.loadImage(getActivity(), bean.getData().getHerosList().get(1).getImg_url(), cv_hertwo);
                                GlideUtil.loadImage(getActivity(), bean.getData().getHerosList().get(2).getImg_url(), cv_herthree);
                                tv_heroone.setText(bean.getData().getHerosList().get(0).getGroup_name());
                                tv_herotwo.setText(bean.getData().getHerosList().get(1).getGroup_name());
                                tv_herothree.setText(bean.getData().getHerosList().get(2).getGroup_name());
                            } else if (bean.getData().getHerosList().size() >= 2) {
                                GlideUtil.loadImage(getActivity(), bean.getData().getHerosList().get(0).getImg_url(), cv_herone);
                                GlideUtil.loadImage(getActivity(), bean.getData().getHerosList().get(1).getImg_url(), cv_hertwo);
//        GlideUtil.loadImage(getActivity(),bean.getData().getHerosList().get(2).getImg_url(),cv_herthree);
                                tv_heroone.setText(bean.getData().getHerosList().get(0).getGroup_name());
                                tv_herotwo.setText(bean.getData().getHerosList().get(1).getGroup_name());
//        tv_herothree.setText(bean.getData().getHerosList().get(2).getGroup_name());
                            } else if (bean.getData().getHerosList().size() >= 2) {
                                GlideUtil.loadImage(getActivity(), bean.getData().getHerosList().get(0).getImg_url(), cv_herone);
//        GlideUtil.loadImage(getActivity(),bean.getData().getHerosList().get(1).getImg_url(),cv_hertwo);
//        GlideUtil.loadImage(getActivity(),bean.getData().getHerosList().get(2).getImg_url(),cv_herthree);
                                tv_heroone.setText(bean.getData().getHerosList().get(0).getGroup_name());
//        tv_herotwo.setText(bean.getData().getHerosList().get(1).getGroup_name());
//        tv_herothree.setText(bean.getData().getHerosList().get(2).getGroup_name());
                            }

                        }
                    }
                });
    }

    public void notappoin() {
        getBanner();
        aitem.notifyData(0, 0, 0);
    }
}
