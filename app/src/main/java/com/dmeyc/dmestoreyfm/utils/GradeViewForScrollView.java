package com.dmeyc.dmestoreyfm.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Administrator on 2018/9/10.
 */

public class GradeViewForScrollView extends GridView {
    public GradeViewForScrollView(Context context) {
        super(context);
    }

    public GradeViewForScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GradeViewForScrollView(Context context, AttributeSet attrs,
                                 int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    /**
     * 重写该方法，达到使ListView适应ScrollView的效果
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
    @Override
    public void setSelection(int position) {
        super.setSelection(position);
    }

//    public boolean dispatchTouchEvent(MotionEvent ev){
//     if(ev.getAction()==MotionEvent.ACTION_MOVE) {
//      return false; //禁止GridView滑动
//                 }
// return super.dispatchTouchEvent(ev);
//    }

}