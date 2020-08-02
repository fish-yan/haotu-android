package com.dmeyc.dmestoreyfm.ui;

import android.net.Uri;
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
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

public class NickNameChangeActivity extends BaseActivity {
    @Bind(R.id.et_name)
    EditText et_name;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_nicknamechage;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        et_name.setText(getIntent().getStringExtra("nickname"));
    }

    public void changeName(){
        if(TextUtils.isEmpty(et_name.getText().toString().trim())){
            ToastUtil.show("请输入昵称");
            return;
        }
        RestClient.getYfmNovate(this).post(Constant.API.YFM_CHANGETRUENAME, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("nick_name", et_name.getText().toString().trim())
                .build(), new DmeycBaseSubscriber<PublishActionAfterBean>() {
            @Override
            public void onSuccess(PublishActionAfterBean bean) {
   RongIM.getInstance().refreshUserInfoCache(new UserInfo(SPUtils.getStringData(Constant.Config.USER_ID), et_name.getText().toString().trim(), Uri.parse(getIntent().getStringExtra("header"))));

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
    @OnClick(R.id.btn_regist)
    public void onClick(View view){
        int viewid =view.getId();
        if(viewid==R.id.btn_regist){
            changeName();
        }
    }
}
