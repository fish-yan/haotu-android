package com.dmeyc.dmestoreyfm.ui;


import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.HaseBandCardListBean;
import com.dmeyc.dmestoreyfm.bean.YFMUserBean;
import com.dmeyc.dmestoreyfm.bean.YUEbean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;

import butterknife.Bind;
import butterknife.OnClick;

public class VacancyActivity extends BaseActivity {

    @Bind(R.id.btn_tocash)
    TextView Button;
    @Bind(R.id.tv_totalmoney)
    TextView tv_totalmoney;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_vacancy;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
//        if("client".equals(getIntent().getStringExtra("type"))){
//            Button.setVisibility(View.GONE);
//        }else {
//            Button.setVisibility(View.VISIBLE);
//            getData();
//        }

    }


    @Override
    protected void onResume() {
        super.onResume();

        if("client".equals(getIntent().getStringExtra("type"))){
            Button.setVisibility(View.GONE);
        }else {
            Button.setVisibility(View.VISIBLE);
            getData();
        }
    }

    @OnClick({R.id.tv_right_title_bar,R.id.btn_tocash})
    public void onClick(View view){
        int viewid=view.getId();
        if(viewid==R.id.tv_right_title_bar){
            startActivity(new Intent(VacancyActivity.this,BillActivity.class).putExtra("type",getIntent().getStringExtra("type")));
        }else if(R.id.btn_tocash==viewid){
            checkCard();
        }
    }
    YUEbean yuEbean;
    public void getData(){


        RestClient.getYfmNovate(VacancyActivity.this).get(Constant.API.YFM_YUE, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<YUEbean>() {
            @Override
            public void onSuccess(YUEbean bean) {
                yuEbean=bean;
//                ToastUtil.show(bean.getMsg());
                tv_totalmoney.setText(bean.getData().getAvaliableAmount()+"");
            }
        });
    }



    public void checkCard(){
        RestClient.getYfmNovate(this).post(Constant.API.YFM_QUERYBANKLIST, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<HaseBandCardListBean>() {
            @Override
            public void onSuccess(HaseBandCardListBean bean) {
                if(bean.getData().size()==0){
                    StyledDialog.buildIosAlert(VacancyActivity.this, "您还未绑定银行卡是否绑定", "",  new MyDialogListener() {
                        @Override
                        public void onFirst() {
                            startActivity(new Intent(VacancyActivity.this, CreditCarActivity.class));
                        }
                        @Override
                        public void onSecond() {
                        }
                    }).setBtnText("是","否").show();
                }else {
                    startActivity(new Intent(VacancyActivity.this,WithDrawActivity.class)
                            .putExtra("totlalamount",yuEbean.getData().getTotalAmount()).
                                    putExtra("avaliablamount",yuEbean.getData().getAvaliableAmount()).putExtra("fizilable",yuEbean.getData().getFrozenAmount()));
                }
            }
        });
    }




}
