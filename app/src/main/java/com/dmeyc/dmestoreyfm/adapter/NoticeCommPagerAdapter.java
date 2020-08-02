package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.CommNoticeBean;
import com.dmeyc.dmestoreyfm.ui.ActionItemActivity;
import com.dmeyc.dmestoreyfm.ui.NoticeShowActivity;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import java.util.ArrayList;

public class NoticeCommPagerAdapter extends PagerAdapter {
    private  CommNoticeBean bean;
    ArrayList<CommNoticeBean.DataBean.ActivityListBean> commNoticeBean;
    private TextView test,tv_sigup;
    private TextView tv_person;
    private TextView tv_state;
    private LinearLayout ll_pageritem;
    private Context context;

    int position1;
//    public NoticeCommPagerAdapter(CommNoticeBean commNoticeBean, Context context){
//        this.commNoticeBean=commNoticeBean;
//        this.context=context;
//    }
    public NoticeCommPagerAdapter(ArrayList<CommNoticeBean.DataBean.ActivityListBean> commNoticeBean, Context context,CommNoticeBean bean){
        this.commNoticeBean=commNoticeBean;
        this.context=context;
        this.bean=bean;
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
        View view = View.inflate(container.getContext(), R.layout.pageritem_commnotice, null);
        test= view.findViewById(R.id.test);
        tv_sigup=view.findViewById(R.id.tv_sigup);
        ll_pageritem=view.findViewById(R.id.ll_pageritem);
        if(TextUtils.isEmpty(bean.getData().getNotice())){
                  position1=position % commNoticeBean.size();
                String  timeyear= commNoticeBean.get(position1).getStart_time().split(" ")[0];
                String tiemday=timeyear.split("-")[1]+"月"+timeyear.split("-")[2]+"日";
                String timehor[]=commNoticeBean.get(position1).getStart_time().split(" ")[1].split(":");
                test.setText(tiemday+"  "+timehor[0]+":"+timehor[1]+commNoticeBean.get(position1).getVenueName());
                tv_sigup.setText(commNoticeBean.get(position1).getSign_up_no()+"人报名");
        }else {
            position1=position % commNoticeBean.size();
            if(0==position1){
                test.setText(commNoticeBean.get(position1).getActivityName());
               tv_sigup.setVisibility(View.GONE);
            }else {
                String  timeyear= commNoticeBean.get(position1).getStart_time().split(" ")[0];
                String tiemday=timeyear.split("-")[1]+"月"+timeyear.split("-")[2]+"日";
                String timehor[]=commNoticeBean.get(position1).getStart_time().split(" ")[1].split(":");
                test.setText(tiemday+"  "+timehor[0]+":"+timehor[1]+commNoticeBean.get(position1).getVenueName());
                tv_sigup.setText(commNoticeBean.get(position1).getSign_up_no()+"人报名");
            }
        }

        ll_pageritem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(bean.getData().getNotice())){
                    if(0==position1){
                        context.startActivity(new Intent(context,ActionItemActivity.class).putExtra("activityid",commNoticeBean.get(commNoticeBean.size()-1).getActivity_id()));
                    }else {
                        context.startActivity(new Intent(context,ActionItemActivity.class).putExtra("activityid",commNoticeBean.get(position1-1).getActivity_id()));
//                                context.startActivity(new Intent(context,ActionItemActivity.class).putExtra("activityid",commNoticeBean.get(commNoticeBean.size()-position1).getActivity_id()));
                    }

//                    context.startActivity(new Intent(context,ActionItemActivity.class).putExtra("activityid",commNoticeBean.get(position1).getActivity_id()));
                }else {
                    if(commNoticeBean.size()==1){
                            context.startActivity(new Intent(context,NoticeShowActivity.class).putExtra("notice",commNoticeBean.get(0).getActivityName()));
                    }else {
                        if(1==position1){
                            context.startActivity(new Intent(context,NoticeShowActivity.class).putExtra("notice",commNoticeBean.get(0).getActivityName()));
                        }else {
                            if(0==position1){
                                context.startActivity(new Intent(context,ActionItemActivity.class).putExtra("activityid",commNoticeBean.get(commNoticeBean.size()-1).getActivity_id()));
                            }else {
                                context.startActivity(new Intent(context,ActionItemActivity.class).putExtra("activityid",commNoticeBean.get(position1-1).getActivity_id()));
//                                context.startActivity(new Intent(context,ActionItemActivity.class).putExtra("activityid",commNoticeBean.get(commNoticeBean.size()-position1).getActivity_id()));
                            }
                        }
                    }
                }
            }
        });

//        tv_person=view.findViewById(R.id.tv_person);
//        tv_state=view.findViewById(R.id.tv_state);
//        ll_pageritem=view.findViewById(R.id.ll_pageritem);

//                  rv_noteice.setAdapter (new ConversationActivity.NoticeAdapter());
//                  ToastUtil.show(bean.getMsg());
//                test.requestFocus();
//                TranslateAnimation translateAnimation = new TranslateAnimation(
//                        //X轴初始位置
//                        Animation.RELATIVE_TO_SELF, 1.0f,
//                        //X轴移动的结束位置
//                        Animation.RELATIVE_TO_SELF,0.0f,
//                        //y轴开始位置
//                        Animation.RELATIVE_TO_SELF,0.0f,
//                        //y轴移动后的结束位置
//                        Animation.RELATIVE_TO_SELF,0.0f);
//                //3秒完成动画
//                translateAnimation.setDuration(10000);
//                translateAnimation.setRepeatCount(10000);
//                test.startAnimation(translateAnimation);


//                test.setText(commNoticeBean.getData().getActivity_notice_list().get(0).getStart_time());
//                tv_person.setText(commNoticeBean.getData().getActivity_notice_list().get(0).getSign_up_no()+"/"+commNoticeBean.getData().getActivity_notice_list().get(0).getTotal_no());
//                if("1".equals(commNoticeBean.getData().getActivity_notice_list().get(0).getStatus())){
//                    tv_state.setText("正在报名");
//                }else {
//                    tv_state.setText("报名结束");
//                }
//        ll_pageritem.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                context.startActivity(new Intent(context,ActionItemActivity.class).putExtra("activityid",commNoticeBean.getData().getActivity_notice_list().get(0).getActivity_id()));
//            }
//        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
         container.removeView((View) view);
    }
}
