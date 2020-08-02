/**
 *
 */
package com.dmeyc.dmestoreyfm.utils.photoselector;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Used to scale the Bitmap.
 */
public class BitmapUtil {
    private static final String FORMAT_BITMPA = "/format/webp";
    // used in upload photo page
    public static boolean isNeedNewFlushedInputStream = false;
    public static final String MIME_TYPE_IMAGE = "image/*";
    public static final String EXTRA_TEMP_PHOTO_NAME = "sdp_mpos.jpg";

    public static final String SDCARD_ROOT_PATH = android.os.Environment
            .getExternalStorageDirectory().getAbsolutePath();// 路径
    public static final String SAVE_PATH_IN_SDCARD = "/somall/"; // 图片及其他数据保存文件夹

    public static Bitmap scaleToNewBitmap(Bitmap origin, int width, int height) {
        int orgWidth = origin.getWidth();
        int orgHeight = origin.getHeight();

        float xScale = width / orgWidth;
        float yScale = height / orgHeight;

        Matrix matrix = new Matrix();
        matrix.postScale(xScale, yScale);
        try {
            Bitmap newBitmap = Bitmap.createBitmap(origin, 0, 0, orgWidth,
                    orgHeight, matrix, true);
            return newBitmap;
        } catch (OutOfMemoryError e) {
            // TODO: handle exception
//            LogUtils.e("scaleToNewBitmap", "createBitmap cause oom");
            return null;
        }
    }

    public static Bitmap scaleToNewBitmap(Bitmap origin, float scaleRate) {
        Matrix matrix = new Matrix();
        matrix.postScale(scaleRate, scaleRate);
        try {
            Bitmap newBitmap = Bitmap.createBitmap(origin, 0, 0,
                    origin.getWidth(), origin.getHeight(), matrix, true);
            return newBitmap;
        } catch (OutOfMemoryError e) {
            // TODO: handle exception
//            LogUtils.e("scaleToNewBitmap", "createBitmap cause oom");
            return null;
        }

    }

