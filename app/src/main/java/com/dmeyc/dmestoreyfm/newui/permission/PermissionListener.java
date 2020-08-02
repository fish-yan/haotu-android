package com.dmeyc.dmestoreyfm.newui.permission;

/**
 * Created by gis on 2017/4/17.
 */

public interface PermissionListener {

    /**
     * 同意权限申请
     * <p>
     * Agree with permissions
     *
     * @param grantedPermission
     */
    void onGranted(int requestCode, String grantedPermission, int grantResults);


    /**
     * 拒绝权限申请
     * <p>
     * Permission Denied
     *
     * @param deniedPermission 被拒绝的权限
     */
    void onDenied(int requestCode, String deniedPermission, int grantResults);


    /**
     * 拒绝权限申请,并且勾选不在询问/或者之前已经拒绝，并且勾选了不在询问
     * <p>
     * No longer prompt application permissions
     *
     * @param deniedPermission
     */
    void onNoPrompt(int requestCode, String deniedPermission, int grantResults);

}
