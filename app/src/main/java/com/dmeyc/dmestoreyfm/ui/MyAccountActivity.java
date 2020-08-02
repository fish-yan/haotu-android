package com.dmeyc.dmestoreyfm.ui;

import android.animation.FloatEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.CommRecordListBean;
import com.dmeyc.dmestoreyfm.bean.HaseBandCardListBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;

import java.text.DecimalFormat;

public class MyAccountActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_hasmoney,tv_right_title_bar;
    private TextView tv_credit,tv_cr;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_myaccount;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        tv_hasmoney=(TextView) findViewById(R.id.tv_hasmoney);
        tv_right_title_bar=(TextView) findViewById(R.id.tv_right_title_bar);
        tv_credit=(TextView) findViewById(R.id.tv_credit);
        tv_credit.setOnClickListener(this);
        tv_cr=(TextView) findViewById(R.id.tv_cr);
        tv_cr.setOnClickListener(this);
        tv_right_title_bar.setOnClickListener(this);
        autoIncrement(tv_hasmoney,0,10000,3000);
    }

    public static void autoIncrement(final TextView target, final float start, final float end, long duration) {

        ValueAnimator animator = ValueAnimator.ofFloat(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            private FloatEvaluator evalutor = new FloatEvaluator();
            private DecimalFormat format = new DecimalFormat("####0.0#");

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                float currentValue = evalutor.evaluate(fraction, start, end);
                target.setText(format.format(currentValue));
            }
        });
        animator.setDuration(duration);
        animator.start();
    }

    @Override
    public void onClick(View v) {
        int viewid=v.getId();
        if(viewid==R.id.tv_right_title_bar){
            startActivity(new Intent(MyAccountActivity.this,DetailActivity.class));
        }else if(viewid==R.id.tv_credit){

            checkCard();
        }else if(viewid==R.id.tv_cr){
            startActivity(new Intent(MyAccountActivity.this,ReChargeActivity.class));
        }
    }

    public void checkCard(){

        RestClient.getYfmNovate(this).post(Constant.API.YFM_QUERYBANKLIST, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<HaseBandCardListBean>() {
            @Override
            public void onSuccess(HaseBandCardListBean bean) {
                if(bean.getData().size()==0){
                    StyledDialog.buildIosAlert(MyAccountActivity.this, "您还未绑定银行卡是否绑定", "",  new MyDialogListener() {
                        @Override
                        public void onFirst() {
                            startActivity(new Intent(MyAccountActivity.this, CreditCarActivity.class));
                        }

                        @Override
                        public void onSecond() {
                        }

                    }).setBtnText("是","否").show();
                }else {
                                startActivity(new Intent(MyAccountActivity.this,WithDrawActivity.class));
                }

            }
        });


    }
}
