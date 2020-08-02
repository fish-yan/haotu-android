package com.dmeyc.dmestoreyfm.wedgit;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jockie on 2017/12/14
 * Email:jockie911@gmail.com
 */

public class TextWithTimerView extends FrameLayout {

    private TextView tvLeft;
    private TextView tvTimer;
    private TextView tvRight;
    private long mRemindTime;
    private Timer timer;
    private OnTimerCompleteLister lister;

    public TextWithTimerView(@NonNull Context context) {
        super(context);
    }

    public TextWithTimerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TextWithTimerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.text_with_timer_view, this, true);
        tvLeft = (TextView) view.findViewById(R.id.tv_left);
        tvTimer = (TextView) view.findViewById(R.id.tv_timer);
        tvRight = (TextView) view.findViewById(R.id.tv_right);
    }

    public TextWithTimerView setLeftContent(String leftContent){
        tvLeft.setText(leftContent);
        return this;
    }

    public TextWithTimerView setRightContent(String rightContent){
        tvRight.setText(rightContent);
        return this;
    }

    public void setRemindTime(long remindTime){
        this.mRemindTime = remindTime;
        start();
    }

    public void start(){
        cancle();
        timer = new Timer();
        timer.schedule(new RunTask(),0,SECOND);
    }

    public void cancle(){
        if(timer != null)
            timer.cancel();
    }

    class RunTask extends TimerTask{

        @Override
        public void run() {
            if(mRemindTime >= 0){
                refreshTime(mRemindTime);
                mRemindTime -= SECOND;
            }else{
                if(timer != null){
                    timer.cancel();
                }
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(timer != null)
            timer.cancel();
    }

    private long SECOND = 1000;
    private long MINUTE = SECOND * 60 ;
    private long HOURE = MINUTE * 60;
    private long DAY = HOURE * 24;
    int day,houre,minute,second;
    public void refreshTime(long time){

        if(time > DAY)
            day = (int) (time / DAY);
        if(time > HOURE)
            houre = (int) ((time % DAY)/HOURE);
        if(time > MINUTE)
            minute = (int) ((time % HOURE)/MINUTE);
        second = (int) ((time % MINUTE)/SECOND);
       /*
        if(time > DAY){
            day = (int) (time / DAY);
            houre = (int) ((time % DAY) / HOURE);
        }else if(time > HOURE){
            houre = (int) (time / DAY);
            minute = (int) ((time % HOURE) / MINUTE);
        }else if(time > MINUTE){
            minute = (int) (time / MINUTE);
        }else{
            minute = 1;
        }*/
        handler.sendEmptyMessage(0);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(day > 0){
                tvTimer.setText(day + "天" + houre + "小时" + minute + "分" + second + "秒");
            }else if(houre > 0){
                tvTimer.setText(houre + "小时" + minute + "分"+ second + "秒");
            }else{
                tvTimer.setText( minute + "分"+ second + "秒");
            }
        }
    };

    public void setOnTimerCompleteLister(OnTimerCompleteLister lister){
        this.lister = lister;
    }

    public interface OnTimerCompleteLister{
        void onComplete();
    }
}
