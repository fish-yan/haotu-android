package com.dmeyc.dmestoreyfm.fragment;

import android.annotation.SuppressLint;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.bean.TailorListBean;
import com.dmeyc.dmestoreyfm.wedgit.ShapeView;

import java.util.Arrays;
import java.util.HashMap;

import butterknife.Bind;

/**
 * Created by jockie on 2017/12/4
 * Email:jockie911@gmail.com
 */

@SuppressLint("ValidFragment")
public class TailorPictureFragment extends BaseFragment {

    ShapeView mShapeView;
    @Bind(R.id.iv_shape)
    ImageView ivShapView;
    @Bind(R.id.height_weight)
    TextView tvHeightWeight;
    private TailorListBean.DataBean itemdata;
    private HashMap<String, String> hashMap;

    @SuppressLint("ValidFragment")
    public TailorPictureFragment(TailorListBean.DataBean item) {
        super();
        this.itemdata = item;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fm_tailor_picture;
    }

    @Override
    protected void initData() {
//        List<RecordBean.DataBean.InfoBean> info = itemdata.getInfo();
        hashMap = new HashMap<>();
        hashMap.put("脚踝围", String.valueOf(itemdata.getAnkle()));
        hashMap.put("臂长", String.valueOf(itemdata.getArm()));
        hashMap.put("胸围", String.valueOf(itemdata.getBust()));
        hashMap.put("下档长", String.valueOf(itemdata.getDownSide()));
        hashMap.put("肩宽", String.valueOf(itemdata.getShoulder()));
        hashMap.put("腕围", String.valueOf(itemdata.getWrist()));
        hashMap.put("腰围", String.valueOf(itemdata.getWaistline()));
        hashMap.put("臀围", String.valueOf(itemdata.getHipline()));
        hashMap.put("身高", String.valueOf(itemdata.getPheight()));
        hashMap.put("体重", String.valueOf(itemdata.getPweight()));
        tvHeightWeight.setText(getBodyItemData("身高") + "CM/" + getBodyItemData("体重") + "KG");

        mShapeView = (ShapeView) mRootView.findViewById(R.id.shapeview);
        mShapeView.initData(1,
                Arrays.asList(
                        getBodyItemData("胸围"),//
                        getBodyItemData("腰围"),
                        getBodyItemData("臀围"),
                        getBodyItemData("腕围"),
                        getBodyItemData("肩宽"),//
                        getBodyItemData("臂长"),//
                        getBodyItemData("下档长"),
                        getBodyItemData("脚踝围")), new ShapeView.OnSwitchPicShowLisner() {
                    @Override
                    public void switchPicShow(@DrawableRes int mDrawableResId) {
                        ivShapView.setImageResource(mDrawableResId);
                    }
                });
    }

    @Override
    protected void initData(View view) {

    }


    //    public String getBodyItemData(String keyName){
//        for (RecordBean.DataBean.InfoBean infoBean : itemdata.getInfo()) {
//            if(TextUtils.equals(keyName,infoBean.getName()))
//                return infoBean.getValue();
//        }
//        return "0";
//    }
    public String getBodyItemData(String keyName) {
            return hashMap.get(keyName);
    }

}
