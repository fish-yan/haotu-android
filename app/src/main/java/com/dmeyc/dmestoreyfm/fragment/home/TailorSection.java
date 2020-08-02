package com.dmeyc.dmestoreyfm.fragment.home;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.TailorListBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.ui.TailorDetailActivity;
import com.dmeyc.dmestoreyfm.ui.photo.FrontTailorActivity;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.wedgit.camera.CameraManager;
import com.dmeyc.dmestoreyfm.wedgit.sectioned.SectionedRecyclerViewAdapter;
import com.dmeyc.dmestoreyfm.wedgit.sectioned.StatelessSection;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TailorSection extends StatelessSection {

    private List<TailorListBean.DataBean> list = new ArrayList();
    private Context context;
    private SectionedRecyclerViewAdapter sectionedRecyclerViewAdapter;

    public TailorSection(Context context, SectionedRecyclerViewAdapter sectionedRecyclerViewAdapter) {
        super(R.layout.section_tailor_head, R.layout.section_tailor_bady);
        this.context = context;
        this.sectionedRecyclerViewAdapter = sectionedRecyclerViewAdapter;
    }

    public  void setData(List<TailorListBean.DataBean> data) {
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
        return new TailorViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, final int position) {
        TailorViewHolder tailorViewHolder = (TailorViewHolder) holder;
        if(list.size() == 0){
            return;
        }
        if(position == 0){
            tailorViewHolder.text_title.setVisibility(View.VISIBLE);
        }
        else{
            tailorViewHolder.text_title.setVisibility(View.GONE);
        }
        tailorViewHolder.tv_attention.setText(list.get(position).getMemo());
        tailorViewHolder.tv_attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TailorDetailActivity.class);
                intent.putExtra(Constant.Config.ITEM,list.get(position));
                context.startActivity(intent);
//                Intent intent = new Intent(context, WeightHeightActivity.class);
//                context.startActivity(intent);
            }
        });
    }

    static class TailorViewHolder extends RecyclerView.ViewHolder
    {

        @Bind(R.id.text_title)
        TextView text_title;
        @Bind(R.id.tv_attention)
        TextView tv_attention;

        TailorViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new TailorViewHolderHead(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        ((TailorViewHolderHead) holder).img_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    SPUtils.savaIntData("SP_CAMERA_DIRECTION", CameraManager.CameraDirection.CAMERA_BACK.ordinal());
                    context.startActivity(new Intent(context, FrontTailorActivity.class));
                }else{
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
                }
            }
        });


    }

    static class TailorViewHolderHead extends RecyclerView.ViewHolder
    {
        @Bind(R.id.img_top)
        ImageView img_top;
        TailorViewHolderHead(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
