package com.dmeyc.dmestoreyfm.video.videoplaytest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.video.topicutils.OnTopicClickListener;
import com.dmeyc.dmestoreyfm.video.topicutils.SpanWeiBoTextUtils;
import com.tencent.liteav.demo.play.SuperPlayerView;

public class VideoPlayTestActvity extends Activity implements  SuperPlayerView.OnSuperPlayerViewCallback{
    private TextView  textView;
    private String s = "#哈吉倒海翻江安徽省大家#按实际肯定会饭卡手动阀空间#哈哈哈哈哈#";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_video_play_test);
        textView = findViewById(R.id.tv);

        // 话题正则处理
        textView.setText(SpanWeiBoTextUtils.getWeiBoContent(VideoPlayTestActvity.this, s, textView, new OnTopicClickListener() {
            @Override
            public void onClick(String topic) {
                //
                ToastUtil.show("点击了话题："+topic);
            }
        }));
    }

    @Override
    public void onStartFullScreenPlay() {

    }
    @Override
    public void onStopFullScreenPlay() {

    }
    @Override
    public void onClickFloatCloseBtn() {

    }

    @Override
    public void onClickSmallReturnBtn() {

    }

    @Override
    public void onStartFloatWindowPlay() {

    }
}
