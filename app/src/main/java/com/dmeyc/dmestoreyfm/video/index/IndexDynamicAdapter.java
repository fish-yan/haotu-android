package com.dmeyc.dmestoreyfm.video.index;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.NewWhoPkBean;
import com.dmeyc.dmestoreyfm.ui.BannerClickActivity;
import com.dmeyc.dmestoreyfm.ui.BigActionActivity;
import com.dmeyc.dmestoreyfm.ui.PkResultActivity;
import com.dmeyc.dmestoreyfm.utils.DataClass;
import com.dmeyc.dmestoreyfm.utils.ScreenUtil;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.video.newbrand.NewBrandActivity;
import com.dmeyc.dmestoreyfm.video.pkactivity.NewPkActivity;
import com.dmeyc.dmestoreyfm.video.topicutils.OnTopicClickListener;
import com.dmeyc.dmestoreyfm.video.topicutils.SpanWeiBoTextUtils;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.dmeyc.dmestoreyfm.wedgit.GlideImageLoader;
import com.taobao.library.VerticalBannerView;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.List;

public class IndexDynamicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<IndexDynamicBean.DataBean> mListData;
    private int[] heights;
    private final int TYPE_HEADER = 0;
    private final int TYPE_NORMAL = 1;

    private ArrayList<String> banners = new ArrayList<>();
    private ArrayList<NewWhoPkBean.DataBean> verticalBannerData = new ArrayList<>();

    public IndexDynamicAdapter(Context context, List<IndexDynamicBean.DataBean> datas) {
        mContext = context;
        mListData = datas;
        heights = new int[]{ScreenUtil.dp2px(mContext, 173), ScreenUtil.dp2px(mContext, 191)};
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(holder.getLayoutPosition() == 0);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.index_dynamic_header_layout, parent, false);
            return new HeaderViewHolder(view);
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_index_dynamic_layout, null, false);
        IndexDynamicViewHolder myViewHolder = new IndexDynamicViewHolder(view);
        return myViewHolder;
    }

    private VerticalBannerAdapter adapter;

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case TYPE_HEADER:
                HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
                headerViewHolder.layout_yumaoqiu_one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        NewBrandActivity.newIntent((Activity) mContext, "羽毛球");
                    }
                });
                headerViewHolder.layout_yundong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        NewBrandActivity.newIntent((Activity) mContext, "运动教社");
                    }
                });
                headerViewHolder.layout_business.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        NewBrandActivity.newIntent((Activity) mContext, "合作商户");
                    }
                });
                LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

                if (banners.size() > 0) {
                    headerViewHolder.banner.setVisibility(View.VISIBLE);
                    headerViewHolder.banner.setImageLoader(new GlideImageLoader());
                    headerViewHolder.banner.setImages(banners);
                    headerViewHolder.banner.start();
                    headerViewHolder.banner.setOnBannerClickListener(new OnBannerClickListener() {
                        @Override
                        public void OnBannerClick(int position) {
                            if (2 == position) {
                                mContext.startActivity(new Intent(mContext, BigActionActivity.class));
                            } else if (1 == position) {
                                mContext.startActivity(new Intent(mContext, BannerClickActivity.class));
                            }
                        }
                    });
                } else {
                    headerViewHolder.banner.setVisibility(View.GONE);
                }

                headerViewHolder.mPkName.setTag("1000" + position);

                headerViewHolder.mPkLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        NewPkActivity.newIntent((Activity) mContext);
                    }
                });
//                    if(adapter == null){
//                        adapter = new VerticalBannerAdapter(verticalBannerData);
//                        headerViewHolder.mPkName.setAdapter(adapter);
//                    }
                headerViewHolder.mPkName.setClickable(false);
                headerViewHolder.mPkName.setLongClickable(false);
                if (verticalBannerData.size() > 0) {
                    if (headerViewHolder.mPkName.getAdapter() != null) {
                        headerViewHolder.mPkName.stop();
                        headerViewHolder.mPkName.start();
                    }
                }
