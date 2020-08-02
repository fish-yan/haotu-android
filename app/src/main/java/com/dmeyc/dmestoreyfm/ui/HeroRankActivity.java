package com.dmeyc.dmestoreyfm.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.HeroListBean;
import com.dmeyc.dmestoreyfm.bean.HeroRankBean;
import com.dmeyc.dmestoreyfm.bean.SpecialBean;
import com.dmeyc.dmestoreyfm.bean.YFMUserBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.BlurPopWin;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class HeroRankActivity extends BaseActivity  implements OnRefreshListener, OnLoadmoreListener {

    int flag = 1;
    HeroAdapter heroAdapter;
    @Bind(R.id.lv_pkinglist)
    ListView recycleview_pking;
    @Bind(R.id.smartRl)
    SmartRefreshLayout smartRl;
    @Bind(R.id.iv_right_title_bar)
    ImageView iv_right_title_bar;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_herorank;
    }
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
//        LinearLayoutManager lm=new LinearLayoutManager(PkingActivity.this);
//        lm.setOrientation(LinearLayoutManager.VERTICAL);
//        PkListAdapter pkListAdapter=new PkListAdapter();
//        setData();
        recycleview_pking.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(HeroRankActivity.this,HeroDetailActivity.class)
                        .putExtra("groupid",beanList.get(i).getGroupId()).putExtra("gorupname",beanList.get(i).getGroup_name())
                        .putExtra("groupicon",beanList.get(i).getImg_url()));
            }
        });
        smartRl.setOnRefreshListener(this);
        smartRl.setOnLoadmoreListener(this);
        heroAdapter  =new HeroAdapter();
        recycleview_pking.setAdapter(heroAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setData();
    }
    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        flag++;
        setData();
        closeRefresh();
//        initSection();
    }
    /**
     * 关闭刷新
     */
    public void closeRefresh() {
        if (smartRl.isRefreshing())
            smartRl.finishRefresh();
        if (smartRl.isLoading())
            smartRl.finishLoadmore();
    }
    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        flag = 1;
        setData();
        closeRefresh();
    }
    @Override
    public void onPause() {
        super.onPause();
        flag = 1;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_ranknum,tv_won_rank,tv_des,tv_teampower,tv_date;
        private CircleImageView iv_teamone;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_ranknum=(TextView) itemView.findViewById(R.id.tv_ranknum);
            tv_won_rank=(TextView) itemView.findViewById(R.id.tv_won_rank);
            tv_des=(TextView) itemView.findViewById(R.id.tv_des);
            tv_teampower=(TextView) itemView.findViewById(R.id.tv_teampower);
            tv_date=(TextView) itemView.findViewById(R.id.tv_date);
            iv_teamone=(CircleImageView) itemView.findViewById(R.id.iv_teamone);
        }
    }
    HeroRankBean heroRankBean;
//    private List<HeroListBean> beanList = new ArrayList<>();
private List<HeroRankBean.DataBean> beanList = new ArrayList<>();

    public void setData(){

        if (flag == 1) {
            beanList.clear();
        }
        RestClient.getYfmNovate(this).post(Constant.API.YFM_HERORANK, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                        .addParams("pageSize",10)
                        .addParams("pageIndex",flag)
                .addParams("longitude", SPUtils.getStringData(Constant.Config.LONGTUDE))
                .addParams("latitude", SPUtils.getStringData(Constant.Config.LATITUDE))
                .build(), new DmeycBaseSubscriber<HeroRankBean>() {
            @Override
            public void onSuccess(final HeroRankBean bean) {
//                ToastUtil.show(bean.getMsg());
                for (int i=0;i<bean.getData().size();i++){
                    beanList.add(bean.getData().get(i));
                    }
                heroRankBean=bean;
                heroAdapter.notifyDataSetChanged();
//                HeroAdapter heroAdapter=new HeroAdapter();
//                recycleview_pking.setAdapter(heroAdapter);
            }
        });
    }

    public class HeroAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return beanList.size();
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
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view==null){
                view =getLayoutInflater().inflate(R.layout.adapter_heroitem,null);
                myViewHolder=new MyViewHolder(view);
                view.setTag(myViewHolder);
            }else {
                myViewHolder=(MyViewHolder) view.getTag();
            }
            myViewHolder.tv_teampower.setText(beanList.get(i).getBattle_number()+"");
