package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.response.ActivityDetailBean;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;

/**
 * create by cxg on 2019/12/1
 */
public class HeaderIconAdatper extends BaseQuickAdapter<ActivityDetailBean.DataBean.SignUp, BaseViewHolder> {

    public static final String TYPE_ITEM = "TYPE_ITEM";
    public static final String TYPE_MEMBER_LIST = "TYPE_MEMBER_LIST";


    private Context mContext;

    public HeaderIconAdatper(Context context) {
        super(R.layout.adapter_header_icon);
        mContext = context;
    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    @Override
    protected void convert(BaseViewHolder helper, ActivityDetailBean.DataBean.SignUp item) {
        if (helper == null || item == null) {
            return;
        }
        GlideUtil.loadLocalImage(mContext, item.getUrl(), (ImageView) helper.getView(R.id.civ_header));
    }
}
