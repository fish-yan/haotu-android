package com.dmeyc.dmestoreyfm.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.dmeyc.dmestoreyfm.R;

@SuppressLint("AppCompatCustomView")
public class MyImageView extends ImageView {
    //图片的宽高比
    private float ratio = 60;

    private boolean imgType;

    /**
     * 圆角的半径
     */
    private int mRadius;
    /**
     * 3x3 矩阵，主要用于缩小放大
     */
    private Matrix mMatrix;

    /**
     * 绘图的Paint
     */
    private Paint mBitmapPaint;

    /**
     * 圆角的大小
     */
    private int mBorderRadius;

    /**
     * 渲染图像，使用图像为绘制图形着色
     */
    private BitmapShader mBitmapShader;

    private RectF mRoundRect;

    private int widthSize;

    public MyImageView(Context context) {
        this(context, null);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyImageViewRatio);
        ratio = ta.getFloat(R.styleable.MyImageViewRatio_ratio, 0);
        mBorderRadius = ta.getInteger(R.styleable.MyImageViewRatio_angle, 0);
        imgType = ta.getBoolean(R.styleable.MyImageViewRatio_isCircle, false);
        ta.recycle();
        mMatrix = new Matrix();
        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        widthSize = MeasureSpec.getSize(widthMeasureSpec);

        /**
         * 如果类型是圆形，则强制改变view的宽高一致，以小值为准
         */
        if (imgType) {
            widthSize = Math.min(getMeasuredWidth(), getMeasuredHeight());
            mRadius = widthSize / 2;
            setMeasuredDimension(widthSize, widthSize);
        } else {

            if (ratio != 0) {
                // 告诉系统我要申请这么多的宽高
                setMeasuredDimension(widthSize, (int) (widthSize / ratio));
            }

        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e("onSizeChanged=", "onSizeChanged");
        // 圆角图片的范围
        if (!imgType)
            mRoundRect = new RectF(0, 0, w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        setUpShader();

        if (imgType) {
            canvas.drawCircle(mRadius, mRadius, mRadius, mBitmapPaint);
        } else {
            canvas.drawRoundRect(mRoundRect, mBorderRadius, mBorderRadius, mBitmapPaint);
        }

    }

    /**
     * 初始化BitmapShader
     */
    private void setUpShader() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }

        Bitmap bmp = drawableToBitamp(drawable);
        // 将bmp作为着色器，就是在指定区域内绘制bmp
        mBitmapShader = new BitmapShader(bmp, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        float scale = 1.0f;
        if (imgType) {
            // 拿到bitmap宽或高的小值
            int bSize = Math.min(bmp.getWidth(), bmp.getHeight());
            scale = widthSize * 1.0f / bSize;

        } else {
            if (!(bmp.getWidth() == getWidth() && bmp.getHeight() == getHeight())) {
                // 如果图片的宽或者高与view的宽高不匹配，计算出需要缩放的比例；缩放后的图片的宽高，一定要大于我们view的宽高；所以我们这里取大值；
                scale = Math.max(getWidth() * 1.0f / bmp.getWidth(),
                        getHeight() * 1.0f / bmp.getHeight());
            }

        }
        // shader的变换矩阵，我们这里主要用于放大或者缩小
        mMatrix.setScale(scale, scale);
        // 设置变换矩阵
        mBitmapShader.setLocalMatrix(mMatrix);
        // 设置shader
        mBitmapPaint.setShader(mBitmapShader);
    }

    /**
     * drawable转bitmap
     *
     * @param drawable
     * @return
     */
    private Bitmap drawableToBitamp(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        }
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();

        try {
            Bitmap bitmap = Bitmap.createBitmap(w, h, drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, w, h);
            drawable.draw(canvas);

            return bitmap;
        } catch (OutOfMemoryError outOfMemoryError) {
            System.gc();
            return BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        }
    }

}
