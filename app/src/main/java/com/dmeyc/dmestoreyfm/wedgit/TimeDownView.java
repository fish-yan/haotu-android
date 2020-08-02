package com.dmeyc.dmestoreyfm.wedgit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.widget.TextView;


import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseApp;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jockie on 2018/3/26
 * Email:jockie911@gmail.com
 */

@SuppressLint("AppCompatCustomView")
public class TimeDownView extends TextView {
    private Timer timer;
    private DownTimerTask downTimerTask;
    private int downCount;
    private int lastDown;
    private long intervalMills;
    private long delayMills;
    private AnimationSet animationSet;

    private int textColor = getResources().getColor(R.color.indicator_selected_color);

    public TimeDownView(Context context) {
        this(context, null);
    }

    public TimeDownView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeDownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = VERSION_CODES.LOLLIPOP)
    public TimeDownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
//        if (timer == null) {
//            timer = new Timer();
//        }
        if (animationSet == null) {
            animationSet = new AnimationSet(true);
        }
        if (downHandler == null){
            downHandler = new DownHandler();
        }
        setGravity(Gravity.CENTER);
        setTextColor(textColor);
        setTextSize(200);

        if(soundPool == null){
            if (Build.VERSION.SDK_INT >= 21) {
                SoundPool.Builder builder = new SoundPool.Builder();
                //传入音频的数量
                builder.setMaxStreams(2);
                //AudioAttributes是一个封装音频各种属性的类
                AudioAttributes.Builder attrBuilder = new AudioAttributes.Builder();
                //设置音频流的合适属性
                attrBuilder.setLegacyStreamType(AudioManager.STREAM_MUSIC);
                builder.setAudioAttributes(attrBuilder.build());
                soundPool = builder.build();
            } else {
                //第一个参数是可以支持的声音数量，第二个是声音类型，第三个是声音品质
                soundPool = new SoundPool(2, AudioManager.STREAM_SYSTEM, 5);
            }
        }
        //第一个参数Context,第二个参数资源Id，第三个参数优先级
        soundId_start = soundPool.load(BaseApp.getContext(), R.raw.camera_start, 1);
        soundId_end = soundPool.load(BaseApp.getContext(), R.raw.camera_end, 1);
    }

    /**
     * 开始计时
     *
     * @param seconds
     */
    public void downSecond(int seconds) {
        downTime(seconds, 1, 0, 1000);
    }

    /**
     * 倒计时开启方法
     *
     * @param downCount     倒计时总数
     * @param lastDown      显示的倒计时的最后一个数
     * @param delayMills    延迟启动倒计时（毫秒数）
     * @param intervalMills 倒计时间隔时间（毫秒数）
     */
    public void downTime(int downCount, int lastDown, long delayMills, long intervalMills) {
        this.downCount = downCount;
        this.lastDown = lastDown;
        this.delayMills = delayMills;
        this.intervalMills = intervalMills;
        setVisibility(VISIBLE);
        initDefaultAnimate();

//        if (downTimerTask == null) {
            downTimerTask = new DownTimerTask();
//        }
        timer = new Timer();
        timer.schedule(downTimerTask, delayMills, intervalMills);
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (View.GONE == visibility) {
            timer.cancel();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (drawTextFlag == DRAW_TEXT_NO) {
            return;
        }
        super.onDraw(canvas);
    }

    /**
     * 取消
     */
    public void cancel() {
        if(timer != null){
            timer.cancel();
            setVisibility(INVISIBLE);
            timer = null;
        }
    }

    private class DownTimerTask extends TimerTask {

        @Override
        public void run() {
            if (downCount >= (lastDown - 1)) {
                Message msg = Message.obtain();
                msg.what = 1;
                downHandler.sendMessage(msg);
            }
        }
    }

    public interface DownTimeWatcher {
        void onTime(int num);

        void onLastTime(int num);

        void onLastTimeFinish(int num);
    }

    private DownTimeWatcher downTimeWatcher = null;

    /**
     * 监听倒计时的变化
     *
     * @param downTimeWatcher
     */
    public void setOnTimeDownListener(DownTimeWatcher downTimeWatcher) {
        this.downTimeWatcher = downTimeWatcher;
    }
    private DownHandler downHandler;

    private class DownHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (downTimeWatcher != null) {
                    downTimeWatcher.onTime(downCount);
                }
                if (downCount >= (lastDown - 1)) {
                    drawTextFlag = DRAW_TEXT_YES;//默认绘制
                    playSound(downCount);
                    //未到结束时
                    if (downCount >= lastDown) {
                        setText(downCount + "");
                        startDefaultAnimate();
                        if (downCount == lastDown && downTimeWatcher != null) {
                            downTimeWatcher.onLastTime(downCount);
                        }
                    }

                    //到结束时
                    else if (downCount == (lastDown - 1)) {// 若lastDown为0，downCount == -1时是倒计时真正结束之时。
                        if (downTimeWatcher != null) {
                            downTimeWatcher.onLastTimeFinish(downCount);
                        }
                        //倒计时结束，虽然setText()方法触发onDraw，但重写使之不进行绘制
                        //设置不绘制标记
                        if (afterDownDimissFlag == AFTER_LAST_TIME_DIMISS) {
                            drawTextFlag = DRAW_TEXT_NO;
                        }
                        invalidate();//刷新一下
                        timer.cancel();
                    }
                    downCount--;
                }
                //
            }
        }
    }
    private final int DRAW_TEXT_YES = 1;
    private final int DRAW_TEXT_NO = 0;
    /**
     * 是否执行onDraw的标识，默认绘制
     */
    private int drawTextFlag = DRAW_TEXT_YES;

    private final int AFTER_LAST_TIME_DIMISS = 1;
    private final int AFTER_LAST_TIME_NODIMISS = 0;
    /**
     * 在倒计时结束之后文字是否消失的标志，默认消失
     */
    private int afterDownDimissFlag = AFTER_LAST_TIME_DIMISS;

    /**
     * 设置倒计时结束后文字不消失
     */
    public void setAfterDownNoDimiss() {
        afterDownDimissFlag = AFTER_LAST_TIME_NODIMISS;
    }

    /**
     * 设置倒计时结束后文字消失
     */
    public void setAferDownDimiss() {
        afterDownDimissFlag = AFTER_LAST_TIME_DIMISS;
    }

    private boolean startDefaultAnimFlag = true;

    //关闭默认动画
    public void closeDefaultAnimate() {
        animationSet.reset();
        startDefaultAnimFlag = false;
    }
    //开启默认动画
    private void startDefaultAnimate() {
        if (startDefaultAnimFlag) {
            animationSet.startNow();
        }
    }

    private void initDefaultAnimate() {
        if (animationSet == null) {
            animationSet = new AnimationSet(true);
        }
//        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 0.3f, 1f, 0.3f, Animation.RESTART, 0.5f, Animation.RESTART, 0.5f);
//        scaleAnimation.setDuration(intervalMills);

        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0.3f);
        alphaAnimation.setDuration(intervalMills);
        //将AlphaAnimation这个已经设置好的动画添加到 AnimationSet中
//        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setInterpolator(new AccelerateInterpolator());
        setAnimation(animationSet);
    }

    static SoundPool soundPool;
    static int soundId_start,soundId_end;

    /**
     * 播放声音
     * @param currentTime
     */
    public static void playSound(int currentTime) {
       /* soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                soundPool.play(1, 1, 1, 0, 0, 1);
            }
        });*/
        if(currentTime == 1){
            soundPool.play(soundId_end, 1, 1, 0, 0, 1);
        }else if(currentTime > 1){
            soundPool.play(soundId_start, 1, 1, 0, 0, 1);
        }
        //第一个参数id，即传入池中的顺序，第二个和第三个参数为左右声道，第四个参数为优先级，第五个是否循环播放，0不循环，-1循环
        //最后一个参数播放比率，范围0.5到2，通常为1表示正常播放
//        soundPool.play(1, 1, 1, 0, 0, 1);
        //回收Pool中的资源
        //soundPool.release();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        cancel();
        if(soundPool != null)
            soundPool.release();
    }
}
