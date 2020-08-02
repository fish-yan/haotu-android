package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.response.UserListBean;
import com.dmeyc.dmestoreyfm.constant.Constant;

import java.util.List;

/**
 * create by cxg on 2019/12/30
 */
public class LivingAdapter extends BaseMultiItemQuickAdapter<UserListBean.DataBean, BaseViewHolder> {
    private Context mContext;
    public LivingAdapter(Context context,List<UserListBean.DataBean> data) {
        super(data);
        addItemType(Constant.AdapterItemType.TYPE_LIVING_HOME, R.layout.item_video_play);

        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, UserListBean.DataBean item) {
        if (helper == null || item == null){
            return;
        }

    }
}
