package com.dmeyc.dmestoreyfm.wedgit;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;

/**
 * Created by jockie on 2017/11/6
 * Email:jockie911@gmail.com
 */

public class IndercatorTextView extends LinearLayout{

    private TextView tvCurrentPosition;
    private TextView tvTotalCount;
    private int totalCount;

    public IndercatorTextView(Context context) {
        super(context);
        init();
    }

    public IndercatorTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IndercatorTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.indercator_textview, this, true);
        tvCurrentPosition = (TextView) view.findViewById(R.id.tv_current_position);
        tvTotalCount = (TextView) view.findViewById(R.id.tv_total_count);

        setCurrentPosition(0);
    }

    public void setCurrentPosition(int position){
        tvCurrentPosition.setText(String.valueOf(totalCount == 0 ? position : position % totalCount  + 1 ));
    }

    public void initCurrentPosition(int position,int totalCount){
        this.totalCount = totalCount;
        setCurrentPosition(position);
        tvTotalCount.setText(String.valueOf(totalCount));
    }
}
