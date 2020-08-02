package com.dmeyc.dmestoreyfm.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.HeroRankAdapter;
import com.dmeyc.dmestoreyfm.adapter.PKItemAdapter;
import com.dmeyc.dmestoreyfm.base.BaseRefreshFragment;
import com.dmeyc.dmestoreyfm.bean.LookBean;
import com.dmeyc.dmestoreyfm.bean.MyCreatCommListBean;
import com.dmeyc.dmestoreyfm.bean.NewWhoPkBean;
import com.dmeyc.dmestoreyfm.bean.PKListBean;
import com.dmeyc.dmestoreyfm.bean.ProjectTypeBean;
import com.dmeyc.dmestoreyfm.bean.SportItemListBean;
import com.dmeyc.dmestoreyfm.bean.SportSubmitBean;
import com.dmeyc.dmestoreyfm.bean.common.ReviewsBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.ActionRecordActivity;
import com.dmeyc.dmestoreyfm.ui.CommInActivity;
import com.dmeyc.dmestoreyfm.ui.MachesResultActivity;

import com.dmeyc.dmestoreyfm.ui.PKInforActivity;
import com.dmeyc.dmestoreyfm.ui.PKMyCreatCommListActivity;
import com.dmeyc.dmestoreyfm.ui.PkResultActivity;
import com.dmeyc.dmestoreyfm.ui.SystemMacthResultActivity;
import com.dmeyc.dmestoreyfm.utils.DataClass;
import com.dmeyc.dmestoreyfm.utils.GsonTools;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.PopupMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jockie on 2017/11/3
 * Email:jockie911@gmail.com
 */

@SuppressLint("ValidFragment")
public class ActivityItemFragment extends BaseRefreshFragment<NewWhoPkBean,PKItemAdapter> {
            int itemppos;
            int id;
    private PKItemAdapter lookAdapter;
    @SuppressLint("ValidFragment")
    public ActivityItemFragment(int itemppos,int id){
        this.itemppos=itemppos;
        this.id=id;
    }

    @Override
    protected PKItemAdapter getAdapter() {
//        if(0==state||1==state){
            lookAdapter= new PKItemAdapter(getActivity(), R.layout.adapter_pkandhero, new ArrayList<NewWhoPkBean.DataBean>(),state);
//        }else {
////            lookAdapter= new HeroRankAdapter(getActivity(), R.layout.item_rv_home_look, new ArrayList<PKListBean.DataBean.GroupPkListBean>());
//            lookAdapter= new PKItemAdapter(getActivity(), R.layout.item_rv_home_look, new ArrayList<PKListBean.DataBean.GroupPkListBean>(),state);
//        }
            return lookAdapter;
    }

    @Override
    protected NewWhoPkBean parseData(String string) {
        return GsonTools.changeGsonToBean(string,NewWhoPkBean.class);
    }
    NewWhoPkBean pkListBean;
     PKListBean.DataBean.GroupPkListBean groupPkListBean;
     ArrayList<PKListBean.DataBean.GroupPkListBean>  pkListBeanArrayList=new ArrayList<PKListBean.DataBean.GroupPkListBean>();;
    @Override
    protected void setData(NewWhoPkBean lookBean, boolean isRefresh) {
        pkListBean=lookBean;
//        if(0==state||1==state){
            lookAdapter.setState(state);
            if(lookBean.getData()==null||lookBean.getData().size()==0){
                adapter.addData(lookBean.getData(),isRefresh);
                adapter.notifyDataSetChanged();
                mSmartRefresh.setVisibility(View.GONE);
                tv_nodata.setVisibility(View.VISIBLE);
            }else {
                mSmartRefresh.setVisibility(View.VISIBLE);
                adapter.addData(lookBean.getData(),isRefresh);
                adapter.notifyDataSetChanged();
                tv_nodata.setVisibility(View.GONE);
            }

       /* }else {
            lookAdapter.setState(state);
            if(lookBean.getData().getRankList().size()==0){

                adapter.addData(lookBean.getData().getGroup_pk_list(),isRefresh);
                adapter.notifyDataSetChanged();
                mSmartRefresh.setVisibility(View.GONE);
                tv_nodata.setVisibility(View.VISIBLE);
            }else {
                pkListBeanArrayList.clear();
                for (int i=0;i<lookBean.getData().getRankList().size();i++){
                    groupPkListBean=new PKListBean.DataBean.GroupPkListBean();
                    groupPkListBean.setActivity_venue_address(lookBean.getData().getRankList().get(i).getActivity_venue_address());
                    groupPkListBean.setBattle_number(lookBean.getData().getRankList().get(i).getBattle_number());
                    groupPkListBean.setGroup_name(lookBean.getData().getRankList().get(i).getGroup_name());
                    groupPkListBean.setImg_url(lookBean.getData().getRankList().get(i).getImg_url());
                    groupPkListBean.setRank(lookBean.getData().getRankList().get(i).getRank());
                    groupPkListBean.setProject_type(lookBean.getData().getRankList().get(i).getProject_type());
                    groupPkListBean.setDistance(lookBean.getData().getRankList().get(i).getDistance());
                    pkListBeanArrayList .add(groupPkListBean);
                }
                mSmartRefresh.setVisibility(View.VISIBLE);
                adapter.addData(pkListBeanArrayList,isRefresh);
                adapter.notifyDataSetChanged();
                tv_nodata.setVisibility(View.GONE);
            }
        }*/

    }

    @Override
    protected String getUrl() {
//        if(0==state||1==state){
            return Constant.API.YFM_PKLIST;
//        }else {
//            return Constant.API.YFM_HERO;
//        }

    }

