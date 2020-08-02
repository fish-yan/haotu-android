package com.dmeyc.dmestoreyfm.fragment;

import android.content.Intent;
import android.view.View;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.TeachListAdapter;
import com.dmeyc.dmestoreyfm.base.BaseRefreshFragment;
import com.dmeyc.dmestoreyfm.bean.LookBean;
import com.dmeyc.dmestoreyfm.bean.TeachListBean;
import com.dmeyc.dmestoreyfm.bean.common.ReviewsBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.ui.TeachInforActivity;
import com.dmeyc.dmestoreyfm.utils.GsonTools;
import com.dmeyc.dmestoreyfm.utils.SPUtils;

import java.util.ArrayList;
import java.util.Map;

public class TeachFragment extends BaseRefreshFragment<TeachListBean,TeachListAdapter> {


    private TeachListAdapter lookAdapter;
    @Override
    protected TeachListAdapter getAdapter() {
        lookAdapter= new TeachListAdapter(getActivity(), R.layout.adapter_teachitem, new ArrayList<TeachListBean.DataBean>());
        return lookAdapter;
    }
    @Override
    protected TeachListBean parseData(String string)
    {     closeRefresh();
        return GsonTools.changeGsonToBean(string,TeachListBean.class);

    }
    TeachListBean listBean;
    @Override
    protected void setData(TeachListBean lookBean, boolean isRefresh) {
        listBean=lookBean;
        if(lookBean.getData().size()==0){
            mSmartRefresh.setVisibility(View.GONE);
            tv_nodata.setVisibility(View.VISIBLE);

        }else {
            mSmartRefresh.setVisibility(View.VISIBLE);
            adapter.addData(lookBean.getData(),false);
            tv_nodata.setVisibility(View.GONE);
        }
        lookAdapter.notifyDataSetChanged();

    }

    @Override
    protected String getUrl() {
        return Constant.API.YFM_TEACH_LIST;
    }

    @Override
    protected Map<String, Object> getParamMap() {
        return new ParamMap.Build().addParams("user_token",SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("city_name", SPUtils.getStringData(Constant.Config.CURRENT_CITY))
                .addParams("longitude", SPUtils.getStringData(Constant.Config.LONGTUDE))
                .addParams("latitude", SPUtils.getStringData(Constant.Config.LATITUDE)).build();
    }

    @Override
    protected void initData(View view) {
        mRecycleView.setBackgroundColor(getActivity().getResources().getColor(R.color.gb));
        lookAdapter.setOnTeachItemClick(new TeachListAdapter.OnTeachItemClickLisenter() {
            @Override
            public void onTeachIntemClick(int pos) {
                startActivity(new Intent(getActivity(),TeachInforActivity.class).putExtra("groupid",listBean.getData().get(pos).getGroup_id()));
            }
        } );
    }

    int frash;
    public void notice(){
        requestData();

        frash=1;
    }
}
