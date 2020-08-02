package com.dmeyc.dmestoreyfm.video.webh5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.video.systeminfo.SystemInfoActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class H5Activity extends AppCompatActivity {

    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.webView)
    MyWebView webView;

    private String url;

    public static void newIntent(Activity activity, String url) {
        Intent intent = new Intent(activity, SystemInfoActivity.class);
        intent.putExtra("url", url);
        activity.startActivity(intent);
    }

    private void loadData() {
        if (TextUtils.isEmpty(url)) {
            webView.loadUrl(url);
        }
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5);
        ButterKnife.bind(this);
        url = getIntent().getStringExtra("url");
        loadData();
    }

    @OnClick(R.id.back)
    public void onClick() {
        onBackPressed();
    }
}
