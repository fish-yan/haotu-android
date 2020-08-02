package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dmeyc.dmestoreyfm.bean.response.ActivityDetailBean;
import com.dmeyc.dmestoreyfm.bean.response.GroupActionBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.wedgit.HeaderIconView;

import java.util.ArrayList;
import java.util.List;

/**
 * create by cxg on 2019/12/1
 */
public class GroupActionAdapter extends BaseMultiItemQuickAdapter<GroupActionBean.DataBean, BaseViewHolder> {
    private Context mContext;

    public GroupActionAdapter(Context context, List<GroupActionBean.DataBean> data) {
        super(data);
        addItemType(Constant.AdapterItemType.MATCH, R.layout.adapter_match);
        addItemType(Constant.AdapterItemType.ACTION, R.layout.adapter_match);
        mContext = context;
    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    @Override
    protected void convert(BaseViewHolder helper, GroupActionBean.DataBean item) {
        if (helper == null || item == null) {
            return;
        }
        helper.setText(R.id.tv_match_name, item.getActivityName())
                .setText(R.id.tv_address, item.getActivityAddress())
                .setText(R.id.tv_time, "时间:" + item.getStart_date())// TODO 时间格式
                .setText(R.id.tv_address, item.getActivityAddress())
                .setText(R.id.tv_distance, item.getDistance());

        HeaderIconView headerIconView = helper.getView(R.id.headerIconView);
        if ("3".equals(item.getStatus())) {
            helper.getView(R.id.iv_state).setVisibility(ImageView.VISIBLE);
        } else {
            helper.getView(R.id.iv_state).setVisibility(ImageView.GONE);
        }
        if ("0".equals(item.getIsPk())) {
            helper.getView(R.id.tv_tip).setVisibility(View.INVISIBLE);
            headerIconView.setVisibility(View.VISIBLE);
            headerIconView.setData(item.getSign_up_list());
            int count = 0;
            if (item.getSign_up_list()!=null){
                count = item.getSign_up_list().size();
            }
            headerIconView.setCount(count+"人报名");
        } else if ("2".equals(item.getIsPk())) {
            helper.getView(R.id.tv_tip).setVisibility(View.VISIBLE);
            headerIconView.setVisibility(View.GONE);
        }
        GlideUtil.loadImage(mContext, item.getGroup_logo(), (ImageView) helper.getView(R.id.civ_icon));
    }


}
