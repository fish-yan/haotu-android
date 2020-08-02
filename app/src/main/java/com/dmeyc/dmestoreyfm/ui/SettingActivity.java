package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.utils.SnackBarUtil;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.dmeyc.dmestoreyfm.wedgit.CustomDialog;

import butterknife.Bind;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @Bind(R.id.tv_version)
    TextView tvVersion;


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_setting;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        tvVersion.setText("V" + Util.getLocalVersionName(this));
    }

    @OnClick({R.id.tv_about_us,R.id.tv_user_appointment,R.id.tv_clear_cash,R.id.tv_logout,R.id.tv_return_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_about_us:
                startActivity(new Intent(this,AboutUsActivity.class));
                break;
            case R.id.tv_user_appointment:
                Intent intent = new Intent(this, WebviewActivity.class);
                intent.putExtra(Constant.Config.TITLE,"用户协议");
                intent.putExtra(Constant.Config.URL,Constant.API.BASE_URL+Constant.API.USER_AGREEMENT);
                startActivity(intent);
                break;
            case R.id.tv_clear_cash:
                SnackBarUtil.showShortSnackbar(tvTitle,"清理缓存成功");
                break;
            case R.id.tv_logout:
                new CustomDialog(this)
                        .builder()
                        .showTitle()
                        .setMsg("确定退出吗?")
                        .setPositiveButton("", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Util.exit();
                                startActivity(new Intent(SettingActivity.this, MainActivity.class));
                            }
                        }).setNegativeButton(null,null)
                        .show();
                break;
            case R.id.tv_return_back:
                startActivity(new Intent(this,FeedBackActivity.class));
                break;
        }
    }
}
