package com.dmeyc.dmestoreyfm.video.index.indextoptype;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.dmeyc.dmestoreyfm.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class IndexTopTypeAdapter extends CommonAdapter<IndexTopTypeBean> {

    public IndexTopTypeAdapter(Context context, int layoutId, List<IndexTopTypeBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, IndexTopTypeBean indexTopTypeBean, final int position) {
        LinearLayout mTopTypeParentLayout = holder.getView(R.id.mTopTypeParentLayout);

        if(mListener != null){
            mTopTypeParentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.OnItemClick(position);
                }
            });
        }
    }


    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }
}
