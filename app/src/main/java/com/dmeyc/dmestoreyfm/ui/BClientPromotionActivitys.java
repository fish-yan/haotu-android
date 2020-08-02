package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.RouleBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.OnClick;

public class BClientPromotionActivitys extends BaseActivity {

    @Bind(R.id.lv_mypronotion)
    ListView lv_mypronotion;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_mypromotion;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        getData();
    }
    PromotionBean promotionBean;
    public void getData(){
//        RestClient.getYfmNovate(this).get(Constant.API.YFM_GETBCPROMOTION+"/"+getIntent().getIntExtra("groupid",-1), new ParamMap.Build()
        RestClient.getYfmNovate(this).get(Constant.API.YFM_GETBCPROMOTION+"/"+2, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
////                .addParams("user_token","8be5579032474cb395755161b85e9ffa")
//                .addParams("groupId ",getIntent().getIntExtra("groupid",-1))
                .build(), new DmeycBaseSubscriber<PromotionBean>() {
            @Override
            public void onSuccess(final PromotionBean bean) {
//                ToastUtil.show(bean.getMsg());
                promotionBean=bean;
                BClientPromotionActivitys.PromotionAdapter promotionAdapter = new BClientPromotionActivitys.PromotionAdapter();
                lv_mypronotion.setAdapter(promotionAdapter);
            }
        });
    }
    public class PromotionAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return promotionBean.getData().size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }
        BClientPromotionActivitys.PromotionAdapter.PromotionViewHolder promotionViewHolder;
        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            if(view==null){
                view =getLayoutInflater().inflate(R.layout.adapter_promotionlist,null);
                promotionViewHolder=new BClientPromotionActivitys.PromotionAdapter.PromotionViewHolder(view);
                view.setTag(promotionViewHolder);
            }else {
                promotionViewHolder=(BClientPromotionActivitys.PromotionAdapter.PromotionViewHolder)view.getTag();
            }
            String rule= promotionBean.getData().get(i).getRule();
            Gson gs=new Gson();
            final RouleBean rouleBean=  gs.fromJson(rule,RouleBean.class);
            promotionViewHolder.tv_money.setText(rouleBean.getDiscountAmount()+"");
            promotionViewHolder.tv_endtime.setText(rouleBean.getEndDate());
            promotionViewHolder.tv_name.setText(rouleBean.getName());
            promotionViewHolder.tv_rule.setText((String)rouleBean.getUserRule());
            promotionViewHolder.ll_adaperitem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent();
                    intent.putExtra("promotionname",rouleBean.getName());
                    intent.putExtra("promotid",promotionBean.getData().get(i).getId());
                    setResult(321,intent);
                    finish();
                }
            });
            return view;
        }

        public class PromotionViewHolder{
            private TextView tv_money,tv_endtime,tv_name,tv_rule;
            private LinearLayout ll_adaperitem;
            public  PromotionViewHolder(View view){
                tv_money=(TextView) view.findViewById(R.id.tv_money);
                tv_endtime=(TextView) view.findViewById(R.id.tv_endtime);
                tv_name=(TextView) view.findViewById(R.id.tv_name);
                tv_rule=(TextView) view.findViewById(R.id.tv_rule);
                ll_adaperitem=(LinearLayout)view.findViewById(R.id.ll_adaperitem);
            }
        }
    }
    @OnClick(R.id.iv_right_title_bar)
    public void OnClick(View view){
        int viewid=view.getId();
        if(R.id.iv_right_title_bar==viewid){
            startActivity(new Intent(this,AddPromotionActivity.class).putExtra("groupid",getIntent().getIntExtra("groupid",-1)));
        }

    }
}
