package com.dmeyc.dmestoreyfm.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.GoodsAdaper;
import com.dmeyc.dmestoreyfm.base.BaseRefreshFragment;
import com.dmeyc.dmestoreyfm.bean.BrandDetailBean;
import com.dmeyc.dmestoreyfm.bean.common.BrandDesignerBean;
import com.dmeyc.dmestoreyfm.bean.common.CommonBean;
import com.dmeyc.dmestoreyfm.bean.common.ProductCategoryBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.decoration.SpaceItemDecoration;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.BrandDetailActivity;
import com.dmeyc.dmestoreyfm.utils.GsonTools;
import com.dmeyc.dmestoreyfm.utils.SnackBarUtil;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.dmeyc.dmestoreyfm.wedgit.ScreenView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jockie on 2018/3/15
 * Email:jockie911@gmail.com
 */

public class BrandDesignFragment extends BaseRefreshFragment<BrandDetailBean,GoodsAdaper>{

    private TextView headFollow;
    private ScreenView screenView;

    @Override
    protected GoodsAdaper getAdapter() {
        mRecycleView.addItemDecoration(new SpaceItemDecoration(true));
        return new GoodsAdaper(getActivity(), R.layout.item_lv_gv_item_single, new ArrayList(),true);
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
    }

    @Override
    protected boolean isWithHead() {
        return true;
    }

    @Override
    protected BrandDetailBean parseData(String result) {
        return GsonTools.changeGsonToBean(result,BrandDetailBean.class);
    }

    @Override
    protected void setData(BrandDetailBean bean, boolean isRefresh) {
        BrandDesignerBean itemBean = isBrand() ? bean.getData().getBrand() : bean.getData().getDesigner();
        if(itemBean != null){
            setheadView(itemBean,bean.getData().getProductCategory());
        }
        adapter.addData(bean.getData().getGoods(),isRefresh);
        wrapper.notifyDataSetChanged();
    }

    @Override
    protected String getUrl() {
        return isBrand() ? Constant.API.DETAIL_BRAND : Constant.API.DETAIL_DESIGNER;
    }

    @Override
    protected Map<String, Object> getParamMap() {
        Map<String, Object> map = new ParamMap.Build()
                .addParams(Constant.Config.ID, getBrandId())
                .addParams(Constant.Config.TYPE, ((BrandDetailActivity) getActivity()).getType())
                .build();
        if(screenView != null){
            map = screenView.setSeasonParam(screenView.setPriceParam(screenView.setCategoryParam(map)));
        }
        return map;
    }

    public int getBrandId(){
        return ((BrandDetailActivity)getActivity()).getId();
    }

    public boolean isBrand(){
        return ((BrandDetailActivity)getActivity()).isBrand();
    }

    /**
     * set headview
     * @param itemBean
     * @param productCategory
     */
    public void setheadView(BrandDesignerBean itemBean, List<ProductCategoryBean> productCategory){
        if(wrapper.getHeadersCount() > 0) return;
        ((BrandDetailActivity)getActivity()).setTitle(itemBean.getName());
        View view = View.inflate(getActivity(), R.layout.head_detail_brand, null);
        wrapper.addHeaderView(view);

        final TextView headName = (TextView) view.findViewById(R.id.head_name);
        final TextView headDes = (TextView) view.findViewById(R.id.head_des);
        headFollow = (TextView) view.findViewById(R.id.head_follow);
        screenView = (ScreenView) view.findViewById(R.id.screen_view);
        headFollow.setText(!isBrand() ? "关注设计师" : "关注品牌");

        if(itemBean != null){
            headName.setText(itemBean.getName());
            headDes.setText(itemBean.getIntroduction());
            boolean isAttend = itemBean.getIsAttend();
            reSetAddtentStastus(isAttend);
        }
        headFollow.setClickable(true);
        screenView.addData(productCategory);
        // add listener
        screenView.setOnChooseClickListener(new ScreenView.OnChooseClickListener() {
            @Override
            public void onChooseClickListener(Map<Integer, String> map) {
                mCurrentPage = 1;
                requestData();
            }
        });
        headFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(!Util.checkLoginStatus(getActivity())){
//                    return;
//                }
                RestClient.getNovate(getActivity()).post(Constant.API.ATTEND_DESIGNER, new ParamMap.Build()
//                        .addParams("value", SPUtils.getStringData(Constant.Config.TOKEN))
//                        .addParams("key", SPUtils.getStringData(Constant.Config.MOBILE))
                        .addParams("attend", getBrandId())
                        .addParams("type", ((BrandDetailActivity)getActivity()).getType()).build(), new DmeycBaseSubscriber<CommonBean>(getActivity()) {
                    @Override
                    public void onSuccess(CommonBean bean) {
                        if(bean.getData() instanceof Boolean){
                            if((boolean)bean.getData()){
                                SnackBarUtil.showShortSnackbar(mRecycleView,"关注成功");
                            }else{
                                SnackBarUtil.showShortSnackbar(mRecycleView,"取消关注成功");
                            }
                            reSetAddtentStastus((boolean)bean.getData());
                        }
                    }
                });
            }
        });
    }

    public void reSetAddtentStastus(boolean isAttend){
        if(isAttend){
            headFollow.setText("已关注");
            headFollow.setBackground(getResources().getDrawable(R.drawable.shape_14radius_99));
            headFollow.setTextColor(getResources().getColor(R.color.gray));
        }else{
            headFollow.setText(!isBrand() ? "关注设计师" : "关注品牌");
            headFollow.setBackground(getResources().getDrawable(R.drawable.shape_14radius_fd7e18));
            headFollow.setTextColor(getResources().getColor(R.color.indicator_selected_color));
        }
    }

    @Override
    protected void initData(View view) {

    }
}
