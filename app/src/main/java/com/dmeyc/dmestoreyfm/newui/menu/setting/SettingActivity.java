package com.dmeyc.dmestoreyfm.newui.menu.setting;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.newbase.BaseMvpActivity;
import com.dmeyc.dmestoreyfm.newbase.MyActivityLifecycleCallbacks;
import com.dmeyc.dmestoreyfm.newui.login.LoginActivity;
import com.dmeyc.dmestoreyfm.newui.menu.setting.about.AboutActivity;
import com.dmeyc.dmestoreyfm.newui.menu.setting.accountsave.AccountSaveActivity;
import com.dmeyc.dmestoreyfm.newui.menu.setting.privacy.PrivacyActivity;
import com.dmeyc.dmestoreyfm.ui.YFMLoginActivity;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.TimerTaskTextView;

import butterknife.Bind;
import butterknife.OnClick;
import io.rong.imkit.RongIM;

/**
 * create by cxg on 2019/11/24
 */
public class SettingActivity extends BaseMvpActivity<ISettingView, SettingPresenter> implements ISettingView {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }
    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int setContentView() {
        return R.layout.activity_setting_new;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        setTitle("设置");
    }

    @Override
    protected SettingPresenter createPresenter() {
        return new SettingPresenter();
    }

    @OnClick({R.id.civ_privacy,
            R.id.civ_safe,
            R.id.civ_about,
            R.id.tv_logout})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.civ_privacy:
                PrivacyActivity.startActivity(this);
                break;
            case R.id.civ_safe:
                AccountSaveActivity.startActivity(this);
                break;
            case R.id.civ_about:
                AboutActivity.startActivity(this);
                break;
            case R.id.tv_logout:
                mPresenter.logout();
                break;
        }
    }

    @Override
    public void logoutSucc(String msg) {
        ToastUtil.show(msg);
        RongIM.getInstance().logout();
        SPUtils.savaBooleanData(Constant.Config.ISLOGIN,false);
        SPUtils.savaStringData(Constant.Config.ROLECODE,"0");
        SPUtils.savaStringData(Constant.Config.TOKEN,"");
        SPUtils.savaStringData(Constant.Config.RC_TOKEN,"");
        SPUtils.savaStringData(Constant.Config.USER_ID,"");
        SPUtils.savaStringData(Constant.Config.AVATAR,"");
        SPUtils.savaStringData(Constant.Config.NICK_NAME,"");
        MyActivityLifecycleCallbacks.exitAllActivity();
        startActivity(new Intent(SettingActivity.this, LoginActivity.class));
        finish();
    }

}
