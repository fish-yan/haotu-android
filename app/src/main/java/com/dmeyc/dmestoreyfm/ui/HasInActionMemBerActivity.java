package com.dmeyc.dmestoreyfm.ui;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.ActionInBean;
import com.dmeyc.dmestoreyfm.bean.IdenityBean;
import com.dmeyc.dmestoreyfm.bean.ProjectTypeBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.dmeyc.dmestoreyfm.wedgit.NoScrollListView;

import java.util.ArrayList;

import butterknife.Bind;

public class HasInActionMemBerActivity extends BaseActivity {

    @Bind(R.id.cv_header)
    CircleImageView cv_header;
    @Bind(R.id.tv_name)
    TextView tv_name;
    @Bind(R.id.iv_sex)
    ImageView iv_sex;
    @Bind(R.id.lv_vip_meneger)
    NoScrollListView lv_vip_meneger;
    @Bind(R.id.lv_vip_member)
    NoScrollListView lv_vip_member;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_hasinactionmemberlist;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        getdata();
    }
    ArrayList<IdenityBean> al1=new ArrayList<>();
    ArrayList<IdenityBean> al2=new ArrayList<>();
    ArrayList<IdenityBean> al3=new ArrayList<>();
    public void getdata(){
        ParamMap.Build PB=   new ParamMap.Build();
        PB.addParams("activity_id", getIntent().getIntExtra("activityid",-1));
        RestClient.getYfmNovate(HasInActionMemBerActivity.this).post(Constant.API.YFM_GETACTIONMEMBER,
                PB.addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                        .addParams("pageIndex", 1)
                        .addParams("pageSize", 100)
                        .build(),
                new DmeycBaseSubscriber<ActionInBean>(HasInActionMemBerActivity.this){

                    @Override
                    public void onSuccess(ActionInBean bean) {

                        for (int i=0;i<bean.getData().size();i++){
                            IdenityBean idenityBean;
                            if("1".equals(bean.getData().get(i).getIndentity())){
                                idenityBean=new IdenityBean();
                                idenityBean.setNick_name(bean.getData().get(i).getNick_name());
                                idenityBean.setSex(bean.getData().get(i).getSex());
                                idenityBean.setUrl(bean.getData().get(i).getUrl());
                                idenityBean.setUser_id(bean.getData().get(i).getUser_id());
                                idenityBean.setIndentity(bean.getData().get(i).getIndentity());
                                al1.add(idenityBean);
                                al3.add(idenityBean);
                            }else if("2".equals(bean.getData().get(i).getIndentity())){
                                idenityBean=new IdenityBean();
                                idenityBean.setNick_name(bean.getData().get(i).getNick_name());
                                idenityBean.setSex(bean.getData().get(i).getSex());
                                idenityBean.setUrl(bean.getData().get(i).getUrl());
                                idenityBean.setUser_id(bean.getData().get(i).getUser_id());
                                idenityBean.setIndentity(bean.getData().get(i).getIndentity());
                                al2.add(idenityBean);
                                al3.add(idenityBean);
                            }else if("3".equals(bean.getData().get(i).getIndentity())){
                                idenityBean=new IdenityBean();
                                idenityBean.setNick_name(bean.getData().get(i).getNick_name());
                                idenityBean.setSex(bean.getData().get(i).getSex());
                                idenityBean.setUrl(bean.getData().get(i).getUrl());
                                idenityBean.setUser_id(bean.getData().get(i).getUser_id());
                                idenityBean.setIndentity(bean.getData().get(i).getIndentity());
                                al3.add(idenityBean);
                            }
                        }
//                        al3.addAll(al1);
//                        al3.addAll(al2);
//                        GlideUtil.loadImage(HasInActionMemBerActivity.this,al1.get(0).getUrl(),cv_header);
//                        tv_name.setText(al1.get(0).getNick_name());
//                        if(al1.get(0).getSex().equals("1")){
//                            iv_sex.setBackground(getResources().getDrawable(R.drawable.woman_check));
//                        }else {
//                            iv_sex.setBackground(getResources().getDrawable(R.drawable.man_check));
//                        }
                        lv_vip_meneger.setAdapter(new BaseAdapter() {
                            @Override
                            public int getCount() {
                                return al2.size();
                            }

                            @Override
                            public Object getItem(int i) {
                                return null;
                            }

                            @Override
                            public long getItemId(int i) {
                                return 0;
                            }
                            MemberVeiwHorder memberVeiwHorder;
                            @Override
                            public View getView(int i, View view, ViewGroup viewGroup) {
                                if(view==null){
                                    view=   getLayoutInflater().inflate(R.layout.adapter_actioninmember,null);
                                    memberVeiwHorder=new MemberVeiwHorder(view);
                                    view.setTag(memberVeiwHorder);
                                }else {
                                    memberVeiwHorder=(MemberVeiwHorder) view.getTag();
                                }
                                GlideUtil.loadImage(HasInActionMemBerActivity.this,al2.get(i).getUrl(),memberVeiwHorder.cv_itemheader);
                                memberVeiwHorder.tv_itemname.setText(al2.get(i).getNick_name());
                                if(al2.get(i).getSex().equals(2)){
                                    memberVeiwHorder.iv_itemsex.setBackground(getResources().getDrawable(R.drawable.man_check));
                                }else {
                                    memberVeiwHorder.iv_itemsex.setBackground(getResources().getDrawable(R.drawable.woman_check));
                                }

                                return view;

                            }

                            class MemberVeiwHorder {
                                CircleImageView cv_itemheader;
                                TextView tv_itemname;
                                ImageView iv_itemsex;
                                public MemberVeiwHorder(View view){
                                    cv_itemheader=  view.findViewById(R.id.cv_itemheader);
                                    tv_itemname=view.findViewById(R.id.tv_itemname);
                                    iv_itemsex=view.findViewById(R.id.iv_itemsex);
                                }

                            }
                        });
                        lv_vip_member.setAdapter(new BaseAdapter() {
                            @Override
                            public int getCount() {
                                return al3.size();
                            }

                            @Override
                            public Object getItem(int i) {
                                return null;
                            }

                            @Override
                            public long getItemId(int i) {
                                return 0;
                            }
                            MemberVeiwHorder memberVeiwHorder;
                            @Override
                            public View getView(int i, View view, ViewGroup viewGroup) {
                                if(view==null){
                                    view=   getLayoutInflater().inflate(R.layout.adapter_actioninmember,null);
                                    memberVeiwHorder=new MemberVeiwHorder(view);
                                    view.setTag(memberVeiwHorder);
                                }else {
                                    memberVeiwHorder=(MemberVeiwHorder) view.getTag();
                                }
                                GlideUtil.loadImage(HasInActionMemBerActivity.this,al3.get(i).getUrl(),memberVeiwHorder.cv_itemheader);
                                memberVeiwHorder.tv_itemname.setText(al3.get(i).getNick_name());
                                if(al3.get(i).getSex()!=null){
                                    if(al3.get(i).getSex().equals("1")){
                                        memberVeiwHorder.iv_itemsex.setBackground(getResources().getDrawable(R.drawable.man_check));
                                    }else {
                                        memberVeiwHorder.iv_itemsex.setBackground(getResources().getDrawable(R.drawable.woman_check));
                                    }
                                }
                                return view;
                            }

                            class MemberVeiwHorder {
                                CircleImageView cv_itemheader;
                                TextView tv_itemname;
                                ImageView iv_itemsex;
                                public MemberVeiwHorder(View view){
                                    cv_itemheader=  view.findViewById(R.id.cv_itemheader);
                                    tv_itemname=view.findViewById(R.id.tv_itemname);
                                    iv_itemsex=view.findViewById(R.id.iv_itemsex);
                                }
                            }
                        });
                    }
                });
    }

}