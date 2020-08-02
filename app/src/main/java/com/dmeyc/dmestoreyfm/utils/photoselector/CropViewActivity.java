package com.dmeyc.dmestoreyfm.utils.photoselector;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.utils.DimenUtil;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;


import java.io.File;
import java.io.FileOutputStream;


/**
 * 裁剪图片界面
 *
 * @author user
 */
public class CropViewActivity extends BaseActivity {
    public static final int RESULT_FIALD = -2;

    private ImageViewCrop imageViewCrop;
    private String pathImg;
    private int type;
//    private boolean isLoadImg;

    private void startLoadImgTask(){
        new Handler().postDelayed(new Runnable() {//解决部分手机第一次加载失败的情况
                @Override
                public void run() {
                    Bitmap b = loadImgFromPath();
                    if (b != null) {
//                        isLoadImg = true;
                        imageViewCrop.setBitmap(b);
                    }
                }
            }, 200);
    }

    private Bitmap loadImgFromPath() {
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeFile(pathImg,options);
        //防止除数为0
        if (options.outWidth > 0) {
            int h=DimenUtil.getScreenWidth()*options.outHeight/options.outWidth;



//            Glide.with(this).load(new File(pathImg)).asBitmap().override(DimenUtil.getScreenWidth(),h).into(new SimpleTarget() {
//                @Override
//                public void onResourceReady(Object resource, GlideAnimation glideAnimation) {
//                    if (resource != null) {
//                        Bitmap bitmap = (Bitmap) resource;
//                        if (bitmap != null) {
//                            imageViewCrop.setBitmap(bitmap);
//                        }
//                    }
//                }
//
//            });
        }
        return null;
    }

    private void initUI() {
        imageViewCrop = (ImageViewCrop) findViewById(R.id.iv_crop_img);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("图片剪裁");
    }

    public void clickCancel(View v) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_FIALD, getIntent());
        super.onBackPressed();
    }

    public void clickSure(View v) {

        final Handler handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                switch (msg.what) {
                    case 0://裁剪成功
//                        baseHelper().hideProgressBar();
                        setResult(Activity.RESULT_OK);
                        CropViewActivity.this.finish();
                        break;
                    case 1://裁剪失败
//                        baseHelper().hideProgressBar();
                       ToastUtil.show("剪切图片失败！请重试");
                        setResult(RESULT_FIALD);
                        CropViewActivity.this.finish();
                        break;
                    default:
                        break;
                }

            }

            ;
        };

        new Thread(new Runnable() {

            @Override
            public void run() {
                //Bitmap result = imageViewCrop.getSubsetBitmap();
                if (imageViewCrop != null) {
                    Bitmap result = imageViewCrop.takeScreenShot(CropViewActivity.this);
                    if (result != null) {
                        writeLocal(result);
                        handler.sendEmptyMessage(0);
                    } else {
                        handler.sendEmptyMessage(1);
                    }
                } else {
                    handler.sendEmptyMessage(1);
                }
            }
        }).start();

    }

    private void writeLocal(Bitmap target) {
        String savePath = getIntent().getStringExtra("saveCropImgPath");
        if (savePath == null) {
            ToastUtil.show("剪切图片失败！请重试");
            setResult(RESULT_FIALD);
            this.finish();
            return;
        }
        try {
            FileOutputStream out = new FileOutputStream(savePath);
            target.compress(CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private TextView tv_title;


    public void initWidget() {
        initUI();
        type = getIntent().getIntExtra("type", 0);
        pathImg = getIntent().getStringExtra("pathImg");

        if (getIntent().hasExtra("cropVO")){
            imageViewCrop.mCropVO = (CropVO) getIntent().getSerializableExtra("cropVO");
        }

        CropVO cropVO=new CropVO(true, DimenUtil.getScreenWidth(),1.0f);
        imageViewCrop.mCropVO=cropVO;

        imageViewCrop.invalidate();

        if (pathImg != null) {
//			showProgressBar();
            Bitmap b = loadImgFromPath();
            if (b != null) {
                imageViewCrop.setBitmap(b);
            }else{
                startLoadImgTask();
            }

        } else {
           ToastUtil.show("获取图片失败，请重试！");
            setResult(RESULT_FIALD);
            this.finish();
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_crop_img;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        initWidget();
    }
}
