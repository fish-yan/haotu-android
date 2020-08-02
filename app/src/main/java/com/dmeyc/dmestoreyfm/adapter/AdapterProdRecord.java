package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.TeachListBean;
import com.dmeyc.dmestoreyfm.bean.common.ReviewsBean;
import com.dmeyc.dmestoreyfm.ui.ProdListBean;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class AdapterProdRecord extends BaseRvAdapter<ProdListBean.DataBean> {
    private Context context;
    public AdapterProdRecord(Context context, int layoutId, List<ProdListBean.DataBean> datas) {
        super(context, layoutId, datas);
        this.context=context;
    }

    @Override
    protected void convert(ViewHolder holder, ProdListBean.DataBean bean, final int position) {
       LinearLayout teachitem= holder.getView(R.id.teachitem);
        teachitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickLisenter.onTeachIntemClick(position);
            }
        });
      TextView tv_protime=  holder.getView(R.id.tv_protime);
        tv_protime.setText("保障期限："+bean.getStartDate()+"至"+bean.getEndDate());
        TextView tv_fromclub=  holder.getView(R.id.tv_fromclub);
        tv_fromclub.setText("来自："+bean.getGroupName()+"赠送投保");
      TextView textView=  holder.getView(R.id.tv_state);
      if("1".equals(bean.getStatus())){
          textView.setText("保险未开始");
      }else if("2".equals(bean.getStatus())){
          textView.setText("保障中");
      }else {
          textView.setText("保障结束");
      }
//     LinearLayout ll_top= holder.getView(R.id.ll_top);
//        ll_top.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//        CircleImageView civ_avatar=holder.getView(R.id.civ_avatar);
//        TextView  tv_teach_name=holder.getView(R.id.tv_teach_name);
//        TextView  tv_city=holder.getView(R.id.tv_city);
//        TextView  tv_destens=holder.getView(R.id.tv_destens);
//        TextView  tv_take=holder.getView(R.id.tv_take);
//        TextView tv_adress=holder.getView(R.id.tv_adress);
//        GlideUtil.loadImage(context,bean.getCoach_logo(),civ_avatar);
//        tv_teach_name.setText(bean.getCoach_name());
//        tv_adress.setText("地点："+bean.getAddress());
//        tv_city.setText("项目： "+bean.getProject_type());
//        tv_destens.setText("距您："+bean.getDistance());
//        tv_take.setText("费用： "+bean.getAmount());
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
