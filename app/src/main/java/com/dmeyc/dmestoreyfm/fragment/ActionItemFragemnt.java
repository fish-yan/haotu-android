package com.dmeyc.dmestoreyfm.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.ActionItemAdapter;
import com.dmeyc.dmestoreyfm.base.BaseRefreshFragment;
import com.dmeyc.dmestoreyfm.bean.FirstActivityListBean;
import com.dmeyc.dmestoreyfm.bean.LookBean;
import com.dmeyc.dmestoreyfm.bean.common.ReviewsBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.ui.ActionItemActivity;
import com.dmeyc.dmestoreyfm.ui.ChartInforActivity;
import com.dmeyc.dmestoreyfm.ui.CourseItemActivity;
import com.dmeyc.dmestoreyfm.utils.GsonTools;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

@SuppressLint("ValidFragment")
public class ActionItemFragemnt extends BaseRefreshFragment<FirstActivityListBean,ActionItemAdapter> {
    private int item;

    public ActionItemFragemnt(int item) {
        this.item = item;
    }

    private ActionItemAdapter lookAdapter;

    @Override
    protected ActionItemAdapter getAdapter() {
        lookAdapter = new ActionItemAdapter(getActivity(), R.layout.fragment_actionitem, new ArrayList<FirstActivityListBean.DataBean>());
        return lookAdapter;
    }

    @Override
    protected FirstActivityListBean parseData(String string) {
        closeRefresh();
        return GsonTools.changeGsonToBean(string, FirstActivityListBean.class);
    }

    FirstActivityListBean firstActivityListBean;

    @Override
    protected void setData(FirstActivityListBean lookBean, boolean isRefresh) {
        firstActivityListBean = lookBean;
        if(lookBean.getData()!=null){
            if (lookBean.getData().size() == 0) {
                mSmartRefresh.setVisibility(View.GONE);
                tv_nodata.setVisibility(View.VISIBLE);
                tv_nodata.setText("暂无活动");
            } else {
                mSmartRefresh.setVisibility(View.VISIBLE);
                adapter.addData(lookBean.getData(), isRefresh);
                tv_nodata.setVisibility(View.GONE);
            }
            adapter.notifyDataSetChanged();
        }else {
            mSmartRefresh.setVisibility(View.GONE);
            tv_nodata.setVisibility(View.VISIBLE);
            tv_nodata.setText("暂无活动");
        }

//        adapter.addData( lookBean.getData().getActivity_list(),isRefresh);
    }
    @Override
    protected String getUrl() {
        return Constant.API.YFM_ACTION_DATA;
    }

