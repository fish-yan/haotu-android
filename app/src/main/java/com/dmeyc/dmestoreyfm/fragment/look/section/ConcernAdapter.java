package com.dmeyc.dmestoreyfm.fragment.look.section;

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
import com.dmeyc.dmestoreyfm.ui.look.FindDetailActivity;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shadyY on 2018/12/3
 * 关注_section
 */

public class ConcernAdapter extends RecyclerView.Adapter<ConcernAdapter.MyHolder> {

    private Context context;
    private List<SpecialBean.DataBean> beanList = new ArrayList<>();

    public ConcernAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<SpecialBean.DataBean> data, int flag) {
        if (flag == 0) {
            beanList.clear();
        }
        beanList.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.section_concern, viewGroup, false);
        ConcernAdapter.MyHolder holder = new ConcernAdapter.MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder myHolder, int position) {
        myHolder.text_name.setText(String.valueOf(position));

        myHolder.ll_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(context, FindDetailActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        RoundedImageView img_head;
        TextView text_name;
        TextView text_time;
        ImageView img_share;
        LinearLayout ll_concern_father;
        ImageView img_like;
        TextView text_like_num;
        ImageView img_comment;
        TextView text_comment_num;
        LinearLayout ll_first;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            img_head = itemView.findViewById(R.id.img_head);
            text_name = itemView.findViewById(R.id.text_name);
            text_time = itemView.findViewById(R.id.text_time);
            img_share = itemView.findViewById(R.id.img_share);
            ll_concern_father = itemView.findViewById(R.id.ll_concern_father);
            img_like = itemView.findViewById(R.id.img_like);
            text_like_num = itemView.findViewById(R.id.text_like_num);
            img_comment = itemView.findViewById(R.id.img_comment);
            text_comment_num = itemView.findViewById(R.id.text_comment_num);
            ll_first = itemView.findViewById(R.id.ll_first);
        }
    }
}
