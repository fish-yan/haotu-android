package com.dmeyc.dmestoreyfm.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseApp;

/**
 * Created by Eric on 2017/1/17.
 */

public class NetUtil {

    /**
     * 检查当前网络是否可用
     * @return 是否连接到网络
     */
    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) BaseApp.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isNetworkErrThenShowMsg() {
        if (!isNetworkAvailable()) {
            //TODO: 刚启动app Snackbar不起作用，延迟显示也不好使，这是why？
            Toast.makeText(BaseApp.getContext(), BaseApp.getContext().getString(R.string.internet_error),
                    Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}
