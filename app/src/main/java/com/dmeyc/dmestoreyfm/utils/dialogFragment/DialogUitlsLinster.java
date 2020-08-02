package com.dmeyc.dmestoreyfm.utils.dialogFragment;

import android.app.Dialog;

/**
 * Created by ytq on 2018/11/7.
 */
public class DialogUitlsLinster {

    public interface DialogLinster{
        void onCancel(Dialog dialog);
        void onConfirm(Dialog dialog);
    }

    public interface BottomSheetDialogLinster{
        void onConfirm(String type);
    }

}
