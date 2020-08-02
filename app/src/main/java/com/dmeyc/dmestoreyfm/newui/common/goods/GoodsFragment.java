package com.dmeyc.dmestoreyfm.newui.common.goods;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dmeyc.dmestoreyfm.adapter.GoodsAdapter;
import com.dmeyc.dmestoreyfm.bean.response.GoodsBean;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.event.RefreshEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseRefreshFragment;
import com.dmeyc.dmestoreyfm.newui.home.goodsdetail.CourseDetailActivity;
import com.dmeyc.dmestoreyfm.newui.home.goodsdetail.GoodsDetailActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

/**
 * create by cxg on 2019/12/9
 */
public class GoodsFragment extends BaseRefreshFragment<IGoodsView, GoodsPresenter, GoodsBean.DataBean, GoodsAdapter> implements IGoodsView {

    public static final String TYPE_GOODS = "1";
    public static final String TYPE_COURSE = "2";
    public static final String TYPE_GOODS_MINE = "3";
    public static final String TYPE_COURSE_MINE = "4";
    public static final String TYPE_GOODS_HOME_MINE = "5";
    public static final String TYPE_COURSE_HOME_MINE = "6";


    private String mType;
    private String mSearchStr;

    private boolean needRefresh = false;

    public static GoodsFragment newInstance(String typeFrom) {
        GoodsFragment fragment = new GoodsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ExtraKey.TYPE_FROM, typeFrom);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected GoodsAdapter getAdapter() {
        return new GoodsAdapter();
    }

    @Override
    protected GoodsPresenter createPresenter() {
        return new GoodsPresenter();
    }

    @Override
    protected boolean enableLoadMore() {
        return false;
    }

    @Override
    protected boolean enableRefresh() {
        return true;
    }

    @Override
    protected boolean enableEventBus() {
        return TYPE_GOODS_HOME_MINE.equals(mType) || TYPE_COURSE_HOME_MINE.equals(mType);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mType = getArguments().getString(ExtraKey.TYPE_FROM);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void curFragmentVisible() {
        super.curFragmentVisible();
        if (needRefresh) {
            needRefresh = false;
            mPageNo = 1;
            requestData();
        }
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        if (TYPE_GOODS_MINE.equals(mType) || TYPE_COURSE_MINE.equals(mType)) {
            // 懒加载方法不执行，手动调用下
            mPresenter.httpRequestData(mType);
        }
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (mType) {
                    case TYPE_GOODS:
                    case TYPE_GOODS_MINE:
                    case TYPE_GOODS_HOME_MINE:
                        GoodsDetailActivity.newInstance(getContext(), mData.get(position).getId());
                        break;
                    case TYPE_COURSE:
                    case TYPE_COURSE_MINE:
                    case TYPE_COURSE_HOME_MINE:
                        CourseDetailActivity.newInstance(getContext(), mData.get(position).getId());
                        break;
                }

            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.httpRequestData(mType);
    }

    @Override
    public void requestData() {
        mPresenter.httpRequestData(mType);
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();

        switch (mType) {
            case TYPE_GOODS:
            case TYPE_GOODS_MINE:
            case TYPE_GOODS_HOME_MINE:
                params.put("type", "1");
                break;
            case TYPE_COURSE:
            case TYPE_COURSE_MINE:
            case TYPE_COURSE_HOME_MINE:
                params.put("type", "2");
                break;
        }
        if (!TextUtils.isEmpty(mSearchStr)) {
            params.put("keyword", mSearchStr);
        }
        return params;
    }

    public void doSearch(String searchString, boolean request) {
        mSearchStr = searchString;
        mPageNo = 1;
        if (request) {
            mPresenter.httpRequestData(mType);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void releaseSuccess(RefreshEvent.Release event) {
        switch (event.mType) {

            case RefreshEvent.Release.TYPE_GOODS:
                if (TYPE_GOODS_HOME_MINE.equals(mType)) {
                    needRefresh = true;
                }
                break;
            case RefreshEvent.Release.TYPE_COURSE:
                if (TYPE_COURSE_HOME_MINE.equals(mType)) {
                    needRefresh = true;
                }
                break;
        }
    }
}
