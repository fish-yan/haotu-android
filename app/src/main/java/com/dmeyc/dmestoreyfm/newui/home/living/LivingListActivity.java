package com.dmeyc.dmestoreyfm.newui.home.living;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.response.ActivityDetailBean;
import com.dmeyc.dmestoreyfm.bean.response.LivingListBean;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.event.MyEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseFragment;
import com.dmeyc.dmestoreyfm.newbase.BaseTabNewActivity;
import com.dmeyc.dmestoreyfm.newui.home.living.lookanchor.LookAnchorActivity;
import com.dmeyc.dmestoreyfm.newui.release.selectanchor.SearchAnchorActivity;
import com.dmeyc.dmestoreyfm.utils.DensityUtil;
import com.dmeyc.dmestoreyfm.utils.ShareDialogHelper;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.dmeyc.dmestoreyfm.wedgit.GlideImageLoader;
import com.dmeyc.dmestoreyfm.wedgit.HeaderIconView;
import com.dmeyc.dmestoreyfm.wedgit.PopupMenu;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * create by cxg on 2019/12/29
 */
public class LivingListActivity extends BaseTabNewActivity<ILivingActivityView, LivingActivityPresenter> implements ILivingActivityView {
    private String mActivityId;
    ArrayList<String> list = new ArrayList<>();

    private PopupMenu mPopupMenu;

    private LivingListFragment mNormalFragment;
    private LivingListFragment mHotFragment;

    private int mPageNo = 1;
    private String mCheckType = LivingListFragment.TYPE_TIME;//请求接口
    private String mRequestTime = "";

