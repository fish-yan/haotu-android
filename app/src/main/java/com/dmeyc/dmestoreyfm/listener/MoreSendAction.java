package com.dmeyc.dmestoreyfm.listener;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.ui.MyJoinCommActivity;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import io.rong.imkit.actions.IClickActions;

public class MoreSendAction implements IClickActions {
    private Context context;
    public MoreSendAction(Context context){
        this.context=context;
    }
    @Override
    public Drawable obtainDrawable(Context context) {
        return  context.getResources().getDrawable(R.drawable.right_iconmore);
    }

    @Override
    public void onClick(Fragment fragment) {
//        ToastUtil.show("zhuanfa");
        context.startActivity(new Intent(context,MyJoinCommActivity.class));
    }
}
