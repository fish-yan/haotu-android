package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dmeyc.dmestoreyfm.bean.response.UserListBean;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.config.CommonConfig;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;

/**
 * create by cxg on 2019/12/9
 */
public class UserListAdapter extends BaseQuickAdapter<UserListBean.DataBean, BaseViewHolder> {
    private Context mContext;

    public UserListAdapter(Context context) {
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
    protected void convert(BaseViewHolder helper, UserListBean.DataBean item) {
        if (helper == null || item == null) {
            return;
        }
        GlideUtil.loadImage(mContext, item.getUserLogo(), ((ImageView) helper.getView(R.id.iv_header)));
        helper.setText(R.id.tv_name, item.getNickName()) ;
        if (Constant.AdapterItemType.TYPE_SEARCH_AUCHOR==item.getLocalType()){
            helper.setText(R.id.tv_follow, item.getLocalRightString());
        }else{
            if (CommonConfig.isFollow(item.getStatus())) {
                helper.setText(R.id.tv_follow, "已关注");
            } else {
                helper.setText(R.id.tv_follow, "关注");
            }
        }

        helper.addOnClickListener(R.id.tv_follow);
    }
}
