package com.dmeyc.dmestoreyfm.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.RouleBean;
import com.dmeyc.dmestoreyfm.bean.YFMUserBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import butterknife.Bind;

public class MyPromotionActivity extends BaseActivity {

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

//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        public MyViewHolder(View itemView) {
//            super(itemView);
//        }
//    }
    PromotionBean promotionBean;
    public void getData(){
        RestClient.getYfmNovate(this).get(Constant.API.YFM_GETPROMOTION, new ParamMap.Build()
//                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                        .addParams("user_token","8be5579032474cb395755161b85e9ffa")
                .build(), new DmeycBaseSubscriber<PromotionBean>() {
            @Override
            public void onSuccess(final PromotionBean bean) {
//                ToastUtil.show(bean.getMsg());
                promotionBean=bean;
                PromotionAdapter promotionAdapter = new PromotionAdapter();
                lv_mypronotion.setAdapter(promotionAdapter);
            }
        });
    }
   public class PromotionAdapter extends BaseAdapter{
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
       PromotionViewHolder promotionViewHolder;
       @Override
       public View getView(int i, View view, ViewGroup viewGroup) {
           if(view==null){
               view =getLayoutInflater().inflate(R.layout.adapter_promotionlist,null);
               promotionViewHolder=new PromotionViewHolder(view);
               view.setTag(promotionViewHolder);
           }else {
               promotionViewHolder=(PromotionViewHolder)view.getTag();
           }
       String rule= promotionBean.getData().get(i).getInviteRewardDTO().getRule();
           Gson gs=new Gson();
           RouleBean rouleBean=  gs.fromJson(rule,RouleBean.class);
           promotionViewHolder.tv_money.setText(rouleBean.getDiscountAmount()+"");
           promotionViewHolder.tv_endtime.setText(rouleBean.getEndDate());
           promotionViewHolder.tv_name.setText(rouleBean.getName());
           promotionViewHolder.tv_rule.setText((String)rouleBean.getUserRule());
           return view;
       }

       public class PromotionViewHolder{
           private TextView tv_money,tv_endtime,tv_name,tv_rule;
           public  PromotionViewHolder(View view){
               tv_money=(TextView) view.findViewById(R.id.tv_money);
               tv_endtime=(TextView) view.findViewById(R.id.tv_endtime);
               tv_name=(TextView) view.findViewById(R.id.tv_name);
               tv_rule=(TextView) view.findViewById(R.id.tv_rule);
           }
       }
   }
}
