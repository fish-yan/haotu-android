package com.dmeyc.dmestoreyfm.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;

import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.newui.login.LoginActivity;
import com.dmeyc.dmestoreyfm.ui.YFMLoginActivity;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import java.util.logging.Handler;

import io.rong.imlib.RongIMClient;

public class ImConnectionLister implements RongIMClient.ConnectionStatusListener {

private Context context;
public ImConnectionLister(Context context){
    this.context=context;
}
    @Override
    public void onChanged(ConnectionStatus connectionStatus) {
        switch (connectionStatus) {

            case CONNECTED://连接成功。

                break;
            case DISCONNECTED://断开连接。

                break;
            case CONNECTING://连接中。

                break;
            case NETWORK_UNAVAILABLE://网络不可用。

                break;
            case KICKED_OFFLINE_BY_OTHER_CLIENT://用户账户在其他设备登录，本机会被踢掉线
                        Intent intent=new Intent(context, LoginActivity.class);
//                         intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//注意本行的FLAG设置
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                        context.startActivity(intent);
                SPUtils.savaBooleanData(Constant.Config.ISLOGIN,false);
                ( (Activity)context).finish();
                break;
        }
    }
}
//    @Override
//    public void onChanged(ConnectionStatus connectionStatus) {
//        switch (connectionStatus){
//
//            case CONNECTED://连接成功。
//
//                break;
//            case DISCONNECTED://断开连接。
//
//                break;
//            case CONNECTING://连接中。
//
//                break;
//            case NETWORK_UNAVAILABLE://网络不可用。
//
//                break;
//            case KICKED_OFFLINE_BY_OTHER_CLIENT://用户账户在其他设备登录，本机会被踢掉线
//                ToastUtil.show("ldsjfdslkf");
////                new Handler(Looper.getMainLooper()).post(new Runnable() {
////                    @Override
////                    public void run() {
////                        Intent intent=new Intent(context,ConnectStatusDialog.class);
////                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
////                        context.startActivity(intent);
////                    }
////                });
//                break;
//
//    }
//}

