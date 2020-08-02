package com.dmeyc.dmestoreyfm.wedgit;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jockie on 2017/12/7
 * Email:jockie911@gmail.com
 */

public class CountView extends LinearLayout {

    @Bind(R.id.tv_count)
    TextView tvCount;
    public CountView(Context context) {
        super(context);
    }

    public CountView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CountView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.count_view, this, true);
        ButterKnife.bind(view);
    }

    public void setCount(Object count){
        if(count instanceof Integer){
            tvCount.setText(String.valueOf(count));
        }else if(count instanceof String){
            tvCount.setText((String) count);
        }
    }
}
