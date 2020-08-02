package com.dmeyc.dmestoreyfm.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.response.MyClubBean;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;

/**
 * create by cxg on 2019/11/29
 */
public class MyClubAdapter extends BaseQuickAdapter<MyClubBean.DataBean, BaseViewHolder> {
    public MyClubAdapter() {
        super(R.layout.adapter_my_club);
    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    @Override
    protected void convert(BaseViewHolder helper, MyClubBean.DataBean item) {
        if (helper ==null || item ==null){
            return;
        }
        GlideUtil.loadImage(mContext,item.getGroup_logo(), ((ImageView) helper.getView(R.id.iv_header)));
        helper.setText(R.id.tv_group_name,item.getGroup_name());
//                .setText(R.id.tv_grouper,item.get)
    }
}
