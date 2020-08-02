package com.dmeyc.dmestoreyfm.newui.home.video;

import com.dmeyc.dmestoreyfm.newbase.IBaseRefreshView;
import com.dmeyc.dmestoreyfm.newui.update.UpdateResultBean;

import java.util.Map;

/**
 * create by cxg on 2019/11/23
 */
interface IVideoHomeView extends IBaseRefreshView {
    Map<String, String> getParams();

    void getVideosSucc();

    void handleLikeSucc(int position, boolean isUnLikePre);

    void handleLikeFail(int position, boolean isUnLikePre);

    Map<String, String> getUpdateVersionParams();
    void getUpdateVerSucc(UpdateResultBean bean);
    void getActivityImage(String activityId, String group_logo);
}
