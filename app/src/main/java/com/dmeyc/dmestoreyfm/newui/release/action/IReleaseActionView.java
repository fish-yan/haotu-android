package com.dmeyc.dmestoreyfm.newui.release.action;

import com.dmeyc.dmestoreyfm.bean.ProjectTypeBean;
import com.dmeyc.dmestoreyfm.newbase.IBaseView;

import java.util.List;
import java.util.Map;

/**
 * create by cxg on 2019/11/30
 */
public interface IReleaseActionView extends IBaseView {
    void getActionListSucc(List<ProjectTypeBean.DataBean> data);

    Map<String, String> getParams();

    void httpRequestSucc();
}
