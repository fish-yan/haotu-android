package com.dmeyc.dmestoreyfm.newui.home.search.searchdetail;

import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.dialog.YFMScreenCityDialog;
import com.dmeyc.dmestoreyfm.newbase.BaseFragment;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;
import com.dmeyc.dmestoreyfm.newbase.BaseTabNewActivity;

import java.util.List;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.newbase.IBaseView;
import com.dmeyc.dmestoreyfm.newui.common.goods.GoodsFragment;
import com.dmeyc.dmestoreyfm.newui.home.groupaction.GroupActionFragment;
import com.dmeyc.dmestoreyfm.newui.home.search.searchuser.SearchUserFragment;
import com.dmeyc.dmestoreyfm.newui.home.search.searchvideo.SearchVideoFragment;
import com.dmeyc.dmestoreyfm.utils.DensityUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.dmeyc.dmestoreyfm.wedgit.SearchTitleView;

import butterknife.Bind;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * create by cxg on 2019/12/9
 */
public class SearchDetailActivity extends BaseTabNewActivity<IBaseView, BasePresenter<IBaseView>> implements SearchTitleView.OnItemClickListener {

    @Bind(R.id.searchTitleView)
    SearchTitleView mSearchTitleView;

    private SearchVideoFragment mSearchVideoFragment;
    private SearchUserFragment mSearchUserFragment;
    private GroupActionFragment mGroupActionFragment;
    private GoodsFragment mGoodsFragment;
    private GoodsFragment mCourseFragment;


    private YFMScreenCityDialog mCityDialog;

    private String mSearchStr = "";

    public static void newInstance(Context context, String searchString) {
        Intent intent = new Intent(context, SearchDetailActivity.class);
        intent.putExtra("search", searchString);
        context.startActivity(intent);
    }

    protected void reSetTabBlockWidth() {
        Util.reflex(mTabLayout, DensityUtil.dip2px(10));
    }
    @Override
    protected void getFragmentLists(List<BaseFragment> mFragmentLists) {
        mSearchVideoFragment = new SearchVideoFragment();
        mSearchUserFragment = new SearchUserFragment();
        mGroupActionFragment = GroupActionFragment.newInstance(GroupActionFragment.FROM_GROUP_ACTION);
//        Bundle bundle2 = new Bundle();
//        bundle2.putString(ExtraKey.GROUP_ACTION_FROM, GroupActionFragment.FROM_GROUP_ACTION);
//        mGroupActionFragment.setArguments(bundle2);

        mGoodsFragment = GoodsFragment.newInstance(GoodsFragment.TYPE_GOODS);
        mCourseFragment = GoodsFragment.newInstance(GoodsFragment.TYPE_COURSE);
        mFragmentLists.add(mSearchVideoFragment);
        mFragmentLists.add(mSearchUserFragment);
        mFragmentLists.add(mGroupActionFragment);

//        Bundle bundle = new Bundle();
//        bundle.putString(ExtraKey.TYPE_FROM, GoodsFragment.TYPE_GOODS);
//        mGoodsFragment.setArguments(bundle);
        mFragmentLists.add(mGoodsFragment);

//        Bundle bundle1 = new Bundle();
//        bundle1.putString(ExtraKey.TYPE_FROM, GoodsFragment.TYPE_COURSE);
//        mCourseFragment.setArguments(bundle1);
        mFragmentLists.add(mCourseFragment);
        doSearch(mSearchStr, false);

    }

    @Override
    protected void getTitleList(List<String> mTitleLists) {
        mTitleLists.add("视频");
        mTitleLists.add("用户");
        mTitleLists.add("活动");
        mTitleLists.add("商品");
        mTitleLists.add("课程");
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int setContentView() {
        return R.layout.activity_search_detail;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        mSearchStr = getIntent().getStringExtra("search");

        mSearchTitleView.setListener(this);
        mSearchTitleView.setLocationString(SPUtils.getStringData(Constant.Config.CURRENT_CITY));
        mSearchTitleView.setSearchText(getIntent().getStringExtra("search"));
        mSearchTitleView.setListener(this);

    }

    @Override
    public void onLocationClick() {
        mCityDialog = new YFMScreenCityDialog(this, R.style.dialog_style_right);
        mCityDialog.onSave(new YFMScreenCityDialog.SaveClickLisenter() {
            @Override
            public void selectCity(String city) {
                mSearchTitleView.setLocationString(city);
                mCityDialog.dismiss();
                SPUtils.savaStringData(Constant.Config.CURRENT_CITY, city + "市");
                // TODO: 2019/12/9  search
            }
        });
        mCityDialog.show();
    }

    @Override
    public void onSearch(String searchString) {
        doSearch(searchString, true);
    }

    private void doSearch(String searchString, boolean request) {
        mSearchVideoFragment.doSearch(searchString, request);
        mSearchUserFragment.doSearch(searchString, request);
        mGroupActionFragment.doSearch(searchString, request);
        mGoodsFragment.doSearch(searchString, request);
        mCourseFragment.doSearch(searchString, request);
    }

    ;

    @Override
    public void onCancel() {
        finish();
    }

}
