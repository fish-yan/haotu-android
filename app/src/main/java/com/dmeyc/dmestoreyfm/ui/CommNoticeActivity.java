package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.PublishActionAfterBean;
import com.dmeyc.dmestoreyfm.bean.VipMemberListBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;

public class CommNoticeActivity extends BaseActivity {
    @Bind(R.id.et_content)
    EditText et_content;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_commnotice;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }
    @OnClick(R.id.btn_login)
    public void onClick(View view){
        int viewid=view.getId();
        if(viewid==R.id.btn_login){
            if(TextUtils.isEmpty(et_content.getText().toString().trim())){
                ToastUtil.show("请输入群公告");
                return;
            }

            RestClient.getYfmNovate(this).post(Constant.API.YFM_COMMEDIT,  new ParamMap.Build()
                    .addParams("user_token",SPUtils.getStringData(Constant.Config.TOKEN))
                    .addParams("group_id", Integer.parseInt(getIntent().getStringExtra("groupid")))
                    .addParams("notice", et_content.getText().toString().trim())
                    .build(), new DmeycBaseSubscriber<PublishActionAfterBean>() {
                @Override
                public void onSuccess(final PublishActionAfterBean bean) {
                    ToastUtil.show("提交成功");
                    Intent intent=new Intent();
//                    intent.putExtras(123,et_content.getText().toString().trim())
                    finish();
                }});
//            if(TextUtils.isEmpty(et_content.getText().toString())){
//                ToastUtil.show("公告内容不为空");
//                return;
//            }
//            ParamMap.Build  pb=  new ParamMap.Build();
//            pb .addParams("group_id", getIntent().getIntExtra("groupid",-1))
//                    .addParams("group_logo", "")
//                    .addParams("group_name", "")
//                    .addParams("activity_venue_address", "")
//                    .addParams("remark", "")
//                    .addParams("notice", et_content.getText().toString());
//            RestClient.getYfmNovate(this).post(Constant.API.YFM_COMMEDIT,  pb
//                    .addParams("user_token",SPUtils.getStringData(Constant.Config.TOKEN))
//                    .build(), new DmeycBaseSubscriber<VipMemberListBean>() {
//                @Override
//                public void onSuccess(final VipMemberListBean bean) {
//                    ToastUtil.show(bean.getMsg());
//                }});
        }
    }
}
