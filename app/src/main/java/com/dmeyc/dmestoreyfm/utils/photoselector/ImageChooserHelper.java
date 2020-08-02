package com.dmeyc.dmestoreyfm.utils.photoselector;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.utils.DimenUtil;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageChooserHelper {

    public enum FromPager {
        FROM_CONTACT_ONE2ONE, FROM_DAIDING, FROM_STUDENT_DYNAMIC, FROM_USER_INFO, FROM_NONE, FROM_DYNAMIC
    }
    //拍照上传 & 相册选择
    public static final int MODE_ALL = 1;
    //拍照上传
    public static final int MODE_TAKE_PHOTO = 2;
    //相册选择
    public static final int MODE_GET_PHOTO_FROM_ABULM = 3;

    // 没有视频，相册可以选择多张，可以拍照
    public static final int MODE_PHOTO_NOT_CROP = 6;

    //不需要选择视频上传
    public static final int MODE_NO_VEDIO = 4;

    public static final int MODE_WITH_VEDIO = 5;
    // 增加一个语音选择
    public static final int MODE_ALL_WITH_VOICE = 7;

    //拍照上传
    public static final int TAKE_PHOTO = 0;
    //相册选择
    public static final int GET_PHOTO_FROM_ABULM = 1;
    //相册选择
    public static final int CHOICE_VOICE = 5;
    //取消
    public static final int CANCEL = 2;

    public static final int TAKE_VIDEO = 3;

    public static final int LIST_VIDEO = 4;

    public static final int RESULT_FOR_TAKE_PIC = 0x101;
    public static final int RESULT_FOR_TAKE_CROP = 0x102;
    public static final int RESULT_FOR_GET_PHOTO_FROM_ABULM = 0x103;
    private static final int RESULT_TAKE_VIDEO=0xdd;
    private static final int RESULT_LIST_VIDEO=0xCC;

    private Activity mBaseActivity;
    private String[] chooseItems;
    private ArrayList<String> chooseImgs;
    private File dirRootImgCache;
    private File fileTakePhone;
    private File fileTakeVideo;
    private File fileCrop;
    private IHandleChooseResult handleChooseResult;
    private boolean isNeedCrop;
    private File fileTakePhoneDir;
    private int maxCount = 1;
    private boolean fromAblum = false;
    private boolean cropPhotoSize16_9 = false;
    private boolean setLongVideo = false;
    private AlbumBroadcastReceiver recv;
    public CropVO mCropVO;
    public ArrayList<String> cropFilePaths = new ArrayList<String>();

    public FromPager type = FromPager.FROM_CONTACT_ONE2ONE;

    private String takeVideoFileName;

    private OnItemClickListener itemClickListener;

    public void setOnItemClickListener(OnItemClickListener  listener){
        itemClickListener = listener;
    }
    // 判断当前选择的是哪个item
    public interface OnItemClickListener{
        void clickedPosition(int tag);
    }
    public void setHandleChooseResult(IHandleChooseResult handleChooseResult) {
        this.handleChooseResult = handleChooseResult;
    }
    public interface IHandleChooseResult {
        void onChooseOver(String path);
        void onChooseOver(ArrayList<String> paths, String is_check_original_image);
    }
    public interface OnStatListener {
        void onCancel();
        void onTakePhoto();
        void onalbum();
    }
    private OnStatListener statListener;

    public void setOnStatListener(OnStatListener listener) {

    }

    public void setSetLongVideo(boolean isLong){
        setLongVideo = isLong;
    }

    public interface OnDialogItemClickListener {
        void onItemClick(int index);
    }

    public ImageChooserHelper() {

    }

    public void setSelectMaxCount(int count) {
        maxCount = count;
    }

    public void selectFromAblum(Boolean b) {
        fromAblum = b;
    }

    public void setCropPhotoSize16_9(boolean cropPhotoSize16_9) {
        this.cropPhotoSize16_9 = cropPhotoSize16_9;
    }

    public void setChoosedImages(List<String> list) {
        chooseImgs = (ArrayList<String>) list;
    }

    public ImageChooserHelper(Activity baseActivity, boolean isNeedCrop,
                              IHandleChooseResult iHandleChooseResult) {
        this.mBaseActivity = baseActivity;
        this.handleChooseResult = iHandleChooseResult;
        this.isNeedCrop = isNeedCrop;
        initParams();
    }

    public ImageChooserHelper(BaseActivity baseActivity, boolean isNeedCrop, CropVO cropVO,
                              IHandleChooseResult iHandleChooseResult) {
        this.mBaseActivity = baseActivity;
        this.handleChooseResult = iHandleChooseResult;
        this.isNeedCrop = isNeedCrop;
        this.mCropVO = cropVO;
        initParams();
    }

    private void initParams() {
        chooseItems = mBaseActivity.getResources().getStringArray(
                R.array.photoSelectList
        );
        chooseImgs = new ArrayList<String>();
        if (InitUtils.isSDCardAvailable()) {
            dirRootImgCache = mBaseActivity.getExternalFilesDir(null);
        } else {
            dirRootImgCache = mBaseActivity.getFilesDir();
        }

        fileTakePhoneDir = new File(dirRootImgCache + "/img");
        if (!fileTakePhoneDir.exists()) {
            fileTakePhoneDir.mkdirs();
        }
        // System.out.println("fileTakePhoneDir:"+fileTakePhoneDir.getAbsolutePath());
        try {
            fileTakePhone = File.createTempFile("phone", ".jpg",
                    fileTakePhoneDir);
            fileCrop = File.createTempFile("crop", ".jpg", fileTakePhoneDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // System.out.println("fileTakePhone:"+fileTakePhone.getAbsolutePath());
        recv = new AlbumBroadcastReceiver(mBaseActivity);
        recv.registerAlbumReceiver();
        recv.setAlbumSelectedListener(new AlbumBroadcastReceiver.AlbumSelectedListener() {
            @Override
            public void onAlbumSelected(ArrayList<String> paths, String is_check_original_image) {
                if (handleChooseResult != null) {
                    handleChooseResult.onChooseOver(paths,is_check_original_image);
                }
            }
        });
    }
    
    public void destroy() {
        if (recv != null) {
            recv.unRegisterAlbumReceiver();
        }
    }

    public void showChooseDialog(String[] items) {
        chooseItems = items;
        showChooseDialog();
    }


    public void showChooseDialog() {
        showChooseDialog(MODE_ALL, null);
    }

    public void showChooseDialog(int TYPE) {
        showChooseDialog(TYPE, null);
    }

    public void showChooseDialog(OnDialogItemClickListener onDialogItemClickListener,int t) {
        showChooseDialog(MODE_ALL, onDialogItemClickListener);
    }

    public void showChooseDialog(FromPager type,int t) {
        this.type = type;
        showChooseDialog(MODE_ALL, null);
    }

    public void showChooseDialogWithVedio(int mode,FromPager type){
        this.type = type;
        showChooseDialog(mode, null);
    }

    public void showChooseDialogNoVedio(int mode,FromPager type) {
        this.type = type;
        showChooseDialog(mode, null);
    }

    /**
     * mode 1.拍照上传 & 相册选择 2.拍照上传 3.相册选择 默认1
     *
     * @param mode
     */
    public void showChooseDialog(int mode, final OnDialogItemClickListener onDialogItemClickListener) {
        if (chooseItems == null) {
                chooseItems = mBaseActivity.getResources().getStringArray(R.array.photoSelectList);
        }
        if (chooseItems != null) {
            String[] items;
            int[] itemTags;
                switch (mode) {
                    case MODE_TAKE_PHOTO:
                        items = new String[]{chooseItems[0], chooseItems[4]};
                        itemTags = new int[]{TAKE_PHOTO, CANCEL};
                        break;
                    case MODE_GET_PHOTO_FROM_ABULM:
                        items = new String[]{chooseItems[1], chooseItems[4]};
                        itemTags = new int[]{GET_PHOTO_FROM_ABULM, CANCEL};
                        break;
                    case MODE_NO_VEDIO:
                        items = new String[]{chooseItems[0], chooseItems[1],chooseItems[4]};
                        itemTags = new int[]{TAKE_PHOTO, GET_PHOTO_FROM_ABULM,CANCEL};
                        break;
                    case MODE_WITH_VEDIO:
                        items = new String[]{chooseItems[2], chooseItems[3],chooseItems[4]};
                        itemTags = new int[]{LIST_VIDEO,TAKE_VIDEO,CANCEL};
                        break;
                    case MODE_PHOTO_NOT_CROP:
                        items = new String[]{chooseItems[0], chooseItems[1],chooseItems[4]};
                        itemTags = new int[]{TAKE_PHOTO, GET_PHOTO_FROM_ABULM,CANCEL};
                        break;
                    case MODE_ALL_WITH_VOICE:
                        items = new String[]{chooseItems[0], chooseItems[1],chooseItems[2], chooseItems[5],chooseItems[4]};
                        itemTags = new int[]{TAKE_PHOTO, GET_PHOTO_FROM_ABULM,LIST_VIDEO,CHOICE_VOICE,CANCEL};
                        break;
                    case MODE_ALL:
                    default:
                        items = new String[]{chooseItems[0], chooseItems[1],chooseItems[2],chooseItems[4]};
                        itemTags = new int[]{TAKE_PHOTO, GET_PHOTO_FROM_ABULM,LIST_VIDEO,CANCEL};
                        break;
                }
            CustomDialogTools.createCustomDialog(mBaseActivity, items, itemTags, "",
                    new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                           final int idx = (Integer) v.getTag();
                            switch (idx) {
                                case TAKE_PHOTO:// 拍照
                                    if(Build.VERSION.SDK_INT >= 23){
                                        MPermissionUtils.requestPermissionsResult(mBaseActivity, 1, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                new MPermissionUtils.OnPermissionListener(){
                                                    @Override
                                                    public void onPermissionGranted(){
                                                        setTakePhoto(onDialogItemClickListener);
                                                    }
                                                    @Override
                                                    public void onPermissionDenied(){
                                                        MPermissionUtils.showTipsDialog(mBaseActivity, "当前应用缺少必要的权限,录音权限或者读取存储权限(录音,照片,视频等需要),该功能暂时无法使用。如若需要，请单击【确定】按钮前往设置中心进行权限授权。");
                                                    }
                                                });
                                    }else{
                                        setTakePhoto(onDialogItemClickListener);
                                    }
                                    break;
                                case GET_PHOTO_FROM_ABULM:
                                    if(Build.VERSION.SDK_INT >= 23){
                                        MPermissionUtils.requestPermissionsResult(mBaseActivity, 1, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                new MPermissionUtils.OnPermissionListener(){
                                                    @Override
                                                    public void onPermissionGranted(){
                                                        setModeGetPhotoFromAbulm(onDialogItemClickListener);
                                                    }
                                                    @Override
                                                    public void onPermissionDenied(){
                                                        MPermissionUtils.showTipsDialog(mBaseActivity,"当前应用缺少读取存储权限(录音,照片,视频等需要),该功能暂时无法使用。如若需要，请单击【确定】按钮前往设置中心进行权限授权。");
                                                    }
                                                });
                                    }else{
                                        setModeGetPhotoFromAbulm(onDialogItemClickListener);
                                    }
                                    break;
                                case CANCEL:
                                    switch (type) {
                                        case FROM_USER_INFO:
                                            break;
                                        default:
                                            break;
                                    }
                                    // isSetPhoto = false;
                                    if (statListener != null)
                                        statListener.onCancel();
                                    if (onDialogItemClickListener != null) {
                                        onDialogItemClickListener.onItemClick(4);
                                    }
                                    break;
                                case CHOICE_VOICE:
                                    if (onDialogItemClickListener != null) {
                                        onDialogItemClickListener.onItemClick(2);
                                    }
                                    break;
                                default:
                                    break;
                            }
                            if(itemClickListener != null){
                                if(idx == ImageChooserHelper.CHOICE_VOICE){
                                    if(Build.VERSION.SDK_INT >= 23){
                                        MPermissionUtils.requestPermissionsResult(mBaseActivity, 1, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_EXTERNAL_STORAGE},
                                                new MPermissionUtils.OnPermissionListener(){
                                                    @Override
                                                    public void onPermissionGranted(){
                                                        itemClickListener.clickedPosition(idx);
                                                    }
                                                    @Override
                                                    public void onPermissionDenied(){
                                                        MPermissionUtils.showTipsDialog(mBaseActivity,"当前应用缺少必要的权限,录音权限或者读取存储权限(录音,照片,视频等需要),该功能暂时无法使用。如若需要，请单击【确定】按钮前往设置中心进行权限授权。");
                                                    }
                                                });
                                    }else{
                                        itemClickListener.clickedPosition(idx);
                                    }
                                }else{
                                    itemClickListener.clickedPosition(idx);
                                }
                            }
                            Dialog dlg = (Dialog) v.getTag(R.id.tag_first);
                            dlg.dismiss();
                        }
                    });
        }
    }

    private void setModeGetPhotoFromAbulm(OnDialogItemClickListener onDialogItemClickListener){
        switch (type) {
            case FROM_USER_INFO:
                break;
            default:
                break;
        }
        if (isNeedCrop) {
            try {
                fileCrop = File.createTempFile(
                        System.currentTimeMillis() + "",
                        ".jpg", fileTakePhoneDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (statListener != null)
            statListener.onalbum();
        if (onDialogItemClickListener != null) {
            onDialogItemClickListener.onItemClick(1);
        }
        if (fromAblum)
            chooseImgFromAlbum();
        else
            chooseFromLibrary();
    }

    private void setTakePhoto(OnDialogItemClickListener onDialogItemClickListener){
        switch (type){
            case FROM_USER_INFO:
                break;
            default:
                break;
        }
        if (isNeedCrop){
            try {
                fileCrop = File.createTempFile(
                        System.currentTimeMillis() + "",
                        ".jpg", fileTakePhoneDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        takePhoto();
        if (statListener != null)
            statListener.onTakePhoto();
        if (onDialogItemClickListener != null) {
            onDialogItemClickListener.onItemClick(0);
        }
    }

	/*
     * private String getChooseImgPath(BaseActivity baseActivity,Intent data) {
	 * String path = null; if (data.getData() != null) { path =
	 * getAlbumChoosedImgPath(baseActivity,data.getData()); File file = null; if
	 * (path != null) { file = new File(path); } if (file == null) {
	 * baseActivity.showToast("选择图片失败！请重试"); path = null; }
	 * LogUtils.i("chooseImg","getChooseImgPath data.getData() != null:"+path);
	 * 
	 * } else if (data.getAction() != null) {// Note 1 等手机返回的是Action File path =
	 * data.getAction();
	 * LogUtils.i("chooseImg","getChooseImgPath data.getData() == null:"
	 * +path);// URI } else {//baseActivity.show(getBaseContext(), "");}
	 * return path; }
	 */

	/*
     * private String getAlbumChoosedImgPath(Context context,Uri
	 * selectedImageUri) {
	 * 
	 * String result = null; // 获取文件的名字和路径 Cursor cursor =
	 * context.getContentResolver().query( selectedImageUri, new String[] {
	 * "_data" }, null, null, null);
	 * 
	 * try{ if (cursor.moveToFirst()) { result = cursor.getString(0); }
	 * }catch(Exception e){ LogUtils.e("error", e.getMessage()); return null; }
	 * 
	 * return result; }
	 */


    public void chooseImgFromAlbum(){
        Intent intent = new Intent();
        intent.setClass(mBaseActivity, AlbumListActivity.class);
        intent.putExtra(AlbumListActivity.INTENT_TAG_MAX_COUNT, maxCount);
        intent.putExtra(AlbumListActivity.INTENT_TAG_HAS_SELECTED, chooseImgs);
        mBaseActivity.startActivity(intent);
    }

    public void chooseFromLibrary() {
        // InitUtils.deletePhoto(InitUtils.getPhotoPath(),
        // EXTRA_TEMP_PHOTO_NAME);
        // File out = new File(InitUtils.getPhotoPath(), EXTRA_TEMP_PHOTO_NAME);
        // Uri imageUri = Uri.fromFile(fileTakePhone);

        Intent mIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		/*
         * Intent mIntent = new Intent(Intent.ACTION_GET_CONTENT);
		 * mIntent.addCategory(Intent.CATEGORY_OPENABLE);
		 * mIntent.setType("image/*");
		 */

        // mIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri.toString());
        mIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        mIntent.putExtra("return-data", false);
        boolean flag = false;
        try {
            mBaseActivity.startActivityForResult(mIntent,
                    RESULT_FOR_GET_PHOTO_FROM_ABULM);
        } catch (ActivityNotFoundException ex) {
            flag = true;
        }
        if(flag){
            try{
                mIntent.setAction(Intent.ACTION_PICK);
                mIntent.setData(MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                mBaseActivity.startActivityForResult(mIntent,
                        RESULT_FOR_GET_PHOTO_FROM_ABULM);
            }catch (ActivityNotFoundException e){
                e.printStackTrace();
            }
        }
    }

    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (InitUtils.isSDCardAvailable()){
            try {
                fileTakePhone = File.createTempFile("phone", ".jpg",
                        fileTakePhoneDir);
                fileCrop = File.createTempFile("crop", ".jpg", fileTakePhoneDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        FileProvider.getUriForFile(mBaseActivity, "com.dmeyc.dmestoreyfm.fileprovider", fileTakePhone));// 文件存储位置
            }else{
                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(fileTakePhone));// 文件存储位置
            }
            mBaseActivity.startActivityForResult(intent, RESULT_FOR_TAKE_PIC);
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtil.show("无法使用相机拍照功能");
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            switch (requestCode) {
                case RESULT_FOR_TAKE_PIC:
                    break;
                case RESULT_FOR_GET_PHOTO_FROM_ABULM:
                    break;
            }
            return;
        }
        switch (requestCode) {
            case RESULT_FOR_TAKE_PIC:
                if (isNeedCrop) {
                    if (InitUtils.isSDCardAvailable()) {
                        cropImageUri(fileTakePhone.getPath(), fileCrop.getPath());
                    } else {
                        Bitmap bm = (Bitmap) data.getExtras().get("data");
                        cropImageUri(write(bm), fileCrop.getPath());
                    }
                } else {
                    chooseOver(fileTakePhone.getPath());
                }
                break;
            case RESULT_FOR_GET_PHOTO_FROM_ABULM:
                String pathChoosed = null;
                System.out.println("data   " + data);
                Uri originalUri = data.getData();
                pathChoosed = AlbumChooseImgHelper.getImageAbsolutePath(mBaseActivity, originalUri);
                if (TextUtils.isEmpty(pathChoosed)) {// Note 1 等手机返回的是Action File
                    pathChoosed = Uri.parse(data.getAction()).getPath();
                }

                if (isNeedCrop) {
                    cropImageUri(pathChoosed, fileCrop.getPath());
                } else {
                    chooseOver(pathChoosed);
                }
                break;
            case RESULT_FOR_TAKE_CROP:
                cropFilePaths.add(fileCrop.getAbsolutePath());
                chooseOver(fileCrop.getAbsolutePath());
                break;
            default:
                break;
        }

    }

    /**
     * 将数据写入系统默认位置
     *
     * @param bm 要保存的字符
     */
    public String write(Bitmap bm) {
        if (bm == null) {
            return null;
        }
        try {
            // System.out.println("write start :"+bm.getByteCount()/1024);

            // 通过openFileOutput方法得到一个输出流，方法参数为创建的文件名（不能有斜杠），操作模式
            // FileOutputStream fos =
            // mBaseActivity.openFileOutput("fileTakePhone.jpg",Context.MODE_WORLD_READABLE);
            FileOutputStream fos = new FileOutputStream(fileTakePhone);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();

            fos.close(); // 关闭输出流
            // 弹出Toast消息
            // Toast.makeText(mBaseActivity,"保存成功",Toast.LENGTH_LONG).show();
            // File file = new
            // File(mBaseActivity.getFilesDir().getAbsolutePath()+"/capture.png");
            // System.out.println("write over :"+file.length());
            return fileTakePhone.getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void chooseOver(String pathChoosed) {
        if (handleChooseResult == null || pathChoosed == null) {
            throw new NullPointerException(
                    "handleChooseResult || pathChoosed is null");
        } else {
            handleChooseResult.onChooseOver(pathChoosed);
        }
    }

    private String getAlbumChoosedImgPath(Uri selectedImageUri) {
        String result = null;

        if ("content".equals(selectedImageUri.getScheme())) {
            // 获取文件的名字和路径
            Cursor cursor = mBaseActivity.getContentResolver().query(
                    selectedImageUri, new String[]{"_data"}, null, null,
                    null);
            if (cursor == null)
                return null;

            try {
                if (cursor.moveToFirst()) {
                    result = cursor.getString(0);
                }
            } catch (Exception e) {
                Log.e("getAlbumChoosedImgPath", e.getMessage());
                return null;
            }
        } else if ("file".equals(selectedImageUri.getScheme())) {
            result = selectedImageUri.getPath();
        }

        return result;
    }

    /**
     * 裁剪
     *
     * @param pathChoosedFile
     * @param pathOutputFile
     */
    protected void cropImageUri(String pathChoosedFile, String pathOutputFile) {
        if (pathChoosedFile == null || pathOutputFile == null) {
            return;
            // throw new
            // NullPointerException("pathChoosedFile == null || pathOutputFile == null");
        }
        Intent intent = new Intent(mBaseActivity,
                CropViewActivity.class).putExtra("pathImg", pathChoosedFile)
                .putExtra("saveCropImgPath", pathOutputFile).putExtra("type", type.ordinal());
        if (cropPhotoSize16_9) {
            mCropVO = new CropVO(true, DimenUtil.getScreenWidth(), ((float) 9) / 16);
        } else {
            mCropVO = null;
        }
        if (mCropVO != null) {
            intent.putExtra("cropVO", mCropVO);
        }
        mBaseActivity.startActivityForResult(intent,
                RESULT_FOR_TAKE_CROP);
    }

    public boolean isNeedCrop() {
        return isNeedCrop;
    }

    public void setNeedCrop(boolean isNeedCrop) {
        this.isNeedCrop = isNeedCrop;
    }

}
