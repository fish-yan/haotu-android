package com.dmeyc.dmestoreyfm.ui.look.section;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.wedgit.sectioned.SectionedRecyclerViewAdapter;
import com.dmeyc.dmestoreyfm.wedgit.sectioned.StatelessSection;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FindDetailSection extends StatelessSection {

    private ArrayList<String> list = new ArrayList<>();
    private Context context;
    private SectionedRecyclerViewAdapter sectionedRecyclerViewAdapter;

    public FindDetailSection(Context context, SectionedRecyclerViewAdapter sectionedRecyclerViewAdapter) {
        super(R.layout.section_find_detail);
        this.context = context;
        this.sectionedRecyclerViewAdapter = sectionedRecyclerViewAdapter;
    }

    public void setData(List<String> data) {
        this.list.clear();
        this.list.addAll(data);
        sectionedRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public int getContentItemsTotal() {
        return list.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder itemHolder = (ViewHolder) holder;
        GlideUtil.loadImage(context,list.get(position),itemHolder.img_show);

        itemHolder.ll_father.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.img_show)
        public ImageView img_show;
        @Bind(R.id.text_title)
        public TextView text_title;
        @Bind(R.id.text_num)
        public TextView text_num;
        @Bind(R.id.text_price)
        public TextView text_price;
        @Bind(R.id.ll_father)
        public LinearLayout ll_father;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
