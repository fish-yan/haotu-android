package com.dmeyc.dmestoreyfm.ui.tailor;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

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

import butterknife.Bind;
import butterknife.OnClick;

public class DetailTailorActicity extends BaseActivity implements IDialogView {

    @Bind(R.id.recycleview)
    RecyclerView mRecycleView;
    private TailorAdapter mAdapter;
    private DialogPresenter mPresenter;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_detail_tailor_acticity;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        tvTitle.setText("细节定制");
        tvRightTitle.setText("清单");
        tvRightTitle.setTextColor(getResources().getColor(R.color.indicator_selected_color));

        mRecycleView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
//        mAdapter = new TailorAdapter(this, R.layout.item_tailor_detail, new ArrayList());
//        mRecycleView.setAdapter(mAdapter);

        mPresenter = new DialogPresenter(this);
        GoodsBean bean = getIntent().getParcelableExtra("goodsBean");
        int id = bean.getId();

        RestClient.getNovate(this).get(Constant.API.TAILOR_DETAIL, new ParamMap.Build().addParams("goods", id).build(), new DmeycBaseSubscriber<TailorBean>() {
            @Override

            public void onSuccess(TailorBean bean) {
                mAdapter.addData(bean.getData().getParentCustomProduct());
            }
        });
    }

    @OnClick({R.id.tv_right_title_bar,R.id.tv_add_car,R.id.tv_buy})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_right_title_bar:
                break;
            case R.id.tv_add_car:
                mPresenter.addCarTailor(this,getProductId());
                break;
            case R.id.tv_buy:
                mPresenter.buyTailor(this,(GoodsBean) getIntent().getParcelableExtra("goodsBean"),getProductId());
                break;
        }
    }
    @Override
    public boolean isCuston() {
        return true;
    }

    @Override
    public void showMsg(String msg) {
        SnackBarUtil.showShortSnackbar(tvTitle,msg);
    }

    @Override
    public int getProductId() {
        return getIntent().getIntExtra("productId",0);
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
    public SelectInfo getSelectSizeInfo() {
        SelectInfo selectinfo = getIntent().getParcelableExtra("selectinfo");
        return selectinfo;
    }

    @Override
    public String getCustoms() {
        String result = "";
        Map<Integer, Integer> selectedMap = mAdapter.selectedMap;
        for (Map.Entry<Integer, Integer> entry : selectedMap.entrySet()) {
            result += mAdapter.getItem(entry.getKey()).getChildrenCustomProduct().get(entry.getValue()).getId() + ",";
        }
        if(!TextUtils.isEmpty(result))
            result = result.substring(0,result.length() - 1);
        return result;
    }

    @Override
    public String getClickColor() {
        return null;
    }

    @Override
    public String getClickSize() {
        return null;
    }

    @Override
    public String getTailNme() {
        return null;
    }
}
