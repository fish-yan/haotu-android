package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.NewMemberListBean;
import com.dmeyc.dmestoreyfm.config.CommonConfig;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;

/**
 * create by cxg on 2019/12/9
 */
public class MemberAdapter extends BaseQuickAdapter<NewMemberListBean.DataBean.ListBean, BaseViewHolder> {
    private Context mContext;

    public MemberAdapter(Context context) {
        super(R.layout.adapter_user);
        mContext = context;
    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    @Override
    protected void convert(BaseViewHolder helper, NewMemberListBean.DataBean.ListBean item) {
        if (helper == null || item == null) {
            return;
        }
        GlideUtil.loadImage(mContext, item.getHead_icon(), ((ImageView) helper.getView(R.id.iv_header)));
        helper.setText(R.id.tv_name, item.getNick_name());
        if (CommonConfig.isFollow(item.getIs_follower())) {
            helper.setText(R.id.tv_follow, "已关注");
        } else {
            helper.setText(R.id.tv_follow, "关注");
        }
        helper.addOnClickListener(R.id.tv_follow);
    }
}
