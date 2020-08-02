package com.dmeyc.dmestoreyfm.newui.release.selectanchor;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.UserListAdapter;
import com.dmeyc.dmestoreyfm.bean.response.UserListBean;
import com.dmeyc.dmestoreyfm.config.CommonConfig;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.event.MyEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseRefreshFragment;
import com.dmeyc.dmestoreyfm.newui.common.CommentRequestHelper;
import com.dmeyc.dmestoreyfm.newui.home.search.searchuser.ISearchUserView;
import com.dmeyc.dmestoreyfm.newui.home.search.searchuser.SearchUserPresenter;
import com.dmeyc.dmestoreyfm.video.releasedynamic.TopicInEditBean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by cxg on 2019/11/30
 */
public class SearchAnchorFragment extends BaseRefreshFragment<ISearchUserView, SearchUserPresenter, UserListBean.DataBean, UserListAdapter> implements ISearchUserView {
    private String mSearchStr;
    private String mActivityId;
    public static SearchAnchorFragment newInstance(String activityId) {
        SearchAnchorFragment fragment = new SearchAnchorFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ExtraKey.ACTIVITY_ID, activityId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected UserListAdapter getAdapter() {
        return new UserListAdapter(getContext());
    }

    @Override
    protected SearchUserPresenter createPresenter() {
        return new SearchUserPresenter();
    }

    @Override
    protected boolean enableLoadMore() {
        return true;
    }

    @Override
    protected boolean needSearch() {
        return true;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        mActivityId = getArguments().getString(ExtraKey.ACTIVITY_ID);
        mEtSearch.setHint("搜索主播");
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                if (view.getId() == R.id.tv_follow) {
                    mData.get(position).setCheck(!mData.get(position).isCheck());
                    if (mData.get(position).isCheck()) {
                        mData.get(position).setLocalRightString("已选定");
                    } else {
                        mData.get(position).setLocalRightString("选定");
                    }
                    mAdapter.notifyItemChanged(position);
                }
            }
        });
        request();
    }


    @Override
    public void requestData() {
        request();
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("pageIndex",mPageNo+"");
        params.put("pageSize",PAGE_SIZE);
        if (!TextUtils.isEmpty(mSearchStr)) {
            params.put("keyword", mSearchStr);
        }
        if (mActivityId!=null){
            params.put("activityId",mActivityId);
        }
        return params;
    }

    @Override
    protected void onSearch(String searchStr) {
        mPageNo = 1;
        mSearchStr = searchStr;
        request();
    }

    private void request() {
        mPresenter.httpRequestData(SearchUserPresenter.TYPE_SEARCH_ANCHOR);
    }

    public List<UserListBean.DataBean> getCheckList() {
        List<UserListBean.DataBean> list = new ArrayList<>();
        for (UserListBean.DataBean bean : mData) {
            if (bean.isCheck()) {
                list.add(bean);
            }
        }
        return list;
    }
}
