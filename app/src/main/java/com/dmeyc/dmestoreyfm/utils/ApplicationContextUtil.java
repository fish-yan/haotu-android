package com.dmeyc.dmestoreyfm.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by ytq on 18/11/6.
 */
public class ApplicationContextUtil {

    private static WeakReference<Context> mWeakReference;
    private static Handler handler;

    public static void init(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context cannot be null!");
        }
        mWeakReference = new WeakReference<>(context);
        handler = new Handler();
    }

    public static Context getContext() {
        Context context = mWeakReference.get();
        if (context == null) {
            throw new IllegalArgumentException("context cannot be null!");
        }
        return context;
    }

    public static Handler applicationHandler(){
        return handler;
    }

    /**
     * 判断当前应用程序处于前台还是后台，相关权限android.permission.GET_TASKS
     */
    public static boolean isApplicationBroughtToBackground() {
        Context context = getContext();
        if (context == null) {
            return false;
        } else {
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            if (am != null) {
                List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
                if (tasks != null && !tasks.isEmpty()) {
                    ComponentName topActivity = tasks.get(0).topActivity;
                    if (!topActivity.getPackageName().equals(context.getPackageName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void killProcess(){
        ActivityManager am = (ActivityManager) getContext().getSystemService(Context.ACTIVITY_SERVICE);
        am.killBackgroundProcesses(ApplicationContextUtil.getContext().getPackageName());
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public static synchronized boolean isLowMemory() {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getContext().getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.lowMemory;
    }


    public static String getCurProcessName() {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) getContext().getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager.getRunningAppProcesses() != null) {
            for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
                if (appProcess.pid == pid) {
                    return appProcess.processName;
                }
            }
        }
        return "";
    }

    public static String getMainActivity(Context context, String pkgName) {
        String className = null;
        PackageInfo pi = null;
        try {
            pi = context.getPackageManager().getPackageInfo(pkgName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (pi == null) return null;

        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(pi.packageName);

        List<ResolveInfo> apps = context.getPackageManager().queryIntentActivities(resolveIntent, 0);
        if (apps == null || apps.size() == 0) return null;

        ResolveInfo ri = apps.iterator().next();
        if (ri != null) {
            className = ri.activityInfo.name;
        }
        return className;
    }

    public static Intent setIntentFilter(Intent intent, String filterJson) {
        try {
            JSONObject json = new JSONObject(filterJson);
            if (json.has("category")) {
                JSONArray category = json.getJSONArray("category");
                if (category != null) {
                    for (int i = 0; i < category.length(); i++) {
                        String ctg = category.opt(i).toString();
                        if (!TextUtils.isEmpty(ctg)) {
                            intent.addCategory(ctg);
                        }
                    }
                }
            }
            if (json.has("data")) {
                JSONObject dataJson = json.getJSONObject("data");
                String mimeType = null;
                String scheme = null;
                if (dataJson != null) {
                    if (dataJson.has("mimeType")) {
                        mimeType = dataJson.getString("mimeType");
                    }
                    if (dataJson.has("scheme")) {
                        scheme = dataJson.getString("scheme");
                    }
                    if (TextUtils.isEmpty(mimeType) && !TextUtils.isEmpty(scheme)) {
                        intent.setData(Uri.parse(scheme));
                    } else if (!TextUtils.isEmpty(mimeType) && !TextUtils.isEmpty(scheme)) {
                        intent.setDataAndType(Uri.parse(scheme), mimeType);
                    } else if (!TextUtils.isEmpty(mimeType) && TextUtils.isEmpty(scheme)) {
                        intent.setType(mimeType);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return intent;
    }
}
