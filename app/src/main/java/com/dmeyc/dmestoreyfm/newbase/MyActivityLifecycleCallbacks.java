package com.dmeyc.dmestoreyfm.newbase;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * create by cxg on 2020/1/1
 */
public class MyActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    private static List<Activity> mActivityList = new ArrayList<>();

    public static void exitAllActivity() {
        List<Activity> removeList = new ArrayList<>(mActivityList);
        for (Activity activity : removeList) {
            if (activity != null) {
                activity.finish();
            }
        }
        mActivityList.clear();
    }

    public static int getActivitySize() {
        return mActivityList.size();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        mActivityList.add(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        mActivityList.remove(activity);

    }
}
