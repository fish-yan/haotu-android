package com.dmeyc.dmestoreyfm.fragment.section;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseApp;
import com.dmeyc.dmestoreyfm.bean.common.GoodsBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.ui.ProductActivity;
import com.dmeyc.dmestoreyfm.utils.DensityUtil;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.wedgit.PriceView;
import com.dmeyc.dmestoreyfm.wedgit.sectioned.SectionedRecyclerViewAdapter;
import com.dmeyc.dmestoreyfm.wedgit.sectioned.StatelessSection;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MineGoodsListSection extends StatelessSection {

    private Context context;
    private SectionedRecyclerViewAdapter sectionedRecyclerViewAdapter;
    private List<GoodsBean> list = new ArrayList<>();

    public MineGoodsListSection(Context context, SectionedRecyclerViewAdapter sectionedRecyclerViewAdapter) {
        super(R.layout.item_lv_gv_item_single);
        this.context = context;
        this.sectionedRecyclerViewAdapter = sectionedRecyclerViewAdapter;
    }

    public void setData(List<GoodsBean> data) {
        this.list.clear();
        this.list.addAll(data);
        sectionedRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public int getContentItemsTotal() {
        return 6;
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder itemHolder = (ViewHolder) holder;
        itemHolder.ll_good.setVisibility(View.VISIBLE);
        int itemWidth = (BaseApp.getWidth() - DensityUtil.dip2px(50)) / 2;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(itemWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        itemHolder.itemLlRoot.setLayoutParams(params);

        String size = "";
        for (int i = 0; i < list.get(position).getSizeList().size(); i++) {
            size = size + list.get(position).getSizeList().get(i) + " ";
        }

        itemHolder.itemTvBrand.setText(list.get(position).getName());
        itemHolder.itemTvPrice.setPrice(list.get(position).getPrice());
        itemHolder.itemTvName.setText(size);
        GlideUtil.loadImage(context, list.get(position).getImage(), itemHolder.itemIvCoverPic);
        itemHolder.ivIstailor.setVisibility(list.get(position).isIsCustom() == 0 ? View.INVISIBLE : View.VISIBLE);

        itemHolder.ll_good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(Constant.Config.ID, list.get(position).getId());
                context.startActivity(intent);
            }
        });

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.ll_good)
        public LinearLayout ll_good;
        @Bind(R.id.item_ll_root)
        public LinearLayout itemLlRoot;
        @Bind(R.id.item_tv_priceview)
        public PriceView itemTvPrice;
        @Bind(R.id.item_tv_brand)
        public TextView itemTvBrand;
        @Bind(R.id.item_tv_name)
        public TextView itemTvName;
        @Bind(R.id.item_iv_cover_pic)
        public ImageView itemIvCoverPic;
        @Bind(R.id.item_iv_istailor)
        public ImageView ivIstailor;


        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
