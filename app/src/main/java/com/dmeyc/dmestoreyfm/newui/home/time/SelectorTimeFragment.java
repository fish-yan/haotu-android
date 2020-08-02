package com.dmeyc.dmestoreyfm.newui.home.time;

import android.view.View;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.event.MyEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseFragment;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.utils.DateUtil;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.Date;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * create by cxg on 2019/12/1
 */
public class SelectorTimeFragment extends BaseFragment implements CalendarView.OnMonthChangeListener {

    @Bind(R.id.tv_selector_time)
    TextView mTvSelectorTime;

    @Bind(R.id.calendarView)
    CalendarView mCalendarView;

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int setContentView() {
        return R.layout.fragment_selector_time;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        java.util.Calendar date = java.util.Calendar.getInstance();
        int year = date.get(java.util.Calendar.YEAR);
        int month = date.get(java.util.Calendar.MONTH) + 1;
        mTvSelectorTime.setText(year + "年" + month + "月");
        mCalendarView.setOnMonthChangeListener(this);
    }

    @OnClick({R.id.iv_left_icon, R.id.iv_right_icon, R.id.tv_confirm, R.id.tv_reset})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_left_icon:
                mCalendarView.scrollToPre();
                break;
            case R.id.iv_right_icon:
                mCalendarView.scrollToNext();
                break;
            case R.id.tv_confirm:
                Logger.d(mCalendarView.getMultiSelectCalendars());
                EventBus.getDefault().post(new MyEvent.GroupActionTabClick());
                EventBus.getDefault().post(new MyEvent.SelectorDates(MyEvent.SelectorDates.TYPE_REFRESH, getSelectorDates()));
                break;
            case R.id.tv_reset:
                mCalendarView.clearMultiSelect();
                break;
            default:
        }
    }

    @Override
    public void onMonthChange(int year, int month) {
        mTvSelectorTime.setText(year + "年" + month + "月");
    }

    private String getSelectorDates() {
        StringBuffer sb = new StringBuffer();
        if (mCalendarView.getMultiSelectCalendars().size() > 0) {
            for (Calendar date : mCalendarView.getMultiSelectCalendars()) {
                sb.append(DateUtil.changeDate(date.toString())).append(",");
            }
            return sb.substring(0, sb.length() - 1);
        }
        return "";
    }
}
