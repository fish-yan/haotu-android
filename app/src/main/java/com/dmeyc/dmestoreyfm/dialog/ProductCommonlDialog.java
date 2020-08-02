package com.dmeyc.dmestoreyfm.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.ProductAttrAdapter;
import com.dmeyc.dmestoreyfm.base.BaseApp;
import com.dmeyc.dmestoreyfm.bean.AttrBean;
import com.dmeyc.dmestoreyfm.bean.SelectInfo;
import com.dmeyc.dmestoreyfm.bean.common.GoodsBean;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.AmountView;
import com.dmeyc.dmestoreyfm.wedgit.PriceView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jockie on 2017/9/27
 * Email:jockie911@gmail.com
 */
public class ProductCommonlDialog extends Dialog implements IDialogView{
    protected GoodsBean mGoodsBean;
    protected int mProductId;
    @Bind(R.id.recycleview)
    protected RecyclerView mRecycleView;
    @Bind(R.id.iv_cover)
    ImageView ivCover;
    @Bind(R.id.price_view)
    PriceView priceView;
    @Bind(R.id.tv_detail)
    TextView tvDetail;
    @Bind(R.id.amount_view)
    AmountView amountView;
private String size;
private String color;

    protected ProductAttrAdapter mAdapter;
    protected DialogPresenter mPresenter;

    public ProductCommonlDialog(@NonNull Context context) {
        super(context, R.style.dialog_style);
    }

    public ProductCommonlDialog(@NonNull Context context, GoodsBean goodsBean,int mProductId) {
        this(context);
        this.mGoodsBean = goodsBean;
        this.mProductId = mProductId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResLayoutId());
        ButterKnife.bind(this);

        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = AbsListView.LayoutParams.MATCH_PARENT;
        lp.height = BaseApp.getHeight()*4/5;
        lp.y = 0;//设置Dialog距离底部的距离
        dialogWindow.setAttributes(lp);

        mPresenter = new DialogPresenter(this);
        initData();
    }

    protected int getResLayoutId(){
        return R.layout.dialog_product_common;
    }

    protected void initData() {
        amountView.setMaxCount(isCuston() ? Integer.MAX_VALUE : 1);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        mAdapter = new ProductAttrAdapter(getContext(), R.layout.item_product_category, new ArrayList(),mProductId,isCuston());
        mRecycleView.setAdapter(mAdapter);

        mPresenter.requestDetail(getContext());

        priceView.setPrice(mGoodsBean.getPrice());
        GlideUtil.loadImage(getContext(),mGoodsBean.getImage(),ivCover);
        mAdapter.setOnSelectedChangedLister(new ProductAttrAdapter.SelectedChangedLister() {
            @Override
            public void onSelectedChanged(String selectResult, String totalPrice, String coverUrl, int stock,Map<String,String> se) {
               Set<Map.Entry<String,String>> selet= se.entrySet();
               for (Map.Entry<String,String> vale : selet){
                   if("size".equals(vale.getKey())){
                       size=vale.getValue();
                   }else if("color".equals(vale.getKey())){
                       color=vale.getValue();
                   }

                }
                tvDetail.setText(selectResult);
                priceView.setPrice(TextUtils.isEmpty(totalPrice) ? mGoodsBean.getPrice() : totalPrice);
                GlideUtil.loadImage(getContext(),TextUtils.isEmpty(coverUrl) ? mGoodsBean.getImage() : coverUrl,ivCover);
                if(!isCuston()){
                    amountView.setMaxCount(stock);
                }
            }
        });
    }

    @OnClick({R.id.tv_add_car,R.id.tv_buy})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_add_car:
                mPresenter.addCar(getContext());
                break;
            case R.id.tv_buy:
                mPresenter.buy(getContext(),mGoodsBean);
                break;
        }
    }

    @Override
    public boolean isCuston() {
        return false;
    }

    @Override
    public void showMsg(String msg) {
        ToastUtil.show(msg);
    }

    @Override
    public int getProductId() {
        return mProductId;
    }

    @Override
    public void requestDataSuccess(List<AttrBean.DataBean.AttributeDetailsBean> list) {
        mAdapter.addData(list);
    }

    @Override
    public Map<String, String> checkPreStatus() {
        return mAdapter.selectedKinds;
    }

    @Override
    public int getQuantity() {
        return amountView.getCurCount();
    }

    @Override
    public void dialogDismiss() {
        dismiss();
    }

    @Override
    public SelectInfo getSelectSizeInfo() {
        return null;
    }

    @Override
    public String getCustoms() {
        return null;
    }

    @Override
    public String getClickColor() {

        return color;
    }

    @Override
    public String getClickSize() {
        return size;
    }

    @Override
    public String getTailNme() {
        return null;
    }
}
