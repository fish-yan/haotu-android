package com.dmeyc.dmestoreyfm.newui.home.groupaction;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dmeyc.dmestoreyfm.adapter.GroupActionAdapter;
import com.dmeyc.dmestoreyfm.bean.response.GroupActionBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.event.MyEvent;
import com.dmeyc.dmestoreyfm.event.RefreshEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseRefreshFragment;
import com.dmeyc.dmestoreyfm.newui.home.groupaction.actiondetail.ActionDetailActivity;
import com.dmeyc.dmestoreyfm.newui.home.groupaction.matchdetail.MatchDetailActivity;
import com.dmeyc.dmestoreyfm.newui.home.living.LivingListActivity;
import com.dmeyc.dmestoreyfm.newui.release.live.LiveActivity;
import com.dmeyc.dmestoreyfm.utils.CommonUtil;
import com.dmeyc.dmestoreyfm.utils.DateUtil;
import com.dmeyc.dmestoreyfm.utils.DensityUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.wedgit.SpaceItemDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * create by cxg on 2019/12/1
 */
public class GroupActionFragment extends BaseRefreshFragment<IGroupActionView, GroupActionPresenter, GroupActionBean.DataBean, GroupActionAdapter> implements IGroupActionView {

    // 首页搜索 活动
    public static final String FROM_GROUP_ACTION = "1";
    //首页搜索 赛事
    public static final String FROM_GROUP_MATCH = "2";
    //首页搜索 全部
    public static final String FROM_GROUP_ALL = "3";

    // 首页我的活动
    public static final String FROM_MINE_ACTION = "4";
    // 关联
    public static final String FROM_RELATION = "5";

    public static final String FROM_MENU_ACTION = "6";
    public static final String FROM_MENU_MATCH = "7";

    public static final String FROM_SELECTOR_MATCH = "8";//选择赛事

    private String mFrom;

    private String mSelectorDates = "";

    private String mSearchStr = "";

    private boolean needRefresh = false;

    public static GroupActionFragment newInstance(String groupActionFrom) {
        GroupActionFragment fragment = new GroupActionFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ExtraKey.GROUP_ACTION_FROM, groupActionFrom);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected void curFragmentVisible() {
        super.curFragmentVisible();
        if (needRefresh) {
            needRefresh = false;
            mPresenter.httpRequestData(mFrom);
        }
    }

    @Override
    protected GroupActionAdapter getAdapter() {
        return new GroupActionAdapter(getContext(), new ArrayList<GroupActionBean.DataBean>());
    }

    @Override
    protected GroupActionPresenter createPresenter() {
        return new GroupActionPresenter();
    }

    @Override
    protected boolean enableLoadMore() {
        return true;
    }

