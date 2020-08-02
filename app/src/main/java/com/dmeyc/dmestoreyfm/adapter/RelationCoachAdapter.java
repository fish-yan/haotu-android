package com.dmeyc.dmestoreyfm.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.response.RelationClubBean;
import com.dmeyc.dmestoreyfm.bean.response.RelationCoachBean;

/**
 * create by cxg on 2019/11/30
 */
public class RelationCoachAdapter extends BaseQuickAdapter<RelationCoachBean.DataBean, BaseViewHolder> {
    public RelationCoachAdapter() {
        super(R.layout.adapter_relation_coach);
    }


    @Override
    protected void convert(BaseViewHolder helper, RelationCoachBean.DataBean item) {
        if (helper == null || item == null) {
            return;
        }
    }
}
