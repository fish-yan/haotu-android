package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.PlaceListBean;
import com.dmeyc.dmestoreyfm.bean.TeachListBean;
import com.dmeyc.dmestoreyfm.bean.common.ReviewsBean;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class PlaceAdapter extends BaseRvAdapter<PlaceListBean.DataBean> {
    private Context context;
    public PlaceAdapter(Context context, int layoutId, List<PlaceListBean.DataBean> datas) {
        super(context, layoutId, datas);
        this.context=context;
    }

    @Override
    protected void convert(ViewHolder holder,PlaceListBean.DataBean bean, final int position) {
        CircleImageView civ_avatar=holder.getView(R.id.civ_avatar);
        TextView  tv_teach_name=holder.getView(R.id.tv_teach_name);
        TextView  tv_city=holder.getView(R.id.tv_city);
        TextView  tv_destens=holder.getView(R.id.tv_destens);
        TextView  tv_take=holder.getView(R.id.tv_take);
        TextView tv_averg_price=holder.getView(R.id.tv_averg_price);
        GlideUtil.loadImage(context,bean.getVenue_logo(),civ_avatar);
        tv_teach_name.setText(bean.getVenue_name());
        tv_city.setText("项目： "+bean.getProject_type_name());
        tv_destens.setText("距您："+bean.getDistance());
        tv_take.setText("地点： "+bean.getAddress());
        tv_averg_price.setText("平均消费："+bean.getAverage_amount());
        LinearLayout teachitem = (LinearLayout)holder.getView(R.id.teachitem);
        if(teachitem!=null){
            teachitem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickLisenter.onTeachIntemClick(position);
                }
            });
        }
    }

    private OnTeachItemClickLisenter onItemClickLisenter;

    public void setOnTeachItemClick(OnTeachItemClickLisenter onItemClickLisenter){
        this.onItemClickLisenter=onItemClickLisenter;
    }

    public  interface OnTeachItemClickLisenter{
        void onTeachIntemClick(int pos);
    }
}
