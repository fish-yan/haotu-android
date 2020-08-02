package com.dmeyc.dmestoreyfm.newui.home.search;

import com.dmeyc.dmestoreyfm.bean.response.SearchHistoryBean;
import com.dmeyc.dmestoreyfm.newbase.IBaseView;

import java.util.List;

/**
 * create by cxg on 2019/12/2
 */
public interface ISearchHistoryView extends IBaseView {
    void httpDataSucc(List<SearchHistoryBean.DataBean> data);
}
