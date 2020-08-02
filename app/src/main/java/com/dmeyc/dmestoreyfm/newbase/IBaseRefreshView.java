package com.dmeyc.dmestoreyfm.newbase;

import java.util.List;
import java.util.Map;

/**
 * create by cxg on 2019/11/29
 */
public interface IBaseRefreshView<M>  extends IBaseView {
    // 请求数据
    void requestData();
    Map<String,String> getParams();
    void getDataListSucc(List<M> datas);
    void onCloseRefresh();
}
