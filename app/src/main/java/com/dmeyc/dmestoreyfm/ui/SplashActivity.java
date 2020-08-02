package com.dmeyc.dmestoreyfm.ui;

import android.media.MediaPlayer;
import android.net.Uri;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.ui.chat.MyExtensionModule;
import com.dmeyc.dmestoreyfm.wedgit.CustomVideoView;

import butterknife.Bind;
import io.rong.imkit.RongExtensionManager;
import io.rong.sight.SightExtensionModule;

public class SplashActivity extends BaseActivity {

    @Bind(R.id.videoview)
    CustomVideoView mVideoView;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected boolean isContainTitle() {
        return false;
    }

    @Override
    protected void initData() {
//设置播放加载路径

        mVideoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.welcome));
        //播放
        mVideoView.start();
        //循环播放
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mVideoView.start();
            }
        });

    }

    @Override
    protected boolean isFullScreem() {
        return true;
    }
}
