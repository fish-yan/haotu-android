package com.dmeyc.dmestoreyfm.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.newui.login.LoginActivity;
import com.dmeyc.dmestoreyfm.ui.chat.MyExtensionModule;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.Util;

import io.rong.imkit.RongExtensionManager;
import io.rong.sight.SightExtensionModule;

public class YFMSplashActivity extends Activity {

    String type = "1";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置没有标题栏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_yfmsplash);
//        RongExtensionManager.getInstance().registerExtensionModule(new MyExtensionModule());
        RongExtensionManager.getInstance().registerExtensionModule(new SightExtensionModule());

        RelativeLayout layoutSplash = (RelativeLayout) findViewById(R.id.activity_splash);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
        alphaAnimation.setDuration(3000);//设置动画播放时长1000毫秒（1秒）
        layoutSplash.startAnimation(alphaAnimation);
        //设置动画监听
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            //动画结束
            @Override
            public void onAnimationEnd(Animation animation) {
                //页面的跳转
                if (Util.isLogin()) {
                    type = "1";
                    if ("0".equals(type)) {
                        Intent intent = new Intent(YFMSplashActivity.this, BMainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        SPUtils.savaStringData(Constant.Config.IDENITY, "1");
                        Intent intent = new Intent(YFMSplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                } else {
                    // TODO: 2019/12/20
//                    Intent intent = new Intent(YFMSplashActivity.this, YFMLoginActivity.class);
                    Intent intent = new Intent(YFMSplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
    }
}
