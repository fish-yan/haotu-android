package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.BankListBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;

import butterknife.Bind;

public class NameChangeActivity  extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.et_name)
    EditText et_name;
    @Bind(R.id.tv_right_title_bar)
    TextView tv_right_title_bar;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_namechange;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
//        tvTitle.setText("关于我们");
//        tvVersion.setText("V" + Util.getLocalVersionName(this));

        et_name.setText(getIntent().getStringExtra("name_nick"));
        tv_right_title_bar.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        int viewid=view.getId();
        if(viewid==R.id.tv_right_title_bar){
            RestClient.getYfmNovate(this).post(Constant.API.YFM_USER_CHAREGNAME, new ParamMap.Build()
                    .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                    .addParams("nick_name", et_name.getText().toString().trim())
                    .addParams("id_type", "")
                    .addParams("id_card", "")
                    .addParams("full_name", "")
                    .addParams("status", "")
                    .build(), new DmeycBaseSubscriber<BankListBean>() {
                @Override
                public void onSuccess(final BankListBean bean) {
                    Toast.makeText(NameChangeActivity.this,"操作成功",Toast.LENGTH_LONG).show();
                    setResult(222,new Intent().putExtra("name_nake",et_name.getText().toString().trim()));
                    finish();
                }
            });
        }
    }
}

