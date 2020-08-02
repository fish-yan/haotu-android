package com.dmeyc.dmestoreyfm.ui;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.PublishActionAfterBean;
import com.dmeyc.dmestoreyfm.bean.SockInBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ScreenUtil;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.dmeyc.dmestoreyfm.wedgit.CommonPopupWindow;

import butterknife.Bind;

public class BclientSocketInPKActivity extends BaseActivity {

    @Bind(R.id.lv_pkinglist)
    ListView recycleview_pking;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.tv_nodata)
    TextView tv_nodata;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_bclientsockerin;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        setData();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout ll_sockclick;
        private TextView action_onename,action_twoname,tv_actonthreename,tv_action_fourname,tv_teamsocke,tv_teambsocker;
        private CircleImageView cv_actionone,cv_actiontwo,cv_actionthree,cv_actionfour;
        public MyViewHolder(View itemView) {
            super(itemView);
            ll_sockclick= (LinearLayout) itemView.findViewById(R.id.ll_sockclick);
            cv_actionone=(CircleImageView) itemView.findViewById(R.id.cv_actionone);
            cv_actiontwo=(CircleImageView) itemView.findViewById(R.id.cv_actiontwo);
            cv_actionthree=(CircleImageView) itemView.findViewById(R.id.cv_actionthree);
            cv_actionfour=(CircleImageView) itemView.findViewById(R.id.cv_actionfour);
            action_onename=(TextView) itemView.findViewById(R.id.action_onename);
            action_twoname=(TextView) itemView.findViewById(R.id.action_twoname);
            tv_actonthreename=(TextView) itemView.findViewById(R.id.tv_actonthreename);
            tv_action_fourname=(TextView) itemView.findViewById(R.id.tv_action_fourname);
            tv_teamsocke=(TextView) itemView.findViewById(R.id.tv_teamsocke);
            tv_teambsocker=(TextView) itemView.findViewById(R.id.tv_teambsocker);
        }
    }
    SockInBean sockInBean;
    SockInAdapter sockInAdapter;
    String url;
    public void setData(){
//        if("0".equals(getIntent().getStringExtra("is_pk"))){
//            url=Constant.API.YFM_GETRECORD;
//            getnoPKrecord();
//        }else {
        if("1".equals(getIntent().getStringExtra("isgover"))){
            getbigpkrecord();
        }else {
            getpkrecord();
        }

//            url=  ;
//        }



//                recycleview_pking.setAdapter(new android.widget.BaseAdapter() {
//                    @Override
//                    public int getCount() {
//                        return 10;
//                    }
//                    @Override
//                    public Object getItem(int i) {
//                        return null;
//                    }
//
//                    @Override
//                    public long getItemId(int i) {
//                        return 0;
//                    }
//                    BClientSorckerInActivity.MyViewHolder myViewHolder;
//                    @Override
//                    public View getView(final int i, View view, ViewGroup viewGroup) {
//                        if(view==null){
//                            view =getLayoutInflater().inflate(R.layout.adapter_bclientsocin,null);
//                            myViewHolder=new BClientSorckerInActivity.MyViewHolder(view);
//                            view.setTag(myViewHolder);
//                        }else {
//                            myViewHolder=(BClientSorckerInActivity.MyViewHolder) view.getTag();
//                        }
//                        myViewHolder.ll_sockclick.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
////                                ToastUtil.show(i+"ssssss");
//                                initTimePopupWindow();
//                                PopupWindow win=window.getPopupWindow();
//                                win.setAnimationStyle(R.style.animAlpha);
//                                window.showAtLocation(tv_title, Gravity.CENTER,50,ScreenUtil.dp2px(BClientSorckerInActivity.this,60));
////            window.showAsDropDown(tv_twonextdaybell,  0, 50);
//                                WindowManager.LayoutParams lp=getWindow().getAttributes();
//                                lp.alpha=0.5f;
//                                win.setFocusable(true);
//                                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//                               getWindow().setAttributes(lp);
//                            }
//                        });
//                        return view;
//                    }
//                });
    }
    //    PickerView second_pv;
    TextView btn_sub,tv_dissmiss;

    EditText et_sokinteamone,et_teamtwosokin;
    private CommonPopupWindow window;
    private void initTimePopupWindow() {
        // get the height of screen
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        window=new CommonPopupWindow(getActivity(), R.layout.pop_time,ViewGroup.LayoutParams.MATCH_PARENT, 500) {
        window=new CommonPopupWindow(this, R.layout.pop_sockerin, ScreenUtil.dp2px(this,324), ScreenUtil.dp2px(this,201)) {
            @Override
            protected void initView() {
                View view=getContentView();
                et_sokinteamone=(EditText)view.findViewById(R.id.et_sokinteamone);
                et_teamtwosokin=(EditText) view.findViewById(R.id.et_teamtwosokin);
//                et_sokinteamone.setText(sockInBean.getData().get(clickitem).getTeam_a_score()+"");
                et_sokinteamone.setHint(sockInBean.getData().getList().get(clickitem).getTeam_a_score()+"");
//                et_teamtwosokin.setText(sockInBean.getData().get(clickitem).getTeam_b_score()+"");
                et_teamtwosokin.setHint(sockInBean.getData().getList().get(clickitem).getTeam_b_score()+"");
                btn_sub=(TextView) view.findViewById(R.id.btn_sub);
                tv_dissmiss=view.findViewById(R.id.tv_dissmiss);
            }

            @Override
            protected void initWindow() {
                super.initWindow();
                PopupWindow instance=getPopupWindow();

                tv_dissmiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        window.getPopupWindow().dismiss();
                    }
                });
                btn_sub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                            ToSockIn();

                        window.getPopupWindow().dismiss();
                    }
                });
                instance.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp=getWindow().getAttributes();
                        lp.alpha=1f;
                        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        getWindow().setAttributes(lp);
                    }
                });
            }
            @Override
            protected void initEvent() {


            }
        };

    }
    public int clickitem=-1;

    public class SockInAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return sockInBean.getData().getList().size();
        }
        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }
       MyViewHolder myViewHolder;
        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            if(view==null){
                view =getLayoutInflater().inflate(R.layout.adapter_bclientsocin,null);
                myViewHolder=new MyViewHolder(view);
                view.setTag(myViewHolder);
            }else {
                myViewHolder=(MyViewHolder) view.getTag();
            }
            GlideUtil.loadImage(BclientSocketInPKActivity.this,sockInBean.getData().getList().get(i).getTeam_a_member_a_logo(),myViewHolder.cv_actionone);
            GlideUtil.loadImage(BclientSocketInPKActivity.this,sockInBean.getData().getList().get(i).getTeam_a_member_b_logo(),myViewHolder.cv_actiontwo);
            GlideUtil.loadImage(BclientSocketInPKActivity.this,sockInBean.getData().getList().get(i).getTeam_b_member_a_logo(),myViewHolder.cv_actionthree);
            GlideUtil.loadImage(BclientSocketInPKActivity.this,sockInBean.getData().getList().get(i).getTeam_b_member_b_logo(),myViewHolder.cv_actionfour);
            myViewHolder.action_onename.setText(sockInBean.getData().getList().get(i).getTeam_a_member_a_nickname());
            myViewHolder.action_twoname.setText(sockInBean.getData().getList().get(i).getTeam_a_member_b_nickname());
            myViewHolder.tv_actonthreename.setText(sockInBean.getData().getList().get(i).getTeam_b_member_a_nickname());
            myViewHolder.tv_action_fourname.setText(sockInBean.getData().getList().get(i).getTeam_b_member_b_nickname());
            myViewHolder.tv_teamsocke.setText(sockInBean.getData().getList().get(i).getTeam_a_score()+"");
            myViewHolder.tv_teambsocker.setText(sockInBean.getData().getList().get(i).getTeam_b_score()+"");
            myViewHolder.ll_sockclick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                                ToastUtil.show(i+"ssssss");
                    clickitem=i;
                    initTimePopupWindow();
                    PopupWindow win=window.getPopupWindow();
                    win.setAnimationStyle(R.style.animAlpha);
                    window.showAtLocation(tv_title, Gravity.CENTER,50, ScreenUtil.dp2px(BclientSocketInPKActivity.this,60));
