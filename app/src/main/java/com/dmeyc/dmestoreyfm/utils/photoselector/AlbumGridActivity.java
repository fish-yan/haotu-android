package com.dmeyc.dmestoreyfm.utils.photoselector;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import java.io.File;
import java.util.ArrayList;


public class AlbumGridActivity extends BaseActivity {

    private GridView mGridView;
    private AlbumGridAdapter mAdapter;
    private AlbumVO mAlbumVO = null;

    private LinearLayout mSelectLayout;


    private HorizontalScrollView mSHImageWall;

    private Button mFinishButton;
    private RadioButton rb_button;
    private ArrayList<String> mSelects;
    private int mMaxCount;
    private boolean isChecked = false;
    private TextView tv_title;


    @SuppressWarnings("unchecked")
    public void initUI() {
        mSHImageWall = (HorizontalScrollView) this.findViewById(R.id.hsv);
        mFinishButton = (Button) this.findViewById(R.id.finishBtn);
        rb_button = (RadioButton) this.findViewById(R.id.rb_button);
        mSelectLayout = (LinearLayout) this.findViewById(R.id.selectLayout);
        tv_title = this.findViewById(R.id.tv_title);
        mAlbumVO = (AlbumVO) this.getIntent().getSerializableExtra(AlbumListActivity.INTENT_TAG_HAS_ALBUM);
        mSelects = (ArrayList<String>) this.getIntent().getSerializableExtra(AlbumListActivity.INTENT_TAG_HAS_SELECTED);
        mMaxCount = getIntent().getIntExtra(AlbumListActivity.INTENT_TAG_MAX_COUNT, 4);

        mGridView = (GridView) findViewById(R.id.gridView);

        if (mAlbumVO != null) {
            setTitle(mAlbumVO.albumName);
            mAdapter = new AlbumGridAdapter(this, mAlbumVO.albumIamges, mSelects, mGridView);
            mGridView.setAdapter(mAdapter);
        }

        mGridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String path = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(path)) {
                    if (mSelects.contains(path)) { //取消选中状态
                        mSelects.remove(path);
                    } else {

                        if (mSelects.size() < mMaxCount) {
                            mSelects.add(path);
                        } else {
                           ToastUtil.show("最多能选取" + mMaxCount + "张照片");
                            return;
                        }

                    }
                }
                addSelectLayout(path);
                updateGridViewWhenIdle();
            }
        });

        rb_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isChecked) {
                    rb_button.setChecked(true);
                    rb_button.setTextColor(Color.parseColor("#000000"));
                    isCheckOriginalImage = "1";
                } else {
                    rb_button.setChecked(false);
                    rb_button.setTextColor(Color.parseColor("#CBCBCB"));
                    isCheckOriginalImage = "0";
                }
                isChecked = !isChecked;
            }
        });

        int size = 0;
        if (mSelects != null) {
            size = mSelects.size();
        }

        for (int i = 0; i < size; i++) {
            String path = mSelects.get(i);
            if (TextUtils.isEmpty(path))
                continue;
            addSelectLayout(path);
        }

//		mGridView.post(new Runnable() {
//			@Override
//			public void run() {
//				mGridView.setSelection(mAlbumVO.albumIamges.size());
//			}
//		});
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
        _initFinishButton();
    }

    protected void addSelectLayout(final String path) {
        if (mSelects.contains(path)) {

            RelativeLayout layout = (RelativeLayout) this.getLayoutInflater().inflate(R.layout.item_photo_thumb_view, null);
            mSelectLayout.addView(layout);
            layout.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    mSelects.remove(path);
                    removeSelectLayout(path);
                }
            });
            LayoutParams lp = (LayoutParams) layout.getLayoutParams();
            int margin = DisplayUtils.dip2px(this, 5);
            lp.setMargins(margin, margin, margin, margin);
            layout.setLayoutParams(lp);
            layout.setTag(path);

            ImageView iv = (ImageView) layout.findViewById(R.id.photoview);
            layout.findViewById(R.id.selectview).setVisibility(View.GONE);
            RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) iv.getLayoutParams();
            rlp.width = rlp.height = DisplayUtils.dip2px(this, 60);
            iv.setLayoutParams(rlp);
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .error(R.drawable.image_default)
                    .placeholder(R.drawable.image_default);
            if (path.startsWith("http") || path.startsWith("https")) {
                Glide.with(this).load(path).apply(options).into(iv);
            } else {
                Glide.with(this).load(new File(path)).apply(options).into(iv);
            }
        } else {
            removeSelectLayout(path);
        }
        _initFinishButton();
        mSHImageWall.post(new Runnable() {
            @Override
            public void run() {
                int w = mSelectLayout.getMeasuredWidth() - mSHImageWall.getMeasuredWidth();
                mSHImageWall.scrollBy(w, 0);
            }
        });
    }

    protected void removeSelectLayout(String path) {
        int count = mSelectLayout.getChildCount();
        for (int i = 0; i < count; i++) {
            RelativeLayout layout = (RelativeLayout) mSelectLayout.getChildAt(i);
            if (layout.getTag().equals(path)) {
                mSelectLayout.removeViewAt(i);
                break;
            }
        }
        updateGridViewWhenIdle();
        _initFinishButton();
    }

    private void _initFinishButton() {
        int size = 0;
        if (mSelects != null) {
            size = mSelects.size();
        }
        mFinishButton.setText(String.format("完成(%d/%d)", size, mMaxCount));
        mFinishButton.setEnabled(size > 0 ? true : false);
    }

    protected void updateGridViewWhenIdle() {
        mAdapter.notifyDataSetChanged();
    }

    public void onLeftNaviBtnClick(View v) {
        Intent intent = new Intent();
        intent.putExtra(AlbumListActivity.INTENT_TAG_HAS_SELECTED, mSelects);
        setResult(0, intent);
        this.finish();
    }

    @Override
    public void onBackPressed() {
        onLeftNaviBtnClick(null);
    }
    private String isCheckOriginalImage = "0";
    public void onFinishSelectClick(View v) {
        Intent intent = new Intent(AlbumBroadcastReceiver.BROADCAST_TAG_ALBUM_SELECTED);
        intent.putExtra(AlbumListActivity.INTENT_TAG_HAS_SELECTED, mSelects);
        if (rb_button.isSelected()) {   //1.代表是选择原图上传
            intent.putExtra(AlbumListActivity.INTENT_TAG_IS_CHECK_ORIGINAL_IMAGE, isCheckOriginalImage);
        }else {                         //0.代表是默认压缩图片
            intent.putExtra(AlbumListActivity.INTENT_TAG_IS_CHECK_ORIGINAL_IMAGE,isCheckOriginalImage);
        }
        intent.putExtra("", mSelects);
        sendBroadcast(intent);
        this.finish();
    }

//    @Override
//    public void initWidget(Bundle savedInstanceState) {
//        setContentView(R.layout.activity_photo_grid);
//        initUI();
//        getMyGobackView().setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onLeftNaviBtnClick(v);
//            }
//        });
//    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_photo_grid;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        initUI();
    }
}
