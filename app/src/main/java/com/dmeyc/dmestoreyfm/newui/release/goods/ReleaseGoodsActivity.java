package com.dmeyc.dmestoreyfm.newui.release.goods;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.dmeyc.dmestoreyfm.event.RefreshEvent;
import com.dmeyc.dmestoreyfm.newbase.BaseMvpActivity;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.utils.photoselector.ImageChooserHelper;
import com.dmeyc.dmestoreyfm.wedgit.CustomItemView;
import com.dmeyc.dmestoreyfm.wedgit.UploadPictureView;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;

/**
 * create by cxg on 2019/12/1
 */
public class ReleaseGoodsActivity extends BaseMvpActivity<IReleaseGoodsView, ReleaseGoodsPresenter> implements IReleaseGoodsView {

    @Bind(R.id.civ_title)
    CustomItemView mCivTitle;
    @Bind(R.id.civ_price)
    CustomItemView mCivPrice;
    @Bind(R.id.civ_cost_price)
    CustomItemView mCivCostPrice;
    @Bind(R.id.civ_type)
    CustomItemView mCivType;
    @Bind(R.id.upv_goods)
    UploadPictureView mUpvGoods;

    private ImageChooserHelper mChooseHelper;

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, ReleaseGoodsActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected ReleaseGoodsPresenter createPresenter() {
        return new ReleaseGoodsPresenter();
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @Override
    protected int setContentView() {
        return R.layout.activity_release_goods;
    }

    /**
     * 初始化视图控件
     */
    @Override
    protected void initViews() {
        setTitleWithBothText("取消发布", "发布", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSucs()) {
                    mPresenter.uploadImage(mUpvGoods.getImgPath());
                }
            }
        });
        mUpvGoods.setListener(new UploadPictureView.AddClickListerner() {
            @Override
            public void onAddClick() {
                mChooseHelper.showChooseDialog(ImageChooserHelper.MODE_NO_VEDIO);
            }
        });
        mChooseHelper = new ImageChooserHelper(this, false, new ImageChooserHelper.IHandleChooseResult() {
            @Override
            public void onChooseOver(String path) {
                Logger.d("choose path :" + path);
                mUpvGoods.loadImage(path);
            }

            @Override
            public void onChooseOver(ArrayList<String> paths, String is_check_original_image) {
                Logger.d("choose paths:" + paths);

            }
        });

        mChooseHelper.setSelectMaxCount(1);
        mChooseHelper.selectFromAblum(false);
        mChooseHelper.setNeedCrop(false);
    }

    private boolean checkSucs() {
        if (TextUtils.isEmpty(mCivTitle.getTitle())) {
            ToastUtil.show("请输入商品标题");
            return false;
        }
        if (TextUtils.isEmpty(mCivPrice.getTitle())) {
            ToastUtil.show("请输入商品价格");
            return false;
        }
        if (TextUtils.isEmpty(mCivCostPrice.getTitle())) {
            ToastUtil.show("请输入商品折后价格");
            return false;
        }
        if (TextUtils.isEmpty(mCivType.getTitle())) {
            ToastUtil.show("请输入商品标签");
            return false;
        }
        if (TextUtils.isEmpty(mUpvGoods.getImgPath())) {
            ToastUtil.show("请上传商品图片");
            return false;
        }
        return true;
    }

    @Override
    public void httpDataSucc() {
        ToastUtil.show("发布成功");
        EventBus.getDefault().post(new RefreshEvent.Release(RefreshEvent.Release.TYPE_GOODS));
        finish();
    }

    @Override
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();
        params.put("name", mCivTitle.getTitle());
        params.put("price", mCivPrice.getTitle());
        params.put("discountPrice", mCivCostPrice.getTitle());
        params.put("tag", mCivType.getTitle());
        params.put("type", "1");// 1商品、2课程
        return params;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mChooseHelper.onActivityResult(requestCode, resultCode, data);
    }
}
