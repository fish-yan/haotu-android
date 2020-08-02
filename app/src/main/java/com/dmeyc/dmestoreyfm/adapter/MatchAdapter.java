package com.dmeyc.dmestoreyfm.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.response.MatchBean;

/**
 * create by cxg on 2019/11/30
 */
public class MatchAdapter extends BaseQuickAdapter<MatchBean.DataBean, BaseViewHolder> {
    public MatchAdapter() {
        super(R.layout.adapter_match);
    }


    @Override
    protected void convert(BaseViewHolder helper, MatchBean.DataBean item) {
        if (helper == null || item == null) {
            return;
        }
    }
}
