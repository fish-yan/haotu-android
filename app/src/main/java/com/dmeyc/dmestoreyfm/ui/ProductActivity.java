package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.GoodsAdaper;
import com.dmeyc.dmestoreyfm.adapter.LookAdapter;
import com.dmeyc.dmestoreyfm.adapter.ProductPageAdapter;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.bean.GoodDetailBean;
import com.dmeyc.dmestoreyfm.bean.SelectInfo;
import com.dmeyc.dmestoreyfm.bean.common.GoodsBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.dialog.ProductCommonlDialog;
import com.dmeyc.dmestoreyfm.dialog.ProductTailorlDialog;
import com.dmeyc.dmestoreyfm.dialog.ShareDialog;
import com.dmeyc.dmestoreyfm.present.IProductView;
import com.dmeyc.dmestoreyfm.present.ProductPresent;
import com.dmeyc.dmestoreyfm.utils.DensityUtil;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.SnackBarUtil;
import com.dmeyc.dmestoreyfm.wedgit.PriceView;
import com.dmeyc.dmestoreyfm.wedgit.ProductIndicatorView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import io.rong.imlib.model.Conversation;

public class ProductActivity extends BaseActivity<ProductPresent> implements IProductView,NestedScrollView.OnScrollChangeListener{

    @Bind(R.id.scrollview)
    NestedScrollView nestedScrollView;
    @Bind(R.id.product_indicator_view)
    ProductIndicatorView productIndicatorView;

    @Bind(R.id.ll_root1)
    LinearLayout llRoot1;
    @Bind(R.id.ll_root2)
    LinearLayout llRoot2;
    @Bind(R.id.ll_root3)
    LinearLayout llRoot3;
    @Bind(R.id.ll_root4)
    LinearLayout llRoot4;

