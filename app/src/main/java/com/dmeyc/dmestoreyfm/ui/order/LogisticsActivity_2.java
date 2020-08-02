package com.dmeyc.dmestoreyfm.ui.order;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.LogisticBean;
import com.dmeyc.dmestoreyfm.bean.OrderDetailBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.order.section.LogisticsSection;
import com.dmeyc.dmestoreyfm.wedgit.sectioned.SectionedRecyclerViewAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class LogisticsActivity_2 extends BaseActivity implements OnRefreshListener {

    @Bind(R.id.main_recyclerView)
    RecyclerView contentRecycler;
    @Bind(R.id.smartRl)
    SmartRefreshLayout mSmartRefresh;

    private List<LogisticBean.DataBean> list = new ArrayList();
    private List<OrderDetailBean> list_head = new ArrayList();
    private SectionedRecyclerViewAdapter sectionedRecyclerViewAdapter;
    private boolean isFirst = true;//第一次下载数据
    private LogisticsSection logisticsSection;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_logistics;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        tvTitle.setText("物流信息");
        initRecyclerView();
        RestClient.getNovate(this).get(Constant.API.ORDER_DETAIL, new ParamMap.Build()
                .addParams("orderIds",  getIntent().getIntExtra("orderIds",0))
                .build(), new DmeycBaseSubscriber<OrderDetailBean>(this) {
            @Override
            public void onSuccess(OrderDetailBean bean) {
                list_head.clear();
                list_head.add(bean);
                getDataBean();

            }
        });

    }

    private void getDataBean() {
        RestClient.getNovate(this).get(Constant.API.LOGISTIC, new ParamMap.Build()
                .addParams("logisticCode", getIntent().getStringExtra("deliveryCode")) //快遞公司編碼
                .addParams("shipperCode", getIntent().getStringExtra("deliveryNumber"))  //快遞單號
                .build(), new DmeycBaseSubscriber<LogisticBean>(this) {
            @Override
            public void onSuccess(LogisticBean bean) {
                if (bean.getData().isSuccess()) {
                    closeRefresh();
                    initSection(bean.getData());
                }
            }
        });
    }

    private void initSection(LogisticBean.DataBean data) {
        list.clear();
        list.add(data);
        if (isFirst) {
            if (null != list) {
                logisticsSection = new LogisticsSection(LogisticsActivity_2.this, sectionedRecyclerViewAdapter);
                logisticsSection.setData(list,list_head);
            }
        } else {
            sectionedRecyclerViewAdapter.removeAllSections();
            logisticsSection.setData(list,list_head);
        }
        sectionedRecyclerViewAdapter.addSection(logisticsSection);
        isFirst = false;
    }

    private void initRecyclerView() {
        LinearLayoutManager myLayoutManager = new LinearLayoutManager(LogisticsActivity_2.this);
        contentRecycler.setLayoutManager(myLayoutManager);
        sectionedRecyclerViewAdapter = new SectionedRecyclerViewAdapter();
        contentRecycler.setAdapter(sectionedRecyclerViewAdapter);
        mSmartRefresh.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        getDataBean();
    }

    @Override
    public void onResume() {
        super.onResume();
        getDataBean();
    }

    /**
     * 关闭刷新
     */
    public void closeRefresh() {
        if (mSmartRefresh.isRefreshing())
            mSmartRefresh.finishRefresh();
    }
}
