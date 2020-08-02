package com.dmeyc.dmestoreyfm.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.response.RelationClubBean;
import com.dmeyc.dmestoreyfm.bean.response.RelationShopsBean;

/**
 * create by cxg on 2019/11/30
 */
public class RelationShopsAdapter extends BaseQuickAdapter<RelationShopsBean.DataBean, BaseViewHolder> {
    public RelationShopsAdapter() {
        super(R.layout.adapter_relation_club);
    }


    @Override
    protected void convert(BaseViewHolder helper, RelationShopsBean.DataBean item) {
        if (helper == null || item == null) {
            return;
        }
    }
}
