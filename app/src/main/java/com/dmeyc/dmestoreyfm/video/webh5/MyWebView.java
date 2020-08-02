package com.dmeyc.dmestoreyfm.video.webh5;

import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.dmeyc.dmestoreyfm.R;


/**
 * 类描述：一个让其支持App内部显示资源、支持JavaScript、支持显示进度条的WebView
 */

public class MyWebView extends WebView {

    private ProgressBar progressbar;

    public MyWebView(Context context) {
        super(context);
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initProgressBar(context);
        openJavaScript();
        setWebViewClient(new WebViewClient());
        setWebChromeClient(new WebChromeClient());
    }

    private void initProgressBar(Context context) {
        progressbar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, dp2px(context, 3), 0, 0));
        //改变progressbar默认进度条的颜色（深红色）为Color.GREEN
        progressbar.setProgressDrawable(new ClipDrawable(new ColorDrawable(ContextCompat.getColor(context, R.color.colorPrimary)), Gravity.LEFT, ClipDrawable.HORIZONTAL));
        addView(progressbar);
    }

    //实例化WebViwe后，调用此方法可滚动到底部
    public void scrollToBottom() {
        scrollTo(0, computeVerticalScrollRange());
    }

    /**
     * 方法描述：启用支持javascript
     */
    private void openJavaScript() {
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(false);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSaveFormData(true);
        settings.setGeolocationEnabled(true);
        settings.setSavePassword(false);
        settings.setDomStorageEnabled(true);
        requestFocus();
        setScrollBarStyle(SCROLLBARS_INSIDE_OVERLAY);
        //设置自适应屏幕，两者合用（下面这两个方法合用）
        settings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        //缩放操作
        settings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        settings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        settings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        //其他细节操作
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE); //关闭webview中缓存
        settings.setAllowFileAccess(false); //设置可以访问文件
        settings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        settings.setLoadsImagesAutomatically(true); //支持自动加载图片
        settings.setDefaultTextEncodingName("utf-8");//设置编码格式
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        //支持内容重新布局
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setAppCacheEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        //支持javascript
//        settings.setDomStorageEnabled(true);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }

    /**
     * 方法描述：根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 类描述：显示WebView加载的进度情况
     */
    public class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressbar.setVisibility(GONE);
            } else {
                if (progressbar.getVisibility() == GONE)
                    progressbar.setVisibility(VISIBLE);

                progressbar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }
}
