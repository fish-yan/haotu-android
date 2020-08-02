package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.common.ReviewsBean;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by jockie on 2018/1/4
 * Email:jockie911@gmail.com
 */

public class LookAdapter extends BaseRvAdapter<ReviewsBean> {

    public LookAdapter(Context context, int layoutId, List<ReviewsBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, ReviewsBean bean, final int position) {
       LinearLayout linearLayout = (LinearLayout)holder.getView(R.id.actionitem);
       if(linearLayout!=null){
           linearLayout.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   onItemClickLisenter.onIntemClick(position);
               }
           });
       }

//        final LikeView likeview = holder.getView(R.id.item_like_view);
//        likeview.setStatus(false,bean.getLikeCount());
//        likeview.setOnClickListener(new LikeView.OnClickListener() {
//            @Override
//            public void onClick() {
//                if(Util.checkLoginStatus(mContext)){
//                    likeview.clickReSetStatus();
//                }
//            }
//        });
//        TextView itemTvContent = holder.getView(R.id.item_tv_content);
//        TextView itemTvNickName = holder.getView(R.id.item_tv_nickname);
//        final RoundedImageView roundedImageView = holder.getView(R.id.item_roundiv);
//        roundedImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        itemTvContent.setText(bean.getContent());
//        itemTvNickName.setText(bean.getNickname());
//        GlideUtil.loadImage(BaseApp.getContext(),bean.getAvatar(),roundedImageView);

//        RecyclerView rvLookview = holder.getView(R.id.item_rv_look);
//        rvLookview.setLayoutManager(new LinearLayoutManager(holder.getConvertView().getContext(),LinearLayoutManager.HORIZONTAL,false));
//
//        Object tag = rvLookview.getTag(R.id.tag);
//        if(tag == null){
//            rvLookview.setTag(R.id.tag,true);
//            rvLookview.addItemDecoration(new RecyclerView.ItemDecoration() {
//
//                @Override
//                public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                    int position = parent.getChildLayoutPosition(view);
//                    if(outRect.left > 0)
//                        return;
//                    if(position == 0){
//                        outRect.left = (ScreemUtil.getWidth() - DensityUtil.dip2px(280))/2;
//                    }else{
//                        outRect.left = DensityUtil.dip2px(15);
//                    }
//                }
//            });
//        }
//        rvLookview.setAdapter(new CommonAdapter<ReviewsBean.ReviewImagesBean>(holder.getConvertView().getContext(),R.layout.item_rv_pic_look, bean.getReviewImages() ){

//            @Override
//            protected void convert(ViewHolder holder, final ReviewsBean.ReviewImagesBean s, int position) {
//                RoundedImageView imageView = holder.getView(R.id.item_iv);
//                roundedImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                GlideUtil.loadImage(holder.getConvertView().getContext(),s.getSource(),imageView);
//                imageView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(mContext, ShowPicActivity.class);
//                        intent.putExtra("pic",s.getSource());
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        mContext.startActivity(intent);
//                    }
//                });
//            }

//            @Override
//            public int getItemCount() {
//                return mDatas == null ? 0 : mDatas.size();
//            }
//        });
    }

    private  OnItemClickLisenter onItemClickLisenter;

    public void setOnActionClick(OnItemClickLisenter onItemClickLisenter){
        this.onItemClickLisenter=onItemClickLisenter;
    }


    public  interface OnItemClickLisenter{
        void onIntemClick(int pos);
    }
}
