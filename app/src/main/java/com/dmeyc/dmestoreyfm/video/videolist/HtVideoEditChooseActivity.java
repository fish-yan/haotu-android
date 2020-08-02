package com.dmeyc.dmestoreyfm.video.videolist;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;

import butterknife.Bind;

public class HtVideoEditChooseActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tv_title;

    private HtVideoChooseFragment mVideoChooseFragment;

    private HandlerThread mHandlerThread;
    private Handler mHandler;

    @Override
    protected int getLayoutRes() {
        return R.layout.ac_new_video_list;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        tv_title.setText("视频选择");

        mHandlerThread = new HandlerThread("LoadList");
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper());

        mVideoChooseFragment = new HtVideoChooseFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container,mVideoChooseFragment)   // 此处的R.id.fragment_container是要盛放fragment的父容器
                .commit();
        requestPermission();
    }

    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mVideoChooseFragment.loadVideoList();
                }
            });
        } else {
            if (Build.VERSION.SDK_INT >= 23) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults != null && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mVideoChooseFragment.loadVideoList();
                }
            });
        }
    }
}
