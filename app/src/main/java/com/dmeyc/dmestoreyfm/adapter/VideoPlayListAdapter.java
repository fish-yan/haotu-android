package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.newui.home.groupaction.actiondetail.ActionDetailActivity;
import com.dmeyc.dmestoreyfm.newui.pagerdetail.other.OtherDetailActivity;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.StringUtil;
import com.dmeyc.dmestoreyfm.video.index.IndexDynamicBean;
import com.orhanobut.logger.Logger;
import com.tencent.liteav.demo.play.SuperPlayerConst;
import com.tencent.liteav.demo.play.SuperPlayerView;

import java.util.ArrayList;
import java.util.List;


/**
 * create by cxg on 2019/11/27
 */
public class VideoPlayListAdapter extends BaseMultiItemQuickAdapter<IndexDynamicBean.DataBean, BaseViewHolder> {

    private Context mContext;

    public VideoPlayListAdapter(Context context, List<IndexDynamicBean.DataBean> data) {
        super(data);
        addItemType(Constant.AdapterItemType.TYPE_HOME_VIDEO, R.layout.item_video_play);
        addItemType(Constant.AdapterItemType.TYPE_SEARCH_VIDEO, R.layout.adapter_video_search);
        addItemType(Constant.AdapterItemType.TYPE_ME_VIDEO, R.layout.adapter_video);
        addItemType(Constant.AdapterItemType.TYPE_ME_VIDEO_LIVE, R.layout.adapter_video);
        addItemType(Constant.AdapterItemType.TYPE_ME_VIDEO_LIKE, R.layout.adapter_video);
        mContext = context;
    }