    @Override
    protected boolean enableRefresh() {
        return true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mFrom = getArguments().getString(ExtraKey.GROUP_ACTION_FROM);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected boolean needSearch() {
        // 关联时候需要搜索
        return FROM_RELATION.equals(mFrom);
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(DensityUtil.dip2px(10)));
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                if (!CommonUtil.isLogin(getContext())) {
                    return;
                }

                GroupActionBean.DataBean bean = (GroupActionBean.DataBean) adapter.getData().get(position);

                switch (mFrom) {
                    case FROM_RELATION:
                        EventBus.getDefault().post(new MyEvent.ReleaseRelation(bean.getActivity_id(), bean.getGroup_name(), "3"));
                        ((Activity) mContent).finish();
                        break;
                    case FROM_SELECTOR_MATCH:
                        mPresenter.httpActivityDetail(bean.getActivity_id());
                        break;
                    default:
                            if ("0".equals(bean.getIsPk())) {// 活动
                                ActionDetailActivity.newInstance(getContext(), bean.getActivity_id());
                            } else if ("2".equals(bean.getIsPk())) {//赛事
                                if ("2".equals(bean.getStage()) || "3".equals(bean.getStage())){
                                    LivingListActivity.newInstance(getContext(), bean.getActivity_id());
//                                    mPresenter.httpActivityDetail(bean.getActivity_id());
                                }else {
                                    MatchDetailActivity.newInstance(getContext(), bean.getActivity_id());
                                }
                            }

                        break;
                }


            }
        });

        if (FROM_SELECTOR_MATCH.equals(mFrom)) {
            mPresenter.httpRequestData(mFrom);
        }
    }

    @Override
    protected boolean enableEventBus() {
        return true;
    }

    @Override
    protected void initData() {
        mPresenter.httpRequestData(mFrom);
    }

    @Override
    public void requestData() {
        mPresenter.httpRequestData(mFrom);
    }

    @Override
    public Map<String, String> getParams() {
//     type:   群活动状态(0：普通活动 1：pk 2：赛事,99全部)
        // 活动没有分页
        Map<String, String> params = new HashMap<>();

        if (isFromHome()) {//首页搜索
            if (!TextUtils.isEmpty(mSearchStr)) {
                params.put("keyword", mSearchStr);
            }
            params.put("pageSize", PAGE_SIZE);
            params.put("pageIndex", mPageNo + "");
            if (TextUtils.isEmpty(mSelectorDates)) {
                params.put("activity_date", DateUtil.appendDateZero(DateUtil.getYMD(new Date())));
            } else {
                params.put("activity_date", mSelectorDates);
            }
            params.put("longitude", SPUtils.getStringData(Constant.Config.LONGTUDE));
            params.put("latitude", SPUtils.getStringData(Constant.Config.LATITUDE));
            switch (mFrom) {
                case FROM_GROUP_ACTION:
                    params.put("is_group_pk", "0");
                    break;
                case FROM_GROUP_MATCH:
                    params.put("is_group_pk", "2");
                    break;
                case FROM_GROUP_ALL:
                    params.put("is_group_pk", "99");
                    break;
                default:
            }
        } else {
            switch (mFrom) {
                case FROM_MINE_ACTION:
                    params.put("status", "0");
                    params.put("type", "99");
                    break;
                case FROM_RELATION:
                    params.put("keyword", mSearchStr);
                    params.put("pageSize", PAGE_SIZE);
                    params.put("pageIndex", mPageNo + "");
                    break;
                case FROM_MENU_ACTION:
                    params.put("status", "0");
                    params.put("type", "0");
                    break;
                case FROM_MENU_MATCH:
                case FROM_SELECTOR_MATCH:
                    params.put("status", "0");
                    params.put("type", "2");
                    break;
            }
        }


        return params;
    }

    private boolean isFromHome() {
        return FROM_GROUP_ACTION.equals(mFrom) ||
                FROM_GROUP_MATCH.equals(mFrom) ||
                FROM_GROUP_ALL.equals(mFrom);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void selectorTime(MyEvent.SelectorDates event) {
        if (isFromHome()) {
            mSelectorDates = event.mDates;

            if (event.mType == MyEvent.SelectorDates.TYPE_REFRESH) {
                mPageNo = 1;
                mPresenter.httpRequestData(mFrom);
            }
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void releaseSuccess(RefreshEvent.Release event) {
        switch (event.mType) {
            case RefreshEvent.Release.TYPE_GOODS:
                if (FROM_MINE_ACTION.equals(mFrom)) {
                    needRefresh = true;
                }
                break;
        }
    }

    /**
     * 首页搜索
     *
     * @param searchString
     * @param request
     */
    public void doSearch(String searchString, boolean request) {
        mSearchStr = searchString;
        mPageNo = 1;
        if (request) {
            mPresenter.httpRequestData(mFrom);
        }
    }

    @Override
    protected void onSearch(String searchStr) {
        mSearchStr = searchStr;
        mPageNo = 1;
        mPresenter.httpRequestData(mFrom);
    }

    @Override
    public void getActivityImage(String activityId, String sportsPoster) {
        LiveActivity.newInstance(getContext(), LiveActivity.TYPE_MATCH, activityId, sportsPoster);
    }
}
