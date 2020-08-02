package com.dmeyc.dmestoreyfm.newui.menu.club.notice;

import com.dmeyc.dmestoreyfm.newbase.IBaseView;

import java.util.Map;

/**
 * create by cxg on 2019/11/30
 */
public interface INoticeView extends IBaseView {

    Map<String, String> getParams();

    void httpDataSucc();

}
