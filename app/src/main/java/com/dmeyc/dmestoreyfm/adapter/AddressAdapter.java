package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.AddressBean;
import com.dmeyc.dmestoreyfm.ui.EditAddressActivity;
import com.dmeyc.dmestoreyfm.ui.ManageAddressActivity;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by jockie on 2018/1/8
 * Email:jockie911@gmail.com
 */

public class AddressAdapter extends BaseRvAdapter<AddressBean.DataBean.ReceiverBean>{

    /**
     * @param context
     * @param layoutId
     * @param datas    不能为null
     */
    public AddressAdapter(Context context, int layoutId, List<AddressBean.DataBean.ReceiverBean> datas) {
        super(context, layoutId, datas);
    }




    @Override
    protected void convert(ViewHolder holder, AddressBean.DataBean.ReceiverBean address, final int position) {
        holder.getView(R.id.item_tv_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EditAddressActivity.class);
                intent.putExtra("item",mDatas.get(position));
                intent.putExtra("position",position);
                ((ManageAddressActivity)mContext).startActivityForResult(intent,ManageAddressActivity.REQUEST_EDIT_ADDRESS);
            }
        });
        TextView itemTvDetail = holder.getView(R.id.item_tv_detail);
        TextView itemTvName = holder.getView(R.id.item_tv_name);
        TextView itemTvPhone = holder.getView(R.id.item_tv_phone);
        TextView itemTvAddress = holder.getView(R.id.item_tv_address);

        itemTvName.setText(address.getReceiverPeople());
        itemTvAddress.setText(address.getArea());
        itemTvPhone.setText(address.getMobile());
        itemTvDetail.setText(address.getAddress());
    }

    public void addData(AddressBean.DataBean.ReceiverBean address){
        mDatas.add(0,address);
        notifyDataSetChanged();
    }

    public void replaceData(int position,AddressBean.DataBean.ReceiverBean address){
        mDatas.remove(position);
        mDatas.add(position,address);
        notifyDataSetChanged();
    }
}
