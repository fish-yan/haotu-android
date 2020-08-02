package com.dmeyc.dmestoreyfm.ui;

import android.content.Context;
import android.content.Intent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.constant.Constant;

import butterknife.Bind;

public class WebviewActivity extends BaseActivity {

    private String type;

    @Bind(R.id.webview)
    WebView mWebview;

    public static void newInstance(Context context,String title,String url) {
        Intent intent = new Intent(context, WebviewActivity.class);
        intent.putExtra(Constant.Config.TITLE, title);
        intent.putExtra(Constant.Config.URL, url);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_webview;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        type=getIntent().getStringExtra("type");
        tvTitle.setText(getIntent().getStringExtra(Constant.Config.TITLE));
        if("type".equals(type)){
            mWebview.loadDataWithBaseURL(null,getIntent().getStringExtra(Constant.Config.URL), "text/html", "UTF-8", null);
//            mWebview.loadUrl();
        }else {
            mWebview.loadUrl(getIntent().getStringExtra(Constant.Config.URL));
        }


        WebSettings webSettings = mWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
// 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
// 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可

//设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

//缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

//其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式


        mWebview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
           if("type".equals(type)){
               mWebview.loadDataWithBaseURL(null,getIntent().getStringExtra(Constant.Config.URL), "text/html", "UTF-8", null);
           }else {
               view.loadUrl(url);
           }

                return true;
            }
        });
    }
}
