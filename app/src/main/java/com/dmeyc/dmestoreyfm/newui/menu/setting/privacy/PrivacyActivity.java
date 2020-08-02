package com.dmeyc.dmestoreyfm.newui.menu.setting.privacy;

import android.content.Context;
import android.content.Intent;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.newbase.BaseMvpActivity;

/**
 * create by cxg on 2019/11/24
 */
public class PrivacyActivity extends BaseMvpActivity<IPrivacyView, PrivacyPresenter> implements IPrivacyView {

    // TODO: 2019/11/24 逻辑未写
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, PrivacyActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected PrivacyPresenter createPresenter() {
        return new PrivacyPresenter();
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int setContentView() {
        return R.layout.activity_privacy;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        setTitle("隐私设置");
    }
}
