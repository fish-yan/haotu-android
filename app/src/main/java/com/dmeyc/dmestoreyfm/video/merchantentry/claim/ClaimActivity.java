package com.dmeyc.dmestoreyfm.video.merchantentry.claim;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.dialog.YFMScreenCityDialog;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.DataClass;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.video.merchantentry.createstore.CreatStoreActivity;
import com.dmeyc.dmestoreyfm.wedgit.PullRecyclerView;
import com.tamic.novate.Throwable;
import com.tencent.liteav.demo.videoediter.eventbus.EventVideoBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class ClaimActivity extends BaseActivity {

    public static void newIntent(Activity activity, String business_token) {
        Intent intent = new Intent(activity, ClaimActivity.class);
        intent.putExtra("business_token", business_token);
        activity.startActivity(intent);
    }

    private String business_token;

    @Bind(R.id.tv_title)
    TextView tv_title;

    @Bind(R.id.tv_address)
    TextView tv_address; // 城市名

    YFMScreenCityDialog dialog;

    @OnClick(R.id.ll_address_choice)
    void onCityChoiceClick() {
        //城市切换
        dialog  = new YFMScreenCityDialog(ClaimActivity.this, R.style.dialog_style_right);
        dialog.onSave(new YFMScreenCityDialog.SaveClickLisenter() {
            @Override
            public void selectCity(String city) {
                mCity = city+"市";
                tv_address.setText(mCity);
                dialog.dismiss();
                // 搜索
                String content = edit_search_content.getText().toString().trim();
                if(TextUtils.isEmpty(content)){
                    content = "";
                }
                page = 1;
                getListModel(page,content);
            }
        });
        dialog.show();
    }

    @Bind(R.id.edit_search_content)
    EditText edit_search_content; // 搜索输入框

    private int type = 1;

    @OnClick(R.id.tv_to_search)
    void onToSearchClick() {
        // TODO 搜索
        String searchContent = edit_search_content.getText().toString().trim();
        if (TextUtils.isEmpty(searchContent)) {
            ToastUtil.show("请输入搜索内容");
        } else {
            type = 2;
            getListModel(page, searchContent);
        }

    }

    @Bind(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.rv_claim)
    PullRecyclerView mRvClaim;

    private ArrayList<ClaimStoreBean.DataBean> mListData = new ArrayList<>();
    private ClaimStoreAdapter mAdapter;

    @Bind(R.id.ll_no_data)
    LinearLayout ll_no_data; // 空视图数据

    @OnClick(R.id.tv_to_create_store)
    void OnCreateStoreClick() {
        // 创建新门店
        CreatStoreActivity.newIntent(ClaimActivity.this, null, business_token);
    }

    private int page = 1;

    @Override
    protected int getLayoutRes() {
        return R.layout.ac_claim_layout;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(ClaimActivity.this);
        tv_title.setText("商户入驻");
        business_token = getIntent().getStringExtra("business_token");
        initRecyclerView();
        mCity = SPUtils.getStringData(Constant.Config.CURRENT_CITY);
        tv_address.setText(mCity);
    }


    /**
     * 事件响应方法
     * 接收消息
     *
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String key) {
        if ("create_success".equals(key)) {
            // 获取数据成功
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(ClaimActivity.this);
    }

    private String mCity;

    /**
     * 初始化列表控件
     */
    private void initRecyclerView() {
        mAdapter = new ClaimStoreAdapter(ClaimActivity.this, R.layout.item_claim_list_layout, mListData);
        LinearLayoutManager layoutManager = new LinearLayoutManager(ClaimActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvClaim.getRecyclerView().setLayoutManager(layoutManager);
        mRvClaim.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                CreatStoreActivity.newIntent(ClaimActivity.this, mListData.get(position), business_token);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        mRvClaim.setOutSwipeRefreshLayout(mSwipeRefreshLayout);
        //下拉刷新
        mRvClaim.setPullDownRefreshListener(new PullRecyclerView.PullDownRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                if (type == 1) {
                    getListModel(page);
                } else {
                    getListModel(page, edit_search_content.getText().toString().trim());
                }
            }
        });
        //上拉加载
        mRvClaim.setPullUpLoadMoreListener(new PullRecyclerView.PullUpLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                if (type == 1) {
                    getListModel(page);
                } else {
                    getListModel(page, edit_search_content.getText().toString().trim());
                }
            }
        });

        getListModel(page);
    }


    private void getListModel(final int page) {

        RestClient.getYfmNovate(this).post(Constant.API.YFM_BASE_URL + "/api/v2.0/business/listModel", new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("city", mCity)
                .addParams("search", "")
                .addParams("page", page)
                .addParams("size", "20")
                .build(), new DmeycBaseSubscriber<ClaimStoreBean>() {
            @Override
            public void onSuccess(ClaimStoreBean gensign) {
                if (gensign.getCode().equals("200")) {
                    if (page == 1) {
                        mListData.clear();
                    }
                    mRvClaim.setPullDownRefreshCompleted();
                    mRvClaim.setPullDownRefreshCompleted();
                    mListData.addAll(gensign.getData());
                    mAdapter.notifyDataSetChanged();
                    setShowView();
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }

    private void getListModel(final int page, String search) {
        RestClient.getYfmNovate(this).post(Constant.API.YFM_BASE_URL + "/api/v2.0/business/listModel", new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("city", mCity)
                .addParams("search", search)
                .addParams("page", page)
                .addParams("size", "20")
                .build(), new DmeycBaseSubscriber<ClaimStoreBean>() {
            @Override
            public void onSuccess(ClaimStoreBean gensign) {
                if (gensign.getCode().equals("200")) {
                    if (page == 1) {
                        mListData.clear();
                    }
                    mRvClaim.setPullDownRefreshCompleted();
                    mRvClaim.setPullUpLoadMoreCompleted();
                    mListData.addAll(gensign.getData());
                    mAdapter.notifyDataSetChanged();
                    setShowView();
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }

    private void setShowView() {
        if (mListData.size() > 0) {
            ll_no_data.setVisibility(View.GONE);
            mSwipeRefreshLayout.setVisibility(View.VISIBLE);
        } else {
            ll_no_data.setVisibility(View.VISIBLE);
            mSwipeRefreshLayout.setVisibility(View.GONE);
        }
    }

    private class ClaimStoreAdapter extends CommonAdapter<ClaimStoreBean.DataBean> {

        public ClaimStoreAdapter(Context context, int layoutId, List<ClaimStoreBean.DataBean> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, ClaimStoreBean.DataBean claimStoreBean, int position) {
            holder.setText(R.id.tv_business_name, claimStoreBean.getName())
                    .setText(R.id.tv_business_address, claimStoreBean.getAddress());
        }
    }
}
