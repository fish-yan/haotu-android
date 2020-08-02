package com.dmeyc.dmestoreyfm.ui;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.LogisticBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.wedgit.logistics.LogisticsData;
import com.dmeyc.dmestoreyfm.wedgit.logistics.NodeProgressAdapter;
import com.dmeyc.dmestoreyfm.wedgit.logistics.NodeProgressView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class LogisticsActivity extends BaseActivity {

    @Bind(R.id.npv_NodeProgressView)
    NodeProgressView nodeProgressView;
    @Override
    protected int getLayoutRes() {
        return R.layout.ac_logistics;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        tvTitle.setText("物流信息");

        RestClient.getNovate(this).get(Constant.API.LOGISTIC, new ParamMap.Build()
                .addParams("logisticCode", getIntent().getStringExtra("deliveryCode")) //快遞公司編碼
                .addParams("shipperCode", getIntent().getStringExtra("deliveryNumber"))  //快遞單號
                .build(), new DmeycBaseSubscriber<LogisticBean>(this) {
            @Override
            public void onSuccess(LogisticBean bean) {
                if(bean.getData().isSuccess()){
                    List<LogisticBean.DataBean.TracesBean> traces = bean.getData().getTraces();
                    final List<LogisticsData> logisticsDatas = new ArrayList<>();
                    for (LogisticBean.DataBean.TracesBean trace : traces) {
                        logisticsDatas.add(new LogisticsData().setTime(trace.getAcceptTime()).setContext(trace.getAcceptStation()));
                    }
                    //TODO nodeProgressView 不刷新
                    nodeProgressView.setNodeProgressAdapter(new NodeProgressAdapter() {

                        @Override
                        public int getCount() {
                            return logisticsDatas.size();
                        }

                        @Override
                        public List<LogisticsData> getData() {
                            return logisticsDatas;
                        }
                    });
                }
            }
        });
    }
}
