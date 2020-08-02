package com.dmeyc.dmestoreyfm.newui.home.living;

import android.text.TextUtils;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.api.exception.ApiException;
import com.dmeyc.dmestoreyfm.bean.response.ActivityDetailBean;
import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;
import com.dmeyc.dmestoreyfm.bean.response.LivingBannerBean;
import com.dmeyc.dmestoreyfm.bean.response.LivingListBean;
import com.dmeyc.dmestoreyfm.bean.response.UserListBean;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;
import com.dmeyc.dmestoreyfm.video.systeminfo.ReadMsgModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by cxg on 2019/12/29
 */
public class LivingActivityPresenter extends BasePresenter<ILivingActivityView> {

    public static final String TYPE_H5 = "TYPE_H5";
    public static final String TYPE_QRCODE = "TYPE_QRCODE";

    private List<ActivityDetailBean.DataBean.SignUp> mAnchorList = new ArrayList<>();//主播列表
    private List<UserListBean.DataBean> mCheckListTemp = new ArrayList<>();//主播列表

    public List<ActivityDetailBean.DataBean.SignUp> getUserDatas() {
        return mAnchorList;
    }

    public void httpBannerData() {
        subscriber = new CustomSubscriber<LivingBannerBean>(mView, mGuid) {
            @Override
            public void onSuccess(LivingBannerBean bean) {
                List<String> list = new ArrayList<>();
                if (bean.getData() != null) {
                    for (LivingBannerBean.DataBean item : bean.getData()) {
                        list.add(item.getItemName());
                    }
                }
                mView.getBannerSucc(list);
            }
        };
        Map<String, String> params = new HashMap<>();
        params.put("key", "LIVEBROADCAST_ROATAIN");
        RetrofitService.getInstance().getDicItemByKey(params, subscriber);
    }

    public void httpAddAnchorData(List<UserListBean.DataBean> checkList) {
        mCheckListTemp.clear();
        mCheckListTemp.addAll(checkList);
        StringBuffer sbIds = new StringBuffer();
        for (UserListBean.DataBean bean : checkList) {
            sbIds.append(bean.getUserId()).append(",");
        }
        if (sbIds.length() == 0) {
            return;
        }
        String mAnchorIds = sbIds.substring(0, sbIds.length() - 1);

        subscriber = new CustomSubscriber<BaseRespBean>(mView, mGuid) {
            @Override
            public void onSuccess(BaseRespBean bean) {
                for (UserListBean.DataBean item : mCheckListTemp) {
                    ActivityDetailBean.DataBean.SignUp signUp = new ActivityDetailBean.DataBean.SignUp();
                    signUp.setUrl(item.getUserLogo());
                    signUp.setNick_name(item.getNickName());
                    mAnchorList.add(0, signUp);
                }
                mView.resetHeaderIcon(mAnchorList, true);
            }
        };
        Map<String, String> params = mView.getAddAnchorParams();
        params.put("userId", mAnchorIds);
        RetrofitService.getInstance().addAnchor(params, subscriber);
    }

    public void httpRequestData() {
        subscriber = new CustomSubscriber<LivingListBean>(mView, mGuid) {
            @Override
            public void onSuccess(LivingListBean bean) {
                if (mView.getCurrentPage() == 1) {
                    mAnchorList.clear();
                }
                mView.getDataSucc(bean.getData());

                List<LivingListBean.DataBean.ArchorListBean> archorList = bean.getData().getArchorList();
                if (archorList != null) {
                    for (LivingListBean.DataBean.ArchorListBean item : archorList) {
                        ActivityDetailBean.DataBean.SignUp signUp = new ActivityDetailBean.DataBean.SignUp();
                        signUp.setUrl(item.getUserLogo());
                        signUp.setNick_name(item.getNickName());
                        mAnchorList.add(signUp);
                    }
                }

                mView.resetHeaderIcon(mAnchorList, false);

            }

            @Override
            public void onError(ApiException ex) {
                super.onError(ex);
                mView.requestDataError();
            }
        };
        RetrofitService.getInstance().listImages(mView.getParams(), subscriber);
    }

    public void httpShareUrl(final String type) {
        subscriber = new CustomSubscriber<ReadMsgModel>(mView, mGuid) {
            @Override
            public void onSuccess(ReadMsgModel bean) {
                if (!TextUtils.isEmpty(bean.getData())) {
                    mView.getShareUrlSucc(type, bean.getData());
                }
            }
        };

        switch (type) {
            case TYPE_H5:
                RetrofitService.getInstance().liveBroadcast(mView.getAddAnchorParams(), subscriber);
                break;
            case TYPE_QRCODE:
                RetrofitService.getInstance().liveBroadcastQrCode(mView.getAddAnchorParams(), subscriber);
                break;
        }
    }

    public void httpAddSeeCount(String activityId) {
        subscriber = new CustomSubscriber<BaseRespBean>(null,null) {
            @Override
            public void onSuccess(BaseRespBean o) {

            }
        };
        Map<String,String> params = new HashMap<>();
        params.put("id",activityId);
        RetrofitService.getInstance().addSeeCount(params,subscriber);
    }
}
