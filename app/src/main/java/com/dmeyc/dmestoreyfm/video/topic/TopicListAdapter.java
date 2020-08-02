package com.dmeyc.dmestoreyfm.video.topic;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class TopicListAdapter extends CommonAdapter<TopicListBean.DataBean> {

    public TopicListAdapter(Context context, int layoutId, List<TopicListBean.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, TopicListBean.DataBean topicListBean, final int position) {
        RelativeLayout mParentLayout = holder.getView(R.id.mParentLayout);
        TextView tv_topic_name = holder.getView(R.id.tv_topic_name);
        if(!TextUtils.isEmpty(topicListBean.getContent())){
            tv_topic_name.setText("#"+topicListBean.getContent()+"#");
        }else{
            tv_topic_name.setText("");
        }
        mParentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener != null){
                    mListener.onItemClick(position);
                }
            }
        });
    }

    private OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
