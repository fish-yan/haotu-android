package com.dmeyc.dmestoreyfm.newui.menu.club.clubmember;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.MemberAdapter;
import com.dmeyc.dmestoreyfm.bean.MemberListBean;
import com.dmeyc.dmestoreyfm.bean.NewMemberListBean;
import com.dmeyc.dmestoreyfm.config.CommonConfig;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.newbase.BaseRefreshFragment;
import com.dmeyc.dmestoreyfm.newui.common.CommentRequestHelper;
import com.dmeyc.dmestoreyfm.utils.CommonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * create by cxg on 2019/12/23
 */
public class MemberFragment extends BaseRefreshFragment<IMemberView, MemberPresenter, NewMemberListBean.DataBean.ListBean, MemberAdapter> implements IMemberView {

    public static MemberFragment newInstance(String groupId) {
        MemberFragment memberFragment = new MemberFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ExtraKey.GROUP_ID, groupId);
        memberFragment.setArguments(bundle);
        return memberFragment;
    }

    private String mGroupId;


    @Override
    protected MemberAdapter getAdapter() {
        return new MemberAdapter(getContext());
    }

    @Override
    protected MemberPresenter createPresenter() {
        return new MemberPresenter();
    }

    @Override
    protected boolean enableLoadMore() {
        return false;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        mGroupId = getArguments().getString(ExtraKey.GROUP_ID);

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                String status = mData.get(position).getIs_follower();
                if (view.getId() == R.id.tv_follow) {
                    final String followStatus = CommonConfig.isFollow(status) ? "1" : "0";
                    CommentRequestHelper.httpFollowData(getActivity(), mData.get(position).getUser_id() + "", followStatus, new CommentRequestHelper.CallBackAdapter() {
                        @Override
                        public void onSuccess() {
                            if (CommonConfig.isFollow(followStatus)) {
                                mData.get(position).setIs_follower("0");
                            } else {
                                mData.get(position).setIs_follower("1");
                            }
                            mAdapter.notifyItemChanged(position);
                        }
                    });
                }
            }
        });
        mPresenter.httpMembersData();
    }

    @Override
    public void requestData() {

    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("group_id", mGroupId);
        return params;
    }
}