    @Override
    protected Map<String, Object> getParamMap() {
        ParamMap.Build pb = new ParamMap.Build();

                if(2==state){
                    pb.addParams("keyword",keyword);
                }
                if(SPUtils.getStringData("keyword").equals("0")){
                    pb.addParams("keyword","");
                }
        if(porject==2||2== item){
            if (TextUtils.isEmpty(SPUtils.getStringData(Constant.Config.CHECKHOR))) {
//                    Calendar now = Calendar.getInstance();
//                    if((now.get(Calendar.DAY_OF_MONTH)+"").length()==1){
                        pb.addParams("activity_date","1_0");
//                    }else {
//                        pb.addParams("activity_date", now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.DAY_OF_MONTH) + " " + SPUtils.getStringData(Constant.Config.CHECKHOR));
//                    }
            } else {
                pb.addParams("activity_date",SPUtils.getStringData(Constant.Config.CHECKHOR));
            }
        }else {
            if (TextUtils.isEmpty(SPUtils.getStringData(Constant.Config.CHECKDAY))) {
                if (TextUtils.isEmpty(SPUtils.getStringData(Constant.Config.CHECKHOR))) {
                    Calendar now = Calendar.getInstance();
                    if((now.get(Calendar.DAY_OF_MONTH)+"").length()==1){
                        pb.addParams("activity_date", now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) + "-" + "0"+now.get(Calendar.DAY_OF_MONTH) + " " + "00:00:00");
                    }else {
                        pb.addParams("activity_date", now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.DAY_OF_MONTH) + " " + "00:00:00");
                    }
//                pb.addParams("endDate", now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.DAY_OF_MONTH) + " " + "00:00:00");
                } else {
                    Calendar now = Calendar.getInstance();
                    if((now.get(Calendar.DAY_OF_MONTH)+"").length()==1){
                        pb.addParams("activity_date", now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) + "-" +"0"+ now.get(Calendar.DAY_OF_MONTH) + " " + SPUtils.getStringData(Constant.Config.CHECKHOR));
                    }else {
                        pb.addParams("activity_date", now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.DAY_OF_MONTH) + " " + SPUtils.getStringData(Constant.Config.CHECKHOR));
                    }
//                pb.addParams("endDate", now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.DAY_OF_MONTH) + " " +SPUtils.getStringData(Constant.Config.CHECKENDHOR));
                }

            } else {

                if (TextUtils.isEmpty(SPUtils.getStringData(Constant.Config.CHECKHOR))) {
                    Calendar now = Calendar.getInstance();
                    pb.addParams("activity_date", SPUtils.getStringData(Constant.Config.CHECKDAY) + " " + "00:00:00");
//                pb.addParams("endDate", SPUtils.getStringData(Constant.Config.CHECKDAY) + " " + "00:00:00");
                } else {
                    Calendar now = Calendar.getInstance();
                    pb.addParams("activity_date", SPUtils.getStringData(Constant.Config.CHECKDAY) + " " + SPUtils.getStringData(Constant.Config.CHECKHOR));
//                pb.addParams("endDate",SPUtils.getStringData(Constant.Config.CHECKDAY) + " " +SPUtils.getStringData(Constant.Config.CHECKENDHOR));
                }
            }
        }

        if (0 == item) {
                    pb.addParams("group_type", "1");
                      pb.addParams("project_type", "1");
        } else if(1==item) {
            pb.addParams("group_type", "3");
//            if(TextUtils.isEmpty(SPUtils.getStringData(Constant.Config.PROJECTID))){
//                pb .addParams("project_type", "1");
//            }else {
//                pb .addParams("project_type", SPUtils.getStringData(Constant.Config.PROJECTID));
//            }
//                 pb.addParams("group_type", "2");
//            if(TextUtils.isEmpty(SPUtils.getStringData(Constant.Config.PROJECTID))){
//                pb .addParams("project_type", "1");
//            }else {
//                pb .addParams("project_type", SPUtils.getStringData(Constant.Config.PROJECTID));
//            }
        }else if(2==item) {
            pb.addParams("group_type", "5");
//            if(TextUtils.isEmpty(SPUtils.getStringData(Constant.Config.PROJECTID))){
//                pb .addParams("project_type", "1");
//            }else {
//                pb .addParams("project_type", SPUtils.getStringData(Constant.Config.PROJECTID));
//            }

//            pb.addParams("group_type", "3");
//            if(TextUtils.isEmpty(SPUtils.getStringData(Constant.Config.PROJECTID))){
//                pb .addParams("project_type", "1");
//            }else {
//                pb .addParams("project_type", SPUtils.getStringData(Constant.Config.PROJECTID));
//            }
        }else if(3==item){
               pb.addParams("group_type", "5");
            if(TextUtils.isEmpty(SPUtils.getStringData(Constant.Config.PROJECTID))){
                pb .addParams("project_type", "1");
            }else {
                pb .addParams("project_type", SPUtils.getStringData(Constant.Config.PROJECTID));
            }
        }else if(4==item){
            pb.addParams("group_type", "99");
            if(TextUtils.isEmpty(SPUtils.getStringData(Constant.Config.PROJECTID))){
                pb .addParams("project_type", "1");
            }else {
                pb .addParams("project_type", SPUtils.getStringData(Constant.Config.PROJECTID));
            }
        }
        if(TextUtils.isEmpty(SPUtils.getStringData(Constant.Config.CURRENT_CITY))){
            pb.addParams("city_name", "上海");
        }else {
            pb.addParams("city_name", SPUtils.getStringData(Constant.Config.CURRENT_CITY));
        }
        return pb.addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
//                .addParams("city_name", SPUtils.getStringData(Constant.Config.CURRENT_CITY))
                .addParams("longitude", SPUtils.getStringData(Constant.Config.LONGTUDE))
                .addParams("latitude", SPUtils.getStringData(Constant.Config.LATITUDE))
                .addParams("sort", 1).build();
//        pb.addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
    }

    @Override
    protected void initData(final View view) {
        lookAdapter.setOnActionClick(new ActionItemAdapter.OnItemClickLisenter() {
            @Override
            public void onIntemClick(int pos, int view1, int activityid, final int groupid, final String grname) {
//              int viewid=  view.getId();
                if (view1 == 1) {
                            RongIM.getInstance().startGroupChat(getActivity(), groupid+"", grname);
//                    RongIM.getInstance().startConversation(getActivity(), Conversation.ConversationType.GROUP, groupid + "", grname);
//                    RongIM.getInstance().startGroupChat(getActivity(), groupid + "", grname);
                } else if (view1 == 0) {
                    if(item==1){
                        startActivity(new Intent(getActivity(), CourseItemActivity.class).putExtra("activityid", activityid));
                    }else {
                        startActivity(new Intent(getActivity(), ActionItemActivity.class).putExtra("activityid", activityid));
                    }

                } else if (view1 == 2) {
                    startActivity(new Intent(getActivity(),ChartInforActivity.class).putExtra("group_id",groupid));
                }
            }
        });
    }
    int porject = 1, state = 1;
    public void notifyData(int porject, int state) {
        this.porject = porject;
        this.state = state;
        requestData();
    }
    String keyword;
    public void notifyData(int porject, int state,String keyword) {
        this.porject = porject;
        this.state = state;
        this.keyword=keyword;
        requestData();
    }
}
