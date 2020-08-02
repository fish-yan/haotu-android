package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.AttrBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jockie on 2018/2/7
 * Email:jockie911@gmail.com
 */

public class ProductAttrAdapter extends BaseRvAdapter<AttrBean.DataBean.AttributeDetailsBean>{

    private boolean isCustom;
    private int mProductId;
    public Map<String,String> selectedKinds = new HashMap<>();
    private SelectedChangedLister lister;

    /**
     * @param context
     * @param layoutId
     * @param datas    不能为null
     */
    public ProductAttrAdapter(Context context, int layoutId, List<AttrBean.DataBean.AttributeDetailsBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void addData(List<AttrBean.DataBean.AttributeDetailsBean> datas) {
        super.addData(datas);
        selectedKinds.clear();
        for (AttrBean.DataBean.AttributeDetailsBean data : datas) {
            selectedKinds.put(data.getAttributeKey(),"");
        }
    }

    public ProductAttrAdapter(Context context, int layoutId, List<AttrBean.DataBean.AttributeDetailsBean> datas, int mProductId,boolean isCustom) {
        this(context, layoutId, datas);
        this.mProductId = mProductId;
        this.isCustom = isCustom;
    }

    @Override
    protected void convert(ViewHolder holder, final AttrBean.DataBean.AttributeDetailsBean parentAttributeDetailBean, final int position) {
        final RecyclerView rvItemCategory = holder.getView(R.id.rv_item_categroy);
        holder.setText(R.id.tv_cate_name,parentAttributeDetailBean.getAttributeName());
        rvItemCategory.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));

        rvItemCategory.setAdapter(new CommonAdapter<String>(mContext,R.layout.item_item_product_category,parentAttributeDetailBean.getChildrenAttributeName()) {

            @Override
            protected void convert(ViewHolder holder, final String s, final int pos) {
                final TextView catagroyName = holder.getView(R.id.tv_item_catagory_name);
                catagroyName.setClickable(true);
                holder.setText(R.id.tv_item_catagory_name,s);

                if(attributeDetails != null){
                    if(position<=attributeDetails.size()-1){
                        if(attributeDetails.get(position).getChildrenAttributeName().contains(s)){
                            catagroyName.setClickable(true);
                        } else {
                            catagroyName.setClickable(false);
                        }
                    }

                }
                if(TextUtils.equals(s,selectedKinds.get(ProductAttrAdapter.this.mDatas.get(position).getAttributeKey()))){
                    catagroyName.setTextColor(mContext.getResources().getColor(R.color.indicator_selected_color));
                    catagroyName.setBackgroundResource(R.drawable.shape_1radius_fd_stroke);
                }else{
                    if(catagroyName.isClickable()){
                        catagroyName.setTextColor(mContext.getResources().getColor(R.color.color_1a1a1a));
                        catagroyName.setBackgroundResource(R.drawable.shape_1radius_1a_stroke);
                    }else{
                        catagroyName.setTextColor(mContext.getResources().getColor(R.color.color_c5c5c5));
                        catagroyName.setBackgroundResource(R.drawable.shape_1radius_c5_stroke);
                    }
                }
                if(catagroyName.isClickable()){
                    catagroyName.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(TextUtils.equals(s,selectedKinds.get(ProductAttrAdapter.this.mDatas.get(position).getAttributeKey()))){
                                // 已经被选中，双击取消
                                selectedKinds.put(ProductAttrAdapter.this.mDatas.get(position).getAttributeKey(),"");
                            }else{
                                selectedKinds.put(ProductAttrAdapter.this.mDatas.get(position).getAttributeKey(),s);
                            }
                            requestRemainAttru();
//                                           notifyDataSetChanged();
                        }
                    });
                }else{
                }
            }
        });
    }

    private List<AttrBean.DataBean.AttributeDetailsBean> attributeDetails,attributeDetails1;

    /**
     * 查看剩余商品的属性
     */
    private void requestRemainAttru(){
        ParamMap.Build build = new ParamMap.Build();
        build.addParams("goods",mProductId);

        build.addParams("isCustom",isCustom);
        for (Map.Entry<String, String> entry : selectedKinds.entrySet()) {
            if(!TextUtils.isEmpty(entry.getValue()))
                build.addParams(entry.getKey(),entry.getValue());
        }
        RestClient.getNovate(mContext).get(Constant.API.WATCH_PRODUCT_DETAIL, build.build(), new DmeycBaseSubscriber<AttrBean>(mContext) {

            @Override
            public void onSuccess(AttrBean bean) {
                attributeDetails = bean.getData().getAttributeDetails();
                attributeDetails1 = new ArrayList();
                for (int i = 0; i < mDatas.size(); i++) {
                    for (AttrBean.DataBean.AttributeDetailsBean detail : attributeDetails) {
                        if(TextUtils.equals(detail.getAttributeKey(),mDatas.get(i).getAttributeKey())){
                            attributeDetails1.add(detail);
                            break;
                        }
                    }
                }
                attributeDetails = attributeDetails1;
                if(lister != null){
                    String result = "";
                    for (Map.Entry<String, String> entry : selectedKinds.entrySet()) {
                        if(!TextUtils.isEmpty(entry.getValue())){
                            String temp = entry.getKey();
                            if(TextUtils.equals(entry.getKey(),"size")){
                                temp = "尺寸";
                            }else if(TextUtils.equals(entry.getKey(),"color")){
                                temp = "颜色";
                            }else if(TextUtils.equals(entry.getKey(),"material")){
                                temp = "面料";
                            }
                            result += temp + " : " + entry.getValue() + " ; ";
                        }
                    }
                    lister.onSelectedChanged(result,bean.getData().getProduct() == null ? "" : String.valueOf(bean.getData().getProduct().getPrice()),
                            Util.objEmpty(bean.getData().getCustomImages()) ? "" : bean.getData().getCustomImages().get(0).getThumbnail(),
                            bean.getData().getProduct() == null ? 1 : bean.getData().getProduct().getAllocatedStock(),selectedKinds);
                }
                notifyDataSetChanged();
            }
        });
    }

    public void setOnSelectedChangedLister(SelectedChangedLister lister){
        this.lister = lister;
    }

    public interface SelectedChangedLister{
        void onSelectedChanged(String selectResult,String totalPrice,String url,int stock,Map<String,String> se);
    }
}