//            window.showAsDropDown(tv_twonextdaybell,  0, 50);
                    WindowManager.LayoutParams lp=getWindow().getAttributes();
                    lp.alpha=0.5f;
                    win.setFocusable(true);
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                    getWindow().setAttributes(lp);
                }
            });
            return view;
        }
    }
    public void ToSockIn(){
        if(TextUtils.isEmpty(et_sokinteamone.getText().toString().trim())){
            ToastUtil.show("比分不为空");
            return;
        }
        if(TextUtils.isEmpty(et_teamtwosokin.getText().toString().trim())){
            ToastUtil.show("比分不为空");
            return;
        }
        RestClient.getYfmNovate(this).post(Constant.API.YFM_TOSOCKINPK, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("activity_id",getIntent().getIntExtra("activityid",-1))
                .addParams("group_id",getIntent().getIntExtra("activityid",-1))
                .addParams("group_pk_match_id",sockInBean.getData().getList().get(clickitem).getId())
                .addParams("a_score",Integer.parseInt(et_sokinteamone.getText().toString().trim()))
                .addParams("b_score",Integer.parseInt(et_teamtwosokin.getText().toString().trim()))
                .build(), new DmeycBaseSubscriber<PublishActionAfterBean>() {
            @Override
            public void onSuccess(final PublishActionAfterBean bean) {

//ToastUtil.show(bean.getMsg());
                setData();
                sockInAdapter.notifyDataSetChanged();
            }});
    }

