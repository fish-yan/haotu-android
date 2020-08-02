package com.dmeyc.dmestoreyfm.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.CustomLazyViewPagerAdapter;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.bean.AttendBean;
import com.dmeyc.dmestoreyfm.bean.RecommendBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.fragment.BrandStoreFragment;
import com.dmeyc.dmestoreyfm.fragment.CategoryFragment;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.present.BandDetailPresenter;
import com.dmeyc.dmestoreyfm.utils.DensityUtil;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tamic.novate.Throwable;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class BrandDetailActivity extends BaseActivity<BandDetailPresenter>  {

    @Bind(R.id.recycleview)
    RecyclerView mRecycleView;
//    public int type; // type:1代表品牌,2代表设计师,
    @Bind(R.id.tv_titles)
    TextView tv_titles;
    @Bind(R.id.tv_sale)
    TextView tv_sale;
    @Bind(R.id.tv_attention)
    TextView tv_attention;
    @Bind(R.id.iv_picitem)
    RoundedImageView iv_picitem;

    @Bind(R.id.tablayout)
    TabLayout mTabLayout;

    @Bind(R.id.viewpager)
    ViewPager mViewPager;

    @Bind(R.id.tv_attbande)
    TextView tv_attbande;
//
//    @Bind(R.id.iv_band)
//    ImageView iv_band;

//    private RecommendBean recommendBean;
  int stae=-1;
    protected List<String> mTitleLists; //导航条文字List
    protected List<BaseFragment> mFragmentLists;    //导航fragment list
    @Override
    protected int getLayoutRes() {
        return R.layout.ac_brand_detail;
    }
    @Override
    protected BandDetailPresenter initPresenter() {
        return new BandDetailPresenter();
    }

    @Override
    protected void initData() {

        mTitleLists = new ArrayList<>();
        mFragmentLists = new ArrayList<>();
        getTitleList(mTitleLists);
        getFragmentLists(mFragmentLists);

        mTabLayout = (TabLayout) mRootView.findViewById(R.id.tablayout);
        mViewPager = (ViewPager) mRootView.findViewById(R.id.viewpager);
        mTabLayout.setVisibility(View.INVISIBLE);

      FragmentManager fm =getSupportFragmentManager();
        mViewPager.setAdapter(new CustomLazyViewPagerAdapter(fm,mFragmentLists,mTitleLists));
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
        reSetTabBlockWidth();
        getDetail(null);
        mTabLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mTabLayout.setVisibility(View.VISIBLE);
            }
        },200);

//        mPresenter.getDetail(this,getStoryId(), new BandDetailPresenter.OnDetailLisenter() {
//            @Override
//            public void onSuccess(RecommendBean bean) {
//                ToastUtil.show("成功");
//                recommendBean=bean;
//                if(cf!=null){
//                        GlideUtil.loadImage(BrandDetailActivity.this,
//                                bean.getData().getStore().getLogo()
//                                , iv_band);
//
////                    cf.setLog(bean.getData().getStore().getLogo());
//                }
//
//                if(bsf!=null){
//                    bsf.setIntr(bean.getData().getStore().getIntroduction());
//                }

//                setTitle(bean.getData().getStore().getName());
//                tv_titles.setText(bean.getData().getStore().getName());
//                tv_sale.setText(bean.getData().getStore().getBrands().get(0).getStore()+"");
//                tv_attention.setText(bean.getData().getStore().getAttend()+"");

//                iv_picitem.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                GlideUtil.loadImage(BrandDetailActivity.this,
//                        bean.getData().getStore().getLogo()
//                        ,iv_picitem);

