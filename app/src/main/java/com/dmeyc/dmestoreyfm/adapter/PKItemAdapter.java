package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.MyCreatCommListBean;
import com.dmeyc.dmestoreyfm.bean.NewWhoPkBean;
import com.dmeyc.dmestoreyfm.bean.PKListBean;
import com.dmeyc.dmestoreyfm.bean.common.ReviewsBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.PKMyCreatCommListActivity;
import com.dmeyc.dmestoreyfm.utils.DataClass;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jockie on 2018/1/4
 * Email:jockie911@gmail.com
 */

public class PKItemAdapter extends BaseRvAdapter<NewWhoPkBean.DataBean> {
            Context context;
            int state;
            int chalangepos;
    int [] arr = {1,2};
    NewWhoPkBean.DataBean dataBean;
    List<NewWhoPkBean.DataBean> dataBeans;
    public  PKItemAdapter(Context context, int layoutId, List<NewWhoPkBean.DataBean> datas, int state) {
        super(context, layoutId, datas);
        this.context=context;
        this.state=state;
        this.dataBeans=datas;
    }
    public void setState(int state){
        this.state=state;
    }
    @Override
    protected void convert(ViewHolder holder, final NewWhoPkBean.DataBean bean, final int position) {
        dataBean=bean;
        final RelativeLayout rl_pkitem = holder.getView(R.id.rl_pkitem);
      TextView tv_cubnam=(TextView) holder.getView(R.id.tv_cubnam);
//        tv_cubnam.setText(bean.getGroup_pk_name());
        tv_cubnam.setText(bean.getGroup_pk_subname());
        TextView tv_adress=(TextView) holder.getView(R.id.tv_adress);
        tv_adress.setText(bean.getAddress());
        TextView tv_pktime=(TextView) holder.getView(R.id.tv_pktime);
        if(!TextUtils.isEmpty(bean.getEnd_time())){
            tv_pktime.setText(bean.getStart_time()+"-"+(bean.getEnd_time().split(" ")[1]));
        }
      TextView tv_destance= holder.getView(R.id.tv_destance);
        tv_destance.setText(bean.getDistance());
       TextView tv_tochalange=holder.getView(R.id.tv_tochalange);
        tv_tochalange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chalangepos=position;
                getMyComm();
            }
        });

        if("1".equals(bean.getIs_group_a_owner())){
            tv_tochalange.setVisibility(View.GONE);
        }
        if("1".equals(bean.getIsGovernment())){
            tv_tochalange.setVisibility(View.GONE);
        }
        if("0".equals(bean.getIsPked())){
            tv_tochalange.setBackground(context.getResources().getDrawable(R.drawable.shap_red_cilce));
            tv_tochalange.setText("挑战TA");
            tv_tochalange.setClickable(true);
        }else if("1".equals(bean.getIsPked())){
            tv_tochalange.setBackground(context.getResources().getDrawable(R.drawable.shap_gray_cicle));
            tv_tochalange.setText("待同意");
            tv_tochalange.setClickable(false);
        }else if("2".equals(bean.getIsPked())){
            tv_tochalange.setBackground(context.getResources().getDrawable(R.drawable.shap_gray_cicle));
            tv_tochalange.setText("已同意");
            tv_tochalange.setClickable(false);
        }else {
            tv_tochalange.setBackground(context.getResources().getDrawable(R.drawable.shap_gray_cicle));
            tv_tochalange.setText("已拒绝");
            tv_tochalange.setClickable(false);
        }
        TextView tv_teamonename=(TextView) holder.getView(R.id.tv_teamonename);
        tv_teamonename.setText(bean.getGroup_a_name());
        TextView tv_teanmoneperson=(TextView) holder.getView(R.id.tv_teanmoneperson);
        tv_teanmoneperson.setText(bean.getTotal_no()+"/人");
        TextView tv_teantwonaem=(TextView) holder.getView(R.id.tv_teantwonaem);
        tv_teantwonaem.setText(bean.getGroup_b_name());
        TextView tv_teantwoperson=(TextView) holder.getView(R.id.tv_teantwoperson);
        tv_teantwoperson.setText(bean.getTotal_no()+"/人");


      TextView tv_powernumber=  holder.getView(R.id.tv_powernumber);
        tv_powernumber.setText(bean.getMinBattle()+"-"+bean.getMaxBattle());
       CircleImageView iv_teamone=holder.getView(R.id.iv_teamone);
        GlideUtil.loadImage(context,bean.getGroup_a_logo(),iv_teamone);
        CircleImageView iv_teamtwo=holder.getView(R.id.iv_teamtwo);
        GlideUtil.loadImage(context,bean.getGroup_b_logo(),iv_teamtwo);

         CircleImageView circleImageView=(CircleImageView) holder.getView(R.id.civ_avatar);
