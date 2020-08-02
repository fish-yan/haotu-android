package com.dmeyc.dmestoreyfm.newui.home.video;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.api.exception.ApiException;
import com.dmeyc.dmestoreyfm.bean.response.ActivityDetailBean;
import com.dmeyc.dmestoreyfm.bean.response.ActivityDetailBean;
import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;
import com.dmeyc.dmestoreyfm.newui.update.UpdateResultBean;
import com.dmeyc.dmestoreyfm.video.index.IndexDynamicBean;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by cxg on 2019/11/23
 */
public class VideoHomePresenter extends BasePresenter<IVideoHomeView> {
    public void getVideoList() {
        subscriber = new CustomSubscriber<IndexDynamicBean>(mView, mGuid) {
            @Override
            public void onSuccess(IndexDynamicBean bean) {
                Logger.d(bean);
                List<IndexDynamicBean.DataBean> data = bean.getData();
                if (data != null) {
                    for (int i = 0; i < data.size(); i++) {
                        data.get(i).setItemType(Constant.AdapterItemType.TYPE_HOME_VIDEO);
                    }
                }
                mView.getDataListSucc(data);
            }

            @Override
            public void onError(ApiException ex) {
                super.onError(ex);
                mView.onCloseRefresh();
            }
        };
        RetrofitService.getInstance().getListVideo(mView.getParams(), subscriber);
    }

    public void onLike(int id, final int position, final boolean isUnLikePre) {
        subscriber = new CustomSubscriber<BaseRespBean>(mView, mGuid) {
            @Override
            public void onSuccess(BaseRespBean bean) {
                mView.handleLikeSucc(position, isUnLikePre);
            }

            @Override
            public void onError(ApiException ex) {
                super.onError(ex);
                mView.handleLikeFail(position, isUnLikePre);
            }
        };

        Map<String, String> params = new HashMap<>();
        params.put("video_id", String.valueOf(id));
        params.put("type", "1");// 1 点赞，2 收藏
        if (isUnLikePre) {
            RetrofitService.getInstance().addVideoLike(params, subscriber);
        } else {
            RetrofitService.getInstance().delVideoLike(params, subscriber);
        }
    }


    public void updateVersion(Map<String, String> params){
        subscriber = new CustomSubscriber<UpdateResultBean>(mView,mGuid) {
            @Override
            public void onSuccess(UpdateResultBean bean) {
                mView.getUpdateVerSucc(bean);
            }

            @Override
            public void onError(ApiException ex) {
            }
        };
        RetrofitService.getInstance().updateVersion(params,subscriber);
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
