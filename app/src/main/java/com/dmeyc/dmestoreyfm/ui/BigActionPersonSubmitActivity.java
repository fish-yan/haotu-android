package com.dmeyc.dmestoreyfm.ui;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
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

import com.bumptech.glide.Glide;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.IsTrueNameBean;
import com.dmeyc.dmestoreyfm.bean.ProjectTypeBean;
import com.dmeyc.dmestoreyfm.bean.PublishActionAfterBean;
import com.dmeyc.dmestoreyfm.bean.SeatBean;
import com.dmeyc.dmestoreyfm.bean.WXSubmitBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.GradeViewForScrollView;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.dmeyc.dmestoreyfm.wedgit.NoScrollListView;
import com.tamic.novate.Throwable;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class BigActionPersonSubmitActivity extends BaseActivity {
    private LinearLayout ll_orderset;
    @Bind(R.id.ll_setnumb)
    LinearLayout ll_setnumb;
    @Bind(R.id.tv_set1name)
    TextView tv_set1name;
    @Bind(R.id.tv_set2name)
    TextView tv_set2name;

//    @Bind(R.id.ll_wxpay)
//    LinearLayout ll_wxpay;
//    @Bind(R.id.ll_vip)
//    LinearLayout ll_vip;
//    @Bind(R.id.ll_alipay)
//    LinearLayout ll_alipay;
//    @Bind(R.id.ll_bank)
//    LinearLayout ll_bank;

//    @Bind(R.id.rb_alipay)
//    RadioButton rb_alipay;
//    @Bind(R.id.rb_vip)
//    RadioButton rb_vip;
//    @Bind(R.id.rb_wx)
//    RadioButton rb_wx;
//    @Bind(R.id.rb_bank)
//    RadioButton rb_bank;


    @Bind(R.id.tv_submitorder)
    TextView tv_submitorder;
    @Bind(R.id.tv_activityname)
    TextView tv_activityname;
    @Bind(R.id.tv_groupname)
    TextView tv_groupname;
    @Bind(R.id.tv_owername)
    TextView tv_owername;
    @Bind(R.id.tv_adress)
    TextView tv_adress;
    @Bind(R.id.tv_tiemhor)
    TextView tv_tiemhor;
    @Bind(R.id.tv_time_mone)
    TextView tv_time_mone;
    @Bind(R.id.tv_amount)
    TextView tv_amount;
    @Bind(R.id.iv_set1)
    ImageView iv_set1;
    @Bind(R.id.iv_set2)
    ImageView iv_set2;


    @Bind(R.id.rv_member)
    RecyclerView rv_member;
    @Bind(R.id.no_list)
    NoScrollListView no_list;
    @Bind(R.id.no_gv)
    GradeViewForScrollView no_gv;
    //@Bind(R.id.iv_setsex2)
//    ImageView iv_setsex2;
//@Bind(R.id.iv_setsex1)
//    ImageView iv_setsex1;
    @Bind(R.id.iv_setviewable)
    ImageView iv_setviewable;
    @Bind(R.id.tv_hassument)
    TextView tv_hassument;
    int setviable=1;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_submitorder;
    }
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
    String time;
    String data[];
    String hor;
    String day;
    @Override
    protected void initData() {
        no_gv.setVisibility(View.GONE);
        iv_setviewable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(1==setviable){
                    no_gv.setVisibility(View.GONE);
                    setviable=0;
                }else {
                    no_gv.setVisibility(View.VISIBLE);
                    setviable=1;
                }
            }
        });
        tv_activityname.setText(getIntent().getStringExtra("activityname"));
        tv_groupname.setText("俱乐部："+getIntent().getStringExtra("groupname"));
        tv_owername.setText("组织者："+getIntent().getStringExtra("owername"));
        tv_adress.setText("地址："+getIntent().getStringExtra("adress"));

        time=getIntent().getStringExtra("time");
        if(!TextUtils.isEmpty(time)){
            data=  time.split(" ");
            day=  data[0];
            hor=data[1];
            tv_time_mone.setText(day.split("-")[1]+"月"+day.split("-")[2]+"日");
            tv_tiemhor.setText(hor.split(":")[0]+":"+hor.split(":")[1]);
        }
        ll_orderset=mRootView.findViewById(R.id.ll_orderset);

        setData();
    }
    TextView textView;
    ArrayList<Integer> poss=new ArrayList<>();
    int pos;
    SeatBean seatBean;

    int teamid1;
    int teamid2;
    int submitid;
    String setname1;
    String setname2;
    private void setData() {

//                YFM_ACTIVITSET
        RestClient.getYfmNovate(this).post(Constant.API.YFM_ACTIONTEAM, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("activity_id", getIntent().getIntExtra("activityid",-1))
                .build(), new DmeycBaseSubscriber<SeatBean>() {
            @Override
            public void onSuccess(final SeatBean bean) {
                seatBean=bean;
//                         getPayTyoe();
                setadapter=new BigActionPersonSubmitActivity.setAdapter();
                no_gv.setAdapter(setadapter);
                if("1".equals(bean.getData().getGroupType())||"4".equals(bean.getData().getGroupType())){
//                    if(bean.getData().getSafeAmount()==0){
//                        tv_hassument.setVisibility(View.VISIBLE);
//                    }else {
//                        tv_hassument.setVisibility(View.GONE);
//                    }
                }else {
//                    tv_hassument.setVisibility(View.GONE);
                }
                tv_amount.setText(seatBean.getData().getTotalAmount()+"元");
                pty=bean.getData().getPayList();
                paytype=bean.getData().getPayList().get(0).getPay_code();
                payadapter=new BigActionPersonSubmitActivity.payAdapter();
                no_list.setAdapter(payadapter);
                no_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        clickpos=i;
                        paytype=pty.get(i).getPay_code();
                        payadapter.notifyDataSetChanged();
                    }
                });

//                LinearLayoutManager lm=  new LinearLayoutManager(SubmitOrderActivity.this);
//                lm.setOrientation(OrientationHelper.HORIZONTAL);
//                rv_member.setLayoutManager(lm);
//                rv_member.addItemDecoration(new RecyclerView.ItemDecoration() {
//                    @Override
//                    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                        int pos = parent.getChildLayoutPosition(view);
////                        if(pos == 0){
//                            outRect.left = DensityUtil.dip2px(5);
////                        }
//                        outRect.right = DensityUtil.dip2px(5);
//                    }
//                });

//                rv_member.setAdapter(new SubmitOrderActivity.iconAdapter());

//                iv_set1.setOnClickListener(new View.OnClickListener() {
//                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//                    @Override
//                    public void onClick(View view) {
//
//                            if(TextUtils.isEmpty(setname1)){
//                                submitid=teamid1;
//                            }else {
//                                ToastUtil.show("座位被占");
//                            }
//                        iv_set1.setBackground(SubmitOrderActivity.this.getDrawable(R.drawable.set_ku));
//                        iv_set2.setBackground(SubmitOrderActivity.this.getDrawable(R.drawable.icon_seat));
//
//                    }
//                });
//                iv_set2.setOnClickListener(new View.OnClickListener() {
//                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//                    @Override
//                    public void onClick(View view) {
//                        if(TextUtils.isEmpty(setname2)){
//                            submitid=teamid2;
//                        }else {
//                            ToastUtil.show("座位被占");
//                        }
//
//                        iv_set1.setBackground(SubmitOrderActivity.this.getDrawable(R.drawable.icon_seat));
//                        iv_set2.setBackground(SubmitOrderActivity.this.getDrawable(R.drawable.set_ku));
//                    }
//
//                });
                registerReceiver(new BigActionPersonSubmitActivity.WXBroadcastReceiver(),
                        new IntentFilter("wx_success"));
            }
            @Override
            public void onError(Throwable e) {

            }
        });
    }

    @OnClick({R.id.tv_submitorder})
    public void onClick(View view){
        int viewid=view.getId();
        if(R.id.tv_submitorder==viewid){
//     goShop();
            if(TextUtils.isEmpty(paytype)){
                ToastUtil.show("请选择支付方式");
                return;
            }
            if("1".equals(paytype)){
                submitorderWX();
            }else if("4".equals(paytype)) {
                submitorder();
            }
        }
    }

    public void submitorder(){

//        if(teammemberid==0){
//            ToastUtil.show("请选择座位");
//            return;
//        }
        ParamMap.Build pb=  new ParamMap.Build();
        if(teammemberid==0){
        }else {
            pb.addParams("activity_member_id", teammemberid);
        }
        RestClient.getYfmNovate(this).post(Constant.API.YFM_ACTIVITBAOMING,pb
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("group_id", seatBean.getData().getGroup_id())
                .addParams("activity_id", seatBean.getData().getActivity_id())
                .addParams("sign_up_amount", seatBean.getData().getSignupAmount())
                .addParams("totalAmount", seatBean.getData().getTotalAmount())
                .addParams("safeAmount", seatBean.getData().getSafeAmount())
                .addParams("appType",2)

//               .addParams("recommendUserId", seatBean.getData().getSafeAmount())
//                .addParams("couponId", seatBean.getData().getSafeAmount())
//               .addParams("discountAmount", seatBean.getData().getSafeAmount())
//                .addParams("appType","2")
                .addParams("payType",paytype)
                .build(), new DmeycBaseSubscriber<PublishActionAfterBean>() {
            @Override
            public void onSuccess(PublishActionAfterBean bean) {
                ToastUtil.show("报名成功");
                checkIsTrueName();
//                finish();
            }
        });
    }


    WXSubmitBean wxSubmitBean;
    public void submitorderWX(){

//        if(teammemberid==0){
//            ToastUtil.show("请选择座位");
//            return;
//        }
        ParamMap.Build pb=  new ParamMap.Build();
        if(teammemberid==0){
        }else {
            pb.addParams("activity_member_id", teammemberid);
        }
        RestClient.getYfmNovate(this).post(Constant.API.YFM_ACTIVITBAOMING,pb
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("group_id", seatBean.getData().getGroup_id())
                .addParams("activity_id", seatBean.getData().getActivity_id())
                .addParams("sign_up_amount", seatBean.getData().getSignupAmount())
                .addParams("totalAmount", seatBean.getData().getTotalAmount())
                .addParams("safeAmount", seatBean.getData().getSafeAmount())
                .addParams("appType",2)
//               .addParams("recommendUserId", seatBean.getData().getSafeAmount())
//                .addParams("couponId", seatBean.getData().getSafeAmount())
//               .addParams("discountAmount", seatBean.getData().getSafeAmount())
//                .addParams("appType","2")
                .addParams("payType",paytype)
                .build(), new DmeycBaseSubscriber<WXSubmitBean>() {
            @Override
            public void onSuccess(WXSubmitBean bean) {
                wxSubmitBean=bean;
                prePay();
//                ToastUtil.show("报名成功");
//                checkIsTrueName();
//                finish();
            }
        });
    }


    int clickpos=-1;
    //    ProjectTypeBean pty;
    List<SeatBean.DataBean.PayListBean> pty;
    String paytype;
    ArrayList<String> ar=new ArrayList<>();
    private void getPayTyoe() {
//        YFM_PROJECTPAYTYPE
//
        RestClient.getYfmNovate(this).post(Constant.API.YFM_PROJECTLIST, new ParamMap.Build()
                .addParams("key","PAY_METHOD")
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
//                .addParams("group_id",seatBean.getData().getGroup_id())
                .build(), new DmeycBaseSubscriber<ProjectTypeBean>() {
            @Override
            public void onSuccess(ProjectTypeBean bean) {

//                pty=bean;
//                paytype=bean.getData().get(0).getItemCode();
//                payadapter=new payAdapter();
//                no_list.setAdapter(payadapter);
//                no_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        clickpos=i;
//                        paytype=pty.getData().get(i).getItemCode();
//                        payadapter.notifyDataSetChanged();
//                    }
//                });

            }
            @Override
            public void onError(Throwable e) {

            }
        });
    }
    private boolean istruename=false;
    public void checkIsTrueName(){
        RestClient.getYfmNovate(this).post(Constant.API.YFM_CHECKTRUENAME, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<IsTrueNameBean>() {
            @Override
            public void onSuccess(IsTrueNameBean bean) {
//                finish();
                istruename=bean.isData();
                if(istruename){
                    startActivity(new Intent(BigActionPersonSubmitActivity.this, MainActivity.class));
                    finish();
                }else {
                    goShop();
                }
            }
            @Override
            public void onError(Throwable e) {

            }
        });

    }
    class iconAdapter extends RecyclerView.Adapter<iconAdapter.ViewHolder>{

        public iconAdapter(){

        }

        @Override
        public BigActionPersonSubmitActivity.iconAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view =getLayoutInflater().inflate(R.layout.adapter_settitlenumb,null);

            return new BigActionPersonSubmitActivity.iconAdapter.ViewHolder(view);
        }

        int clickpos=-1;
        ArrayList<TextView> tvs=new ArrayList<>();
        @Override
        public void onBindViewHolder(final BigActionPersonSubmitActivity.iconAdapter.ViewHolder holder, final int position) {
//            GlideUtil.loadImage(SubmitOrderActivity.this,activityDeatilBean.getData().getSign_up_list().get(position).getUrl(),holder.civ_avatar);
            tvs.add(holder.tv_settitle);
            holder.tv_settitle.setText("0"+(position+1));
            poss.add(position);
            tv_set1name.setText(seatBean.getData().getMember_list().get(0*2).getUser_nickname());
            tv_set2name.setText(seatBean.getData().getMember_list().get(0*2+1).getUser_nickname());
            if(-1==clickpos) {
                tvs.get(0).setBackground(BigActionPersonSubmitActivity.this.getResources().getDrawable(R.drawable.shap_settitlebg));
            }else {
//                tvs.get(0).setBackgroundColor(SubmitOrderActivity.this.getResources().getColor(R.color.gb));
            }
//            }else {
//
//                tvs.get(clickpos).setBackgroundColor(SubmitOrderActivity.this.getResources().getColor(R.color.indicator_selected_color));
//            }
            teamid1=seatBean.getData().getMember_list().get(0).getTeam_member_id();
            teamid2=seatBean.getData().getMember_list().get(1).getTeam_member_id();
            tv_set1name.setText(seatBean.getData().getMember_list().get(0).getUser_nickname());
            tv_set2name.setText(seatBean.getData().getMember_list().get(1).getUser_nickname());
            holder.tv_settitle.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View view) {
                    clickpos=position;
                    for (int ii=0;ii<tvs.size();ii++){
//                        ToastUtil.show(position+"111");
                        if(ii==position){
                            tvs.get(position).setBackground(BigActionPersonSubmitActivity.this.getResources().getDrawable(R.drawable.shap_settitlebg));
                        }else {
                            tvs.get(ii).setBackgroundColor(BigActionPersonSubmitActivity.this.getResources().getColor(R.color.gb));
                        }

                    }
                    for (int i=0;i<poss.size();i++){
                        if(position==i){
                            setname1=   seatBean.getData().getMember_list().get(i*2).getUser_nickname();
                            setname2=seatBean.getData().getMember_list().get(i*2+1).getUser_nickname();
                            tv_set1name.setText(seatBean.getData().getMember_list().get(i*2).getUser_nickname());
                            tv_set2name.setText(seatBean.getData().getMember_list().get(i*2+1).getUser_nickname());
                            if(TextUtils.isEmpty(seatBean.getData().getMember_list().get(i*2).getUser_nickname())){
                                iv_set1.setBackground(BigActionPersonSubmitActivity.this.getDrawable(R.drawable.icon_seat));
                            }else {
                                iv_set1.setBackground(BigActionPersonSubmitActivity.this.getDrawable(R.drawable.set_ku));
                            }
                            if(TextUtils.isEmpty(seatBean.getData().getMember_list().get(i*2+1).getUser_nickname())){
                                iv_set1.setBackground(BigActionPersonSubmitActivity.this.getDrawable(R.drawable.icon_seat));
                            }else {
                                iv_set1.setBackground(BigActionPersonSubmitActivity.this.getDrawable(R.drawable.set_ku));
                            }
//                                              ToastUtil.show(pos+"");
                            teamid1=seatBean.getData().getMember_list().get(i*2).getTeam_member_id();
                            teamid2=seatBean.getData().getMember_list().get(i*2+1).getTeam_member_id();
                        }
                    }
                }

            });

        }



        @Override
        public int getItemCount() {
            return seatBean.getData().getMember_list().size()-(seatBean.getData().getMember_list().size()/2);
//            return activityDeatilBean.getData().getSign_up_list().size();
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView tv_settitle;
            //            private RoundedImageView rimag;
//            private TextView tv_titl;
            public ViewHolder(View itemView) {
                super(itemView);
                tv_settitle=itemView.findViewById(R.id.tv_settitle);
////                recycleview1= (RecyclerView) itemView.findViewById(R.id.recycleview1);
//                rimag= (RoundedImageView) itemView.findViewById(R.id.iv_roundmage);
//                tv_titl=  itemView.findViewById(R.id.tv_titl);

            }
        }
    }
    BigActionPersonSubmitActivity.payAdapter payadapter;
    class viewHoder{
        ImageView rb_alipay;
        TextView tv_paytype,tv_ranknum;
        LinearLayout ll_item;
        public viewHoder(View view){
            tv_paytype=view.findViewById(R.id.tv_paytype);
            rb_alipay=view.findViewById(R.id.rb_alipay);
            ll_item=view.findViewById(R.id.ll_item);
            tv_ranknum=view.findViewById(R.id.tv_ranknum);
        }
    }

    public class  payAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return pty.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        BigActionPersonSubmitActivity.viewHoder viewHoder;
        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            if(view==null){
                view=getLayoutInflater().inflate(R.layout.adapter_payitem,null);
                viewHoder=new BigActionPersonSubmitActivity.viewHoder(view);
                view.setTag(viewHoder);
            }else {
                viewHoder=(BigActionPersonSubmitActivity.viewHoder)view.getTag();
            }
