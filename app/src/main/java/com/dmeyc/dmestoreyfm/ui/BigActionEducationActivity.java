package com.dmeyc.dmestoreyfm.ui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.SpecialActionBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.dmeyc.dmestoreyfm.wedgit.H5BlurPopWin;

import butterknife.Bind;
import butterknife.OnClick;

public class BigActionEducationActivity extends BaseActivity {

    @Bind(R.id.civ_avatar)
    CircleImageView civ_avatar;
    @Bind(R.id.tv_ballnum)
    TextView tv_ballnum;
    @Bind(R.id.tv_commname)
    TextView tv_commname;

    @Bind(R.id.tv_clubintro)
    TextView tv_clubintro;
    @Bind(R.id.tv_notice)
    TextView tv_notice;
    @Bind(R.id.tv_idenity)
    TextView tv_idenity;

    @Bind(R.id.line3)
    View view3;
    @Bind(R.id.line2)
    View view2;
    @Bind(R.id.line1)
    View view1;
    @Bind(R.id.rv_member)
    RecyclerView rv_member;
    @Bind(R.id.tv_activityname)
    TextView tv_activityname;
    @Bind(R.id.tv_time)
    TextView tv_time;
    @Bind(R.id.tv_place)
    TextView tv_place;
    @Bind(R.id.tv_adress)
    TextView tv_adress;
    @Bind(R.id.tv_leader)
    TextView tv_leader;
    @Bind(R.id.tv_rule)
    TextView tv_rule;
    @Bind(R.id.adword)
    TextView adword;
    @Bind(R.id.ll_infros)
    LinearLayout ll_infros;
    @Bind(R.id.tv_tochanlage)
    TextView tv_tochanlage;
@Bind(R.id.iv_share)
    ImageView iv_share;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_bigactioneducation;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        getSpicialInfor();
    }
    private String Shareurl;
    @OnClick({R.id.tv_tochanlage, R.id.iv_share})
    public void onClick(View view) {
        int viewid = view.getId();
        if (R.id.tv_tochanlage == viewid) {

            startActivity(new Intent(BigActionEducationActivity.this, BigCreartCommListActivity.class).
                    putExtra("groupkid", specialActionBean.getData().getGroupId()).putExtra("activityid",specialActionBean.getData().getId()));

//            goShop();
        }else if(R.id.iv_share==viewid){
            if(specialActionBean==null){
                ToastUtil.show("暂无数据");
                return;
            }
            if(specialActionBean.getData()==null){
                ToastUtil.show("暂无数据");
                return;
            }

                Shareurl= Constant.API.YFM_SHAREBASE_URL+ Constant.API.SHARE_BIGACTION+"&activityId="+specialActionBean.getData().getId();

            new H5BlurPopWin.Builder(BigActionEducationActivity.this).setContent(specialActionBean.getData().getActivityName())
                    //Radius越大耗时越长,被图片处理图像越模糊
                    .setRadius(3).setTitle(specialActionBean.getData().getActivityName())
                    //设置居中还是底部显示&activity_id=XX
//                    .setId(getIntent().getIntExtra("activityid",-1),getIntent().getStringExtra("ispk"))
                    .setUrl(Shareurl)
//                    .setId(getIntent().getIntExtra("activityid",-1))
                    .setshowAtLocationType(1)
                    .setShowCloseButton(true)
                    .setOutSideClickable(false)
                    .show(iv_share);
        }
    }

    Dialog dialog;
    ListView lv_shop;
    TextView alltv_price;
    TextView tv_toinfo, tv_changeident;

    public void goShop() {
        dialog = new Dialog(BigActionEducationActivity.this, R.style.MyDialog);
        //设置它的ContentView
        setFinishOnTouchOutside(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_commin);
        Window window = dialog.getWindow();

        WindowManager.LayoutParams lp = window.getAttributes();
        window.setGravity(Gravity.CENTER);
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高
        WindowManager.LayoutParams params = window.getAttributes();
        params.height = (int) (d.getHeight() * 0.3); // 高度设置为屏幕的0.5
        params.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.6
        window.setAttributes(params);
        tv_toinfo = dialog.findViewById(R.id.tv_toinfo);

//        lv_shop = dialog.findViewById(R.id.lv_shop);
        tv_changeident = dialog.findViewById(R.id.tv_changeident);
        tv_toinfo.setText("我的群");
        tv_changeident.setText("关闭");
        dialog.show();
        tv_toinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                startActivity(new Intent(BigActionEducationActivity.this, BigCreartCommListActivity.class).putExtra("groupkid", 1));

            }
        });

        tv_changeident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
