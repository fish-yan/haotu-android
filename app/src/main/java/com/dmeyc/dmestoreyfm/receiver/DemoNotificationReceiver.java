package com.dmeyc.dmestoreyfm.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.ui.ChartInforActivity;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import io.rong.imkit.RongIM;
import io.rong.push.PushType;
import io.rong.push.notification.PushMessageReceiver;
import io.rong.push.notification.PushNotificationMessage;

public class DemoNotificationReceiver  extends PushMessageReceiver {

    NotificationManager mNotificationManager;
    @Override
    public boolean onNotificationMessageArrived(Context context, PushType pushType, PushNotificationMessage pushNotificationMessage) {
//        System.out.print("sssssssssssssssssssss"+11111111);
//        Log.e("ssssssssssssss","1111111111111");
//        ToastUtil.show("onNotificationMessageArrived");
        Notification.Builder builder3=new Notification.Builder(context);
        Intent intent3=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.jianshu.com/p/82e249713f1b"));
        PendingIntent pendingIntent3=PendingIntent.getActivity(context,0,intent3,0);
        builder3.setContentIntent(pendingIntent3);
        builder3.setSmallIcon(R.mipmap.ic_launcher);
        builder3.setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher));
        builder3.setAutoCancel(true);
        builder3.setContentTitle("悬挂通知");
        mNotificationManager.notify(2,builder3.build());
        return true;
    }
    /**
     * 第三方push状态回调
     * @param pushType   push类型
     * @param action     当前的操作，连接或者获取token
     * @param resultCode 返回的错误码
     */
    @Override
    public void onThirdPartyPushState(PushType pushType, String action, long resultCode) {
        super.onThirdPartyPushState(pushType, action, resultCode);
            ToastUtil.show("onThirdPartyPushState");
    }

    @Override
    public boolean onNotificationMessageClicked(Context context, PushType pushType, PushNotificationMessage pushNotificationMessage) {
//        ToastUtil.show("onNotificationMessageClicked");
        RongIM.getInstance().startGroupChat(context, pushNotificationMessage.getTargetId()+"", pushNotificationMessage.getTargetUserName()+"");
//        pushNotificationMessage.getTargetId();
        return false;
    }
}
