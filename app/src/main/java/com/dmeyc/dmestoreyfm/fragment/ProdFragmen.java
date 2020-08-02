package com.dmeyc.dmestoreyfm.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.AdapterProdRecord;
import com.dmeyc.dmestoreyfm.adapter.TeachListAdapter;
import com.dmeyc.dmestoreyfm.base.BaseRefreshFragment;
import com.dmeyc.dmestoreyfm.bean.TeachListBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.ui.ProdDetailActivity;
import com.dmeyc.dmestoreyfm.ui.ProdListBean;
import com.dmeyc.dmestoreyfm.ui.TeachInforActivity;
import com.dmeyc.dmestoreyfm.utils.GsonTools;
import com.dmeyc.dmestoreyfm.utils.SPUtils;

import java.util.ArrayList;
import java.util.Map;

@SuppressLint("ValidFragment")
public class ProdFragmen extends BaseRefreshFragment<ProdListBean,AdapterProdRecord> {

    private AdapterProdRecord lookAdapter;
    int pos;
    public ProdFragmen(int pos){
        this.pos=pos;
    }
    @Override
    protected AdapterProdRecord getAdapter() {
        lookAdapter= new AdapterProdRecord(getActivity(), R.layout.adapter_prodrecord, new ArrayList<ProdListBean.DataBean>());
        return lookAdapter;
    }
    @Override
    protected ProdListBean parseData(String string)
    {     closeRefresh();
        return GsonTools.changeGsonToBean(string,ProdListBean.class);

    }
    ProdListBean listBean;
    @Override
    protected void setData(ProdListBean lookBean, boolean isRefresh) {
        listBean=lookBean;
        if(lookBean.getData().size()==0){
            mSmartRefresh.setVisibility(View.GONE);
            tv_nodata.setVisibility(View.VISIBLE);

        }else {
            mSmartRefresh.setVisibility(View.VISIBLE);
            lookAdapter.addData(lookBean.getData(),false);
            tv_nodata.setVisibility(View.GONE);
        }
        lookAdapter.notifyDataSetChanged();

    }

    @Override
    protected String getUrl() {
        return Constant.API.YFM_MyPROGARD;
    }

    @Override
    protected Map<String, Object> getParamMap() {
        ParamMap.Build  pb=  new ParamMap.Build();
        if(0==pos){
            pb.addParams("type","0");
        }else {
            pb.addParams("type","2");
        }
        return pb.addParams("user_token",SPUtils.getStringData(Constant.Config.TOKEN))
                .build();
        }

    @Override
    protected void initData(View view) {
        mRecycleView.setBackgroundColor(getActivity().getResources().getColor(R.color.gb));
        lookAdapter.setOnTeachItemClick(new AdapterProdRecord.OnTeachItemClickLisenter() {
            @Override
            public void onTeachIntemClick(int pos) {
                startActivity(new Intent(getActivity(),ProdDetailActivity.class).
                        putExtra("name",listBean.getData().get(pos).getName()).
                        putExtra("saftacount",listBean.getData().get(pos).getSafeAcount()+"").
                        putExtra("staust",listBean.getData().get(pos).getStatus()).
                        putExtra("data",listBean.getData().get(pos).getStartDate()+"至"+listBean.getData().get(pos).getEndDate()).
                        putExtra("datanumber",listBean.getData().get(pos).getDate()+""));
//                startActivity(new Intent(getActivity(),TeachInforActivity.class).putExtra("groupid",listBean.getData().get(pos).getGroup_id()));
            }
        } );
    }
    int frash;
    public void notice(){
        requestData();
        frash=1;
    }
}
