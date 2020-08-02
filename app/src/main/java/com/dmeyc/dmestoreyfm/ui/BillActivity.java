package com.dmeyc.dmestoreyfm.ui;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.BillBean;
import com.dmeyc.dmestoreyfm.bean.SeatBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.AccountTimePicker;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.dmeyc.dmestoreyfm.wedgit.PickerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class BillActivity extends BaseActivity {
    @Bind(R.id.tv_date)
    TextView tv_date;
    @Bind(R.id.tv_moneyout)
    TextView tv_moneyout;
    @Bind(R.id.tv_moneyin)
    TextView tv_moneyin;
    @Bind(R.id.lv_account)
    ListView lv_account;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_bill;
    }
   String year,mone;
    Calendar  now;
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        now = Calendar.getInstance();
        tv_date.setText(now.get(Calendar.YEAR)+"年0"+(now.get(Calendar.MONTH)+1)+"月");
        year=  now.get(Calendar.YEAR)+"";
        mone=(now.get(Calendar.MONTH)+1)+"";

        if("client".equals(getIntent().getStringExtra("type"))){
            getData();
        }else {
        getBClient();
        }
    }
    @OnClick(R.id.tv_date)
    public void onClick(View view){
        int viewid=view.getId();
        if(viewid==R.id.tv_date){
            goShop();
        }
    }
    Dialog dialog;
    AccountTimePicker pv_year;
    AccountTimePicker pv_mone;
    TextView tv_outlog;
    String checkkyear="";
        String chexday="";
    public void goShop(){
        dialog  = new Dialog(BillActivity.this, R.style.MyDialog);
        //设置它的ContentView
        setFinishOnTouchOutside(false);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_timechick);
        Window window = dialog.getWindow();

        WindowManager.LayoutParams lp = window.getAttributes();
        window.setGravity(Gravity.CENTER);
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高
        WindowManager.LayoutParams params = window.getAttributes();
        params.height = (int) (d.getHeight() * 0.5); // 高度设置为屏幕的0.5
        params.width = (int) (d.getWidth() * 0.9); // 宽度设置为屏幕的0.6
        window.setAttributes(params);


        pv_year = (AccountTimePicker) dialog.findViewById(R.id.pv_year);
        pv_mone=(AccountTimePicker)dialog.findViewById(R.id.pv_mone);
        tv_outlog=dialog.findViewById(R.id.tv_outlog);
        List<String> data = new ArrayList<String>();
                List<String> mones = new ArrayList<String>();
        for (int i = 2019; i < 2021; i++)
        {
           data.add(i+"");
        }
        for (int i = 1; i < 13; i++)
        {
            mones.add(i +"");
        }
        pv_mone.setData(mones);
        pv_year.setData(data);
        pv_year.setOnSelectListener(new PickerView.onSelectListener()
        {

            @Override
            public void onSelect(String text)
            {
                year=text;
            }
        });
        pv_mone.setOnSelectListener(new PickerView.onSelectListener()
        {

            @Override
            public void onSelect(String text)
            {
                mone=text;
            }
        });
        dialog.show();
        tv_outlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_date.setText(year+"年"+mone+"月");
                dialog.dismiss();
                if("client".equals(getIntent().getStringExtra("type"))){
                    getData();
                }else {
                    getBClient();
                }
                billListAdapter.notifyDataSetChanged();
            }
        });
    }
    BillBean billBean;
    BillListAdapter billListAdapter;
    public void getData(){

        RestClient.getYfmNovate(this).post(Constant.API.YFM_ACCOUNTLIST, new ParamMap.Build()
                .addParams("user_token",SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("type", "0")
               .addParams("year_month", year+"-"+mone)
                .build(), new DmeycBaseSubscriber<BillBean>() {
            @Override
            public void onSuccess(final BillBean bean) {
                billBean=bean;
//                ToastUtil.show(bean.getMsg());
                tv_moneyout.setText("支出￥"+bean.getData().getPayed()+"");
                tv_moneyin.setText("收入￥"+bean.getData().getHarvest()+"");
                billListAdapter =new BillListAdapter();
                lv_account.setAdapter(billListAdapter);
            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_groupname,tv_time,tv_moneyaccount,tv_resulce;
        private CircleImageView me_ic_head;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_groupname=(TextView) itemView.findViewById(R.id.tv_groupname);
            tv_time=(TextView) itemView.findViewById(R.id.tv_time);
            tv_moneyaccount=itemView.findViewById(R.id.tv_moneyaccount);
//            tv_des=(TextView) itemView.findViewById(R.id.tv_des);
//            tv_teampower=(TextView) itemView.findViewById(R.id.tv_teampower);
//            tv_date=(TextView) itemView.findViewById(R.id.tv_date);
            me_ic_head=(CircleImageView) itemView.findViewById(R.id.me_ic_head);
            tv_resulce=(TextView) itemView.findViewById(R.id.tv_resulce);
        }
    }

    public  class  BillListAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return billBean.getData().getAcountDetailList().size();
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
                view =getLayoutInflater().inflate(R.layout.adapter_billitem,null);
                myViewHolder=new MyViewHolder(view);
                view.setTag(myViewHolder);
            }else {
                myViewHolder=(MyViewHolder) view.getTag();
            }

            myViewHolder.tv_groupname.setText(billBean.getData().getAcountDetailList().get(i).getGroup_name());
            myViewHolder.tv_time.setText(billBean.getData().getAcountDetailList().get(i).getCreate_time());

            if("1".equals(billBean.getData().getAcountDetailList().get(i).getType())){
                myViewHolder.tv_moneyaccount.setTextColor(getResources().getColor(R.color.red));
                myViewHolder.tv_moneyaccount.setText("+"+billBean.getData().getAcountDetailList().get(i).getAmount()+"");
            }else {
                myViewHolder.tv_moneyaccount.setTextColor(getResources().getColor(R.color.c22));
                myViewHolder.tv_moneyaccount.setText("-"+billBean.getData().getAcountDetailList().get(i).getAmount()+"");
            }
            if("4".equals(billBean.getData().getAcountDetailList().get(i).getSource())){
                myViewHolder.tv_resulce.setText("来源：会费支付");
            }else {
                myViewHolder.tv_resulce.setText("来源：微信支付");
               }


//                        myViewHolder.tv_ranknum.setText(beanList.get(i).getRank().substring(1,2));
//                        if(i<3){
//                            myViewHolder.tv_ranknum.setVisibility(View.VISIBLE);
//                        }else {
//                            myViewHolder.tv_ranknum.setVisibility(View.GONE);
//                        }
//                        myViewHolder.tv_won_rank.setText("胜率："+beanList.get(i).getSuccessRate());
//                        myViewHolder.tv_des.setText(beanList.get(i).getActivity_venue_address());
//                        myViewHolder.tv_date.setText(beanList.get(i).getGroup_name());
                        GlideUtil.loadImage(BillActivity.this,billBean.getData().getAcountDetailList().get(i).getLogo(),myViewHolder.me_ic_head);
            return view;
        }
    }

    public void getBClient(){

        RestClient.getYfmNovate(this).post(Constant.API.YFM_ACCOUNGROUPTLIST, new ParamMap.Build()
                .addParams("user_token",SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("type", "0")
                .addParams("year_month", year+"-"+mone)
                .build(), new DmeycBaseSubscriber<BillBean>() {
            @Override
            public void onSuccess(final BillBean bean) {
                billBean=bean;
//                ToastUtil.show(bean.getMsg());
                tv_moneyout.setText("支出￥"+bean.getData().getPayed()+"");
                tv_moneyin.setText("收入￥"+bean.getData().getHarvest()+"");
                billListAdapter =new BillListAdapter();
                lv_account.setAdapter(billListAdapter);
            }
        });


    }
}
