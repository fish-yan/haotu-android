package com.dmeyc.dmestoreyfm.ui;

import android.app.Dialog;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.maps.model.Text;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.CommDetailBean;
import com.dmeyc.dmestoreyfm.bean.MemberListBean;
import com.dmeyc.dmestoreyfm.bean.PublishActionAfterBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.dmeyc.dmestoreyfm.wedgit.NoScrollListView;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyItemDialogListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MemberListActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.tv_right_title_bar)
    TextView tv_right_title_bar;

    private String speak="0";
    int flag=0;
    int memberid;
    String groupid;
    List<String> strings;
    int pos;

    @Bind(R.id.lv_vip_host)
    NoScrollListView lv_vip_host;
    @Bind(R.id.lv_vip_meneger)
    NoScrollListView lv_vip_meneger;
    @Bind(R.id.lv_vip_member)
    NoScrollListView lv_vip_member;

    @Bind(R.id.tv_textmanager)
    TextView tv_textmanager;
    @Bind(R.id.tv_nomalmember)
    TextView tv_nomalmember;
    @Bind(R.id.tv_managernum)
    TextView tv_managernum;
    @Bind(R.id.tv_memnumber)
    TextView tv_memnumber;
    @Bind(R.id.ll_nomal)
    LinearLayout ll_nomal;
    @Bind(R.id.ll_mannager)
    LinearLayout ll_mannager;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_memberlist;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
//        lv_vip_member=(ListView) findViewById(R.id.lv_vip_member);
        speakstate=getIntent().getStringExtra("isallban");
        groupid=getIntent().getStringExtra("groupid");
        if(groupid==null){
            groupid=getIntent().getIntExtra("groupid",-1)+"";
        }
        if("0".equals(getIntent().getStringExtra("isallban"))){
            tv_right_title_bar.setText("全体禁言");
        }else {
            tv_right_title_bar.setText("全体发言");
        }
        if("1".equals(getIntent().getStringExtra("type"))){
            tv_right_title_bar.setVisibility(View.VISIBLE);
        }else {
            tv_right_title_bar.setVisibility(View.GONE);
        }

        if("1".equals(getIntent().getStringExtra("type"))||"2".equals(getIntent().getStringExtra("type"))){

            if("1".equals(getIntent().getStringExtra("type"))){
                lv_vip_meneger.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                pos=i;
                        kipgroupid=manager.get(i).getGroup_id();
                        userid=manager.get(i).getUser_id();
//                strings = new ArrayList<>();
//                strings.add("踢出群聊");
                        goShop();
//                StyledDialog.buildIosSingleChoose(MemberListActivity.this, strings, new MyItemDialogListener() {
//                    @Override
//                    public void onItemClick(CharSequence text, int position) {
//                        kipGroup();
//                    }
//                    @Override
//                    public void onBottomBtnClick() {
//                    }
//                }).show();
                        return true;
                    }
                });
            }


            lv_vip_member.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                pos=i;
                    kipgroupid=memberListBean.getData().getNormalList().get(i).getGroup_id();
                    userid=memberListBean.getData().getNormalList().get(i).getUser_id();
                    strings = new ArrayList<>();
                    goShop();
