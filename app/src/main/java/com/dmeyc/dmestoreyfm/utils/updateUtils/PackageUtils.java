package com.dmeyc.dmestoreyfm.utils.updateUtils;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * APK信息
 * Created by shadyY on 18/12/22.
 */

public class PackageUtils {

    /**
     * 获取版本名称
     * @param context
     * @return
     */
    public static String getVersionName (Context context){
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取版本号
     * @param context
     * @return
     */
    public static int getVersionCode(Context context){
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
