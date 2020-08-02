package com.dmeyc.dmestoreyfm.receiver;

import android.content.Context;
import android.os.Bundle;

import com.huawei.hms.support.api.push.PushReceiver;

public class HuaWeiPushReciver extends PushReceiver {
    @Override
    public void onToken(Context context, String token) {
        super.onToken(context, token);
    }

    @Override
    public boolean onPushMsg(Context context, byte[] msgBytes, Bundle extras) {
        return super.onPushMsg(context, msgBytes, extras);
    }

    @Override
    public void onEvent(Context context, Event event, Bundle extras) {
        super.onEvent(context, event, extras);
    }
}
