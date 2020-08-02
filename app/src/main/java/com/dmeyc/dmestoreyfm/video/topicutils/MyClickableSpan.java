package com.dmeyc.dmestoreyfm.video.topicutils;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

public class MyClickableSpan extends ClickableSpan {
    @Override
    public void onClick(@NonNull View view) {

    }
    @Override
    public void updateDrawState(@NonNull TextPaint ds) {
        super.updateDrawState(ds);
        ds.setColor(Color.parseColor("#32aaff"));
        ds.setUnderlineText(false);
    }
}
