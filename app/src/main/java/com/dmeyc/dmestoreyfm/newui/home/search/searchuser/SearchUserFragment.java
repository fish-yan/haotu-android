package com.dmeyc.dmestoreyfm.newui.home.search.searchuser;

import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dmeyc.dmestoreyfm.adapter.UserListAdapter;
import com.dmeyc.dmestoreyfm.bean.response.UserListBean;
import com.dmeyc.dmestoreyfm.config.CommonConfig;
import com.dmeyc.dmestoreyfm.newbase.BaseRefreshFragment;

import java.util.HashMap;
import java.util.Map;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.newui.common.CommentRequestHelper;
import com.dmeyc.dmestoreyfm.newui.pagerdetail.other.OtherDetailActivity;
import com.dmeyc.dmestoreyfm.utils.CommonUtil;

/**
 * create by cxg on 2019/12/9
 */
public class SearchUserFragment extends BaseRefreshFragment<ISearchUserView, SearchUserPresenter, UserListBean.DataBean, UserListAdapter> implements ISearchUserView {
    private String mSearchStr = "";

    @Override
    protected UserListAdapter getAdapter() {
        return new UserListAdapter(getContext());
    }

    @Override
    protected SearchUserPresenter createPresenter() {
        return new SearchUserPresenter();
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                String status = mData.get(position).getStatus();
                if (view.getId() == R.id.tv_follow) {
                    if (!CommonUtil.isLogin(getContext())) {
                        return;
                    }
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

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                OtherDetailActivity.newInstance(getContext(),mData.get(position).getUserId());
            }
        });
    }

    @Override
    public void requestData() {
        mPresenter.httpRequestData(SearchUserPresenter.TYPE_SEARCH);
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("pageIndex", mPageNo + "");
        params.put("pageSize", PAGE_SIZE);
        if (!TextUtils.isEmpty(mSearchStr)) {
            params.put("keyword", mSearchStr);
        }
        return params;
    }

    public void doSearch(String searchString, boolean request) {
        mSearchStr = searchString;
        mPageNo = 1;
        if (request) {
            mPresenter.httpRequestData(SearchUserPresenter.TYPE_SEARCH);
        }
    }
}
