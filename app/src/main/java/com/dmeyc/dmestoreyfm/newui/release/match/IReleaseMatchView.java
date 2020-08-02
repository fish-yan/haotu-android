package com.dmeyc.dmestoreyfm.newui.release.match;

import com.dmeyc.dmestoreyfm.bean.ProjectTypeBean;
import com.dmeyc.dmestoreyfm.newbase.IBaseView;

import java.util.List;
import java.util.Map;

/**
 * create by cxg on 2019/11/30
 */
public interface IReleaseMatchView extends IBaseView {
    void getBandListSucc(List<ProjectTypeBean.DataBean> data);

    void httpRequestDataSucc();

    Map<String, String> getParams();
}