//                rbs.add(viewHoder.rb_alipay);
            viewHoder.tv_paytype.setText(pty.get(i).getPay_name());
            if("会费".equals(pty.get(i).getPay_name())){
                viewHoder.tv_ranknum.setVisibility(View.VISIBLE);
                viewHoder.tv_ranknum.setText("余额："+pty.get(i).getAmount()+"元");
            }else {
                viewHoder.tv_ranknum.setVisibility(View.GONE);
            }

            if((-1==clickpos&&0==i)||i==clickpos){
                viewHoder.rb_alipay.setBackground(BigActionPersonSubmitActivity.this.getResources().getDrawable(R.drawable.pay_check));
            }else {
                viewHoder.rb_alipay.setBackground(BigActionPersonSubmitActivity.this.getResources().getDrawable(R.drawable.pay_unchke));
            }
            return view;
        }

    }


    BigActionPersonSubmitActivity.setAdapter setadapter;
    int teammemberid;
    int clickgrpos=-1;
    int clicktype=-1;

    int flage=0;
    ArrayList<CircleImageView>alc1=new ArrayList<>();
    ArrayList<CircleImageView>alc2=new ArrayList<>();
    public class setAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return seatBean.getData().getMember_list().size()/2;
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
        BigActionPersonSubmitActivity.Holders holders;
        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            if(view==null){
                view=getLayoutInflater().inflate(R.layout.adapter_ordersit,null);
                holders=new BigActionPersonSubmitActivity.Holders(view);
                view.setTag(holders);
            }else {
                holders=(BigActionPersonSubmitActivity.Holders) view.getTag();
            }
            if(i>=9){
                holders.tv_numb.setText(""+(i+1));
            }else {
                holders.tv_numb.setText("0"+(i+1));
            }

            if(TextUtils.isEmpty(seatBean.getData().getMember_list().get(i*2).getUser_nickname())){
                holders.tv_set1name.setText(seatBean.getData().getMember_list().get(i*2).getUser_nickname());
            }else {
                holders.tv_set1name.setText(seatBean.getData().getMember_list().get(i*2).getUser_nickname());
            }
            if(TextUtils.isEmpty(seatBean.getData().getMember_list().get(i*2+1).getUser_nickname())){
                holders.tv_set2name.setText(seatBean.getData().getMember_list().get(i*2+1).getUser_nickname());
            }else {
                holders.tv_set2name.setText(seatBean.getData().getMember_list().get(i*2+1).getUser_nickname());
            }

            if(flage==i){
                flage++;
                alc1.add(holders.iv_set1);
                alc2.add(holders.iv_set2);
            }

            if(clickgrpos==i){
                if(clicktype==0){
                    GlideUtil.loadImage(BigActionPersonSubmitActivity.this, SPUtils.getStringData(Constant.Config.AVATAR),holders.iv_set1);
                    Glide.with(BigActionPersonSubmitActivity.this).load(seatBean.getData().getMember_list().get(i*2+1)).into(holders.iv_set2);
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
                    Glide.with(BigActionPersonSubmitActivity.this).load(seatBean.getData().getMember_list().get(i*2)).into(holders.iv_set1);
                    GlideUtil.loadImage(BigActionPersonSubmitActivity.this, SPUtils.getStringData(Constant.Config.AVATAR),holders.iv_set2);
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
                if(TextUtils.isEmpty(seatBean.getData().getMember_list().get(i*2+1).getUser_nickname())){
                    holders.iv_setsex2.setVisibility(View.GONE);
                    holders.tv_set2name.setText("");
                    Glide.with(BigActionPersonSubmitActivity.this).load(R.drawable.set_rabbit).into(holders.iv_set2);
                    teammemberid=seatBean.getData().getMember_list().get(i*2+1).getTeam_member_id();
                }else {
                    holders.iv_setsex2.setVisibility(View.VISIBLE);
                    if("1".equals(seatBean.getData().getMember_list().get(i*2+1).getSex())){
                        holders.iv_setsex2.setBackground(getResources().getDrawable(R.drawable.set_man));
                    }else {
                        holders.iv_setsex2.setBackground(getResources().getDrawable(R.drawable.set_woman));
                    }
                    holders.tv_set2name.setText(seatBean.getData().getMember_list().get(i*2+1).getUser_nickname());
                    Glide.with(BigActionPersonSubmitActivity.this).load(seatBean.getData().getMember_list().get(i*2+1).getUser_logo()).into(holders.iv_set2);
                }

                if(TextUtils.isEmpty(seatBean.getData().getMember_list().get(i*2).getUser_nickname())){
                    holders.iv_setsex1.setVisibility(View.GONE);
                    holders.tv_set1name.setText("");
                    Glide.with(BigActionPersonSubmitActivity.this).load(R.drawable.set_rabbit).into(holders.iv_set1);
                }else {
                    holders.iv_setsex1.setVisibility(View.VISIBLE);
                    if("1".equals(seatBean.getData().getMember_list().get(i*2).getSex())){
                        holders.iv_setsex1.setBackground(getResources().getDrawable(R.drawable.set_man));
                    }else {
                        holders.iv_setsex1.setBackground(getResources().getDrawable(R.drawable.set_woman));
                    }
                    holders.tv_set1name.setText(seatBean.getData().getMember_list().get(i*2).getUser_nickname());
                    Glide.with(BigActionPersonSubmitActivity.this).load(seatBean.getData().getMember_list().get(i*2).getUser_logo()).into(holders.iv_set1);
                }

            }

            holders.iv_set1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                     ToastUtil.show(i+"ss");
//                     alc1.clear();
//                     alc2.clear();
                    if(!TextUtils.isEmpty(seatBean.getData().getMember_list().get(i*2).getUser_nickname())){
                        ToastUtil.show("位置被坐");
                        return;
                    }
                    teammemberid=seatBean.getData().getMember_list().get(i*2).getTeam_member_id();
                    clickgrpos=i;
                    clicktype=0;
                    notifyDataSetChanged();
                }
            });
            holders.iv_set2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    ToastUtil.show(i+"ss");
