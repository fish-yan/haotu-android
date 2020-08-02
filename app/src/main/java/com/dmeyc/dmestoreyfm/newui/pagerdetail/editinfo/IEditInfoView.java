package com.dmeyc.dmestoreyfm.newui.pagerdetail.editinfo;

import com.dmeyc.dmestoreyfm.newbase.IBaseView;

import java.util.Map;

/**
 * create by cxg on 2019/12/21
 */
public interface IEditInfoView extends IBaseView {
    void httpRequestDataSucc();

    Map<String, String> getParams();
}
