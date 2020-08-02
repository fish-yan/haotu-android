package com.dmeyc.dmestoreyfm.newui.permission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gis on 2017/4/17.
 */
public class PermissionManager extends Fragment {


    /**
     * 申请权限的requestCode
     */
    public static final int PERMISSIONS_REQUEST_CODE = 200;

    //////////////////////////////////////////////////////////////////////////////////////////
    /**危险权限**/
    /**
     * 读写权限
     */
    public static final String GROUP_STORAGE = Manifest.permission_group.STORAGE;//权限组,存储
    public static final String READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;//读取SD卡
    public static final String WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;//写入SD

    /**
     * 位置权限
     */
    public static final String GROUP_LOCATION = Manifest.permission_group.LOCATION;//权限组-位置
    public static final String ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;//WiFi或移动基站定位，粗略
    public static final String ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;//GPS卫星定位，精确定位

    /**
     * 传感器
     */
    public static final String GROUP_SENSORS = Manifest.permission_group.SENSORS;//权限组-传感器
    public static final String BODY_SENSORS = Manifest.permission.BODY_SENSORS;

    /**
     * 相机
     */
    public static final String GROUP_CAMERA = Manifest.permission_group.CAMERA;//权限组-相机
    public static final String CAMERA = Manifest.permission.CAMERA;

    /**
     * 麦克风、音频
     */
    public static final String GROUP_MICROPHONE = Manifest.permission_group.MICROPHONE;//权限组-麦克风
    public static final String RECORD_AUDIO = Manifest.permission.RECORD_AUDIO;//录制声音

    /**
     * 拨打电话和管理
     */
    public static final String GROUP_PHONE = Manifest.permission_group.PHONE;//权限组-电话
    public static final String CALL_PHONE = Manifest.permission.CALL_PHONE;    //拨打电话
    public static final String PROCESS_OUTGOING_CALLS = Manifest.permission.PROCESS_OUTGOING_CALLS;  //监视、修改、放弃播出电话
    public static final String READ_CALL_LOG = Manifest.permission.READ_CALL_LOG;//读取通话记录
    public static final String READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE;//访问电话状态
    public static final String USE_SIP = Manifest.permission.USE_SIP;//允许程序使用SIP视频服务
    public static final String WRITE_CALL_LOG = Manifest.permission.WRITE_CALL_LOG;//修改通话记录

    /**
     * 通讯录
     */
    public static final String GROUP_CONTACTS = Manifest.permission_group.CONTACTS;//权限组-联系人
    public static final String GET_ACCOUNTS = Manifest.permission.GET_ACCOUNTS;//访问GMail账户列表
    public static final String READ_CONTACTS = Manifest.permission.READ_CONTACTS;//允许应用访问联系人通讯录信息
    public static final String WRITE_CONTACTS = Manifest.permission.WRITE_CONTACTS;//写入联系人，但不可读取

    /**
     * 发送和查看短信
     */
    public static final String GROUP_SMS = Manifest.permission_group.SMS;//权限组-短信
    public static final String SEND_SMS = Manifest.permission.SEND_SMS;//发送短信
    public static final String READ_SMS = Manifest.permission.READ_SMS;//读取短信内容
    public static final String RECEIVE_SMS = Manifest.permission.RECEIVE_SMS;//接收短信
    public static final String RECEIVE_MMS = Manifest.permission.RECEIVE_MMS;//接收彩信
    public static final String RECEIVE_WAP_PUSH = Manifest.permission.RECEIVE_WAP_PUSH;//接收WAP PUSH信息


    /**
     * 日历和活动
     */
    public static final String GROUP_CALENDAR = Manifest.permission_group.CALENDAR;//权限组-日历
    public static final String READ_CALENDAR = Manifest.permission.READ_CALENDAR;//允许程序读取用户的日程信息
    public static final String WRITE_CALENDAR = Manifest.permission.WRITE_CALENDAR;//写入日程，但不可读取

    /////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 权限监听接口
     */
    private PermissionListener permissionListener;

    void setListener(PermissionListener listener) {
        this.permissionListener = listener;
    }

    /**
     * 申请权限
     *
     * @param permissions 需要申请的权限
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    void requestPermissions(@NonNull String[] permissions) {
        List<String> requestPermissionList = new ArrayList<>();
        //找出所有未授权的权限
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(getContext(), permission) != PackageManager.PERMISSION_GRANTED) {
                requestPermissionList.add(permission);
            }
        }
        if (!requestPermissionList.isEmpty()) {
            //申请授权
            requestPermissions(requestPermissionList.toArray(new String[requestPermissionList.size()]), PERMISSIONS_REQUEST_CODE);
        }
    }

    /**
     * fragment回调处理权限的结果
     *
     * @param requestCode  请求码 要等于申请时候的请求码
     * @param permissions  申请的权限
     * @param grantResults 对应权限的处理结果
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CODE: {
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        if (permissionListener != null)
                            permissionListener.onGranted(requestCode, permissions[i], grantResults[i]);
                    } else {
                        boolean flag = shouldShowRequestPermissionRationale(permissions[i]);
                        if (flag) {
                            if (permissionListener != null)
                                permissionListener.onDenied(requestCode, permissions[i], grantResults[i]);
                        } else {
                            if (permissionListener != null)
                                permissionListener.onNoPrompt(requestCode, permissions[i], grantResults[i]);
                        }
                    }
                }
                return;
            }
        }
    }

}




