package com.dmeyc.dmestoreyfm.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;

import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.common.CommonBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.dialog.LoadingDialog;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.SideTailorActivity;
import com.dmeyc.dmestoreyfm.ui.WeightHeightActivity;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.tamic.novate.Throwable;

import java.io.File;
import java.math.BigDecimal;

/**
 * Created by yc on 2017/5/23.
 */

public class FrontSidePresenter<T extends BaseActivity> extends BasePresenter<IFrontSideView,T> {

    private LoadingDialog dialog;

    /**
     * 下一步操作
     */
    public void nextStep(int gender) {
        boolean isFront = mBaseView.isFrontPic();
        String picPath = SPUtils.getStringData(isFront ? Constant.Config.FRONT_PIC_PATH : Constant.Config.SIDE_PIC_PATH);
        File file = new File(picPath);
        if(TextUtils.isEmpty(picPath) || !file.exists() || file.length() == 0){
            ToastUtil.show(isFront ? "请根据模板拍摄正面照片!" : "请根据模板拍摄侧面照片!");
            return;
        }
        Intent intent = new Intent(mActivity.get(),isFront ? SideTailorActivity.class : WeightHeightActivity.class);
        intent.putExtra(Constant.Config.GENDER,mBaseView.getGender());
//        Rect region = HumanContourExtractor.getInstance().getSearchRegion();
//        BodyResult bodyResult = new BodyResult(region.x, region.y, region.width, region.height);
//        LogUtil.i("SquareCameraContainer","bodyResult : " + region.x + "\\" + region.y + "\\" + region.width + "\\" + region.height);
//        if(isFront){
//            intent.putExtra("front_result",bodyResult);
//        }else{
//            intent = mBaseView.setOldIntent(intent);
//            intent.putExtra("side_result",bodyResult);
//        }
        if(isFront){
            SPUtils.savaIntData(Constant.Config.GENDER,gender);
        }
        if(dialog != null && dialog.isShowing() && mActivity != null && mActivity.get() != null)
            dialog.dismiss();
        Log.i("SquareCameraContainer","HumanContourExtractor next step : " + System.currentTimeMillis());
        (mActivity.get()).startActivity(intent);
//        (mActivity.get()).finish();
//        mBaseView.stopPreview();
    }

    /**
     * 从相册选取照片，ndk检查是否是正确格式的照片
     * @param picAbsPath
     * @param cameraRequestCode
     */
    public void proseccPhotoData(final String picAbsPath, final int cameraRequestCode) {
       /* if(mActivity != null && mActivity.get() != null){
            mActivity.get().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    dialog = new LoadingDialog(mActivity.get()).builder();
                    dialog.setCancelable(true)
                            .setMsg(BaseApp.getContext().getResources().getString(R.string.check_out_shape))
                            .show();
                }
            });
        }*/

       mActivity.get().runOnUiThread(new Runnable() {
           @Override
           public void run() {
               File file = new File(picAbsPath);
               RestClient.getNovate(mActivity.get()).uploadImage(Constant.API.FACE_TEST, file, new DmeycBaseSubscriber<CommonBean>(mActivity.get()) {

                   @Override
                   public void onSuccess(CommonBean bean) {
                       if(bean.getData() instanceof Integer || bean.getData() instanceof Double || bean.getData() instanceof Float || bean.getData() instanceof BigDecimal){ //服务端返回的是int，这里返回的double
                           int data = new Double((double)bean.getData()).intValue();
                           if(data == 0){
                               mBaseView.ndkresultPicError(cameraRequestCode,picAbsPath);
                           }else{
                               mBaseView.ndkResultSuccess(data);
                           }
                       }else{
                           mBaseView.ndkresultPicError(cameraRequestCode,picAbsPath);
                       }
                   }

                   @Override
                   public void onError(Throwable e) {
                       mBaseView.ndkresultPicError(cameraRequestCode,picAbsPath);
                   }
               });
           }
       });
    }


    /**
     * 照片不符合，让用户重新选择
     */
    public void showErrorDialog() {
        new AlertDialog.Builder(mActivity.get())
                .setTitle("照片格式错误")
                .setMessage(mBaseView.isFrontPic()? "请根据模板提示选择正确的正面照片,点击确定进入相册重新选择照片!" : "请根据模板提示选择正确的侧面照,点击确定进入相册重新选择照片!")
                .setCancelable(true)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mBaseView.openAlbumChoosePic();
                        dialog.dismiss();
                    }
                }).show();

        /*AlertDialog builder = new AlertDialog(mActivity.get()).builder();
        builder.setTitle(R.string.error_pic_model)
                .setMsg(mBaseView.isFrontPic()?R.string.choose_right_front_pic : R.string.choose_right_side_pic)
                .setNegativeButton(R.string.cancel,null)
                .setPositiveButton(R.string.ok, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mBaseView.openAlbumChoosePic();
                    }
                }).show();*/
    }
}
