package com.dmeyc.dmestoreyfm.newui.home.search;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dmeyc.dmestoreyfm.adapter.SearchHistoryAdapter;
import com.dmeyc.dmestoreyfm.adapter.SearchHotAdapter;
import com.dmeyc.dmestoreyfm.bean.response.SearchHistoryBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.constant.SPKey;
import com.dmeyc.dmestoreyfm.dialog.YFMScreenCityDialog;
import com.dmeyc.dmestoreyfm.newbase.BaseMvpActivity;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.newui.home.search.searchdetail.SearchDetailActivity;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.wedgit.SearchTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * create by cxg on 2019/12/2
 */
public class SearchHistoryActivity extends BaseMvpActivity<ISearchHistoryView, SearchHistoryPresenter> implements ISearchHistoryView, SearchTitleView.OnItemClickListener {

    @Bind(R.id.rv_history)
    RecyclerView mRvHistory;
    @Bind(R.id.rv_hot)
    RecyclerView mRvHot;
    @Bind(R.id.searchTitleView)
    SearchTitleView mSearchTitleView;

    @Bind(R.id.tv_hot_title)
    TextView mTtvHotTitle;

    private SearchHistoryAdapter mHistoryAdapter;
    private SearchHotAdapter mHotAdapter;
    private YFMScreenCityDialog mCityDialog;
    private List<String> mHistoryData = new ArrayList<>();

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, SearchHistoryActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected SearchHistoryPresenter createPresenter() {
        return new SearchHistoryPresenter();
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int setContentView() {
        return R.layout.activity_search_history;
    }


    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        mSearchTitleView.setLocationString(SPUtils.getStringData(Constant.Config.CURRENT_CITY));
        mSearchTitleView.setListener(this);
        mRvHistory.setLayoutManager(new LinearLayoutManager(this));
        mHistoryAdapter = new SearchHistoryAdapter();

        mRvHistory.setAdapter(mHistoryAdapter);
        mHistoryAdapter.bindToRecyclerView(mRvHistory);
        mHistoryAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_delete:
                        SPUtils.removeAppendString(SPKey.SEARCH_HISTORY, (String) adapter.getData().get(position));
                        mHistoryData.remove(position);
                        adapter.replaceData(mHistoryData);
                        break;
                }
            }
        });
        mHistoryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                onSearch(mHistoryData.get(position));
            }
        });

        mRvHot.setLayoutManager(new GridLayoutManager(this, 2));
        mHotAdapter = new SearchHotAdapter();

        mRvHot.setAdapter(mHotAdapter);
        mHotAdapter.bindToRecyclerView(mRvHot);

        mHotAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                onSearch(mHotAdapter.getData().get(position).getKeyword());
            }
        });

        mHistoryData = SPUtils.getAppendString(SPKey.SEARCH_HISTORY);
        mHistoryAdapter.replaceData(mHistoryData);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.httpRequestData();
    }

    @Override
    public void onLocationClick() {
        mCityDialog = new YFMScreenCityDialog(this, R.style.dialog_style_right);
        mCityDialog.onSave(new YFMScreenCityDialog.SaveClickLisenter() {
            @Override
            public void selectCity(String city) {
                mSearchTitleView.setLocationString(city);
                mCityDialog.dismiss();
                SPUtils.savaStringData(Constant.Config.CURRENT_CITY, city + "市");
            }
        });
        mCityDialog.show();
    }

    @Override
    public void onSearch(String searchString) {
        SPUtils.savaAppendString(SPKey.SEARCH_HISTORY, searchString);
        mHistoryData = SPUtils.getAppendString(SPKey.SEARCH_HISTORY);
        SearchDetailActivity.newInstance(this, searchString);
    }

    @Override
    public void onCancel() {
        finish();
    }

    @Override
    public void httpDataSucc(List<SearchHistoryBean.DataBean> data) {
        if (data!=null&&data.size()>0){
            mTtvHotTitle.setVisibility(View.VISIBLE);
        }else {
            mTtvHotTitle.setVisibility(View.GONE);
        }
        mHotAdapter.replaceData(data);
    }

}
