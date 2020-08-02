package com.dmeyc.dmestoreyfm.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.CheckChatActivity;
import com.dmeyc.dmestoreyfm.bean.CommDetailBean;
import com.dmeyc.dmestoreyfm.bean.FirstActivityListBean;
import com.dmeyc.dmestoreyfm.bean.common.ReviewsBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.ActionItemActivity;
import com.dmeyc.dmestoreyfm.ui.ChartInforActivity;
import com.dmeyc.dmestoreyfm.ui.HasInActionMemBerActivity;
import com.dmeyc.dmestoreyfm.ui.MemberListActivity;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.utils.city.ToastUtils;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.dmeyc.dmestoreyfm.wedgit.RoundAngleImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jockie on 2018/1/4
 * Email:jockie911@gmail.com
 */

public class ActionItemAdapter extends BaseRvAdapter<FirstActivityListBean.DataBean> {

    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;
    public static final int ITEM_TYPE_BOTTOM = 2;
    public static final int ITEM_TYPE_BODY= 3;
    private static List<FirstActivityListBean.DataBean> deata;
    private LinearLayout iv_chat;
    private   LinearLayout linearLayout,ll_top;
    private static Context context;

    int [] arr = {1,2};
    //产生0-(arr.length-1)的整数值,也是数组的索引


    public ActionItemAdapter(Context context, int layoutId, ArrayList<FirstActivityListBean.DataBean> datas) {
        super(context, layoutId, datas);
        this.deata=datas;
        this.context=context;
    }
    @Override
    protected void convert(ViewHolder holder, final FirstActivityListBean.DataBean bean, final int position) {
        ImageView iv_ispk=holder.getView(R.id.iv_ispk);
        if("0".equals(bean.getIsPk())){
            iv_ispk.setVisibility(View.GONE);
        }else {
            iv_ispk.setVisibility(View.VISIBLE);
        }
       ImageView iv_qinzi= holder.getView(R.id.iv_qinzi);
        if("5".equals(bean.getGroup_type())){
            if("2".equals(bean.getSport_type())){
                iv_qinzi.setVisibility(View.VISIBLE);
                iv_qinzi.setBackground(context.getResources().getDrawable(R.drawable.tuan_iocn));
            } else if("1".equals(bean.getSport_type())){
                iv_qinzi.setVisibility(View.VISIBLE);
                iv_qinzi.setBackground(context.getResources().getDrawable(R.drawable.qin_icon));
            }else {
                iv_qinzi.setVisibility(View.GONE);
            }
        }else {
            iv_qinzi.setVisibility(View.GONE);
        }
        ImageView iv_owerlog=holder.getView(R.id.iv_owerlog);
        TextView tv_owename=holder.getView(R.id.tv_owename);
        ImageView iv_intrance=holder.getView(R.id.iv_intrance);
        TextView tv_time=(TextView) holder.getView(R.id.tv_time);
        RoundAngleImageView iv_activitylog=holder.getView(R.id.iv_activitylog);
        RelativeLayout rl_timeback=holder.getView(R.id.rl_timeback);
        RecyclerView rv_listitem= holder.getView(R.id.rv_listitem);
//        TextView tv_tv_click=holder.getView(R.id.tv_tv_click);
        LinearLayout ll_mumberitem=holder.getView(R.id.ll_mumberitem);
        ll_mumberitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bean.getSign_up_no()==0){
                    ToastUtil.show("暂无报名");
                }else {
                    context.startActivity(new Intent(context,HasInActionMemBerActivity.class).putExtra("activityid",bean.getActivity_id()).putExtra("isallban",""));
                }
            }
        });
        LinearLayoutManager lm=  new LinearLayoutManager(context);
        lm.setOrientation(OrientationHelper.HORIZONTAL);
        rv_listitem.setLayoutManager(lm);
        iconAdapter iconAdr= new iconAdapter(bean,position);
        rv_listitem.setAdapter(iconAdr);
        if(bean.getGroup_type().equals("5")){
            if(!TextUtils.isEmpty(bean.getStart_date())){
                String day[]= bean.getStart_date().split(" ")[0].split("-");
                tv_time.setText(day[1]+"-"+day[2]);
            }
            iv_intrance.setVisibility(View.GONE);
            tv_owename.setText(bean.getOwner_name());
            iv_owerlog.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ower_icon));
