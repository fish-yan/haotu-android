package com.dmeyc.dmestoreyfm.utils;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseApp;

/**
 * Created by jockie on 2017/11/7
 * Email:jockie911@gmail.com
 */

public class ToastUtil {

    private static Toast toastCenter;
    private static Toast toast;
    private static TextView tvToastView;

    /**
     * WaveSideBar 使用
     * @param text
     */
    public static void showCenter(String text){
        if(toastCenter == null){
            View view = View.inflate(BaseApp.getContext(), R.layout.toast_view, null);
            toastCenter = Toast.makeText(BaseApp.getContext(),"",Toast.LENGTH_SHORT);
            toastCenter.setView(view);
            toastCenter.setGravity(Gravity.CENTER,0,0);
        }
        TextView tvToastView = toastCenter.getView().findViewById(R.id.tv_toast_view);
        tvToastView.setText(text);
        toastCenter.show();
    }

    /**
     * Toast 弹窗
     * @param text
     */
    public static void show(String text){
        show(text,Toast.LENGTH_SHORT);
    }

    public static void show(String text,int duration){
        if(toast == null){
            toast = Toast.makeText(BaseApp.getContext(),"",duration);
            tvToastView = toast.getView().findViewById(android.R.id.message);
            tvToastView.setTextSize(14);
        }
        tvToastView.setText(text);
        toast.show();
    }
}
