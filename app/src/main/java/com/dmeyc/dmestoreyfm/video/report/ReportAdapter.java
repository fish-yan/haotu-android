package com.dmeyc.dmestoreyfm.video.report;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

public class ReportAdapter extends CommonAdapter<ReportBean.DataBean> {

    public ReportAdapter(Context context, int layoutId, List<ReportBean.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, ReportBean.DataBean item, int position) {
        TextView tv = viewHolder.getView(R.id.tv_report_type);
        ImageView iv = viewHolder.getView(R.id.iv_select);
        tv.setText(item.getName());
        if(item.isSelect()){
            iv.setVisibility(View.VISIBLE);
        }else{
            iv.setVisibility(View.GONE);
        }
    }
}
