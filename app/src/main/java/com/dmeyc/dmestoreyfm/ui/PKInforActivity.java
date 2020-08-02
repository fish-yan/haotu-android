package com.dmeyc.dmestoreyfm.ui;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;

import com.dmeyc.dmestoreyfm.adapter.PKItemAdapter;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.WareActivityBean;
import com.dmeyc.dmestoreyfm.bean.common.ReviewsBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.utils.Util;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class PKInforActivity  extends BaseActivity {

    @Bind(R.id.tv_actionmumber)
    EditText tv_actionmumber;
    @Bind(R.id.et_cubname)
    EditText et_cubname;
    @Bind(R.id.et_activitylong)
    EditText et_activitylong;
    @Bind(R.id.tv_selectcity)
    TextView tv_selectcity;
    @Bind(R.id.et_actiondetail)
    EditText et_actiondetail;
    @Bind(R.id.tv_time)
    TextView tv_time;


    @Bind(R.id.cb_womanhas)
    CheckBox cb_womanhas;
    @Bind(R.id.ll_wo_procation)
    LinearLayout ll_wo_procation;
    @Bind(R.id.et_product)
    EditText et_product;
    @Bind(R.id.et_vipmoney)
    EditText et_vipmoney;
    @Bind(R.id.et_toursepeice)
    EditText et_toursepeice;
    @Bind(R.id.et_waitcount)
    EditText et_waitcount;
    @Bind(R.id.et_phonemum)
    EditText et_phonemum;
    @Bind(R.id.et_backtext)
    EditText et_backtext;

    @Bind(R.id.tv_textnum)
    TextView tv_textnum;
    int iswoman;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_pkinfor;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }


    @Override
    protected void initData() {
        initAction();

        cb_womanhas.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    ll_wo_procation.setVisibility(View.VISIBLE);
                }else {
                    ll_wo_procation.setVisibility(View.GONE);
                }
            }
        });

        et_backtext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tv_textnum.setText(et_backtext.getText().toString().length()+"/"+200);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void initAction() {
        RestClient.getYfmNovate(this).post(Constant.API.YFM_INITACION, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("activity_id",getIntent().getIntExtra("activityid",-1))
                .build(), new DmeycBaseSubscriber<WareActivityBean>() {
            @Override
            public void onSuccess(WareActivityBean bean) {
//                ToastUtil.show(bean.getMsg());
                tv_time.setText(bean.getData().getStart_date());
                et_actiondetail.setText(bean.getData().getActivity_address());
                if(bean.getData().getProvince().equals(bean.getData().getCity())){
                    tv_selectcity.setText(bean.getData().getProvince()+" "+bean.getData().getArea());
                }else {
                    tv_selectcity.setText(bean.getData().getProvince()+" "+bean.getData().getCity()+" "+bean.getData().getArea());
                }
                et_activitylong.setText(bean.getData().getDuration()+"");
                et_cubname.setText(bean.getData().getActivity_name());
                tv_actionmumber.setText(bean.getData().getTotal_no()+"");
            }
        });

    }
@OnClick(R.id.btn_upload)
    public void onClick(View view){
        int viewid=view.getId();
        if(R.id.btn_upload==viewid){
            submit();
        }
  }


  public void submit(){

        if(TextUtils.isEmpty(et_phonemum.getText().toString().trim())){
            ToastUtil.show("请输入手机号");
            return;
        }
      if(TextUtils.isEmpty(et_backtext.getText().toString().trim())){
          ToastUtil.show("请输入简介");
          return;
      }
      if(TextUtils.isEmpty(et_waitcount.getText().toString().trim())){
          ToastUtil.show("请输入候补人数");
          return;
      }
      if(TextUtils.isEmpty(et_toursepeice.getText().toString().trim())){
          ToastUtil.show("请输入游客价格");
          return;
      }

      if(TextUtils.isEmpty(et_vipmoney.getText().toString().trim())){
          ToastUtil.show("请输入会员价格");
          return;
      }
      if(TextUtils.isEmpty(et_waitcount.getText().toString().trim())){
          ToastUtil.show("请输入候补人数");
          return;
      }
      if(TextUtils.isEmpty(et_toursepeice.getText().toString().trim())){
          ToastUtil.show("请输入游客价格");
          return;
      }
      if(cb_womanhas.isChecked()){
          iswoman=1;
      }else {
          iswoman=0;
      }
       ParamMap.Build pb=  new ParamMap.Build();
      if(iswoman==1){
          if(TextUtils.isEmpty(et_product.getText().toString().trim())){
              ToastUtil.show("请输入优惠金额");
              return;
          }
          pb.addParams("w_discount_amount",et_product.getText().toString().trim());
      }
      RestClient.getYfmNovate(this).post(Constant.API.YFM_PKINFO,pb
              .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
              .addParams("activity_phone_no",et_phonemum.getText().toString().trim())
              .addParams("group_id",getIntent().getIntExtra("groupidpk",-1))
              .addParams("replace_no",et_waitcount.getText().toString().trim())
              .addParams("is_w_discount",iswoman+"")
              .addParams("m_member_amount",getIntent().getIntExtra("activityid",-1))
              .addParams("m_visitor_amount",et_vipmoney.getText().toString().trim())
              .addParams("remark",et_backtext.getText().toString().trim())
              .addParams("group_pk_id",getIntent().getIntExtra("gorupid",-1))
//              .addParams("group_pk_id",getIntent().getIntExtra("activityid",-1))
//              .addParams("w_discount_amount",getIntent().getIntExtra("activityid",-1))
              .build(), new DmeycBaseSubscriber<WareActivityBean>() {
          @Override
          public void onSuccess(WareActivityBean bean) {
              ToastUtil.show(bean.getMsg());
              finish();
          }
      });

  }
}