//            myViewHolder.tv_ranknum.setText(beanList.get(i).getRank().substring(1,2));
            myViewHolder.tv_ranknum.setText(i+1+"");
            if(i<50){
                myViewHolder.tv_ranknum.setVisibility(View.VISIBLE);
            }else {
                myViewHolder.tv_ranknum.setVisibility(View.GONE);
            }
            myViewHolder.tv_won_rank.setText("胜率："+beanList.get(i).getSuccessRate());
            myViewHolder.tv_des.setText(beanList.get(i).getActivity_venue_address());
            myViewHolder.tv_date.setText(beanList.get(i).getGroup_name());
            GlideUtil.loadImage(HeroRankActivity.this,beanList.get(i).getImg_url(),myViewHolder.iv_teamone);
            return view;
    }
        }
    @OnClick(R.id.iv_right_title_bar)
    public void onClick(View view){
        int viewid=view.getId();
        if(viewid==R.id.iv_right_title_bar){
            goShop();
        }
    }


    //此方法，如果显示则隐藏，如果隐藏则显示
    private void hintKbOne() {
        InputMethodManager imm = (InputMethodManager) HeroRankActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
// 得到InputMethodManager的实例
        if (imm.isActive()) {
            // 如果开启
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    Dialog dialog;
    ListView lv_shop;
    TextView alltv_price;
    TextView tv_toinfo,tv_submit,tv_notcont;
    EditText et_rank;
    public void goShop(){
        dialog  = new Dialog(HeroRankActivity.this, R.style.MyDialog);
        //设置它的ContentView
        setFinishOnTouchOutside(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_sharerank);
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
        tv_submit=dialog.findViewById(R.id.tv_submit);
        et_rank=dialog.findViewById(R.id.et_rank);
        tv_notcont=dialog.findViewById(R.id.tv_notcont);
        tv_notcont.setText("请输入分享名次1-"+(beanList.size()));
//        lv_shop = dialog.findViewById(R.id.lv_shop);
//        alltv_price=dialog.findViewById(R.id.alltv_price);
        dialog.show();
        tv_toinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
//                startActivity(new Intent(HeroRankActivity.this,TrueNameActivity.class));
            }
        });
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(et_rank.getText().toString())){
                    ToastUtil.show("请输入分享的名次1-"+(beanList.size()));
                    return;
                }
                if(Integer.parseInt(et_rank.getText().toString())<1||Integer.parseInt(et_rank.getText().toString())>(beanList.size())){
                    ToastUtil.show("请输入分享的名次1-"+(beanList.size()));
                    return;
                }
                dialog.dismiss();
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//                hintKbOne();
                new BlurPopWin.Builder(HeroRankActivity.this).setContent(beanList.get((Integer.parseInt(et_rank.getText().toString().trim())-1)).getGroup_name())
                        //Radius越大耗时越长,被图片处理图像越模糊
                        .setRadius(3).setTitle("胜率"+beanList.get((Integer.parseInt(et_rank.getText().toString().trim())-1)).getSuccessRate())
                        .setUrl(Constant.API.YFM_SHAREBASE_URL+Constant.API.SHARE_HERORANK+"&rankSize="+(Integer.parseInt(et_rank.getText().toString().trim())))
                        //设置居中还是底部显示
                        .setshowAtLocationType(1)
                        .setShowCloseButton(true)
                        .setOutSideClickable(false)
                        /*.onClick(new BlurPopWin.PopupCallback() {
                            @Override
                            public void onClick(@NonNull BlurPopWin blurPopWin) {
                                Toast.makeText(MainActivity.this, "中间被点了", Toast.LENGTH_SHORT).show();
                                blurPopWin.dismiss();
                            }
                        })*/.show(iv_right_title_bar);
            }
        });
    }
}
