package com.dmeyc.dmestoreyfm.photolive.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * create by cxg on 2019/12/26
 */
public class BitmapUtil {
    public static Bitmap getCompressBitmap(Context context, String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        Bitmap bitmap = null;

        if (path.contains("jpg") || path.contains("png")
                || path.contains("jpeg") || path.contains("bmp") || path.contains("GIF")
                || path.contains("JPG") || path.contains("PNG") || path.contains("JPEG")
                || path.contains("BMP")) {
            try {
                bitmap = new Compressor(context).compressToBitmap(new File(path));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return bitmap;
    }

    /**
     * 下载完图片返回文件路径
     *
     * @param bitmap
     * @param recycle
     * @return  文件路径
     */
    public static String saveBitmap(Bitmap bitmap, boolean recycle) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            StringBuffer sbPath = new StringBuffer();
            sbPath.append(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)).append(File.separator).append("好兔");

            File haotuFile = new File(sbPath.toString());
            if (!haotuFile.exists()) {
                haotuFile.mkdirs();
            }
            File fileDir = new File(haotuFile, System.currentTimeMillis() + ".jpg");
            try {
                FileOutputStream out = new FileOutputStream(fileDir);
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
                out.flush();
                out.close();
                return fileDir.getAbsolutePath();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e("BitmapUtils", "下载失败");

        }
        if (recycle) {
            bitmap.recycle();
        }
        return "";
    }


}
