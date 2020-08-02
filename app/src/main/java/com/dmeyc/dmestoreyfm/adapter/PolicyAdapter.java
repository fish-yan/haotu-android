package com.dmeyc.dmestoreyfm.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.ui.ProdListBean;

/**
 * create by cxg on 2019/11/29
 */
public class PolicyAdapter extends BaseQuickAdapter<ProdListBean.DataBean, BaseViewHolder> {
    public PolicyAdapter() {
        super(R.layout.adapter_policy);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProdListBean.DataBean item) {
        if (helper == null || item == null){
            return;
        }

    }
}
