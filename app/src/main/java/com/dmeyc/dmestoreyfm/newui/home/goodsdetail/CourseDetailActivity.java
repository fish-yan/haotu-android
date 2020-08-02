package com.dmeyc.dmestoreyfm.newui.home.goodsdetail;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.response.GoodsDetailBean;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.newbase.BaseFragment;
import com.dmeyc.dmestoreyfm.newbase.BaseMvpActivity;
import com.dmeyc.dmestoreyfm.newbase.BaseTabNewActivity;
import com.dmeyc.dmestoreyfm.utils.CommonUtil;
import com.dmeyc.dmestoreyfm.utils.DensityUtil;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.dmeyc.dmestoreyfm.wedgit.GoodsTagView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * create by cxg on 2020/1/1
 */
public class CourseDetailActivity extends BaseTabNewActivity<IGoodsDetailView, GoodsDetailPresenter> implements IGoodsDetailView {

    @Bind(R.id.iv_top)
    ImageView mIvTop;
    @Bind(R.id.tv_name)
    TextView mTvName;
    @Bind(R.id.tv_count)
    TextView mTvCount;
    @Bind(R.id.tv_discountPrice)
    TextView mTvPrice;
    @Bind(R.id.goodsTagView)
    GoodsTagView mGoodsTagView;

    private String mGoodsId;
    private String mPhoneNo;

    private CourseDetail2Fragment mCourseDetail2Fragment;
    private CourseDetail1Fragment mCourseDetail1Fragment;

    public static void newInstance(Context context, String goodsId) {
        Intent intent = new Intent(context, CourseDetailActivity.class);
        intent.putExtra(ExtraKey.GOODS_ID, goodsId);
        context.startActivity(intent);
    }

    protected void reSetTabBlockWidth() {
        Util.reflex(mTabLayout, DensityUtil.dip2px(10));
    }

    @Override
    protected void getFragmentLists(List<BaseFragment> mFragmentLists) {

        mCourseDetail1Fragment = new CourseDetail1Fragment();
        mCourseDetail2Fragment = new CourseDetail2Fragment();
        mFragmentLists.add(mCourseDetail1Fragment);
        mFragmentLists.add(mCourseDetail2Fragment);
    }

    @Override
    protected void getTitleList(List<String> mTitleLists) {
        mTitleLists.add("课程详情");
        mTitleLists.add("上课地址");
    }

    @Override
    protected GoodsDetailPresenter createPresenter() {
        return new GoodsDetailPresenter();
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_course_detail;
    }

    @Override
    protected void initViews() {
        setTitle("课程详情");
        mGoodsId = getIntent().getStringExtra(ExtraKey.GOODS_ID);
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("id", mGoodsId);
        return params;
    }

    @Override
    public void httpDataSucc(GoodsDetailBean.DataBean bean) {
        mCourseDetail1Fragment.setData(bean);
        mCourseDetail2Fragment.setData(bean);
        mGoodsTagView.setTags(bean.getTag());
        mTvName.setText(bean.getName());
        GlideUtil.loacImageCenterCrop(this,bean.getImg(),mIvTop);
        mPhoneNo = bean.getPhoneNo();
        mTvPrice.setText(bean.getPrice());
        mTvCount.setText("还有"+bean.getPeopleNumber()+"个名额");
    }
    @Override
    protected void initData() {
        super.initData();
        mPresenter.httpRequestData();
    }
    @OnClick(R.id.tv_pay)
    public void click() {
        CommonUtil.jumpCallActivity(this, mPhoneNo);
    }
}
