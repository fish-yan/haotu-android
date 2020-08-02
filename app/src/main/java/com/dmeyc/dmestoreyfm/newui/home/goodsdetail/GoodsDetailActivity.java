package com.dmeyc.dmestoreyfm.newui.home.goodsdetail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.response.GoodsDetailBean;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.newbase.BaseMvpActivity;
import com.dmeyc.dmestoreyfm.utils.CommonUtil;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.wedgit.GoodsTagView;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * create by cxg on 2020/1/1
 */
public class GoodsDetailActivity extends BaseMvpActivity<IGoodsDetailView, GoodsDetailPresenter> implements IGoodsDetailView {

    @Bind(R.id.tv_name)
    TextView mTtvName;
    @Bind(R.id.goodTagView)
    GoodsTagView mGoodTagView;
    @Bind(R.id.imageView)
    ImageView mImageView;
    @Bind(R.id.tv_discountPrice)
    TextView mTvCost;

    @Bind(R.id.tv_price)
    TextView mTvPrice;

    private String mGoodsId;
    private String mPhoneNo;
    public static void newInstance(Context context,String goodsId) {
        Intent intent = new Intent(context, GoodsDetailActivity.class);
        intent.putExtra(ExtraKey.GOODS_ID,goodsId);
        context.startActivity(intent);
    }

    @Override
    protected GoodsDetailPresenter createPresenter() {
        return new GoodsDetailPresenter();
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_goods_detail;
    }

    @Override
    protected void initViews() {
        setTitle("商品详情");
        mGoodsId = getIntent().getStringExtra(ExtraKey.GOODS_ID);
        mTvPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
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

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("id",mGoodsId);
        return params;
    }

    @Override
    public void httpDataSucc(GoodsDetailBean.DataBean bean) {
        mPhoneNo = bean.getPhoneNo();
        mTtvName.setText(bean.getName());
        mTvCost.setText("￥" + bean.getDiscountPrice());
        mTvPrice.setText("￥"+bean.getPrice());
        mGoodTagView.setTags(bean.getTag());
        GlideUtil.loadImage(this,bean.getImg(),mImageView);
    }
}