    @Override
    protected void convert(final BaseViewHolder holder, final IndexDynamicBean.DataBean item) {
        if (holder == null || item == null) {
            return;
        }
        switch (holder.getItemViewType()) {
            case Constant.AdapterItemType.TYPE_HOME_VIDEO:
                holder.setText(R.id.tv_share_count, item.getForwardCount())
                        .setText(R.id.tv_comment_count, item.getCommentCount())
                        .setText(R.id.tv_like_count, item.getLikeCount() + "")
                        .setText(R.id.tv_content, item.getContent())
                        .addOnClickListener(R.id.iv_share)
                        .addOnClickListener(R.id.iv_comment)
                        .addOnClickListener(R.id.iv_like)
                        .addOnClickListener(R.id.civ_header);
                if ("1".equals(item.getIsLiveBroadCast())) {
                    holder.getView(R.id.iv_living_flag).setVisibility(View.VISIBLE);
                } else {
                    holder.getView(R.id.iv_living_flag).setVisibility(View.GONE);
                }
                loadNextImage(item,holder.getView(R.id.rv_images));

                if (item.getLinkedType() != null) {// 判断广告
                    holder.setText(R.id.tv_ad_name, item.getLinkedTitle());
                    GlideUtil.loadImage(mContext, item.getLinkedImage(), (ImageView) holder.getView(R.id.iv_ad_icon));
                    holder.getView(R.id.iv_ad_close).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            holder.getView(R.id.cl_advice).setVisibility(View.GONE);
                        }
                    });
                    switch (item.getLinkedType()) {
                        case "1":
                        case "2":
                        case "4":
                        case "5":
                            holder.setText(R.id.tv_ad_instruction, "最新信息点击查看");
                            holder.getView(R.id.cl_advice).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (!TextUtils.isEmpty(item.getLinkedId()))
                                        OtherDetailActivity.newInstance(mContext, item.getLinkedId());
                                }
                            });
                            break;
                        case "3":
                            holder.setText(R.id.tv_ad_instruction, "最新活动信息点击查看");
                            holder.getView(R.id.cl_advice).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (!TextUtils.isEmpty(item.getLinkedId()))
                                        ActionDetailActivity.newInstance(mContext, item.getLinkedId());
                                }
                            });
                            break;
                    }
                }

                String author = "@" + item.getUserNickName() + " 的创作";
                StringUtil.setTVColor(author, 0, author.length() - 3, mContext.getResources().getColor(R.color.color_FFA700), (TextView) holder.getView(R.id.tv_author));
                GlideUtil.loadImage(mContext, item.getUserImageUrl(), (ImageView) holder.getView(R.id.civ_header));
                setLikeImage(item, (ImageView) holder.getView(R.id.iv_like));
                RecyclerView rvImages = (RecyclerView) holder.getView(R.id.rv_images);
                rvImages.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
                HomeImageAdapter adapter = new HomeImageAdapter(mContext);
                rvImages.setAdapter(adapter);
                adapter.bindToRecyclerView(rvImages);
                List<String> imageUrls = new ArrayList<>();
                if (item.getType() == 1) {//视频
                    imageUrls.add(item.getCoverUrl());
                    holder.getView(R.id.view_spv_cover).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SuperPlayerView superPlayerView = ((SuperPlayerView) holder.getView(R.id.mViewPlayer));
                            if (superPlayerView.getVodController().isPlaying()) {
                                holder.getView(R.id.iv_video_pause).setVisibility(View.VISIBLE);
                                superPlayerView.onPause();
                            } else {
                                holder.getView(R.id.iv_video_pause).setVisibility(View.GONE);
                                superPlayerView.onResume();
                            }

                        }
                    });
                } else if (item.getType() == 2) {//图片
                    if (item.getImageUrls() != null) {
                        for (IndexDynamicBean.DataBean.ImageUrlBean imageUrlBean : item.getImageUrls()) {
                            imageUrls.add(imageUrlBean.getUrl());
                        }
                    }

                }
                adapter.replaceData(imageUrls);
                break;
            case Constant.AdapterItemType.TYPE_SEARCH_VIDEO:
                GlideUtil.loacImageCenterCrop(mContext, item.getUserImageUrl(), (ImageView) holder.getView(R.id.icon_header));
                loadImageByType(item, (ImageView) holder.getView(R.id.imageView));
                setLikeImage(item, (ImageView) holder.getView(R.id.iv_like));

                holder.setText(R.id.tv_nike_name, item.getUserNickName())
                        .setText(R.id.tv_remark, item.getContent())
                        .setText(R.id.tv_like_count, item.getLikeCount() + "");
                break;
            case Constant.AdapterItemType.TYPE_ME_VIDEO:
                loadImageByType(item, (ImageView) holder.getView(R.id.iv_bg));
                ((ImageView) holder.getView(R.id.iv_icon)).setImageResource(R.drawable.icon_play);
                holder.setText(R.id.tv_count, item.getSeeCount() + "");
                break;
            case Constant.AdapterItemType.TYPE_ME_VIDEO_LIKE:
                loadImageByType(item, (ImageView) holder.getView(R.id.iv_bg));
                setLikeImage(item, (ImageView) holder.getView(R.id.iv_icon));
                holder.setText(R.id.tv_count, item.getLikeCount() + "");
                break;
        }

    }

    /**
     * type 1 视频、2图片
     */
    private void loadImageByType(IndexDynamicBean.DataBean item, ImageView imageView) {
        if (item.getType() == 1) {//视频
            GlideUtil.loacImageCenterCrop(mContext, item.getCoverUrl(), imageView);

        } else if (item.getType() == 2) {//图片
            if (item.getImageUrls() == null || item.getImageUrls().size() == 0) {
                GlideUtil.loacImageCenterCrop(mContext, "", imageView);
            } else {
                GlideUtil.loacImageCenterCrop(mContext, item.getImageUrls().get(0).getThumbnailUrl(), imageView);
            }
        }
    }

    private void setLikeImage(IndexDynamicBean.DataBean item, ImageView imageView) {
        if (item.getIsLike() == 1) {
            imageView.setImageResource(R.drawable.icon_like_like);
        } else {
            imageView.setImageResource(R.drawable.icon_like_normal);
        }
    }

    private void loadNextImage(IndexDynamicBean.DataBean item,View view) {
        try {
            int position = mData.indexOf(item);
            Logger.d("pre position:" + position);
            IndexDynamicBean.DataBean nextItem = mData.get(position++);
            String nextImageUrl = "";
            if (nextItem.getType() == 1){
                nextImageUrl = nextItem.getCoverUrl();
            }else {
                nextImageUrl = nextItem.getImageUrls().get(0).getUrl();
            }
            GlideUtil.preLoadImage(mContext,nextImageUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
