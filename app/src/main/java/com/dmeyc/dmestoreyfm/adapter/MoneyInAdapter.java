package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.MoneyManagerBean;
import com.dmeyc.dmestoreyfm.bean.common.ReviewsBean;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class MoneyInAdapter extends BaseRvAdapter<MoneyManagerBean.DataBean> {

    public MoneyInAdapter(Context context, int layoutId, List<MoneyManagerBean.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, MoneyManagerBean.DataBean bean, final int position) {
      TextView tv_time=(TextView) holder.getView(R.id.tv_time);
        TextView tv_projecttype=(TextView) holder.getView(R.id.tv_projecttype);
        TextView tv_moneyname=(TextView) holder.getView(R.id.tv_moneyname);
        TextView tv_money=(TextView) holder.getView(R.id.tv_money);

        tv_time.setText(bean.getCreate_time());
        tv_projecttype.setText(bean.getProject_type());
        tv_moneyname.setText(bean.getNick_name());
        tv_money.setText("+"+bean.getAmount());
//        LinearLayout teachitem = (LinearLayout)holder.getView(R.id.teachitem);
//        if(teachitem!=null){
//            teachitem.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onItemClickLisenter.onTeachIntemClick(position);
//                }
//            });
//        }



    }

    private OnTeachItemClickLisenter onItemClickLisenter;

    public void setOnTeachItemClick(OnTeachItemClickLisenter onItemClickLisenter){
        this.onItemClickLisenter=onItemClickLisenter;
    }

    public  interface OnTeachItemClickLisenter{
        void onTeachIntemClick(int pos);
    }
}
