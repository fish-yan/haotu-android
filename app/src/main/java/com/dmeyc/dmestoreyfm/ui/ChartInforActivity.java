package com.dmeyc.dmestoreyfm.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.ActionItemAdapter;
import com.dmeyc.dmestoreyfm.adapter.ChartPageterActionAdapter;
import com.dmeyc.dmestoreyfm.adapter.CustomLazyViewPagerAdapter;
import com.dmeyc.dmestoreyfm.adapter.NoticeCommPagerAdapter;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.ActionDetailRountBean;
import com.dmeyc.dmestoreyfm.bean.ActivityDeatilBean;
import com.dmeyc.dmestoreyfm.bean.CheckChatActivity;
import com.dmeyc.dmestoreyfm.bean.CommDetailBean;
import com.dmeyc.dmestoreyfm.bean.CommNoticeBean;
import com.dmeyc.dmestoreyfm.bean.PublishActionAfterBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.dialog.ShareDialog;
import com.dmeyc.dmestoreyfm.fragment.ActionInformaTionFragment;
import com.dmeyc.dmestoreyfm.fragment.EquipInfromationFragment;
import com.dmeyc.dmestoreyfm.fragment.FragmentCubNotice;
import com.dmeyc.dmestoreyfm.fragment.MacthRoleFragment;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.chat.ConversationActivity;
import com.dmeyc.dmestoreyfm.utils.BlurPopWin;
import com.dmeyc.dmestoreyfm.utils.DensityUtil;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.dmeyc.dmestoreyfm.wedgit.GlideImageLoader;
import com.dmeyc.dmestoreyfm.wedgit.PopupMenu;
import com.dmeyc.dmestoreyfm.wedgit.PopupPublicMenu;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tamic.novate.Throwable;
import com.youth.banner.Banner;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import io.rong.imkit.RongIM;

public class ChartInforActivity  extends BaseActivity {
    @Bind(R.id.rv_member)
    RecyclerView rv_member;
    @Bind(R.id.civ_avatar)
    CircleImageView civ_avatar;
    @Bind(R.id.tv_power)
    TextView tv_power;
    @Bind(R.id.tv_actioncount)
    TextView tv_actioncount;
    @Bind(R.id.tv_commname)
    TextView tv_commname;
    @Bind(R.id.tv_numberacoun)
    TextView tv_numberacoun;
    @Bind(R.id.civ_header)
    CircleImageView civ_header;
    @Bind(R.id.tv_name)
    TextView tv_name;
    @Bind(R.id.tv_personname)
    TextView tv_personname;
    @Bind(R.id.tv_time)
    TextView tv_time;
    @Bind(R.id.tv_adress)
    TextView tv_adress;
    @Bind(R.id.tv_phone)
    TextView tv_phone;
//    @Bind(R.id.tv_intro)
//    TextView tv_intro;
//    @Bind(R.id.tv_notice)
//    TextView tv_notice;
    @Bind(R.id.btn_login)
    Button btn_login;

    @Bind(R.id.tv_follownum)
    TextView tv_follownum;

    @Bind(R.id.ll_activityinfo)
    LinearLayout  ll_activityinfo;
    @Bind(R.id.iv_share)
    ImageView iv_share;

    @Bind(R.id.viewpager)
    ViewPager viewpager;

     @Bind(R.id.ll_points)
    LinearLayout ll_points;

