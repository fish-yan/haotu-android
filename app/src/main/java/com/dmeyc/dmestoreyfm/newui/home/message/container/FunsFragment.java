package com.dmeyc.dmestoreyfm.newui.home.message.container;

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
import com.dmeyc.dmestoreyfm.newui.pagerdetail.other.OtherDetailActivity;
import com.dmeyc.dmestoreyfm.video.releasedynamic.TopicInEditBean;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

/**
 * create by cxg on 2019/11/30
 * Funs
 */
public class FunsFragment extends BaseRefreshFragment<ISearchUserView, SearchUserPresenter, UserListBean.DataBean, UserListAdapter> implements ISearchUserView {

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

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                OtherDetailActivity.newInstance(getContext(), mData.get(position).getUserId());
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                String status = mData.get(position).getStatus();
                if (view.getId() == R.id.tv_follow) {
                    final String followStatus = CommonConfig.isFollow(status) ? "1" : "0";
                    CommentRequestHelper.httpFollowData(getActivity(), mData.get(position).getUserId(), followStatus, new CommentRequestHelper.CallBackAdapter() {
                        @Override
                        public void onSuccess() {
                            if ("1".equals(followStatus)) {
                                mData.get(position).setStatus("0");
                            } else {
                                mData.get(position).setStatus("1");
                            }
                            mAdapter.notifyItemChanged(position);
                        }
                    });
                }
            }
        });
        mPresenter.httpRequestData(SearchUserPresenter.TYPE_MINE);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public void requestData() {
        mPresenter.httpRequestData(SearchUserPresenter.TYPE_MINE);
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        return params;
    }

}