//    public void getnoPKrecord(){
//        RestClient.getYfmNovate(this).post(url, new ParamMap.Build()
//                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
//                .addParams("activity_id",getIntent().getIntExtra("activityid",-1))
//                .build(), new DmeycBaseSubscriber<SockInBean>() {
//            @Override
//            public void onSuccess(final SockInBean bean) {
////            ToastUtil.show(bean.getMsg());
//                if(bean.getData().getList().size()==0){
//                    tv_nodata.setVisibility(View.VISIBLE);
//                    recycleview_pking.setVisibility(View.GONE);
//                }else {
//                    tv_nodata.setVisibility(View.GONE);
//                    recycleview_pking.setVisibility(View.VISIBLE);
//                    sockInBean=bean;
//                    sockInAdapter =new SockInAdapter();
//                    recycleview_pking.setAdapter(sockInAdapter);
//                }
//            }});
//    }

    public void getpkrecord(){
        RestClient.getYfmNovate(this).post(Constant.API.YFM_GETRECORD, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("activity_id",getIntent().getIntExtra("activityid",-1))
                .build(), new DmeycBaseSubscriber<SockInBean>() {
            @Override
            public void onSuccess(final SockInBean bean) {
//            ToastUtil.show(bean.getMsg());
                if(bean.getData().getList().size()==0){
                    tv_nodata.setVisibility(View.VISIBLE);
                    recycleview_pking.setVisibility(View.GONE);
                }else {
                    tv_nodata.setVisibility(View.GONE);
                    recycleview_pking.setVisibility(View.VISIBLE);
                    sockInBean=bean;
                    sockInAdapter =new SockInAdapter();
                    recycleview_pking.setAdapter(sockInAdapter);
                }
            }});
    }
    public void getbigpkrecord(){
        RestClient.getYfmNovate(this).post(Constant.API.YFM_GETBIGRECORD, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("activity_id",getIntent().getIntExtra("grovpkid",-1))
                .build(), new DmeycBaseSubscriber<SockInBean>() {
            @Override
            public void onSuccess(final SockInBean bean) {
//            ToastUtil.show(bean.getMsg());
                if(bean.getData().getList().size()==0){
                    tv_nodata.setVisibility(View.VISIBLE);
                    recycleview_pking.setVisibility(View.GONE);
                }else {
                    tv_nodata.setVisibility(View.GONE);
                    recycleview_pking.setVisibility(View.VISIBLE);
                    sockInBean=bean;
                    sockInAdapter =new SockInAdapter();
                    recycleview_pking.setAdapter(sockInAdapter);
                }
            }});
    }
}
