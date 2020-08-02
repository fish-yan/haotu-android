package com.dmeyc.dmestoreyfm.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.response.PayDetailBean;
import com.dmeyc.dmestoreyfm.wedgit.CustomItemView;
import com.dmeyc.dmestoreyfm.wedgit.CustomItemView1;

/**
 * create by cxg on 2019/12/22
 */
public class PaySafeAdapter extends BaseQuickAdapter<PayDetailBean.DataBean.SafeListBean, BaseViewHolder> {
    private ICallBack iCallBack;

    public PaySafeAdapter(ICallBack callBack) {
        super(R.layout.adapter_pay_safe);
        iCallBack = callBack;
    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    @Override
    protected void convert(BaseViewHolder helper, final PayDetailBean.DataBean.SafeListBean item) {
        if (helper == null || item == null) {
            return;
        }
        CustomItemView1 civ = (CustomItemView1) helper.getView(R.id.civ1_item_safe);
        civ.setTitle(item.getItemName());
        civ.setTip(item.getExt() + "元/份");

        civ.setListener(new CustomItemView1.OnTextChangeListener() {
            @Override
            public void onTextChange(int nowCount) {
                final int parentPosition = getParentPosition(item);
                mData.get(parentPosition).setBuyCount(nowCount);
                if (iCallBack != null) {
                    iCallBack.onTextCountChanger();
                }
            }
        });
    }

    public interface ICallBack {
        void onTextCountChanger();
    }


}
