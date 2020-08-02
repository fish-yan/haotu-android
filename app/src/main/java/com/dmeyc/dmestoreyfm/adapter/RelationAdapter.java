package com.dmeyc.dmestoreyfm.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.response.RelationBean;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;

/**
 * create by cxg on 2019/11/30
 */
public class RelationAdapter extends BaseQuickAdapter<RelationBean.DataBean, BaseViewHolder> {
    public RelationAdapter() {
        super(R.layout.adapter_relation);
    }


    @Override
    protected void convert(BaseViewHolder helper, RelationBean.DataBean item) {
        if (helper == null || item == null) {
            return;
        }
        helper.setText(R.id.tv_relation_content,item.getGroupName());
        // TODO: 2019/12/16
//                .setText(R.id.tv_relation_title,item.);
        GlideUtil.loadImage(mContext,item.getGroupLogo(), (ImageView) helper.getView(R.id.iv_relation_icon));
        
    }
}
