package com.dmeyc.dmestoreyfm.newui.menu.setting.about.report;

import com.dmeyc.dmestoreyfm.newbase.IBaseView;

import java.util.Map;

/**
 * create by cxg on 2019/11/24
 */
public interface IReportView extends IBaseView {
    Map<String,String> getParams();

    void httpDataSuc();
}
