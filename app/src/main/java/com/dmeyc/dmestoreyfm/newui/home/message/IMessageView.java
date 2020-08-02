package com.dmeyc.dmestoreyfm.newui.home.message;

import com.dmeyc.dmestoreyfm.bean.response.GroupDetailBean;
import com.dmeyc.dmestoreyfm.newbase.IBaseView;

/**
 * create by cxg on 2019/12/4
 */
public interface IMessageView extends IBaseView {
    void jumpToChat(GroupDetailBean.DataBean data);
}