//                if(0==flag){
//                    speak=memberListBean.getData().getGroupMemberList().get(i).getIs_ban();
////                }
//                memberid=memberListBean.getData().getGroupMemberList().get(i).getUser_id();
//                if("0".equals(speak)){
//                    strings.add("禁言该成员");
//                }else {
//                    strings.add("解禁该成员");
//                }
//                strings.add("踢出群聊");
//                setFinishOnTouchOutside(false);
//                StyledDialog.buildIosSingleChoose(MemberListActivity.this, strings, new MyItemDialogListener() {
//                    @Override
//                    public void onItemClick(CharSequence text, int position) {
////                        if("0".equals(speak)){
////                            ItemnoSpeak();
////                        }else {
////                            ItemSpeak();
////                        }
////                        ToastUtil.show("wobeidianjie"+pos);
//                        kipGroup();
//                    }
//                    @Override
//                    public void onBottomBtnClick() {
//                    }
//                }).show();
                    return true;
                }
            });
        }
        setData();
    }
    MemberListBean memberListBean;
    HostAdapter hostAdapter;
    ManagerListAdapter managerListAdapter;
    NorMalMemberAdapter norMalMemberAdapter;
    ArrayList<MemberListBean.DataBean.ManagerListBean> host=new ArrayList<>();
    List<MemberListBean.DataBean.ManagerListBean> manager=new ArrayList<>();
    public void setData(){
                RestClient.getYfmNovate(this).post(Constant.API.YFM_GETGROUPMEBER, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("group_id", groupid)
                .build(), new DmeycBaseSubscriber<MemberListBean>() {
                    @Override
                    public void onSuccess(final MemberListBean bean) {
                        memberListBean=bean;
                        host.clear();
                        manager.clear();
                       for (int i=0;i<bean.getData().getManagerList().size();i++){
                           if("1".equals(bean.getData().getManagerList().get(i).getIdentify_status())){
                               host.add(bean.getData().getManagerList().get(i));
                           }else {
                               manager.add(bean.getData().getManagerList().get(i));
                           }
                       }
                       if(manager.size()==0){
                           ll_mannager.setVisibility(View.GONE);
                           tv_textmanager.setVisibility(View.GONE);
                       }else {
                           ll_mannager.setVisibility(View.VISIBLE);
                           tv_textmanager.setVisibility(View.VISIBLE);
                              }
                       if(bean.getData().getNormalList().size()==0){
                           ll_nomal.setVisibility(View.GONE);
                           tv_nomalmember.setVisibility(View.GONE);
                       }else {
                           ll_nomal.setVisibility(View.VISIBLE);
                           tv_nomalmember.setVisibility(View.VISIBLE);
                          }
                        hostAdapter= new  HostAdapter();
                        lv_vip_host.setAdapter(hostAdapter);
                        managerListAdapter =new ManagerListAdapter();
                        lv_vip_meneger.setAdapter(managerListAdapter);
                        norMalMemberAdapter=new NorMalMemberAdapter();
                        lv_vip_member.setAdapter(norMalMemberAdapter);
                        tv_title.setText("群成员"+"("+(host.size()+manager.size()+memberListBean.getData().getNormalList().size())+")人");
                        tv_managernum.setText(manager.size()+"人");
                        tv_memnumber.setText(memberListBean.getData().getNormalList().size()+"人");
                    }
             });
       }
    class ViewHoder{
        ImageView iv_sex;
        CircleImageView iv_commicon;
        TextView tv_mmbname,tv_timein,tv_itemmoney;
        public ViewHoder(View view){
            iv_commicon=(CircleImageView)view.findViewById(R.id.iv_commicon);
            tv_mmbname=view.findViewById(R.id.tv_mmbname);
            tv_timein=view.findViewById(R.id.tv_timein);
            tv_itemmoney=view.findViewById(R.id.tv_itemmoney);
            iv_sex=view.findViewById(R.id.iv_sex);

        }
    }

    class NomalViewHoder{
        ImageView iv_sex;
        CircleImageView iv_commicon;
        TextView tv_mmbname,tv_timein,tv_itemmoney;
        public NomalViewHoder(View view){
            iv_commicon=(CircleImageView)view.findViewById(R.id.iv_commicon);
            tv_mmbname=view.findViewById(R.id.tv_mmbname);
            tv_timein=view.findViewById(R.id.tv_timein);
            tv_itemmoney=view.findViewById(R.id.tv_itemmoney);
            iv_sex=view.findViewById(R.id.iv_sex);

        }
    }

  String speakstate="";
    @OnClick(R.id.tv_right_title_bar)
    public void onClick(View view){
        int viewid=view.getId();
        if(R.id.tv_right_title_bar==viewid){

            if("0".equals(speakstate)){
                noSpeak();
            }else {
                allSpeak();
            }
        }
    }

    String  kipgroupid="";
    int  userid=0;
    public void noSpeak(){
        RestClient.getYfmNovate(this).post(Constant.API.YFM_GROUPNOSPEAK, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("id", getIntent().getStringExtra("groupid")+"")
//                .addParams("group_id", getIntent().getIntExtra("group_id",-1))
                .build(), new DmeycBaseSubscriber<PublishActionAfterBean>() {
            @Override
            public void onSuccess(PublishActionAfterBean bean) {
                ToastUtil.show(bean.getMsg());
                speakstate="1";
                tv_right_title_bar.setText("全体发言");
              }
        });
    }
    public void allSpeak(){
        RestClient.getYfmNovate(this).post(Constant.API.YFM_GROUPALLSPEAK, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("id", getIntent().getStringExtra("groupid")+"")
//                .addParams("group_id", getIntent().getIntExtra("group_id",-1))
                .build(), new DmeycBaseSubscriber<PublishActionAfterBean>() {
            @Override
            public void onSuccess(PublishActionAfterBean bean) {
                ToastUtil.show(bean.getMsg());
                speakstate="0";
                tv_right_title_bar.setText("全体禁言");
            }
        });
    }

    public void ItemnoSpeak(){
        RestClient.getYfmNovate(this).post(Constant.API.YFM_NOSPEAK, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("groupId", getIntent().getIntExtra("group_id",-1)+"")
                .addParams("memberId", memberid+"")
//                .addParams("group_id", getIntent().getIntExtra("group_id",-1))
                .build(), new DmeycBaseSubscriber<MemberListBean>() {
            @Override
            public void onSuccess(MemberListBean bean) {
                ToastUtil.show(bean.getMsg());
//           speak="1";
//           flag++;
//                memberListBean.getData().getGroupMemberList().get(pos).setIs_ban("1");
                strings.clear();
            }
        });
    }

    public void ItemSpeak(){
        RestClient.getYfmNovate(this).post(Constant.API.YFM_ALLSPEAK, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("groupId", getIntent().getIntExtra("group_id",-1)+"")
                 .addParams("memberId", memberid+"")
//                .addParams("group_id", getIntent().getIntExtra("group_id",-1))
                .build(), new DmeycBaseSubscriber<MemberListBean>() {
            @Override
            public void onSuccess(MemberListBean bean) {
                ToastUtil.show(bean.getMsg());
//                speak="0";
//                flag++;
//                memberListBean.getData().getGroupMemberList().get(pos).setIs_ban("0");
                strings.clear();
            }
        });
    }

    public class NorMalMemberAdapter extends BaseAdapter{
        public NorMalMemberAdapter(){

        }
        @Override
        public int getCount() {
            return memberListBean.getData().getNormalList().size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }
        NomalViewHoder nomalViewHoder;
        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            if(view==null){
                view = getLayoutInflater().inflate(R.layout.adapter_memberlist,null);
                nomalViewHoder=new NomalViewHoder(view);
                view.setTag(nomalViewHoder);
            }else {
                nomalViewHoder=(NomalViewHoder) view.getTag();
            }
//                        viewHoder.tv_timein.setText(bean.getData().getManagerList().get(i).get());
            GlideUtil.loadImage(MemberListActivity.this,memberListBean.getData().getNormalList().get(i).getHead_icon(),nomalViewHoder.iv_commicon);
            nomalViewHoder.tv_mmbname.setText(memberListBean.getData().getNormalList().get(i).getNick_name());
            if("1".equals(memberListBean.getData().getNormalList().get(i).getSex())){
                nomalViewHoder.iv_sex.setBackground(getResources().getDrawable(R.drawable.set_man));
            }else {
                nomalViewHoder.iv_sex.setBackground(getResources().getDrawable(R.drawable.set_woman));
            }
            if("1".equals(memberListBean.getData().getNormalList().get(i).getIdentify_status())){
                nomalViewHoder.tv_timein.setText("群主");
            }else if("2".equals(memberListBean.getData().getNormalList().get(i).getIdentify_status())){

                if("1".equals(getIntent().getStringExtra("type"))){
                    nomalViewHoder.tv_timein.setText("卸任管理员");
                  }else  if("2".equals(getIntent().getStringExtra("type"))){
                    nomalViewHoder.tv_timein.setText("管理员");
                    nomalViewHoder.tv_timein.setClickable(false);
                }else {
                    nomalViewHoder.tv_timein.setText("管理员");
                    nomalViewHoder.tv_timein.setClickable(false);
                  }

            }else{
                if("1".equals(getIntent().getStringExtra("type"))){
                    nomalViewHoder.tv_timein.setText("聘为管理员");
                }else   if("1".equals(getIntent().getStringExtra("type"))){
                    nomalViewHoder.tv_timein.setText("管理员");
                    nomalViewHoder.tv_timein.setClickable(false);
                }else {
                    nomalViewHoder.tv_timein.setText("群成员");
                    nomalViewHoder.tv_timein.setClickable(false);
                }
            }

            nomalViewHoder.tv_timein.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                     noManager(memberListBean.getData().getManagerList().get(i).get);
                    nomaral=i;
                    if("1".equals(getIntent().getStringExtra("type"))){
                        addManger();
                      }else {

                    }
                }
            });
