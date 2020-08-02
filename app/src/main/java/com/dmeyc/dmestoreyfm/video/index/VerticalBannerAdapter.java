package com.dmeyc.dmestoreyfm.video.index;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.NewWhoPkBean;
import com.taobao.library.BaseBannerAdapter;
import com.taobao.library.VerticalBannerView;

import java.util.List;

public class VerticalBannerAdapter extends BaseBannerAdapter<NewWhoPkBean.DataBean> {

    private OnContentClickListener onContentClickListener;

    public void setOnContentClickListener(OnContentClickListener onContentClickListener) {
        this.onContentClickListener = onContentClickListener;
    }

    public VerticalBannerAdapter(List<NewWhoPkBean.DataBean> datas) {
        super(datas);
    }

    @Override
    public View getView(VerticalBannerView verticalBannerView) {
        return LayoutInflater.from(verticalBannerView.getContext()).inflate(R.layout.item_verticalbanner_layout, null);
    }

    @Override
    public void setItem(View view, final NewWhoPkBean.DataBean s) {
        TextView content = view.findViewById(R.id.content);
        content.setText(s.getGroup_pk_name()+"正在进行中");
        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onContentClickListener != null) {
                    onContentClickListener.onContentClick(s);
                }
            }
        });
    }

    interface OnContentClickListener {

        void onContentClick(NewWhoPkBean.DataBean model);

    }

}
