package com.dmeyc.dmestoreyfm.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.response.SearchHistoryBean;

import java.util.List;

/**
 * create by cxg on 2019/12/2
 */
public class SearchHotAdapter extends BaseQuickAdapter<SearchHistoryBean.DataBean, BaseViewHolder> {

    public SearchHotAdapter() {
        super(R.layout.adapter_search_hot);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchHistoryBean.DataBean item) {
        if (helper == null || item == null) {
            return;
        }
        helper.setText(R.id.tv_hot_title, item.getKeyword());
        TextView tvType = (TextView) helper.getView(R.id.tv_type);
        switch (item.getType()) {
            case "1":
                tvType.setVisibility(View.VISIBLE);
                tvType.setText("新");
                tvType.setBackgroundResource(R.drawable.shape_blue_r2);
                break;
            case "2":

                tvType.setVisibility(View.VISIBLE);
                tvType.setText("热");
                tvType.setBackgroundResource(R.drawable.shape_red_r2);
                break;
            case "3":

                tvType.setVisibility(View.VISIBLE);
                tvType.setText("爆");
                tvType.setBackgroundResource(R.drawable.shape_dark_red_r2);
                break;
            case "4":

                tvType.setVisibility(View.GONE);
                break;
            default:

                tvType.setVisibility(View.GONE);
                break;
        }
    }
}
