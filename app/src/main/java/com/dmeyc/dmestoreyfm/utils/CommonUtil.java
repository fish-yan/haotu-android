package com.dmeyc.dmestoreyfm.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.constant.SPKey;
import com.dmeyc.dmestoreyfm.newui.login.LoginActivity;
import com.orhanobut.logger.Logger;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import butterknife.OnClick;

/**
 * create by cxg on 2019/12/8
 */
public class CommonUtil {
    /**
     * 忽略所有https证书
     */
    public static SSLContext overlockCardSSLContext() {
        SSLContext sslContext;
        final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                X509Certificate[] x509Certificates = new X509Certificate[0];
                return x509Certificates;
            }
        }};
        try {
            //            SSLContext.getInstance("TLS");
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts,
                    new java.security.SecureRandom());
            return sslContext;
        } catch (Exception e) {
            Logger.e("ssl出现异常");
        }
        return null;
    }

    // 防过快点击
    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) <= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }

    public static void jumpCallActivity(Context context, String phoneNo) {
        if (!TextUtils.isEmpty(phoneNo)) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri data = Uri.parse("tel:" + phoneNo);
            intent.setData(data);
            context.startActivity(intent);
        }
    }

    public static boolean isLogin(Context context) {
        Boolean isLogin = SPUtils.getBooleanData(Constant.Config.ISLOGIN, false);
        if (!isLogin) {
            context.startActivity(new Intent(context, LoginActivity.class));
            return false;
        }

        return true;
    }

    public static boolean hasUnReadMessage() {
        return SPUtils.getIntData(SPKey.PUSH_COMMEND) == 0 &&
                SPUtils.getIntData(SPKey.PUSH_LIKE) == 0 &&
                SPUtils.getIntData(SPKey.PUSH_AT) == 0 &&
                SPUtils.getIntData(SPKey.PUSH_FOLLOW) == 0;
    }
}
