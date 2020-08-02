package com.dmeyc.dmestoreyfm.photolive;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.api.exception.ApiException;
import com.dmeyc.dmestoreyfm.base.BaseApp;
import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;
import com.dmeyc.dmestoreyfm.bean.response.UploadImageCompressBean;
import com.dmeyc.dmestoreyfm.dialog.LoadingDialog;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;
import com.dmeyc.dmestoreyfm.photolive.mtp.PicInfo;
import com.dmeyc.dmestoreyfm.utils.LoadingUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by cxg on 2019/12/26
 */
public class LivingPresenter extends BasePresenter<ILivingView> {

    private int upLoadPosition = 0;
    private int totalCount = 0;
    private List<UploadImageCompressBean.DataBean> uploadSuccList = new ArrayList<>();

    public void uploadImage1(List<PicInfo> list) {
        mView.beginUpload();

        List<String> filePaths = new ArrayList<>();
        for (PicInfo bean : list) {
            filePaths.add(importfile(bean.getObjectHandler()));
        }
        if (filePaths.size() == 0) {
            ToastUtil.show("数据异常");
            mView.endUpload();
            return;
        }
        upLoadPosition = 0;
        uploadSuccList.clear();
        totalCount = filePaths.size();
        uploadImageLocal(filePaths.get(upLoadPosition), filePaths);
    }

    private void uploadImageLocal(String filePath, final List<String> filePaths) {

        subscriber = new CustomSubscriber<UploadImageCompressBean>(null, null) {
            @Override
            public void onSuccess(UploadImageCompressBean bean) {
                uploadSuccList.add(bean.getData());
                upLoadPosition++;

                if (upLoadPosition < totalCount) {
                    uploadImageLocal(filePaths.get(upLoadPosition), filePaths);
                } else {
                    if (uploadSuccList.size() > 0) {
                        httpUploadData();
                    } else {
                        ToastUtil.show("上传图片失败，请稍后重试");
                        mView.endUpload();
                    }
                }
            }

            @Override
            public void onError(ApiException ex) {
                super.onError(ex);
                mView.endUpload();
            }
        };
        RetrofitService.getInstance().uploadImageNeedCompress(filePath, subscriber);
    }

    private void httpUploadData() {
        subscriber = new CustomSubscriber<BaseRespBean>(null, null) {
            @Override
            public void onSuccess(BaseRespBean o) {
                ToastUtil.show("上传成功");
                mView.upLoadImageSucc();
            }

            @Override
            public void onError(ApiException ex) {
                super.onError(ex);
                mView.endUpload();
            }
        };

        Map<String, String> params = new HashMap<>();

        StringBuffer sb = new StringBuffer();
        for (UploadImageCompressBean.DataBean bean : uploadSuccList) {
            sb.append(bean.getOriginImage()).append("&").append(bean.getZoomImage()).append("&").append(bean.getOriginImageSize()).append(",");
        }
        String images = "";
        if (sb.length() > 0) {
            images = sb.substring(0, sb.length() - 1);
        }

        params.put("activityId", mView.getActivityId());
        params.put("images", images);
        RetrofitService.getInstance().broadcastAdd(params, subscriber);
    }

    StringBuilder filePath = new StringBuilder();

    private String importfile(int handler) {
        filePath.setLength(0);
        filePath.append(BaseApp.getContext().getCacheDir())
                .append(File.separator)
                .append(handler)
                .append(".jpg");
        if (Constant.mtpDevice != null) {
            Constant.mtpDevice.importFile(handler, filePath.toString());
            return filePath.toString();
        }
        return "";
    }
}
