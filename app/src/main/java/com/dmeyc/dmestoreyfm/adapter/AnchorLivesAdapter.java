package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.response.AnchorLivesBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;

import java.util.List;

/**
 * create by cxg on 2019/12/26
 */
public class AnchorLivesAdapter extends BaseMultiItemQuickAdapter<AnchorLivesBean.DataBean, BaseViewHolder> {
    private Context mContext;

    public AnchorLivesAdapter(Context context, List<AnchorLivesBean.DataBean> datas) {
        super(datas);
        addItemType(Constant.AdapterItemType.TYPE_LIVING_HOME,R.layout.adapter_living_see);
        addItemType(Constant.AdapterItemType.TYPE_LIVING_RELEASE,R.layout.adapter_anchor_lives);
        mContext = context;
    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    @Override
    protected void convert(BaseViewHolder helper, AnchorLivesBean.DataBean item) {
        if (helper == null || item == null) {
            return;
        }
        switch (helper.getItemViewType()){
            case Constant.AdapterItemType.TYPE_LIVING_RELEASE:
                GlideUtil.loadImage(mContext, item.getSportsPoster(), (ImageView) helper.getView(R.id.imageView));
                helper.setText(R.id.tv_group_name, item.getActivityName())
                        .setText(R.id.tv_time, item.getStartTime())
                        .setText(R.id.tv_address, item.getAddress());
                break;
            case Constant.AdapterItemType.TYPE_LIVING_HOME:
                helper.setText(R.id.tv_count,item.getSeeCount()+"");
                GlideUtil.loadImage(mContext, item.getSportsPoster(), (ImageView) helper.getView(R.id.iv_bg));
                break;
        }

    }
}
