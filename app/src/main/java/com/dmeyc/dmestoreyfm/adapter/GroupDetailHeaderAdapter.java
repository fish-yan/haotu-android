package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.MemberListBean;
import com.dmeyc.dmestoreyfm.bean.NewMemberListBean;
import com.dmeyc.dmestoreyfm.bean.response.GroupMemberBean;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;

/**
 * create by cxg on 2019/12/17
 */
public class GroupDetailHeaderAdapter extends BaseQuickAdapter<NewMemberListBean.DataBean.ListBean, BaseViewHolder> {
    private Context mContext;

    public GroupDetailHeaderAdapter(Context context) {
        super(R.layout.adapter_member_icon);
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
        GlideUtil.loacImageCenterCrop(mContext, item.getHead_icon(), (ImageView) helper.getView(R.id.iv_header_icon));
        helper.setText(R.id.tv_name, item.getNick_name());
    }
}
