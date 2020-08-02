package com.dmeyc.dmestoreyfm.wedgit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jockie on 2017/11/8
 * Email:jockie911@gmail.com
 */

@SuppressLint("AppCompatCustomView")
public class TimerTaskTextView extends TextView{

    private Timer timer;
    private static int TOTAL_TIMER_TIME = 60;
    private static int CURRENT_tIME = TOTAL_TIMER_TIME;

    public TimerTaskTextView(Context context) {
        super(context);
        init();
    }

    public TimerTaskTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setGravity(Gravity.CENTER);
        setStatus(false);
        timer = new Timer();
    }

    public void setStatus(boolean isClickable){
//        setBackgroundResource(isClickable ? R.drawable.shape_input_code_focus : R.drawable.shape_input_code_normal);
//        setTextColor(getResources().getColor(isClickable ? R.color.color_1a1a1a : R.color.color_c5c5c5));
        setClickable(isClickable);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(timer != null)
            timer.cancel();
    }

    public void startTimer(){
        CURRENT_tIME = TOTAL_TIMER_TIME;
        setStatus(false);
        timer = new Timer();
        timer.schedule(new TimeRunneable(),0,1000);
    }

    public void pauseTimer(){
        CURRENT_tIME = TOTAL_TIMER_TIME;
        timer.cancel();
        setStatus(false);
    }

    public void stopTimer(){
        if (timer!=null){
            timer.cancel();
            timer = null;
        }
    }

    class TimeRunneable extends TimerTask {

        @Override
        public void run() {
            CURRENT_tIME --;
            Message obtain = Message.obtain();
            obtain.what = CURRENT_tIME;
            handler.sendMessage(obtain);
        }
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            if(what > 0){
                setStatus(false);
                setText(what + "秒");
            }else{
                setStatus(true);
                timer.cancel();
                CURRENT_tIME = TOTAL_TIMER_TIME;
                setText("发送");
            }
        }
    };
}