    private int prevPosition = 0;

@Bind(R.id.tv_batnumber)
TextView tv_batnumber;
//@Bind(R.id.tv_noticeandproduce)
//TextView tv_noticeandproduce;

@Bind(R.id.tv_activityname)
TextView tv_activityname;

@Bind(R.id.rl_routpager)
RelativeLayout rl_routpager;
@Bind(R.id.view_line)
View view_line;
@Bind(R.id.banner)
Banner banner;
@Bind(R.id.tv_clubintro)
TextView tv_clubintro;
@Bind(R.id.tv_notice)
TextView tv_notice;
    @Bind(R.id.tv_intro)
    TextView tv_intro;
    @Bind(R.id.tv_idenity)
    TextView tv_idenity;
    @Bind(R.id.rl_idinty)
    RelativeLayout rl_idinty;
//    private ViewPager actionrule_pager;
//    private TabLayout action_ruletablayout;
@Bind(R.id.tv_clubname)
TextView tv_clubname;
@Bind(R.id.cv_clublogo)
CircleImageView cv_clublogo;

@Bind(R.id.tv_year)
TextView tv_year;
@Bind(R.id.tv_money)
TextView tv_money;
    protected List<String> mTitleLists; //导航条文字List
    protected List<BaseFragment> mFragmentLists;    //导航fragment list

    private Handler handler = new Handler() {

        @SuppressLint("HandlerLeak")
        public void handleMessage(android.os.Message msg) {

            switch (msg.what) {
                case 0:
                    // 得到mvp当前页面的索引
                    int currentItem = viewpager.getCurrentItem();
                    // 要显示的下一个页面的索引
                    currentItem++;
                    // 设置ViewPager显示的页面
                    ToastUtil.show(currentItem%5+"ssssss");
                    viewpager.setCurrentItem(currentItem % 5);

                    break;

                default:
                    break;
            }
        };
    };

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_chartinfro;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

//        action_ruletablayout = (TabLayout) mRootView.findViewById(R.id.action_ruletablayout); //使用bind 会出现空指针
//        action_ruletablayout.post(new Runnable() {
//            @Override
//            public void run() {
//                setIndicator(action_ruletablayout,20,20);
//            }
//        });
//        actionrule_pager = (ViewPager) mRootView.findViewById(R.id.actionrule_pager); //使用bind 会出现空指针
        mTitleLists = new ArrayList<>();
        mFragmentLists = new ArrayList<>();


        LinearLayoutManager lm=  new LinearLayoutManager(this);
        lm.setOrientation(OrientationHelper.HORIZONTAL);
        rv_member.setLayoutManager(lm);
//        setData();
        setRouto();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setData();
//        setRouto();
    }
