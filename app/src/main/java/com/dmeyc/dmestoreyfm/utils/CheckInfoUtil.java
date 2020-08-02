package com.dmeyc.dmestoreyfm.utils;

import android.text.TextUtils;

/**
 * create by cxg on 2019/11/25
 */
public class CheckInfoUtil {

    public static boolean isPhoneNo(String content) {
        return isPhoneNo(content, "请输入正确的手机号码");
    }

    public static boolean isPhoneNo(String content, String toastInfo) {
        if (checkPhoneNum(content)) {
            return true;
        } else {
            ToastUtil.show(toastInfo);
            return false;
        }
    }


    /**
     * 手机号正则校验
     */
    private static boolean checkPhoneNum(String phoneNum) {
        String telRegex = "[1][35678]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(phoneNum)) return false;
        else return phoneNum.matches(telRegex);
    }
}
