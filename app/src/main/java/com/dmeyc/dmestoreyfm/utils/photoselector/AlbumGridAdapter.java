
package com.dmeyc.dmestoreyfm.utils.photoselector;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;

import java.io.File;
import java.util.ArrayList;

public class AlbumGridAdapter extends BaseAdapter {

    private BaseActivity mContext;
    private ArrayList<String> mDataList;
    private ArrayList<String> mSelects;
    private GridView mGridView;

    public AlbumGridAdapter(Context ctx, ArrayList<String> albums, ArrayList<String> selects,
                            GridView gridView) {
        mContext = (BaseActivity) ctx;
        mDataList = albums;
        mSelects = selects;
        mGridView = gridView;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mContext.getLayoutInflater()
                    .inflate(R.layout.item_photo_thumb_view, null);
            holder = new ViewHolder();
            holder.iv_select = (ImageView) convertView.findViewById(R.id.selectview);
            holder.iv = (ImageView) convertView.findViewById(R.id.photoview);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String path = mDataList.get(position);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(R.drawable.image_default)
                .placeholder(R.drawable.image_default);
        Glide.with(mContext).load(new File(path)).apply(options).into(holder.iv);

        if (mSelects.contains(path)) {
            holder.iv_select.setVisibility(View.VISIBLE);
        } else {
            holder.iv_select.setVisibility(View.GONE);
        }

        return convertView;
    }

    public class ViewHolder {
        public ImageView iv;
        public ImageView iv_select;

    }
}
