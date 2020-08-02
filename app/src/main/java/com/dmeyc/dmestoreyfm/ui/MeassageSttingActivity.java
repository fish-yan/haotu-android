package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;

import butterknife.Bind;
import butterknife.OnClick;

public class MeassageSttingActivity extends BaseActivity {
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_messagestting;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }
  @OnClick(R.id.tv_outlog)
    public void onClick(View view){
//      if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//          Intent intent = new Intent();
//          intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
//          intent.putExtra("app_package", getPackageName());
//          intent.putExtra("app_uid", getApplicationInfo().uid);
//          startActivity(intent);
//      } else if (android.os.Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
//          Intent intent = new Intent();
//          intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//          intent.addCategory(Intent.CATEGORY_DEFAULT);
//          intent.setData(Uri.parse("package:" + getPackageName()));
//          startActivity(intent);
//      }
      Intent intent=new Intent(Settings.ACTION_APPLICATION_SETTINGS);
      startActivity(intent);
  }
}
