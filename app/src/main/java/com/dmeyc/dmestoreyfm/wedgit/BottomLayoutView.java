package com.dmeyc.dmestoreyfm.wedgit;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.config.ReleaseConfig;
import com.dmeyc.dmestoreyfm.event.MyEvent;
import com.dmeyc.dmestoreyfm.utils.CommonUtil;
import com.dmeyc.dmestoreyfm.wedgit.dialog.ReleaseSelectDialog;

import org.greenrobot.eventbus.EventBus;

/**
 * create by cxg on 2019/12/4
 */
public class BottomLayoutView extends ConstraintLayout implements View.OnClickListener, ReleaseSelectDialog.ReleaseSelectorListener {

    /**
     * 对应viewpager index
     */
    public static final int SELECT_INDEX_HOME = 0;
    public static final int SELECT_INDEX_RELEASE = -1;
    public static final int SELECT_INDEX_MINE = 1;
    private OnItemCheckListener mListener;
    private Context mContext;

    private TextView mTvHome;
    private View mViewHome;
    private ImageView mIvHomeBottom;

    private TextView mTvMine;
    private View mViewMine;
    private ImageView mIvMineBottom;

    private ConstraintLayout mClBottom;

    private View mViewRelease;
    private ImageView mIvBottomRelease;

    private int mCurrentIndex = SELECT_INDEX_HOME;


    // 记录前一个选中状态
    private int mPreCheckIndex = SELECT_INDEX_HOME;

    //
    private boolean isHomeVideo = true;

    public BottomLayoutView(Context context) {
        this(context, null);
    }

    public BottomLayoutView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public BottomLayoutView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.view_bottom_layout, this, true);
        mClBottom = findViewById(R.id.cl_bottom);
        mTvHome = findViewById(R.id.tv_bottom_home);
        mViewHome = findViewById(R.id.view_home);
        mIvHomeBottom = findViewById(R.id.iv_bottom_home_line);
        mTvMine = findViewById(R.id.tv_bottom_mine);
        mViewMine = findViewById(R.id.view_mine);
        mIvMineBottom = findViewById(R.id.iv_bottom_mine_line);
        mViewRelease = findViewById(R.id.view_release);
        mIvBottomRelease = findViewById(R.id.iv_bottom_release);
        mViewHome.setOnClickListener(this);
        mViewMine.setOnClickListener(this);
        mViewRelease.setOnClickListener(this);
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        boolean isClick = false;
        switch (v.getId()) {
            case R.id.view_home:
//                if (mCurrentIndex == SELECT_INDEX_HOME) {
//                    return;
//                }
                mCurrentIndex = SELECT_INDEX_HOME;
                checkHome();
                isClick = true;

                break;
            case R.id.view_release:
                if (!CommonUtil.isLogin(mContext)) {
                    return;
                }

                // 发布不需要重置状态
                mIvHomeBottom.setVisibility(View.GONE);
                mIvMineBottom.setVisibility(View.GONE);

                new ReleaseSelectDialog.Build(mContext).setListener(this).create().show();
                return;
            case R.id.view_mine:
                if (!CommonUtil.isLogin(mContext)) {
                    return;
                }
//                if (mCurrentIndex == SELECT_INDEX_MINE) {
//                    return;
//                }
                EventBus.getDefault().post(new MyEvent.CloseHomePlay());
                mCurrentIndex = SELECT_INDEX_MINE;
                checkMine();
                isClick = true;
                break;

            default:
        }

        mPreCheckIndex = mCurrentIndex;
        if (isClick && mListener != null) {
            mListener.onItemClick(mCurrentIndex);
        }
    }

    private void checkHome() {
        resetState();

        mIvHomeBottom.setVisibility(View.VISIBLE);
        int nowColor = isHomeVideo ? getResources().getColor(R.color.white) : getResources().getColor(R.color.black);

        if (isHomeVideo) {
            mClBottom.setBackgroundResource(R.drawable.shape_home_top_bg);
        } else {
            mClBottom.setBackgroundColor(getResources().getColor(R.color.white));
        }
        mTvHome.setTextColor(nowColor);
        mTvMine.setTextColor(nowColor);
        mIvHomeBottom.setBackgroundColor(nowColor);
    }

    private void checkMine() {
        resetState();

        mIvMineBottom.setVisibility(View.VISIBLE);
//        mIvMineBottom.setBackgroundColor(getResources().getColor(R.color.black));
        mClBottom.setBackgroundColor(getResources().getColor(R.color.white));
        mTvHome.setTextColor(getResources().getColor(R.color.black));
        mIvHomeBottom.setBackgroundColor(getResources().getColor(R.color.black));
        mTvMine.setTextColor(getResources().getColor(R.color.black));
    }

    private void resetState() {
        mIvHomeBottom.setVisibility(View.GONE);
        mIvMineBottom.setVisibility(View.GONE);

        mClBottom.setBackgroundColor(getResources().getColor(R.color.transparent));
        mTvHome.setTextColor(getResources().getColor(R.color.white));
        mIvHomeBottom.setBackgroundColor(getResources().getColor(R.color.white));
        mTvMine.setTextColor(getResources().getColor(R.color.white));
//        mIvMineBottom.setBackgroundColor(getResources().getColor(R.color.white));
    }

    public void setListener(OnItemCheckListener listener) {
        this.mListener = listener;
    }


    /* --------------------------releaseDialog回调监听--------------------------*/
    @Override
    public void onShow() {
        mIvBottomRelease.setImageResource(R.drawable.icon_home_release_selector);
    }

    @Override
    public void onHide() {
        mIvBottomRelease.setImageResource(R.drawable.icon_home_release_normal);

        switch (mPreCheckIndex) {
            case SELECT_INDEX_HOME:
                mIvHomeBottom.setVisibility(View.VISIBLE);
                break;
            case SELECT_INDEX_MINE:
                mIvMineBottom.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onItemClick(ReleaseConfig.Type type) {
        ReleaseConfig.startActivityByType(mContext, type);
    }
    /* --------------------releaseDialog回调监听 end------------------------------*/


    public void setIsHomeVideo(boolean isVideo) {
        isHomeVideo = isVideo;
        checkHome();
    }


    public interface OnItemCheckListener {
        void onItemClick(int position);
    }
}
