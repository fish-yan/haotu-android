package com.dmeyc.dmestoreyfm.fragment.brand;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.BaseRvAdapter;
import com.dmeyc.dmestoreyfm.adapter.BrandDesignAdapter;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.bean.BrandBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.DensityUtil;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.HanyuPinyinHelper;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.dmeyc.dmestoreyfm.wedgit.WaveSideBar;
import com.makeramen.roundedimageview.RoundedImageView;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by jockie on 2017/11/7
 * Email:jockie911@gmail.com
 */

public class BrandDesignFragment extends BaseFragment {

    @Bind(R.id.recycleview)
    RecyclerView mRecycleView;
    @Bind(R.id.wavasidebar)
    WaveSideBar mWaveSideBar;

    private BrandDesignAdapter mAdapter;
    private HeaderAndFooterWrapper wrapper;

    @Override
    protected int getLayoutRes() {
        return R.layout.fm_brand_design;
    }

    @Override
    protected void initData() {
        mAdapter = new BrandDesignAdapter(getContext(), R.layout.item_rv_brand_design_item, new ArrayList<BrandBean.DataBean.BrandDesignersBean>());
        wrapper = new HeaderAndFooterWrapper(mAdapter);

        RestClient.getNovate(getActivity()).get(Constant.API.BRAND_DESIGNER, new ParamMap.Build().build(), new DmeycBaseSubscriber<BrandBean>(getActivity()) {
            @Override
            public void onSuccess(BrandBean bean) {
                mAdapter.addData(bean.getData().getBrandDesigners());
                setHeadView(bean.getData());
                wrapper.notifyDataSetChanged();
            }
        });
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycleView.setAdapter(wrapper);

        mWaveSideBar.setOnSelectIndexItemListener(new WaveSideBar.OnSelectIndexItemListener() {
            @Override
            public void onSelectIndexItem(String index) {
                ToastUtil.showCenter(index);
                if(getReturnPositin(index) != -1)
                    mRecycleView.smoothScrollToPosition(getReturnPositin(index));
            }
        });
        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Util.startBrandDesignDetailActivity(getActivity(),getType(),mAdapter.getItem(position - 1).getId(),mAdapter.getItem(position-1).getStore());
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }


    @Override
    protected void initData(View view) {

    }
    protected int getReturnPositin(String index) {
        if("推荐".equals(index))
            return 0;
        for (int i = 0; i < mAdapter.getDatas().size(); i++) {
            if(TextUtils.equals(index, HanyuPinyinHelper.getSingleFirstSpell(mAdapter.getItem(i).getName())))
                return i + 1;
        }
        return -1;
    }

    public void setHeadView(BrandBean.DataBean data) {
        View headview = LayoutInflater.from(getContext()).inflate(R.layout.item_rv_brand_design_headview,null,true);
        wrapper.addHeaderView(headview);
        RecyclerView recycleview1 = (RecyclerView) headview.findViewById(R.id.recycleview1);
        RecyclerView recycleview2 = (RecyclerView) headview.findViewById(R.id.recycleview2);
        recycleview1.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recycleview2.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        recycleview1.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.left = parent.getChildLayoutPosition(view) == 0 ? (DensityUtil.dip2px(20) - 20) : (DensityUtil.dip2px(15) - 40);
            }
        });
        recycleview2.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.left = parent.getChildLayoutPosition(view) == 0 ? (DensityUtil.dip2px(20) - 20) : (DensityUtil.dip2px(15) - 40);
            }
        });

        recycleview1.setAdapter(new HeadBrandAdapter_2(getActivity(), R.layout.item_rv_head_brand,data.getRecentBrandsDesigners()));
        recycleview2.setAdapter(new HeadBrandAdapter(getActivity(), R.layout.item_rv_head_brand,data.getPopularBrandDesigners()));

        TextView tvHeadBrandName1 = (TextView) headview.findViewById(R.id.tv_head_brand_1);
        TextView tvHeadBrandName2 = (TextView) headview.findViewById(R.id.tv_head_brand_2);
        TextView tvHeadBrandName3 = (TextView) headview.findViewById(R.id.tv_head_brand_3);
        TextView tvHeadBrandName4 = (TextView) headview.findViewById(R.id.tv_head_brand_4);
        tvHeadBrandName1.setText(getType() == Constant.Config.TYPE_DESIGNER ? "最新设计师入住" : "最新品牌入住");
        tvHeadBrandName2.setText(getType() == Constant.Config.TYPE_DESIGNER ? "最受欢迎设计师排行榜" : "最受欢迎品牌排行榜");
        tvHeadBrandName3.setText(getType() == Constant.Config.TYPE_DESIGNER ? "全部设计师" : "全部品牌");
        tvHeadBrandName4.setText(getType() == Constant.Config.TYPE_DESIGNER ? "ALLDESIGNER" : "ALLBRANDS");
    }

    /**
     * type: "1或2(1代表品牌,2代表设计师)",
     * @return
     */
    protected int getType(){
        return Constant.Config.TYPE_DESIGNER;
    }

    class HeadBrandAdapter extends BaseRvAdapter<BrandBean.DataBean.PopularBrandDesignersBean>{

        /**
         * @param context
         * @param layoutId
         * @param datas    不能为null
         */
        public HeadBrandAdapter(Context context, int layoutId, List<BrandBean.DataBean.PopularBrandDesignersBean> datas) {
            super(context, layoutId, datas);
            setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                    Util.startBrandDesignDetailActivity(getActivity(),getType(),getItem(position).getId(),getItem(position).getStore());
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                    return false;
                }
            });
        }

        @Override
        protected void convert(ViewHolder holder, BrandBean.DataBean.PopularBrandDesignersBean bean, int position) {
            RoundedImageView roungimageview = holder.getView(R.id.item_roundiv);
            roungimageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
            GlideUtil.loadImage(getContext(),bean.getRecentImage(),roungimageview);
        }
    }

    class HeadBrandAdapter_2 extends BaseRvAdapter<BrandBean.DataBean.RecentBrandsDesignersBean>{

        /**
         * @param context
         * @param layoutId
         * @param datas    不能为null
         */
        public HeadBrandAdapter_2(Context context, int layoutId, List<BrandBean.DataBean.RecentBrandsDesignersBean> datas) {
            super(context, layoutId, datas);
            setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                    Util.startBrandDesignDetailActivity(getActivity(),getType(),getItem(position).getId(),getItem(position).getStore());
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                    return false;
                }
            });
        }

        @Override
        protected void convert(ViewHolder holder, BrandBean.DataBean.RecentBrandsDesignersBean bean, int position) {
            RoundedImageView roungimageview = holder.getView(R.id.item_roundiv);
            roungimageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
            GlideUtil.loadImage(getContext(),bean.getRecentImage(),roungimageview);
        }
    }
}
