package com.dmeyc.dmestoreyfm.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.codbking.widgets.DatePickDialog;
import com.codbking.widgets.OnSureLisener;
import com.codbking.widgets.bean.DateType;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.AddPromotionBean;
import com.dmeyc.dmestoreyfm.bean.SockInBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.OnClick;

public class AddPromotionActivity extends BaseActivity {
    @Bind(R.id.et_money)
    EditText et_money;
    @Bind(R.id.tv_selecttime)
    TextView tv_selecttime;
    @Bind(R.id.et_rule)
    EditText et_rule;


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_addpromotion;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }
    String json;
    public void AddData(){
        if(TextUtils.isEmpty(et_money.getText().toString().trim())){
            ToastUtil.show("金额不为空");
            return;
        }
        if(TextUtils.isEmpty(et_rule.getText().toString().trim())){
            ToastUtil.show("请输入使用规则");
            return;
        }

        AddPromotionBean addPromotionBean=new AddPromotionBean();
        addPromotionBean.setEndDate(tv_selecttime.getText().toString().trim()+":00");
        addPromotionBean.setDiscountAmount(Double.parseDouble(et_money.getText().toString().trim()));
        addPromotionBean.setUseRule(et_rule.getText().toString().trim());
        Gson gson=new Gson();
        json= gson.toJson(addPromotionBean);
        RestClient.getYfmNovate(this).post(Constant.API.YFM_ADDPROMOTION, new ParamMap.Build()
                      .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                      .addParams("groupId",getIntent().getIntExtra("groupid",-1))
                      .addParams("type",1)
                      .addParams("rule",json)
                      .build(), new DmeycBaseSubscriber<SockInBean>() {
                        @Override
                        public void onSuccess(final SockInBean bean) {
                      ToastUtil.show(bean.getMsg());
                      finish();
                        }
        });
    }
    @OnClick({R.id.tv_selecttime,R.id.btn_upload})
    public void onClick(View view){
        int viewid=view.getId();
        if(viewid==R.id.tv_selecttime){
            showDatePickDialog(DateType.TYPE_ALL);
        }else if(R.id.btn_upload==viewid){
            AddData();
        }
    }
    private void showDatePickDialog(DateType type) {
        final DatePickDialog dialog = new DatePickDialog(this);
        //设置上下年分限制
        dialog.setYearLimt(5);
        //设置标题
        dialog.setTitle("选择时间");
        //设置类型
        dialog.setType(type);
        //设置消息体的显示格式，日期格式
        dialog.setMessageFormat("yyyy-MM-dd HH:mm");
        //设置选择回调
        dialog.setOnChangeLisener(null);
        //设置点击确定按钮回调
        dialog.setOnSureLisener(new OnSureLisener() {
            @Override
            public void onSure(Date date) {
                tv_selecttime.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date));
//                Toast.makeText(PublishActivity.this,new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date),Toast.LENGTH_LONG).show();
            }
        });
        dialog.show();
    }
}
