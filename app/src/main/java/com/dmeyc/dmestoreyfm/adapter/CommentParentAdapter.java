package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.response.CommentParentBean;
import com.dmeyc.dmestoreyfm.newui.common.CommentRequestHelper;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.sqk.emojirelease.EmojiUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * create by cxg on 2019/12/8
 */
public class CommentParentAdapter extends BaseQuickAdapter<CommentParentBean.DataBean.CommentListBean, BaseViewHolder> {
    private Context mContext;

    private int mCheckPosition = 0;

    public CommentParentAdapter(Context context) {
        super(R.layout.adapter_comment_parent);
        mContext = context;
    }


    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    @Override
    protected void convert(final BaseViewHolder helper, final CommentParentBean.DataBean.CommentListBean item) {
        if (helper == null || item == null) {
            return;
        }
        GlideUtil.loadImage(mContext, item.getUserImageUrl(), (ImageView) helper.getView(R.id.iv_comment_icon));
        helper.setText(R.id.tv_parent_name, item.getUserNickName())
        ;

        try {
            EmojiUtil.handlerEmojiText((TextView) helper.getView(R.id.tv_comment_content), item.getContent(), mContext);
        } catch (IOException e) {
            helper.setText(R.id.tv_comment_content, item.getContent());
        }
        List<CommentParentBean.DataBean.CommentListBean> beanList = item.getCommentListBean();
        if (beanList == null) {
            beanList = new ArrayList<>();
        }
        helper.setText(R.id.tv_time_bottom, item.getCreateTime());

        if (beanList.size() > 0 || item.getCommentNum() > 0) {
            final int moreCommendCount = Math.max(item.getCommentNum(), beanList.size());
            helper.getView(R.id.ll_look_more).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_look_more, "展开" + moreCommendCount + "条回复");
            helper.getView(R.id.ll_look_more).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCheckPosition = getParentPosition(item);
                    item.setOpen(!item.isOpen());
                    if (!item.isOpen()) {
                        helper.setText(R.id.tv_look_more, "展开" + moreCommendCount + "条回复");
                        helper.getView(R.id.rv_comment_child).setVisibility(View.GONE);
                        ((ImageView) helper.getView(R.id.iv_arrow)).setImageResource(R.drawable.icon_arrow_down);
                    } else {
                        helper.setText(R.id.tv_look_more, "收起");
                        ((ImageView) helper.getView(R.id.iv_arrow)).setImageResource(R.drawable.icon_arrow_up);
                        helper.getView(R.id.rv_comment_child).setVisibility(View.VISIBLE);
                        if (item.getCommentListBean() != null) {
                            helper.getView(R.id.rv_comment_child).setVisibility(View.VISIBLE);
                        } else {
                            CommentRequestHelper.httpCommentListData(mContext, item.getVideoId(), item.getId(), 1, new CommentRequestHelper.CallBackAdapter() {
                                @Override
                                public void onQueryListSucc(CommentParentBean.DataBean data) {

                                    List<CommentParentBean.DataBean.CommentListBean> list = new ArrayList<>();
                                    list.addAll(data.getCommentList());
                                    item.setCommentListBean(list);
                                    notifyDataSetChanged();
                                }
                            });
                        }

                    }

                }
            });


        } else {
            helper.getView(R.id.ll_look_more).setVisibility(View.GONE);
        }

        RecyclerView rvChild = helper.getView(R.id.rv_comment_child);
        rvChild.setLayoutManager(new LinearLayoutManager(mContext));
        CommentChildAdapter mAdapter = new CommentChildAdapter(mContext);
        rvChild.setAdapter(mAdapter);
        mAdapter.bindToRecyclerView(rvChild);
        List<CommentParentBean.DataBean.CommentListBean> commentListBean = item.getCommentListBean();
        if (commentListBean != null) {
            mAdapter.replaceData(commentListBean);
        }
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (listener != null) {
                    listener.onItemClick(item.getId(), item.getUserNickName());
                }
            }
        });
    }

    private MyListener listener;

    public void setListener(MyListener listener) {
        this.listener = listener;
    }

    public interface MyListener {
        void onItemClick(String parentId, String childNickName);
    }
}
