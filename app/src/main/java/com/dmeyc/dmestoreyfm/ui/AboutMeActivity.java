package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.utils.Util;

import butterknife.Bind;
import butterknife.OnClick;

public class AboutMeActivity extends BaseActivity {

    @Bind(R.id.tv_viewversion)
    TextView tv_viewversion;
    @Bind(R.id.tv_service)
    TextView tv_service;
    @Bind(R.id.tv_secrece)
    TextView tv_secrece;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_aboutme;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        tv_viewversion.setText("Version  " + Util.getLocalVersionName(this) + "");
    }

    @OnClick({R.id.tv_reviewstome, R.id.tv_service, R.id.tv_secrece})
    public void onClick(View view) {
        int viewid = view.getId();
        if (viewid == R.id.tv_reviewstome) {
            startActivity(new Intent(AboutMeActivity.this, ReversToMeActivity.class).putExtra("type", "2"));
        } else if (viewid == R.id.tv_service) {
            Intent intent = new Intent(this, WebviewActivity.class);
            intent.putExtra(Constant.Config.TITLE, "用户协议");

            intent.putExtra(Constant.Config.URL, "http://www.hotu.club:9595/agreement/agreementhz.html");
//            intent.putExtra(Constant.Config.URL,"http://192.168.0.104/agreement/agreementhz.html");
//            intent.putExtra(Constant.Config.URL,Constant.API.BASE_URL+Constant.API.USER_AGREEMENT);
            startActivity(intent);
        } else if (R.id.tv_secrece == viewid) {
            Intent intent = new Intent(this, WebviewActivity.class);
            intent.putExtra(Constant.Config.TITLE, "隐私政策");
            intent.putExtra(Constant.Config.URL, "http://www.hotu.club:9595/agreement/agreement.html");
//            intent.putExtra(Constant.Config.URL,"\"http://192.168.0.104/agreement/agreementhz.html");
//            intent.putExtra(Constant.Config.URL,"http://192.168.0.104/agreement/agreement.html");
//            intent.putExtra(Constant.Config.URL,Constant.API.BASE_URL+Constant.API.USER_AGREEMENT);
            startActivity(intent);
        }

    }

}
