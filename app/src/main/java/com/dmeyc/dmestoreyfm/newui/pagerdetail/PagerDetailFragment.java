package com.dmeyc.dmestoreyfm.newui.pagerdetail;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.parser.ParserConfig;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.bean.response.AccountInfoBean;
import com.dmeyc.dmestoreyfm.config.PagerListConfig;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.event.MyEvent;
import com.dmeyc.dmestoreyfm.event.RefreshEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseFragment;
import com.dmeyc.dmestoreyfm.newbase.BaseMVPFragment;
import com.dmeyc.dmestoreyfm.newbase.BaseMvpTabFragment;
import com.dmeyc.dmestoreyfm.newbase.BaseNewTabFragment;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;
import com.dmeyc.dmestoreyfm.newui.home.groupaction.GroupActionFragment;
import com.dmeyc.dmestoreyfm.newui.home.search.searchvideo.SearchVideoPresenter;
import com.dmeyc.dmestoreyfm.newui.pagerdetail.editinfo.EditInfoActivity;
import com.dmeyc.dmestoreyfm.newui.pagerdetail.video.PersonVideoFragment;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.utils.photoselector.ImageChooserHelper;
import com.dmeyc.dmestoreyfm.video.releasedynamic.ReleasedynamicActivity;
import com.dmeyc.dmestoreyfm.wedgit.CircleImageView;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * create by cxg on 2019/12/1
 */
public class PagerDetailFragment extends BaseMvpTabFragment<IPagerDetailView, PagerDetailPresenter> implements IPagerDetailView {

    @Bind(R.id.civ_header)
    CircleImageView mCivHeader;

    @Bind(R.id.tv_person_name)
    TextView mTvName;
    @Bind(R.id.tv_praise)
    TextView mTvPraise;
    @Bind(R.id.tv_follow)
    TextView mTvFollow;
    @Bind(R.id.tv_like)
    TextView mTvLike;
    @Bind(R.id.tv_person_instruction)
    TextView mTvPersonInstruction;

    private String mHeadIconUrl = "";
    private ImageChooserHelper mChooseHelper;


    /**
     * tab title list
     *
     * @param mTitleLists
     */
    @Override
    protected void getTitleList(List<String> mTitleLists) {
        mTitleLists.addAll(PagerListConfig.getTabTitles());
    }

    /**
     * fragment list
     *
     * @param mFragmentLists
     */
    @Override
    protected void getFragmentLists(List<BaseFragment> mFragmentLists) {
        mFragmentLists.addAll(PagerListConfig.getTabFragments());
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int setContentView() {
        return R.layout.fragment_pager_detail;
    }

    @Override
    protected boolean enableEventBus() {
        return true;
    }

    @Override
    protected void curFragmentVisible() {
        super.curFragmentVisible();
        mPresenter.httpRequestData();
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        initChooseHelper();
    }

    @OnClick({R.id.iv_top_bg, R.id.iv_menu, R.id.tv_edit_content})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_top_bg:
                mChooseHelper.showChooseDialog(ImageChooserHelper.MODE_NO_VEDIO);
                break;
            case R.id.iv_menu:
                EventBus.getDefault().post(new MyEvent.ShowMenuList());
                break;
            case R.id.tv_edit_content:
                EditInfoActivity.newInstance(getContext(), mTvName.getText().toString(), mTvPersonInstruction.getText().toString(), mHeadIconUrl);
                break;
            default:
        }
    }

    /**
     * 初始化图片选择器
     */
    private void initChooseHelper() {
        mChooseHelper = new ImageChooserHelper((Activity) mContent, false, new ImageChooserHelper.IHandleChooseResult() {
            @Override
            public void onChooseOver(String path) {
                Logger.d("onChooseOver path:" + path);
//                reFreshAdapter();
            }

            @Override
            public void onChooseOver(ArrayList<String> paths, String is_check_original_image) {
                Logger.d("onChooseOver paths:" + paths);
                if (paths != null) {
//                    reFreshAdapter();
//                    mLocalSelectedPic.addAll(paths);
//                    setShowList();
                }
            }
        });
        mChooseHelper.setSelectMaxCount(1);
        mChooseHelper.selectFromAblum(false);
        mChooseHelper.setNeedCrop(false);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void resetInfo(MyEvent.EditInfo event) {
        if (MyEvent.EditInfo.TYPE_ALL.equals(event.getType())) {
            mTvName.setText(event.getName());
            mTvPersonInstruction.setText(event.getMark());
            if (!TextUtils.isEmpty(event.getImageUrl())) {
                mHeadIconUrl = event.getImageUrl();
                GlideUtil.loadImage(getContext(), mHeadIconUrl, mCivHeader);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void resetTabs(RefreshEvent.PagerDetailFragments event){
        reset();
    }

    @Override
    protected PagerDetailPresenter createPresenter() {
        return new PagerDetailPresenter();
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.httpRequestData();
    }

    @Override
    public void httpRequestSucc(AccountInfoBean.DataBean data) {
        mHeadIconUrl = data.getUser_logo();
        GlideUtil.loacImageCenterCrop(getContext(), data.getUser_logo(), mCivHeader);
        if (data.getNick_name() != null) {
            mTvName.setText(data.getNick_name());
        }
        mTvFollow.setText(data.getFollowersNo());
        mTvLike.setText(data.getFansNo());
        mTvPraise.setText(data.getLikedNo());
        if (data.getAutograph()!=null)
        mTvPersonInstruction.setText(data.getAutograph());
    }
}
