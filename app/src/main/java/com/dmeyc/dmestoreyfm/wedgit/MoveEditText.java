package com.dmeyc.dmestoreyfm.wedgit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;

import com.dmeyc.dmestoreyfm.R;

/**
 * Created by jockie on 2017/11/27
 * Email:jockie911@gmail.com
 */

@SuppressLint("AppCompatCustomView")
public class MoveEditText extends EditText implements View.OnFocusChangeListener {

    private OnCompleteListener listener;

    public MoveEditText(Context context) {
        super(context);
        init();
    }

    public MoveEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setBackground(null);
        setFocusableInTouchMode(true);
        setTextSize(13);
        if(hasFocus())
            clearFocus();
        setGravity(Gravity.CENTER_VERTICAL|Gravity.RIGHT);
        setHintTextColor(getResources().getColor(R.color.gray));
        setOnFocusChangeListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(hasFocus){
            setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);
            setTextColor(getResources().getColor(R.color.color_1a1a1a));
            setCursorVisible(true);
        }else{
            setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
            setTextColor(getResources().getColor(R.color.gray));
            setCursorVisible(false);
            if(listener != null)
                listener.onComplete(v);
        }
    }

    public void setOnCompleteListener(OnCompleteListener listener){
        this.listener = listener;
    }

    public interface OnCompleteListener{
        void onComplete(View view);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        setTextColor(getResources().getColor(R.color.gray));
    }
}
