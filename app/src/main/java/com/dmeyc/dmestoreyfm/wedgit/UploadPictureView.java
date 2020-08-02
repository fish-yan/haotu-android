package com.dmeyc.dmestoreyfm.wedgit;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;

/**
 * create by cxg on 2019/11/30
 */
public class UploadPictureView extends ConstraintLayout implements View.OnClickListener {
    private ImageView mIvBackground;
    private ImageView mIvAddIcon;
    private TextView mTvDelete;
    private TextView mTvTip;
    private Context mContext;
    private String mImgPath;

    private AddClickListerner mListener;

    public UploadPictureView(Context context) {
        this(context, null);
    }

    public UploadPictureView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UploadPictureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        View layout = LayoutInflater.from(context).inflate(R.layout.view_upload_image, this, true);
        mIvBackground = layout.findViewById(R.id.iv_upv_bg);
        mIvAddIcon = layout.findViewById(R.id.iv_upv_add_icon);
        mTvTip = layout.findViewById(R.id.tv_upv_tip);
        mTvDelete = layout.findViewById(R.id.tv_upv_delete);

        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ViewUploadPicture);
            String tips = ta.getString(R.styleable.ViewUploadPicture_vup_tips);
            if (tips != null) {
                mTvTip.setText(tips);
            }
            ta.recycle();
        }

//        addView(layout);
        mTvDelete.setOnClickListener(this);
        mIvAddIcon.setOnClickListener(this);
    }

    public void loadImage(String imgPath) {
        if (!TextUtils.isEmpty(imgPath)) {
            mIvAddIcon.setVisibility(View.GONE);
            mTvTip.setVisibility(View.GONE);
            mTvDelete.setVisibility(View.VISIBLE);
            GlideUtil.loadLocalImage(mContext, imgPath, mIvBackground);
            mImgPath = imgPath;
        }
    }

    public void setListener(AddClickListerner listener) {
        this.mListener = listener;
    }

    public String getImgPath() {
        return mImgPath;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_upv_delete:
                mTvDelete.setVisibility(View.GONE);
                mIvAddIcon.setVisibility(View.VISIBLE);
                mTvTip.setVisibility(View.VISIBLE);
                mIvBackground.setImageBitmap(null);
                mImgPath = null;
                break;
            case R.id.iv_upv_add_icon:
                if (mListener != null) {
                    mListener.onAddClick();
                }
                break;
            default:
        }
    }

    public interface AddClickListerner {
        void onAddClick();
    }
}
