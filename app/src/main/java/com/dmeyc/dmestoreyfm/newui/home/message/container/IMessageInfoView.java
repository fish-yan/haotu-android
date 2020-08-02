package com.dmeyc.dmestoreyfm.newui.home.message.container;

import com.dmeyc.dmestoreyfm.newbase.IBaseRefreshView;
import com.dmeyc.dmestoreyfm.video.index.IndexDynamicBean;

import java.util.List;
import java.util.Map;

/**
 * create by cxg on 2019/12/22
 */
public interface IMessageInfoView extends IBaseRefreshView {
    String getVideoDetailParams();

    void getVideoDetailSucc(List<IndexDynamicBean.DataBean> list);
}
