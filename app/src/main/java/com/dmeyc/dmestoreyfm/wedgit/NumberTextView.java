package com.dmeyc.dmestoreyfm.wedgit;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;

/**
 * Created by jockie on 2017/11/3
 * Email:jockie911@gmail.com
 */

public class NumberTextView extends RelativeLayout {

    private TextView tvCount;
    private TextView tvName;

    public NumberTextView(Context context) {
        super(context);
    }

    public NumberTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public NumberTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.number_textview, this,true);
        tvCount = (TextView) view.findViewById(R.id.tv_count);
        tvName = (TextView) view.findViewById(R.id.tv_name);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.numberTextView);
        if(attributes != null){
            String numberName = attributes.getString(R.styleable.numberTextView_numberName);
            tvName.setText(numberName);
        }
        attributes.recycle();

        notifyCount(0);
    }

    public void notifyCount(int count){
        if(count == 0){
            tvCount.setTextColor(getResources().getColor(R.color.color_adadad));
            tvName.setTextColor(getResources().getColor(R.color.color_adadad));
        }else{
            tvCount.setTextColor(getResources().getColor(R.color.tabber_selected_color));
            tvName.setTextColor(getResources().getColor(R.color.tabber_selected_color));
        }
        tvCount.setText(String.valueOf(count));
    }
}
