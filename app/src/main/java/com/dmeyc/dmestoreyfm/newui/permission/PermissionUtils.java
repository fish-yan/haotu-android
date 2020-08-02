package com.dmeyc.dmestoreyfm.newui.permission;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import static android.content.ContentValues.TAG;

/**
 * 6.0动态获取权限
 * <p>
 * Created by gis on 2017/4/14.
 */
public class PermissionUtils {

    private FragmentActivity mActivity;

    private Context mContext;

    private PermissionManager permissionManager;


    public PermissionUtils(@NonNull FragmentActivity fragment) {
        this.permissionManager = getPermissionActivity(fragment);
        this.mActivity = fragment;
        mContext = fragment.getApplicationContext();
    }


    private PermissionManager getPermissionActivity(FragmentActivity activity) {
        permissionManager = (PermissionManager) activity.getSupportFragmentManager().findFragmentByTag(TAG);
        boolean isNewInstance = permissionManager == null;
        if (isNewInstance) {
            permissionManager = new PermissionManager();
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            fragmentManager.beginTransaction().add(permissionManager, TAG).commit();
            fragmentManager.executePendingTransactions();
        }
        return permissionManager;
    }


    /**
     * 判断是否有该权限
     *
     * @param permission
     * @return
     */
    public boolean isPermission(String permission) {
        if (ContextCompat.checkSelfPermission(mContext, permission) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }


    /**
     * 获取程序清单文件中的所有权限
     *
     * @return [android.permission.INTERNET, android.permission.READ_PHONE_STATE, ... android.permission.READ_CONTACTS]
     */
    public String[] getPermissionsStrings() {
        String[] permissions = null;
        PackageManager pm = mContext.getPackageManager();
        try {
            PackageInfo pack = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_PERMISSIONS);
            permissions = pack.requestedPermissions;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return permissions;
    }


    /**
     * 外部调用申请权限
     *
     * @param permission 申请的权限
     * @param listener
     */
    public void requestPermissions(String permission, PermissionListener listener) {
        String[] permissions = {permission};
        requestPermissions(permissions, listener);
    }

    /**
     * 申请清单文件中所有的权限
     *
     * @param listener
     */
    public void requestPermissionsAll(PermissionListener listener) {
        requestPermissions(getPermissionsStrings(), listener);
    }


    /**
     * @param permissions 申请的权限
     * @param listener
     */
    public void requestPermissions(String[] permissions, PermissionListener listener) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }
        this.permissionManager.setListener(listener);
        this.permissionManager.requestPermissions(permissions);
    }


    /**
     * 跳转到APP应用信息页面
     */
    public  void getAppDetailSettingIntent() {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.fromParts("package", mContext.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            intent.setAction(Intent.ACTION_VIEW);
            intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            intent.putExtra("com.android.settings.ApplicationPkgName", mContext.getPackageName());
        }
        mActivity.startActivity(intent);
    }


    /**
     * 判断是否不再提示权限授权
     *
     * @param activity   BaseActivity
     * @param permission 请求的权限
     * @return true，需要显示请求权限的理由；false，不需要显示理由
     */
    public static boolean shouldShowRequestPermissionRationale(AppCompatActivity activity, String permission) {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
    }


    /**
     * 判断是否不再提示权限授权
     *
     * @param fragment   BaseFragment
     * @param permission 请求的权限
     * @return true，需要显示请求权限的理由；false，不需要显示理由
     */
    public static boolean shouldShowRequestPermissionRationale(Fragment fragment, String permission) {
        return fragment.shouldShowRequestPermissionRationale(permission);
    }

}
