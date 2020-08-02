package com.dmeyc.dmestoreyfm.wedgit.date;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.dmeyc.dmestoreyfm.utils.DensityUtil;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.MultiMonthView;


public class CustomMultiMonthView extends MultiMonthView {

    private int mRadius;

    public CustomMultiMonthView(Context context) {
        super(context);
    }


    @Override
    protected void onPreviewHook() {
//        mRadius = Math.min(mItemWidth, mItemHeight) / 5 * 2;
        mRadius = 8;
        mSchemePaint.setStyle(Paint.Style.STROKE);
//        mCurMonthLunarTextPaint.setTextSize(DensityUtil.dip2px(14));
    }

    @Override
    protected boolean onDrawSelected(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme,
                                     boolean isSelectedPre, boolean isSelectedNext) {
        int cx = x + mItemWidth / 2;
        int cy = y + mItemHeight*3/4;

        Log.e("flag: " ,isSelectedPre + " :: " + isSelectedNext);
//        if (isSelectedPre){
            canvas.drawCircle(cx, cy, mRadius, mSelectedPaint);
//        }
//        if (isSelectedPre) {
//            if (isSelectedNext) {
//                canvas.drawRect(x, cy - mRadius, x + mItemWidth, cy + mRadius, mSelectedPaint);
//            } else {//最后一个，the last
//                canvas.drawRect(x, cy - mRadius, cx, cy + mRadius, mSelectedPaint);
//                canvas.drawCircle(cx, cy, mRadius, mSelectedPaint);
//            }
//        } else {
//            if(isSelectedNext){
//                canvas.drawRect(cx, cy - mRadius, x + mItemWidth, cy + mRadius, mSelectedPaint);
//            }
//            canvas.drawCircle(cx, cy, mRadius, mSelectedPaint);
//            //
//        }

        return false;
    }

    @Override
    protected void onDrawScheme(Canvas canvas, Calendar calendar, int x, int y, boolean isSelected) {
//        int cx = x + mItemWidth / 2;
//        int cy = y + mItemHeight / 2;
//        canvas.drawCircle(cx, cy, mRadius, mSchemePaint);
    }

    @Override
    protected void onDrawText(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme, boolean isSelected) {
        float baselineY = mTextBaseLine + y;
        int cx = x + mItemWidth / 2;

        boolean isInRange = isInRange(calendar);
        boolean isEnable = !onCalendarIntercept(calendar);

        if ( calendar.isCurrentDay() ){
            canvas.drawText(String.valueOf(calendar.getDay()), cx, baselineY, mCurDayTextPaint);
        }else if ((calendar.getTimeInMillis()<java.util.Calendar.getInstance().getTimeInMillis())){
            canvas.drawText(String.valueOf(calendar.getDay()), cx, baselineY, mCurMonthLunarTextPaint);
        }else {
            canvas.drawText(String.valueOf(calendar.getDay()), cx, baselineY, mCurMonthTextPaint);
        }

//        if (isSelected) {
//            canvas.drawText(String.valueOf(calendar.getDay()),
//                    cx,
//                    baselineY,
//                    mSelectTextPaint);
//        } else if (hasScheme) {
//            canvas.drawText(String.valueOf(calendar.getDay()),
//                    cx,
//                    baselineY,
//                    calendar.isCurrentDay() ? mCurDayTextPaint :
//                            calendar.isCurrentMonth() && isInRange && isEnable? mSchemeTextPaint : mOtherMonthTextPaint);
//
//        } else {
//            canvas.drawText(String.valueOf(calendar.getDay()), cx, baselineY,
//                    calendar.isCurrentDay() ? mCurDayTextPaint :
//                            calendar.isCurrentMonth() && isInRange && isEnable? mCurMonthTextPaint : mOtherMonthTextPaint);
//        }
    }
}