//            lv_vip_member.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
//                @Override
//                public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
//                    contextMenu.add(Menu.NONE, 5, 0, "踢出群聊");
//                    ToastUtil.show("sssss"+i);
////                    kipgroupid=memberListBean.getData().getNormalList().get(i).getGroup_id();
////                    userid=memberListBean.getData().getNormalList().get(i).getUser_id();
////                if(bean.getData().getActivity_list().get(position).getIsDelete()==1){
////                    contextMenu.add(Menu.NONE, 3, ispk, "删除");
////                } if(bean.getData().getActivity_list().get(position).getIsEdit()==1){
////                    contextMenu.add(Menu.NONE, 4, ispk, "编辑");
////                } if(bean.getData().getActivity_list().get(position).getIsEnterSocre()==1){
////                    contextMenu.add(Menu.NONE, 5, ispk, "录入比分");
////                }
//                }
//            });
            return view;
        }

    }
         int managerpos;
       int managerposs;
         int nomaral;
    public class ManagerListAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return manager.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        ViewHoder viewHoder;
        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            if(view==null){
                view = getLayoutInflater().inflate(R.layout.adapter_memberlist,null);
                viewHoder=new ViewHoder(view);
                view.setTag(viewHoder);
            }else {
                viewHoder=(ViewHoder) view.getTag();
            }
            GlideUtil.loadImage(MemberListActivity.this,manager.get(i).getHead_icon(),viewHoder.iv_commicon);
            viewHoder.tv_mmbname.setText(manager.get(i).getNick_name());
            if("1".equals(manager.get(i).getSex())){
                viewHoder.iv_sex.setBackground(getResources().getDrawable(R.drawable.set_man));
            }else {
                viewHoder.iv_sex.setBackground(getResources().getDrawable(R.drawable.set_woman));
            }
            if("1".equals(manager.get(i).getIdentify_status())){
                viewHoder.tv_timein.setText("群主");
            }else if("2".equals(manager.get(i).getIdentify_status())){
                if("1".equals(getIntent().getStringExtra("type"))){
                    viewHoder.tv_timein.setText("卸任管理员");
                }else {
                    viewHoder.tv_timein.setText("管理员");
                    viewHoder.tv_timein.setClickable(false);
                }
            }else{
                if("1".equals(getIntent().getStringExtra("type"))){
                    viewHoder.tv_timein.setText("聘为管理员");
                }else {
                    viewHoder.tv_timein.setText("群成员");
                    viewHoder.tv_timein.setClickable(false);
                }
            }
            if("1".equals(manager.get(i).getIdentify_status())){

            }else {
                viewHoder.tv_timein.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                     noManager(memberListBean.getData().getManagerList().get(i).get);
                        managerposs=i;
                        if("1".equals(getIntent().getStringExtra("type"))){
                            noManager();
                        }else {

                        }
                    }
                });
            }
