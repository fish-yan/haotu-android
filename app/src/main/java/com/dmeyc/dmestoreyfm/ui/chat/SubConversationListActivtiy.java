package com.dmeyc.dmestoreyfm.ui.chat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dmeyc.dmestoreyfm.R;

@SuppressLint("Registered")
public class SubConversationListActivtiy extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subchat);
    }
}
