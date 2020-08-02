package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.response.ActivityDetailBean;
import com.dmeyc.dmestoreyfm.bean.response.RelationBean;
import com.dmeyc.dmestoreyfm.bean.response.UserListBean;
import com.dmeyc.dmestoreyfm.newui.home.living.lookanchor.LookAnchorActivity;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;

/**
 * create by cxg on 2019/11/30
 */
public class LookAnchorAdapter extends BaseQuickAdapter<ActivityDetailBean.DataBean.SignUp, BaseViewHolder> {
    private Context mContext;

    public LookAnchorAdapter(Context context) {
        super(R.layout.adapter_relation);
        mContext = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, ActivityDetailBean.DataBean.SignUp item) {
        if (helper == null || item == null) {
            return;
        }
        helper.setText(R.id.tv_relation_content, item.getNick_name());
        GlideUtil.loacImageCenterCrop(mContext, item.getUrl(), (ImageView) helper.getView(R.id.iv_relation_icon));

    }
}
