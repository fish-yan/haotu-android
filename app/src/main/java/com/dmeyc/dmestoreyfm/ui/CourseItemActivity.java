package com.dmeyc.dmestoreyfm.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.navi.INaviInfoCallback;
import com.amap.api.navi.model.AMapNaviLocation;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.CustomLazyViewPagerAdapter;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.ActivityDeatilBean;
import com.dmeyc.dmestoreyfm.bean.RoClodBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.fragment.ActionInformaTionFragment;
import com.dmeyc.dmestoreyfm.fragment.ActionIntroFragment;
import com.dmeyc.dmestoreyfm.fragment.BClientActionListFragment;
import com.dmeyc.dmestoreyfm.fragment.EquipInfromationFragment;
import com.dmeyc.dmestoreyfm.fragment.MacthRoleFragment;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.BlurPopWin;
import com.dmeyc.dmestoreyfm.utils.DateUtil;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ScreenUtil;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.dmeyc.dmestoreyfm.wedgit.CommDialog;
import com.dmeyc.dmestoreyfm.wedgit.CommSharePopWin;
import com.dmeyc.dmestoreyfm.wedgit.CountdownTime;
import com.dmeyc.dmestoreyfm.wedgit.CountdownView;
import com.dmeyc.dmestoreyfm.wedgit.GlideImageLoader;
import com.google.gson.Gson;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyItemDialogListener;
import com.tamic.novate.Throwable;
import com.youth.banner.Banner;

import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import io.rong.imkit.RongIM;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.message.TextMessage;

public class CourseItemActivity  extends BaseActivity implements View.OnClickListener , INaviInfoCallback {

    @Bind(R.id.tv_version)
    TextView tvVersion;

    TextView btn_bao;
    //    @Bind(R.id.iv_activitylog)
//    ImageView iv_activitylog;
    @Bind(R.id.tv_activityname)
    TextView tv_activityname;
    @Bind(R.id.tv_activityaddrss)
    TextView tv_activityaddrss;

    @Bind(R.id.tv_phone)
    TextView tv_phone;
    @Bind(R.id.tv_money)
    TextView tv_money;
    @Bind(R.id.tv_time)
    TextView tv_time;
    @Bind(R.id.tv_activityintro)
    TextView tv_activityintro;

    @Bind(R.id.rv_member)
    RecyclerView rv_member;

    @Bind(R.id.tv_personnum)
    TextView tv_personnum;

    @Bind(R.id.btn_baoming)
    TextView btn_baoming;


    @Bind(R.id.tv_call)
    LinearLayout tv_call;

    @Bind(R.id.tv_map)
    LinearLayout tv_map;

    @Bind(R.id.ll_tocommdetail)
    LinearLayout ll_tocommdetail;
    @Bind(R.id.cb_agree)
    CheckBox cb_agree;
    @Bind(R.id.iv_shopcar)
    ImageView iv_shopcar;
    @Bind(R.id.countdownView)
    CountdownView countdownView;
    @Bind(R.id.time_desc)
    TextView time_desc;

    @Bind(R.id.iv_share)
    ImageView iv_share;
    @Bind(R.id.tv_inperson)
    TextView tv_inperson;
    @Bind(R.id.tv_tiaokuan)
    TextView tv_tiaokuan;
    @Bind(R.id.tv_moneyvip)
    TextView tv_moneyvip;
    @Bind(R.id.tv_moneywoman)
    TextView tv_moneywoman;
    @Bind(R.id.ll_mumberitem)
    LinearLayout ll_mumberitem;
//    private ViewPager pkrule_pager;
//    private TabLayout pk_ruletablayout;

//    protected List<String> mTitleLists; //导航条文字List
//    protected List<BaseFragment> mFragmentLists;    //导航fragment list


    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.tv_intro)
    TextView tv_intro;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_courseitem;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        ll_mumberitem.setOnClickListener(this);
