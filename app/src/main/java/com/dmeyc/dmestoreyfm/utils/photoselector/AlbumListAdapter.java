package com.dmeyc.dmestoreyfm.utils.photoselector;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dmeyc.dmestoreyfm.R;

import java.io.File;
import java.util.List;

public class AlbumListAdapter extends BaseAdapter {

	private Activity mContext;
	private List<AlbumVO> mDataList;
	private ListView mListView;


	public AlbumListAdapter(Context ctx, List<AlbumVO> albumVOs, ListView lv) {
		mContext = (Activity) ctx;
		mDataList = albumVOs;
		mListView = lv;
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
			convertView = mContext.getLayoutInflater().inflate(R.layout.item_photo_folder_list, null);
			holder = new ViewHolder();
			holder.folderNameTxt = (TextView) convertView.findViewById(R.id.folderName);
			holder.iv = (ImageView) convertView.findViewById(R.id.thumb);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		AlbumVO mAlbumVO = mDataList.get(position);
		
		if (mAlbumVO != null){
			holder.folderNameTxt.setText(String.format("%s (%d)", mAlbumVO.albumName,mAlbumVO.albumIamges.size()));
			if(mAlbumVO.albumIamges.size()>0){
			    String pathFirst = mAlbumVO.albumIamges.get(0);
				if(pathFirst==null)
					return convertView;
				
	/*			holder.iv.setTag(pathFirst);
				Bitmap b=mLoader.loadBitmap(Uri.fromFile(new File(pathFirst)), holder.iv, new ImageUriCallback() {
					
					@Override
					public void imageLoaded(Bitmap imageBitmap, String imageUrl, Uri uri) {
						ImageView iv=(ImageView) mListView.findViewWithTag(uri.getPath());
						if(iv!=null)
							iv.setImageBitmap(imageBitmap);
					}
					
					@Override
					public void imageLoadFailed(Bitmap imageBitmap, String imageUrl, Uri uri) {
						
					}
				}, 1.0f, true,null);
				
				if(b!=null)
					holder.iv.setImageBitmap(b);
				else
					holder.iv.setImageResource(R.drawable.moren);*/
				RequestOptions options = new RequestOptions()
						.centerCrop()
						.error(R.drawable.image_default)
						.placeholder(R.drawable.image_default);
				Glide.with(mContext).load(new File(pathFirst)).apply(options).into(holder.iv);
			}
			
		}
		
		
		return convertView;
	}

	public class ViewHolder {
		public ImageView iv;
		public TextView folderNameTxt;

	}
}
