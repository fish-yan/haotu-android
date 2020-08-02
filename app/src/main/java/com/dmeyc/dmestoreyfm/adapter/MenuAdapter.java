package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.MenuItemBean;
import com.dmeyc.dmestoreyfm.config.MenuConfig;

import java.util.List;

/**
 * create by cxg on 2019/11/23
 */
public class MenuAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<MenuItemBean> mDataList;

    private ICallBack iCallBack;

    public MenuAdapter(Context context, List<MenuItemBean> list,ICallBack iCallBack){
        this.mContext = context;
        this.mDataList = list;
        this.iCallBack = iCallBack;

    }
    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return mDataList==null?0:mDataList.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public MenuItemBean getItem(int position) {
        return mDataList.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View layout =LayoutInflater.from(mContext).inflate(R.layout.item_menu,null,false);
        ((TextView) layout.findViewById(R.id.tv_menu_title)).setText(mDataList.get(position).title);
        if (mDataList.get(position).icon!=0){
            layout.findViewById(R.id.iv_menu_icon).setBackgroundResource(mDataList.get(position).icon);
        }
        if (iCallBack!=null){
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iCallBack.onMenuItemClick(mDataList.get(position).menuType);
                }
            });
        }
        return layout;
    }

    public interface ICallBack{
        void onMenuItemClick(MenuConfig.MenuType menuType);
    }
}
