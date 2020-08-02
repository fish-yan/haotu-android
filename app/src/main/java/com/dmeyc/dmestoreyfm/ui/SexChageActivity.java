package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.IsTrueNameBean;
import com.dmeyc.dmestoreyfm.bean.PublishActionAfterBean;
import com.dmeyc.dmestoreyfm.bean.TrueNameBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.tamic.novate.Throwable;

import butterknife.Bind;
import butterknife.OnClick;

public class SexChageActivity extends BaseActivity {

    @Bind(R.id.iv_chanman)
    ImageView iv_chanman;
    @Bind(R.id.iv_chanwoman)
    ImageView iv_chanwoman;
    String sexcheck="1";
    @Override
    protected int getLayoutRes() {
        return R.layout.activtiy_sexchange;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        if("1".equals(getIntent().getStringExtra("sex"))){
            iv_chanman.setVisibility(View.VISIBLE);
            iv_chanwoman.setVisibility(View.GONE);
        }else {
            iv_chanman.setVisibility(View.GONE);
            iv_chanwoman.setVisibility(View.VISIBLE);
        }
    }
    @OnClick({R.id.ll_chageman,R.id.ll_changewoman,R.id.btn_regist})
    public void onClick(View view){
        int viewid=view.getId();
        if(viewid==R.id.ll_chageman){
            iv_chanman.setVisibility(View.VISIBLE);
            iv_chanwoman.setVisibility(View.GONE);
            sexcheck="1";
        }else if(viewid==R.id.ll_changewoman){
            iv_chanman.setVisibility(View.GONE);
            iv_chanwoman.setVisibility(View.VISIBLE);
            sexcheck="2";
        }else if(R.id.btn_regist==viewid){
            if("teachin".equals(getIntent().getStringExtra("type"))){
                Intent intent=new Intent();
                intent.putExtra("sex",sexcheck);
                setResult(999,intent);
                finish();
            }else {
                checkIsTrueName();
            }

//            Intent intent=new Intent();
//            intent.putExtra("sex",sexcheck);
//            setResult(999,intent);
//            finish();

//            changeSex();
        }
    }
   public void changeSex(){
       RestClient.getYfmNovate(this).post(Constant.API.YFM_CHANGETRUENAME, new ParamMap.Build()
               .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
               .addParams("sex", sexcheck)
               .build(), new DmeycBaseSubscriber<PublishActionAfterBean>() {
           @Override
           public void onSuccess(PublishActionAfterBean bean) {
//                trueNameBean=bean;
//                et_idenity.setText(bean.getData().getIdNo()+"");
//                et_name.setText(bean.getData().getName());
//                et_phone.setText(bean.getData().getPhoneNO());
               Intent intent=new Intent();
               intent.putExtra("sex",sexcheck);
               setResult(999,intent);
               finish();
               ToastUtil.show(bean.getMsg());
               finish();
           }
           @Override
           public void onError(Throwable e) {

           }
       });
   }

    public void checkIsTrueName(){

        RestClient.getYfmNovate(this).post(Constant.API.YFM_CHECKTRUENAME, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<IsTrueNameBean>() {
            @Override
            public void onSuccess(IsTrueNameBean bean) {
//                finish();
                if(bean.isData()){
                  ToastUtil.show("实名认证后不可修改性别");
                }else {


                    changeSex();
//                    Intent intent=new Intent();
//                    intent.putExtra("sex",sexcheck);
//                    setResult(999,intent);
//                    finish();
                }
            }
        });
    }
}