//                    adapter.setOnContentClickListener(new VerticalBannerAdapter.OnContentClickListener() {
//                        @Override
//                        public void onContentClick(NewWhoPkBean.DataBean bean) {
//                            //  跳转
//                            mContext.startActivity(new Intent(mContext, PkResultActivity.class)
//                                    .putExtra("ispked", bean.getIsPked())
//                                    .putExtra("PK_groupid", bean.getGroup_pk_id())
//                                    .putExtra("headurl", bean.getGroup_a_logo())
//                                    .putExtra("graopname", bean.getGroup_a_name()));
//                        }
//                    });
                break;
            case TYPE_NORMAL:
                IndexDynamicViewHolder dynamicViewHolder = (IndexDynamicViewHolder) holder;

                dynamicViewHolder.dynamic_parent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mListener != null) {
                            mListener.onItemClick(position);
                        }
                    }
                });
                ViewGroup.LayoutParams layoutParams = dynamicViewHolder.rl_media_parent.getLayoutParams();
                if (position % 2 != 0) {
                    layoutParams.height = heights[0];
                } else {
                    layoutParams.height = heights[1];
                }
                int mCurrentPosition = position - 1;
                IndexDynamicBean.DataBean mBean = mListData.get(mCurrentPosition);
                if (!TextUtils.isEmpty(mBean.getContent())) {
                    dynamicViewHolder.tv_content.setText(SpanWeiBoTextUtils.getWeiBoContent(mContext, mBean.getContent(), dynamicViewHolder.tv_content, new OnTopicClickListener() {
                        @Override
                        public void onClick(String topic) {
                            //
//                            ToastUtil.show("点击了话题："+topic);
                        }
                    }));
                } else {
                    dynamicViewHolder.tv_content.setText("");
                }
                if (!TextUtils.isEmpty(mBean.getUserNickName())) {
                    dynamicViewHolder.user_name.setText(mBean.getUserNickName());
                } else {
                    dynamicViewHolder.user_name.setText("");
                }

                dynamicViewHolder.digs.setText(mBean.getLikeCount() + "");

                if (TextUtils.isEmpty(DataClass.LocationDistrict)) {
                    dynamicViewHolder.mDistance.setVisibility(View.GONE);
                    dynamicViewHolder.mDistanceLayout.setVisibility(View.GONE);
                    dynamicViewHolder.mDistance.setText("");
                } else {
                    if (TextUtils.isEmpty(mBean.getDistance())) {
                        dynamicViewHolder.mDistance.setVisibility(View.GONE);
                        dynamicViewHolder.mDistanceLayout.setVisibility(View.GONE);
                        dynamicViewHolder.mDistance.setText("");
                    } else {
                        dynamicViewHolder.mDistance.setVisibility(View.VISIBLE);
                        dynamicViewHolder.mDistanceLayout.setVisibility(View.VISIBLE);
                        dynamicViewHolder.mDistance.setText(mBean.getDistance());
                    }
                }
                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .error(R.drawable.image_default);
                Glide.with(mContext).load(mBean.getUserImageUrl()).apply(options).into(dynamicViewHolder.iv_user_pic);
                if (mBean.getType() == 1) {
                    // 视频
                    dynamicViewHolder.iv_video.setVisibility(View.VISIBLE);
                    Glide.with(mContext).load(mBean.getCoverUrl()).apply(options).into(dynamicViewHolder.img);
                } else {
                    // 图片
                    dynamicViewHolder.iv_video.setVisibility(View.GONE);
                    if (mBean.getImageUrls() != null && mBean.getImageUrls().size() > 0) {
                        Glide.with(mContext).load(mBean.getImageUrls().get(0).getUrl()).apply(options).into(dynamicViewHolder.img);
                    } else {
                        Glide.with(mContext).load(R.drawable.image_default).apply(options).into(dynamicViewHolder.img);
                    }
                }
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        return mListData.size() + 1;
    }


    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout layout_yumaoqiu_one;
        private final LinearLayout layout_yundong;
        private final LinearLayout layout_business;
        private final Banner banner;
        private final VerticalBannerView mPkName;
        private final CardView mPkLayout;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            layout_yumaoqiu_one = itemView.findViewById(R.id.layout_yumaoqiu_one);
            layout_yundong = itemView.findViewById(R.id.layout_yundong);
            layout_business = itemView.findViewById(R.id.layout_business);
            banner = itemView.findViewById(R.id.banner);
            mPkName = itemView.findViewById(R.id.verticalBanner);
            mPkLayout = itemView.findViewById(R.id.mPkLayout);
        }
    }

    public class IndexDynamicViewHolder extends RecyclerView.ViewHolder {
        private final ImageView img;
        private final RelativeLayout rl_media_parent;
        private final CardView dynamic_parent;
        private final TextView tv_content;
        private final CircleImageView iv_user_pic;
        private final TextView user_name;
        private final TextView digs;
        private final ImageView iv_video;
        private final TextView mDistance;
        private final LinearLayout mDistanceLayout;

        public IndexDynamicViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.iv_pic);
            rl_media_parent = itemView.findViewById(R.id.rl_media_parent);
            dynamic_parent = itemView.findViewById(R.id.dynamic_parent);
            tv_content = itemView.findViewById(R.id.tv_content);
            iv_user_pic = itemView.findViewById(R.id.iv_user_pic);
            user_name = itemView.findViewById(R.id.user_name);
            digs = itemView.findViewById(R.id.digs);
            iv_video = itemView.findViewById(R.id.iv_video);
            mDistance = itemView.findViewById(R.id.mDistance);
            mDistanceLayout = itemView.findViewById(R.id.mDistanceLayout);
        }
    }


    /**
     * 设置banner轮播
     */

    public void initBannerData(List<String> list) {
        banners.clear();
        banners.addAll(list);

    }

    public void initVerticalBannerData(List<NewWhoPkBean.DataBean> list) {
        verticalBannerData.clear();
        verticalBannerData.addAll(list);
        this.notifyItemChanged(0);
    }

    private OnIndexItemClickListener mListener;

    public void setOnIndexItemClickListener(OnIndexItemClickListener listener) {
        mListener = listener;
    }

    public interface OnIndexItemClickListener {
        void onItemClick(int position);
    }
}
