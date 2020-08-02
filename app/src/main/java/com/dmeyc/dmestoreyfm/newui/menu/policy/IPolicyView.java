package com.dmeyc.dmestoreyfm.newui.menu.policy;

import com.dmeyc.dmestoreyfm.newbase.IBaseRefreshView;

import java.util.Map;

/**
 * create by cxg on 2019/11/29
 */
public interface IPolicyView extends IBaseRefreshView {
    Map<String,String> getParams();
}
