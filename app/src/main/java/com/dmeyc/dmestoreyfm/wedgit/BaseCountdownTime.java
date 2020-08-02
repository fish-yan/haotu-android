package com.dmeyc.dmestoreyfm.wedgit;

/**
 * Created by zhaocheng on 2017/3/27.
 */

public abstract class BaseCountdownTime {
    protected int hour;
    protected int minute;
    protected int second;
    protected int day;
    public abstract String getTimeText();
}
