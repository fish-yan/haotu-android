package com.dmeyc.dmestoreyfm.ui;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.BClientActionBean;
import com.dmeyc.dmestoreyfm.bean.BClientChangesetBean;
import com.dmeyc.dmestoreyfm.bean.PublishActionAfterBean;
import com.dmeyc.dmestoreyfm.bean.SetChangeBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.GradeViewForScrollView;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class BClientChangeSetActivity extends BaseActivity {
@Bind(R.id.tv_tiemhor)
        TextView tv_tiemhor;
    @Bind(R.id.tv_time_mone)
    TextView tv_time_mone;
    @Bind(R.id.tv_activityname)
    TextView tv_activityname;
    @Bind(R.id.tv_groupname)
    TextView tv_groupname;
    @Bind(R.id.tv_owername)
    TextView tv_owername;
//    @Bind(R.id.btn_regist)
//    Button btn_regist;
    GradeViewForScrollView no_gv;

//    @Bind(R.id.cv_setchan)
//    CircleImageView cv_setchan;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_bclientchangeset;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
    BClientChangeSetActivity.setAdapter setadapter;
    @Override
    protected void initData() {
        no_gv=mRootView.findViewById(R.id.no_gv);
        getData();
    }

    int teammemberid;
    int clickgrpos=-1;
    int clicktype=-1;


    int pos1=-1;
    int pos2=-2;
    int flag=0;
//    int flage=0;
//    ArrayList<CircleImageView> alc1=new ArrayList<>();
//    ArrayList<CircleImageView>alc2=new ArrayList<>();
ArrayList<Integer> al=new ArrayList<>();
    public class setAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return bClientChangesetBean.getData().getMember_list().size()/2;
//            return 10;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }
      Holders holders;
        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            if(view==null){
                view=getLayoutInflater().inflate(R.layout.adapter_ordersit,null);
                holders=new Holders(view);
                view.setTag(holders);
            }else {
                holders=(Holders) view.getTag();
            }
            if(i>=9){
                holders.tv_numb.setText(""+(i+1));
            }else {
                holders.tv_numb.setText("0"+(i+1));
            }

            if(TextUtils.isEmpty(bClientChangesetBean.getData().getMember_list().get(i*2).getUser_nickname())){
                holders.tv_set1name.setText(bClientChangesetBean.getData().getMember_list().get(i*2).getUser_nickname());
            }else {
                holders.tv_set1name.setText(bClientChangesetBean.getData().getMember_list().get(i*2).getUser_nickname());
            }
            if(TextUtils.isEmpty(bClientChangesetBean.getData().getMember_list().get(i*2+1).getUser_nickname())){
                holders.tv_set2name.setText(bClientChangesetBean.getData().getMember_list().get(i*2+1).getUser_nickname());
            }else {
                holders.tv_set2name.setText(bClientChangesetBean.getData().getMember_list().get(i*2+1).getUser_nickname());
            }

//            if(flage==i){
//                flage++;
//                alc1.add(holders.iv_set1);
//                alc2.add(holders.iv_set2);
//            }

            if(clickgrpos==i){
                if(clicktype==0){
                    GlideUtil.loadImage(BClientChangeSetActivity.this,SPUtils.getStringData(Constant.Config.AVATAR),holders.iv_set1);
                    Glide.with(BClientChangeSetActivity.this).load(bClientChangesetBean.getData().getMember_list().get(i*2+1)).into(holders.iv_set2);
                    holders.tv_set1name.setText(SPUtils.getStringData(Constant.Config.NICK_NAME));
//                            holders.tv_set2name.setText("");
                    holders.iv_setsex1.setVisibility(View.VISIBLE);
                    holders.iv_setsex2.setVisibility(View.GONE);
                    if("1".equals(SPUtils.getStringData(Constant.Config.SEX))){
                        holders.iv_setsex1.setBackground(getResources().getDrawable(R.drawable.set_man));
                    }else {
                        holders.iv_setsex1.setBackground(getResources().getDrawable(R.drawable.set_woman));
                    }
                }else if(clicktype==1){
                    Glide.with(BClientChangeSetActivity.this).load(bClientChangesetBean.getData().getMember_list().get(i*2)).into(holders.iv_set1);
                    GlideUtil.loadImage(BClientChangeSetActivity.this,SPUtils.getStringData(Constant.Config.AVATAR),holders.iv_set2);
//                            holders.tv_set1name.setText("");
                    holders.tv_set2name.setText(SPUtils.getStringData(Constant.Config.NICK_NAME));
                    holders.iv_setsex1.setVisibility(View.GONE);
                    holders.iv_setsex2.setVisibility(View.VISIBLE);
                    if("1".equals(SPUtils.getStringData(Constant.Config.SEX))){
                        holders.iv_setsex2.setBackground(getResources().getDrawable(R.drawable.set_man));
                    }else {
                        holders.iv_setsex2.setBackground(getResources().getDrawable(R.drawable.set_woman));
                    }
                }
            }else {
                if(TextUtils.isEmpty(bClientChangesetBean.getData().getMember_list().get(i*2+1).getUser_nickname())){
                    holders.iv_setsex2.setVisibility(View.GONE);
                    holders.tv_set2name.setText("");
                    Glide.with(BClientChangeSetActivity.this).load(R.drawable.set_rabbit).into(holders.iv_set2);
                    teammemberid=bClientChangesetBean.getData().getMember_list().get(i*2+1).getTeam_member_id();
                }else {
                    holders.iv_setsex2.setVisibility(View.VISIBLE);
                    if("1".equals(bClientChangesetBean.getData().getMember_list().get(i*2+1).getSex())){
                        holders.iv_setsex2.setBackground(getResources().getDrawable(R.drawable.set_man));
                    }else {
                        holders.iv_setsex2.setBackground(getResources().getDrawable(R.drawable.set_woman));
                    }
                    holders.tv_set2name.setText(bClientChangesetBean.getData().getMember_list().get(i*2+1).getUser_nickname());
                    Glide.with(BClientChangeSetActivity.this).load(bClientChangesetBean.getData().getMember_list().get(i*2+1).getUser_logo()).into(holders.iv_set2);
                }

                if(TextUtils.isEmpty(bClientChangesetBean.getData().getMember_list().get(i*2).getUser_nickname())){
                    holders.iv_setsex1.setVisibility(View.GONE);
                    holders.tv_set1name.setText("");
                    Glide.with(BClientChangeSetActivity.this).load(R.drawable.set_rabbit).into(holders.iv_set1);
                }else {
                    holders.iv_setsex1.setVisibility(View.VISIBLE);
                    if("1".equals(bClientChangesetBean.getData().getMember_list().get(i*2).getSex())){
                        holders.iv_setsex1.setBackground(getResources().getDrawable(R.drawable.set_man));
                    }else {
                        holders.iv_setsex1.setBackground(getResources().getDrawable(R.drawable.set_woman));
                    }
                    holders.tv_set1name.setText(bClientChangesetBean.getData().getMember_list().get(i*2).getUser_nickname());
                    Glide.with(BClientChangeSetActivity.this).load(bClientChangesetBean.getData().getMember_list().get(i*2).getUser_logo()).into(holders.iv_set1);
                }


            }




            holders.iv_set1.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("NewApi")
                @Override
                public void onClick(View view) {
//                                holders.tv_set1name.setBackgroundResource();
                    flag++;
//                                ToastUtil.show(i+"sss");
                    pos1=i;
                    al.add(i*2);
//                    holders.iv_set1.setBackground(BClientChangeSetActivity.this.getDrawable(R.drawable.set_ku));
                    if(2==flag){
                        chageSet();
                    }
//                    GlideUtil.loadImage(BClientChangeSetActivity.this,bClientChangesetBean.getData().getMember_list().get(i*2).getUser_logo(),cv_setchan);
                    notifyDataSetChanged();
                }
            });
            holders.iv_set2.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("NewApi")
                @Override
                public void onClick(View view) {
                                pos2=i;
                    flag++;
                    al.add(i*2+1);
//                                ToastUtil.show(i+"sss");
//                    holders.iv_set2.setBackground(BClientChangeSetActivity.this.getDrawable(R.drawable.set_ku));
                    if(2==flag){
                        chageSet();
                    }
//                    GlideUtil.loadImage(BClientChangeSetActivity.this,bClientChangesetBean.getData().getMember_list().get(i*2+1).getUser_logo(),cv_setchan);
                    notifyDataSetChanged();
                }
            });

