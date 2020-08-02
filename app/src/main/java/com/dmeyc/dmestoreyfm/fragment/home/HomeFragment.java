package com.dmeyc.dmestoreyfm.fragment.home;

import android.content.Intent;
import android.os.Build;
import android.view.View;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.base.BaseTabFragment;
import com.dmeyc.dmestoreyfm.fragment.brand.BrandDesignFragment;
import com.dmeyc.dmestoreyfm.ui.SearchGoodsActivity;
import com.dmeyc.dmestoreyfm.ui.show.SpecialFragment;
import com.dmeyc.dmestoreyfm.utils.DensityUtil;
import com.dmeyc.dmestoreyfm.utils.Util;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jockie on 2017/11/3
 * Email:jockie911@gmail.com
 */

public class HomeFragment extends BaseTabFragment{

    @Bind(R.id.status_view)
    View statusView;
    @Override
    protected int getLayoutRes() {
        return R.layout.fm_home;
    }

    @Override
    protected void initData() {
        super.initData();
        setStatusGone();
    }

    @Override
    protected void initData(View view) {

    }

    @Override
    protected void getTitleList(List<String> mTitleLists) {
        mTitleLists.add("推荐");
        mTitleLists.add("品牌");
        mTitleLists.add("专题");
    }
    @Override
    protected void getFragmentLists(List<BaseFragment> mFragmentLists) {
        mFragmentLists.add(new HomeRecommendFragment());
        mFragmentLists.add(new BrandDesignFragment());
        mFragmentLists.add(new SpecialFragment());
    }

    @OnClick({R.id.iv_home_search})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_home_search:
                startActivity(new Intent(getActivity(), SearchGoodsActivity.class));
                break;
             }
      }
    /**
     * 低于5.0 让带填充的view gone
     */
    public void setStatusGone(){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            statusView.setVisibility(View.GONE);
        }
    }
    @Override
    protected void reSetTabBlockWidth() {
        Util.reflex(mTabLayout, DensityUtil.dip2px(40));
    }
}