    @Bind(R.id.viewpager)
    ViewPager viewPager;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_bottom_right)
    TextView tvBottomRight;
    @Bind(R.id.tv_bottom_left)
    TextView tvBottomLeft;
    @Bind(R.id.tv_des_custom)
    TextView tvDesCustom;

    @Bind(R.id.price_view)
    PriceView mPriceView;
    @Bind(R.id.tv_product_introduction)
    TextView tvProductionIntroduction;

    //-------------------------
    @Bind(R.id.recomendRecycleView)
    RecyclerView recommendRecyclerView;
    @Bind(R.id.tv_empty_recommend)
    TextView tvEmptRecommend;
    @Bind(R.id.tv_proname)
    TextView tv_proname;

    //------------------------
    @Bind(R.id.introductionRecycleview)
    RecyclerView introductionRecycleView;
    @Bind(R.id.tv_introduction_price)
    TextView tvIntroductionPrice;
    @Bind(R.id.tv_introduction_kind)
    TextView tvIntroductionKind;
    @Bind(R.id.tv_introduction_brand)
    TextView tvIntroductionBrand;
    @Bind(R.id.tv_introduction_material)
    TextView tvIntroductionMaterial;
    @Bind(R.id.tv_introduction_brandName)
    TextView tvIntroductionbrandName;
    @Bind(R.id.iv_introduction_avatar)
    RoundedImageView ivIntroductionAvatar;
    @Bind(R.id.tv_introduction_follow)
    TextView tvIntroductionFollow;
    //----------------------------
    @Bind(R.id.evaluateRecycleview)
    RecyclerView evaluataRecycleView;
    @Bind(R.id.tv_evaluate_empty)
    TextView tvEvaluateEmpty;
    @Bind(R.id.tv_evaluate_count)
    TextView tvEvaluateCount;

    @Bind(R.id.iv_store)
    ImageView ivStore;
    @Bind(R.id.tv_brand_name)
    TextView tvBrandName;
    @Bind(R.id.rel_head)
    RelativeLayout llHead;
    @Bind(R.id.rel_common_show_select)
    RelativeLayout relCommonShowSelect;

    String type;
    private int mProductId;
    public GoodDetailBean resultBean;
    ProductCommonlDialog productCommonlDialog;
    ProductTailorlDialog productTailorlDialog;
    public static final int TYPE_COMMON = 0;
    public static final int TYPE_CUSTOM = 1;
    public static final int TYPE_TAILOR = 2;

    private int price [];
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_product;
    }

    @Override
    protected ProductPresent initPresenter() {
        return new ProductPresent();
    }

    @Override
    protected void initData() {
        type= getIntent().getStringExtra("type");
//        DisplayMetrics dm = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
        WindowManager wm = this.getWindowManager();

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();


        RelativeLayout.LayoutParams rl=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,width);
        viewPager.setLayoutParams(rl);
        mProductId = getIntent().getIntExtra(Constant.Config.ID, 0);
        mPresenter.requestProductData(mProductId);

        productIndicatorView.setOnItemClickLister(new ProductIndicatorView.OnItemClickLister() {
            @Override
            public void onClick(int position) {
                switch (position){
                    case 0:
                        nestedScrollView.scrollTo(0,0);
                        break;
                    case 1:
                        nestedScrollView.scrollTo(0,llRoot2.getTop() - DensityUtil.dip2px(79));
                        break;
                    case 2:
                        nestedScrollView.scrollTo(0,llRoot3.getTop() - DensityUtil.dip2px(79));
                        break;
                    case 3:
                        nestedScrollView.scrollTo(0,llRoot4.getTop() - DensityUtil.dip2px(79));
                        break;
                }
            }
        });
        nestedScrollView.setOnScrollChangeListener(this);
    }

    private ArrayList<String>reouse=new ArrayList<>();
    private void setOtherData(final GoodDetailBean.DataBean data) {
        if(data == null)
            return;
        tvProductionIntroduction.setText(data.getGoods().getProductIntroduction());
        if(data.getRecommendMatch() == null || data.getRecommendMatch().size() == 0){
            tvEmptRecommend.setVisibility(View.VISIBLE);
        }else{
            recommendRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
            recommendRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                    int pos = parent.getChildLayoutPosition(view);
                    if(pos == 0){
                        outRect.left = DensityUtil.dip2px(20);
                    }
                    outRect.right = DensityUtil.dip2px(10);
                }
            });
            recommendRecyclerView.setAdapter(new GoodsAdaper(ProductActivity.this, R.layout.item_lv_gv_item_single,data.getRecommendMatch()));
        }

        introductionRecycleView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        introductionRecycleView.setAdapter(new CommonAdapter<GoodDetailBean.DataBean.ProductImageListBottomBean>(this,R.layout.item_product_introduction,data.getProductImageListBottom()) {

            @Override
            protected void convert(ViewHolder holder, GoodDetailBean.DataBean.ProductImageListBottomBean bean, final int position) {
                ImageView ivIntroduction = holder.getView(R.id.item_iv_introduction);
                TextView tvIntroduction = holder.getView(R.id.item_tv_introduction);
                GlideUtil.loadImage(mContext,bean.getSource(),ivIntroduction);
                if(TextUtils.isEmpty(bean.getTitle())){
                    tvIntroduction.setVisibility(View.GONE);
                }else {
                    tvIntroduction.setVisibility(View.VISIBLE);
                    tvIntroduction.setText(bean.getTitle());
                }
                ivIntroduction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        for (int i=0;i<data.getProductImageListBottom().size();i++){
                            reouse.add(data.getProductImageListBottom().get(i).getSource());
                        }

                        Intent intent = new Intent(ProductActivity.this, ShowPicActivity.class);
                        intent.putExtra(Constant.Config.POSITION,0);
                        intent.putStringArrayListExtra("pics",reouse);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                             startActivity(intent);
                    }
                });
