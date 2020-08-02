package com.dmeyc.dmestoreyfm.wedgit.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.utils.DateUtil;
import com.haibin.calendarview.CalendarView;


/**
 * create by cxg on 2019/12/11
 */
public class DateSelectorDialog extends Dialog implements View.OnClickListener, CalendarView.OnMonthChangeListener {

    private Context mContext;
    private OnClickListener mListener;
    private TextView mTvSelectorTime;
    private CalendarView mCalendarView;
    private View mView;

    private DateSelectorDialog(@NonNull Context context) {
        super(context, R.style.Dialog);
        mContext = context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_date_selector, null, false);
        view.findViewById(R.id.iv_close).setOnClickListener(this);
        view.findViewById(R.id.iv_left_icon).setOnClickListener(this);
        view.findViewById(R.id.iv_right_icon).setOnClickListener(this);
        view.findViewById(R.id.tv_confirm).setOnClickListener(this);
        view.findViewById(R.id.tv_reset).setOnClickListener(this);
        mCalendarView = view.findViewById(R.id.calendarView);
        mTvSelectorTime = view.findViewById(R.id.tv_selector_time);
        mCalendarView.setOnMonthChangeListener(this);
        setContentView(view);

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = mContext.getResources().getDisplayMetrics().widthPixels;
        lp.height = mContext.getResources().getDisplayMetrics().heightPixels;
        getWindow().setAttributes(lp);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.iv_left_icon:
                mCalendarView.scrollToPre();
                break;
            case R.id.iv_right_icon:
                mCalendarView.scrollToNext();
                break;
            case R.id.tv_confirm:
                if (mListener != null) {
                    mListener.onConfirm(mView, DateUtil.changeDate1(mCalendarView.getSelectedCalendar().toString()));
                }
                dismiss();
                break;
            case R.id.tv_reset:
                mCalendarView.clearSingleSelect();
                break;
            default:
        }
    }

    @Override
    public void onMonthChange(int year, int month) {
        mTvSelectorTime.setText(year + "年" + month + "月");
    }

    public static class Build {
        private DateSelectorDialog mDialog;

        public Build(Context context) {
            mDialog = new DateSelectorDialog(context);
        }

        public Build setListener(OnClickListener listener) {
            mDialog.mListener = listener;
            return this;
        }

        public Build setView(View view) {
            mDialog.mView = view;
            return this;
        }

        public DateSelectorDialog create() {
            return mDialog;
        }
    }

    public interface OnClickListener {
        void onConfirm(View view, String time);
    }
}
