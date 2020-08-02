package com.dmeyc.dmestoreyfm.video.shpodetail;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dmeyc.dmestoreyfm.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class CommentAdapter extends CommonAdapter<ShopDetailCommetModel.DataBean> {

    ContentClickListener listener;

    public void setListener(ContentClickListener listener) {
        this.listener = listener;
    }

    public CommentAdapter(Context context, int layoutId, List<ShopDetailCommetModel.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, ShopDetailCommetModel.DataBean s, final int position) {
        ImageView avatar = holder.getConvertView().findViewById(R.id.Avatar);
        ImageView isLike = holder.getConvertView().findViewById(R.id.isLike);
        holder.setText(R.id.userName, s.getUserNickName())
                .setText(R.id.createTime, s.getCreateTime())
                .setText(R.id.content, s.getContent());

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.drawable.image_default);

        Glide.with(mContext).load(s.getUserImageUrl()).apply(options).into(avatar);
        if (s.getIsLike() == 0) {
            isLike.setImageResource(R.drawable.icon_zan_dynamic);
        } else {
            isLike.setImageResource(R.drawable.icon_yizan_dynamic);
        }
        isLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onLikeClick(position);
                }
            }
        });
    }

    interface ContentClickListener {
        void onLikeClick(int position);
    }
}
