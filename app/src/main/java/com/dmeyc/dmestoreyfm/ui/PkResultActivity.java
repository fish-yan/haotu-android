package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.CustomLazyViewPagerAdapter;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.MatchResultBean;
import com.dmeyc.dmestoreyfm.bean.MyCreatCommListBean;
import com.dmeyc.dmestoreyfm.bean.PkResultDetailBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.dialog.ShareDialog;
import com.dmeyc.dmestoreyfm.fragment.ActionInformaTionFragment;
import com.dmeyc.dmestoreyfm.fragment.ActionItemFragemnt;
import com.dmeyc.dmestoreyfm.fragment.EquipInfromationFragment;
import com.dmeyc.dmestoreyfm.fragment.MacthRoleFragment;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.BlurPopWin;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.dmeyc.dmestoreyfm.wedgit.NoScrollListView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class PkResultActivity extends BaseActivity {


    private ViewPager pkrule_pager;
    private TabLayout pk_ruletablayout;
    protected List<String> mTitleLists; //导航条文字List
    protected List<BaseFragment> mFragmentLists;    //导航fragment list
@Bind(R.id.rv_member)
    RecyclerView rv_member;
    @Bind(R.id.iv_share)
    ImageView iv_share;

    @Bind(R.id.civ_avatar)
    CircleImageView civ_avatar;
    @Bind(R.id.tv_commname)
    TextView tv_commname;
    @Bind(R.id.tv_ballnum)
    TextView tv_ballnum;
    @Bind(R.id.ll_bottom)
    LinearLayout ll_bottom;
@Bind(R.id.tv_tochanlage)
    TextView tv_tochanlage;

    @Override
    protected int getLayoutRes() {

        return R.layout.activity_pkresult;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

        pk_ruletablayout = (TabLayout) mRootView.findViewById(R.id.pk_ruletablayout); //使用bind 会出现空指针
        pk_ruletablayout.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(pk_ruletablayout,20,20);
            }
        });
        pkrule_pager = (ViewPager) mRootView.findViewById(R.id.pkrule_pager); //使用bind 会出现空指针
        mTitleLists = new ArrayList<>();
        mFragmentLists = new ArrayList<>();
        GlideUtil.loadImage(this,getIntent().getStringExtra("headurl"),civ_avatar);
        tv_commname.setText(getIntent().getStringExtra("graopname"));
        setData();
    }

    PkResultDetailBean pkResultDetailBean;
    public void setData() {
        RestClient.getYfmNovate(this).post(Constant.API.YFM_PKRESULT, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
//                .addParams("group_pk_id", 1)
                        .addParams("group_pk_id", getIntent().getIntExtra("PK_groupid",-1))
                .build(), new DmeycBaseSubscriber<PkResultDetailBean>() {
            @Override
            public void onSuccess(PkResultDetailBean bean) {
                pkResultDetailBean=bean;
                getTitleList(mTitleLists);
                getFragmentLists(mFragmentLists);

                FragmentManager fm =getSupportFragmentManager();
                pkrule_pager.setAdapter(new CustomLazyViewPagerAdapter(fm,mFragmentLists,mTitleLists));
//        pk_ruletablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
                pk_ruletablayout.setupWithViewPager(pkrule_pager);
                equipInfromationFragment.setdata(pkResultDetailBean.getData().getBrand_name(),pkResultDetailBean.getData().getBrand_ball_url());
                macthRoleFragment.setData(pkResultDetailBean.getData().getRemark());
                tv_ballnum.setText(pkResultDetailBean.getData().getBattle_a_num()+"");

                LinearLayoutManager lm=  new LinearLayoutManager(PkResultActivity.this);
                lm.setOrientation(OrientationHelper.HORIZONTAL);
                rv_member.setLayoutManager(lm);
                rv_member.setAdapter(new PkResultActivity.iconAdapter());
                if("0".equals(bean.getData().getIs_group_a_owner())){
                    if("1".equals(getIntent().getStringExtra("isgover"))){
                        tv_tochanlage.setVisibility(View.GONE);
                    }else {
                        tv_tochanlage.setVisibility(View.VISIBLE);
                    }
                }else {
                    tv_tochanlage.setVisibility(View.GONE);
                }
//                if("0".equals(getIntent().getStringExtra("ispked"))){
//                    tv_tochanlage.setBackground(getResources().getDrawable(R.drawable.shap_red_cilce));
//                    tv_tochanlage.setText("挑战TA");
//                }else if("1".equals(getIntent().getStringExtra("ispked"))){
//                    tv_tochanlage.setBackground(getResources().getDrawable(R.drawable.shap_gray_cicle));
//                    tv_tochanlage.setText("待同意");
//                    tv_tochanlage.setClickable(false);
//                }else if("2".equals(getIntent().getStringExtra("ispked"))){
//                    tv_tochanlage.setBackground(getResources().getDrawable(R.drawable.shap_gray_cicle));
//                    tv_tochanlage.setText("已同意");
//                    tv_tochanlage.setClickable(false);
//                }else {
//                    tv_tochanlage.setBackground(getResources().getDrawable(R.drawable.shap_gray_cicle));
//                    tv_tochanlage.setText("已拒绝");
//                    tv_tochanlage.setClickable(false);
//                }

                if("0".equals(bean.getData().getStatus())){
                    tv_tochanlage.setBackground(getResources().getDrawable(R.drawable.shap_red_cilce));
                    tv_tochanlage.setText("挑战TA");
                }else if("1".equals(bean.getData().getStatus())){
                    tv_tochanlage.setBackground(getResources().getDrawable(R.drawable.shap_gray_cicle));
                    tv_tochanlage.setText("待同意");
                    tv_tochanlage.setClickable(false);
                }else if("2".equals(bean.getData().getStatus())){
                    tv_tochanlage.setBackground(getResources().getDrawable(R.drawable.shap_gray_cicle));
                    tv_tochanlage.setText("已同意");
                    tv_tochanlage.setClickable(false);
                }else {
                    tv_tochanlage.setVisibility(View.GONE);
//                    tv_tochanlage.setBackground(getResources().getDrawable(R.drawable.shap_gray_cicle));
//                    tv_tochanlage.setText("已拒绝");
//                    tv_tochanlage.setClickable(false);
                }
            }
        });
    }
    protected void getTitleList(List<String> mTitleLists) {
        mTitleLists.add("活动信息");
        mTitleLists.add("装备信息");
        mTitleLists.add("比赛规则");
    }
    EquipInfromationFragment equipInfromationFragment;
    MacthRoleFragment macthRoleFragment;
    protected void getFragmentLists(List<BaseFragment> mFragmentLists) {
            mFragmentLists.add(new ActionInformaTionFragment(getIntent().getIntExtra("PK_groupid", -1)));
        equipInfromationFragment=  new EquipInfromationFragment();
        macthRoleFragment=new MacthRoleFragment(pkResultDetailBean.getData().getRemark());
            mFragmentLists.add(equipInfromationFragment);
            mFragmentLists.add(macthRoleFragment);
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

    class iconAdapter extends RecyclerView.Adapter<PkResultActivity.iconAdapter.ViewHolder>{

        public iconAdapter(){

        }

        @Override
        public PkResultActivity.iconAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view =getLayoutInflater().inflate(R.layout.adapter_member,null);
            return new PkResultActivity.iconAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(PkResultActivity.iconAdapter.ViewHolder holder, final int position) {
//            ToastUtil.show(pkResultDetailBean.getData().getLogos().get(position)+"");
            GlideUtil.loadImage(PkResultActivity.this,pkResultDetailBean.getData().getAccepts().get(position).getGroupLogo(),holder.civ_avatar);
            holder.civ_avatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(PkResultActivity.this,ChartInforActivity.class).putExtra("group_id",pkResultDetailBean.getData().getAccepts().get(position).getGroupChallengeId()));
//                    ToastUtil.show(position+"woshi ");
                }
            });
        }
        @Override
        public int getItemCount() {
            return pkResultDetailBean.getData().getAccepts().size();
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

@OnClick({R.id.iv_share,R.id.ll_bottom,R.id.tv_tochanlage})
    public void onClick(View view){
       int veid= view.getId();
       if(R.id.iv_share==veid){
           new BlurPopWin.Builder(PkResultActivity.this).setContent(pkResultDetailBean.getData().getGroup_pk_name())
                   //Radius越大耗时越长,被图片处理图像越模糊
                   .setRadius(3).setTitle(pkResultDetailBean.getData().getStart_date()+"  "+pkResultDetailBean.getData().getAddress())
                   //设置居中还是底部显示&groupPkId=XX
                   .setUrl(Constant.API.YFM_SHAREBASE_URL+Constant.API.SHARE_PKDETAIL+"&groupPkId="+getIntent().getIntExtra("PK_groupid",-1))
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

       }else if(R.id.ll_bottom==veid){
//           if("1".equals(pkResultDetailBean.getData().getIs_group_a_owner())){
               startActivity(new Intent(PkResultActivity.this,ToCheckListActivity.class).
                       putExtra("PK_groupid",getIntent().getIntExtra("PK_groupid",-1)).putExtra("is_ower",pkResultDetailBean.getData().getIs_group_a_owner()));
//           }
//           ToastUtil.show("我是全部");
       }else if(R.id.tv_tochanlage==veid){
              getMyComm();
           }
}

    public void getMyComm(){
        ParamMap.Build pb=  new ParamMap.Build();
        RestClient.getYfmNovate(PkResultActivity.this).post(Constant.API.YFM_GETCOMM,
                pb.addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
//                        .addParams("is_have_invoice", check+"")
                        .build(), new DmeycBaseSubscriber<MyCreatCommListBean>() {
                    @Override
                    public void onSuccess(final MyCreatCommListBean bean) {
                        if(bean.getData().size()==0){
                            ToastUtil.show("您没有创建羽毛球群");
                        }else if(bean.getData().size()>0){
                            startActivity(new Intent(PkResultActivity.this,PKMyCreatCommListActivity.class).putExtra("groupkid",1));
                        }
                    }
                });
           }

}