//            GlideUtil.loadImage(context,R.drawable.ower_icon,iv_owerlog);
        }else if(bean.getGroup_type().equals("4")||bean.getGroup_type().equals("3")||bean.getGroup_type().equals("2")){
            if(!TextUtils.isEmpty(bean.getStart_date())){
                String day[]= bean.getStart_date().split(" ")[1].split(":");
                tv_time.setText(day[0]+":"+day[1]);
            }

            if(bean.getGroup_type().equals("4")){
                iv_intrance.setVisibility(View.VISIBLE);
            }else {
                iv_intrance.setVisibility(View.GONE);
            }

//            GlideUtil.loadImage(context,R.drawable.action_timeicon,iv_owerlog);
            tv_owename.setText("24小时营业");
            iv_owerlog.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ower_icon));
//            iv_owerlog.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.action_timeicon));
            tv_owename.setText(bean.getOwner_name());
        }else if(bean.getGroup_type().equals("1")){
            if(!TextUtils.isEmpty(bean.getStart_date())){
                String day[]= bean.getStart_date().split(" ")[1].split(":");
                tv_time.setText(day[0]+":"+day[1]);
            }
            tv_owename.setText(bean.getOwner_name());
            iv_owerlog.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ower_icon));
            iv_intrance.setVisibility(View.GONE);
        }
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonnts/unit.ttf");
//        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonnts/digital.ttf");
         tv_time.setTypeface(typeface);
//         ||"2".equals(bean.getStatus()
        if("1".equals(bean.getStatus())){
//            tv_time.setBackgroundColor(context.getResources().getColor(R.color.indicator_selected_color));
            int index=(int)(Math.random()*arr.length);
            int rand = arr[index];
            if(rand%2==0){
                rl_timeback.setBackgroundColor(context.getResources().getColor(R.color.home_tiemcolorrand));
            }else {
                rl_timeback.setBackgroundColor(context.getResources().getColor(R.color.home_tiemcolorrandtwo));
            }
        }else {
            rl_timeback.setBackgroundColor(context.getResources().getColor(R.color.c3));
//            tv_time.setBackgroundColor(context.getResources().getColor(R.color.gray));
        }
        CircleImageView iv_groupiocn=holder.getView(R.id.iv_groupiocn);
        CircleImageView iv_heanderview=holder.getView(R.id.iv_heanderview);

        GlideUtil.loadImage(context,bean.getHeadIcon(),iv_heanderview);
        System.out.print("glade_uering"+bean.getGroup_logo());
