package com.dmeyc.dmestoreyfm.utils.dialogFragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.utils.DimenUtil;

/**
 * Created by ytq on 2018/11/7.
 */
public class DialogUtils {

    private static DialogUtils instance;

    private DialogUtils() {
    }

    public static DialogUtils getInstance() {
        if (instance == null) {
            instance = new DialogUtils();
        }
        return instance;
    }

    public Dialog showDialog(Context context, final DialogUitlsLinster.DialogLinster linster, String hint,
                             String cancel, String confirm, String title, @DrawableRes int resId) {
        final Dialog dialog = new Dialog(context, R.style.DialogStyle);

        View view = ((Activity) context).getLayoutInflater().inflate(R.layout.dialogutils_layout, null);
        TextView content_hint = view.findViewById(R.id.content_hint);
        TextView tv_cancel = view.findViewById(R.id.tv_cancel);
        TextView tv_confirm = view.findViewById(R.id.tv_confirm);
        TextView tv_title = view.findViewById(R.id.tv_title);
        ImageView content_iv = view.findViewById(R.id.content_iv);
        content_hint.setText(hint);
        tv_cancel.setText(cancel);
        tv_confirm.setText(confirm);
        tv_title.setText(title);
        content_iv.setImageResource(resId);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linster.onCancel(dialog);
            }
        });
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linster.onConfirm(dialog);
            }
        });

        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(view);
//        dialog.setOnKeyListener(this);
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = DimenUtil.getScreenWidth();
            window.setAttributes(lp);
        }
        return dialog;
    }

}
