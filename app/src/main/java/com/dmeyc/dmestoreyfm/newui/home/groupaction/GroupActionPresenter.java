package com.dmeyc.dmestoreyfm.newui.home.groupaction;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.api.exception.ApiException;
import com.dmeyc.dmestoreyfm.bean.response.ActivityDetailBean;
import com.dmeyc.dmestoreyfm.bean.response.GroupActionBean;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;

import java.util.HashMap;
import java.util.Map;

/**
 * create by cxg on 2019/12/1
 */
public class GroupActionPresenter extends BasePresenter<IGroupActionView> {
    public void httpRequestData(String mTypeFrom) {
        subscriber = new CustomSubscriber<GroupActionBean>(mView, mGuid) {
            @Override
            public void onSuccess(GroupActionBean bean) {
                mView.getDataListSucc(bean.getData());
            }
            @Override
            public void onError(ApiException ex) {
                super.onError(ex);
                mView.onCloseRefresh();
            }
        };

        switch (mTypeFrom){
            case GroupActionFragment.FROM_GROUP_ACTION:
            case GroupActionFragment.FROM_GROUP_MATCH:
            case GroupActionFragment.FROM_GROUP_ALL:
                RetrofitService.getInstance().queryActivityList(mView.getParams(), subscriber);
                break;
            case GroupActionFragment.FROM_MINE_ACTION:
            case GroupActionFragment.FROM_MENU_ACTION:
            case GroupActionFragment.FROM_MENU_MATCH:
            case GroupActionFragment.FROM_SELECTOR_MATCH:
                RetrofitService.getInstance().queryMyCreateActivity(mView.getParams(), subscriber);
                break;
            case GroupActionFragment.FROM_RELATION:
                RetrofitService.getInstance().listLinkedActivitys(mView.getParams(), subscriber);
                break;
        }
    }
    public void httpActivityDetail(final String activityId) {
        subscriber = new CustomSubscriber<ActivityDetailBean>(mView,mGuid) {
            @Override
            public void onSuccess(ActivityDetailBean bean) {
                mView.getActivityImage(activityId,bean.getData().getSportsPoster());
            }
        };
        Map<String, String> params = new HashMap<>();
        params.put("group_activity_id", activityId);
        RetrofitService.getInstance().getActivityDetail(params,subscriber);
    }
}
