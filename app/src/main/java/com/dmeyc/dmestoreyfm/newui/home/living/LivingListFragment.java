package com.dmeyc.dmestoreyfm.newui.home.living;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.LivingListAdapter;
import com.dmeyc.dmestoreyfm.adapter.VideoPlayListAdapter;
import com.dmeyc.dmestoreyfm.bean.response.LivingListBean;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.newbase.BaseRefreshFragment;
import com.dmeyc.dmestoreyfm.newui.common.CommentRequestHelper;
import com.dmeyc.dmestoreyfm.newui.home.living.largerimage.LookLargerImageActivity;
import com.dmeyc.dmestoreyfm.photolive.ILivingView;
import com.dmeyc.dmestoreyfm.utils.DensityUtil;
import com.dmeyc.dmestoreyfm.video.index.IndexDynamicBean;
import com.lzy.imagepicker.view.GridSpacingItemDecoration;

import java.util.List;
import java.util.Map;

/**
 * create by cxg on 2019/12/29
 */
public class LivingListFragment extends BaseRefreshFragment<ILivingListView, LivingListPresenter, LivingListBean.DataBean.ImageListBean, LivingListAdapter> implements ILivingListView {

    public static final String TYPE_TIME = "TYPE_TIME";
    public static final String TYPE_HOT = "TYPE_HOT";

    private String mTypeFrom;

    public static LivingListFragment newInstance(String type) {
        LivingListFragment fragment = new LivingListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ExtraKey.TYPE_FROM, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected LivingListAdapter getAdapter() {
        return new LivingListAdapter(getContext());
    }


    @Override
    protected LivingListPresenter createPresenter() {
        return new LivingListPresenter();
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        mTypeFrom = getArguments().getString(ExtraKey.TYPE_FROM);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LookLargerImageActivity.newInstance(getContext(), mData.get(position));
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_delete:
                        mPresenter.httpDeleteImage(position,mData.get(position).getId()+"");
                        break;
                    case R.id.iv_like:
                        CommentRequestHelper.onLivingLickOrNot(mData.get(position).getId() + "",
                                position, "0".equals(mData.get(position).getIsLiked()), new CommentRequestHelper.CallBackAdapter() {
                                    @Override
                                    public void onLikeSucc(int position, boolean isUnLikePre) {
                                        super.onLikeSucc(position, isUnLikePre);
                                        if (isUnLikePre) {
                                            ((ImageView) mAdapter.getViewByPosition(mRecyclerView, position, R.id.iv_like)).setImageResource(R.drawable.icon_like_like);
                                        } else {
                                            ((ImageView) mAdapter.getViewByPosition(mRecyclerView, position, R.id.iv_like)).setImageResource(R.drawable.icon_like_normal);
                                        }
                                    }
                                });
                        break;
                }
            }
        });
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(3, DensityUtil.dip2px(1f),false));
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(getContext(), 3);
    }

    @Override
    public void requestData() {
        ((LivingListActivity) getContext()).request(mTypeFrom, mPageNo);
    }

    @Override
    public Map<String, String> getParams() {
        return null;
    }


    public void getDataFromParent(List<LivingListBean.DataBean.ImageListBean> imageList, boolean isRefresh) {
        if (isRefresh) {
            mPageNo = 1;
            mData.clear();
        }
        getDataListSucc(imageList);
    }

    @Override
    public void deleteImageSucc(int position) {
        mData.remove(position);
        mAdapter.replaceData(mData);
    }

    public int getLoacCount(){
        return mData.size();
    }
}
