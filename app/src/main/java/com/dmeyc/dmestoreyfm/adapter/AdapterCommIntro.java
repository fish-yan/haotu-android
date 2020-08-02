package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

public class AdapterCommIntro extends  RecyclerView.Adapter<AdapterCommIntro.picViewHolder> {
    protected DisplayImageOptions options;
    private Context mContext;
    private ArrayList<String> dataList;
    private ArrayList<String> dec;
    private ArrayList<String> desc;
    protected ImageLoader loader;

    ArrayList<String>aldes=new ArrayList<>();
    public AdapterCommIntro(Context c, ArrayList<String> dataList, ImageLoader loader, DisplayImageOptions options, ArrayList<String> dec) {
        this.mContext = c;
        this.dataList = dataList;
        this.loader = loader;
        this.options = options;
        this.dec=dec;
    }

    @Override
    public AdapterCommIntro.picViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_grid_img, null);
        return new AdapterCommIntro.picViewHolder(view);
    }


    @Override
    public void onBindViewHolder(AdapterCommIntro.picViewHolder holder, final int position) {
        String path;
//        if (dataList != null && position < dataList.size())
        path = dataList.get(position);
//        else
//            path = "camera_default";
//        if (path.contains("camera_default")){
//            holder.imageview.setImageResource(R.drawable.addphoto_button_pressed);
//            holder.iv_delete.setVisibility(View.GONE);
//        }
//        else{
//                ImageLoader imageLoader = ImageLoader.getInstance();
//                DisplayImageOptions options = new DisplayImageOptions.Builder()
//                        .cacheInMemory(true)
//                        .cacheOnDisc(true)
//                        .build();
        GlideUtil.loadImage(mContext,path,holder.imageview);


//        }
//        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onDeletClick.onDelet(position);
//            }
//        });

//        if(position<dec.size()){
//            holder.et_desc.setText(dec.get(position));
//        }
//        if(!TextUtils.isEmpty(holder.et_desc.getText().toString().trim())&&desctype==1) {
//            aldes.add(holder.et_desc.getText().toString().trim());
//        }
//        if(position==dataList.size()-1){
//            onDeletClick.desc(aldes);
//        }
        holder.imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDeletClick.onDelet(position);
            }
        });
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


    AdapterCommIntro.OnDeletClick onDeletClick;
    public void setOnDeletClickList(AdapterCommIntro.OnDeletClick onDeletClick){
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

