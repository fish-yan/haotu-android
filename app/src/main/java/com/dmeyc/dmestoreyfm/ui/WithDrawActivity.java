package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.BankDataBean;
import com.dmeyc.dmestoreyfm.bean.CommDetailBean;
import com.dmeyc.dmestoreyfm.bean.YUEbean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.DataClass;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;

public class WithDrawActivity extends BaseActivity {

    @Bind(R.id.ll_bankcardlist)
    LinearLayout ll_bankcardlist;
    @Bind(R.id.tv_bankname)
    TextView tv_bankname;
    @Bind(R.id.tv_banknumber)
    TextView tv_banknumber;
    @Bind(R.id.iv_bank_icon)
    ImageView iv_bank_icon;
    @Bind(R.id.tv_available)
    TextView tv_available;
    @Bind(R.id.tv_fiziable)
    TextView tv_fiziable;
    @Bind(R.id.tv_totalaccount)
    TextView tv_totalaccount;

    @Bind(R.id.et_amount)
    EditText et_amount;

    int  bandcardid=-1;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_withdraw;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        tv_available.setText("可提现金额："+getIntent().getDoubleExtra("avaliablamount",-1));
//        tv_fiziable.setText("冻结金额："+getIntent().getDoubleExtra("fizilable",-1));
//        tv_totalaccount.setText("总金额："+getIntent().getDoubleExtra("totlalamount",-1));
        getData();
    }
    public void getData(){
        RestClient.getYfmNovate(this).post(Constant.API.YFM_CARELIST, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<BankDataBean>() {
            @Override
            public void onSuccess(BankDataBean bean) {
//                ToastUtil.show(bean.getMsg());
                if(bean.getData().size()>0){
                    tv_bankname.setText(bean.getData().get(0).getBank_name());
                    tv_banknumber.setText(bean.getData().get(0).getBank_account());
                    GlideUtil.loadImage(WithDrawActivity.this,bean.getData().get(0).getBank_logo(),iv_bank_icon);
                    bandcardid=bean.getData().get(0).getId();
                }else {
                    tv_bankname.setText("请选择提现银行卡");
                    tv_banknumber.setVisibility(View.GONE);
                    iv_bank_icon.setVisibility(View.GONE);
                }

            }
        });
    }
@OnClick({R.id.ll_bankcardlist,R.id.tv_tixian})
    public void onClick(View view){
        int viewid=view.getId();
        if(R.id.ll_bankcardlist==viewid){
            startActivityForResult(new Intent(this, CreditCarActivity.class).putExtra("type","tixian"),111);
          }else if(R.id.tv_tixian==viewid){
            tixian();
        }
      }
      public void tixian(){
          if(TextUtils.isEmpty(et_amount.getText().toString().trim())){
              ToastUtil.show("提现金额不为空");
              return;
          }
          if(-1==bandcardid){
              ToastUtil.show("请选择提现银行卡");
              return;
          }
          if(Double.parseDouble(et_amount.getText().toString().trim())>getIntent().getDoubleExtra("avaliablamount",-1)){
              ToastUtil.show("提现金额能大于可用金额");
              return;
          }
          RestClient.getYfmNovate(WithDrawActivity.this).post(Constant.API.YFM_TIXIAN, new ParamMap.Build()
                  .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                  .addParams("bankCradId", bandcardid)
                  .addParams("amount", et_amount.getText().toString().trim())
                  .build(), new DmeycBaseSubscriber<YUEbean>() {
              @Override
              public void onSuccess(YUEbean bean) {
                ToastUtil.show("已提交申请，请耐心等待");
                finish();
              }
          });
      }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if(222==resultCode){
//            intent.putExtra("cardname,",bankListBean.getData().get(i).getBank_name());
//            intent.putExtra("cardid,",bankListBean.getData().get(i).getId());
//            intent.putExtra("cardlogo,",bankListBean.getData().get(i).getBank_logo());
//            intent.putExtra("cardnumber,",bankListBean.getData().get(i).getBank_account());

            bandcardid=DataClass.bankid;
            tv_bankname.setText(DataClass.bankname);
            tv_banknumber.setText(DataClass.bankcardnumber);
            GlideUtil.loadImage(WithDrawActivity.this,DataClass.banklogo,iv_bank_icon);
//            bandcardid=data.getIntExtra("cardid",-1);
//            tv_bankname.setText(data.getStringExtra("cardname"));
//            tv_banknumber.setText(data.getStringExtra("cardnumber"));
//            GlideUtil.loadImage(WithDrawActivity.this,data.getStringExtra("cardlogo"),iv_bank_icon);
        }
    }
}