//                startActivity(new Intent(CommInActivity.this,ChartInforActivity.class).putExtra("group_id",(int)commonBean.getData()));
//                finish();
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
    SpecialActionBean specialActionBean;
    public void getSpicialInfor() {
        RestClient.getYfmNovate(this).get(Constant.API.YFM_SPECIALACTION, new ParamMap.Build()
//                .addParams("user_token", "5c76f9af5cf940efbf7e66e2c9bfe661")
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<SpecialActionBean>() {

            @Override
            public void onSuccess(SpecialActionBean bean) {
//                ToastUtil.show(bean.getMsg());
                specialActionBean=bean;
                GlideUtil.loadImage(BigActionEducationActivity.this,bean.getData().getGroupLogo(),civ_avatar);
                tv_ballnum.setText(bean.getData().getGroupBattle()+"");
                tv_commname.setText(bean.getData().getGroupName());
                tv_activityname.setText(bean.getData().getActivityName());
                tv_time.setText(bean.getData().getStartDate());
                tv_place.setText(bean.getData().getVenueName());
                tv_adress.setText(bean.getData().getActivityAddress());
                tv_leader.setText(bean.getData().getPublishUserName()+"  "+bean.getData().getActivityPhoneNo());
                tv_rule.setText(bean.getData().getGameRule());
                adword.setText(bean.getData().getAward());
                if("0".equals(bean.getData().getJoinStatus())){
                    tv_tochanlage.setBackground(getResources().getDrawable(R.drawable.shap_red_cilce));
                    tv_tochanlage.setText("报名");
                }else if("1".equals(bean.getData().getJoinStatus())){
                    tv_tochanlage.setBackground(getResources().getDrawable(R.drawable.shap_gray_cicle));
                    tv_tochanlage.setText("等待审核");
                }else if("2".equals(bean.getData().getJoinStatus())){
                    tv_tochanlage.setBackground(getResources().getDrawable(R.drawable.shap_gray_cicle));
                    tv_tochanlage.setText("已同意");
                }else if("3".equals(bean.getData().getJoinStatus())){
                    tv_tochanlage.setBackground(getResources().getDrawable(R.drawable.shap_gray_cicle));
                    tv_tochanlage.setText("已拒绝");
                }else {
                    tv_tochanlage.setVisibility(View.GONE);
                }
                LinearLayoutManager lm=  new LinearLayoutManager(BigActionEducationActivity.this);
                lm.setOrientation(OrientationHelper.HORIZONTAL);
                rv_member.setLayoutManager(lm);
                rv_member.setAdapter(new iconAdapter());
            }
        });
    }
    @OnClick({R.id.ll_infro, R.id.ll_rule, R.id.ll_give})
    public void onclick(View view){
        int viewid=view.getId();
        if(R.id.ll_infro==viewid){
            tv_clubintro.setTextColor(getResources().getColor(R.color.black));
            tv_clubintro.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            tv_notice.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            tv_idenity.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            tv_notice.setTextColor(getResources().getColor(R.color.gray_deep));
            tv_idenity.setTextColor(getResources().getColor(R.color.gray_deep));
            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.GONE);
            view3.setVisibility(View.GONE);

            ll_infros.setVisibility(View.VISIBLE);
            adword.setVisibility(View.GONE);
            tv_rule.setVisibility(View.GONE);
        }else if(R.id.ll_rule==viewid){
            tv_clubintro.setTextColor(getResources().getColor(R.color.gray_deep));
            tv_clubintro.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            tv_notice.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            tv_idenity.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            tv_notice.setTextColor(getResources().getColor(R.color.black));
            tv_idenity.setTextColor(getResources().getColor(R.color.gray_deep));
            view1.setVisibility(View.GONE);
            view2.setVisibility(View.VISIBLE);
            view3.setVisibility(View.GONE);

            ll_infros.setVisibility(View.GONE);
            adword.setVisibility(View.GONE);
            tv_rule.setVisibility(View.VISIBLE);
        }else if(R.id.ll_give==viewid){
            tv_clubintro.setTextColor(getResources().getColor(R.color.gray_deep));
            tv_clubintro.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            tv_notice.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            tv_idenity.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            tv_notice.setTextColor(getResources().getColor(R.color.gray_deep));
            tv_idenity.setTextColor(getResources().getColor(R.color.black));
            view1.setVisibility(View.GONE);
            view2.setVisibility(View.GONE);
            view3.setVisibility(View.VISIBLE);

            ll_infros.setVisibility(View.GONE);
            adword.setVisibility(View.VISIBLE);
            tv_rule.setVisibility(View.GONE);
        }

    }


    class iconAdapter extends RecyclerView.Adapter<iconAdapter.ViewHolder>{


        @Override
        public iconAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view =getLayoutInflater().inflate(R.layout.adapter_member,null);
            return new iconAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(iconAdapter.ViewHolder holder, final int position){
            GlideUtil.loadImage(BigActionEducationActivity.this,specialActionBean.getData().getAccepts().get(position).getGroupLogo(),holder.civ_avatar);
            holder.civ_avatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(BigActionEducationActivity.this, CheckBigActionActivity.class)
                            .putExtra("ispublish",specialActionBean.getData().getIsPublishUser()).putExtra("activityid",specialActionBean.getData().getId()));
//                    onItemClickListener.onClick(position);
                }
            });
        }
        @Override
        public int getItemCount() {
            return specialActionBean.getData().getAccepts().size();
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
}
