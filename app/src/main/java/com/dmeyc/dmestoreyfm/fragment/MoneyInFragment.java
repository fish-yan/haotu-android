package com.dmeyc.dmestoreyfm.fragment;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.ActionItemAdapter;
import com.dmeyc.dmestoreyfm.adapter.MoneyInAdapter;
import com.dmeyc.dmestoreyfm.adapter.TeachListAdapter;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.base.BaseRefreshFragment;
import com.dmeyc.dmestoreyfm.bean.LookBean;
import com.dmeyc.dmestoreyfm.bean.MoneyManagerBean;
import com.dmeyc.dmestoreyfm.bean.common.ReviewsBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.utils.GsonTools;
import com.dmeyc.dmestoreyfm.utils.SPUtils;

import java.util.ArrayList;
import java.util.Map;

import butterknife.Bind;

@SuppressLint("ValidFragment")
public class MoneyInFragment extends BaseRefreshFragment<MoneyManagerBean,MoneyInAdapter> {
    private MoneyInAdapter lookAdapter;

    int groupid;
    @Bind(R.id.tv_nodata)
    TextView tv_nodata;
    public MoneyInFragment(int groupid){
        this.groupid=groupid;
    }
    @Override
    protected MoneyInAdapter getAdapter() {
        lookAdapter= new MoneyInAdapter(getActivity(), R.layout.adapter_moneyin,  new ArrayList<MoneyManagerBean.DataBean>());
        return lookAdapter;
    }

    @Override
    protected MoneyManagerBean parseData(String result) {
        return GsonTools.changeGsonToBean(result,MoneyManagerBean.class);
    }

    @Override
    protected void setData(MoneyManagerBean lookBean, boolean isRefresh) {
        if(lookBean.getData().size()==0){
            tv_nodata.setVisibility(View.VISIBLE);
            mRecycleView.setVisibility(View.GONE);
        }
        adapter.addData(lookBean.getData(),isRefresh);
    }

    @Override
    protected String getUrl() {
        return Constant.API.YFM_ACCOUNTDETAIL;
    }

    @Override
    protected Map<String, Object> getParamMap() {
        return new ParamMap.Build().addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("group_id",groupid)
                 .build();
//        return new ParamMap.Build().addParams("user_token", "60bd8520c13f4377847cf3fa04e0e4bd  ")
//                .addParams("group_id",groupid)
//                .build();
    }

    @Override
    protected void initData(View view) {

    }
}