//                    alc1.clear();
//                    alc2.clear();
                    if(!TextUtils.isEmpty(seatBean.getData().getMember_list().get(i*2+1).getUser_nickname())){
                        ToastUtil.show("位置被坐");
                        return;
                    }
                    teammemberid=seatBean.getData().getMember_list().get(i*2+1).getTeam_member_id();
                    clickgrpos=i;
                    clicktype=1;
                    notifyDataSetChanged();
                }
            });

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
    Dialog dialog;
    ListView lv_shop;
    TextView alltv_price;
    TextView tv_toinfo;
    public void goShop(){
        dialog  = new Dialog(BigActionPersonSubmitActivity.this, R.style.MyDialog);
        //设置它的ContentView
        setFinishOnTouchOutside(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_toinstrument);
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
//        lv_shop = dialog.findViewById(R.id.lv_shop);
//        alltv_price=dialog.findViewById(R.id.alltv_price);
        dialog.show();
        tv_toinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                startActivity(new Intent(BigActionPersonSubmitActivity.this, TrueNameActivity.class));
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



    /**
     * 根据订单orderId去获取预生成订单详情
     */
    public void prePay(){
//        if(payMethod == Constant.Config.PAY_WX){
//            RestClient.getNovate(SubmitOrderActivity.this, Constant.API.BASE_URL).get(Constant.API.PAY_WX, new ParamMap.Build().addParams("orderIds", orderId).build(), new DmeycBaseSubscriber<WXPayBean>(mContext) {
//                @Override
//                public void onSuccess(WXPayBean bean) {
//                    SPUtils.savaStringData(Constant.Config.ORDER_ID,orderId.contains(",") ? orderId.split(",")[0] : orderId);
        WXSubmitBean.DataBean.PayInfoBean data = wxSubmitBean.getData().getPayInfo();

        IWXAPI msgApi = WXAPIFactory.createWXAPI(BigActionPersonSubmitActivity.this, null);
        msgApi.registerApp(Constant.Key.WEIXIN_APPID);

        PayReq request = new PayReq();
        request.appId = Constant.Key.WEIXIN_APPID;
        request.partnerId = data.getPartnerid();
        request.prepayId= data.getPrepayid();
//                    request.packageValue = data.getPackageX();
        request.packageValue = "Sign=WXPay";
        request.nonceStr= data.getNonceStr();
        request.timeStamp= String.valueOf(data.getTimeStamp());
        request.sign= data.getSign();
        msgApi.sendReq(request);
    }
//            });
//        }
//    }


    public class WXBroadcastReceiver extends BroadcastReceiver {

        @Override

        public void onReceive(Context context, Intent intent) {
            checkIsTrueName();
        }
    }
}
