package com.dmeyc.dmestoreyfm.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.dmeyc.dmestoreyfm.R;

public class TestWebVeiw extends Activity {

    public WebView webview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_testview);
        webview=(WebView) findViewById(R.id.webview);

        WebSettings webSettings = webview.getSettings();
//支持缩放，默认为true。
        webSettings .setSupportZoom(false);
//调整图片至适合webview的大小
        webSettings .setUseWideViewPort(true);
// 缩放至屏幕的大小
        webSettings .setLoadWithOverviewMode(true);
//设置默认编码
        webSettings .setDefaultTextEncodingName("utf-8");
////设置自动加载图片
        webSettings .setLoadsImagesAutomatically(true);
        webview.loadUrl("https://open.duodianbao.net/H5d/login.html?mobile=shuiyu6&inviteCode=JFQSD&vip=VIP1");
    }
}