//                if(bean.getData().getStore().isAttend()){
//                    tv_attbande.setText("已关注");
//                    tv_attbande.setTextColor(getResources().getColor(R.color.gray));
//                    stae=1;
//                }else {
//                    stae=0;
//                    tv_attbande.setText("关注品牌");
//                    tv_attbande.setTextColor(getResources().getColor(R.color.indicator_selected_color));
//                }
//            }
//            @Override
//            public void onFailure(String errMsg) {
//                ToastUtil.show(errMsg);
//            }
//        });

    }
    private CategoryFragment cf;
    private BrandStoreFragment bsf;
    protected void getTitleList(List<String> mTitleLists) {
        mTitleLists.add("全部商品");
        mTitleLists.add("品牌故事");
//        mTitleLists.add("专题");
    }
    protected void getFragmentLists(List<BaseFragment> mFragmentLists) {
        cf=new CategoryFragment(getStoryId());
        mFragmentLists.add(cf);
        bsf= new BrandStoreFragment(getStoryId());
        mFragmentLists.add(bsf);
//        mFragmentLists.add(new HomeLookFragment());
//        mFragmentLists.add(new SpecialFragment());
    }
    /**
     * 导航条长度形态改变,适应字体长度或者等分, 详情见Util
     */
    protected void reSetTabBlockWidth() {
        Util.reflex(mTabLayout,DensityUtil.dip2px(75));
    }
    /**
     * 获取品牌或者设计师的Id
     * @return
     */
    public int getId(){
        return getIntent().getIntExtra(Constant.Config.ID,0);
    }

    public int getStoryId(){
        return getIntent().getIntExtra(Constant.Config.STORY_ID,-1);
    }

    public int getType(){
        return getIntent().getIntExtra(Constant.Config.TYPE, Constant.Config.TYPE_BRAND);
    }

    /**
     * 设置标题
     * @param title
     */
    public void setTitle(String title){
        tvTitle.setText(title);
    }

    /**
     * 是否是品牌
     * @return
     */
    public boolean isBrand(){
        return getType() == Constant.Config.TYPE_BRAND;
    }

    @OnClick(R.id.tv_attbande)
    public void onAtetendClick(View view) {
        if(TextUtils.isEmpty(Util.getUserId())){
            ToastUtil.show("请登录");
            return;
        }
        RestClient.getNovate(this).post(Constant.API.ATTEND_SHOP, new ParamMap.Build()
//                .addParams("categoryChildren", categoryuid)
//                .addParams("gender", gener)
//                .addParams("season", season)
                .addParams("userId", Util.getUserId())
                .addParams("type", 1)
                .addParams("attend",getStoryId())
                .build(), new DmeycBaseSubscriber<AttendBean>() {
            @Override
            public void onSuccess(AttendBean bean) {
                 if(1==stae){
                     tv_attbande.setText("关注店铺");
                     tv_attbande.setTextColor(getResources().getColor(R.color.indicator_selected_color));
                     stae=0;
                 }else {
                     tv_attbande.setText("已关注");
                     tv_attbande.setTextColor(getResources().getColor(R.color.gray));
                     stae=1;
                 }
         }
            @Override
            public void onError(Throwable e) {
//                Toast.makeText(BrandDetailActivity.this,"势必爱",Toast.LENGTH_LONG).show();
            }
        });
    }


    public void getDetail(  final BandDetailPresenter.OnDetailLisenter lisener){

        RestClient.getNovate(this).post(Constant.API.STORE_DETIAL, new ParamMap.Build()
                .addParams("store", getStoryId())
                .build(), new DmeycBaseSubscriber<RecommendBean>() {
            @Override
            public void onSuccess(RecommendBean bean) {
//                if(bean.getData().getGoods().size()>0){
                    tv_sale.setText(bean.getData().getStore().getProduct()+"");
                    tv_attention.setText(bean.getData().getStore().getAttend()+"");

                iv_picitem.setScaleType(ImageView.ScaleType.CENTER_CROP);
                GlideUtil.loadImage(BrandDetailActivity.this,
                        bean.getData().getStore().getLogo()
                        ,iv_picitem);

                setTitle(bean.getData().getStore().getName());
                tv_titles.setText(bean.getData().getStore().getName());
                if(cf!=null){
                    cf.setLog(bean.getData().getStore().getLogo());
                }
                if(bean.getData().getStore().isAttend()){
                    tv_attbande.setText("已关注");
                    tv_attbande.setTextColor(getResources().getColor(R.color.gray));
                    stae=1;
                }else {
                    stae=0;
                    tv_attbande.setText("关注品牌");
                    tv_attbande.setTextColor(getResources().getColor(R.color.indicator_selected_color));
                }
                if(bsf!=null){
                    bsf.setIntr(bean.getData().getStore().getIntroduction());
                }


            }
            @Override
            public void onError(Throwable e) {

            }
        });

    }
}
