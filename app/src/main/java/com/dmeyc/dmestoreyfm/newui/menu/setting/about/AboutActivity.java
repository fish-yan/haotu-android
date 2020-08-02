package com.dmeyc.dmestoreyfm.newui.menu.setting.about;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;

import com.dmeyc.dmestoreyfm.BuildConfig;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.newbase.BaseActivity;
import com.dmeyc.dmestoreyfm.newui.menu.setting.about.report.ReportActivity;
import com.dmeyc.dmestoreyfm.wedgit.CustomItemView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * create by cxg on 2019/11/24
 */
public class AboutActivity extends BaseActivity {

    @Bind(R.id.civ_version)
    CustomItemView mCivVersion;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, AboutActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected int setContentView() {
        return R.layout.activity_about;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        setTitle("关于好兔");
        mCivVersion.setRightTextContent(BuildConfig.VERSION_NAME);
    }

    @OnClick(R.id.civ_report)
    public void click(){
        ReportActivity.startActivity(this,ReportActivity.TYPE_REPORT);
    }

}