//         GlideUtil.loadImage(context,bean.getGroup_logo(),iv_groupiocn);
        GlideUtil.loadImage(context,bean.getGroup_logo(),iv_groupiocn);

         TextView tv_groupname=holder.getView(R.id.tv_groupname);
        tv_groupname.setText(bean.getGroup_name());
        TextView tv_adress=holder.getView(R.id.tv_adress);
        tv_adress.setText(bean.getActivity_address());
        TextView tv_owername=holder.getView(R.id.tv_owername);
        tv_owername.setText("发起人 "+bean.getOwner_name());
        TextView tv_destance=holder.getView(R.id.tv_destance);
        tv_destance.setText(bean.getDistance());
        TextView tv_signo=holder.getView(R.id.tv_signo);
        tv_signo.setText(bean.getSign_up_no()+"");
        TextView tv_totalno=holder.getView(R.id.tv_totalno);
        tv_totalno.setText("/"+bean.getTotal_no());

        iv_chat = (LinearLayout)holder.getView(R.id.iv_chat);
        iv_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if("4".equals(bean.getGroup_type())){

                    RestClient.getYfmNovate(context).post(Constant.API.YFM_COMMPERCANCHAT, new ParamMap.Build()
                            .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                            .addParams("group_id", bean.getGroup_id())
//                .addParams("group_id", getIntent().getIntExtra("group_id",-1))
                            .build(), new DmeycBaseSubscriber<CheckChatActivity>() {

                                @Override
                                public void onSuccess(CheckChatActivity chachatbean) {
                                    ToastUtil.show(chachatbean.getMsg());
                                    if(1==chachatbean.getData()){
                                        onItemClickLisenter.onIntemClick(position,1,bean.getActivity_id(),bean.getGroup_id(),bean.getGroup_name());
                                    }else {
                                        ToastUtil.show("您不在该群，请先加入才能查看");
                                    }
                                }
                            }
                );

                }else {
                    onItemClickLisenter.onIntemClick(position,1,bean.getActivity_id(),bean.getGroup_id(),bean.getGroup_name());
                }
            }
        });
        linearLayout = (LinearLayout)holder.getView(R.id.actionitem);
        ll_top = (LinearLayout)holder.getView(R.id.ll_top);
            ll_top.setVisibility(View.VISIBLE);
        if(linearLayout!=null){
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickLisenter.onIntemClick(position,0,bean.getActivity_id(),bean.getGroup_id(),bean.getGroup_name());
                }
            });
        }

        iconAdr.setOnItemClickListener(new iconAdapter.OnItemClickListener() {
            @Override
            public void onClick(int p) {
           context.startActivity(new Intent(context,HasInActionMemBerActivity.class).putExtra("activityid",bean.getActivity_id()).putExtra("isallban",""));
            }
        });
       LinearLayout  ll_todetail=(LinearLayout) holder.getView(R.id.ll_todetail);
        ll_todetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickLisenter.onIntemClick(position,2,bean.getActivity_id(),bean.getGroup_id(),bean.getGroup_name());
            }
        });
    }


//    @Override
//    public int getItemViewType(int position) {
//        int dataItemCount = getContentItemCount();
//   if(position==0){
//            return ITEM_TYPE_HEADER;
//
//        }else {
////内容View
//            return ITEM_TYPE_CONTENT;
//        }
//    }
//
//    public int getContentItemCount(){
////        if(goodsBeans!=null){
////            return goodsBeans.size();
////        }else {
//            return deata.size();
////        }
//    }



    private  OnItemClickLisenter onItemClickLisenter;

    public void setOnActionClick(OnItemClickLisenter onItemClickLisenter){
        this.onItemClickLisenter=onItemClickLisenter;
    }

    public  interface OnItemClickLisenter{
        void onIntemClick(int pos,int type,int activityid,int groupid,String groupname);
    }

    static class iconAdapter extends RecyclerView.Adapter<iconAdapter.ViewHolder>{
      int pos;
        FirstActivityListBean.DataBean activityListBean;
        public iconAdapter(FirstActivityListBean.DataBean bean,int pos){
        this.pos=pos;
        this.activityListBean=bean;
        }
        @Override
        public iconAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view =((Activity)context).getLayoutInflater().inflate(R.layout.adapter_circlememberlist_first,null);
            return new iconAdapter.ViewHolder(view);
        }
        @Override
        public void onBindViewHolder(iconAdapter.ViewHolder holder, final int position) {
            GlideUtil.loadImage(context,activityListBean.getSign_up_list().get(position).getUrl(),holder.iv_groupiocn);
            holder.iv_groupiocn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onClick(position);
                }
            });
        }
        @Override
        public int getItemCount() {
            return activityListBean.getSign_up_list().size();
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            public CircleImageView iv_groupiocn;
            public ViewHolder(View itemView) {
                super(itemView);
                iv_groupiocn=itemView.findViewById(R.id.iv_groupiocn);
            }
        }

        /**
         * 点击
         */
        public interface OnItemClickListener {
            void onClick(int position);

        }
        OnItemClickListener onItemClickListener;
        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

    }

}
