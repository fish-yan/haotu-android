package com.dmeyc.dmestoreyfm.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.ViewConfiguration;
import android.view.WindowManager;


import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 与尺寸相关的工具集
 * Created by ytq on 18/11/7.
 */
public class DimenUtil {

    private static Point screenSize;

    public static int dp2px(float dpValue) {
        final float scale = ApplicationContextUtil.getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp(float pxValue) {
        final float scale = ApplicationContextUtil.getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * Use {@link DimenUtil#dp2px(float)} instead for font size unification.
     */
    @Deprecated
    public static int sp2px(float spValue) {
        final float fontScale = ApplicationContextUtil.getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int getScreenWidth() {
        if (screenSize == null) {
            screenSize = new Point();
            WindowManager wm = (WindowManager) ApplicationContextUtil.getContext().getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getSize(screenSize);
        }
        return screenSize.x;
    }

    public static int getScreenHeight() {
        if (screenSize == null) {
            screenSize = new Point();
            WindowManager wm = (WindowManager) ApplicationContextUtil.getContext().getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getSize(screenSize);
        }
        return screenSize.y;
    }

    public static float getScreenDensity() {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) ApplicationContextUtil.getContext().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.density;
    }

    /**
     * 虚拟按键高度
     *
     * @return
     */
    public static int getNavigationBarHeight() {
        int result = 0;
        if (hasNavBar(ApplicationContextUtil.getContext())) {
            Resources res = ApplicationContextUtil.getContext().getResources();
            int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = res.getDimensionPixelSize(resourceId);
            }
        }
        return result;
    }

    /**
     * 检查是否存在虚拟按键栏
     *
     * @param context
     * @return
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public static boolean hasNavBar(Context context) {
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("config_showNavigationBar", "bool", "android");
        if (resourceId != 0) {
            boolean hasNav = res.getBoolean(resourceId);
            // check override flag
            String sNavBarOverride = getNavBarOverride();
            if ("1".equals(sNavBarOverride)) {
                hasNav = false;
            } else if ("0".equals(sNavBarOverride)) {
                hasNav = true;
            }
            return hasNav;
        } else { // fallback
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        }
    }

    /**
     * 判断虚拟按键栏是否重写
     *
     * @return
     */
    private static String getNavBarOverride() {
        String sNavBarOverride = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                Class c = Class.forName("android.os.SystemProperties");
                Method m = c.getDeclaredMethod("get", String.class);
                m.setAccessible(true);
                sNavBarOverride = (String) m.invoke(null, "qemu.hw.mainkeys");
            } catch (Throwable e) {
            }
        }
        return sNavBarOverride;
    }

    public static int getStatusBarHeight() {
        int statusBarHeight = 0;
        try {
            /**
             * 通过反射机制获取StatusBar高度
             */
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            Field field = clazz.getField("status_bar_height");
            int height = Integer.parseInt(field.get(object).toString());
            statusBarHeight = ApplicationContextUtil.getContext().getResources().getDimensionPixelSize(height);
            if (statusBarHeight > 0) {
                return statusBarHeight;
            }
        } catch (Exception e) {
            return 0;
        }
        return 0;
    }
}
