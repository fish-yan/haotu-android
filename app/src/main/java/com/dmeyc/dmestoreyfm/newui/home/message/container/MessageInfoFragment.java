package com.dmeyc.dmestoreyfm.newui.home.message.container;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dmeyc.dmestoreyfm.adapter.UserNoticeAdapter;
import com.dmeyc.dmestoreyfm.bean.response.UserNoticeBean;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.newbase.BaseRefreshFragment;
import com.dmeyc.dmestoreyfm.newui.common.container.ContainerActivity;
import com.dmeyc.dmestoreyfm.newui.home.video.VideoDetailActivity;
import com.dmeyc.dmestoreyfm.video.index.IndexDynamicBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * create by cxg on 2019/12/22
 */
public class MessageInfoFragment extends BaseRefreshFragment<IMessageInfoView, MessageInfoPresenter, UserNoticeBean.DataBean, UserNoticeAdapter> implements IMessageInfoView {


    private String mType;
    private String mVideoId;

    public static MessageInfoFragment newInstance(String type) {
        MessageInfoFragment fragment = new MessageInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ExtraKey.TYPE_FROM, type);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected boolean enableRefresh() {
        return true;
    }

    @Override
    protected UserNoticeAdapter getAdapter() {
        return new UserNoticeAdapter(getContext());
    }

    @Override
    protected MessageInfoPresenter createPresenter() {
        return new MessageInfoPresenter();
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        mType = getArguments().getString(ExtraKey.TYPE_FROM);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mVideoId = mData.get(position).getVideoId();
                mPresenter.httpVideoInfo();
            }
        });
        mPresenter.httpRequestData();
    }

    @Override
    public void requestData() {
        mPresenter.httpRequestData();
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        String type = "";
        switch (mType) {
            case ContainerActivity.TYPE_MESSAGE_AT_ME:
                type = "2";
                break;
            case ContainerActivity.TYPE_MESSAGE_LIKE:
                type = "4";
                break;
            case ContainerActivity.TYPE_MESSAGE_COMMON:
                type = "3";
                break;
        }
        params.put("page",String.valueOf(mPageNo));
        params.put("size",PAGE_SIZE);
        params.put("type", type);
        return params;
    }

    @Override
    public String getVideoDetailParams() {
        return mVideoId;
    }

    @Override
    public void getVideoDetailSucc(List<IndexDynamicBean.DataBean> list) {
        VideoDetailActivity.newInstance(getContext(), list, 0);
    }
}
