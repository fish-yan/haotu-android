package com.dmeyc.dmestoreyfm.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.bean.HotDataBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.SearchGoodsActivity;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.wedgit.flowview.AutoFlowLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jockie on 2017/8/31
 * Email:jockie911@gmail.com
 */

public class SearchRecordFragment extends BaseFragment {

    @Bind(R.id.historyFlowLayout)
    AutoFlowLayout<String> historyFlowLayout;
    @Bind(R.id.hotFlowLayout)
    AutoFlowLayout<String> hotFlowLayout;
    @Bind(R.id.ll_history_record)
    LinearLayout llHistoryRecord;


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_search_reord;
    }

    @Override
    protected void initData() {
        RestClient.getNovate(getActivity()).get(Constant.API.HOT_SEARCH, new ParamMap.Build().build(), new DmeycBaseSubscriber<HotDataBean>(getActivity()) {
            @Override
            public void onSuccess(final HotDataBean bean) {
                if(hotFlowLayout.getChildCount() > 0)
                    hotFlowLayout.removeAllViews();
                for (String s : bean.getData()) {
                    View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.item_flowlayout_search, null);
                    TextView textView = (TextView) inflate.findViewById(R.id.item_tv_flowlayout);
                    textView.setText(s);
                    hotFlowLayout.addView(inflate);
                }
                hotFlowLayout.setOnItemClickListener(new AutoFlowLayout.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, View view) {
                        ((SearchGoodsActivity) getActivity()).doSearch(bean.getData().get(position));
                        llHistoryRecord.setVisibility(View.VISIBLE);
                        ((SearchGoodsActivity) getActivity()).setEtSearchContent(bean.getData().get(position));
                        addHistoryRecord(bean.getData().get(position));
                    }
                });

            }
        });

        final List<String> historyDatas = SPUtils.getAppendString("history");
        llHistoryRecord.setVisibility(historyDatas.size() == 0 ? View.GONE : View.VISIBLE);
        for (int i = 0; i < historyDatas.size(); i++) {
            View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.item_flowlayout_search, null);
            TextView textView = (TextView) inflate.findViewById(R.id.item_tv_flowlayout);
            textView.setText(historyDatas.get(i));
            historyFlowLayout.addView(inflate);
        }

        historyFlowLayout.setOnItemClickListener(new AutoFlowLayout.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                TextView textView = (TextView) view.findViewById(R.id.item_tv_flowlayout);
                ((SearchGoodsActivity) getActivity()).setEtSearchContent(textView.getText().toString());
                ((SearchGoodsActivity) getActivity()).doSearch(textView.getText().toString());
            }
        });
    }

    @Override
    protected void initData(View view) {

    }

    @OnClick({R.id.iv_search_delete})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_search_delete:
                historyFlowLayout.removeAllViews();
                SPUtils.savaStringData("history","");
                llHistoryRecord.setVisibility(View.GONE);
                break;
        }
    }

    public void addHistoryRecord(String content) {
        SPUtils.savaAppendString("history",content);
        if(historyFlowLayout != null && historyFlowLayout.getChildCount() != 0)
             historyFlowLayout.removeAllViews();
        List<String> history = SPUtils.getAppendString("history");
        for (String s : history) {
            View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.item_flowlayout_search, null);
            TextView textView = (TextView) inflate.findViewById(R.id.item_tv_flowlayout);
            textView.setText(s);
            if(historyFlowLayout != null)
                historyFlowLayout.addView(inflate);
        }
    }
}
