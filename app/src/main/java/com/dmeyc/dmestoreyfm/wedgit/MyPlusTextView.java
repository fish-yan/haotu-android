package com.dmeyc.dmestoreyfm.wedgit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;

@SuppressLint("AppCompatCustomView")
public class MyPlusTextView extends TextView {

    Context context;
    private Paint mBgPaint = new Paint();
    public MyPlusTextView(Context context) {
        super(context);
        this.context=context;
    }

    public MyPlusTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }

    public MyPlusTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
    }

    public MyPlusTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context=context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mBgPaint.setAntiAlias(true);
        mBgPaint.setColor(context.getResources().getColor(R.color.indicator_selected_color));
        canvas.drawCircle(50, 50,
                50,mBgPaint);
        mBgPaint.setColor(context.getResources().getColor(R.color.white));
        mBgPaint.setStrokeWidth(4);
        canvas.drawLine(4,50,96,50,mBgPaint);
        canvas.drawLine(50,4,50,96,mBgPaint);
        super.onDraw(canvas);
    }
}
