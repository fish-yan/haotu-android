package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.BankListBean;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;

/**
 * create by cxg on 2019/11/30
 */
public class BankCardAdapter extends BaseQuickAdapter<BankListBean.DataBean, BaseViewHolder> {
    private Context mContext;
    public BankCardAdapter(Context context) {
        super(R.layout.adapter_creadite_item);
        mContext = context;
    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    @Override
    protected void convert(BaseViewHolder helper, BankListBean.DataBean item) {
        if (helper == null || item == null){
            return;
        }

        helper.setText(R.id.tv_bankname,item.getBank_name())
                .setText(R.id.tv_bank_numb,item.getBank_account());
        GlideUtil.loacImageCenterCrop(mContext,item.getBank_logo(), (ImageView) helper.getView(R.id.iv_bankicon));
    }
}
