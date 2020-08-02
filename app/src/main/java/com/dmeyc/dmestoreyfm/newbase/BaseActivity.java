package com.dmeyc.dmestoreyfm.newbase;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;

import butterknife.ButterKnife;

public abstract class BaseActivity extends BaseCompatStatusBarActivity {
    private boolean isShowStatusBar = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentView());
        setImmersiveStatusBar(true, getResources().getColor(R.color.white));

        //设定为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ButterKnife.bind(this);
        onCreateT(savedInstanceState);
        initViews();
        initData();
    }

    protected abstract int setContentView();

    protected void onCreateT(Bundle savedInstanceState) {

    }

    protected void initData() {

    }

    /**
     * 设置title + 返回icon
     *
     * @param title
     */
    protected void setTitle(String title) {
        ImageView ivBack = findViewById(R.id.iv_title_back);
        ivBack.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        setSingleTitle(title);
    }

    /**
     * 设置title
     *
     * @param title
     */
    protected void setSingleTitle(String title) {
        ((TextView) findViewById(R.id.tv_title)).setText(!TextUtils.isEmpty(title) ? title : "");
    }

    /**
     * 左箭头，title 右图片
     *
     * @param title
     * @param listener
     */
    protected void setTitleWithRightIcon(String title, View.OnClickListener listener) {
        setTitleWithRightIcon(title, listener, -1);
    }

    protected void setTitleWithRightIcon(String title, View.OnClickListener listener, int iconRes) {
        setTitle(title);
        ImageView ivRight = findViewById(R.id.iv_title_right);
        ivRight.setVisibility(View.VISIBLE);
        if (listener != null) {
            ivRight.setOnClickListener(listener);
        }
        if (iconRes != -1) {
            ivRight.setImageResource(iconRes);
        }
    }

    /**
     * 左箭头，title 右文字
     *
     * @param title
     * @param rightString
     * @param listener
     */
    protected void setTitleWithRightText(String title, String rightString, View.OnClickListener listener) {
        setTitle(title);
        TextView tvRight = findViewById(R.id.tv_title_right);
        if (rightString != null) {
            tvRight.setText(rightString);
        }
        tvRight.setVisibility(View.VISIBLE);
        if (listener != null) {
            tvRight.setOnClickListener(listener);
        }
    }

    /**
     * 没有标题，左右文字
     *
     * @param leftString
     * @param rightString
     * @param listener
     */
    protected void setTitleWithBothText(String leftString, String rightString, View.OnClickListener listener) {
        TextView tvRight = findViewById(R.id.tv_title_right);
        if (rightString != null) {
            tvRight.setText(rightString);
        }
        tvRight.setVisibility(View.VISIBLE);
        if (listener != null) {
            tvRight.setOnClickListener(listener);
        }
        TextView tvLeft = findViewById(R.id.tv_title_back);
        tvLeft.setVisibility(View.VISIBLE);
        if (leftString != null) {
            tvLeft.setText(leftString);
        }
        tvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    protected void setTitleBlackBg(String title) {
        findViewById(R.id.cl_title).setBackgroundColor(getResources().getColor(R.color.black));
        ((ImageView) findViewById(R.id.iv_title_back)).setImageResource(R.drawable.camera_back);
        ((TextView) findViewById(R.id.tv_title)).setTextColor(getResources().getColor(R.color.white));
        setTitle(title);
    }

    /**
     * 是否显示状态栏
     *
     * @param showStatusBar
     */
    public void setShowStatusBar(boolean showStatusBar) {
        isShowStatusBar = showStatusBar;
    }

    protected abstract void initViews();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1) {
            getResources();
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();

            res.updateConfiguration(newConfig, res.getDisplayMetrics());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                createConfigurationContext(newConfig);
            } else {
                res.updateConfiguration(newConfig, res.getDisplayMetrics());
            }
        }
        return res;
    }
}