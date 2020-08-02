package com.dmeyc.dmestoreyfm.ui.photo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.bean.event.MeasureSuccessEvent;
import com.dmeyc.dmestoreyfm.bean.event.PicPathEvent;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.fragment.FrontSidePresenter;
import com.dmeyc.dmestoreyfm.fragment.IFrontSideView;
import com.dmeyc.dmestoreyfm.fragment.PhotoCommandFragment;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.camera.CameraManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.List;


public class FrontTailorActivity extends BaseActivity<FrontSidePresenter> implements IFrontSideView {

    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int ALBUM_REQUEST_CODE = 2;
    private PhotoCommandFragment mFragment;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_front_tailor;
    }

    @Override
    protected boolean isFullScreem() {
        return true;
    }

    @Override
    protected void initData() {
        if(!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);

        mFragment = new PhotoCommandFragment(isFrontPic(), isBackDireation());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container,mFragment).commit();

        findViewById(R.id.iv_left_title_bar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected FrontSidePresenter initPresenter() {
        mPresenter = new FrontSidePresenter();
        mPresenter.attachView(this);
        return mPresenter;
    }

    /**
     * 相机拍照callback
     */
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void takePhotoCallBack(PicPathEvent obj){
        if(obj.isFrontTakePhoto() == isFrontPic()){
            String path = SPUtils.getStringData(isFrontPic() ? Constant.Config.FRONT_PIC_PATH : Constant.Config.SIDE_PIC_PATH);
            mPresenter.proseccPhotoData(path,CAMERA_REQUEST_CODE);
        }
    }

    /**
     * 测量成功,退出当前activity
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void measureSuccessFinish(MeasureSuccessEvent event){
        finish();
    }

    @Override
    public int getGender() {
        return getIntent().getIntExtra(Constant.Config.GENDER, Constant.Config.GENDER_MALE);
    }

    /**
     * 正面照片
     * @return
     */
    @Override
    public boolean isFrontPic() {
        return true;
    }

    /**
     * 是否是后置摄像头拍照
     * @return
     */
    public boolean isBackDireation(){
        return SPUtils.getIntData("SP_CAMERA_DIRECTION", CameraManager.CameraDirection.CAMERA_BACK.ordinal()) == CameraManager.CameraDirection.CAMERA_BACK.ordinal();
    }

    // Avoid android.content.ActivityNotFoundException
    @Override
    public void openAlbumChoosePic() {
        Intent photoIntent;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            photoIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI); // act=android.intent.action.PICK dat=content://media/external/images/media
            if (!isIntentAvailable(this, photoIntent)) {
                ToastUtil.show("很抱歉，当前您的手机不支持相册选择功能");
                return;
            }
        } else {
            photoIntent = new Intent(Intent.ACTION_GET_CONTENT);
            photoIntent.setType("image/*");
            if (photoIntent.resolveActivity(getPackageManager()) == null) {
                ToastUtil.show("很抱歉，当前您的手机不支持相册选择功能");
                return;
            }
        }
        startActivityForResult(photoIntent, ALBUM_REQUEST_CODE);
    }

    public static boolean isIntentAvailable(Context context, Intent intent) {
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ALBUM_REQUEST_CODE && resultCode == RESULT_OK) {
            if(data != null){
                String albumPath = getRealPathFromURI(data.getData()); // 图片文件路径
                mPresenter.proseccPhotoData(albumPath,ALBUM_REQUEST_CODE);
            }else{
                ndkresultPicError(ALBUM_REQUEST_CODE,"");
            }
        }
    }

    @Override
    public void ndkResultSuccess(int gender) {
        mFragment.restartPreview();
        if(!isBackDireation()){
            mFragment.restartRegist();
            mFragment.resetFrontCameraParams();
        }
        mPresenter.nextStep(gender);
    }

    @Override
    public void ndkresultPicError(int code, String picAbsPath) {
        switch (code){
            case CAMERA_REQUEST_CODE:
                if(!isBackDireation()){
                    mFragment.restartRegist();
                    mFragment.resetFrontCameraParams();
                }
                mFragment.restartPreview();
                ToastUtil.show(isFrontPic() ? "请根据模板拍摄正面照片!" : "请根据模板拍摄侧面照片!");
                if(!TextUtils.isEmpty(picAbsPath)){
                    File file = new File(picAbsPath);
                    if(file.length() > 0)
                        file.delete();
                }
                break;
            case ALBUM_REQUEST_CODE:
                mPresenter.showErrorDialog();
                break;
        }
    }

    /** add front result */
    @Override
    public Intent setOldIntent(Intent intent) {
        intent.putExtras(getIntent());
        return intent;
    }

    @Override
    protected void onDestroy() {
        if(EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    /**
     * depend on picture uri get the home path
     * @param contentUri
     * @return
     */
    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public void stopPreview(){
//		mFragment.stopPreview();
    }
}
