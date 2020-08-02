package com.dmeyc.dmestoreyfm.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.ContactBean;

import java.util.List;

/**
 * create by cxg on 2019/12/15
 */
public class ContactAdapter extends BaseQuickAdapter<ContactBean, BaseViewHolder> {
    public ContactAdapter() {
        super(R.layout.adapter_contact);
    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    @Override
    protected void convert(BaseViewHolder helper, ContactBean item) {
        if (helper == null || item == null) {
            return;
        }
        helper.setText(R.id.tv_contact, item.name);
    }
}
