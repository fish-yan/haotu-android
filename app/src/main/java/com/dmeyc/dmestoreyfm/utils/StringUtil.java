package com.dmeyc.dmestoreyfm.utils;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

/**
 * create by cxg on 2019/11/24
 */
public class StringUtil {
    /**
     * 手机号正则校验
     */
    public static boolean checkPhoneNum(String phoneNum) {
        String telRegex = "[1][35678]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(phoneNum)) return false;
        else return phoneNum.matches(telRegex);
    }

    public static void setTVColor(String str, int start, int end, int color, TextView tv) {
        SpannableStringBuilder builder = new SpannableStringBuilder(str);
        builder.setSpan(new ForegroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(builder);
    }

    /**
     * 首行缩进
     */
    public static void setTVRetract(String str, TextView tv) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        str = "缩进" + str;
        SpannableStringBuilder builder = new SpannableStringBuilder(str);
        builder.setSpan(new ForegroundColorSpan(Color.TRANSPARENT), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(builder);
    }
}
