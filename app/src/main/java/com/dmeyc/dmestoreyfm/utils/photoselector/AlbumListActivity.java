
package com.dmeyc.dmestoreyfm.utils.photoselector;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore.Images.Media;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 相册列表界面
 * 
 * @author Danel
 */
public class AlbumListActivity extends BaseActivity implements OnItemClickListener, AlbumBroadcastReceiver.AlbumSelectedListener {
    public static final String INTENT_TAG_HAS_SELECTED = "select_paths";
    public static final String INTENT_TAG_HAS_ALBUM = "select_album";
    public static final String INTENT_TAG_MAX_COUNT = "select_max_count";
    public static final String INTENT_TAG_IS_CHECK_ORIGINAL_IMAGE= "is_check_original_image";

    private ListView mListView;
    private String LogTag="AlbumListActivity";
    
    private List<AlbumVO> mAlbumVOs = new ArrayList<AlbumVO>();
    
    private AlbumListAdapter mAdapter;

    private ArrayList<String> mHasSelectPaths;
    
    private AlbumBroadcastReceiver mReceiver;

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 0:
                    ToastUtil.show("Image doesn't exist!");
                    break;
                case 1:
//                    baseHelper().hideProgressBar();
                    mAlbumVOs.clear();
                    mAlbumVOs.addAll((Collection<? extends AlbumVO>) msg.obj);
                    mAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }

    };

    private TextView tv_title;
    public void initUI() {
        mReceiver = new AlbumBroadcastReceiver(this, this);
        mReceiver.registerAlbumReceiver();
        mHasSelectPaths = getIntent().getStringArrayListExtra(INTENT_TAG_HAS_SELECTED);        
        if (null == mHasSelectPaths) {
            mHasSelectPaths = new ArrayList<String>();
        }
        tv_title = (TextView) findViewById(R.id.tv_title);
        mListView = (ListView) findViewById(R.id.listView);

        mAdapter = new AlbumListAdapter(this, mAlbumVOs, mListView);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        tv_title.setText("照片选择");
       // this.setPullListView(mListView);

    }
    private void showThumbnails() {
        String[] projection = {
                Media._ID, Media.DATA
        };
        Cursor cursor = getContentResolver().query(Media.EXTERNAL_CONTENT_URI, projection, null,null, Media.DATE_ADDED + " DESC");
        if (cursor != null) {
            List<AlbumVO> tempAlbumVOs = new ArrayList<AlbumVO>();
            if (cursor.moveToFirst()) {
                for (int i = 0; i < cursor.getCount(); i++){
                    cursor.moveToPosition(i);
                    String path = cursor.getString(cursor.getColumnIndex(Media.DATA));
                    int m = path.lastIndexOf("/");
                    if (m != -1) {
                        int n = path.substring(0, m - 1).lastIndexOf("/");
                        if (n != -1) {
                            String folder = path.substring(n + 1, m);
                            boolean isExist = false;
                            for (AlbumVO mAlbumVO : tempAlbumVOs) {
                                if(mAlbumVO.albumName.equals(folder)){
                                    mAlbumVO.albumIamges.add(path);
                                    isExist = true;
                                    break;
                                }
                            }
                            if(!isExist){
                                AlbumVO mAlbumVO = new AlbumVO(folder);
                                mAlbumVO.albumIamges.add(path);
                                tempAlbumVOs.add(mAlbumVO);
                            }
                        }

                    }

                }

            }
            cursor.close();
            Message message = new Message();
            message.what = 1;
            message.obj = tempAlbumVOs;
            mHandler.sendMessage(message);
        } else {
            mHandler.sendEmptyMessage(0);
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AlbumVO mAlbumVO = (AlbumVO) parent.getItemAtPosition(position);
        if(null == mAlbumVO){
           ToastUtil.show("无法选取该相册");
            return;
        }
        
        Intent intent = new Intent();
        intent.setClass(this,AlbumGridActivity.class);
        intent.putExtra(INTENT_TAG_HAS_ALBUM, mAlbumVO);
        intent.putExtra(INTENT_TAG_HAS_SELECTED,mHasSelectPaths);
        intent.putExtra(INTENT_TAG_MAX_COUNT, getIntent().getIntExtra(INTENT_TAG_MAX_COUNT, 4));
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if(reqCode == 0 && null != data ){
            ArrayList<String> mList = data.getStringArrayListExtra(INTENT_TAG_HAS_SELECTED);
            mHasSelectPaths.clear();
            mHasSelectPaths.addAll(mList);
        }
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mReceiver.unRegisterAlbumReceiver();
    }

    @Override
    public void onAlbumSelected(ArrayList<String> paths, String is_check_original_image) {
        this.finish();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_photo_folder_list;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        initUI();
        new Thread() {
            @Override
            public void run() {
                super.run();
                if(Build.VERSION.SDK_INT >= 23){
                    MPermissionUtils.requestPermissionsResult(AlbumListActivity.this, 1, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            new MPermissionUtils.OnPermissionListener(){
                                @Override
                                public void onPermissionGranted(){
                                    // 照片
                                    showThumbnails();
                                }
                                @Override
                                public void onPermissionDenied(){
                                    MPermissionUtils.showTipsDialog(AlbumListActivity.this, "当前应用缺少读取存储权限(录音,照片,视频等需要),该功能暂时无法使用。如若需要，请单击【确定】按钮前往设置中心进行权限授权。");
                                }
                            });
                }else{
                    // 照片
                    showThumbnails();
                }
            }

        }.start();
    }
}
