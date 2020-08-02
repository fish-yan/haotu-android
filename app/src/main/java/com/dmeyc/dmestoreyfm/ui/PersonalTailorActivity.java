package com.dmeyc.dmestoreyfm.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.product.TailorAdapter;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.AttrBean;
import com.dmeyc.dmestoreyfm.bean.SelectInfo;
import com.dmeyc.dmestoreyfm.bean.TailorBean;
import com.dmeyc.dmestoreyfm.bean.common.GoodsBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.dialog.DialogPresenter;
import com.dmeyc.dmestoreyfm.dialog.IDialogView;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SnackBarUtil;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.OnClick;


public class PersonalTailorActivity extends BaseActivity implements IDialogView {

//    private AutoFlowLayout  sizeFlowLayout,colorFlowLayout;
    private int goodid;
    private GoodsBean goodbean;
    private RecyclerView mRecycleView;
    private TailorAdapter mAdapter;
    private AttrBean beans;

    private TextView tv_total_price;
    private DialogPresenter mPresenter;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_personaltailor;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

        mRecycleView=mRootView.findViewById(R.id.recycleview);
        tv_total_price=mRootView.findViewById(R.id.tv_total_price);
        mPresenter = new DialogPresenter(this);

        goodid=getIntent().getIntExtra("goodid",-1);
//        goodbean=(GoodsBean)getIntent().getSerializableExtra("goodben");
        goodbean= (GoodsBean) getIntent().getParcelableExtra("goodben");
          getSize();
       }

    private void tailorDetail() {

        RestClient.getNovate(this).get(Constant.API.TAILOR_DETAIL, new ParamMap.Build().addParams("goods", goodid).build(), new DmeycBaseSubscriber<TailorBean>() {
            @Override
            public void onSuccess(TailorBean bean) {
//                mAdapter.addData(bean.getData().getParentCustomProduct());

                mRecycleView.setLayoutManager(new LinearLayoutManager(PersonalTailorActivity.this,LinearLayoutManager.VERTICAL,false));
//        mRecycleView.setLayoutManager(layoutManager);
//                mAdapter = new TailorAdapter(PersonalTailorActivity.this, R.layout.item_tailor_detail, new ArrayList(),beans,PersonalTailorActivity.this);
                mRecycleView.setNestedScrollingEnabled(false);
                mAdapter = new TailorAdapter(PersonalTailorActivity.this, R.layout.item_tailor_detail, bean.getData().getParentCustomProduct(),bean.getData().getDefaultClothesList(),beans,PersonalTailorActivity.this,tv_total_price,goodbean.getPrice());
                mRecycleView.setAdapter(mAdapter);
//              mAdapter.getprice(tv_total_price,goodbean.getPrice());
//                mAdapter.addData(bean.getData().getParentCustomProduct());
            }
        });
    }
    private void getSize() {
        RestClient.getNovate(this).get(Constant.API.WATCH_PRODUCT_DETAIL, new ParamMap.Build()
                .addParams("goods",goodid)
                .addParams("isCustom",true)
                .build(), new DmeycBaseSubscriber<AttrBean>() {
            @Override
            public void onSuccess(AttrBean bean) {
                beans=bean;
                tailorDetail();
            }
        });
    }
    @OnClick({R.id.tv_right_title_bar,R.id.tv_add_car,R.id.tv_buy})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_right_title_bar:
                break;
            case R.id.tv_add_car:
