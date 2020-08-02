package com.dmeyc.dmestoreyfm.newui.menu.club.clubdetail;

import com.dmeyc.dmestoreyfm.bean.MemberListBean;
import com.dmeyc.dmestoreyfm.bean.NewMemberListBean;
import com.dmeyc.dmestoreyfm.bean.response.GroupDetailBean;
import com.dmeyc.dmestoreyfm.newbase.IBaseView;

import java.util.List;
import java.util.Map;

/**
 * create by cxg on 2019/11/29
 */
public interface IMyClubDetaiView extends IBaseView {
    Map<String,String> getParams();

    void httpDataSucc(GroupDetailBean.DataBean data);

    void setNotificationStatusResult(boolean b, int value);

    void getNotificationStatusResult(int value);

    void getMemberSucc(List<NewMemberListBean.DataBean.ListBean> groupMemberList);

    void quitGroupSucc();
}
