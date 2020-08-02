package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.PKListBean;
import com.dmeyc.dmestoreyfm.bean.common.ReviewsBean;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by jockie on 2018/1/4
 * Email:jockie911@gmail.com
 */

public class HeroRankAdapter extends BaseRvAdapter<PKListBean.DataBean.GroupPkListBean> {
    Context context;
    public HeroRankAdapter(Context context, int layoutId, List<PKListBean.DataBean.GroupPkListBean> datas) {
        super(context, layoutId, datas);
        this.context=context;
    }

    @Override
    protected void convert(ViewHolder holder, PKListBean.DataBean.GroupPkListBean bean, final int position) {
        final LinearLayout ll_pk_item = holder.getView(R.id.ll_pk_item);
        TextView tv_cubnam=(TextView) holder.getView(R.id.tv_cubnam);
        tv_cubnam.setText(bean.getGroup_pk_name());
        TextView tv_adress=(TextView) holder.getView(R.id.tv_adress);
        tv_adress.setText(bean.getAddress());
        TextView tv_pktime=(TextView) holder.getView(R.id.tv_pktime);
        tv_pktime.setText(bean.getStart_time()+"-"+(bean.getEnd_time().split(" ")[1]));


        TextView tv_teamonename=(TextView) holder.getView(R.id.tv_teamonename);
        tv_teamonename.setText(bean.getGroup_a_name());
        TextView tv_teanmoneperson=(TextView) holder.getView(R.id.tv_teanmoneperson);
        tv_teanmoneperson.setText(bean.getTotal_no()+"/人");
        TextView tv_teantwonaem=(TextView) holder.getView(R.id.tv_teantwonaem);
        tv_teantwonaem.setText(bean.getGroup_b_name());
        TextView tv_teantwoperson=(TextView) holder.getView(R.id.tv_teantwoperson);
        tv_teantwoperson.setText(bean.getTotal_no()+"/人");


        ImageView iv_teamone=holder.getView(R.id.iv_teamone);
        GlideUtil.loadImage(context,bean.getGroup_a_logo(),iv_teamone);
        ImageView iv_teamtwo=holder.getView(R.id.iv_teamtwo);
        GlideUtil.loadImage(context,bean.getGroup_b_logo(),iv_teamtwo);

        if(ll_pk_item!=null){
            ll_pk_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pkItemClickLisenter.onPkItemClick(position);
                }
            });
        }
    }
    private PkItemClickLisenter pkItemClickLisenter;
    public void  OnPkItemClickLisenter(PkItemClickLisenter pkItemClickLisenter){
        this.pkItemClickLisenter=pkItemClickLisenter;
    }
    public interface PkItemClickLisenter{
        void onPkItemClick(int pos);
    }
}