    public static String saveBitmapToPngFile(Bitmap b, String name) {
        String currentPath = SDCARD_ROOT_PATH + SAVE_PATH_IN_SDCARD;
        FileOutputStream fos = null;
        try {
            File sddir = new File(currentPath);
            if (!sddir.exists()) {
                sddir.mkdirs();
            }
            File file = new File(currentPath + "/" + name + ".png");
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            fos = new FileOutputStream(file);
            if (fos != null) {
                b.compress(CompressFormat.PNG, 100, fos);
                fos.close();
            }
            return currentPath + "/" + name + ".png";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentPath;

    }

    public static String saveBitmapToJpgFile(Bitmap b, String name) {
        return saveBitmapToJpgFile(b, 100, name);
    }

    public static final String saveBitmapToJpgFile(File dir, Bitmap b,
                                                   int quality, String name) {
        FileOutputStream fos = null;
        try {
            File file = new File(dir, name + ".jpg");
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            fos = new FileOutputStream(file);
            if (fos != null) {
                b.compress(CompressFormat.JPEG, quality, fos);
                fos.close();
            }
            return file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String saveBitmapToJpgFile(Bitmap b, int quality, String name) {
        String currentPath = SDCARD_ROOT_PATH + SAVE_PATH_IN_SDCARD;
        FileOutputStream fos = null;
        try {
            File sddir = new File(currentPath);
            if (!sddir.exists()) {
                sddir.mkdirs();
            }
            File file = new File(currentPath + "/" + name + ".jpg");
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            fos = new FileOutputStream(file);
            if (fos != null) {
                b.compress(CompressFormat.JPEG, quality, fos);
                fos.close();
            }
            return currentPath + "/" + name + ".jpg";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentPath;
    }

	/*
     * private static int revitionImageSize(Options localOptions) { if
	 * (localOptions != null) { int imageLevel =
	 * QKApplication.getFitImageLevel(); int sizeLevel = 1; switch (imageLevel)
	 * { case Constants.IMAGE_BIG_LEVEL: sizeLevel = PHOTO_SIZE_BIG; break; case
	 * Constants.IMAGE_NORMAL_LEVEL: sizeLevel = PHOTO_SIZE_MIDDLE; break; case
	 * Constants.IMAGE_SMALL_LEVEL: sizeLevel = PHOTO_SIZE_SMALL; break;
	 * default: sizeLevel = PHOTO_SIZE_MIDDLE; break; }
	 * QKLogger.getInstance().d(TAG, "revitionImageSize:" + sizeLevel); return
	 * getSampleSize(localOptions, photoSize[sizeLevel]); } return 0; }
	 */

    @SuppressWarnings({"unused", "deprecation"})

    public static File convertImageUriToFile(Uri imageUri, Context activity) {
        return new File(AlbumChooseImgHelper.getImageAbsolutePath(activity,imageUri)) ;
    }

    public static int fixBitmapInSampleSize(int bitmapWidth, int bitmapHeight, int inSampleSize) {
        long freeMem = Runtime.getRuntime().freeMemory();
        long bitmapCost;
        int iss = inSampleSize;
        if (freeMem <= 0)
            return inSampleSize;

        while (true) {
            bitmapCost = bitmapWidth * bitmapHeight * 4 / inSampleSize / inSampleSize;
//            LogUtils.e("fixBitmapInSampleSize", "freeMem=" + freeMem + ",bitmapCost=" + bitmapCost + ",bitmapHeight=" + bitmapHeight);
            if (bitmapCost > freeMem * 4 / 5) {
                inSampleSize++;
            } else {
                if (iss != inSampleSize) {
//                    LogUtils.e("fixBitmapInSampleSize", "inSampleSize=" + iss + ",fixed inSampleSize=" + inSampleSize);
                }
                return inSampleSize;
            }

            if (inSampleSize >= 3)
                return inSampleSize;
        }
    }


    public static Bitmap rotate(Bitmap b, int degrees) {
        if (degrees != 0 && b != null) {
            Matrix m = new Matrix();
            m.setRotate(degrees, (float) b.getWidth() / 2,
                    (float) b.getHeight() / 2);
            try {
                Bitmap b2 = Bitmap.createBitmap(b, 0, 0, b.getWidth(),
                        b.getHeight(), m, true);
                if (b != b2) {
                    // b.recycle();
                    // b=null;
                    b = b2;
                }
            } catch (OutOfMemoryError ex) {
                // We have no memory to rotate. Return the original bitmap.
                return b;
            }
        }
        return b;
    }

    public static Bitmap getHttpBitmap(String url, ImageView imageView) {
//        LogUtils.i("lg", url);
        URL myFileUrl = null;
        Bitmap bitmap = null;
        try {
            myFileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            if (myFileUrl != null) {
                HttpURLConnection conn = (HttpURLConnection) myFileUrl
                        .openConnection();
                conn.setConnectTimeout(45 * 1000);
                conn.setDoInput(true);
                conn.connect();
                InputStream is = conn.getInputStream();
                byte[] bs = null;
                BitmapFactory.Options new_bitmapOptions = null;
                try {
                    // bitmap = BitmapFactory.decodeStream(is);
                    bs = readStream(is);
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    bitmapOptions.inJustDecodeBounds = true;
                    if (isNeedNewFlushedInputStream) {
                        bitmap = BitmapFactory
                                .decodeStream(new FlushedInputStream(is));
                    } else {
                        bitmap = BitmapFactory.decodeByteArray(bs, 0,
                                bs.length, bitmapOptions);
                    }

                    new_bitmapOptions = new BitmapFactory.Options();

                    if (imageView != null) {
                        new_bitmapOptions.inSampleSize = computeImageSampleSize(
                                new ImageSize(bitmapOptions.outWidth,
                                        bitmapOptions.outHeight),
                                defineTargetSizeForView(imageView, -1, -1),
                                ViewScaleType.fromImageView(imageView), false);
                    } else {
                        new_bitmapOptions.inSampleSize = 1;
                    }
//                    new_bitmapOptions.inSampleSize = 1;
                    new_bitmapOptions.inSampleSize = fixBitmapInSampleSize(bitmapOptions.outWidth, bitmapOptions.outHeight, new_bitmapOptions.inSampleSize);

                    if (bs != null) {
                        while (bitmap == null) {
                            try {
                                bitmap = BitmapFactory.decodeByteArray(bs, 0,
                                        bs.length, new_bitmapOptions);
                                if (bitmap == null)// 防止非rgb格式的图片 如cmyk则放弃
                                    break;
                            } catch (OutOfMemoryError e) {
                                // TODO: handle exception
//                                LogUtils.e("getHttpBitmap2",
//                                        "while OutOfMemoryError....");
                                new_bitmapOptions.inSampleSize++;
                            }
                        }

                        if(bitmap == null && url.contains(FORMAT_BITMPA)){
                            bs = null;
                            is.close();
//                            LogUtils.e("getHttpBitmap2", "download webp fail:"+url);
                            return getHttpBitmap(url.replaceAll(FORMAT_BITMPA,""),imageView);
                        }

//                        LogUtils.i("getHttpBitmap2", String.format(
//                                "inSampleSize=%d,imageUrl=%s",
//                                new_bitmapOptions.inSampleSize, url));

                    }
                } catch (OutOfMemoryError e) {
                    // TODO: handle exception
                } catch (Exception e) {

                }

                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

//    public static Bitmap getHttpBitmap2(String imageUrl, ImageView imageView,
//                                        boolean isNeedScale) {
//        LogUtils.i("lg", imageUrl);
//        Bitmap bitmap = null;
//        // httpGet连接对象
//        HttpGet httpRequest = new HttpGet(imageUrl);
//        // 取得HttpClient 对象
//        HttpClient httpclient = new DefaultHttpClient();
//        // 请求超时
//        httpclient.getParams().setParameter(
//                CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
//        // 读取超时
//        httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
//                60000);
//        InputStream is = null;
//        byte[] bs = null;
//        BitmapFactory.Options new_bitmapOptions = null;
//
//        try {
//            // 请求httpClient ，取得HttpRestponse
//            HttpResponse httpResponse = httpclient.execute(httpRequest);
//            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//                // 取得相关信息 取得HttpEntiy
//                HttpEntity httpEntity = httpResponse.getEntity();
//                // 获得一个输入流
//                is = httpEntity.getContent();
//
//                bs = readStream(is);
//                BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
//                bitmapOptions.inJustDecodeBounds = true;
//                bitmap = BitmapFactory.decodeByteArray(bs, 0, bs.length,
//                        bitmapOptions);
//                new_bitmapOptions = new BitmapFactory.Options();
//
//                if (imageView != null) {
//                    new_bitmapOptions.inSampleSize = computeImageSampleSize(
//                            new ImageSize(bitmapOptions.outWidth,
//                                    bitmapOptions.outHeight),
//                            defineTargetSizeForView(imageView, -1, -1),
//                            ViewScaleType.fromImageView(imageView), false);
//                } else {
//                    new_bitmapOptions.inSampleSize = 1;
//                } /*else if (isNeedScale) {
//					if (bitmapOptions.outWidth > 2400
//							|| bitmapOptions.outHeight > 2400)
//						new_bitmapOptions.inSampleSize = 4;
//					else if (bitmapOptions.outWidth > 1600
//							|| bitmapOptions.outHeight > 1600)
//						new_bitmapOptions.inSampleSize = 3;
//					else if (bitmapOptions.outWidth > 800
//							|| bitmapOptions.outHeight > 800)
//						new_bitmapOptions.inSampleSize = 2;
//					else
//						new_bitmapOptions.inSampleSize = 1;
//				} else {
//					new_bitmapOptions.inSampleSize = 1;
//				}*/
//
//                new_bitmapOptions.inSampleSize = fixBitmapInSampleSize(bitmapOptions.outWidth, bitmapOptions.outHeight, new_bitmapOptions.inSampleSize);
//                LogUtils.e("########", "new_bitmapOptions :new_bitmapOptions:" + new_bitmapOptions.inSampleSize);
//            }
//
//            if (bs != null) {
//                while (bitmap == null) {
//                    try {
//                        if (isNeedNewFlushedInputStream) {
//                            try {
//                                bitmap = BitmapFactory
//                                        .decodeStream(new FlushedInputStream(is));
//                            } catch (Exception e) {
//                                bitmap = BitmapFactory.decodeByteArray(bs, 0,
//                                        bs.length, new_bitmapOptions);
//                            }
//                        } else {
//                            bitmap = BitmapFactory.decodeByteArray(bs, 0,
//                                    bs.length, new_bitmapOptions);
//                        }
//                        if (bitmap == null)// 防止非rgb格式的图片 如cmyk则放弃
//                            break;
//                    } catch (OutOfMemoryError e) {
//                        // TODO: handle exception
//                        LogUtils.e("getHttpBitmap2",
//                                "while OutOfMemoryError....");
//                        new_bitmapOptions.inSampleSize++;
//                    }
//                }
//
//                LogUtils.i("getHttpBitmap2", String.format(
//                        "inSampleSize=%d,imageUrl=%s",
//                        new_bitmapOptions.inSampleSize, imageUrl));
//
//            }
//
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//            return null;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        } catch (Exception e) {
//            return null;
//        } catch (OutOfMemoryError e) {
//            // TODO: handle exception
//            LogUtils.e("getHttpBitmap2", "OutOfMemoryError....");
//            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
//            bitmapOptions.inSampleSize = 16;
//            bitmap = BitmapFactory.decodeStream(is, null, bitmapOptions);
//        }
//
//        try {
//            if (is != null) {
//                is.close();
//            }
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return bitmap;
//    }

    /*
     * 得到图片字节流 数组大小
     */
    public static byte[] readStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        inStream.close();
        return outStream.toByteArray();
    }

    public static int computeSampleSize(BitmapFactory.Options options,
                                        int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength,
                maxNumOfPixels);

        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }

        return roundedSize;
    }

    private static int computeInitialSampleSize(BitmapFactory.Options options,
                                                int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;

        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.round(Math
                .sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
                Math.floor(w / minSideLength), Math.floor(h / minSideLength));

        if (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }

        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }

    public static byte[] bitmapToBytes(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        // 将Bitmap压缩成PNG编码，质量为100%存储
        bitmap.compress(CompressFormat.PNG, 100, os);// 除了PNG还有很多常见格式，如jpeg等。
        return os.toByteArray();
    }

    /**
     * 得到 图片旋转 的角度
     *
     * @param filepath
     * @return
     */
    public static int getExifOrientation(String filepath) {
        int degree = 0;
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(filepath);
        } catch (IOException ex) {
//            LogUtils.e("shape_usercenter_message_text_bg", "cannot read exif", ex);
        }
        if (exif != null) {
            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, -1);
            if (orientation != -1) {
                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        degree = 90;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        degree = 180;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        degree = 270;
                        break;
                }
            }
        }
        return degree;
    }

    /**
     * 圆角图片
     *
     * @param bitmap
     * @return
     */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
        try {
            Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                    bitmap.getHeight(),
                    Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(output);
            final int color = 0xff424242;
            final Paint paint = new Paint();
            final Rect rect = new Rect(0, 0, bitmap.getWidth(),
                    bitmap.getHeight());
            final RectF rectF = new RectF(rect);
            final float roundPx = 6;

            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);
            // bitmap.recycle();//add by lg 20130717 应该处理下不是默认头像应该回收
            return output;
        } catch (OutOfMemoryError e) {
            // return loadSmallLevelImage(localOptions, filePath);
//            LogUtils.e(
//                    "getRoundedCornerBitmap",
//                    "图片过大 内存溢出 w=" + bitmap.getWidth() + ",h="
//                            + bitmap.getHeight());
            return null;
        }

    }

    public static Bitmap getTintBitmap(Bitmap bitmap, int tintColor) {
        try {
            Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                    bitmap.getHeight(),
                    Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(output);

            final Paint paint = new Paint();
            final Rect rect = new Rect(0, 0, bitmap.getWidth(),
                    bitmap.getHeight());

            PorterDuffColorFilter filter = new PorterDuffColorFilter(tintColor,
                    Mode.SRC_ATOP);
            paint.setColorFilter(filter);

            canvas.drawBitmap(bitmap, rect, rect, paint);
            // bitmap.recycle();//add by lg 20130717 应该处理下不是默认头像应该回收
            return output;
        } catch (OutOfMemoryError e) {
            // return loadSmallLevelImage(localOptions, filePath);
//            LogUtils.e(
//                    "getRoundedCornerBitmap",
//                    "图片过大 内存溢出 w=" + bitmap.getWidth() + ",h="
//                            + bitmap.getHeight());
            return null;
        }

    }

    public static Bitmap getRoundFrameBitmap(Context context, int width,
                                             Bitmap bitmap) {
        final int margin = DisplayUtils.dip2px(context, 4);

        try {
            int x = width - margin;
            Bitmap output = Bitmap.createBitmap(width, width,
                    Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(output);

            final Paint paint = new Paint();

            final float roundPx = 12;
            paint.reset();
            paint.setAntiAlias(true);
            // paint.setColor(0xff424242);
            // paint.setColor(context.getResources().getColor(android.R.color.transparent));
            // 画图片
            // canvas.drawRect(0, 0, width - 2, width - 2, paint);
            RectF rect1 = new RectF(2, 2, width - 2, width - 2);
            canvas.drawRoundRect(rect1, roundPx, roundPx, paint);
            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
            Rect rc_src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            canvas.drawBitmap(bitmap, rc_src, new Rect(margin / 2, margin / 2,
                    x + margin / 2, x + margin / 2), paint);

            // 画白色外圆
            paint.reset();
            paint.setAntiAlias(true);
            paint.setColor(Color.WHITE);
            paint.setStyle(Style.STROKE);
            paint.setStrokeWidth(DisplayUtils.dip2px(context, 3));
            final int w1 = DisplayUtils.dip2px(context, 2);
            // canvas.drawRect(w1, w1, width - w1, width - w1, paint);
            RectF rect = new RectF(w1, w1, width - w1, width - w1);

            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
            canvas.drawRoundRect(rect, roundPx, roundPx, paint);

            // 画外圆边线
            paint.setColor(0xFFD0D0D0);
            paint.setStrokeWidth(DisplayUtils.dip2px(context, 1));
            canvas.drawRect(0, 0, width, width, paint);
            return output;
        } catch (OutOfMemoryError e) {
            // return loadSmallLevelImage(localOptions, filePath);
//            LogUtils.e(
//                    "getRoundedCornerBitmap",
//                    "图片过大 内存溢出 w=" + bitmap.getWidth() + ",h="
//                            + bitmap.getHeight());
            return null;
        }

    }

    public static Bitmap getGroupBitmap(Context context, int width, int number) {
        Bitmap output = Bitmap.createBitmap(width, width,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff7BAB73;
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTypeface(Typeface.DEFAULT);
        paint.setTextSize(DisplayUtils.sp2px(context, 18));

        paint.setColor(color);
        canvas.drawCircle(width / 2, width / 2, width / 2, paint);

        float xPos = width / 2 - paint.measureText(number + "") / 2;
        float yPos = width / 2 - ((paint.descent() + paint.ascent()) / 2);

        paint.setColor(0xffffffff);
        canvas.drawText(number + "", xPos, yPos, paint);

        return output;

    }

    // 将圆形图片,返回Bitmap
    public static Bitmap getCircleBitmap(int width, Bitmap bitmap, Bitmap mask) {

        int x = width;
        Bitmap output = Bitmap.createBitmap(x, x,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        // 根据原来图片大小画一个矩形
        final Rect rect = new Rect(0, 0, x, x);
        paint.setAntiAlias(true);

        paint.setColor(color);
        // 画出一个圆
        canvas.drawCircle(x / 2, x / 2, (x / 2), paint);
        // canvas.translate(-25, -6);
        // 取两层绘制交集,显示上层
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        // 将图片画上去
        Rect rc_src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, rc_src, rect, paint);

        paint.reset();
        rc_src = new Rect(0, 0, mask.getWidth(), mask.getHeight());

        // canvas.drawBitmap(mask, rc_src, rect, paint);
        // 返回Bitmap对象
        return output;
    }

    public static Bitmap getCircleBitmap(Context context, int width,
                                         Bitmap bitmap, String text) {
        final int margin = DisplayUtils.dip2px(context, 4);

        int x = width - margin;
        Bitmap output = Bitmap.createBitmap(width, width,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();

        // 画圆图
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(0xff424242);

        canvas.drawCircle(width / 2, width / 2, x / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        Rect rc_src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, rc_src, new Rect(margin / 2, margin / 2, x
                + margin / 2, x + margin / 2), paint);

        // 混合半圆蒙版
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(0x9F757575);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_ATOP));
        canvas.drawRect(new Rect(margin / 2, width / 5 * 3, x + margin / 2, x
                + margin / 2), paint);

        // 画文字
        paint.reset();
        paint.setAntiAlias(true);
        paint.setTextAlign(Align.CENTER);
        paint.setColor(Color.WHITE);
        paint.setTextSize(30);
        FontMetrics fontMetrics = paint.getFontMetrics();
        float fontHeight = fontMetrics.bottom - fontMetrics.top;
        canvas.drawText(text, width / 2, width / 5 * 3 + fontHeight + margin,
                paint);

        // 画白色外圆
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(DisplayUtils.dip2px(context, 3));
        canvas.drawCircle(width / 2, width / 2,
                width / 2 - DisplayUtils.dip2px(context, 3), paint);

        // 画外圆边线
        paint.setColor(0xFFD0D0D0);
        paint.setStrokeWidth(DisplayUtils.dip2px(context, 1));
        canvas.drawCircle(width / 2, width / 2,
                width / 2 - DisplayUtils.dip2px(context, 1), paint);

        return output;
    }

    /**
     * 圆形图片，有白色边框
     */
    public static Bitmap getCircleBitmap(Context context, int width,
                                         Bitmap bitmap) {
        final int margin = DisplayUtils.dip2px(context, 3);

        int x = width - 2 * margin;
        Bitmap output = Bitmap.createBitmap(width, width,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();

        // 画圆图
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);

        canvas.drawCircle(width / 2, width / 2, x / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        Rect rc_src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, rc_src, new Rect(margin, margin, x + margin,
                x + margin), paint);

        // 画白色外圆
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(margin);
        canvas.drawCircle(width / 2, width / 2, x / 2, paint);

        return output;
    }

    public static Bitmap getRectBitmap(Context context, int width,
                                       Bitmap bitmap, String text) {
        final int margin = DisplayUtils.dip2px(context, 4);

        int x = width - margin;
        Bitmap output = Bitmap.createBitmap(width, width,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();

        // 画圆图
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(0xff424242);

        canvas.drawRect(0, 0, width, width, paint);
        // canvas.drawCircle(width / 2, width / 2, x / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        Rect rc_src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, rc_src, new Rect(margin / 2, margin / 2, x
                + margin / 2, x + margin / 2), paint);

        // 混合半圆蒙版
        paint.reset();
        paint.setAntiAlias(true);
        paint.setARGB(127, 0, 0, 0);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_ATOP));
        canvas.drawRect(new Rect(margin / 2, width / 5 * 3, x + margin / 2, x
                + margin / 2), paint);

        // 画文字
        paint.reset();
        paint.setAntiAlias(true);
        paint.setTextAlign(Align.CENTER);
        paint.setColor(Color.WHITE);
        paint.setTextSize(30);
        FontMetrics fontMetrics = paint.getFontMetrics();
        float fontHeight = fontMetrics.bottom - fontMetrics.top;
        canvas.drawText(text, width / 2, width / 5 * 3 + fontHeight + margin,
                paint);

        // 画白色外圆
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(DisplayUtils.dip2px(context, 3));
        // canvas.drawCircle(width / 2, width / 2, width / 2 -
        // DisplayUtils.dip2px(context, 3), paint);
        final int w1 = DisplayUtils.dip2px(context, 2);
        canvas.drawRect(w1, w1, width - w1, width - w1, paint);

        // 画外圆边线
        paint.setColor(0xFFD0D0D0);
        paint.setStrokeWidth(DisplayUtils.dip2px(context, 1));
        // canvas.drawCircle(width / 2, width / 2, width / 2 -
        // DisplayUtils.dip2px(context, 1), paint);
        canvas.drawRect(0, 0, width, width, paint);

        return output;
    }

    public static Bitmap getRectBitmap(Context context, int width, Bitmap bitmap) {
        final int margin = DisplayUtils.dip2px(context, 4);

        int x = width - margin;
        Bitmap output = Bitmap.createBitmap(width, width,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();

        // 画圆图
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(0xff424242);

        canvas.drawRect(0, 0, width, width, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        Rect rc_src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, rc_src, new Rect(margin / 2, margin / 2, x
                + margin / 2, x + margin / 2), paint);

        // 画白色外圆
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(DisplayUtils.dip2px(context, 3));
        final int w1 = DisplayUtils.dip2px(context, 2);
        canvas.drawRect(w1, w1, width - w1, width - w1, paint);

        // 画外圆边线
        paint.setColor(0xFFD0D0D0);
        paint.setStrokeWidth(DisplayUtils.dip2px(context, 1));
        canvas.drawRect(0, 0, width, width, paint);

        return output;
    }

    public static Bitmap getCircleBitmapWithWhiteEdge(Context context,
                                                      int width, Bitmap bitmap) {
        final int margin = DisplayUtils.dip2px(context, 4);

        int x = width - margin;
        Bitmap output = Bitmap.createBitmap(width, width,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();

        // 画圆图
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(0xff424242);

        canvas.drawCircle(width / 2, width / 2, x / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        Rect rc_src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        canvas.drawBitmap(bitmap, rc_src, new Rect(margin / 2, margin / 2, x
                + margin / 2, x + margin / 2), paint);

        // 混合半圆蒙版
		/*
		 * paint.reset(); paint.setAntiAlias(true); paint.setColor(0x9F757575);
		 * paint.setXfermode(new PorterDuffXfermode(Mode.SRC_ATOP));
		 * canvas.drawRect(new Rect(margin / 2, width / 5 * 3, x + margin / 2, x
		 * + margin / 2), paint);
		 */

        // 画文字
		/*
		 * paint.reset(); paint.setAntiAlias(true);
		 * paint.setTextAlign(Align.CENTER); paint.setColor(Color.WHITE);
		 * paint.setTextSize(30); FontMetrics fontMetrics =
		 * paint.getFontMetrics(); float fontHeight = fontMetrics.bottom -
		 * fontMetrics.top; canvas.drawText(text, width / 2, width / 5 * 3 +
		 * fontHeight + margin, paint);
		 */

        // 画白色外圆
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(DisplayUtils.dip2px(context, 3));
        canvas.drawCircle(width / 2, width / 2,
                width / 2 - DisplayUtils.dip2px(context, 3), paint);

		/*
		 * // 画外圆边线 paint.setColor(0xFFD0D0D0);
		 * paint.setStrokeWidth(DisplayUtils.dip2px(context, 1));
		 * canvas.drawCircle(width / 2, width / 2, width / 2 -
		 * DisplayUtils.dip2px(context, 1), paint);
		 */

        return output;
    }

    /**
     * You can call this method to generate the circular bitmap, even if you
     * don't use this class
     */
    /**
     * @param bitmap
     * @param wid    输出图片的宽
     * @return
     */
    public static Bitmap getCircleBitmap(Bitmap bitmap, int wid) {
        try {
            Bitmap output = Bitmap.createBitmap(wid, wid,
                    Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(output);

            final int color = 0xff424242;
            final Paint paint = new Paint();
            // 根据原来图片大小画一个矩形
            final Rect rect = new Rect(0, 0, wid, wid);
            paint.setAntiAlias(true);

            paint.setColor(color);
            // 画出一个圆
            canvas.drawCircle(wid / 2, wid / 2, (wid / 2), paint);
            // canvas.translate(-25, -6);
            // 取两层绘制交集,显示上层
            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
            // 将图片画上去
            Rect rc_src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            canvas.drawBitmap(bitmap, rc_src, rect, paint);
            paint.reset();
            return output;
        } catch (OutOfMemoryError e) {
//            LogUtils.e("getCircleBitmap", "图片过大 内存溢出 w=" + bitmap.getWidth()
//                    + ",h=" + bitmap.getHeight());
            return null;
        }

    }

    public static Bitmap getRoundTopBitmap(Bitmap bitmap, int w, int h, int r) {
        try {
            Bitmap output = Bitmap.createBitmap(w, h,
                    Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(output);

            final int color = 0xff424242;
            final Paint paint = new Paint();
            // 根据原来图片大小画一个矩形
            final RectF rect = new RectF(0, 0, w, h);
            paint.setAntiAlias(true);

            paint.setColor(color);

            canvas.drawRoundRect(rect, r, r, paint);
            canvas.drawRect(0, h / 2, w, h, paint);

            // 取两层绘制交集,显示上层
            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
            // 将图片画上去
            Rect rc_src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            canvas.drawBitmap(bitmap, rc_src, rect, paint);
            paint.reset();
            return output;
        } catch (OutOfMemoryError e) {
//            LogUtils.e("getCircleBitmap", "图片过大 内存溢出 w=" + bitmap.getWidth()
//                    + ",h=" + bitmap.getHeight());
            return null;
        }

    }

    /**
     * 将彩色图转换为灰度图
     *
     * @param img 位图 <a href=
     *            "\"http://www.eoeandroid.com/home.php?mod=space&uid=7300\""
     *            target="\"_blank\"">@return</a> 返回转换好的位图
     */
    public static Bitmap convertGreyImg(Bitmap img, boolean isNeedRecycle) {
        int width = img.getWidth(); // 获取位图的宽
        int height = img.getHeight(); // 获取位图的高

        int[] pixels = new int[width * height]; // 通过位图的大小创建像素点数组

        img.getPixels(pixels, 0, width, 0, 0, width, height);
        int alpha = 0xFF << 24;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int grey = pixels[width * i + j];

                int red = ((grey & 0x00FF0000) >> 16);
                int green = ((grey & 0x0000FF00) >> 8);
                int blue = (grey & 0x000000FF);

                grey = (int) ((float) red * 0.3 + (float) green * 0.59 + (float) blue * 0.11);
                grey = alpha | (grey << 16) | (grey << 8) | grey;
                pixels[width * i + j] = grey;
            }
        }
        try {
            Bitmap result = Bitmap.createBitmap(width, height,
                    Bitmap.Config.RGB_565);
            result.setPixels(pixels, 0, width, 0, 0, width, height);
            if (isNeedRecycle) {
                img.recycle();
            }
            return result;
        } catch (OutOfMemoryError e) {
            // TODO: handle exception
            return img;
        }

    }

    public static byte[] bmpToByteArray(final Bitmap bmp,
                                        final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(CompressFormat.PNG, 60, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        try {
            Bitmap bitmap = Bitmap
                    .createBitmap(
                            drawable.getIntrinsicWidth(),
                            drawable.getIntrinsicHeight(),
                            drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                    : Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }

    }

    public static Bitmap getThumbnail(String path, int THUMBNAIL_SIZE)
            throws IOException {
        // Decode image size
        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, onlyBoundsOptions);

        int originalSize = (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth) ? onlyBoundsOptions.outHeight
                : onlyBoundsOptions.outWidth;
        double ratio = (originalSize > THUMBNAIL_SIZE) ? (originalSize / THUMBNAIL_SIZE)
                : 1.0;
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio);
        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// optional
        Bitmap bitmap = BitmapFactory.decodeFile(path, bitmapOptions);

        int angle = getExifOrientation(path);
        if (angle != 0) {

            if (bitmap.getConfig() == Bitmap.Config.ARGB_8888) {
                /*if (angle == 90 && InitUtils.getCurrentOsVersionCode() > 10)// 用native方法可以减少一半的内存开销
                {
                    return JNIHelper.rotateBitmapCcw90(bitmap);
                } else*/ {
                    Matrix matrix = new Matrix();
                    matrix.postRotate(angle);
                    Bitmap photoViewBitmap = Bitmap
                            .createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                                    bitmap.getHeight(), matrix, true);
                    bitmap.recycle();
                    return photoViewBitmap;
                }
            } else {
                Matrix matrix = new Matrix();
                matrix.postRotate(angle);
                Bitmap photoViewBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                        bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                bitmap.recycle();
                return photoViewBitmap;
            }

        }
        return bitmap;
    }

    private static int getPowerOfTwoForSampleRatio(double ratio) {
        int k = Integer.highestOneBit((int) Math.floor(ratio));
        if (k == 0)
            return 1;
        else
            return k;
    }

    public static ImageSize defineTargetSizeForView(ImageView imageView,
                                                    int maxImageWidth, int maxImageHeight) {
        final DisplayMetrics displayMetrics = imageView.getContext()
                .getResources().getDisplayMetrics();

        final LayoutParams params = imageView.getLayoutParams();
        int width = (int) (params.width == LayoutParams.WRAP_CONTENT ? 0
                : imageView.getWidth() * 1.0f); // Get actual image width
        if (width <= 0)
            width = params.width; // Get layout width parameter
        if (width <= 0)
            width = getImageViewFieldValue(imageView, "mMaxWidth"); // Check
        // maxWidth
        // parameter
        if (width <= 0)
            width = maxImageWidth;
        if (width <= 0)
            width = displayMetrics.widthPixels;

        int height = (int) (params.height == LayoutParams.WRAP_CONTENT ? 0
                : imageView.getHeight() * 1.0f); // Get actual image height
        if (height <= 0)
            height = params.height; // Get layout height parameter
        if (height <= 0)
            height = getImageViewFieldValue(imageView, "mMaxHeight"); // Check
        // maxHeight
        // parameter
        if (height <= 0)
            height = maxImageHeight;
        if (height <= 0)
            height = displayMetrics.heightPixels;

        return new ImageSize(width, height);
    }

    private static int getImageViewFieldValue(Object object, String fieldName) {
        int value = 0;
        try {
            Field field = ImageView.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            int fieldValue = (Integer) field.get(object);
            if (fieldValue > 0 && fieldValue < Integer.MAX_VALUE) {
                value = fieldValue;
            }
        } catch (Exception e) {
//            LogUtils.e("getImageViewFieldValue", e.toString());
        }
        return value;
    }

    /**
     * Computes sample size for downscaling image size (<b>srcSize</b>) to view
     * size (<b>targetSize</b>). This sample size is used during
     * {@linkplain BitmapFactory#decodeStream(InputStream, Rect, BitmapFactory.Options)
     * decoding image} to bitmap.<br />
     * <br />
     * <b>Examples:</b><br />
     * <p/>
     * <pre>
     * srcSize(100x100), targetSize(10x10), powerOf2Scale = true -> sampleSize = 8
     * srcSize(100x100), targetSize(10x10), powerOf2Scale = false -> sampleSize = 10
     *
     * srcSize(100x100), targetSize(20x40), viewScaleType = FIT_INSIDE -> sampleSize = 5
     * srcSize(100x100), targetSize(20x40), viewScaleType = CROP       -> sampleSize = 2
     * </pre>
     * <p/>
     * <br />
     * The sample size is the number of pixels in either dimension that
     * correspond to a single pixel in the decoded bitmap. For example,
     * inSampleSize == 4 returns an image that is 1/4 the width/height of the
     * original, and 1/16 the number of pixels. Any value <= 1 is treated the
     * same as 1.
     *
     * @param srcSize       Original (image) size
     * @param targetSize    Target (view) size
     * @param viewScaleType {@linkplain ViewScaleType Scale type} for placing image in
     *                      view
     * @param powerOf2Scale <i>true</i> - if sample size be a power of 2 (1, 2, 4, 8, ...)
     * @return Computed sample size
     */
    public static int computeImageSampleSize(ImageSize srcSize,
                                             ImageSize targetSize, ViewScaleType viewScaleType,
                                             boolean powerOf2Scale) {
        float srcWidth = srcSize.getWidth();
        float srcHeight = srcSize.getHeight();
        float targetWidth = targetSize.getWidth();
        float targetHeight = targetSize.getHeight();

        int scale = 1;

        int widthScale = Math.round(srcWidth / targetWidth);
        int heightScale = Math.round(srcHeight / targetHeight);

        switch (viewScaleType) {
            case FIT_INSIDE:
                if (powerOf2Scale) {
                    while (srcWidth / 2 >= targetWidth
                            || srcHeight / 2 >= targetHeight) { // ||
                        srcWidth /= 2;
                        srcHeight /= 2;
                        scale *= 2;
                    }
                } else {
                    scale = Math.max(widthScale, heightScale); // max
                }
                break;
            case CROP:
                if (powerOf2Scale) {
                    while (srcWidth / 2 >= targetWidth
                            && srcHeight / 2 >= targetHeight) { // &&
                        srcWidth /= 2;
                        srcHeight /= 2;
                        scale *= 2;
                    }
                } else {
                    scale = Math.min(widthScale, heightScale); // min
                }
                break;
        }

        if (scale < 1) {
            scale = 1;
        }

        // add by liuwei for l080p
        if (scale == 1) {
            if (srcWidth >= 2400 || srcHeight >= 2400)
                scale = 4;
            else if (srcWidth >= 1200 || srcHeight >= 1200)
                scale = 2;
            else
                scale = 1;

        }

        return scale;
    }



    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }
        return inSampleSize;
    }

    public enum ViewScaleType {
        /**
         * Scale the image uniformly (maintain the image's aspect ratio) so that
         * both dimensions (width and height) of the image will be equal to or
         * less the corresponding dimension of the view.
         */
        FIT_INSIDE,
        /**
         * Scale the image uniformly (maintain the image's aspect ratio) so that
         * both dimensions (width and height) of the image will be equal to or
         * larger than the corresponding dimension of the view.
         */
        CROP;

        public static ViewScaleType fromImageView(ImageView imageView) {
            switch (imageView.getScaleType()) {
                case FIT_CENTER:
                case FIT_XY:
                case FIT_START:
                case FIT_END:
                case CENTER_INSIDE:
                    return FIT_INSIDE;
                case MATRIX:
                case CENTER:
                case CENTER_CROP:
                default:
                    return CROP;
            }
        }
    }

    /**
     * 获取指定大少的图片url,如果wid,hei为0，则不进行拼接。
     * this method is deprecated please call BitmapUtil.replaceURL3
     * @param url
     * @param wid
     * @param hei
     * @return
     */
    @Deprecated
    public static String getAssignSizeImgUrl(String url, int wid, int hei) {

        /*final String tag = "imageView/3/w/";
        if (url == null || url.equals("")) {
            return "";
        }

        if (wid == 0 && hei == 0) {
            if (url.contains(tag)) {
                url = url.substring(0, url.indexOf(tag) - 1);
            }
            return url;
        }

        if (url.lastIndexOf("?") == -1) {
            url += "?";
        }

        if (url.contains("imageView/3/w/")) {
            url = url.replaceAll("imageView/3/w/(.*)", "imageView/3/w/" + wid
                    + "/h/" + hei);
        } else {
            url += "imageView/3/w/" + wid + "/h/" + hei;
        }*/

        return BitmapUtil.replaceURL3(url, wid, hei);
    }

    private static String makeRandomCode(String url) {
        if (url.contains("?")) {
            url += "&t=" + Math.random();
        } else {
            url += "?t=" + Math.random();
        }

        return url;
    }

    public static Bitmap scaleBitmapToScreenWidth(int resID, Context ctx) {
        Bitmap bmp = null;
        try {
            bmp = BitmapFactory.decodeResource(ctx.getResources(), resID);
            if (bmp != null) {
                WindowManager wm = (WindowManager) ctx
                        .getSystemService(Context.WINDOW_SERVICE);
                @SuppressWarnings("deprecation")
                int dstW = wm.getDefaultDisplay().getWidth();
                float f = (float) bmp.getHeight() / bmp.getWidth();
                float dstH = dstW * f;
                Bitmap bmpNew = Bitmap.createScaledBitmap(bmp, dstW,
                        (int) dstH, true);
                return bmpNew;
            }
        } catch (OutOfMemoryError e) {
            // TODO: handle exception
            return bmp;
        }
        return bmp;
    }

    /**
     * 按比例缩放到指定的宽度
     *
     * @param ctx
     * @param targetBmp
     * @param targetWid
     * @return
     */
    public static Bitmap scaleBitmapToAssignWidth(Context ctx,
                                                  Bitmap targetBmp, int targetWid) {
        Bitmap bmpNew = null;
        try {
            if (targetBmp != null) {
                float f = (float) targetBmp.getHeight() / targetBmp.getWidth();
                float dstH = targetWid * f;
                bmpNew = Bitmap.createScaledBitmap(targetBmp, targetWid,
                        (int) dstH, true);
                return bmpNew;
            }
        } catch (OutOfMemoryError e) {
            return bmpNew;
        }
        return bmpNew;
    }

    /**
     * 将图片按比例压缩到接近指定大少
     *
     * @param bitmap
     * @param targetWidth
     * @param targetHeight
     * @return
     */
    public static Bitmap compressBmpByWid(Bitmap bitmap, int targetWidth,
                                          int targetHeight) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 100, baos);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        bitmap = BitmapFactory.decodeByteArray(baos.toByteArray(), 0,
                baos.toByteArray().length, opts);
        int imgWidth = opts.outWidth;
        int imgHeight = opts.outHeight;
        int widthRatio = Math.round(imgWidth / (float) targetWidth);
        int heightRatio = Math.round(imgHeight / (float) targetHeight);
        if (widthRatio > 1 && widthRatio > 1) {
            if (widthRatio > heightRatio) {
                opts.inSampleSize = widthRatio;
            } else {
                opts.inSampleSize = heightRatio;
            }
        }
        opts.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeByteArray(baos.toByteArray(), 0,
                baos.toByteArray().length, opts);
        return bitmap;
    }



    static class FlushedInputStream extends FilterInputStream {
        public FlushedInputStream(InputStream inputStream) {
            super(inputStream);
        }

        @Override
        public long skip(long n) throws IOException {
            long totalBytesSkipped = 0L;
            while (totalBytesSkipped < n) {
                long bytesSkipped = in.skip(n - totalBytesSkipped);
                if (bytesSkipped == 0L) {
                    int b = read();
                    if (b < 0) {
                        break; // we reached EOF
                    } else {
                        bytesSkipped = 1; // we read one byte
                    }
                }
                totalBytesSkipped += bytesSkipped;
            }
            return totalBytesSkipped;
        }
    }

    /**
     * 获取最适合屏幕得图片.
     *
     * @param fileName
     * @param maxWidth
     * @param maxHeight
     * @return
     */
    public static Bitmap getBestBitmapFromFile(String fileName, int maxWidth,
                                               int maxHeight) {
        final BitmapFactory.Options decodeOptions = new BitmapFactory.Options();
        decodeOptions.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(fileName, decodeOptions);

        final int actualWidth = decodeOptions.outWidth;
        final int actualHeight = decodeOptions.outHeight;

        final int desiredWidth = getResizedDimension(maxWidth, maxHeight,
                actualWidth, actualHeight);
        final int desiredHeight = getResizedDimension(maxHeight, maxWidth,
                actualHeight, actualWidth);

        decodeOptions.inJustDecodeBounds = false;
        decodeOptions.inSampleSize = findBestSampleSize(actualWidth,
                actualHeight, desiredWidth, desiredHeight);
        final Bitmap tempBitmap = BitmapFactory.decodeFile(fileName,
                decodeOptions);

        if (tempBitmap != null
                && (tempBitmap.getWidth() > desiredWidth || tempBitmap
                .getHeight() > desiredHeight)) {
            bitmap = Bitmap.createScaledBitmap(tempBitmap, desiredWidth,
                    desiredHeight, true);
            tempBitmap.recycle();
        } else {
            bitmap = tempBitmap;
        }
        return bitmap;
    }

    /**
     * 获取最适合屏幕得图片【效果同ImageView的centerCrop】
     */
    public static Bitmap getBestBitmapFromBitmap(Bitmap srcBitmap, int maxWidth, int maxHeight) {
        //图片宽高
        int imgWidth = srcBitmap.getWidth();
        int imgHeight = srcBitmap.getHeight();
        //控件宽高
        int vWidth = maxWidth;
        int vHeight = maxHeight;
//        LogUtils.i("###", "imgWidth=" + imgWidth + ",imgHeight=" + imgHeight + ",vWidth=" + vWidth + ",vHeight=" + vHeight);
        int x1 = 0, y1 = 0, x2, y2;
        if (imgWidth * vHeight > vWidth * imgHeight) {
            y2 = imgHeight;
            x1 = (int) ((imgWidth - vWidth * imgHeight * 1f / vHeight) * 0.5f + 0.5f);
            x2 = (int) ((imgWidth + vWidth * imgHeight * 1f / vHeight) * 0.5f + 0.5f);
        } else {
            x2 = imgWidth;
            y1 = (int) ((imgHeight - vHeight * imgWidth * 1f / vWidth) * 0.5f + 0.5f);
            y2 = (int) ((imgHeight + vHeight * imgWidth * 1f / vWidth) * 0.5f + 0.5f);
        }
//        LogUtils.i("###", "x1=" + x1 + ",y1=" + y1 + ",x2=" + x2 + ",y2=" + y2);
        if (srcBitmap != null) {
            try {
                Bitmap destBitmap = Bitmap.createBitmap(srcBitmap, x1, y1, x2, y2);
//				srcBitmap.recycle();
                return destBitmap;
            } catch (OutOfMemoryError e) {
                return srcBitmap;
            }
        }
        return srcBitmap;
    }

    public static void writeLocalImg(Context context, Bitmap target, String name) {
        if (target == null) {
            return;
        }
        try {
            FileOutputStream out = new FileOutputStream(
                    context.getExternalFilesDir(null) + "/img/" + name);
            target.compress(CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int findBestSampleSize(int actualWidth, int actualHeight,
                                          int desiredWidth, int desiredHeight) {
        final double wr = (double) actualWidth / desiredWidth;
        final double hr = (double) actualHeight / desiredHeight;
        final double ratio = Math.min(wr, hr);
        float n = 1.0f;
        while ((n * 2) <= ratio) {
            n *= 2;
        }

        return (int) n;
    }

    private static int getResizedDimension(int maxPrimary, int maxSecondary,
                                           int actualPrimary, int actualSecondary) {
        if (maxPrimary == 0 && maxSecondary == 0) {
            return actualPrimary;
        }

        if (maxPrimary == 0) {
            final double ratio = (double) maxSecondary
                    / (double) actualSecondary;
            return (int) (actualPrimary * ratio);
        }

        if (maxSecondary == 0) {
            return maxPrimary;
        }

        final double ratio = (double) actualSecondary / (double) actualPrimary;
        int resized = maxPrimary;
        if (resized * ratio > maxSecondary) {
            resized = (int) (maxSecondary / ratio);
        }
        return resized;
    }

    /***
     * this method is deprecated please call replaceURL3
     * @param url
     * @param w
     * @param h
     * @return
     */
    @Deprecated
    public static String replaceURL(String url, int w, int h) {
        /*if (w == 0 || h == 0 || TextUtils.isEmpty(url)) {
            return url;
        }
        String u = "";
        if (url.contains("?imageView")) {
            u = url.substring(0, url.indexOf("?imageView")) + "?imageView/3/w/"
                    + w + "/h/" + h;
        } else if (url.contains("?imageMogr2")) {
            u = url;
        } else {
            u = url + "?imageView/3/w/" + w + "/h/" + h;
        }*/
        return replaceURL3(url,w,h);
    }


    /**
     * http://find.img1.entstudy.com/photo/9299635811393963539.jpg?imageMogr2/gravity/Center/crop/300x300|imageMogr2/blur/30x50
     * add by wangjianwei
     */
    public static String getQiNiuCropAndBlurImageUrl(String url, int width, int height, int radius, int degree) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        String imageUrl = "";
        if (width == 0 || height == 0) {
            if (radius == 0 || degree == 0) {
                return url;
            }
            if (url.contains("?imageView")) {
                imageUrl = url.substring(0, url.indexOf("?imageView")) + "?imageMogr2/blur/" + radius + "x" + degree;
            } else if (url.contains("?imageMogr2")) {
                imageUrl = url.substring(0, url.indexOf("?imageMogr2")) + "?imageMogr2/blur/" + radius + "x" + degree;

            } else {
                imageUrl = url + "?imageMogr2/blur/" + radius + "x" + degree;
            }
        } else {
            if (radius == 0 || degree == 0) {
                if (url.contains("?imageView")) {
                    imageUrl = url.substring(0, url.indexOf("?imageView")) + "?imageMogr2/gravity/Center/crop/" + width + "x" + height;
                } else if (url.contains("?imageMogr2")) {
                    imageUrl = url.substring(0, url.indexOf("?imageMogr2")) + "?imageMogr2/gravity/Center/crop/" + width + "x" + height;
                } else {
                    imageUrl = url + "?imageMogr2/gravity/Center/crop/" + width + "x" + height;
                }
            } else {
                if (url.contains("?imageView")) {
                    imageUrl = url.substring(0, url.indexOf("?imageView")) + "?imageMogr2/gravity/Center/crop/" + width + "x" + height+"|imageMogr2/blur/" + radius + "x" + degree;
                } else if (url.contains("?imageMogr2")) {
                    imageUrl = url.substring(0, url.indexOf("?imageMogr2")) + "?imageMogr2/gravity/Center/crop/" + width + "x" + height+"|imageMogr2/blur/" + radius + "x" + degree;
                } else {
                    imageUrl = url + "?imageMogr2/gravity/Center/crop/" + width + "x" + height+"|imageMogr2/blur/" + radius + "x" + degree;
                }
            }
        }
        return imageUrl;
    }

    /***
     * this method is deprecated please call replaceURL3
     * @param url
     * @param w
     * @param h
     * @return
     */
    @Deprecated
    public static String replaceURL2(String url, int w, int h) {
        /*if (w == 0 && h == 0 || StringUtil.isEmpty(url)) {
            return url;
        }
        String u = "";
        if (url.contains("?imageView")) {
            u = url.substring(0, url.indexOf("?imageView")) + "?imageView/3/w/"
                    + w + "/h/" + h;
        } else {
            u = url;
        }*/
        return replaceURL3(url,w,h);
    }

    public static String replaceURL3(String url, int w, int h) {
        if (w <= 0 || h <= 0 || TextUtils.isEmpty(url)) {
            return url;
        }
        String u = "";
        if (url.contains("?imageView")) {
            u = url.substring(0, url.indexOf("?imageView")) + "?imageView/3/w/"
                    + w + "/h/" + h+FORMAT_BITMPA;
        } else if (url.contains("?imageMogr2")) {
            u = url;
        } else {
            u = url + "?imageView/3/w/" + w + "/h/" + h+FORMAT_BITMPA;
        }
        return u;
    }


    public static void makeViewBackgroundWithPressedState(View iv, Bitmap srcBmp, int tintColor) {
        try {
            StateListDrawable states = new StateListDrawable();
            states.addState(new int[]{android.R.attr.state_pressed},
                    new BitmapDrawable(iv.getResources(), BitmapUtil.getTintBitmap(srcBmp, tintColor)));
            states.addState(new int[]{},
                    new BitmapDrawable(iv.getResources(), srcBmp));
            iv.setBackgroundDrawable(states);
        } catch (OutOfMemoryError e) {
            iv.setBackgroundDrawable(new BitmapDrawable(iv.getResources(), srcBmp));
        }
    }

    public static String saveImageToGallery(Context context, Bitmap bmp, String name) {
        if (!InitUtils.isSDCardAvailable()) {
            return null;
        }
        // 首先保存图片
        File appDir = context.getExternalFilesDir("img");
        if (!appDir.exists()) {
            appDir.mkdirs();
        }

        //String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, name);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), name, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getAbsolutePath())));
        return file.getAbsolutePath();
    }

    /**
     * 截屏
     *
     * @param v        视图
     * @param filePath 保存路径
     */
    public static boolean screenShot(View v, String filePath) {
        boolean result = false;
        if (v == null || TextUtils.isEmpty(filePath)) {
            return false;
        }
        try {
            Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas();
            canvas.setBitmap(bitmap);
            v.draw(canvas);
            FileOutputStream fos = new FileOutputStream(filePath);
            result = bitmap.compress(CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
            return result;
        }
    }

    private static Bitmap loadBitmapInSampleSize(byte[] bs, BitmapFactory.Options opt) {
        Bitmap bitmap = null;
        while (bitmap == null) {
            try {
                bitmap = BitmapFactory.decodeByteArray(bs, 0,
                        bs.length, opt);
                if (bitmap == null)// 防止非rgb格式的图片 如cmyk则放弃
                    break;
            } catch (OutOfMemoryError e) {
                // TODO: handle exception
//                LogUtils.e("getHttpBitmap2",
//                        "while OutOfMemoryError....");
                opt.inSampleSize++;
            }
        }

//        LogUtils.i("loadBitmapInSampleSize", String.format(
//                "inSampleSize=%d,imageUrl=%s bytes",
//                opt.inSampleSize));
        return bitmap;
    }

    private static Bitmap loadBitmapInSampleSize(String filePath, BitmapFactory.Options opt) {
        Bitmap bitmap = null;
        while (bitmap == null) {
            try {
                bitmap = BitmapFactory.decodeFile(filePath, opt);
                if (bitmap == null)// 防止非rgb格式的图片 如cmyk则放弃
                    break;
            } catch (OutOfMemoryError e) {
//                LogUtils.e("loadBitmapInSampleSize",
//                        "while OutOfMemoryError....");
                opt.inSampleSize++;
            }
        }

//        LogUtils.i("getHttpBitmap2", String.format(
//                "inSampleSize=%d,imageUrl=%s",
//                opt.inSampleSize, filePath));
        return bitmap;
    }


    public static Bitmap getLoadImage3(Context mContext, Uri uri,
                                       int imageViewWidth, int imageViewHeight, BitmapInfo bmpInfo)
            throws IOException {
        if (bmpInfo == null)
            return null;
        File m_bitmapfile = null;
        Bitmap m_bitmap = null;
        String filePath;
        String path = InitUtils.getPhotoPath();
        if (uri.getPath().startsWith(path)){
            filePath = path + "/" + EXTRA_TEMP_PHOTO_NAME;
        } else if (uri.getPath().startsWith(
                SDCARD_ROOT_PATH + SAVE_PATH_IN_SDCARD)){
            filePath = uri.getPath();
        } else {
            m_bitmapfile = convertImageUriToFile(uri, mContext);
            if (m_bitmapfile != null){
                filePath = m_bitmapfile.getAbsolutePath();
            } else {
                filePath = uri.getPath();
            }
        }

        // BitmapInfo bmpInfo=new BitmapInfo();
        bmpInfo.bNewSave = false;
        bmpInfo.path = filePath;
        BitmapFactory.Options localOptions = new BitmapFactory.Options();
        try {
            localOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(filePath, localOptions);
            if ((localOptions.outWidth > 0) && (localOptions.outWidth > 0)) {
                BitmapFactory.Options opt = new BitmapFactory.Options();

                if (imageViewWidth > 0 && imageViewHeight > 0) {
                    opt.inSampleSize = computeImageSampleSize(new ImageSize(
                                    localOptions.outWidth, localOptions.outHeight),
                            new ImageSize(imageViewWidth, imageViewHeight),
                            ViewScaleType.FIT_INSIDE, false);
                } else {
                    opt.inSampleSize = 1;
                }/*else{
                    if (localOptions.outWidth >= 2400
							|| localOptions.outHeight >= 2400)
						opt.inSampleSize = 4;
					else if (localOptions.outWidth <= 1200
							|| localOptions.outHeight <= 1200)
						opt.inSampleSize = 1;
					else
						opt.inSampleSize = 2;
				}*/

                if (opt.inSampleSize != 1)
                    // bmpInfo.bNewSave=true;
                    bmpInfo.bNewSave = false;
                opt.inSampleSize = fixBitmapInSampleSize(localOptions.outWidth, localOptions.outHeight, opt.inSampleSize);
//				m_bitmap = BitmapFactory.decodeFile(filePath, opt);
                m_bitmap = loadBitmapInSampleSize(filePath, opt);

                int angle = getExifOrientation(filePath);
                if (angle != 0) {
                    // bmpInfo.bNewSave=true;
                    bmpInfo.bNewSave = false;
                    if (m_bitmap.getConfig() == Bitmap.Config.ARGB_8888){
                        /*if (angle == 90
                                && InitUtils.getCurrentOsVersionCode() > 10)// 用native方法可以减少一半的内存开销
                        {
                            bmpInfo.bmp = JNIHelper.rotateBitmapCcw90(m_bitmap);
                            return bmpInfo.bmp;
                        } else */{
                            Matrix matrix = new Matrix();
                            matrix.postRotate(angle);
                            Bitmap photoViewBitmap = Bitmap.createBitmap(
                                    m_bitmap, 0, 0, m_bitmap.getWidth(),
                                    m_bitmap.getHeight(), matrix, true);
                            m_bitmap.recycle();
                            bmpInfo.bmp = photoViewBitmap;
                            bmpInfo.width=photoViewBitmap.getWidth();
                            bmpInfo.height=photoViewBitmap.getHeight();
                            return photoViewBitmap;
                        }
                    } else {
                        Matrix matrix = new Matrix();
                        matrix.postRotate(angle);
                        Bitmap photoViewBitmap = Bitmap.createBitmap(m_bitmap,
                                0, 0, m_bitmap.getWidth(),
                                m_bitmap.getHeight(), matrix, true);
                        m_bitmap.recycle();
                        bmpInfo.bmp = photoViewBitmap;
                        bmpInfo.width=photoViewBitmap.getWidth();
                        bmpInfo.height=photoViewBitmap.getHeight();
                        return photoViewBitmap;
                    }
                }
                bmpInfo.width=m_bitmap.getWidth();
                bmpInfo.height=m_bitmap.getHeight();
                return m_bitmap;
            }
        } catch (OutOfMemoryError e) {
            // return loadSmallLevelImage(localOptions, filePath);
            if (m_bitmap != null && !m_bitmap.isRecycled())
                m_bitmap.recycle();
            Log.i("getLoadImage2", "图片过大 内存溢出");
            return null;
        }

        return m_bitmap;
    }

    //TODO:底层JNI调用压缩图片
//    public static Bitmap getLoadImage4(Context mContext, Uri uri,
//         int imageViewWidth, int imageViewHeight, BitmapInfo bmpInfo)
//            throws IOException {
//        if (bmpInfo == null)
//            return null;
//        File m_bitmapfile = null;
//        Bitmap m_bitmap = null;
//        String filePath;
//        String path = InitUtils.getPhotoPath();
//        if (uri.getPath().startsWith(path)){
//            filePath = path + "/" + EXTRA_TEMP_PHOTO_NAME;
//        } else if (uri.getPath().startsWith(
//                SDCARD_ROOT_PATH + SAVE_PATH_IN_SDCARD)){
//            filePath = uri.getPath();
//        } else {
//            m_bitmapfile = convertImageUriToFile(uri, mContext);
//            if (m_bitmapfile != null) {
//                filePath = m_bitmapfile.getAbsolutePath();
//            } else {
//                filePath = uri.getPath();
//            }
//        }
//        bmpInfo.bNewSave = false;
//        bmpInfo.path = filePath;
//        String currentPath = SDCARD_ROOT_PATH + SAVE_PATH_IN_SDCARD;
//        m_bitmap = NativeUtil.compressBitmap(NativeUtil.getBitmapFromFile(filePath, bmpInfo), currentPath + System.currentTimeMillis()+ ".jpg");
//        return m_bitmap;
//    }




    /**
     * 根据视频在手机中的地址路径取得指定的视频缩略图\
     * @param cr
     * @return 返回bitmap类型数据
     */
    public static Bitmap getVideoThumbnail(ContentResolver cr, Uri uri) {
        Bitmap bitmap = null;

        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inDither = false;

        options.inPreferredConfig = Bitmap.Config.ARGB_8888;

        Cursor cursor = cr.query(uri,new String[] { MediaStore.Video.Media._ID }, null, null, null);

        if (cursor == null || cursor.getCount() == 0) {

            return null;
        }
        cursor.moveToFirst();
        String videoId = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media._ID)); //image id in image table.s
        if (videoId == null) {
            return null;
        }
        cursor.close();
        long videoIdLong = Long.parseLong(videoId);
        bitmap = MediaStore.Video.Thumbnails.getThumbnail(cr, videoIdLong, MediaStore.Images.Thumbnails.MICRO_KIND, options);
        return bitmap;
    }

    /**
     *  修改当前IamgView图片的透明度
     * @param imageview     View对象
     * @param brightness     透明度
     */
    public static void changeLight(ImageView imageview, int brightness) {
        ColorMatrix matrix = new ColorMatrix();
        matrix.set(new float[]{1, 0, 0, 0, brightness, 0, 1, 0, 0,
                brightness, 0, 0, 1, 0, brightness, 0, 0, 0, 1, 0});
        imageview.setColorFilter(new ColorMatrixColorFilter(matrix));
    }
}
