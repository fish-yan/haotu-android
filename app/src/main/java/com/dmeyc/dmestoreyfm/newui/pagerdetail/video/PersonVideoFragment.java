package com.dmeyc.dmestoreyfm.newui.pagerdetail.video;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dmeyc.dmestoreyfm.adapter.VideoPlayListAdapter;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.event.RefreshEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseRefreshFragment;
import com.dmeyc.dmestoreyfm.newui.home.search.searchvideo.ISearchVideoView;
import com.dmeyc.dmestoreyfm.newui.home.search.searchvideo.SearchVideoPresenter;
import com.dmeyc.dmestoreyfm.newui.home.video.VideoDetailActivity;
import com.dmeyc.dmestoreyfm.video.index.IndexDynamicBean;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

/**
 * create by cxg on 2019/12/2
 * 我的 视频、点赞列表
 */
public class PersonVideoFragment extends BaseRefreshFragment<ISearchVideoView, SearchVideoPresenter, IndexDynamicBean.DataBean, VideoPlayListAdapter> implements ISearchVideoView {
    public static final String TYPE_VIDEO_LIST = "TYPE_VIDEO_LIST";
    public static final String TYPE_VIDEO_LIKE = "TYPE_VIDEO_LIKE";

    private String mUserId;
    private String mFromType;

    private boolean needRefresh = false;

    public static PersonVideoFragment newInstance(String videoType, String userId) {
        PersonVideoFragment fragment = new PersonVideoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ExtraKey.VIDEO_LIST_TYPE, videoType);
        bundle.putString(ExtraKey.USER_ID, userId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected VideoPlayListAdapter getAdapter() {
        return new VideoPlayListAdapter(getContext(), null);
    }

    @Override
    protected SearchVideoPresenter createPresenter() {
        return new SearchVideoPresenter();
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(getContext(), 3);
    }

    @Override
    protected void curFragmentVisible() {
        super.curFragmentVisible();
        if (needRefresh || (TYPE_VIDEO_LIKE.equals(mFromType) && !TextUtils.isEmpty(mUserId))) {
            needRefresh = false;
            mPageNo = 1;
            requestData();
        }
    }

    @Override
    protected boolean enableEventBus() {
        return true;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        Bundle bundle = getArguments();
        mUserId = bundle.getString(ExtraKey.USER_ID);
        mFromType = bundle.getString(ExtraKey.VIDEO_LIST_TYPE);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                VideoDetailActivity.newInstance(getContext(), mData, position);
            }
        });

    }

    @Override
    protected boolean enableLoadMore() {
        return false;
    }

    @Override
    protected boolean enableRefresh() {
        return true;
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.httpRequestData(mFromType);
    }

    @Override
    public void requestData() {
        mPresenter.httpRequestData(mFromType);
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
//        本人可以不用传
        if (!TextUtils.isEmpty(mUserId)) {
            params.put("userId", mUserId);
        }
        return params;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void releaseSuccess(RefreshEvent.Release event) {
        switch (event.mType) {
            case RefreshEvent.Release.TYPE_VIDEO:
                if (TYPE_VIDEO_LIST.equals(mFromType)) {
                    needRefresh = true;

                }
                break;
//            case RefreshEvent.Release.TYPE_LIKE:
//                if (TYPE_VIDEO_LIKE.equals(mFromType)) {
//                    needRefresh = true;
//                }
//                break;
        }
    }
}