//            lv_vip_meneger.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
//                @Override
//                public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
//                    contextMenu.add(Menu.NONE, 5, 0, "踢出群聊");
//                    ToastUtil.show("iiii"+i);
//                    kipgroupid=manager.get(i).getGroup_id();
//                    userid=manager.get(i).getUser_id();
////                if(bean.getData().getActivity_list().get(position).getIsDelete()==1){
////                    contextMenu.add(Menu.NONE, 3, ispk, "删除");
////                } if(bean.getData().getActivity_list().get(position).getIsEdit()==1){
////                    contextMenu.add(Menu.NONE, 4, ispk, "编辑");
////                } if(bean.getData().getActivity_list().get(position).getIsEnterSocre()==1){
////                    contextMenu.add(Menu.NONE, 5, ispk, "录入比分");
////                }
//                }
//            });

            return view;
        }

    }

    public class HostAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return host.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        ViewHoder viewHoder;
        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {

            if(view==null){
                view = getLayoutInflater().inflate(R.layout.adapter_memberlist,null);
                viewHoder=new ViewHoder(view);
                view.setTag(viewHoder);
            }else {
                viewHoder=(ViewHoder) view.getTag();
            }
            GlideUtil.loadImage(MemberListActivity.this,host.get(i).getHead_icon(),viewHoder.iv_commicon);
            viewHoder.tv_mmbname.setText(host.get(i).getNick_name());
            if("1".equals(host.get(i).getSex())){
                viewHoder.iv_sex.setBackground(getResources().getDrawable(R.drawable.set_man));
            }else {
                viewHoder.iv_sex.setBackground(getResources().getDrawable(R.drawable.set_woman));
            }
            if("1".equals(host.get(i).getIdentify_status())){
                viewHoder.tv_timein.setText("群主");
            }else if("2".equals(host.get(i).getIdentify_status())){
                if("1".equals(getIntent().getStringExtra("type"))){
                    viewHoder.tv_timein.setText("卸任管理员");
                }else {
                    viewHoder.tv_timein.setText("管理员");
                    viewHoder.tv_timein.setClickable(false);
                }
            }else{
                if("1".equals(getIntent().getStringExtra("type"))){
                    viewHoder.tv_timein.setText("聘为管理员");
                }else {
                    viewHoder.tv_timein.setText("群成员");
                    viewHoder.tv_timein.setClickable(false);
                }
            }
            if("1".equals(memberListBean.getData().getManagerList().get(i).getIdentify_status())){

            }else {
                viewHoder.tv_timein.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                     noManager(memberListBean.getData().getManagerList().get(i).get);
                        managerpos=i;
                        if("1".equals(getIntent().getStringExtra("type"))){
                            noManager();
                        }else {

                        }

                    }
                });
            }


            return view;
        }

    }

    public void noManager(){
        RestClient.getYfmNovate(this).post(Constant.API.YFM_NOMANAGER, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("group_id", getIntent().getStringExtra("groupid")+"")
                .addParams("id",manager.get(managerposs).getId())
//                .addParams("group_id", getIntent().getIntExtra("group_id",-1))
                .build(), new DmeycBaseSubscriber<PublishActionAfterBean>() {
            @Override
            public void onSuccess(PublishActionAfterBean bean) {
                setData();
                managerListAdapter.notifyDataSetChanged();
                norMalMemberAdapter.notifyDataSetChanged();
            }
    });
  }

    public void addManger(){
        RestClient.getYfmNovate(this).post(Constant.API.YFM_ADDMANAGER, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("group_id", getIntent().getStringExtra("groupid")+"")
                .addParams("id",memberListBean.getData().getNormalList().get(nomaral).getId())
//                .addParams("group_id", getIntent().getIntExtra("group_id",-1))
                .build(), new DmeycBaseSubscriber<PublishActionAfterBean>() {
            @Override
            public void onSuccess(PublishActionAfterBean bean) {

                setData();
                managerListAdapter.notifyDataSetChanged();
                norMalMemberAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        //关键代码在这里
        AdapterView.AdapterContextMenuInfo menuInfo;
        menuInfo =(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        switch (item.getItemId()){
            case 5:
//            kipGroup();
                break;
        }
        return super.onContextItemSelected(item);
    }


    public void kipGroup(){
        RestClient.getYfmNovate(this).post(Constant.API.YFM_KIPGROUP, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("group_id", kipgroupid)
                .addParams("user_id",userid)
                .build(), new DmeycBaseSubscriber<PublishActionAfterBean>() {
            @Override
            public void onSuccess(PublishActionAfterBean bean) {
             ToastUtil.show(bean.getMsg());
                setData();
                managerListAdapter.notifyDataSetChanged();
                norMalMemberAdapter.notifyDataSetChanged();
            }
        });
    }

    Dialog dialog;
    ListView lv_shop;
    TextView alltv_price;
    TextView tv_toinfo;
    public void goShop(){
        dialog  = new Dialog(MemberListActivity.this, R.style.MyDialog);
        //设置它的ContentView
        setFinishOnTouchOutside(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_outcomm);
        Window window = dialog.getWindow();

        WindowManager.LayoutParams lp = window.getAttributes();
        window.setGravity(Gravity.CENTER);
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高
        WindowManager.LayoutParams params = window.getAttributes();
        params.height = (int) (d.getHeight() * 0.3); // 高度设置为屏幕的0.5
        params.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.6
        window.setAttributes(params);
        tv_toinfo=dialog.findViewById(R.id.tv_toinfo);

        dialog.show();
        tv_toinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kipGroup();
            }
        });
//        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialogInterface) {
//
//
//            }
//        });

    }

 }