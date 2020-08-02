package com.dmeyc.dmestoreyfm.video.dynamicdetail.digs;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class DigsAdapter extends CommonAdapter<DigsUserBean> {

    public DigsAdapter(Context context, int layoutId, List<DigsUserBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, DigsUserBean digsUserBean, final int position) {
        TextView tv_follow = holder.getView(R.id.tv_follow);
        if(mListener != null){
            tv_follow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.OnFollowClick(position);
                }
            });
        }

    }

    private OnFollowClickListener mListener;

    public void setOnFollowClickListener(OnFollowClickListener listener){
        mListener = listener;
    }

    public interface OnFollowClickListener{
        void OnFollowClick(int position);
    }
}