ArrayList<ActionDetailRountBean.DataBean> dataBeans=new ArrayList<>();
//    ActionDetailRountBean rountBean;
    public void setRouto(){
        dataBeans.clear();
        RestClient.getYfmNovate(this).post(Constant.API.YFM_GetRotACTIONDET, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("group_id", getIntent().getIntExtra("group_id",-1))
                .addParams("longitude", SPUtils.getStringData(Constant.Config.LONGTUDE))
                .addParams("latitude", SPUtils.getStringData(Constant.Config.LATITUDE))
                .build(), new DmeycBaseSubscriber<ActionDetailRountBean>() {
            @Override
            public void onSuccess(ActionDetailRountBean bean) {
//                   ToastUtil.show(bean.getMsg());
                   dataBeans.addAll(bean.getData());
//                      rountBean=bean;
                      if(dataBeans.size()==0){
                          rl_routpager.setVisibility(View.GONE);
                          tv_activityname.setVisibility(View.GONE);
                          view_line.setVisibility(View.GONE);
                      }else {
                          view_line.setVisibility(View.VISIBLE);
                          tv_activityname.setVisibility(View.VISIBLE);
                          rl_routpager.setVisibility(View.VISIBLE);
                      }
                for (int i = 0; i < dataBeans.size(); i++) {
                    //根据图片的数量生成相对应的数量的小圆点
                    final View view=new View(ChartInforActivity.this);
                    view.setBackgroundResource(R.drawable.shap_dot_blue);
                    DisplayMetrics metrics=new DisplayMetrics();
                    float width=TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,10, metrics);
                    float height=TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 10, metrics);
                    LinearLayout.LayoutParams params=new LinearLayout.LayoutParams((int)width,(int)height);
                    params.leftMargin=5;
                    view.setLayoutParams(params);
                    ll_points.addView(view);
                }
                if(dataBeans.size()!=0){
                    ll_points.getChildAt(0).setBackgroundResource(R.drawable.shap_dot_graynomal);
                    tv_activityname.setText(dataBeans.get(0).getActivityName());
                    viewpager.setAdapter(new ChartPageterActionAdapter(bean,ChartInforActivity.this));
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (true){
                                SystemClock.sleep(2000);
                                //这里是设置当前页的下一页
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        // 得到mvp当前页面的索引
//                                    handler.sendEmptyMessage(0);
                                        // 得到mvp当前页面的索引
//                                    int currentItem = viewpager.getCurrentItem();
//                                    // 要显示的下一个页面的索引
//                                    currentItem++;
//                                    // 设置ViewPager显示的页面
//                                    ToastUtil.show(currentItem%5+"ssssss");
//                                    viewpager.setCurrentItem(currentItem % 5);

                                        viewpager.setCurrentItem(viewpager.getCurrentItem() + 1);
                                    }
                                });
                            }
                        }
                    }).start();

                    viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                        }

                        @Override
                        public void onPageSelected(int position) {
//                        ll_points.getChildAt(prevPosition).setBackgroundResource(R.drawable.shap_dot_blue);
//                        ll_points.getChildAt(position%5).setBackgroundResource(R.drawable.shap_dot_graynomal);
//                        prevPosition=position%5;
                            ll_points.getChildAt(prevPosition).setBackgroundResource(R.drawable.shap_dot_blue);
                            ll_points.getChildAt(position%dataBeans.size()).setBackgroundResource(R.drawable.shap_dot_graynomal);
                            prevPosition=position%dataBeans.size();
                            tv_activityname.setText(dataBeans.get(position%dataBeans.size()).getActivityName());
                        }
                        @Override
                        public void onPageScrollStateChanged(int state) {
                        }
                    });
                }

            }
        });
    }
    protected void getTitleList(List<String> mTitleLists) {
        mTitleLists.clear();
        if(commDetailBean.getData().getGroupType().equals("3")){
            tv_clubintro.setText("教练简介");
            tv_intro.setText("教练公告");
        }else {
            tv_clubintro.setText("俱乐部简介");
            tv_intro.setText("俱乐部公告");
        }

    }

    protected void getFragmentLists(List<BaseFragment> mFragmentLists) {
        mFragmentLists.clear();
        if(commDetailBean.getData().getGroupType().equals("3")){
            if(commDetailBean.getData().getRemarkImgs()!=null){
                for (int i=0;i<commDetailBean.getData().getRemarkImgs().size();i++){
                    commDetailBean.getData().getEducateImgs().add(commDetailBean.getData().getRemarkImgs().get(i));
                }
            }
                commDetailBean.getData().setEducateBackground(commDetailBean.getData().getEducateBackground()+"\n"+commDetailBean.getData().getRemark());
                mFragmentLists.add(new FragmentCubNotice(commDetailBean.getData().getEducateImgs(),commDetailBean.getData().getEducateBackground()));
//        mFragmentLists.add(new FragmentCubNotice(commDetailBean.getData().getRemarkImgs(),commDetailBean.getData().getNotice()));
                mFragmentLists.add(new MacthRoleFragment(commDetailBean.getData().getNotice()));
        }else {
            mFragmentLists.add(new FragmentCubNotice(commDetailBean.getData().getRemarkImgs(),commDetailBean.getData().getRemark()));
//        mFragmentLists.add(new FragmentCubNotice(commDetailBean.getData().getRemarkImgs(),commDetailBean.getData().getNotice()));
            mFragmentLists.add(new MacthRoleFragment(commDetailBean.getData().getNotice()));
        }

    }

    public void setIndicator (TabLayout tabs,int leftDip,int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }
    String isgroup;
    CommDetailBean commDetailBean;
    private void setData() {
        RestClient.getYfmNovate(this).post(Constant.API.YFM_GROUPINFO, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("group_id", getIntent().getIntExtra("group_id",-1))
//                .addParams("group_id", getIntent().getIntExtra("group_id",-1))
                .build(), new DmeycBaseSubscriber<CommDetailBean>() {
            @Override
            public void onSuccess(CommDetailBean bean) {
                commDetailBean=bean;

                if(commDetailBean.getData().getGroupType().equals("3")){
                    mTitleLists.add("教练简介");
                    mTitleLists.add("教练公告");
                }else {
                    mTitleLists.add("俱乐部简介");
                    mTitleLists.add("俱乐部公告");
                }

//                getTitleList(mTitleLists);
//                getFragmentLists(mFragmentLists);
//                FragmentManager fms =getSupportFragmentManager();
//                actionrule_pager.setAdapter(new CustomLazyViewPagerAdapter(fms,mFragmentLists,mTitleLists));
//                action_ruletablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
//                action_ruletablayout.setupWithViewPager(actionrule_pager);

               if(!TextUtils.isEmpty(bean.getData().getCreate_time())){
                   String endtime=   bean.getData().getCreate_time();
                   String times= endtime.split(" ")[0];
                   String pal=   times.split("-")[1]+"-"+times.split("-")[2];
                   tv_year.setText(times.split("-")[1]);
                   tv_money.setText(times.split("-")[2]);
               }


                if(commDetailBean.getData().getGroupType().equals("3")){
                    if(commDetailBean.getData().getRemarkImgs()!=null){
                        for (int i=0;i<commDetailBean.getData().getRemarkImgs().size();i++){
                            commDetailBean.getData().getEducateImgs().add(commDetailBean.getData().getRemarkImgs().get(i));
                        }
                    }
                    commDetailBean.getData().setEducateBackground(commDetailBean.getData().getEducateBackground()+"\n"+commDetailBean.getData().getRemark());

                    if(commDetailBean.getData().getEducateImgs()==null||commDetailBean.getData().getEducateImgs().size()==0){
                        banner.setVisibility(View.GONE);
                    }else {
                        banner.setVisibility(View.VISIBLE);
                        banner.setImageLoader(new GlideImageLoader());
                        banner.setImages(commDetailBean.getData().getEducateImgs());
                        banner.start();
                    }
                    tv_intro.setText(commDetailBean.getData().getEducateBackground());
                }else {
                    if(commDetailBean.getData().getRemarkImgs()==null||commDetailBean.getData().getRemarkImgs().size()==0){
                        banner.setVisibility(View.GONE);
                    }else {
                        banner.setVisibility(View.VISIBLE);
                        banner.setImageLoader(new GlideImageLoader());
                        banner.setImages(commDetailBean.getData().getRemarkImgs());
                        banner.start();
                    }
                    tv_intro.setText(commDetailBean.getData().getRemark());
                    if("1".equals(commDetailBean.getData().getGroupType())){
                        tv_idenity.setVisibility(View.VISIBLE);
                    }else {
                        tv_idenity.setVisibility(View.GONE);
                    }
                    rl_idinty.setVisibility(View.GONE);
//                    mFragmentLists.add(new FragmentCubNotice(commDetailBean.getData().getRemarkImgs(),commDetailBean.getData().getRemark()));
////        mFragmentLists.add(new FragmentCubNotice(commDetailBean.getData().getRemarkImgs(),commDetailBean.getData().getNotice()));
//                    mFragmentLists.add(new MacthRoleFragment(commDetailBean.getData().getNotice()));
                }

                rv_member.setAdapter(new iconAdapter());
                GlideUtil.loadImage(ChartInforActivity.this,bean.getData().getGroup_logo(),civ_avatar);
                GlideUtil.loadImage(ChartInforActivity.this,bean.getData().getOwner_logo(),civ_header);
                tv_clubname.setText(commDetailBean.getData().getGroup_name());
                GlideUtil.loadImage(ChartInforActivity.this,commDetailBean.getData().getGroup_logo(),cv_clublogo);
                tv_power.setText("战斗力  "+bean.getData().getBattle_number());
                tv_actioncount.setText("活跃度  "+bean.getData().getActive_number());
                if(bean.getData().getIs_follow().equals("0")){
                    tv_follownum.setText("加关注  "+bean.getData().getFollow_count());
                }else {
                    tv_follownum.setText("已关注  "+bean.getData().getFollow_count());
                }
                tv_batnumber.setText(bean.getData().getBattle_number()+"");
//                if(TextUtils.isEmpty(bean.getData().getActivity_id()+"")||bean.getData().getActivity_id()<=0){
//                    ll_activityinfo.setVisibility(View.GONE);
//                }else {
//                    ll_activityinfo.setVisibility(View.VISIBLE);
//                }

                tv_commname.setText(bean.getData().getGroup_name());
                tv_numberacoun.setText(bean.getData().getGroup_member_images().size()+"人");
//                tv_numberacoun.setText(bean.getData().getSign_up_no()+"人");
                tv_name.setText(bean.getData().getOwner_name());
                tv_personname.setText("报名："+bean.getData().getSign_up_no()+"/"+bean.getData().getActivity_total_no());
                tv_time.setText("时间："+bean.getData().getStart_date());
                tv_adress.setText("地址："+bean.getData().getActivity_venue_address());
                tv_phone.setText("联系方式："+bean.getData().getPhone_no());
//                tv_intro.setText(bean.getData().getRemark());
//                tv_notice.setText("暂无信息");
                isgroup=bean.getData().getIs_join();
                if("0".equals(isgroup)){
                    btn_login.setText("申请加群");
                    isgroup="0";
                    btn_login.setVisibility(View.VISIBLE);
                }else if("2".equals(isgroup)){
                    btn_login.setVisibility(View.GONE);
                }else if("3".equals(isgroup)){
                    btn_login.setVisibility(View.VISIBLE);
                    btn_login.setText("入群审核中");
                    btn_login.setBackground(ChartInforActivity.this.getResources().getDrawable(R.drawable.shap_grayconner));
                    btn_login.setClickable(false);
                }else if("1".equals(isgroup)){
                    btn_login.setVisibility(View.GONE);
                    isgroup="1";
                    btn_login.setText("申请退群");
//                    btn_login.setVisibility(View.VISIBLE);
                }
                CommNoticeBean commNoticeBean=new CommNoticeBean();
//                for (int i = 0; i < 5; i++) {
//                    //根据图片的数量生成相对应的数量的小圆点
//                    final View view=new View(ChartInforActivity.this);
//                    view.setBackgroundResource(R.drawable.shap_dot_blue);
//                    DisplayMetrics metrics=new DisplayMetrics();
//                    float width=TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,10, metrics);
//                    float height=TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 10, metrics);
//                    LinearLayout.LayoutParams params=new LinearLayout.LayoutParams((int)width,(int)height);
//                    params.leftMargin=5;
//                    view.setLayoutParams(params);
//                    ll_points.addView(view);
//                }
//                ll_points.getChildAt(0).setBackgroundResource(R.drawable.shap_dot_graynomal);
//                tv_noticeandproduce.setText(commDetailBean.getData().getRemark());
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
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           View view =getLayoutInflater().inflate(R.layout.adapter_member,null);
            return new iconAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position){
            GlideUtil.loadImage(ChartInforActivity.this,commDetailBean.getData().getGroup_member_images().get(position).getUrl(),holder.civ_avatar);
            holder.civ_avatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(ChartInforActivity.this,MemberListActivity.class).putExtra("groupid",commDetailBean.getData().getGroup_id()).putExtra("isallban",commDetailBean.getData().getIs_all_ban()));
//                    onItemClickListener.onClick(position);
                }
            });
        }
        @Override
        public int getItemCount() {
            return commDetailBean.getData().getGroup_member_images().size();
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            public CircleImageView civ_avatar;
//            private RoundedImageView rimag;
//            private TextView tv_titl;
            public ViewHolder(View itemView) {
                super(itemView);
                civ_avatar=itemView.findViewById(R.id.civ_avatar);
////                recycleview1= (RecyclerView) itemView.findViewById(R.id.recycleview1);
//                rimag= (RoundedImageView) itemView.findViewById(R.id.iv_roundmage);
//                tv_titl=  itemView.findViewById(R.id.tv_titl);
            }
        }
    }

    public interface OnItemClickListener {
        void onClick(int position);

    }
    OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    int poscode;
    private PopupPublicMenu popupMenu;
    ArrayList <String> ar=new ArrayList<>();
    @OnClick({R.id.btn_login,R.id.ll_activityinfo,R.id.ll_commmember,R.id.rv_member,R.id.iv_share,R.id.iv_tochat,R.id.tv_notice,R.id.tv_clubintro,R.id.tv_idenity})
    public void applyin(View view){
        int viewid=view.getId();
        if(R.id.ll_activityinfo==viewid){
            startActivity(new Intent(ChartInforActivity.this, ActionItemActivity.class).putExtra("activityid", commDetailBean.getData().getActivity_id()));
        }else if(R.id.ll_commmember==viewid){
            startActivity(new Intent(ChartInforActivity.this,MemberListActivity.class).putExtra("groupid",commDetailBean.getData().getGroup_id()).putExtra("isallban",commDetailBean.getData().getIs_all_ban()));
        }else if(R.id.rv_member==viewid){
            startActivity(new Intent(ChartInforActivity.this,MemberListActivity.class).putExtra("groupid",commDetailBean.getData().getGroup_id()).putExtra("isallban",commDetailBean.getData().getIs_all_ban()));
        }else if(R.id.iv_share==viewid){
            ar.clear();
            ar.add("分享");
            ar.add("退群");
            popupMenu = new PopupPublicMenu(ChartInforActivity.this,ar);
            popupMenu.showLocation(R.id.iv_share);// 设置弹出菜单弹出的位置
            // 设置回调监听，获取点击事件
            popupMenu.setOnItemClickListener(new PopupPublicMenu.OnItemClickListener() {
                @Override
                public void onClick(PopupMenu.MENUITEM item, String str) {
                    popupMenu.dismiss();
                }
                @Override
                public void onClick(String str,int pos) {
                    poscode=pos;
//                    Toast.makeText(getApplication(), str, Toast.LENGTH_SHORT).show();
//                    tv_projecttype.setText(str);
                    popupMenu.dismiss();
                    if(0==pos){
                        new BlurPopWin.Builder(ChartInforActivity.this).setContent(commDetailBean.getData().getGroup_name())
                                //Radius越大耗时越长,被图片处理图像越模糊
                                .setRadius(3).setTitle(commDetailBean.getData().getRemark())
                                //设置居中还是底部显示
                                .setUrl(Constant.API.YFM_SHAREBASE_URL+Constant.API.SHARE_CHINFO+"&group_id="+commDetailBean.getData().getGroup_id())
                                .setshowAtLocationType(1)
                                .setShowCloseButton(true)
                                .setOutSideClickable(false)
                                /*.onClick(new BlurPopWin.PopupCallback() {
                                    @Override
                                    public void onClick(@NonNull BlurPopWin blurPopWin) {
                                        Toast.makeText(MainActivity.this, "中间被点了", Toast.LENGTH_SHORT).show();
                                        blurPopWin.dismiss();
                                    }
                                })*/.show(iv_share);

//                        new ShareDialog(ChartInforActivity.this).show();
                    }else {
                        outGroup();
                    }
                }
            });


//            new BlurPopWin.Builder(ChartInforActivity.this).setContent("该配合你演出的我,眼视而不见,在比一个最爱你的人即兴表演")
//                    //Radius越大耗时越长,被图片处理图像越模糊
//                    .setRadius(3).setTitle("我是标题")
//                    //设置居中还是底部显示
//                    .setshowAtLocationType(1)
//                    .setShowCloseButton(true)
//                    .setOutSideClickable(false)
//                    /*.onClick(new BlurPopWin.PopupCallback() {
//                        @Override
//                        public void onClick(@NonNull BlurPopWin blurPopWin) {
//                            Toast.makeText(MainActivity.this, "中间被点了", Toast.LENGTH_SHORT).show();
//                            blurPopWin.dismiss();
//                        }
//                    })*/.show(iv_share);

//            new ShareDialog(this).show();
        }else if(R.id.iv_tochat==viewid){
            if("4".equals(commDetailBean.getData().getGroupType())){

                RestClient.getYfmNovate(ChartInforActivity.this).post(Constant.API.YFM_COMMPERCANCHAT, new ParamMap.Build()
                                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                                .addParams("group_id", commDetailBean.getData().getGroup_id())
//                .addParams("group_id", getIntent().getIntExtra("group_id",-1))
                                .build(), new DmeycBaseSubscriber<CheckChatActivity>() {

                            @Override
                            public void onSuccess(CheckChatActivity chachatbean) {
                                ToastUtil.show(chachatbean.getMsg());
                                if(1==chachatbean.getData()){
                                    RongIM.getInstance().startGroupChat(ChartInforActivity.this, commDetailBean.getData().getGroup_id()+"", commDetailBean.getData().getGroup_name()+"");
                                }else {
                                    ToastUtil.show("您不在该群，请先加入才能查看");
                                }
                            }
                        }
                );
            }else {
                RongIM.getInstance().startGroupChat(ChartInforActivity.this, commDetailBean.getData().getGroup_id()+"", commDetailBean.getData().getGroup_name()+"");
              }
        }else if(R.id.btn_login==viewid){
        if("0".equals(isgroup)){
                inGroup();
          }
        }else if(R.id.tv_notice==viewid){
            rl_idinty.setVisibility(View.GONE);
            banner.setVisibility(View.VISIBLE);
            tv_intro.setVisibility(View.VISIBLE);
            tv_notice.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            tv_clubintro.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            tv_idenity.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            tv_notice.setTextColor(getResources().getColor(R.color.black));
            tv_clubintro.setTextColor(getResources().getColor(R.color.gray_deep));
            tv_idenity .setTextColor(getResources().getColor(R.color.gray_deep));

                banner.setVisibility(View.GONE);
                 tv_intro.setText(commDetailBean.getData().getNotice() );
        }else if(R.id.tv_clubintro==viewid){
            rl_idinty.setVisibility(View.GONE);
            banner.setVisibility(View.VISIBLE);
            tv_intro.setVisibility(View.VISIBLE);
            tv_clubintro.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            tv_idenity.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            tv_notice.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            tv_clubintro.setTextColor(getResources().getColor(R.color.black));
            tv_notice.setTextColor(getResources().getColor(R.color.gray_deep));
            tv_idenity.setTextColor(getResources().getColor(R.color.gray_deep));


            if(commDetailBean.getData().getGroupType().equals("3")){
                if(commDetailBean.getData().getRemarkImgs()!=null){
                    for (int i=0;i<commDetailBean.getData().getRemarkImgs().size();i++){
                        commDetailBean.getData().getEducateImgs().add(commDetailBean.getData().getRemarkImgs().get(i));
                    }
                }
                commDetailBean.getData().setEducateBackground(commDetailBean.getData().getEducateBackground()+"\n"+commDetailBean.getData().getRemark());

                if(commDetailBean.getData().getEducateImgs()==null||commDetailBean.getData().getEducateImgs().size()==0){
                    banner.setVisibility(View.GONE);
                }else {
                    banner.setVisibility(View.VISIBLE);
                    banner.setImageLoader(new GlideImageLoader());
                    banner.setImages(commDetailBean.getData().getEducateImgs());
                    banner.start();
                }
                tv_intro.setText(commDetailBean.getData().getEducateBackground());
            }else {
                if(commDetailBean.getData().getRemarkImgs()==null||commDetailBean.getData().getRemarkImgs().size()==0){
                    banner.setVisibility(View.GONE);
                }else {
                    banner.setVisibility(View.VISIBLE);
                    banner.setImageLoader(new GlideImageLoader());
                    banner.setImages(commDetailBean.getData().getRemarkImgs());
                    banner.start();
                }
                tv_intro.setText(commDetailBean.getData().getRemark());
            }
        }else if(R.id.tv_idenity==viewid){
            rl_idinty.setVisibility(View.VISIBLE);
            tv_idenity.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            tv_clubintro.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            tv_notice.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            tv_idenity.setTextColor(getResources().getColor(R.color.black));
            tv_clubintro.setTextColor(getResources().getColor(R.color.gray_deep));
            tv_notice .setTextColor(getResources().getColor(R.color.gray_deep));
            banner.setVisibility(View.GONE);
        tv_intro.setVisibility(View.GONE);
        }
    }

    public void inGroup(){
        RestClient.getYfmNovate(this).post(Constant.API.YFM_JOINCOMM, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("group_id", getIntent().getIntExtra("group_id",-1))
//                .addParams("group_id", getIntent().getIntExtra("group_id",-1))
                .build(), new DmeycBaseSubscriber<PublishActionAfterBean>() {
            @Override
            public void onSuccess(PublishActionAfterBean bean) {
                if("4".equals(commDetailBean.getData().getGroupType())){
                    btn_login.setVisibility(View.VISIBLE);
                    btn_login.setBackground(ChartInforActivity.this.getResources().getDrawable(R.drawable.shap_grayconner));
                    btn_login.setClickable(false);
                }else {
                    btn_login.setVisibility(View.GONE);
                }
                ToastUtil.show("已提交申请");

//                btn_login.setText("申请退群");
                isgroup="1";
                setData();
            }
            @Override
            public void onError(Throwable e) {

            }
        });
    }


        public void outGroup(){

            RestClient.getYfmNovate(this).post(Constant.API.YFM_OUTCOMM, new ParamMap.Build()
                    .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
//                    .addParams("group_id", 5)
                .addParams("group_id", getIntent().getIntExtra("group_id",-1))
                    .build(), new DmeycBaseSubscriber<PublishActionAfterBean>() {
                @Override
                public void onSuccess(PublishActionAfterBean bean) {
//                    ToastUtil.show(bean.getMsg());
                    btn_login.setText("申请加群");
                    btn_login.setVisibility(View.VISIBLE);
                    isgroup="0";
                    setData();
                }
                @Override
                public void onError(Throwable e) {

                }
            });
    }
int follcount;
    int flag=0;
        @OnClick(R.id.tv_follownum)
    public void onClick(View view){
        int viewid=view.getId();
        if(viewid==R.id.tv_follownum){

            if(0==flag){
                if( commDetailBean.getData().getIs_follow().equals("0")){
                    isfollow=0;
                }else {
                    isfollow=1;
                }
                follcount= commDetailBean.getData().getFollow_count();
            }
                setfollow();
        }
        }
       int isfollow=0;
        public void setfollow(){

            RestClient.getYfmNovate(this).post(Constant.API.YFM_ATTENT, new ParamMap.Build()
                    .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
//                    .addParams("group_id", 5)
                    .addParams("group_id", getIntent().getIntExtra("group_id",-1))
                    .build(), new DmeycBaseSubscriber<CommDetailBean>() {
                @Override
                public void onSuccess(CommDetailBean bean) {
                    flag++;
                    if(0==isfollow){
                        isfollow=1;
                        follcount=follcount+1;
                        tv_follownum.setText("已关注  "+(follcount));
                    }else {
                        isfollow=0;
                        follcount=follcount-1;
                        tv_follownum.setText("加关注  "+(follcount));
                    }
                }
                @Override
                public void onError(Throwable e) {

                }
            });
        }
}
