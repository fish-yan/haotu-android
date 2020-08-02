package com.dmeyc.dmestoreyfm.ui.order.section;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.LogisticBean;
import com.dmeyc.dmestoreyfm.bean.OrderDetailBean;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.wedgit.sectioned.SectionedRecyclerViewAdapter;
import com.dmeyc.dmestoreyfm.wedgit.sectioned.StatelessSection;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LogisticsSection extends StatelessSection {

    private Context context;
    private SectionedRecyclerViewAdapter sectionedRecyclerViewAdapter;
    private List<LogisticBean.DataBean> list = new ArrayList();
    private List<OrderDetailBean> list_head = new ArrayList();

    public LogisticsSection(Context context, SectionedRecyclerViewAdapter sectionedRecyclerViewAdapter) {
        super(R.layout.section_head_logistics, R.layout.section_body_logistics);
        this.context = context;
        this.sectionedRecyclerViewAdapter = sectionedRecyclerViewAdapter;
    }

    public void setData(List<LogisticBean.DataBean> data, List<OrderDetailBean> list_head) {
        this.list.clear();
        this.list_head.clear();
        this.list.addAll(data);
        this.list_head.addAll(list_head);
        sectionedRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public int getContentItemsTotal() {
        return list.get(0).getTraces().size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new LogisticsHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        LogisticsHolder logisticsHolder = (LogisticsHolder) holder;
        if (position == 0) {
            logisticsHolder.text_content.setTextColor(context.getResources().getColor(R.color.color_1a1a1a));
            logisticsHolder.text_time.setTextColor(context.getResources().getColor(R.color.color_1a1a1a));
        } else {
            logisticsHolder.text_content.setTextColor(context.getResources().getColor(R.color.indicator_normal_color));
            logisticsHolder.text_time.setTextColor(context.getResources().getColor(R.color.indicator_normal_color));
        }
        logisticsHolder.text_content.setText(Html.fromHtml(list.get(0).getTraces().get(position).getAcceptStation()));
        logisticsHolder.text_time.setText(Html.fromHtml(list.get(0).getTraces().get(position).getAcceptTime()));
    }

    static class LogisticsHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.text_content)
        TextView text_content;
        @Bind(R.id.text_time)
        TextView text_time;

        LogisticsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new LogisticsHolderHead(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        LogisticsHolderHead logisticsHolderHead = (LogisticsHolderHead) holder;
        GlideUtil.loadImage(context, list_head.get(0).getData().get(0).get(0).getThumbnail(), logisticsHolderHead.img_head);
        logisticsHolderHead.text_title.setText(list_head.get(0).getData().get(0).get(0).getName());
        logisticsHolderHead.text_numb.setText("运单号：" + list_head.get(0).getData().get(0).get(0).getDeliveryNumber());
        logisticsHolderHead.text_address.setText("收件地址：" + list_head.get(0).getData().get(0).get(0).getAddress());
        logisticsHolderHead.text_name.setText("收件人：" + list_head.get(0).getData().get(0).get(0).getReceiverPeople() + "  "
                + list_head.get(0).getData().get(0).get(0).getPhone());
    }

    static class LogisticsHolderHead extends RecyclerView.ViewHolder {
        @Bind(R.id.img_head)
        ImageView img_head;
        @Bind(R.id.text_title)
        TextView text_title;
        @Bind(R.id.text_numb)
        TextView text_numb;
        @Bind(R.id.text_address)
        TextView text_address;
        @Bind(R.id.text_name)
        TextView text_name;

        LogisticsHolderHead(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
