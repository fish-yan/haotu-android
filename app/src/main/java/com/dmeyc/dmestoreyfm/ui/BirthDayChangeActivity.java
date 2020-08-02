package com.dmeyc.dmestoreyfm.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
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

public class BirthDayChangeActivity extends BaseActivity {
    @Bind(R.id.et_birth)
    EditText et_birth;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_birthdaychange;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        et_birth.setText(getIntent().getStringExtra("birth"));
    }
    @OnClick(R.id.btn_regist)
    public void onClick(View view){
        int viewid=view.getId();
        if(viewid==R.id.btn_regist){
            birthdaychange();
        }
    }

    public void birthdaychange(){
                    if(TextUtils.isEmpty(et_birth.getText().toString().trim())){
                        ToastUtil.show("生日不能为空");
                        return;
                    }
        RestClient.getYfmNovate(this).post(Constant.API.YFM_CHANGETRUENAME, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("birthday", et_birth.getText().toString().trim())
                .build(), new DmeycBaseSubscriber<PublishActionAfterBean>() {
            @Override
            public void onSuccess(PublishActionAfterBean bean) {
//                trueNameBean=bean;
//                et_idenity.setText(bean.getData().getIdNo()+"");
//                et_name.setText(bean.getData().getName());
//                et_phone.setText(bean.getData().getPhoneNO());
                ToastUtil.show(bean.getMsg());
                finish();
            }
            @Override
            public void onError(Throwable e) {

            }
        });

    }
}
