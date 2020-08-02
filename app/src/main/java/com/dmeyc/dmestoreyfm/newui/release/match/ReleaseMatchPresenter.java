package com.dmeyc.dmestoreyfm.newui.release.match;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.bean.ProjectTypeBean;
import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;
import com.dmeyc.dmestoreyfm.bean.response.UploadSingleImageBean;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;

import java.util.HashMap;
import java.util.Map;

/**
 * create by cxg on 2019/11/30
 */
public class ReleaseMatchPresenter extends BasePresenter<IReleaseMatchView> {
    private String imgUrl;
    public void getMatchType() {
        subscriber = new CustomSubscriber<ProjectTypeBean>(mView,mGuid) {
            @Override
            public void onSuccess(ProjectTypeBean bean) {
                if(bean.getData()!=null){
                    mView.getBandListSucc(bean.getData());
                }
            }
        };
        Map<String,String> params = new HashMap<>();
        params.put("key","PROJECT_TYPE");
        RetrofitService.getInstance().getDicItem(params,subscriber);
    }
    public void uploadImage(String filePath) {
        subscriber = new CustomSubscriber<UploadSingleImageBean>(mView, mGuid) {
            @Override
            public void onSuccess(UploadSingleImageBean bean) {
                imgUrl = bean.getData();
                httpRequestData();
            }
        };
        RetrofitService.getInstance().uploadImage(filePath, subscriber);
    }
    private void httpRequestData() {
        subscriber = new CustomSubscriber<BaseRespBean>(mView, mGuid) {
            @Override
            public void onSuccess(BaseRespBean bean) {
                mView.httpRequestDataSucc();
            }
        };
        Map<String, String> params = mView.getParams();
        params.put("sports_poster", imgUrl);
        RetrofitService.getInstance().releaseMatch(params, subscriber);
    }
}
