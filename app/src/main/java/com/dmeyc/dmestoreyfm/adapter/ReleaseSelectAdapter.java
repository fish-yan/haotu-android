package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.ReleaseItemBean;
import com.dmeyc.dmestoreyfm.config.ReleaseConfig;

import java.util.List;

public class ReleaseSelectAdapter extends BaseAdapter {
    private Context mContext;
    private List<ReleaseItemBean> mData;
    private ICallBack iCallBack;

    public ReleaseSelectAdapter(Context context, List<ReleaseItemBean> data) {
        this.mData = data;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_release_select, null, false);
        ((TextView) view.findViewById(R.id.tv_release_title)).setText(mData.get(position).title);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iCallBack != null) {
                    iCallBack.onItemClick(mData.get(position).type);
                }
            }
        });
        return view;
    }


    public void setiCallBack(ICallBack iCallBack) {
        this.iCallBack = iCallBack;
    }

    public interface ICallBack {
        void onItemClick(ReleaseConfig.Type type);
    }
}