package com.dmeyc.dmestoreyfm.newui.menu.wallet.addbankcard;

import com.dmeyc.dmestoreyfm.api.CustomSubscriber;
import com.dmeyc.dmestoreyfm.api.RetrofitService;
import com.dmeyc.dmestoreyfm.bean.ProjectTypeBean;
import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;

import java.util.HashMap;
import java.util.Map;

/**
 * create by cxg on 2019/11/25
 */
public class AddBankCardPresenter extends BasePresenter<IAddBankCardView> {
    public void addBankCard() {
        subscriber = new CustomSubscriber<BaseRespBean>(mView,mGuid) {
            @Override
            public void onSuccess(BaseRespBean bean) {
                mView.addBankCardSucc();
            }
        };

        RetrofitService.getInstance().addUserBankCard(mView.getParams(),subscriber);
    }

    public void getCode(){
        subscriber = new CustomSubscriber<BaseRespBean>(mView,mGuid) {
            @Override
            public void onSuccess(BaseRespBean bean) {
                mView.getCodeSucc();
            }
        };

        RetrofitService.getInstance().getCode(mView.getCodeParams(),subscriber);
    }

    public void httpBankList() {
        subscriber = new CustomSubscriber<ProjectTypeBean>(mView,mGuid) {
            @Override
            public void onSuccess(ProjectTypeBean bean) {
                if(bean.getData()!=null){
                    mView.getBandListSucc(bean.getData());
                }
            }
        };
        Map<String,String> params = new HashMap<>();
        params.put("key","BANK_CODE");
        RetrofitService.getInstance().getDicItem(params,subscriber);
    }
}