//                mPresenter.moreTailor(this,goodbean);
                if(TextUtils.isEmpty(getClickColor())||TextUtils.isEmpty(getClickSize())){
                    Toast.makeText(PersonalTailorActivity.this,"请选择颜色和尺寸",Toast.LENGTH_LONG).show();
                    return;
                }

                RestClient.getNovate(this).get(Constant.API.WATCH_PRODUCT_DETAIL, new ParamMap.Build()
                        .addParams("goods",goodid)
                        .addParams("quantity",getQuantity())
                        .addParams("isCustom",true)
                        .addParams("color",mAdapter.getClickcolor())
                        .addParams("size",mAdapter.getClickSize())
                        .build(), new DmeycBaseSubscriber<AttrBean>() {
                    @Override
                    public void onSuccess(AttrBean beanff) {
                        mPresenter.addCarTailor(PersonalTailorActivity.this,beanff.getData().getProduct().getId());
                    }
                });

                break;
            case R.id.tv_buy:
                if(TextUtils.isEmpty(getClickColor())||TextUtils.isEmpty(getClickSize())){
                    Toast.makeText(PersonalTailorActivity.this,"请选择颜色和尺寸",Toast.LENGTH_LONG).show();
                    return;
                }
                goodbean.setPrice((int) mAdapter.totalprice());
                mPresenter.buyTailor(this,goodbean,getProductId());
                break;


        }
    }
    @Override
    public int getProductId() {
        return goodid;
    }

    @Override
    public void requestDataSuccess(List<AttrBean.DataBean.AttributeDetailsBean> list) {

    }

    @Override
    public Map<String, String> checkPreStatus() {
        return new HashMap();
    }

    @Override
    public int getQuantity() {
        return getIntent().getIntExtra("quantity",1);
    }
    @Override
    public void dialogDismiss() {

    }
    @Override
    public void showMsg(String msg) {
        SnackBarUtil.showShortSnackbar(tvTitle,msg);
    }

    @Override
    public boolean isCuston() {
        return true;
    }

    @Override
    public SelectInfo getSelectSizeInfo() {

//        SelectInfo selectinfo = getIntent().getParcelableExtra("selectinfo");
        SelectInfo selectinfo = new SelectInfo(false,mAdapter.getClickSize());
        selectinfo.setTailorRecord(false);
        selectinfo.setSizeInfo(mAdapter.getClickSize());
        return selectinfo;
    }

    @Override
    public String getCustoms() {
        String result = "";
//        Map<Integer, Integer> selectedMap = mAdapter.selectedMap;
        Map<Integer, Integer> selectedMap = mAdapter.getTreeMap();
        for (Map.Entry<Integer, Integer> entry : selectedMap.entrySet()) {
            result += mAdapter.getItem(entry.getKey()-1).getChildrenCustomProduct().get(entry.getValue()).getId() + ",";
        }
        if(!TextUtils.isEmpty(result))
            result = result.substring(0,result.length() - 1);
        return result;
    }

//    /**
//     * 细节定制
//     * @param
//     */
//    public void moreTailor( final GoodsBean goodsBean){0
////        if(!checkPreStatus()) return;
//        ParamMap.Build build = new ParamMap.Build();
//        build.addParams("quantity",mBaseView.getQuantity());
//        build.addParams("goods",mBaseView.getProductId());
//        build.addParams("isCustom",mBaseView.isCuston());
//        if(mBaseView.getSelectSizeInfo().isTailorRecord()){
//            build.addParams("measureId",mBaseView.getSelectSizeInfo().getTailorRecordId());
//        }else{
//            build.addParams("sizeCustom",mBaseView.getSelectSizeInfo().getSizeInfo());
//        }
//        for (Map.Entry<String, String> entry : mBaseView.checkPreStatus().entrySet()) {
//            build.addParams(entry.getKey(),entry.getValue());
//        }
//        RestClient.getNovate(this).get(Constant.API.WATCH_PRODUCT_DETAIL, build.build(), new DmeycBaseSubscriber<AttrBean>(this) {
//            @Override
//            public void onSuccess(AttrBean bean) {
//                Intent intent = new Intent(context, DetailTailorActicity.class);
//                intent.putExtra("goodsBean", goodsBean);
//                intent.putExtra("productId",bean.getData().getProduct().getId());
//                intent.putExtra("quantity",mBaseView.getQuantity());
//
//                intent.putExtra("selectinfo",mBaseView.getSelectSizeInfo());
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
////                mBaseView.dialogDismiss();
//            }
//        });
//    }

                     @Override
                    public String getClickColor(){
                      return mAdapter.getClickcolor();
                       }

                    @Override
                    public String getClickSize() {
                        return mAdapter.getClickSize();
                    }

    @Override
    public String getTailNme() {
        return mAdapter.getTailNmae();
    }

}
