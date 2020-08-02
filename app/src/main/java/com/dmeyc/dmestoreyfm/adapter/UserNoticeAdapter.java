package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseView;
import com.dmeyc.dmestoreyfm.bean.response.UserNoticeBean;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;

/**
 * create by cxg on 2019/12/22
 */
public class UserNoticeAdapter extends BaseQuickAdapter<UserNoticeBean.DataBean, BaseViewHolder> {
    private Context mContext;

    public UserNoticeAdapter(Context context) {
        super(R.layout.adapter_user_notice);
        mContext = context;
    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    @Override
    protected void convert(BaseViewHolder helper, UserNoticeBean.DataBean item) {
        if (helper == null || item == null) {
            return;
        }
        switch (item.getType()) {//类型（2：（@我的3：评论我的4：点赞我的）
            case "2":
            case "3":
                helper.setText(R.id.tv_instruction, item.getOperateContent() +"  " + item.getTimeStr());
                break;
            case "4":
                break;
        }
        GlideUtil.loadImage(mContext,item.getUserLogo(), (ImageView) helper.getView(R.id.civ_header));
        GlideUtil.loacImageCenterCrop(mContext,item.getFaceUrl(), (ImageView) helper.getView(R.id.iv_right));
        helper.setText(R.id.tv_name,item.getNickName())
                .setText(R.id.tv_content,item.getContent());
    }

}
