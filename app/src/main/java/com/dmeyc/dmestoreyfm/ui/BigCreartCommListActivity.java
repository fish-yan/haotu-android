package com.dmeyc.dmestoreyfm.ui;

import android.app.Dialog;
import android.content.Intent;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.DataEmpteBean;
import com.dmeyc.dmestoreyfm.bean.MyCommListBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.tamic.novate.Throwable;

import butterknife.Bind;
import butterknife.OnClick;
import io.rong.imkit.RongIM;

public class BigCreartCommListActivity extends BaseActivity {
    @Bind(R.id.lv_createcommlist)
    ListView lv_createcommlist;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_bigcreatcommlist;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }
    @Override
    protected void initData() {

    }
    @OnClick(R.id.tv_right_title_bar)
    public void onClick(View view){
        int ivewid=view.getId();
        if(R.id.tv_right_title_bar==ivewid){
//            startActivity(new Intent(MyCommActivity.this,CommCheckActivity.class));
        }
    }
    MyCommListBean dataBean;
    private void getData() {

        RestClient.getYfmNovate(this).post(Constant.API.YFM_MYCOMMlist, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("status", "1")
                .build(), new DmeycBaseSubscriber<MyCommListBean>() {
            @Override
            public void onSuccess(final MyCommListBean bean) {
                dataBean=bean;
             MyCOmmAdapter myCOmmAdapter=new MyCOmmAdapter();
                lv_createcommlist.setAdapter(myCOmmAdapter);
            }
            @Override
            public void onError(Throwable e) {

            }
        });
        lv_createcommlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ToastUtil.show("wpo"+i);
                toming(dataBean.getData().get(i).getGroup_id(),dataBean.getData().get(i).getGroup_name());
//                startActivity(new Intent(BigCreartCommListActivity.this,MyCommEditeActivity.class).putExtra("groupid",dataBean.getData().get(i).getGroup_id()));
            }
        });
    }


    public class MyCOmmAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return dataBean.getData().size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }
   MyCOmmAdapter.CommViewHorler commViewHorler;
        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            if(view==null){
                view=getLayoutInflater().inflate(R.layout.adapter_mycommlist,null);
                commViewHorler=new MyCOmmAdapter.CommViewHorler(view);
                view.setTag(commViewHorler);
            }else {
                commViewHorler=(MyCOmmAdapter.CommViewHorler) view.getTag();
            }
            GlideUtil.loadImage(BigCreartCommListActivity.this,dataBean.getData().get(i).getGroup_logo(),commViewHorler.cv_commhead);
            commViewHorler.tv_name.setText(dataBean.getData().get(i).getGroup_name());

            if(dataBean.getData().get(i).getGroup_type().equals("1")){
                commViewHorler.iv_grouptype.setVisibility(View.GONE);
            }else if(dataBean.getData().get(i).getGroup_type().equals("3")){
                commViewHorler.iv_grouptype.setVisibility(View.VISIBLE);
                commViewHorler.iv_grouptype.setBackground(getResources().getDrawable(R.drawable.teach_type));
            }else if(dataBean.getData().get(i).getGroup_type().equals("4")){
                commViewHorler.iv_grouptype.setVisibility(View.VISIBLE);
                commViewHorler.iv_grouptype.setBackground(getResources().getDrawable(R.drawable.instuce_type));
            }else if(dataBean.getData().get(i).getGroup_type().equals("5")){
                commViewHorler.iv_grouptype.setVisibility(View.VISIBLE);
                commViewHorler.iv_grouptype.setBackground(getResources().getDrawable(R.drawable.buess_type));
            }
            commViewHorler.cv_commhead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(BigCreartCommListActivity.this, ChartInforActivity.class).putExtra("group_id",(int)dataBean.getData().get(i).getGroup_id()));
                }
            });
            return view;
        }
        public class CommViewHorler{
            CircleImageView cv_commhead;
            ImageView iv_grouptype;
            TextView tv_name;
            public CommViewHorler(View view){
                iv_grouptype=view.findViewById(R.id.iv_grouptype);
                cv_commhead=(CircleImageView) view.findViewById(R.id.cv_commhead);
                tv_name=(TextView) view.findViewById(R.id.tv_name);
            }
        }
    }

    public void toming(final int i, final String groupname){


        RestClient.getYfmNovate(this).post(Constant.API.YFM_COMMHOSTBAOMING, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                        .addParams("groupId", i)
                        .addParams("activityId", getIntent().getIntExtra("activityid",-1))

                .build(), new DmeycBaseSubscriber<DataEmpteBean>() {
            @Override
            public void onSuccess(DataEmpteBean bean) {

       goShop(i,groupname);
            }
        });

    }


    Dialog dialog;
    ListView lv_shop;
    TextView alltv_price;
    TextView tv_toinfo, tv_changeident,tv_notcont;

    public void goShop(final int i, final String groupname) {
        dialog = new Dialog(BigCreartCommListActivity.this, R.style.MyDialog);
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
        tv_notcont=dialog.findViewById(R.id.tv_notcont);
        tv_notcont.setText("您已报名，等待审核!\n快去群里呼唤大家报名吧");
//        lv_shop = dialog.findViewById(R.id.lv_shop);
        tv_changeident = dialog.findViewById(R.id.tv_changeident);
        tv_toinfo.setText("我的群");
        tv_changeident.setText("关闭");
        dialog.show();
        tv_toinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                RongIM.getInstance().startGroupChat(BigCreartCommListActivity.this, i+"", groupname+"");

//                startActivity(new Intent(BigCreartCommListActivity.this, BigCreartCommListActivity.class).putExtra("groupkid", 1));

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
}
