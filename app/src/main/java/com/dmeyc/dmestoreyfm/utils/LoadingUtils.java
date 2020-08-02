package com.dmeyc.dmestoreyfm.utils;

import android.app.Activity;
import android.app.ProgressDialog;

public class LoadingUtils {

    private static ProgressDialog progressDialog = null;
    private static ProgressDialog horizontalProgressDialog = null;

    public static void showProgressDialog(Activity context, String msg) {
        cancelProgressDialog();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(msg);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public static void cancelProgressDialog() {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.cancel();
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        } finally {
            progressDialog = null;
        }
    }
}
