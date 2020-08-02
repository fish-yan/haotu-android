package com.dmeyc.dmestoreyfm.newui.common.anchorlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dmeyc.dmestoreyfm.adapter.AnchorLivesAdapter;
import com.dmeyc.dmestoreyfm.bean.response.AnchorLivesBean;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.event.RefreshEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseRefreshFragment;
import com.dmeyc.dmestoreyfm.newui.home.living.LivingListActivity;
import com.dmeyc.dmestoreyfm.newui.release.live.LiveActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

/**
 * create by cxg on 2019/12/26
 * 没有分页
 */
public class AnchorLivesFragment extends BaseRefreshFragment<IAnchorLivesView, AnchorLivesPresenter, AnchorLivesBean.DataBean, AnchorLivesAdapter> implements IAnchorLivesView {

    public static final String TYPE_HOME = "TYPE_HOME";
    public static final String TYPE_LIST = "TYPE_LIST";

    private String mType;
    private String mUserId;

    private boolean needRefresh = false;

    public static AnchorLivesFragment newInstance(String from, String userId) {
        AnchorLivesFragment fragment = new AnchorLivesFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ExtraKey.TYPE_FROM, from);
        bundle.putString(ExtraKey.USER_ID, userId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mType = getArguments().getString(ExtraKey.TYPE_FROM);
        mUserId = getArguments().getString(ExtraKey.USER_ID);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected boolean enableRefresh() {
        return true;
    }

    @Override
    protected boolean enableLoadMore() {
        return false;
    }

    @Override
    protected boolean enableEventBus() {
        return true;
    }

    @Override
    protected void curFragmentVisible() {
        super.curFragmentVisible();
        if (needRefresh){
            needRefresh = false;
            mPageNo = 1;
            requestData();
        }
    }

    @Override
    protected AnchorLivesAdapter getAdapter() {
        return new AnchorLivesAdapter(getContext(), null);
    }

    @Override
    protected AnchorLivesPresenter createPresenter() {
        return new AnchorLivesPresenter();
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        switch (mType) {
            case TYPE_HOME:
                return new GridLayoutManager(getContext(), 3);
            case TYPE_LIST:
                return new GridLayoutManager(getContext(), 2);
            default:
                break;
        }
        return super.getLayoutManager();
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (mType) {
                    case TYPE_HOME:
                        LivingListActivity.newInstance(getContext(), mData.get(position).getActivityId());
                        break;
                    case TYPE_LIST:
                        LiveActivity.newInstance(getContext(), LiveActivity.TYPE_LIVES, mData.get(position).getActivityId(), mData.get(position).getSportsPoster());
                        break;
                }
            }
        });

        if (TYPE_LIST.equals(mType)) {
            reqest();
        }

    }

    @Override
    protected void initData() {
        if (!TYPE_LIST.equals(mType)) {
            reqest();
        }
    }

    @Override
    public void requestData() {
        reqest();
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        if (!TextUtils.isEmpty(mUserId)) {
            params.put("targetUserId", mUserId);
        }
        return params;
    }

    private void reqest() {
        mPresenter.httpRequestData(mType);

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void releaseSuccess(RefreshEvent.Release event) {
        switch (event.mType) {
            case RefreshEvent.Release.TYPE_LIVING:
                if (TYPE_HOME.equals(mType)) {
                    needRefresh = true;
                }
                break;
        }
    }
}
