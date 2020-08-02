package com.dmeyc.dmestoreyfm.listener;

import android.content.Context;
import android.net.Uri;
import android.os.SystemClock;

import com.dmeyc.dmestoreyfm.bean.CommInfroRoClude;
import com.dmeyc.dmestoreyfm.bean.MyCommListBean;
import com.dmeyc.dmestoreyfm.bean.PkResultDetailBean;
import com.dmeyc.dmestoreyfm.bean.YFMUserBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.UserInfo;

public class YFMGroupInfoProvider implements RongIM.GroupInfoProvider {
    private Context context;
    private static YFMGroupInfoProvider imcontext;

    public YFMGroupInfoProvider(Context context) {
        this.context = context;
//        new ShowGroupData(context);
        initListener();
//        getData();
    }

    public static void init(Context context) {

        if (imcontext == null) {
            synchronized (YFMGroupInfoProvider.class) {

                if (imcontext == null) {
                    imcontext = new YFMGroupInfoProvider(context);
                }
            }
        }

    }

    private void initListener() {
        RongIM.setGroupInfoProvider(this, true);
    }
    CommInfroRoClude commInfroRoClude;
    @Override
    public Group getGroupInfo(final String s) {

String ssss=s;
String eeee=s;


//System.out.print("sssss"+s);

        RestClient.getYfmNovate(context).post(Constant.API.YFM_GETCOMMINFORBYID, new ParamMap.Build()
                        .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                       .addParams("group_id",s)
                        .build(), new DmeycBaseSubscriber<CommInfroRoClude>() {
            @Override
            public void onSuccess(CommInfroRoClude bean) {
                commInfroRoClude=bean;
               ToastUtil.show(bean.getMsg());
            }
        });
        SystemClock.sleep(2000);
        if(commInfroRoClude==null){
            return null;
        }
//        RongIM.getInstance().refreshGroupInfoCache(new Group(commInfroRoClude.getData().getGroup_id()+"",commInfroRoClude.getData().getGroup_name(),Uri.parse(commInfroRoClude.getData().getGroup_logo())));

        return   new Group(commInfroRoClude.getData().getGroup_id()+"",commInfroRoClude.getData().getGroup_name(),Uri.parse(commInfroRoClude.getData().getGroup_logo()));

//        int result = 0;
//        for (int i = 0; i < groupList.size(); i++) {
//            if (s.equals(groupList.get(i).id + ""))
//                result = i;
//        }
//
//        return new Group(groupList.get(result).id + "",

//                if(ShowGroupData.findGroupData_beanList!=null){
//                    for (FindGroupData_Bean f:ShowGroupData.findGroupData_beanList){
//                        if(f.getGroupid().equals(s)){
//                            return new Group(6+"","红红火火",Uri.parse("http://192.168.0.104/group1/M00/00/09/wKgAaFzcGmWAA5VtAAEn1zrAgoA844.jpg"));
//                        }
//                    }
//                }

//        RestClient.getYfmNovate(context).post(Constant.API.YFM_MYCOMMlist, new ParamMap.Build()
//                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
//                .addParams("status", "0")
//                .build(), new DmeycBaseSubscriber<MyCommListBean>() {
//            @Override
//            public void onSuccess(MyCommListBean bean) {
//                ToastUtil.show(bean.getMsg());



//        if(myCommListBean.getData()!=null){
//            for (MyCommListBean.DataBean f:myCommListBean.getData()){
//                if((f.getGroup_id()+"").equals(s)){
//                    return new Group(f.getGroup_id()+"", f.getGroup_name(), Uri.parse(f.getGroup_logo()));
//                }
//            }
//        }
//        return null;
//return  getData(s);
    }
    MyCommListBean myCommListBean;
    public Group getData(String s){
        RestClient.getYfmNovate(context).post(Constant.API.YFM_MYCOMMlist, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("status", "0")
                .build(), new DmeycBaseSubscriber<MyCommListBean>() {
            @Override
            public void onSuccess(MyCommListBean bean) {
//                ToastUtil.show(bean.getMsg());
//                for (int i = 0; i < bean.getData().size(); i++) {
////                    if (s.equals(bean.getData().get(i).getGroup_id() + ""))
////                        result = i;
//                    System.out.print("555555555555"+bean.getData().get(i).getGroup_logo());
//                    System.out.print("666666666666"+bean.getData().get(i).getGroup_name());
//                }
                myCommListBean=bean;

            }
        });
        if(myCommListBean!=null&&myCommListBean.getData()!=null){
            for (MyCommListBean.DataBean f:myCommListBean.getData()){
                if((f.getGroup_id()+"").equals(s)){
                    Group group=  new Group(f.getGroup_id()+"", f.getGroup_name(), Uri.parse(f.getGroup_logo()));
                    RongIM.getInstance().refreshGroupInfoCache(group);
//                    RongIM.getInstance().refreshGroupInfoCache(new Group(f.getGroup_id()+"", f.getGroup_name(), Uri.parse(f.getGroup_logo())));
                    return group;
                }
            }
        }
        return null;
    }
}
