package com.dmeyc.dmestoreyfm.photolive.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Build;
import android.util.Base64;

import com.orhanobut.logger.Logger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created on : June 18, 2016
 * Author     : zetbaitsu
 * Name       : Zetra
 * GitHub     : https://github.com/zetbaitsu
 */
public class ImageUtilNew {

    private ImageUtilNew() {

    }

    static File compressImage(File imageFile, int reqWidth, int reqHeight, Bitmap.CompressFormat compressFormat, int quality, String destinationPath) throws IOException {
        FileOutputStream fileOutputStream = null;
        File file = new File(destinationPath).getParentFile();
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            fileOutputStream = new FileOutputStream(destinationPath);
            // write the compressed bitmap at the destination specified by destinationPath.
            decodeSampledBitmapFromFile(imageFile, reqWidth, reqHeight).compress(compressFormat, quality, fileOutputStream);
        } finally {
            if (fileOutputStream != null) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }

        return new File(destinationPath);
    }

    public static Bitmap decodeSampledBitmapFromFile(File imageFile) throws IOException{
        return decodeSampledBitmapFromFile(imageFile,612,816);
    }

    public static Bitmap decodeSampledBitmapFromFile(File imageFile, int reqWidth, int reqHeight) throws IOException {
        // First decode with inJustDecodeBounds=true to check dimensions
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imageFile.getAbsolutePath(), options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        Bitmap scaledBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath(), options);

        //check the rotation of the image and display it properly
        ExifInterface exif;
        exif = new ExifInterface(imageFile.getAbsolutePath());
        int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0);
        Matrix matrix = new Matrix();
        if (orientation == 6) {
            matrix.postRotate(90);
        } else if (orientation == 3) {
            matrix.postRotate(180);
        } else if (orientation == 8) {
            matrix.postRotate(270);
        }
        scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
        return scaledBitmap;
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static byte[] parsePhotoToBase64(Bitmap bitmap, int imageSize) {
        Logger.i("原来图片：" + getSize(bitmap) + "");
        int largeSize;
        if (imageSize <= 0) {
            largeSize = 1024 * 1024;
        } else {
            largeSize = imageSize * 1024;
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        String base64 = "";
        byte[] bitmapBytes;
        if (bitmap != null) {
            // (0 - 100)压缩文件
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream);
            bitmapBytes = stream.toByteArray();
            base64 = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            while (base64.getBytes().length > largeSize && quality > 0) {
                quality -= 10;
                stream.reset();
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream);
                bitmapBytes = stream.toByteArray();
            }
            return bitmapBytes;
        }
        return null;
    }
    /**
     * 获取一个bitmap在内存中所占的大小
     * @param image
     * @return
     */
    private static int getSize(Bitmap image){
        int size=0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {    //API 19
            size = image.getAllocationByteCount();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {//API 12
            size = image.getByteCount();
        } else {
            size = image.getRowBytes() * image.getHeight();
        }
        return size;
    }
}
