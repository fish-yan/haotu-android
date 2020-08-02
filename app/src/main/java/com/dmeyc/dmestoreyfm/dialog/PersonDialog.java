package com.dmeyc.dmestoreyfm.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.dmeyc.dmestoreyfm.R;

public class PersonDialog extends Dialog {
    Context context;
    public PersonDialog(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        this.context = context;
    }
    public PersonDialog(Context context, int theme){
        super(context, theme);
        this.context = context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.pop_person );
    }
}
