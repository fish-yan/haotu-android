package com.dmeyc.dmestoreyfm.dialog;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.AttrBean;
import com.dmeyc.dmestoreyfm.bean.SelectInfo;
import com.dmeyc.dmestoreyfm.bean.common.GoodsBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.ui.ProductActivity;
import com.dmeyc.dmestoreyfm.ui.ProductSelectSizeActivity;
import com.dmeyc.dmestoreyfm.utils.SnackBarUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by jockie on 2017/9/27
 * Email:jockie911@gmail.com
 */

public class ProductTailorlDialog extends ProductCommonlDialog {

    private Context mContent;
    @Bind(R.id.tv_address_manager)
    TextView tvAddressManeger;

    private boolean isSetRecord;
    private SelectInfo selectInfo;

    public ProductTailorlDialog(@NonNull Context context, GoodsBean goodsBean,int mProductId) {
        super(context);
        this.mContent = context;
        this.mGoodsBean = goodsBean;
        this.mProductId = mProductId;
    }

    @Override
    protected int getResLayoutId() {
        return R.layout.dialog_goods_detail;
    }

    /**
     * startActivityForResult for get record name and record id
     * @param selectInfo
     */
    public void setTailorRecordName(SelectInfo selectInfo) {
        tvAddressManeger.setText(selectInfo.getSizeInfo());
        this.selectInfo = selectInfo;
        isSetRecord = true;
    }

    @OnClick({R.id.tv_add_car,R.id.tv_buy,R.id.tv_address_manager,R.id.tv_tailor_more})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_add_car:
                if(isSetRecord){
                   mPresenter.addCar(getContext());
                }else{
                    showMsg("请先选择您的量身尺码");
                }
                break;
            case R.id.tv_buy:
                if(isSetRecord){
                    mPresenter.buy(getContext(),mGoodsBean);
                }else{
                    SnackBarUtil.showShortSnackbar(tvAddressManeger,"请先选择您的量身尺码");
                }
                break;
            case R.id.tv_address_manager:
                Intent intent = new Intent(mContent, ProductSelectSizeActivity.class);
                intent.putExtra(Constant.Config.IS_FOR_RESULT,true);
                intent.putExtra("mProductId",mProductId);
                ((ProductActivity)mContent).startActivityForResult(intent,0);
                break;
            case R.id.tv_tailor_more:
                if(isSetRecord){
                    mPresenter.moreTailor(getContext(),mGoodsBean);
                }else{
                    showMsg("请先选择您的量身尺码");
                }
                break;
        }
    }

    @Override
    public boolean isCuston() {
        return true;
    }

    @Override
    public void requestDataSuccess(List<AttrBean.DataBean.AttributeDetailsBean> list) {
        int tempPos = -1;
        for (int i = 0; i < list.size(); i++) {
            if(TextUtils.equals(list.get(i).getAttributeKey(),"size")){
                tempPos = i;
                break;
            }
        }
        if(tempPos != -1)
            list.remove(tempPos);
        mAdapter.addData(list);
    }

    @Override
    public SelectInfo getSelectSizeInfo() {
        return selectInfo;
    }
}
