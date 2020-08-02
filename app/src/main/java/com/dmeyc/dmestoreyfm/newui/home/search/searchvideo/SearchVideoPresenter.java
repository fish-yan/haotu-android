package com.dmeyc.dmestoreyfm.newui.home.search.searchvideo;

import com.dmeyc.dmestoreyfm.adapter.VideoPlayListAdapter;
import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.api.exception.ApiException;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;
import com.dmeyc.dmestoreyfm.newui.pagerdetail.video.PersonVideoFragment;
import com.dmeyc.dmestoreyfm.video.index.IndexDynamicBean;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * create by cxg on 2019/12/9
 */
public class SearchVideoPresenter extends BasePresenter<ISearchVideoView> {

    public static final String TYPE_VIDEO_SEARCH_LIST = "TYPE_VIDEO_SEARCH_LIST";
    public static final String TYPE_VIDEO_LIST = "TYPE_VIDEO_LIST";
    public static final String TYPE_VIDEO_LIKE = "TYPE_VIDEO_LIKE";
    public static final String TYPE_IMAGE_LIVE = "TYPE_IMAGE_LIVE";

    public void httpRequestData(final String type) {
        subscriber = new CustomSubscriber<IndexDynamicBean>(mView, mGuid) {
            @Override
            public void onSuccess(IndexDynamicBean bean) {
                Logger.d(bean);
                List<IndexDynamicBean.DataBean> data = bean.getData();
                if (data != null) {
                    for (int i = 0; i < data.size(); i++) {
                        switch (type) {
                            case TYPE_VIDEO_SEARCH_LIST:
                                data.get(i).setItemType(Constant.AdapterItemType.TYPE_SEARCH_VIDEO);
                                break;
                            case TYPE_VIDEO_LIST:
                                data.get(i).setItemType(Constant.AdapterItemType.TYPE_ME_VIDEO);
                                break;
                            case TYPE_VIDEO_LIKE:
                                data.get(i).setItemType(Constant.AdapterItemType.TYPE_ME_VIDEO_LIKE);
                                break;
                            case TYPE_IMAGE_LIVE:
                                data.get(i).setItemType(Constant.AdapterItemType.TYPE_ME_VIDEO_LIVE);
                                break;
                            default:
                        }

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

        switch (type) {
            case TYPE_VIDEO_SEARCH_LIST:
                RetrofitService.getInstance().getListVideo(mView.getParams(), subscriber);
                break;
            case TYPE_VIDEO_LIST:
                RetrofitService.getInstance().listMyPublishVideo(mView.getParams(), subscriber);
                break;
            case TYPE_VIDEO_LIKE:
                RetrofitService.getInstance().listMyLikedVideo(mView.getParams(), subscriber);
                break;
//            case TYPE_IMAGE_LIVE:
//                RetrofitService.getInstance().listUserVideo(mView.getParams(), subscriber);
//                break;
            default:
        }

    }
}
