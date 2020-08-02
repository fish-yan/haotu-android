package com.dmeyc.dmestoreyfm.adapter;

import android.app.Activity;
import android.graphics.Rect;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.RecommendBean;
import com.dmeyc.dmestoreyfm.bean.common.BrandDesignerBean;
import com.dmeyc.dmestoreyfm.bean.common.GoodsBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.ui.MainActivity;
import com.dmeyc.dmestoreyfm.utils.DensityUtil;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.dmeyc.dmestoreyfm.wedgit.AutoGridView;
import com.dmeyc.dmestoreyfm.wedgit.IndercatorTextView;
import com.dmeyc.dmestoreyfm.wedgit.PriceView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by jockie on 2018/1/5
 * Email:jockie911@gmail.com
 */

public class RecommendAdapter extends BaseAdapter{

    private static final int TYPE_FIRST = 0;
    private static final int TYPE_SECOND = 1;
    private static final int TYPE_THIRD = 2;
    private Activity mActivity;

    RecommendBean.DataBean mDatas;

    public RecommendAdapter(Activity activity) {
        this.mActivity = activity;
    }

    public void addData(RecommendBean.DataBean dataBean){
        this.mDatas = dataBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : 4;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0 || position == 1){
            return TYPE_FIRST;
        }else if(position == 2){
            return TYPE_SECOND;
        }else {
            return TYPE_THIRD;
        }
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        if(getItemViewType(position) == TYPE_FIRST){
            convertView = getStyle1View(position, convertView, parent);
        }else if(getItemViewType(position) == TYPE_SECOND){
            convertView = getStyle2View(position, convertView, parent);
        }else{
            convertView = getStyle3View(position, convertView, parent);
        }
        return convertView;
    }

    private View getStyle3View(int position, View convertView, final ViewGroup parent) {
        final Styly3ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_lv_home_single,null);
            holder = new Styly3ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Styly3ViewHolder) convertView.getTag();
        }

        holder.tvAllClothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)mActivity).changePage(1,0);
            }
        });
        holder.autoGridView.setAdapter(new com.zhy.adapter.abslistview.CommonAdapter<GoodsBean>(parent.getContext(),R.layout.item_lv_gv_item_single,mDatas.getGoods()) {
            @Override
            protected void convert(com.zhy.adapter.abslistview.ViewHolder viewHolder,GoodsBean bean, int position) {

                viewHolder.getView(R.id.ll_good).setVisibility(View.VISIBLE);
               LinearLayout item_ll_root= viewHolder.getView(R.id.item_ll_root);
                RelativeLayout rl_des = viewHolder.getView(R.id.rl_des);
                rl_des.setVisibility(View.VISIBLE);
                RelativeLayout rl_shoppic = viewHolder.getView(R.id.rl_shoppic);
                rl_shoppic.setVisibility(View.VISIBLE);
                PriceView itemTvPrice = viewHolder.getView(R.id.item_tv_priceview);
                itemTvPrice.setVisibility(View.VISIBLE);
                TextView itemTvBrand = viewHolder.getView(R.id.item_tv_brand);
                itemTvBrand.setVisibility(View.VISIBLE);
                TextView itemTvName = viewHolder.getView(R.id.item_tv_name);
                itemTvName.setVisibility(View.VISIBLE);
                ImageView itemIvCoverPic = viewHolder.getView(R.id.item_iv_cover_pic);

                itemTvBrand.setText(bean.getName());
                itemTvPrice.setPrice(bean.getPrice());
                String size = "";
                for (int i = 0; i < bean.getSizeList().size(); i++) {
                    size = size + bean.getSizeList().get(i)+" ";
                }
                itemTvName.setText(size);
//                itemTvName.setText(bean.getCaption());
//                if(bean.isIsCustom()==0){
//                    item_ll_root.setVisibility(View.VISIBLE);
//
//                }else{
//                    item_ll_root.setVisibility(View.GONE);
//                }
                    GlideUtil.loadImage(parent.getContext(),bean.getImage(),itemIvCoverPic);
                ImageView view = viewHolder.getView(R.id.item_iv_istailor);
                view.setVisibility(bean.isIsCustom() == 0 ? View.INVISIBLE : View.VISIBLE);
            }
        });
        holder.autoGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Util.startProductActivity(parent.getContext(),mDatas.getGoods().get(position));
            }
        });
        return convertView;
    }
    private View getStyle2View(int position, View convertView, ViewGroup parent) {
        final Styly2ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(),R.layout.item_lv_home_topic,null);
            holder = new Styly2ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Styly2ViewHolder) convertView.getTag();
        }
        List<RecommendBean.DataBean.PromotionsBean> promotions = mDatas.getPromotions();
        holder.viewpager.setAdapter(new VPAdapter(promotions));
        holder.indercatortextview.initCurrentPosition(0,promotions.size());

        holder.viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                holder.indercatortextview.setCurrentPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return convertView;
    }
    //_______________________________________________________________________
    public class VPAdapter extends PagerAdapter {

        private List<RecommendBean.DataBean.PromotionsBean> piclist;

        public VPAdapter(List<RecommendBean.DataBean.PromotionsBean> piclist) {
            this.piclist = piclist;
        }

        @Override
        public int getCount() {
            return piclist.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(final View container, final int position) {
            View view = View.inflate(container.getContext(), R.layout.item_home_page, null);
            RoundedImageView roundimageview = (RoundedImageView) view.findViewById(R.id.item_roundiv);
            roundimageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
            GlideUtil.loadImage(container.getContext(),piclist.get(position).getImage(),roundimageview);
            TextView tvItemTitle = (TextView) view.findViewById(R.id.tv_vp_item_title);
            TextView tvItemDes = (TextView) view.findViewById(R.id.tv_vp_item_des);
            tvItemTitle.setText(piclist.get(position).getTitle());
            tvItemDes.setText(piclist.get(position).getInfo());

            roundimageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Util.startWebView(container.getContext(),piclist.get(position).getTitle(),piclist.get(position).getUrl());
                }
            });
            ((ViewPager) container).addView(view);
            return view;
        }

        @Override
        public void destroyItem(View container, int position, Object view) {
            ((ViewPager) container).removeView((View) view);
        }
    }

    //_______________________________________________________________________

    private View getStyle1View(final int position, View convertView, final ViewGroup parent) {
        final Styly1ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(),R.layout.item_lv_home_design,null);
            holder = new Styly1ViewHolder(convertView);
            holder.mRecycleView.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                    outRect.left = parent.getChildLayoutPosition(view) == 0 ? (DensityUtil.dip2px(20) - 20) : (DensityUtil.dip2px(15) - 40);
                }
            });
            convertView.setTag(holder);
        } else {
            holder = (Styly1ViewHolder) convertView.getTag();
        }

        final List<BrandDesignerBean> dataLists = position == 0 ? mDatas.getBrands() : mDatas.getDesigners();

        holder.itemTVType1.setText(position == 0 ? "设计师精选" : "品牌精选");
        holder.itemTVType2.setText(position == 0 ? "全部设计师" : "全部品牌");
        holder.mRecycleView.setLayoutManager(new LinearLayoutManager(parent.getContext(),LinearLayoutManager.HORIZONTAL,false));

        final CommonAdapter adapter= new CommonAdapter<BrandDesignerBean>(parent.getContext(),R.layout.item_lv_item_rv_design,dataLists) {
            @Override
            protected void convert(ViewHolder holder, BrandDesignerBean bean, int position) {
                RoundedImageView roundedImageView = holder.getView(R.id.item_roundiv);
                roundedImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                GlideUtil.loadImage(parent.getContext(),bean.getImage(),roundedImageView);
            }
        };

        holder.mRecycleView.setAdapter(adapter);
        adapter.setOnItemClickListener(new com.zhy.adapter.recyclerview.MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int pos) {
                Util.startBrandDesignDetailActivity(parent.getContext(),position == 0 ? Constant.Config.TYPE_DESIGNER : Constant.Config.TYPE_BRAND,dataLists.get(pos).getId(),dataLists.get(pos).getStore());
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        holder.itemTVType2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)mActivity).changePage(3,position);
            }
        });
        return convertView;
    }

    private class Styly1ViewHolder {

        private TextView itemTVType1;
        private TextView itemTVType2;
        private RecyclerView mRecycleView;

        public Styly1ViewHolder(View convertView) {
            itemTVType1 = (TextView) convertView.findViewById(R.id.item_tv_type1);
            itemTVType2 = (TextView) convertView.findViewById(R.id.item_tv_type2);
            mRecycleView = (RecyclerView) convertView.findViewById(R.id.recycleview);
        }
    }

    private class Styly2ViewHolder {
        private IndercatorTextView indercatortextview;
        private ViewPager viewpager;

        public Styly2ViewHolder(View convertView) {
            indercatortextview = (IndercatorTextView) convertView.  findViewById(R.id.indercatortextview);
            viewpager = (ViewPager) convertView.findViewById(R.id.viewpage);
        }
    }

    private class Styly3ViewHolder {
        private AutoGridView autoGridView;
//private GridView autoGridView;
        private TextView tvAllClothes;

        public Styly3ViewHolder(View convertView) {
            autoGridView = (AutoGridView) convertView.findViewById(R.id.autogridview);
            tvAllClothes = (TextView) convertView.findViewById(R.id.tv_all_clothes);
        }
    }
}
