package com.dmeyc.dmestoreyfm.utils;

import android.content.Context;
import android.view.WindowManager;

import com.dmeyc.dmestoreyfm.base.BaseApp;

/**
 * Created by jockie on 2017/11/20
 * Email:jockie911@gmail.com
 */

public class ScreemUtil {

    private static WindowManager wm;

    private ScreemUtil(){}

    public static int getWidth(){
        if (wm == null)
             wm = (WindowManager) BaseApp.getContext().getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }

    public static int getHeight(){
        if (wm == null)
            wm = (WindowManager) BaseApp.getContext().getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getHeight();
    }
}
