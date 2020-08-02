package com.dmeyc.dmestoreyfm.newui.home.search.searchuser;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.api.exception.ApiException;
import com.dmeyc.dmestoreyfm.bean.response.UserListBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * create by cxg on 2019/12/9
 */
public class SearchUserPresenter extends BasePresenter<ISearchUserView> {
    public static final int TYPE_SEARCH = 1;
    public static final int TYPE_MINE = 2;
    public static final int TYPE_FUNS = 3;
    public static final int TYPE_SEARCH_ANCHOR = 4;

    public void httpRequestData(final int type) {
        subscriber = new CustomSubscriber<UserListBean>(mView, mGuid) {
            @Override
            public void onSuccess(UserListBean bean) {
                List<UserListBean.DataBean> data = bean.getData();
                List<UserListBean.DataBean> dataTemp = new ArrayList<>();
                switch (type) {
                    case TYPE_FUNS:
                        if (data != null) {
                            for (UserListBean.DataBean item : data) {
                                if ("1".equals(item.getStatus()) || "3".equals(item.getStatus())) {
                                    dataTemp.add(item);
                                }
                            }
                        }
                        break;

                    case TYPE_SEARCH_ANCHOR:
                        if (data != null) {
                            for (UserListBean.DataBean item : data) {
                                item.setLocalType(Constant.AdapterItemType.TYPE_SEARCH_AUCHOR);
                                item.setLocalRightString("选定");
                                dataTemp.add(item);
                            }
                        }
                        break;
                    default:
                        dataTemp = data;
                        break;
                }

                mView.getDataListSucc(dataTemp);
            }

            @Override
            public void onError(ApiException ex) {
                super.onError(ex);
                mView.onCloseRefresh();
            }
        };

        switch (type) {
            case TYPE_SEARCH:
            case TYPE_SEARCH_ANCHOR:
                RetrofitService.getInstance().listArchorList(mView.getParams(), subscriber);

                break;
            case TYPE_MINE:
            case TYPE_FUNS:

                RetrofitService.getInstance().listFollowUser(mView.getParams(), subscriber);
                break;
        }
    }
}