//        pk_ruletablayout = (TabLayout) mRootView.findViewById(R.id.pk_ruletablayout); //使用bind 会出现空指针
//        pk_ruletablayout.post(new Runnable() {
//            @Override
//            public void run() {
//                setIndicator(pk_ruletablayout,20,20);
//            }
//        });
//        pkrule_pager = (ViewPager) mRootView.findViewById(R.id.pkrule_pager); //使用bind 会出现空指针
//        mTitleLists = new ArrayList<>();
//        mFragmentLists = new ArrayList<>();


        countdownView.setTextSize(ScreenUtil.dp2px(CourseItemActivity.this, 4));
        iv_shopcar.setOnClickListener(this);
        tvTitle.setText("课程信息");
        tvVersion.setText("V" + Util.getLocalVersionName(this));
        btn_bao = (TextView) mRootView.findViewById(R.id.btn_baoming);
        tv_call.setOnClickListener(this);
        tv_map.setOnClickListener(this);
        ll_tocommdetail.setOnClickListener(this);
        cb_agree.setChecked(false);
        tv_tiaokuan.setOnClickListener(this);
        btn_baoming.setBackground(CourseItemActivity.this.getResources().getDrawable(R.drawable.shap_gb));
//        setData();
//        if(activityDeatilBean.getData().getSign_up_no()==activityDeatilBean.getData().getTotal_no()){
//            btn_bao.setText("人数已满");
//        }
//        if(activityDeatilBean.getData().getIs_sign_no().equals("1")){
//            btn_bao.setText("已报名");
//        }

        cb_agree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    if (activityDeatilBean.getData().getSign_up_no() == activityDeatilBean.getData().getTotal_no()) {
                        btn_baoming.setBackground(CourseItemActivity.this.getResources().getDrawable(R.drawable.shap_gb));
                        ToastUtil.show("人数已满");
                    } else if (activityDeatilBean.getData().getIs_sign_no().equals("1")) {
                        btn_baoming.setBackground(CourseItemActivity.this.getResources().getDrawable(R.drawable.shap_gb));
//                        btn_baoming.setBackgroundColor(ActionItemActivity.this.getResources().getColor(R.color.gray_deep));
                        ToastUtil.show("已报名");
                    } else {
                        btn_baoming.setBackground(CourseItemActivity.this.getResources().getDrawable(R.drawable.shape_tailor_sure));
//                        btn_baoming.setBackgroundColor(ActionItemActivity.this.getResources().getColor(R.color.indicator_selected_color));
                    }

                } else {
                    btn_baoming.setBackground(CourseItemActivity.this.getResources().getDrawable(R.drawable.shap_gb));
//                    btn_baoming.setFocusable(true);
//                    btn_baoming.setEnabled(true);
//                    btn_baoming.setClickable(true);
                }
            }
        });
        btn_bao.requestFocus();
        btn_bao.setOnClickListener(this);

        iv_share.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setData();
    }

    protected void getTitleList(List<String> mTitleLists) {
        mTitleLists.clear();
        mTitleLists.add("活动介绍");
//        mTitleLists.add("教练介绍");
//        mTitleLists.add("其他课程");
    }
    EquipInfromationFragment equipInfromationFragment;
    MacthRoleFragment macthRoleFragment;
    protected void getFragmentLists(List<BaseFragment> mFragmentLists) {
//        mFragmentLists.add(new ActionInformaTionFragment(getIntent().getIntExtra("PK_groupid", -1)));
//        equipInfromationFragment=  new EquipInfromationFragment();
//        macthRoleFragment=new MacthRoleFragment("");
//        mFragmentLists.add(equipInfromationFragment);
//        mFragmentLists.add(macthRoleFragment);
        mFragmentLists.clear();
if("3".equals(activityDeatilBean.getData().getGroupType())){
    for (int i=0;i<activityDeatilBean.getData().getRemarkImg().size();i++){
        activityDeatilBean.getData().getCoachRemarkImg().add(activityDeatilBean.getData().getRemarkImg().get(i));
    }
    mFragmentLists.add(new ActionIntroFragment(activityDeatilBean.getData().getCoachRemarkImg(),activityDeatilBean.getData().getCoacheRemark()+activityDeatilBean.getData().getRemark()));
}else {
    mFragmentLists.add(new ActionIntroFragment(activityDeatilBean.getData().getRemarkImg(),activityDeatilBean.getData().getRemark()));
}

//        mFragmentLists.add(new ActionIntroFragment(activityDeatilBean.getData().getRemarkImg(),activityDeatilBean.getData().getRemark()));
//        mFragmentLists.add(new ActionIntroFragment(activityDeatilBean.getData().getRemarkImg(),activityDeatilBean.getData().getRemark()));
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


    ActivityDeatilBean activityDeatilBean;
    ArrayList <String> iamgs=new ArrayList<>();
    private void setData() {
        iamgs.clear();
        RestClient.getYfmNovate(this).post(Constant.API.YFM_ACTIVITYDETAIL, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("group_activity_id", getIntent().getIntExtra("activityid", -1))
                .build(), new DmeycBaseSubscriber<ActivityDeatilBean>() {
            @Override
            public void onSuccess(ActivityDeatilBean bean) {
                activityDeatilBean = bean;
//                ToastUtil.show(bean.getMsg());

                long stattime = DateUtil.getlongToDate(activityDeatilBean.getData().getStart_date());
                int downtime = (int) (stattime / 1000) - (int) (System.currentTimeMillis() / 1000);
//                int downtime= 20;
                if (downtime <= 0) {
                    btn_baoming.setBackground(CourseItemActivity.this.getResources().getDrawable(R.drawable.shap_gb));
                    btn_baoming.setText("报名截止");
                    time_desc.setText("活动报名已截止..");
                    time_desc.setTextColor(getResources().getColor(R.color.red));
                    countdownView.setVisibility(View.GONE);

                    cb_agree.setEnabled(false);
                    cb_agree.setChecked(false);
                    cb_agree.setFocusable(false);
                    btn_bao.setClickable(false);
                }

                CountdownTime.onTimeCountdownOverListener = new CountdownTime.OnTimeCountdownOverListener() {
                    @Override
                    public void onTimeCountdownOver() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                btn_baoming.setBackground(CourseItemActivity.this.getResources().getDrawable(R.drawable.shap_gb));
                                btn_baoming.setText("报名截止");
                                time_desc.setText("活动报名已截止..");
                                time_desc.setTextColor(getResources().getColor(R.color.red));
                                countdownView.setVisibility(View.GONE);
                                cb_agree.setChecked(false);
                                cb_agree.setEnabled(false);
                                cb_agree.setFocusable(false);
                                btn_bao.setClickable(false);
                            }
                        });

                        Log.d("Blin QueueMangerOver", "回调了");
                    }
                };


                countdownView.setCountdownTime(downtime, "");

//                GlideUtil.loadImage(ActionItemActivity.this,bean.getData().getGroup_logo(),iv_activitylog);
                tv_activityname.setText(bean.getData().getActivity_name());
//                tv_activityaddrss.setText(bean.getData().getActivity_address());
                tv_activityaddrss.setText(bean.getData().getActivity_address()+"("+bean.getData().getVenueName()+")");
                tv_phone.setText(bean.getData().getOwner_name() + "  " + bean.getData().getActivity_phone_no());


                tv_money.setText("游客"+bean.getData().getM_visitor_amount()+"元/人次");
                tv_moneyvip.setText("会员"+bean.getData().getM_member_amount()+"元/人次");
                if("0".equals(bean.getData().getIs_w_discount())){
                    tv_moneywoman.setVisibility(View.GONE);
                }else {
                    tv_moneywoman.setVisibility(View.VISIBLE);
//                    tv_moneywoman.setText("女士优惠"+bean.getData().getW_discount_amount()+"元/人次");
                }
                tv_time.setText(bean.getData().getStart_date()+"  时长"+bean.getData().getDuration()+"小时");
//                tv_time.setText(bean.getData().getStart_date());
                tv_activityintro.setText(bean.getData().getRemark());
                tv_inperson.setText(bean.getData().getSign_up_no() + "");
                tv_personnum.setText("/" + bean.getData().getTotal_no() + "人");
                rv_member.setAdapter(new CourseItemActivity.iconAdapter());
                LinearLayoutManager lm = new LinearLayoutManager(CourseItemActivity.this);
                lm.setOrientation(OrientationHelper.HORIZONTAL);
                rv_member.setLayoutManager(lm);
                if (activityDeatilBean.getData().getSign_up_no() == activityDeatilBean.getData().getTotal_no()) {
                    btn_baoming.setBackground(CourseItemActivity.this.getResources().getDrawable(R.drawable.shap_gb));
                    return;
                }


                if(activityDeatilBean!=null&&activityDeatilBean.getData()!=null){
                    if(activityDeatilBean.getData().getSign_up_no()==activityDeatilBean.getData().getTotal_no()){
                        btn_bao.setText("人数已满");
                    }
                    if(activityDeatilBean.getData().getIs_sign_no().equals("1")){
                        btn_bao.setText("已报名");
                    }
                }

//             long stattime=  DateUtil.getlongToDate(activityDeatilBean.getData().getStart_date());
//            int downtime=  (int)(stattime/1000)-(int)(System.currentTimeMillis()/1000);
////                int downtime= 20;
//            if(downtime<=0){
//                btn_baoming.setBackgroundColor(ActionItemActivity.this.getResources().getColor(R.color.gray_deep));
//                btn_baoming.setText("报名截止");
//                time_desc.setText("活动报名已截止..");
//                time_desc.setTextColor(getResources().getColor(R.color.red));
//                countdownView.setVisibility(View.GONE);
//                cb_agree.setEnabled(false);
//                cb_agree.setChecked(false);
//                cb_agree.setFocusable(false);
//                btn_bao.setClickable(false);
//            }
//
//                CountdownTime.onTimeCountdownOverListener = new CountdownTime.OnTimeCountdownOverListener() {
//                    @Override
//                    public void onTimeCountdownOver() {
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                btn_baoming.setBackgroundColor(ActionItemActivity.this.getResources().getColor(R.color.gray_deep));
//                                btn_baoming.setText("报名截止");
//                                time_desc.setText("活动报名已截止..");
//                                time_desc.setTextColor(getResources().getColor(R.color.red));
//                                countdownView.setVisibility(View.GONE);
//                                cb_agree.setChecked(false);
//                                cb_agree.setEnabled(false);
//                                cb_agree.setFocusable(false);
//                                btn_bao.setClickable(false);
//                            }
//                        });
//
//                        Log.d("Blin QueueMangerOver","回调了");
//                    }
//                };
//
//
//                countdownView.setCountdownTime(downtime,"");


//                getTitleList(mTitleLists);
//                getFragmentLists(mFragmentLists);


                if("3".equals(activityDeatilBean.getData().getGroupType())){
                    if(activityDeatilBean.getData().getRemarkImg()!=null){
                        for (int i=0;i<activityDeatilBean.getData().getRemarkImg().size();i++){
                            iamgs.add(activityDeatilBean.getData().getRemarkImg().get(i));
                        }
                    }
                    if(activityDeatilBean.getData().getCoachRemarkImg()!=null){
                        for (int i=0;i<activityDeatilBean.getData().getCoachRemarkImg().size();i++){
                            iamgs.add(activityDeatilBean.getData().getCoachRemarkImg().get(i));
                        }
                    }
                    tv_intro.setText(activityDeatilBean.getData().getCoacheRemark()+activityDeatilBean.getData().getRemark());
                    if(iamgs==null||iamgs.size()==0){
                        banner.setVisibility(View.GONE);
                    }else {
                        banner.setVisibility(View.VISIBLE);
                        banner.setImageLoader(new GlideImageLoader());
                        banner.setImages(iamgs);
                        banner.start();
                    }
//                    mFragmentLists.add(new ActionIntroFragment(activityDeatilBean.getData().getCoachRemarkImg(),activityDeatilBean.getData().getCoacheRemark()+activityDeatilBean.getData().getRemark()));
                }else {
                    if(activityDeatilBean.getData().getRemarkImg()!=null){
                        for (int i=0;i<activityDeatilBean.getData().getRemarkImg().size();i++){
                            iamgs.add(activityDeatilBean.getData().getRemarkImg().get(i));
                        }
                    }

                    if(iamgs==null||iamgs.size()==0){
                        banner.setVisibility(View.GONE);
                    }else {
                        banner.setVisibility(View.VISIBLE);
                        banner.setImageLoader(new GlideImageLoader());
                        banner.setImages(iamgs);
                        banner.start();
                    }
//                    mFragmentLists.add(new ActionIntroFragment(activityDeatilBean.getData().getRemarkImg(),activityDeatilBean.getData().getRemark()));
                }





//                FragmentManager fm =getSupportFragmentManager();
//                pkrule_pager.setAdapter(new CustomLazyViewPagerAdapter(fm,mFragmentLists,mTitleLists));
//        pk_ruletablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
//                pk_ruletablayout.setupWithViewPager(pkrule_pager);
//                LinearLayout.LayoutParams ll=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
//                ll.height=300;
//                pkrule_pager.setLayoutParams(ll);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    final public static int REQUEST_CODE_ASK_CALL_PHONE = 123;
    private CommDialog.Builder builder;
    private CommDialog mDialog;

    @Override
    public void onClick(View view) {
        int viewid = view.getId();
        if (viewid == R.id.btn_baoming) {
            if (!cb_agree.isChecked()) {
                ToastUtil.show("请同意条款");
                return;
            }
            if (activityDeatilBean.getData().getIs_sign_no().equals("1")) {
                btn_baoming.setBackground(CourseItemActivity.this.getResources().getDrawable(R.drawable.shap_gb));
                ToastUtil.show("已报名");
                return;
            }
            if (activityDeatilBean.getData().getSign_up_no() == activityDeatilBean.getData().getTotal_no()) {
                ToastUtil.show("人数已满");
                return;
            }

            if("0".equals(activityDeatilBean.getData().getIs_join_group())&&"4".equals(activityDeatilBean.getData().getGroupType())){
                ToastUtil.show("请先加入到该群内  否则无法报名");
                return;
            }
            startActivity(new Intent(this, SubmitOrderActivity.class).
                    putExtra("activityid", getIntent().getIntExtra("activityid", -1)).
                    putExtra("groupname", activityDeatilBean.getData().getGroup_name()).
                    putExtra("adress", activityDeatilBean.getData().getActivity_address()).
                    putExtra("activityname", activityDeatilBean.getData().getActivity_name()).
                    putExtra("owername", activityDeatilBean.getData().getOwner_name()).
                    putExtra("money", activityDeatilBean.getData().getMember_amount() + "").
                    putExtra("time", activityDeatilBean.getData().getStart_date())
            );
        } else if (R.id.tv_call == viewid) {
            builder = new CommDialog.Builder(this);
            showTwoButtonDialog("提示\n确认拨打电话：" + activityDeatilBean.getData().getActivity_phone_no() + "吗?", "取消", "确定", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                    //这里写自定义处理XXX
                }
            }, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();

                    if (Build.VERSION.SDK_INT >= 23) {
                        int checkCallPhonePermission = ContextCompat.checkSelfPermission(CourseItemActivity.this,
                                Manifest.permission.CALL_PHONE);
                        if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(CourseItemActivity.this, new String[]{
                                    Manifest.permission.CALL_PHONE
                            }, REQUEST_CODE_ASK_CALL_PHONE);
                            return;
                        } else {
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            Uri data = Uri.parse("tel:" + activityDeatilBean.getData().getActivity_phone_no());
                            intent.setData(data);
                            startActivity(intent);
                            // 上面已经写好的拨号方法 callDirectly(mobile);
                        }
                    } else {
                        // 上面已经写好的拨号方法 callDirectly(mobile);
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        Uri data = Uri.parse("tel:" + activityDeatilBean.getData().getActivity_phone_no());
                        intent.setData(data);
                        startActivity(intent);
                    }


                    //这里写自定义处理XXX
//                    Intent intent = new Intent(Intent.ACTION_CALL);
//                    Uri data = Uri.parse("tel:" + activityDeatilBean.getData().getActivity_phone_no());
//                    intent.setData(data);
//                    startActivity(intent);
                }
            });
        } else if (R.id.tv_map == viewid) {

            final List<String> strings = new ArrayList<>();
            strings.add("高德地图");
            strings.add("百度地图");

            StyledDialog.buildBottomItemDialog(CourseItemActivity.this, strings, "cancle",  new MyItemDialogListener() {
                @Override
                public void onItemClick(CharSequence text, int position) {
                    if(0==position){
                        if (isAvilible(CourseItemActivity.this, "com.autonavi.minimap")) {

                            StringBuffer stringBuffer = new StringBuffer("androidamap://route?sourceApplication=").append("amap");

                            stringBuffer.append("&dlat=").append(activityDeatilBean.getData().getLatitude())
                                    .append("&dlon=").append(activityDeatilBean.getData().getLongitude())
                                    .append("&dname=").append(activityDeatilBean.getData().getActivity_address())
                                    .append("&dev=").append(0)
                                    .append("&t=").append(2);

                            Intent intent = new Intent("android.intent.action.VIEW", android.net.Uri.parse(stringBuffer.toString()));
                            intent.setPackage("com.autonavi.minimap");
                            startActivity(intent);


//                try {
//                    //sourceApplication
//
////                    Intent intent = Intent.getIntent("androidamap://navi?sourceApplication=公司的名称（随意写）&poiname=我的目的地&lat=" + activityDeatilBean.getData().getLatitude()+ "&lon=" + activityDeatilBean.getData().getLongitude() + "&dev=0");
////                    startActivity(intent);
//                } catch (URISyntaxException e) {
//                    e.printStackTrace();
//                }
                        } else {
//                            startNavi();
                ToastUtil.show("您尚未安装高德地图或地图版本过低");
                        }
                    }else {
                        startNavi();
                    }
                }

                @Override
                public void onBottomBtnClick() {
//                        ToastUtil.show("sss+");
                }
            }).show();




//            AmapNaviParams params = new AmapNaviParams(new Poi("北京站", p3, ""), null, new Poi("故宫博物院", p2, ""), AmapNaviType.DRIVER);
//            params.setUseInnerVoice(true);
//            AmapNaviPage.getInstance().showRouteActivity(getApplicationContext(), params, ActionItemActivity.this);
        } else if (R.id.ll_tocommdetail == viewid) {
            startActivity(new Intent(CourseItemActivity.this, ChartInforActivity.class).putExtra("group_id", activityDeatilBean.getData().getGroup_id()));
        } else if (R.id.iv_shopcar == viewid) {
            RongIM.getInstance().startGroupChat(CourseItemActivity.this, activityDeatilBean.getData().getGroup_id() + "", activityDeatilBean.getData().getGroup_name());
        } else if (R.id.iv_share == viewid) {
            if (activityDeatilBean != null && activityDeatilBean.getData() != null) {
                CommSharePopWin dd=    new CommSharePopWin.Builder(CourseItemActivity.this).setContent(activityDeatilBean.getData().getActivity_name())
                    //Radius越大耗时越长,被图片处理图像越模糊
                    .setRadius(3).setTitle(activityDeatilBean.getData().getStart_date()+"  "+activityDeatilBean.getData().getActivity_address())
                    //设置居中还是底部显示
                    .setUrl(Constant.API.YFM_SHAREBASE_URL+Constant.API.SHARE_ACTIONITEM+"&group_activity_id="+getIntent().getIntExtra("activityid",-1)+"&groupType="+activityDeatilBean.getData().getGroupType())
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

                dd.setOnCommShareClick(new CommSharePopWin.CommShareClickLisenter() {
                    @Override
                    public void onCOMMClick() {
//                        ToastUtil.show("sss");
                        TextMessage myTextMessage=null;
                        RoClodBean roClodBean=null;
                        if("1".equals( activityDeatilBean.getData().getIsPk())){
                            if(TextUtils.isEmpty(activityDeatilBean.getData().getIsSponser())){

                            }else {
                                if("1".equals(activityDeatilBean.getData().getIsSponser())){
//                                            SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                                            try {
//                                           Str     dateFm.parse(activityDeatilBean.getData().getStart_date());
//                                            } catch (ParseException e) {
//                                                e.printStackTrace();
//                                            }
                                    myTextMessage = TextMessage.obtain("俱乐部"+activityDeatilBean.getData().getGroup_name()+"要来砸场子，童靴们起来保家护群 ！！！----点击报名");
                                    roClodBean =new RoClodBean();
                                    roClodBean.setPkId(getIntent().getIntExtra("activityid", -1));
                                    roClodBean.setType("RC:pkMatched");
                                }else {
                                    myTextMessage = TextMessage.obtain("俺们去打劫"+activityDeatilBean.getData().getGroup_name()+"俱乐部，兄弟姐妹们冲啊 ！！！----点击报名");
                                    roClodBean =new RoClodBean();
                                    roClodBean.setActivityId(getIntent().getIntExtra("activityid", -1));
                                    roClodBean.setType("RC:pkMatched");
                                }
                            }
                        }else {
                            myTextMessage = TextMessage.obtain("欢迎大家报名新活动！----点击报名");
                            roClodBean =new RoClodBean();
                            roClodBean.setActivityId(getIntent().getIntExtra("activityid", -1));
                            roClodBean.setType("RC:normalActivity");
                        }

                        Gson gs=new Gson();
                        myTextMessage.setExtra(gs.toJson(roClodBean));
                        Message myMessage = Message.obtain(activityDeatilBean.getData().getGroup_id()+"", Conversation.ConversationType.GROUP, myTextMessage);
                        RongIM.getInstance().sendMessage(myMessage, null, null, new IRongCallback.ISendMessageCallback() {
                            @Override
                            public void onAttached(Message message) {
                                //消息本地数据库存储成功的回调
                            }
                            @Override
                            public void onSuccess(Message message) {
                                //消息通过网络发送成功的回调
                                ToastUtil.show("已分享");
                            }

                            @Override
                            public void onError(Message message, RongIMClient.ErrorCode errorCode) {
                                //消息发送失败的回调
                            }
                        });


                    }
                });


            }
        } else if (R.id.tv_tiaokuan == viewid) {
            Intent intent = new Intent(this, WebviewActivity.class);
            intent.putExtra(Constant.Config.TITLE, "免责条款");
            intent.putExtra(Constant.Config.URL,"http://www.hotu.club:9595/agreement/liability.html");
//            intent.putExtra(Constant.Config.URL,"http://192.168.0.104/agreement/liability.html");
//            intent.putExtra(Constant.Config.URL, "http://192.168.0.104/agreement/agreementhz.html");
//            intent.putExtra(Constant.Config.URL, Constant.API.BASE_URL + Constant.API.USER_AGREEMENT);
            startActivity(intent);
        }else if(R.id.ll_mumberitem==viewid){
            if(0==activityDeatilBean.getData().getSign_up_no()){
                ToastUtil.show("暂时没人报名");
                return;
            }
            startActivity(new Intent(CourseItemActivity.this,HasInActionMemBerActivity.class).putExtra("activityid",getIntent().getIntExtra("activityid",-1)).putExtra("isallban",""));
        }
    }

    @Override
    public void onInitNaviFailure() {

    }

    @Override
    public void onGetNavigationText(String s) {

    }

    @Override
    public void onLocationChange(AMapNaviLocation aMapNaviLocation) {

    }

    @Override
    public void onArriveDestination(boolean b) {

    }

    @Override
    public void onStartNavi(int i) {

    }

    @Override
    public void onCalculateRouteSuccess(int[] ints) {

    }

    @Override
    public void onCalculateRouteFailure(int i) {

    }

    @Override
    public void onStopSpeaking() {

    }

    @Override
    public void onReCalculateRoute(int i) {

    }

    @Override
    public void onExitPage(int i) {

    }

    @Override
    public void onStrategyChanged(int i) {

    }

    @Override
    public View getCustomNaviBottomView() {
        //返回null则不显示自定义区域
        return getCustomView("底部自定义区域");
    }

    @Override
    public View getCustomNaviView() {
        //返回null则不显示自定义区域
        return getCustomView("中部自定义区域");
    }

    @Override
    public void onArrivedWayPoint(int i) {

    }

    @Override
    public void onMapTypeChanged(int i) {

    }


    class iconAdapter extends RecyclerView.Adapter<CourseItemActivity.iconAdapter.ViewHolder> {

        public iconAdapter() {

        }

        @Override
        public CourseItemActivity.iconAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.adapter_member, null);
            return new CourseItemActivity.iconAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CourseItemActivity.iconAdapter.ViewHolder holder, int position) {
            holder.civ_avatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(0==activityDeatilBean.getData().getSign_up_no()){
                        ToastUtil.show("暂时没人报名");
                        return;
                    }
                    startActivity(new Intent(CourseItemActivity.this, HasInActionMemBerActivity.class).putExtra("activityid", 14).putExtra("isallban", ""));

                }
            });
            GlideUtil.loadImage(CourseItemActivity.this, activityDeatilBean.getData().getSign_up_list().get(position).getUrl(), holder.civ_avatar);
        }

        @Override
        public int getItemCount() {
            return activityDeatilBean.getData().getSign_up_list().size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public CircleImageView civ_avatar;

            //            private RoundedImageView rimag;
//            private TextView tv_titl;
            public ViewHolder(View itemView) {
                super(itemView);
                civ_avatar = itemView.findViewById(R.id.civ_avatar);
////                recycleview1= (RecyclerView) itemView.findViewById(R.id.recycleview1);
//                rimag= (RoundedImageView) itemView.findViewById(R.id.iv_roundmage);
//                tv_titl=  itemView.findViewById(R.id.tv_titl);

            }
        }
    }

    private void showTwoButtonDialog(String alertText, String confirmText, String cancelText, View.OnClickListener conFirmListener, View.OnClickListener cancelListener) {
        mDialog = builder.setMessage(alertText)
                .setPositiveButton(confirmText, conFirmListener)
                .setNegativeButton(cancelText, cancelListener)
                .createTwoButtonDialog();
        mDialog.show();
    }

    TextView text1;
    TextView text2;

    private View getCustomView(String title) {
        LinearLayout linearLayout = new LinearLayout(this);
        try {
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            text1 = new TextView(this);
            text1.setGravity(Gravity.CENTER);
            text1.setHeight(90);
            text1.setMinWidth(300);
            text1.setText(title);

            text2 = new TextView(this);
            text2.setGravity(Gravity.CENTER);
            text1.setHeight(90);
            text2.setMinWidth(300);
            text2.setText(title);
            linearLayout.setGravity(Gravity.CENTER);
            linearLayout.addView(text1, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            linearLayout.addView(text2, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.height = 100;
            linearLayout.setLayoutParams(params);
        } catch (java.lang.Throwable e) {
            e.printStackTrace();
        }
        return linearLayout;
    }

    public static boolean isAvilible(Context context, String packageName) {
        //获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }

    //    //开启百度导航
    public void startNavi() {
        //移动APP调起Android百度地图方式举例
        if (isAvilible(CourseItemActivity.this, "com.baidu.BaiduMap")) {
            Intent intent = null;
            try {
                intent = Intent.getIntent("intent://map/direction?origin=latlng:"+activityDeatilBean.getData().getLatitude()+","+activityDeatilBean.getData().getLongitude()+"|name:我的位置&destination="+activityDeatilBean.getData().getActivity_address()+"&mode=driving&region=西安&src=yourCompanyName|yourAppName#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
//            intent = Intent.getIntent("intent://map/direction?" +
//                    "origin=大柏树&" +
//                    "destination=" +activityDeatilBean.getData().getActivity_address()+
//                    "&mode=driving&" +
//                    "region=上海市" +
//                    "&src=yourCompanyName|yourAppName#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
            } catch (URISyntaxException e) {
                Log.e("intent", e.getMessage());
            }
            startActivity(intent); //启动调用

        }else {
            ToastUtil.show("您尚未安装百度地图或地图版本过低");
        }

    }
}

