package com.dmeyc.dmestoreyfm.utils;

import android.text.TextUtils;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jockie on 2017/8/31
 * Email:jockie911@gmail.com
 */

public class DateUtil {

    private static SimpleDateFormat sf;
    private static SimpleDateFormat sdf;

    /**
     * 获取系统时间 格式为："yyyy/MM/dd "
     **/
    public static String getCurrentDate() {
        Date d = new Date();
        sf = new SimpleDateFormat("yyyy年MM月dd日");
        return sf.format(d);
    }

    /**
     * 获取系统时间 格式为："yyyy "
     **/
    public static String getCurrentYear() {
        Date d = new Date();
        sf = new SimpleDateFormat("yyyy");
        return sf.format(d);
    }

    /**
     * 获取系统时间 格式为："MM"
     **/
    public static String getCurrentMonth() {
        Date d = new Date();
        sf = new SimpleDateFormat("MM");
        return sf.format(d);
    }

    /**
     * 获取系统时间 格式为："dd"
     **/
    public static String getCurrentDay() {
        Date d = new Date();
        sf = new SimpleDateFormat("dd");
        return sf.format(d);
    }

    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static long getCurrentTime() {
        long d = new Date().getTime() / 1000;
        return d;
    }

    /**
     * 时间戳转换成字符窜
     */
    public static String getDateToString(long time) {
        Date d = new Date(time * 1000);
        sf = new SimpleDateFormat("yyyy年MM月dd日");
        return sf.format(d);
    }

    /**
     * 时间戳中获取年
     */
    public static String getYearFromTime(long time) {
        Date d = new Date(time * 1000);
        sf = new SimpleDateFormat("yyyy");
        return sf.format(d);
    }

    /**
     * 时间戳中获取月
     */
    public static String getMonthFromTime(long time) {
        Date d = new Date(time * 1000);
        sf = new SimpleDateFormat("MM");
        return sf.format(d);
    }

    /**
     * 时间戳中获取日
     */
    public static String getDayFromTime(long time) {
        Date d = new Date(time * 1000);
        sf = new SimpleDateFormat("dd");
        return sf.format(d);
    }

    /**
     * 将字符串转为时间戳
     */
    public static long getStringToDate(String time) {
        sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * 将字符串转为时间戳
     */
    public static long getlongToDate(String time) {
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static boolean isLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
        if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
            return true;
        }
        return false;
    }

    public static String getYMDHMDate(long time) {
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(time);
        return sdf.format(date);
    }

    public static String getYMD(Date date) {
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * yyyy-MM-dd 转为 yyyy-MM-dd 00:00:00
     */
    public static String appendDateZero(String date) {
        return date + " 00:00:00";
    }

    /**
     * yyyyMMdd 转为 yyyy-MM-dd 00:00:00
     */
    public static String changeDate(String date) {
        if (TextUtils.isEmpty(date) || date.length() != 8) {
            return date;
        }
        return date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8) + " 00:00:00";
    }

    /**
     * yyyyMMdd 转为 yyyy-MM-dd 00:00
     */
    public static String changeDate1(String date) {
        if (TextUtils.isEmpty(date) || date.length() != 8) {
            return date;
        }
        return date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8) + " 00:00";
    }

    /**
     * 获取几天之后的日期 MM月dd日
     *
     * @param validDays
     * @return
     */
    public static String getValueDate(int validDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR + validDays));
        Date date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
        return format.format(date);
    }

}