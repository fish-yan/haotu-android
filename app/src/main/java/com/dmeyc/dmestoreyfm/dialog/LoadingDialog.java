package com.dmeyc.dmestoreyfm.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.dmeyc.dmestoreyfm.R;

/**
 * Created by jockie on 2018/2/2
 * Email:jockie911@gmail.com
 */

public class LoadingDialog extends Dialog{
    public LoadingDialog(@NonNull Context context) {
        super(context,R.style.dialog_style_center);
//        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
    }
}
