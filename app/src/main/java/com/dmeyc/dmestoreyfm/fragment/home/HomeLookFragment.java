package com.dmeyc.dmestoreyfm.fragment.home;

import android.view.View;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.LookAdapter;
import com.dmeyc.dmestoreyfm.base.BaseRefreshFragment;
import com.dmeyc.dmestoreyfm.bean.LookBean;
import com.dmeyc.dmestoreyfm.bean.common.ReviewsBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.utils.GsonTools;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by jockie on 2017/11/3
 * Email:jockie911@gmail.com
 */

public class HomeLookFragment extends BaseRefreshFragment<LookBean,LookAdapter> {

    @Override
    protected LookAdapter getAdapter() {
        return new LookAdapter(getActivity(), R.layout.item_rv_home_look, new ArrayList<ReviewsBean>());
    }

    @Override
    protected LookBean parseData(String string) {
        return GsonTools.changeGsonToBean(string,LookBean.class);
    }

    @Override
    protected void setData(LookBean lookBean, boolean isRefresh) {
        adapter.addData(lookBean.getData().getReviews(),isRefresh);
    }

    @Override
    protected String getUrl() {
        return Constant.API.LOOK;
    }

    @Override
    protected Map<String, Object> getParamMap() {
        return new ParamMap.Build().build();
    }

    @Override
    protected void initData(View view) {

    }
}
