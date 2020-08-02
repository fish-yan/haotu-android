package com.dmeyc.dmestoreyfm.adapter.photo;

import java.util.ArrayList;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class GridImageAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<String> dataList;
	private ArrayList<String> dec;
    private ImageLoader loader;
    private DisplayImageOptions options;

    private String desc="";
    ArrayList<String>aldes=new ArrayList<>();
    ArrayList<EditText> deset=new ArrayList<>();
	public GridImageAdapter(Context c, ArrayList<String> dataList, ImageLoader loader, DisplayImageOptions options,ArrayList<String> dec) {
		this.mContext = c;
		this.dataList = dataList;
		this.loader = loader;
		this.options = options;
		this.dec=dec;
	}
    public GridImageAdapter(Context c, ArrayList<String> dataList, ImageLoader loader, DisplayImageOptions options) {
        this.mContext = c;
        this.dataList = dataList;
        this.loader = loader;
        this.options = options;

    }

	@Override
	public int getCount() {
		return dataList.size();
	}

	@Override
	public Object getItem(int position) {
		return dataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view;
		ViewHolder holder;

		if (convertView != null) {
			view = convertView;
			holder = (ViewHolder) view.getTag();
		} else {
			view = View.inflate(mContext, R.layout.item_grid_img, null);
			holder = new ViewHolder();
			holder.imageview = (RoundedImageView) view.findViewById(R.id.row_gridview_imageview);
            holder.iv_delete=(ImageView) view.findViewById(R.id.iv_delete);
            holder.et_desc=(EditText) view.findViewById(R.id.et_desc);
			view.setTag(holder);
		}
		String path;
		if (dataList != null && position < dataList.size())
			path = dataList.get(position);
		else
			path = "camera_default";
		if (path.contains("camera_default"))
			holder.imageview.setImageResource(R.drawable.addphoto_button_pressed);
		else{
//			ImageLoader imageLoader = ImageLoader.getInstance();
//			DisplayImageOptions options = new DisplayImageOptions.Builder()
//			.cacheInMemory(true)
//			.cacheOnDisc(true)
//			.build();
//			if(-1==type){
//				loader.displayImage("file://"+path, holder.imageview, options);
//			}else {
				GlideUtil.loadImage(mContext,path,holder.imageview);
//			}

		}
		holder.iv_delete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				onDeletClick.onDelet(position);
			}
		});
		if(position<dec.size()){
			holder.et_desc.setText(dec.get(position));
		}
		if(!TextUtils.isEmpty(holder.et_desc.getText().toString().trim())&&desctype==1) {
			aldes.add(holder.et_desc.getText().toString().trim());
			if(position==dataList.size()-1){
//			onDeletClick.desc(desc);
				onDeletClick.desc(aldes);
			}
		}
		return view;
	}
	
	static class ViewHolder {
		RoundedImageView imageview;
		ImageView iv_delete;
		EditText et_desc;
	}
	int type=-1;
	public void setdefoud(int type){
		this.type=type;
	}
	OnDeletClick onDeletClick;
	public void setOnDeletClickList(OnDeletClick onDeletClick){
		this.onDeletClick=onDeletClick;
	}

	public interface OnDeletClick{
		void onDelet(int pos);
		void desc(String dec);
		void desc(ArrayList<String> dec);
	}

    int desctype;
	public void cleardata(int type){
		aldes.clear();
		desctype=type;
		notifyDataSetChanged();
	}
}
