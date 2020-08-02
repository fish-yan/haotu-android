package com.dmeyc.dmestoreyfm.utils.photoselector;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 微信仿iPhone风格对话框Item点击事件
 * 
 * @author yuanhang <729741121@qq.com>
 * 
 */
public class CustomDialogItemClickListener implements OnClickListener {

    private CustomDialogItem mItem;
    private Dialog mDialog;

    public CustomDialogItemClickListener(CustomDialogItem item, Dialog dialog) {
        mItem = item;
        mDialog = dialog;
    }

    @Override
    public void onClick(View v) {
        if (mItem != null) {
            mItem.onClick();// 使用item的onclick处理
        }
        if (mDialog != null) {
            mDialog.dismiss();// 对话框消失
            mDialog = null;
        }
    }

}