    @Bind(R.id.headerView)
    HeaderIconView mHeaderView;
    @Bind(R.id.smartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;

    @Bind(R.id.tv_activity_name)
    TextView mTvActivityName;

    @Bind(R.id.tv_scan_count)
    TextView mTvScanCount;
    @Bind(R.id.tv_count)
    TextView mTvCount;

    @Bind(R.id.banner)
    Banner mBanner;

    private View mShareView;
    private String mActionName;
    private int mTotalCount=0;

    public static void newInstance(Context context, String activityId) {
        Intent intent = new Intent(context, LivingListActivity.class);
        intent.putExtra(ExtraKey.ACTIVITY_ID, activityId);
        context.startActivity(intent);
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_living_list;
    }

    @Override
    protected boolean enableEventBus() {
        return true;
    }

    protected void reSetTabBlockWidth() {
        Util.reflex(mTabLayout, DensityUtil.dip2px(5));
    }

    @Override
    protected void initViews() {
        list.add("添加主播");
        list.add("分享链接");
        list.add("分享图片");
        mActivityId = getIntent().getStringExtra(ExtraKey.ACTIVITY_ID);
        setTitleWithRightIcon("直播", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShareView = v;
                showMenu(v);
            }
        }, R.drawable.btn_navbar_more);
        mSmartRefreshLayout.setEnableRefresh(true);
        mSmartRefreshLayout.setEnableLoadmore(false);
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPageNo = 1;
                mRequestTime = "";
                mPresenter.httpRequestData();
                mPresenter.httpBannerData();
            }
        });

    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.httpAddSeeCount(mActivityId);
        mPresenter.httpRequestData();
        mPresenter.httpBannerData();
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        mCheckType = LivingListFragment.TYPE_TIME;
                        break;
                    case 1:
                        mCheckType = LivingListFragment.TYPE_HOT;
                        break;
                }
                setCount();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void showMenu(View v) {

        mPopupMenu = new PopupMenu(LivingListActivity.this, list);
        mPopupMenu.setWidth(mPopupMenu.dip2px(this, 100));
        mPopupMenu.setHeight(mPopupMenu.dip2px(this, 130));
        mPopupMenu.showLocation(R.id.iv_title_right);// 设置弹出菜单弹出的位置
        // 设置回调监听，获取点击事件
        mPopupMenu.setOnItemClickListener(new PopupMenu.OnItemClickListener() {
            @Override
            public void onClick(PopupMenu.MENUITEM item, String str) {

            }

            @Override
            public void onClick(String str, int pos) {
                mPopupMenu.dismiss();
                switch (pos) {
                    case 0:
                        SearchAnchorActivity.newInstance(LivingListActivity.this, mActivityId);
                        break;
                    case 1:
                        mPresenter.httpShareUrl(LivingActivityPresenter.TYPE_H5);
                        break;
                    case 2:
                        mPresenter.httpShareUrl(LivingActivityPresenter.TYPE_QRCODE);
                        break;
                }
            }
        });
    }

    @Override
    protected void getFragmentLists(List<BaseFragment> mFragmentLists) {
        mNormalFragment = LivingListFragment.newInstance(LivingListFragment.TYPE_TIME);
        mHotFragment = LivingListFragment.newInstance(LivingListFragment.TYPE_HOT);
        mFragmentLists.add(mNormalFragment);
        mFragmentLists.add(mHotFragment);
    }

    @Override
    protected void getTitleList(List<String> mTitleLists) {
        mTitleLists.add("照片直播");
        mTitleLists.add("热门");
    }


    @Override
    protected LivingActivityPresenter createPresenter() {
        return new LivingActivityPresenter();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getAnchor(MyEvent.SelectorAnchor event) {
        mPresenter.httpAddAnchorData(event.checkList);
    }

    @Override
    public Map<String, String> getAddAnchorParams() {
        Map<String, String> params = new HashMap<>();
        params.put("activityId", mActivityId);
        return params;
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("activityId", mActivityId);
        if (!TextUtils.isEmpty(mRequestTime)) {
            params.put("startTime", mRequestTime);
        }
        switch (mCheckType) {
            case LivingListFragment.TYPE_TIME:
                params.put("sort", "1");
                break;
            case LivingListFragment.TYPE_HOT:
                params.put("sort", "2");
                break;
        }
        params.put("pageIndex", mPageNo + "");
        params.put("pageSize", "18");
        return params;
    }

    @Override
    public void getDataSucc(LivingListBean.DataBean data) {
        if (mSmartRefreshLayout.isRefreshing()) {
            mSmartRefreshLayout.finishRefresh(true);
        }
        if (mPageNo ==1){
            mActionName = data.getActivityName();
            mTvActivityName.setText(data.getActivityName());
            mRequestTime = data.getStartTime();
        }
        mTotalCount = data.getOperationCount().getTotal();

        mTvScanCount.setText(data.getOperationCount().getSeeCount() + "人浏览");

        if (mPageNo == 1) {
            mNormalFragment.getDataFromParent(data.getImageList(), true);
            mHotFragment.getDataFromParent(data.getImageList(), true);
        } else {
            switch (mCheckType) {
                case LivingListFragment.TYPE_TIME:
                    mNormalFragment.getDataFromParent(data.getImageList(), false);
                    break;
                case LivingListFragment.TYPE_HOT:
                    mHotFragment.getDataFromParent(data.getImageList(), false);
                    break;
            }
        }
    setCount();
    }

    private void setCount(){
        int loadCount = 0;
        switch (mCheckType) {
            case LivingListFragment.TYPE_TIME:
                loadCount = mNormalFragment.getLoacCount();
                break;
            case LivingListFragment.TYPE_HOT:
                loadCount = mHotFragment.getLoacCount();
                break;
        }
        mTvCount.setText(loadCount+"/"+mTotalCount);
    }

    @Override
    public void resetHeaderIcon(List<ActivityDetailBean.DataBean.SignUp> list, boolean isFromAdd) {
        if (!isFromAdd && mPageNo != 1) {
            return;
        }
        if (list == null || list.size() == 0) {
            mHeaderView.setVisibility(View.GONE);
        } else {
            mHeaderView.setVisibility(View.VISIBLE);
            mHeaderView.setData(list);
            mHeaderView.setCount(list.size() + "位主播");
        }
    }

    @Override
    public void getBannerSucc(List<String> list) {
        mBanner.setVisibility(View.VISIBLE);
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.setImages(list);
        mBanner.start();
    }

    @Override
    public void requestDataError() {
        if (mSmartRefreshLayout.isRefreshing()) {
            mSmartRefreshLayout.finishRefresh(true);
        }
        mHotFragment.onCloseRefresh();
        mNormalFragment.onCloseRefresh();
    }

    @Override
    public void getShareUrlSucc(String type, String data) {

        switch (type){
            case LivingActivityPresenter.TYPE_H5:
                ShareDialogHelper.shareOthers(LivingListActivity.this,"赛事直播",mActionName,mShareView,data);
                break;
            case LivingActivityPresenter.TYPE_QRCODE:
                ShareDialogHelper.shareOthers(LivingListActivity.this,"","",mShareView,data);

                break;
        }
    }

    @Override
    public int getCurrentPage() {
        return mPageNo;
    }

    @OnClick({R.id.headerView})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.headerView:
                LookAnchorActivity.newInstance(LivingListActivity.this, mPresenter.getUserDatas());
                break;
        }
    }

    public void request(String type, int pageNo) {
        mCheckType = type;
        mPageNo = pageNo;
        mPresenter.httpRequestData();
    }
}