//                iv.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
            }
        });
        tvIntroductionPrice.setText(data.getGoods().getPrice() + "");
        tvIntroductionBrand.setText(data.getGoods().getBrandDesigner());
        tvIntroductionMaterial.setText(data.getGoods().getMaterialDetail());
        tvIntroductionKind.setText(data.getGoods().getProductCategory());

        tvIntroductionbrandName.setText(data.getGoods().getBrandDesigner());
        GlideUtil.loadImage(this,data.getStore().getLogo(),ivIntroductionAvatar);
        setBrandStatus(data.getBrandDesigner().getIsAttend());

        if(data.getReviews() == null || data.getReviews().size() == 0){
            tvEvaluateEmpty.setVisibility(View.VISIBLE);
        }else{
            evaluataRecycleView.setLayoutManager(new LinearLayoutManager(this){
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
            LookAdapter integerCommonAdapter = new LookAdapter(this, R.layout.item_rv_home_look, data.getReviews());
            evaluataRecycleView.setAdapter(integerCommonAdapter);
            tvEvaluateCount.setText("精选评论 (" + data.getReviews().size() + ")");
        }
//        if(data.getGoods().isIsCollection()){
//            ivStore.setImageResource(R.drawable.ic_like_orange);
//        }else {
//            ivStore.setImageResource(R.drawable.ic_like_black);
//        }
    }

    private void setBrandStatus(boolean isAttend){
        if(isAttend){
            tvIntroductionFollow.setText("已关注");
            tvIntroductionFollow.setBackground(getResources().getDrawable(R.drawable.shape_14radius_99));
            tvIntroductionFollow.setTextColor(getResources().getColor(R.color.gray));
        }else{
            tvIntroductionFollow.setText("关注店铺");
            tvIntroductionFollow.setBackground(getResources().getDrawable(R.drawable.shape_14radius_fd7e18));
            tvIntroductionFollow.setTextColor(getResources().getColor(R.color.indicator_selected_color));
        }
    }
    @OnClick({R.id.tv_bottom_right,R.id.iv_left_title_bar,R.id.ll_store,R.id.iv_share,R.id.tv_introduction_follow,R.id.tv_bottom_left,R.id.ll_service,R.id.tv_selected,R.id.iv_introduction_avatar})
    public void onClick(View v){
        if(resultBean == null)
            return;
        switch (v.getId()){
            case R.id.tv_bottom_right:
                mPresenter.bottomRightClick();
                break;
            case R.id.iv_left_title_bar:
                finish();
                break;
            case R.id.tv_bottom_left:
//                mPresenter.bottomLeftClick();
                break;
            case R.id.tv_introduction_follow:
                mPresenter.attendBrand();
                break;
            case R.id.iv_share:
                new ShareDialog(this).show();
                break;
            case R.id.ll_store:
//                mPresenter.addProductToFollow(mProductId);
                break;
            case R.id.ll_service:
                mPresenter.startConvasation(mProductId);
                break;
            case R.id.tv_selected:
                openCommonDialog();
                break;
            case R.id.iv_introduction_avatar:
                if(!"type".equals(type)){
                    Intent intent = new Intent(this, BrandDetailActivity.class);
                    intent.putExtra(Constant.Config.TYPE,resultBean.getData().getGoods().getType());
                    intent.putExtra(Constant.Config.ID,resultBean.getData().getGoods().getId());
                    intent.putExtra(Constant.Config.STORY_ID,resultBean.getData().getStore().getId());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                break;
        }
    }

    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if(scrollY < DensityUtil.dip2px(220)){
            productIndicatorView.setVisibility(View.GONE);
        }else if(scrollY < DensityUtil.dip2px(443)){
            productIndicatorView.setVisibility(View.VISIBLE);
        }
        if(llRoot1.getBottom() - llHead.getBottom() > scrollY){
            productIndicatorView.scrollToSelected(0);
        }else if(llRoot2.getBottom() - llHead.getBottom() > scrollY){
            productIndicatorView.scrollToSelected(1);
        }else if(llRoot3.getBottom() - llHead.getBottom() > scrollY){
            productIndicatorView.scrollToSelected(2);
        }else{
            productIndicatorView.scrollToSelected(3);
        }

        Log.e("WW",llRoot1.getTop() + " / " + scrollY + " / " + llRoot3.getBottom() + " / ");
    }

    @Override
    protected boolean isContainTitle() {
        return false;
    }

    /**
     * 增加定制的量身记录
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null){
            if(requestCode == 0 && resultCode == RESULT_OK){
                productTailorlDialog.setTailorRecordName((SelectInfo)data.getParcelableExtra(Constant.Config.ITEM));
            }
        }
    }
    @Override
    public void requestDataSuccess(GoodDetailBean resultBean) {
        this.resultBean = resultBean;
        ArrayList<String> mProductPicLists = new ArrayList<>();
        for (GoodDetailBean.DataBean.ProductImageListTopBean topBean : resultBean.getData().getProductImageListTop()) {
            mProductPicLists.add(topBean.getSource());
        }
        viewPager.setAdapter(new ProductPageAdapter(mProductPicLists));

        GoodsBean goods = resultBean.getData().getGoods();
        mPriceView.setPrice(goods.getPrice());
//        tvTitle.setText(goods.getProductInfo());
        tvTitle.setText(goods.getBrandDesigner());
        tv_proname.setText(goods.getName());
        switch(switchType()){
            case TYPE_COMMON:
                tvBottomRight.setText("立即购买");
                tvBottomLeft.setText("加入购物车");
                tvDesCustom.setVisibility(View.GONE);
                relCommonShowSelect.setVisibility(View.VISIBLE);
                break;
            case TYPE_CUSTOM:
                tvBottomRight.setText("个性定制");
                tvBottomLeft.setText("立即购买");
                tvDesCustom.setVisibility(View.VISIBLE);
                break;
            case TYPE_TAILOR:
                tvBottomRight.setText("开始定制");
                tvBottomLeft.setVisibility(View.GONE);
                tvDesCustom.setVisibility(View.VISIBLE);
                break;
        }
        if(resultBean.getData().getGoods().isIsCollection()){
            ivStore.setImageResource(R.drawable.ic_like_orange);
        }else {
            ivStore.setImageResource(R.drawable.ic_like_black);
        }
        setOtherData(resultBean.getData());
    }
    @Override
    public void requestDataError() {

    }
    @Override
    public int getBrandType() {
//        return resultBean.getData().getBrandDesigner().getType();
        return resultBean.getData().getGoods().getType();
    }
    @Override
    public int getBrandId() {
//        return resultBean.getData().getBrandDesigner().getId();
        return resultBean.getData().getStore().getId();
    }

    @Override
    public void attendBrandResult(boolean isAttend) {
        setBrandStatus(isAttend);
    }
    @Override
    public void openCommonDialog() {
        if(productCommonlDialog == null)
            productCommonlDialog = new ProductCommonlDialog(this, resultBean.getData().getGoods(),mProductId);
        productCommonlDialog.show();
    }

    @Override
    public void openCustomDialog() {
        if(productCommonlDialog == null)
            productCommonlDialog = new ProductCommonlDialog(this, resultBean.getData().getGoods(),mProductId);
        productCommonlDialog.show();
    }
    @Override

    public void openTailorDialog() {
//        Toast.makeText(this,"sdfdsf",Toast.LENGTH_LONG).show();
       Intent intent =new Intent(this,PersonalTailorActivity.class);
        intent.putExtra("goodid",mProductId);
        intent.putExtra("goodben",resultBean.getData().getGoods());
         startActivity(intent);
//
//        if(productTailorlDialog == null)
//          productTailorlDialog = new ProductTailorlDialog(this, resultBean.getData().getGoods(),mProductId);
//           productTailorlDialog.show();
    }

    @Override
    public int switchType() {

//        if(){
//
//        }
        if(resultBean != null){
            switch (resultBean.getData().getGoods().isIsCustom()){  //之所以用switch是为了防止以后后台改变较大 不建议直接return
                case 0: //标码
                    return TYPE_COMMON;
                case 1: //标码 定制
                    return TYPE_CUSTOM;
                case 2: //定制
                    return TYPE_TAILOR;
            }
        }
        return 0;
    }
    @Override
    public boolean isCustom() {
        return resultBean != null && resultBean.getData().getGoods().isIsCustom() != 0;
    }

    @Override
    public void startConvasation(String productId, String targetConvasationId) {
        Uri uri = Uri.parse("rong://" + getApplicationInfo().processName).buildUpon()
                .appendPath("conversation").appendPath(Conversation.ConversationType.PRIVATE.getName().toLowerCase())
                .appendQueryParameter("targetId", targetConvasationId)
                .appendQueryParameter("title", SPUtils.getStringData(Constant.Config.MOBILE)).build();
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.putExtra("id",productId);
        intent.putExtra("url",resultBean.getData().getGoods().getImage());
        intent.putExtra("title",resultBean.getData().getGoods().getName());
        intent.putExtra("subtitle",resultBean.getData().getGoods().getIntroduction());
        startActivity(intent);
    }

    @Override
    public void resultFollowProduct(boolean isFollow, String resultText) {
        ivStore.setImageResource(isFollow ? R.drawable.ic_like_orange : R.drawable.ic_like_black);
        SnackBarUtil.showShortSnackbar(viewPager,resultText);
    }
}
