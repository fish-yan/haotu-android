package com.dmeyc.dmestoreyfm.newui.menu.club.clubmember;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.api.exception.ApiException;
import com.dmeyc.dmestoreyfm.bean.MemberListBean;
import com.dmeyc.dmestoreyfm.bean.NewMemberListBean;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * create by cxg on 2019/12/23
 */
public class MemberPresenter extends BasePresenter<IMemberView> {
    public void httpMembersData() {
        subscriber = new CustomSubscriber<NewMemberListBean>(mView, mGuid) {
            @Override
            public void onSuccess(NewMemberListBean bean) {
                List<NewMemberListBean.DataBean.ListBean> list = new ArrayList<>();
                list.addAll(bean.getData().getManagerList());
                list.addAll(bean.getData().getNormalList());
                mView.getDataListSucc(list);
            }

            @Override
            public void onError(ApiException ex) {
                super.onError(ex);
                mView.onCloseRefresh();
            }
        };
        RetrofitService.getInstance().httpMembersData(mView.getParams(), subscriber);
    }
}
