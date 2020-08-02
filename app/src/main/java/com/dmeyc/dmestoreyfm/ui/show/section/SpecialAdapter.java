package com.dmeyc.dmestoreyfm.ui.show.section;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.SpecialBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.ui.WebviewActivity;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;


public class SpecialAdapter extends RecyclerView.Adapter<SpecialAdapter.MyHolder> {
    private Context context;
    private List<SpecialBean.DataBean> beanList = new ArrayList<>();

    public SpecialAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<SpecialBean.DataBean> data, int flag) {
        beanList = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.section_special, viewGroup, false);
        SpecialAdapter.MyHolder holder = new SpecialAdapter.MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
        myHolder.textView.setText(beanList.get(i).getTitle());
        myHolder.roundimageview.setScaleType(ImageView.ScaleType.FIT_XY);
        GlideUtil.loadImage(context,
                "http://www.icicle.com/assets/imgsupl/RG6.0_0.36965800_1500520008_c970_news_images_image_2.jpg"
                , myHolder.roundimageview);
        myHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(Constant.Config.TITLE,beanList.get(i).getTitle());
                intent.putExtra(Constant.Config.URL,beanList.get(i).getUrl());
                intent.setClass(context, WebviewActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView textView;
        RoundedImageView roundimageview;
        LinearLayout ll;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_vp_item_title);
            roundimageview = itemView.findViewById(R.id.item_roundiv);
            ll = itemView.findViewById(R.id.ll);
        }
    }
}
