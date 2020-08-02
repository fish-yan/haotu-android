package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.RecordBean;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by jockie on 2018/2/2
 * Email:jockie911@gmail.com
 */

public class TailorRecordAdapter extends BaseRvAdapter<RecordBean.DataBean>{

    /**
     * @param context
     * @param layoutId
     * @param datas    不能为null
     */
    public TailorRecordAdapter(Context context, int layoutId, List<RecordBean.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, RecordBean.DataBean recordBean, int position) {
        holder.setText(R.id.tv_attention,recordBean.getName());
    }
}
