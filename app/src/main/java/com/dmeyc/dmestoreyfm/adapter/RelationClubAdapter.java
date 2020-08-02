package com.dmeyc.dmestoreyfm.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.response.MatchBean;
import com.dmeyc.dmestoreyfm.bean.response.RelationClubBean;

/**
 * create by cxg on 2019/11/30
 */
public class RelationClubAdapter extends BaseQuickAdapter<RelationClubBean.DataBean, BaseViewHolder> {
    public RelationClubAdapter() {
        super(R.layout.adapter_relation_club);
    }


    @Override
    protected void convert(BaseViewHolder helper, RelationClubBean.DataBean item) {
        if (helper == null || item == null) {
            return;
        }
    }
}