    @Override
    protected Map<String, Object> getParamMap() {
//        if(0==state||1==state){
            ParamMap.Build  pb=  new ParamMap.Build();
            pb.addParams("user_token",SPUtils.getStringData(Constant.Config.TOKEN));
            pb .addParams("city_name", SPUtils.getStringData(Constant.Config.CURRENT_CITY))
                    .addParams("longitude", SPUtils.getStringData(Constant.Config.LONGTUDE))
                    .addParams("latitude", SPUtils.getStringData(Constant.Config.LATITUDE))
//                    .addParams("pageIndex", 1)
//                    .addParams("pageSize", 20)
                    .addParams("status", 0)
                    .addParams("project_type", 1);
            return pb.build();
//        }else {
//            ParamMap.Build  pb=  new ParamMap.Build();
//            pb.addParams("user_token",SPUtils.getStringData(Constant.Config.TOKEN));
//            pb .addParams("city_name", SPUtils.getStringData(Constant.Config.CURRENT_CITY))
//                    .addParams("longitude", SPUtils.getStringData(Constant.Config.LONGTUDE))
//                    .addParams("latitude", SPUtils.getStringData(Constant.Config.LATITUDE));
//            return pb.build();
//        }
    }

    @Override
    protected void initData(View view) {
        lookAdapter.OnPkItemClickLisenter(new PKItemAdapter.PkItemClickLisenter() {
            @Override
            public void onPkItemClick(int i,String isSystem) {
                if("1".equals(isSystem)){
//                    startActivity(new Intent(getActivity(),SystemMacthResultActivity.class).putExtra("pk_activityid",pkListBean.getData().get(i).getActivity_a_id()));

                    startActivity(new Intent(getActivity(),SystemMacthResultActivity.class).putExtra("pk_activityid",pkListBean.getData().get(i).getActivity_a_id())
                            .putExtra("groupname",pkListBean.getData().get(i).getGroup_pk_name()).putExtra("acid",pkListBean.getData().get(i).getActivity_a_id()));

                }else {

                    startActivity(new Intent(getActivity(),PkResultActivity.class)
                            .putExtra("ispked",pkListBean.getData().get(i).getIsPked())
                            .putExtra("PK_groupid",pkListBean.getData().get(i).getGroup_pk_id())
                            .putExtra("headurl",pkListBean.getData().get(i).getGroup_a_logo())
                            .putExtra("graopname",pkListBean.getData().get(i).getGroup_a_name())
                            .putExtra("isgover",pkListBean.getData().get(i).getIsGovernment()));

                }
            }

            @Override
            public void whoPkTeamClick(int pos) {
                if(type==1){
                    getMyComm(pos);
                }
            }
        });
    }
      int porject=1,state=0;int type;
    public void notifyData(int porject,int state,int type){
        this.porject=porject;
        this.state=state;
        this.type=type;
//        ToastUtil.show(porject+"1111"+state);
//        ParamMap.Build  pb=  new ParamMap.Build();
//        pb.addParams("user_token",Constant.TEST_USERTOKEN);
//        pb .addParams("city_name", SPUtils.getStringData(Constant.Config.CURRENT_CITY))
//                .addParams("longitude", SPUtils.getStringData(Constant.Config.LONGTUDE))
//                .addParams("latitude", SPUtils.getStringData(Constant.Config.LATITUDE))
//                .addParams("status", "1")
//                .addParams("project_type", "1");
        requestData();
    }
    private PopupMenu popupMenu;
    ArrayList <String> ar=new ArrayList<>();
    public void getMyComm(final int posion){

        ParamMap.Build pb=  new ParamMap.Build();

        RestClient.getYfmNovate(getActivity()).post(Constant.API.YFM_GETCOMM,
                pb.addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
//                        .addParams("is_have_invoice", check+"")

                        .build(), new DmeycBaseSubscriber<MyCreatCommListBean>() {
                    @Override
                    public void onSuccess(final MyCreatCommListBean bean) {
                        if(bean.getData().size()==0){
                        }else if(bean.getData().size()==1){
//                            startActivity(new Intent(getActivity(),PKInforActivity.class).putExtra("activityid",pkListBean.getData().getGroup_pk_list().get(posion).getActivity_id()).
//                           putExtra("groupidpk",bean.getData().get(0).getGroup_id()).putExtra("gorupid",pkListBean.getData().getGroup_pk_list().get(posion).getGroup_pk_id()));
                        }else if(bean.getData().size()>1){
//                            DataClass.myCreatCommListBean=bean;
//                            ArrayList<Integer>pkgid=new ArrayList<>();
//                            ArrayList<Integer>activityid=new ArrayList<>();
//
//                            List<MyCreatCommListBean.DataBean> lbean= bean.getData();
////                            ar.clear();
//                            for (int i=0;i<lbean.size();i++){
//                                ar.add(lbean.get(i).getGroup_name());
//                            }
//                            for (int ia=0;ia<pkListBean.getData().getGroup_pk_list().size();ia++){
//                                pkgid.add(pkListBean.getData().getGroup_pk_list().get(ia).getGroup_pk_id());
//                            }
//                            for (int ia=0;ia<pkListBean.getData().getGroup_pk_list().size();ia++){
//                                pkgid.add(pkListBean.getData().getGroup_pk_list().get(ia).getGroup_pk_id());
//                                activityid.add(pkListBean.getData().getGroup_pk_list().get(ia).getActivity_id());
//                            }
//                            DataClass.myCreatCommListBean.setPkgroupid(pkgid);
//                            DataClass.myCreatCommListBean.setActivityid(activityid);
//                            startActivity(new Intent(getActivity(),PKMyCreatCommListActivity.class));

                        }
                    }
                });

    }
}
