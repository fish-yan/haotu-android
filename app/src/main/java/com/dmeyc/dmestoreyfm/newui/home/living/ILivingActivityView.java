package com.dmeyc.dmestoreyfm.newui.home.living;

import com.dmeyc.dmestoreyfm.bean.response.ActivityDetailBean;
import com.dmeyc.dmestoreyfm.bean.response.LivingListBean;
import com.dmeyc.dmestoreyfm.newbase.IBaseView;

import java.util.List;
import java.util.Map;

/**
 * create by cxg on 2019/12/29
 */
public interface ILivingActivityView extends IBaseView {
    Map<String, String> getAddAnchorParams();

    Map<String, String> getParams();

    void getDataSucc(LivingListBean.DataBean data);

    void resetHeaderIcon(List<ActivityDetailBean.DataBean.SignUp> list, boolean b);

    void getBannerSucc(List<String> list);

    void requestDataError();

    void getShareUrlSucc(String type, String data);

    int getCurrentPage();
}