//         GlideUtil.loadImage(context,bean.getImg_url(),circleImageView);

      RelativeLayout  rl_timeback=holder.getView(R.id.rl_timeback);
        int index=(int)(Math.random()*arr.length);
        int rand = arr[index];
        if(rand%2==0){
            rl_timeback.setBackgroundColor(context.getResources().getColor(R.color.home_tiemcolorrand));
        }else {
            rl_timeback.setBackgroundColor(context.getResources().getColor(R.color.home_tiemcolorrandtwo));
        }

      ImageView iv_isbius=  holder.getView(R.id.iv_isbius);
        if("4".equals(bean.getGroupType())){
            iv_isbius.setVisibility(View.VISIBLE);
        }else {
            iv_isbius.setVisibility(View.GONE);
        }

        if(rl_pkitem!=null){
            rl_pkitem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pkItemClickLisenter.onPkItemClick(position,bean.getIsSystem());
                }
            });
        }
        iv_teamtwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pkItemClickLisenter.whoPkTeamClick(position);
            }
        });
        TextView tv_pk_time=(TextView) holder.getView(R.id.tv_pk_time);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonnts/unit.ttf");
//        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonnts/digital.ttf");
//     String endtime=   bean.getEnd_time();
//       String times= endtime.split(" ")[0];
//     String pal=   times.split("-")[1]+"-"+times.split("-")[2];
//        tv_pk_time.setText(pal);
//        tv_pk_time.setTypeface(typeface);
        String endtime=   bean.getStart_time();
        String times= endtime.split(" ")[0];
        String pal=   times.split("-")[1]+"-"+times.split("-")[2];
        tv_pk_time.setText(pal);
        tv_pk_time.setTypeface(typeface);
    }
     private PkItemClickLisenter pkItemClickLisenter;
    public void  OnPkItemClickLisenter(PkItemClickLisenter pkItemClickLisenter){
        this.pkItemClickLisenter=pkItemClickLisenter;
    }
    public interface PkItemClickLisenter{
          void onPkItemClick(int pos,String isSystem);
          void whoPkTeamClick(int pos);
    }


    ArrayList <String> ar=new ArrayList<>();
    public void getMyComm(){

        ParamMap.Build pb=  new ParamMap.Build();
        RestClient.getYfmNovate(context).post(Constant.API.YFM_GETCOMM,
                pb.addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
//                        .addParams("is_have_invoice", check+"")
                        .build(), new DmeycBaseSubscriber<MyCreatCommListBean>() {
                    @Override
                    public void onSuccess(final MyCreatCommListBean bean) {
                        if(bean.getData().size()==0){
                            ToastUtil.show("您没有创建羽毛球群");
                        }else if(bean.getData().size()>=1){
//                            DataClass.myCreatCommListBean=bean;
//                            ArrayList<Integer> pkgid=new ArrayList<>();
//                            ArrayList<Integer>activityid=new ArrayList<>();
//
//                            List<MyCreatCommListBean.DataBean> lbean= bean.getData();
//                            ar.clear();
//                            for (int i=0;i<lbean.size();i++){
//                                ar.add(lbean.get(i).getGroup_name());
//                            }

//                            DataClass.myCreatCommListBean.setPkgroupid(pkgid);
//                            DataClass.myCreatCommListBean.setActivityid(activityid);
                            context.startActivity(new Intent(context,PKMyCreatCommListActivity.class).putExtra("groupkid",dataBeans.get(chalangepos).getGroup_pk_id()));

                        }
                    }
                });
    }
}
