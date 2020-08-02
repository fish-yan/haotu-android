package com.dmeyc.dmestoreyfm.video.releasedynamic.associated;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class AssociatedRecyclerviewAdapter extends CommonAdapter<AssociatedBean.DataBean> {

    public AssociatedRecyclerviewAdapter(Context context, int layoutId, List<AssociatedBean.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, AssociatedBean.DataBean bean, int position) {
        TextView tv_title = holder.getView(R.id.tv_title);
        TextView tv_distance = holder.getView(R.id.tv_distance);
        if(!TextUtils.isEmpty(bean.getName())){
            tv_title.setText(bean.getName());
        }else{
            tv_title.setText("");
        }
        if(!TextUtils.isEmpty(bean.getDistance())){
            tv_distance.setText(bean.getDistance());
        }else{
            tv_distance.setText("");
        }
        if(bean.isSelected()){
            tv_title.setTextColor(Color.parseColor("#32aaff"));
        }else{
            tv_title.setTextColor(Color.parseColor("#111111"));
        }
    }
}
