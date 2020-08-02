package com.dmeyc.dmestoreyfm.newui.release.friend;

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

import java.util.HashMap;
import java.util.Map;

/**
 * create by cxg on 2019/11/30
 * At friend
 */
public class AtFriendFragment extends BaseRefreshFragment<ISearchUserView, SearchUserPresenter, UserListBean.DataBean, UserListAdapter> implements ISearchUserView {
//    public static final String TYPE_SEARCH_FRIEND = "TYPE_SEARCH_FRIEND";
//    public static final String TYPE_SEARCH_ANCHOR = "TYPE_SEARCH_ANCHOR";
    private String mSearchStr;

//    public static SearchAnchorFragment newInstance(String type) {
//        SearchAnchorFragment fragment = new SearchAnchorFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString(ExtraKey.TYPE_FROM, type);
//        fragment.setArguments(bundle);
//        return fragment;
//    }

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
        return false;
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
        mEtSearch.setHint("搜索好友");
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TopicInEditBean bean = new TopicInEditBean();
                bean.setType(TopicInEditBean.TYPE_SYSTEM_FRIEND);
                bean.setName(mData.get(position).getNickName());
                bean.setObjectText(mData.get(position).getNickName());
                bean.setTopicId(Integer.valueOf(mData.get(position).getUserId()));
                bean.setObjectRule("@");
                EventBus.getDefault().post(new MyEvent.ReleaseVideo(bean));
                getActivity().finish();
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
    }

    @Override
    protected void initData() {
        super.initData();
        request();
    }

    @Override
    public void requestData() {

    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        if (!TextUtils.isEmpty(mSearchStr)) {
            params.put("keyword", mSearchStr);
        }
        return params;
    }

    @Override
    protected void onSearch(String searchStr) {
        mSearchStr = searchStr;
        request();
    }

    private void request() {
        mPresenter.httpRequestData(SearchUserPresenter.TYPE_MINE);

    }
}
