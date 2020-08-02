package com.dmeyc.dmestoreyfm.ui.look.section;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import com.SuperKotlin.pictureviewer.ImagePagerActivity;
import com.SuperKotlin.pictureviewer.PictureConfig;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.ui.look.PictureEditorActivity;
import com.dmeyc.dmestoreyfm.utils.XPluginConstant;
import com.dmeyc.dmestoreyfm.utils.image.ImageUtil;
import com.dmeyc.dmestoreyfm.wedgit.sectioned.SectionedRecyclerViewAdapter;
import com.dmeyc.dmestoreyfm.wedgit.sectioned.StatelessSection;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/12/11/011.
 */

public class addImgSection extends StatelessSection {

    private Context context;
    private SectionedRecyclerViewAdapter sectionedRecyclerViewAdapter;
    private ArrayList<String> list = new ArrayList<>();
    private static final int REQUEST_CODE = 12;
    private startActivity startActivityForResultListener;
    private startActivity_2 startActivityForResultListener_2;
    private String isEvaluate;


    public addImgSection(Context context, SectionedRecyclerViewAdapter sectionedRecyclerViewAdapter, String isEvaluate) {
        super(R.layout.section_img_add_view);
        this.context = context;
        this.sectionedRecyclerViewAdapter = sectionedRecyclerViewAdapter;
        this.isEvaluate = isEvaluate;
    }

    public void setData(List<String> data) {
        this.list.clear();
        this.list.addAll(data);
        sectionedRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public int getContentItemsTotal() {
        return list.size() + 1;
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder itemHolder = (ViewHolder) holder;
        if (list.size() == 0) {
            itemHolder.img_haveList.setVisibility(View.GONE);
            itemHolder.img_noList.setVisibility(View.VISIBLE);
        } else {
            if (position == list.size()) {
                itemHolder.img_haveList.setVisibility(View.GONE);
                itemHolder.img_noList.setVisibility(View.VISIBLE);
                if (position == 9) {
                    itemHolder.img_noList.setVisibility(View.GONE);
                }
            } else if (position < list.size()) {
                itemHolder.img_haveList.setVisibility(View.VISIBLE);
                itemHolder.img_noList.setVisibility(View.GONE);
                Bitmap bitmap = loadImage(list.get(position));
                itemHolder.img_haveList.setImageBitmap(bitmap);

            }
        }
        itemHolder.img_noList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positivePhotoClick();
            }
        });
        itemHolder.img_haveList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEvaluate.equals("isEvaluate")) {
                    browsePic(list);
                } else {
                    Intent intent = new Intent();
                    intent.putStringArrayListExtra("imgList", list);
                    intent.setClass(context, PictureEditorActivity.class);
                    context.startActivity(intent);
                }
            }
        });

    }

    private void browsePic(List<String> list) {
        //使用方式
        PictureConfig config = new PictureConfig.Builder()
                .setListData((ArrayList<String>) list)
                .setPosition(0)    //图片下标（从第position张图片开始浏览）
                .setDownloadPath("juneyaoair")    //图片下载文件夹地址
                .setIsShowNumber(true)//是否显示数字下标
                .needDownload(true)    //是否支持图片下载
                .needDelelete(true)    //是否支持图片下载
//                .setPlacrHolder(R.dra.logo)    //占位符图片（图片加载完成前显示的资源图片，来源drawable或者mipmap）
                .build();
        ImagePagerActivity.startActivity(context, config);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.img_haveList)
        public ImageView img_haveList;
        @Bind(R.id.img_noList)
        public ImageView img_noList;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight, String img) {
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        BitmapFactory.decodeFile(img, options);
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    private Bitmap loadImage(String img) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        int j = calculateInSampleSize(options, 100, 100, img);
        options.inSampleSize = j;
        options.inJustDecodeBounds = false;
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        Log.d("MainActivity", "outH:" + outHeight + ":outW:" + outWidth);
        return BitmapFactory.decodeFile(img, options);
    }


    public void positivePhotoClick() {
        startActivityForResultListener_2.CheckAllListener_2(9 - list.size());
//        DialogUtils.getInstance().showDialog(context, new DialogUitlsLinster.DialogLinster() {
//                    @Override
//                    public void onCancel(Dialog dialog) {
////                        限数量的多选(比喻最多9张)
////                        ImageSelector.builder()
////                                .useCamera(false) // 设置是否使用拍照
////                                .setSingle(false)  //设置是否单选
////                                .setMaxSelectCount(9-list.size()) // 图片的最大选择数量，小于等于0时，不限数量。
////                                .setSelected(list) // 把已选的图片传入默认选中。
////                                .setViewImage(true) //是否点击放大图片查看,，默认为true
////                                .start((Activity) context, REQUEST_CODE); // 打开相册
//                        dialog.cancel();
//                    }
//
//                    @Override
//                    public void onConfirm(Dialog dialog) {
//                        openCamera();
//                        dialog.cancel();
//                    }
//                }, context.getString(R.string.activity_photoverified_positive_hint),
//                context.getString(R.string.activity_search_city_result_cancel), context.getString(R.string.activity_photoverified_positive),
//                context.getString(R.string.activity_photoverified_title), R.drawable.bg_held_sample).show();
    }

    public void openCamera() {
        File tempFile = null;
        try {
            tempFile = ImageUtil.createImageFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (tempFile == null) {
            return;
        }
        final Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri uri = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, tempFile.getAbsolutePath());
            uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        } else {
            uri = Uri.fromFile(tempFile);
        }
        String path = tempFile.getAbsolutePath();
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        cameraIntent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        startActivityForResultListener.CheckAllListener(cameraIntent, XPluginConstant.REQ_CAMERA, path);

//        startActivityForResult(cameraIntent,);
    }

    //提供方法给activity调用CommentImgHideListener 实现接口方法 关闭控件
    public void getstartActivityListener(startActivity Listener) {
        startActivityForResultListener = Listener;
    }

    public void getstartActivityListener_2(startActivity_2 Listener) {
        startActivityForResultListener_2 = Listener;
    }

    //接口 关闭此页面
    public interface startActivity {
        void CheckAllListener(Intent i, int code, String path);
    }

    //接口 关闭此页面
    public interface startActivity_2 {
        void CheckAllListener_2(int i);
    }
}
