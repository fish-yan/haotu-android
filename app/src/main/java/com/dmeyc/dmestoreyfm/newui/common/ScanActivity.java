package com.dmeyc.dmestoreyfm.newui.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.event.MyEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseActivity;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.QRCodeView;

public class ScanActivity extends BaseActivity implements QRCodeView.Delegate {
    @Bind(R.id.zxingview_panasonic)
    QRCodeView zxingview_panasonic;

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, ScanActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_scan;
    }

    @Override
    protected void initViews() {
        zxingview_panasonic.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.iv_back)
    public void click() {
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        beginScan(zxingview_panasonic);
    }

    private void beginScan(QRCodeView qrCodeView) {
        qrCodeView.setDelegate(this);
        qrCodeView.startCamera();
        qrCodeView.showScanRect();
        qrCodeView.startSpot();
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        EventBus.getDefault().post(new MyEvent.ScanResult(result));
        finish();
    }

    /**
     * 摄像头环境亮度发生变化
     *
     * @param isDark 是否变暗
     */
    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        ToastUtil.show("未成功打开相机");
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStop() {
        zxingview_panasonic.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        zxingview_panasonic.onDestroy();
        super.onDestroy();
    }
}