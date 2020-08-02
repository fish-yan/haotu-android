package com.dmeyc.dmestoreyfm.newui.menu.club;

import android.content.Context;
import android.content.Intent;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.newbase.BaseActivity;

/**
 * create by cxg on 2019/11/28
 */
public class MyClubActivity extends BaseActivity {

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, MyClubActivity.class);
        context.startActivity(intent);
    }


    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int setContentView() {
        return R.layout.activity_my_club;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        setTitle("我的俱乐部");
    }
}
