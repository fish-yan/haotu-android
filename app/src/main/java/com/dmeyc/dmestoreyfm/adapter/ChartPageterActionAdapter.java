package com.dmeyc.dmestoreyfm.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.ActionDetailRountBean;
import com.dmeyc.dmestoreyfm.bean.CommNoticeBean;
import com.dmeyc.dmestoreyfm.ui.ActionItemActivity;
import com.dmeyc.dmestoreyfm.ui.CourseItemActivity;
import com.dmeyc.dmestoreyfm.ui.HasInActionMemBerActivity;
import com.dmeyc.dmestoreyfm.ui.PkResultActivity;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;

import java.util.List;

public class ChartPageterActionAdapter extends PagerAdapter {
    private  ActionDetailRountBean commNoticeBean;
    private TextView tv_adress;
    private TextView tv_destance;
    private TextView tv_pktime;
    private TextView personcountin,tv_persontal;
    private Context context;


    RecyclerView rv_member;
    LinearLayout ll_mumberitem,ll_pageritem;
    int position1;
    public ChartPageterActionAdapter(ActionDetailRountBean commNoticeBean, Context context){
        this.commNoticeBean=commNoticeBean;
        this.context=context;
    }


    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = View.inflate(container.getContext(), R.layout.adapter_chartpageritem, null);
        if(commNoticeBean.getData().size()>0){
            if(position>=commNoticeBean.getData().size()){
                position1=(position %commNoticeBean.getData().size());
            }else {
                position1=position;
            }

            rv_member=view.findViewById(R.id.rv_member);
            ll_mumberitem=view.findViewById(R.id.ll_mumberitem);
            ll_pageritem=view.findViewById(R.id.ll_pageritem);
            ll_pageritem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if( "3".equals(commNoticeBean.getData().get(position1).getGroupType())){
                        if(0==position1){
                            context.startActivity(new Intent(context, CourseItemActivity.class).putExtra("activityid", commNoticeBean.getData().get(commNoticeBean.getData().size()-1).getActivityId()));
                        }else {
                            context.startActivity(new Intent(context, CourseItemActivity.class).putExtra("activityid", commNoticeBean.getData().get(position1-1).getActivityId()));
                        }
//                        context.startActivity(new Intent(context, CourseItemActivity.class).putExtra("activityid", commNoticeBean.getData().get(position1).getActivityId()));
                    }else {
                        if(0==position1){
                            context.startActivity(new Intent(context, ActionItemActivity.class).putExtra("activityid", commNoticeBean.getData().get(commNoticeBean.getData().size()-1).getActivityId()));
                        }else {
                            context.startActivity(new Intent(context, ActionItemActivity.class).putExtra("activityid", commNoticeBean.getData().get(position1-1).getActivityId()));
                        }
                    }
                }
            });
            LinearLayoutManager lm=  new LinearLayoutManager(context);
            lm.setOrientation(OrientationHelper.HORIZONTAL);
            rv_member.setLayoutManager(lm);
            rv_member.setAdapter(new ChartPageterActionAdapter.iconAdapter(commNoticeBean.getData().get(position1).getMembers()));
            tv_adress= view.findViewById(R.id.tv_adress);
            tv_destance=view.findViewById(R.id.tv_destance);
            tv_pktime=view.findViewById(R.id.tv_pktime);
            personcountin=view.findViewById(R.id.personcountin);
            tv_persontal=view.findViewById(R.id.tv_persontal);
            tv_adress.setText(commNoticeBean.getData().get(position1).getAddress());
            tv_destance.setText(commNoticeBean.getData().get(position1).getDistance());
            tv_pktime.setText(commNoticeBean.getData().get(position1).getStartTime());
            personcountin.setText(commNoticeBean.getData().get(position1).getSignupNumber()+"");
//            personcountin.setText(commNoticeBean.getData().get(position1).getSignupNumber()+"");
            tv_persontal.setText("/"+commNoticeBean.getData().get(position1).getTotalNumber()+"");
        }else {
        }
        ll_mumberitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(commNoticeBean.getData().get(position1).getSignupNumber()==0){
                ToastUtil.show("暂无报名");
                return;
            }else {
                context. startActivity(new Intent(context,HasInActionMemBerActivity.class).putExtra("activityid",commNoticeBean.getData().get(position %commNoticeBean.getData().size()).getActivityId()).putExtra("isallban",""));

            }
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        container.removeView((View) view);
    }
    class iconAdapter extends RecyclerView.Adapter<ChartPageterActionAdapter.iconAdapter.ViewHolder>{
        List<ActionDetailRountBean.DataBean.MembersBean> membersBean;
        public iconAdapter(List<ActionDetailRountBean.DataBean.MembersBean> membersBean){
            this.membersBean=membersBean;
        }
        @Override
        public ChartPageterActionAdapter.iconAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view =((Activity)context).getLayoutInflater().inflate(R.layout.adapter_member,null);
            return new ChartPageterActionAdapter.iconAdapter.ViewHolder(view);
        }
        @Override
        public void onBindViewHolder(ChartPageterActionAdapter.iconAdapter.ViewHolder holder, int position) {
            GlideUtil.loadImage(context,membersBean.get(position).getUrl(),holder.civ_avatar);
            holder.civ_avatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context. startActivity(new Intent(context,HasInActionMemBerActivity.class).putExtra("activityid",commNoticeBean.getData().get(position1).getActivityId()).putExtra("isallban",""));
                }
            });
        }
        @Override
        public int getItemCount() {
            return membersBean.size();
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            public CircleImageView civ_avatar;
            //            private RoundedImageView rimag;
//            private TextView tv_titl;
            public ViewHolder(View itemView) {
                super(itemView);
                civ_avatar=itemView.findViewById(R.id.civ_avatar);
////                recycleview1= (RecyclerView) itemView.findViewById(R.id.recycleview1);
//                rimag= (RoundedImageView) itemView.findViewById(R.id.iv_roundmage);
//                tv_titl=  itemView.findViewById(R.id.tv_titl);

            }
        }
    }


}
