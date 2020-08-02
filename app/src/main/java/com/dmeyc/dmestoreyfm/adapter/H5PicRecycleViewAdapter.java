package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.photo.GridImageAdapter;
import com.dmeyc.dmestoreyfm.ui.SorcerInActivity;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

public class H5PicRecycleViewAdapter extends RecyclerView.Adapter<H5PicRecycleViewAdapter.picViewHolder> {
    protected DisplayImageOptions options;
    private Context mContext;
    private ArrayList<String> dataList;
    private ArrayList<String> dec;
    private ArrayList<String> desc;
    protected ImageLoader loader;

    ArrayList<String>aldes=new ArrayList<>();
    public H5PicRecycleViewAdapter(Context c, ArrayList<String> dataList, ImageLoader loader, DisplayImageOptions options, ArrayList<String> dec) {
        this.mContext = c;
        this.dataList = dataList;
        this.loader = loader;
        this.options = options;
        this.dec=dec;
    }

    @Override
    public picViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_grid_img, null);
        return new picViewHolder(view);
    }


    @Override
    public void onBindViewHolder(picViewHolder holder, final int position) {
        String path;
//        if (dataList != null && position < dataList.size())
            path = dataList.get(position);
//        else
//            path = "camera_default";
//        if (path.contains("camera_default"))
//            holder.imageview.setImageResource(R.drawable.addphoto_button_pressed);
//        else{
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

//        }
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
        }
        if(position==dataList.size()-1){
            onDeletClick.desc(aldes);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public class picViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView imageview;
        ImageView iv_delete;
        EditText et_desc;
        public picViewHolder(View itemView) {
            super(itemView);
            imageview = (RoundedImageView) itemView.findViewById(R.id.row_gridview_imageview);
            iv_delete =(ImageView) itemView.findViewById(R.id.iv_delete);
            et_desc =(EditText) itemView.findViewById(R.id.et_desc);
        }
    }


    H5PicRecycleViewAdapter.OnDeletClick onDeletClick;
    public void setOnDeletClickList(H5PicRecycleViewAdapter.OnDeletClick onDeletClick){
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
