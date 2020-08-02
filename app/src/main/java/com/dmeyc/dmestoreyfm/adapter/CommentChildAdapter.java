package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.response.CommentParentBean;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.sqk.emojirelease.EmojiUtil;

import java.io.IOException;

/**
 * create by cxg on 2019/12/8
 */
public class CommentChildAdapter extends BaseQuickAdapter<CommentParentBean.DataBean.CommentListBean, BaseViewHolder> {
    private Context mContext;

    public CommentChildAdapter(Context context) {
        super(R.layout.adapter_comment_child);
        mContext = context;
    }


    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    @Override
    protected void convert(BaseViewHolder helper, CommentParentBean.DataBean.CommentListBean item) {
        if (helper == null || item == null) {
            return;
        }
        String content ;
        if (TextUtils.isEmpty(item.getToUserName())){
            content = item.getContent();
        }else {
            content = "回复" + item.getToUserName() +": "+item.getContent() ;
        }

        GlideUtil.loadImage(mContext, item.getUserImageUrl(), (ImageView) helper.getView(R.id.iv_comment_icon));
        helper.setText(R.id.tv_parent_name, item.getUserNickName())
                .setText(R.id.tv_time_bottom, item.getCreateTime());
        try {
            EmojiUtil.handlerEmojiText((TextView) helper.getView(R.id.tv_comment_content), content, mContext);
        } catch (IOException e) {
            helper.setText(R.id.tv_comment_content, item.getContent());
        }
    }
}
