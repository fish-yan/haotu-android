package com.dmeyc.dmestoreyfm.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.dmeyc.dmestoreyfm.R;

/**
 * create by cxg on 2019/12/2
 */
public class SearchHistoryAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public SearchHistoryAdapter() {
        super(R.layout.adapter_search_history);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        if (helper == null || item == null) {
            return;
        }
        helper.setText(R.id.tv_record, item);
        helper.addOnClickListener(R.id.iv_delete);
    }
}
