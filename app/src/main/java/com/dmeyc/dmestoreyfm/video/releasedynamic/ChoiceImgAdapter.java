package com.dmeyc.dmestoreyfm.video.releasedynamic;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dmeyc.dmestoreyfm.R;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

public class ChoiceImgAdapter extends CommonAdapter<String> {
    public final static String IS_ADD_PIC = "isadd";
    private int mType;
    public ChoiceImgAdapter(Context context, int layoutId, List<String> datas,int type) {
        super(context, layoutId, datas);
        mType = type;
    }

    @Override
    protected void convert(ViewHolder holder,final String s,final int position){
        ImageView iv = holder.getView(R.id.iv_choice);
        ImageView tv_video = holder.getView(R.id.tv_video);// 播放按钮控件
        ImageView ic_del = holder.getView(R.id.ic_del); // 删除控件

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.drawable.image_default);
        if(!IS_ADD_PIC.equals(s)){
            Glide.with(mContext).load(s).apply(options).into(iv);
            ic_del.setVisibility(View.VISIBLE);
            if(mType == ReleasedynamicActivity.TYPE_OF_VIDEO){
                tv_video.setVisibility(View.VISIBLE);
            }else{
                tv_video.setVisibility(View.GONE);
            }
        }else{
            Glide.with(mContext).load(R.mipmap.ic_add_media).apply(options).into(iv);
            ic_del.setVisibility(View.GONE);
            tv_video.setVisibility(View.GONE);
        }
        ic_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!IS_ADD_PIC.equals(s)){
                    mListener.onDelete(position);
                }
            }
        });
    }

    private onDeleteListener  mListener;

    public void setOnDeleteClickListener(onDeleteListener listener){
        mListener = listener;
    }

    public interface onDeleteListener{
        void onDelete(int position);
    }

}
