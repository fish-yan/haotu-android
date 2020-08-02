package com.dmeyc.dmestoreyfm.newbase;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * create by cxg on 2019/11/29
 * M 为列表的bean
 */
public abstract class BaseRefreshFragment<V extends IBaseRefreshView, P extends BasePresenter<V>, M, A extends BaseQuickAdapter<M, ?>> extends BaseMVPFragment<V, P> implements IBaseRefreshView, OnRefreshListener, OnLoadmoreListener, TextView.OnEditorActionListener {

    private SmartRefreshLayout mSmartRefresh;
    protected RecyclerView mRecyclerView;
    protected A mAdapter;

    protected String PAGE_SIZE;
    protected int mPageNo = 1;

    private FrameLayout mFlSearch;
    protected EditText mEtSearch;
    private InputMethodManager mImm;

    protected List<M> mData = new ArrayList<>();

    @Override
    protected int setContentView() {
        return R.layout.fragment_base_refresh;
    }

    @Override
    protected void initViews(View view) {
        if (needSearch()) {
            mEtSearch = view.findViewById(R.id.et_base_search);
            mFlSearch = view.findViewById(R.id.fl_search);
            mFlSearch.setVisibility(View.VISIBLE);
            mImm = (InputMethodManager) mEtSearch.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            mEtSearch.setOnEditorActionListener(this);
        }

        mSmartRefresh = view.findViewById(R.id.srl_base);
        mRecyclerView = view.findViewById(R.id.rv_base);
        PAGE_SIZE = setPageSize();
        mRecyclerView.setLayoutManager(getLayoutManager());
        mAdapter = getAdapter();

        mRecyclerView.setAdapter(mAdapter);
        mAdapter.bindToRecyclerView(mRecyclerView);
        if (needEmptyView()) {
            mAdapter.setEmptyView(setEmptyView());
        }
        mSmartRefresh.setEnableRefresh(enableRefresh());
        mSmartRefresh.setEnableLoadmore(false);
        mSmartRefresh.setOnRefreshListener(this);
        mSmartRefresh.setOnLoadmoreListener(this);
    }

    protected String setPageSize() {
        return "10";
    }

    protected abstract A getAdapter();

    @Override
    public void getDataListSucc(List data) {

        onCloseRefresh();
        if (data == null) {
            data = new ArrayList<M>();
        }
        //不需要分页
        if (!enableLoadMore()) {

            mData.clear();
            mData.addAll(data);
            mAdapter.replaceData(data);
            return;
        }
        if (data.size() < Integer.valueOf(PAGE_SIZE)) {
            mSmartRefresh.setEnableLoadmore(false);
        } else {
            mSmartRefresh.setEnableLoadmore(true);
        }
        if (mPageNo == 1) {
            mData.clear();
            mAdapter.replaceData(data);
        } else {
            mAdapter.addData(data);
        }
        mData.addAll(data);

        mPageNo++;
    }

    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    protected boolean needEmptyView() {
        return false;
    }

    protected boolean enableRefresh() {
        return false;
    }

    protected boolean enableLoadMore() {
        return true;
    }

    protected boolean needSearch() {
        return false;
    }

    protected void onSearch(String searchStr) {
    }

    protected int setEmptyView() {
        return R.layout.view_empty_data;
    }


    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        requestData();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        mPageNo = 1;
        requestData();
    }

    @Override
    public void onCloseRefresh() {
        if (mSmartRefresh.isRefreshing()) {
            mSmartRefresh.finishRefresh(true);
        }
        if (mSmartRefresh.isLoading()) {
            mSmartRefresh.finishLoadmore();
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        switch (actionId) {
            case EditorInfo.IME_ACTION_SEARCH:
                if (TextUtils.isEmpty((mEtSearch.getText().toString().trim()))) {
                    ToastUtil.show("请输入搜索内容");
                    return true;
                }
                if (mImm.isActive()) {
                    mImm.hideSoftInputFromWindow(mEtSearch.getWindowToken(), 0);
                }
                mPageNo = 1;
                onSearch(mEtSearch.getText().toString());
                break;
            default:
                break;
        }
        return true;
    }
}