//            holders.iv_set1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
////                     ToastUtil.show(i+"ss");
////                     alc1.clear();
////                     alc2.clear();
//                    if(!TextUtils.isEmpty(bClientChangesetBean.getData().getMember_list().get(i*2).getUser_nickname())){
//                        ToastUtil.show("位置被坐");
//                        return;
//                    }
//                    teammemberid=bClientChangesetBean.getData().getMember_list().get(i*2).getTeam_member_id();
//                    clickgrpos=i;
//                    clicktype=0;
//                    notifyDataSetChanged();
//                }
//            });
//            holders.iv_set2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
////                    ToastUtil.show(i+"ss");
////                    alc1.clear();
////                    alc2.clear();
//                    if(!TextUtils.isEmpty(bClientChangesetBean.getData().getMember_list().get(i*2+1).getUser_nickname())){
//                        ToastUtil.show("位置被坐");
//                        return;
//                    }
//                    teammemberid=bClientChangesetBean.getData().getMember_list().get(i*2+1).getTeam_member_id();
//                    clickgrpos=i;
//                    clicktype=1;
//                    notifyDataSetChanged();
//                }
//            });
            return view;
        }
    }

    class Holders{
        CircleImageView iv_set1,iv_set2;
        TextView tv_set1name,tv_set2name,tv_numb;
        ImageView iv_setsex2,iv_setsex1;
        public Holders(View view){
            iv_set1=view.findViewById(R.id.iv_set1);
            iv_set2=view.findViewById(R.id.iv_set2);
            tv_set1name=view.findViewById(R.id.tv_set1name);
            tv_set2name=view.findViewById(R.id.tv_set2name);
            tv_numb=view.findViewById(R.id.tv_numb);
            iv_setsex2=view.findViewById(R.id.iv_setsex2);
            iv_setsex1=view.findViewById(R.id.iv_setsex1);
        }
    }
    String time;
    String data[];
    String hor;
    String day;
    BClientChangesetBean bClientChangesetBean;
    public void getData(){

        RestClient.getYfmNovate(BClientChangeSetActivity.this).post(Constant.API.YFM_GETCOMMINFO, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("activity_id", getIntent().getIntExtra("activityid",-1))
                .build(), new DmeycBaseSubscriber<BClientChangesetBean>() {
            @Override
            public void onSuccess(BClientChangesetBean bean) {
                bClientChangesetBean=bean;
//                      ToastUtil.show(bean.getMsg());
                       time=bean.getData().getStart_date();
                if(!TextUtils.isEmpty(time)){
                    data=  time.split(" ");
                    day=  data[0];
                    hor=data[1];
                    tv_time_mone.setText(day.split("-")[1]+"月"+day.split("-")[2]+"日");
                    tv_tiemhor.setText(hor.split(":")[0]+":"+hor.split(":")[1]);

                    tv_activityname.setText(bean.getData().getGroup_name());
                    tv_groupname.setText(bean.getData().getActivity_address());
                    tv_owername.setText("组织者："+bean.getData().getNick_name());
//                    tv_adress.setText("地址："+getIntent().getStringExtra("adress"));
                }
                al.clear();
                setadapter=new BClientChangeSetActivity.setAdapter();
                no_gv.setAdapter(setadapter);

            }
        });

        class Holders{
            CircleImageView iv_set1,iv_set2;
            TextView tv_set1name,tv_set2name,tv_numb;
            ImageView iv_setsex2,iv_setsex1;
            public Holders(View view){
                iv_set1=view.findViewById(R.id.iv_set1);
                iv_set2=view.findViewById(R.id.iv_set2);
                tv_set1name=view.findViewById(R.id.tv_set1name);
                tv_set2name=view.findViewById(R.id.tv_set2name);
                tv_numb=view.findViewById(R.id.tv_numb);
                iv_setsex2=view.findViewById(R.id.iv_setsex2);
                iv_setsex1=view.findViewById(R.id.iv_setsex1);
            }
        }

    }



    public void chageSet(){

        pos1=al.get(0);
        pos2=al.get(1);
        flag=0;
        ParamMap.Build pb=   new ParamMap.Build();
        if(bClientChangesetBean.getData().getMember_list().get(pos1).getSignUpId()==0){
            pb .addParams("sign_up_a_id","");
        }else {
            pb .addParams("sign_up_a_id",bClientChangesetBean.getData().getMember_list().get(pos1).getSignUpId());
        }
        if(bClientChangesetBean.getData().getMember_list().get(pos2).getSignUpId()==0){
            pb.addParams("sign_up_b_id","");
        }else {
            pb .addParams("sign_up_b_id",bClientChangesetBean.getData().getMember_list().get(pos2).getSignUpId());
        }
        RestClient.getYfmNovate(this).post(Constant.API.YFM_CHANGEd, pb
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("member_a_id",bClientChangesetBean.getData().getMember_list().get(pos1).getTeam_member_id())
                .addParams("member_b_id",bClientChangesetBean.getData().getMember_list().get(pos2).getTeam_member_id())
                .build(), new DmeycBaseSubscriber<PublishActionAfterBean>() {
            @Override
            public void onSuccess(PublishActionAfterBean bean) {
                ToastUtil.show(bean.getMsg());
                al.clear();
                setadapter.notifyDataSetChanged();
                getData();
            }
        });

    }

//    @OnClick(R.id.btn_regist)
//    public void onClick(View view){
//       int viewid=view.getId();
//       if(R.id.btn_regist==viewid){
//           if(2==flag){
//               chageSet();
//           }else {
//               ToastUtil.show("请选择要调换的位置");
//           }
//           setadapter.notifyDataSetChanged();
//       }
//    }
}
