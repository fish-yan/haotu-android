package com.dmeyc.dmestoreyfm.ui;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.PublishActionAfterBean;
import com.dmeyc.dmestoreyfm.bean.YFMUserBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;

public class ReversToMeActivity extends BaseActivity {

    @Bind(R.id.et_content)
    EditText et_content;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_reverstome;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
                if("1".equals(getIntent().getStringExtra("type"))){
                    et_content.setHint("请输入意见建议");
                    tv_title.setText("意见反馈");
                 }else {
                    et_content.setHint("请输入投诉内容");
                    tv_title.setText("投诉");
                }
          }
    public void upData(){

        if(TextUtils.isEmpty(et_content.getText().toString().trim())){
            ToastUtil.show("请输入内容");
            return;
        }
           RestClient.getYfmNovate(this).post(Constant.API.YFM_REVERTO, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
               .addParams("content", et_content.getText().toString().trim())
                   .addParams("type",getIntent().getStringExtra("type"))
                .build(), new DmeycBaseSubscriber<PublishActionAfterBean>() {
            @Override
            public void onSuccess(PublishActionAfterBean bean) {
                if("1".equals(getIntent().getStringExtra("type"))){
                    ToastUtil.show("意见反馈成功");
                }else {
                    ToastUtil.show("投诉提交成功");
                }

               finish();
            }
           });

    }
    @OnClick(R.id.btn_login)
    public void onClick(){
        upData();
    }
}
