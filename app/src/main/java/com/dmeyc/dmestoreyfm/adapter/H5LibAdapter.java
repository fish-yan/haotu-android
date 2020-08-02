package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.H5TeampBean;
import com.dmeyc.dmestoreyfm.ui.H5PicLibActivty;
import com.dmeyc.dmestoreyfm.ui.H5ShareWebviewActivity;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

public class H5LibAdapter extends RecyclerView.Adapter<H5LibAdapter.picViewHolder> {
//    protected DisplayImageOptions options;
    private Context mContext;
//    protected ImageLoader loader;
    private H5TeampBean h5TeampBean;
    private int teampid;
    ArrayList<String>aldes=new ArrayList<>();
    public H5LibAdapter(Context c, H5TeampBean h5TeampBean, int teampid) {
        this.mContext = c;
        this.h5TeampBean = h5TeampBean;
        this.teampid=teampid;
    }

    @Override
    public H5LibAdapter.picViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.adapter_h5lib, null);
        return new H5LibAdapter.picViewHolder(view);
    }

   int click=-1;
    @Override
    public void onBindViewHolder(H5LibAdapter.picViewHolder holder, final int position) {
              GlideUtil.loadImage(mContext,h5TeampBean.getData().get(position).getImgUrl(),holder.imageview);
              holder.btn_regist2.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      mContext.startActivity(new Intent(mContext,H5ShareWebviewActivity.class).putExtra("teampid",teampid).putExtra("url",h5TeampBean.getData().get(position).getUrl()));
//                      ToastUtil.show(position+"wobeiaile");
                  }
              });
              if(click==position){
                  holder.btn_regist2.setVisibility(View.VISIBLE);
              }else {
                  holder.btn_regist2.setVisibility(View.GONE);
              }
              holder.rl_itemview.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      click=position;
                      notifyDataSetChanged();
                  }
              });
    }
    @Override
    public int getItemCount() {
        return h5TeampBean.getData().size();
    }

    public class picViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView imageview;
        Button btn_regist2;
        RelativeLayout rl_itemview;
//        ImageView iv_delete;
//        EditText et_desc;
        public picViewHolder(View itemView) {
            super(itemView);
            imageview = (RoundedImageView) itemView.findViewById(R.id.row_gridview_imageview);
            btn_regist2=(Button) itemView.findViewById(R.id.btn_regist2);
            rl_itemview=(RelativeLayout)itemView.findViewById(R.id.rl_itemview);
//            iv_delete =(ImageView) itemView.findViewById(R.id.iv_delete);
//            et_desc =(EditText) itemView.findViewById(R.id.et_desc);
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

