package com.dmeyc.dmestoreyfm.newui.home.search.searchvideo;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dmeyc.dmestoreyfm.adapter.VideoPlayListAdapter;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.newbase.BaseRefreshFragment;
import com.dmeyc.dmestoreyfm.newui.home.video.VideoDetailActivity;
import com.dmeyc.dmestoreyfm.newui.pagerdetail.other.OtherDetailActivity;
import com.dmeyc.dmestoreyfm.utils.DataClass;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.video.index.IndexDynamicBean;

import java.util.HashMap;
import java.util.Map;

/**
 * create by cxg on 2019/12/9
 */
public class SearchVideoFragment extends BaseRefreshFragment<ISearchVideoView, SearchVideoPresenter, IndexDynamicBean.DataBean, VideoPlayListAdapter>implements ISearchVideoView {
    private String mSearchStr;

    @Override
    protected VideoPlayListAdapter getAdapter() {
        return new VideoPlayListAdapter(getContext(), null);
    }

    @Override
    protected SearchVideoPresenter createPresenter() {
        return new SearchVideoPresenter();
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(getContext(), 2);
    }

    @Override
    protected boolean enableRefresh() {
        return true;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                VideoDetailActivity.newInstance(getContext(),mData,position);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.httpRequestData(SearchVideoPresenter.TYPE_VIDEO_SEARCH_LIST);
    }

    @Override
    public void requestData() {
        mPresenter.httpRequestData(SearchVideoPresenter.TYPE_VIDEO_SEARCH_LIST);
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(mPageNo));
        params.put("size", PAGE_SIZE);
        if (TextUtils.isEmpty(DataClass.LocationDistrict)) {
            params.put("sorttype", "1");
        } else {
            params.put("sorttype", "4");
        }

        if (!TextUtils.isEmpty(mSearchStr)) {
            params.put("search", mSearchStr);
        }
        String longitude = SPUtils.getStringData(Constant.Config.LONGTUDE);
        if (TextUtils.isEmpty(longitude)) {
            longitude = "0";
        }
        params.put("longitude", longitude);
        String latitude = SPUtils.getStringData(Constant.Config.LATITUDE);
        if (TextUtils.isEmpty(latitude)) {
            latitude = "0";
        }
        params.put("latitude", latitude);
        params.put("longitude", SPUtils.getStringData(Constant.Config.LONGTUDE));
        params.put("latitude", SPUtils.getStringData(Constant.Config.LATITUDE));
        if (!TextUtils.isEmpty(SPUtils.getStringData(Constant.Config.CURRENT_CITY))) {
            params.put("city", SPUtils.getStringData(Constant.Config.CURRENT_CITY));
        } else {
            SPUtils.savaStringData(Constant.Config.CURRENT_CITY, "上海市");
        }
        return params;
    }

    public void doSearch(String searchString, boolean request) {
        mSearchStr = searchString;
        mPageNo = 1;
        if (request){
        mPresenter.httpRequestData(SearchVideoPresenter.TYPE_VIDEO_SEARCH_LIST);
        }
    }
}
