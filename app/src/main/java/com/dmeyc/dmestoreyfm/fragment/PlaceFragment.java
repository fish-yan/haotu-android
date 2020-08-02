package com.dmeyc.dmestoreyfm.fragment;

import android.content.Intent;
import android.view.View;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.PlaceAdapter;
import com.dmeyc.dmestoreyfm.adapter.TeachListAdapter;
import com.dmeyc.dmestoreyfm.base.BaseRefreshFragment;
import com.dmeyc.dmestoreyfm.bean.LookBean;
import com.dmeyc.dmestoreyfm.bean.PlaceListBean;
import com.dmeyc.dmestoreyfm.bean.TeachListBean;
import com.dmeyc.dmestoreyfm.bean.common.ReviewsBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.ui.PlaceInforActivity;
import com.dmeyc.dmestoreyfm.utils.GsonTools;
import com.dmeyc.dmestoreyfm.utils.SPUtils;

import java.util.ArrayList;
import java.util.Map;

public class PlaceFragment extends BaseRefreshFragment<PlaceListBean,PlaceAdapter> {

    private PlaceAdapter lookAdapter;
    @Override
    protected PlaceAdapter getAdapter() {
        lookAdapter= new PlaceAdapter(getActivity(), R.layout.adapter_placeadapter, new ArrayList<PlaceListBean.DataBean>());
        return lookAdapter;
    }
    @Override
    protected PlaceListBean parseData(String string) {
        return GsonTools.changeGsonToBean(string,PlaceListBean.class);
    }
    PlaceListBean teachListBean;
    @Override
    protected void setData(PlaceListBean lookBean, boolean isRefresh) {
        teachListBean=lookBean;
        if(lookBean.getData().size()==0){
            mSmartRefresh.setVisibility(View.GONE);
            tv_nodata.setVisibility(View.VISIBLE);

        }else {
            mSmartRefresh.setVisibility(View.VISIBLE);
            adapter.addData(lookBean.getData(),isRefresh);
            tv_nodata.setVisibility(View.GONE);
        }


    }

    @Override
    protected String getUrl() {
        return Constant.API.YFM_PLACE_LIST;
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
        lookAdapter.setOnTeachItemClick(new PlaceAdapter.OnTeachItemClickLisenter() {
            @Override
            public void onTeachIntemClick(int pos) {
//                Toast.makeText(getActivity(),"jdkfjkdjkl"+pos,Toast.LENGTH_LONG).show();
                startActivity(new Intent(getActivity(),PlaceInforActivity.class).putExtra("venue_id",teachListBean.getData().get(pos).getVenue_id()));

            }
        });
    }

    int frash;
    public void notice(){
        requestData();
        frash=1;
    }
}

