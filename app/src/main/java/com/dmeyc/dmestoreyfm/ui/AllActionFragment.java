package com.dmeyc.dmestoreyfm.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.bean.YFMUserBean;
import com.dmeyc.dmestoreyfm.bean.common.CommonBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;

import butterknife.Bind;

@SuppressLint("ValidFragment")
public class AllActionFragment extends BaseFragment {
   @Bind(R.id.lv_pkinglist)
    ListView lv_pkinglist;
    private int itempos;
    NewActionBean newActionBean;
    @SuppressLint("ValidFragment")
    public AllActionFragment(int itempos){
        this.itempos=itempos;
    }
    @Override
    protected int getLayoutRes() {
        return R.layout.fragement_allaction;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initData(View view) {
        getData();
    }



    public void getData(){

        RestClient.getYfmNovate(getActivity()).post(Constant.API.YFM_ALLACTION, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("status", itempos+"")
                .build(), new DmeycBaseSubscriber<NewActionBean>() {
            @Override
            public void onSuccess(final NewActionBean bean) {
//                ToastUtil.show(bean.getMsg());
                newActionBean=bean;
                AllActionAdapter allActionAdapter=new AllActionAdapter();
                lv_pkinglist.setAdapter(allActionAdapter);
            }
        });
    }

    class ActionViewHolder{
        TextView tv_result,tv_actiontime,tv_actionclub,tv_adress,tv_week;
        CircleImageView cv_header;
        ImageView iv_ispk,iv_intrance;
        public ActionViewHolder(View view){
            tv_result= view.findViewById(R.id.tv_result);
            cv_header=(CircleImageView) view.findViewById(R.id.cv_header);
            tv_actiontime=(TextView) view.findViewById(R.id.tv_actiontime);
            tv_actionclub=(TextView) view.findViewById(R.id.tv_actionclub);
            tv_adress=(TextView) view.findViewById(R.id.tv_adress);
            tv_week=(TextView) view.findViewById(R.id.tv_week);
            iv_ispk=view.findViewById(R.id.iv_ispk);
            iv_intrance=view.findViewById(R.id.iv_intrance);
        }
    }


    public class AllActionAdapter extends BaseAdapter{
            @Override
            public int getCount() {
                return newActionBean.getData().size();
            }
            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }
            ActionViewHolder actionViewHolder;
            @Override
            public View getView(final int i, View view, ViewGroup viewGroup) {
                if(view==null){
                    view=getLayoutInflater().inflate(R.layout.adapter_actionitem,null);
                    actionViewHolder=new ActionViewHolder(view);
                    view.setTag(actionViewHolder);
                }else {
                    actionViewHolder=(ActionViewHolder) view.getTag();
                }
                if("1".equals(newActionBean.getData().get(i).getIsGroupPk())){
                    actionViewHolder.iv_ispk.setVisibility(View.VISIBLE);
                }else {
                    actionViewHolder.iv_ispk.setVisibility(View.GONE);
                }
                GlideUtil.loadImage(getActivity(),newActionBean.getData().get(i).getGroup_logo(),actionViewHolder.cv_header);
                actionViewHolder.tv_actiontime.setText(newActionBean.getData().get(i).getStart_date());
                actionViewHolder.tv_actionclub.setText(newActionBean.getData().get(i).getGroup_name());
                actionViewHolder.tv_adress.setText(newActionBean.getData().get(i).getActivityAddress());
                actionViewHolder.tv_result.setVisibility(View.VISIBLE);
                if(newActionBean.getData().get(i).getStatus().equals("1")){
                    actionViewHolder.tv_result.setText("取消报名");
                }else if(newActionBean.getData().get(i).getStatus().equals("2")){
                    actionViewHolder.tv_result.setText("比赛进程");
                }else if(newActionBean.getData().get(i).getStatus().equals("3")){
                    actionViewHolder.tv_result.setText("比赛结果");
                }else if(newActionBean.getData().get(i).getStatus().equals("5")){
                    actionViewHolder.tv_result.setText("活动已暂停");
                    actionViewHolder.tv_result.setClickable(false);
                }else if(newActionBean.getData().get(i).getStatus().equals("6")){
                    actionViewHolder.tv_result.setText("活动已取消");
                    actionViewHolder.tv_result.setClickable(false);
                }else if(newActionBean.getData().get(i).getStatus().equals("8")){
                    actionViewHolder.tv_result.setText("等待退款");
                    actionViewHolder.tv_result.setClickable(false);
                }else {
                    actionViewHolder.tv_result.setVisibility(View.VISIBLE);
                }
                if("4".equals(newActionBean.getData().get(i).getGroupType())){
                    actionViewHolder.iv_intrance.setVisibility(View.VISIBLE);
                }else {
                    actionViewHolder.iv_intrance.setVisibility(View.GONE);
                }
                actionViewHolder.tv_result.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(newActionBean.getData().get(i).getStatus().equals("1")){
//                            actionViewHolder.tv_result.setText("取消报名");
                            cancelIn(i);
                        }else if(newActionBean.getData().get(i).getStatus().equals("2")){
                            if("1".equals(newActionBean.getData().get(i).getIsGroupPk())){
                                startActivity(new Intent(getActivity(),NewPkResultActivity.class).putExtra("activityid",newActionBean.getData().get(i).getActivity_id()));
                            }else {
                                startActivity(new Intent(getActivity(),BClientPKdetail.class).putExtra("activityid",newActionBean.getData().get(i).getActivity_id()));
                            }
                        }else if(newActionBean.getData().get(i).getStatus().equals("3")){
                            if("1".equals(newActionBean.getData().get(i).getIsGroupPk())){
                                startActivity(new Intent(getActivity(),NewPkResultActivity.class).putExtra("activityid",newActionBean.getData().get(i).getActivity_id()));
                            }else {
                                startActivity(new Intent(getActivity(),BClientPKdetail.class).putExtra("activityid",newActionBean.getData().get(i).getActivity_id()));
                            }
                           }/*else if(newActionBean.getData().get(i).getStatus().equals("5")){

                        }else if(newActionBean.getData().get(i).getStatus().equals("6")){

                        }else {
                            startActivity(new Intent(getActivity(),NewPkResultActivity.class));
                        }*/
//                        startActivity(new Intent(getActivity(),BClientPKdetail.class).putExtra("activityid",newActionBean.getData().get(i).getActivity_id()));
                    }
                });
                actionViewHolder.tv_week.setText(newActionBean.getData().get(i).getWeekDay());
                return view;
            }
    }
    public void cancelIn(int pos){
        RestClient.getYfmNovate(getActivity()).post(Constant.API.YFM_CANCELIN, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("activity_id",newActionBean.getData().get(pos).getActivity_id())
                .build(), new DmeycBaseSubscriber<CommonBean>() {
            @Override
            public void onSuccess(CommonBean bean) {
           ToastUtil.show(bean.getMsg());
                getData();
            }
        });

    }
}
