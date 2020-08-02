package com.dmeyc.dmestoreyfm.newui.release.video;

import android.text.TextUtils;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.api.exception.ApiException;
import com.dmeyc.dmestoreyfm.base.BaseApp;
import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;
import com.dmeyc.dmestoreyfm.bean.response.UploadImageCompressBean;
import com.dmeyc.dmestoreyfm.bean.response.UploadSingleImageBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;
import com.dmeyc.dmestoreyfm.utils.DataClass;
import com.dmeyc.dmestoreyfm.utils.LoadingUtils;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.video.releasedynamic.GenSignBean;
import com.dmeyc.dmestoreyfm.video.releasedynamic.ReleasedynamicActivity;
import com.dmeyc.dmestoreyfm.video.videoupload.TXUGCPublish;
import com.dmeyc.dmestoreyfm.video.videoupload.TXUGCPublishTypeDef;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by cxg on 2019/11/30
 */
public class ReleaseVideoPresenter extends BasePresenter<IReleaseVideoView> {

    private int upLoadPosition = 0;
    private int totalCount = 0;
    private int mType;// 上传类型：图片、视频
    private List<String> mImageUrls = new ArrayList<>();//图片url地址

    private TXUGCPublish mVideoPublish;
    private TXUGCPublishTypeDef.TXPublishResult mResult;

    public void setType(int type) {
        mType = type;
    }

    public void upLoadImages(ArrayList<String> fileNames) {
        upLoadPosition = 0;
        totalCount = fileNames.size();
        mImageUrls.clear();
        String fileName = fileNames.get(upLoadPosition);
        uploadImage(fileName, fileNames);
    }

    private void uploadImage(String filePath, final ArrayList<String> fileNames) {
        mView.showProgress();
        subscriber = new CustomSubscriber<UploadImageCompressBean>(null, null) {
            @Override
            public void onSuccess(UploadImageCompressBean bean) {
                upLoadPosition++;
                mImageUrls.add(bean.getData().getOriginImage() + "@" + bean.getData().getZoomImage());
                if (upLoadPosition < totalCount) {
                    uploadImage(fileNames.get(upLoadPosition), fileNames);
                } else {
                    if (mImageUrls.size() > 0) {
                        publishDynamic();
                    } else {
                        mView.hideProgress();
                        ToastUtil.show("获取图片失败，请稍后重试");
                    }
                }
            }

            @Override
            public void onError(ApiException ex) {
                super.onError(ex);
                mView.hideProgress();
                upLoadPosition = 0;
                mImageUrls.clear();
            }
        };
        RetrofitService.getInstance().uploadImageNeedCompress(filePath, subscriber);
    }

    /**
     * 发布
     */
    private void publishDynamic() {
        subscriber = new CustomSubscriber<BaseRespBean>(null, null) {
            @Override
            public void onSuccess(BaseRespBean bean) {
                mView.publishDynamicSucc();
                mView.hideProgress();
            }

            @Override
            public void onError(ApiException ex) {
                super.onError(ex);
                mView.hideProgress();
            }
        };
        Map<String, String> params = mView.getParams();
        switch (mType) {
            case ReleaseVideoActivity.TYPE_OF_PIC:
                params.put("type", "2");
                params.put("imageUrls", getImageUrls());
                break;
            case ReleaseVideoActivity.TYPE_OF_VIDEO:
                params.put("type", "1");
                params.put("url", mResult.videoURL);
                params.put("coverUrl", mResult.coverURL);
                params.put("tencentVideoId", mResult.videoId);
                break;
            default:
        }
        if (!TextUtils.isEmpty(DataClass.LocationProvince)) {
            params.put("province", DataClass.LocationProvince);
        }
        if (!TextUtils.isEmpty(DataClass.LocationCity)) {
            params.put("city", DataClass.LocationCity);
        }
        if (!TextUtils.isEmpty(DataClass.LocationDistrict)) {
            params.put("area", DataClass.LocationDistrict);
        }
        if (!TextUtils.isEmpty(DataClass.LocationAddress)) {
            params.put("address", DataClass.LocationAddress);
        }
        if (!TextUtils.isEmpty(SPUtils.getStringData(Constant.Config.LONGTUDE))) {
            params.put("longitude", SPUtils.getStringData(Constant.Config.LONGTUDE));
        }
        if (!TextUtils.isEmpty(SPUtils.getStringData(Constant.Config.LATITUDE))) {
            params.put("latitude", SPUtils.getStringData(Constant.Config.LATITUDE));
        }

        RetrofitService.getInstance().publishDynamic(params, subscriber);
    }

    /**
     * 上传视频到腾讯云服务器
     */
    private void uploadVideoToTencent(String sign) {
//        LoadingUtils.showProgressDialog(ReleasedynamicActivity.this, "发布中...");
        mVideoPublish = new TXUGCPublish(BaseApp.getContext().getApplicationContext());
        TXUGCPublishTypeDef.TXPublishParam param = mView.getTXPublishParam();
        param.signature = sign;                        // 需要填写第四步中计算的上传签名
        mVideoPublish.publishVideo(param);
        mVideoPublish.setListener(new TXUGCPublishTypeDef.ITXVideoPublishListener() {
            @Override
            public void onPublishProgress(long uploadBytes, long totalBytes) {
            }

            @Override
            public void onPublishComplete(TXUGCPublishTypeDef.TXPublishResult result) {
                if (!TextUtils.isEmpty(result.videoURL)) {
                    mResult = result;
                    publishDynamic();
//                   publishDynamic(" http://1300375154.vod2.myqcloud.com/8b1f76ffvodcq1300375154/2b3960825285890794779082739/W2tTQjJ3aFIA.mp4", "http://1300375154.vod2.myqcloud.com/8b1f76ffvodcq1300375154/2b3960825285890794779082739/5285890794779082740.jpg", 1);
                } else {
                    mView.hideProgress();
                    ToastUtil.show(result.descMsg);
                }
            }
        });
    }

    /**
     * 获取签名
     */
    public void getGenSign() {
        mView.showProgress();
        subscriber = new CustomSubscriber<GenSignBean>(null, null) {
            @Override
            public void onSuccess(GenSignBean bean) {
                if (!TextUtils.isEmpty(bean.getData())) {
                    uploadVideoToTencent(bean.getData());
                }else {
                    mView.hideProgress();
                }
            }

            @Override
            public void onError(ApiException ex) {
                super.onError(ex);
                mView.hideProgress();
            }
        };

        RetrofitService.getInstance().getGenSign(new HashMap<String, String>(), subscriber);


    }


    private String getImageUrls() {
        if (mImageUrls.size() == 0) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (String imageUrl : mImageUrls) {
            sb.append(imageUrl).append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }

}
