package com.dmeyc.dmestoreyfm.utils.photoselector;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.ArrayList;

/**
 * 相册选取广播
 * @author Danel
 *
 */
public class AlbumBroadcastReceiver extends BroadcastReceiver {
    public static final String BROADCAST_TAG_ALBUM_SELECTED = "com.entstudy.enjoystudy.base.activity.AlbumBroadcastReceiver";
    private AlbumSelectedListener mAlbumSelectedListener;
    private Context mContext;
    private String is_check_original_image;
    public AlbumBroadcastReceiver(Context context){
        this(context, null);
    }
    
    public AlbumBroadcastReceiver(Context context, AlbumSelectedListener albumSelectedListener){
        this.mContext = context;
        this.mAlbumSelectedListener = albumSelectedListener;
    }
    
    public void registerAlbumReceiver(){
        IntentFilter filter = new IntentFilter();
        filter.addAction(BROADCAST_TAG_ALBUM_SELECTED);
        mContext.registerReceiver(this, filter);
    }
    
    public void unRegisterAlbumReceiver(){
        mContext.unregisterReceiver(this);
    }
    
    public void setAlbumSelectedListener(AlbumSelectedListener albumSelectedListener){
        this.mAlbumSelectedListener = albumSelectedListener;
    }
    
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(BROADCAST_TAG_ALBUM_SELECTED.equals(action)){
            ArrayList<String> paths = intent.getStringArrayListExtra(AlbumListActivity.INTENT_TAG_HAS_SELECTED);
            is_check_original_image = intent.getStringExtra(AlbumListActivity.INTENT_TAG_IS_CHECK_ORIGINAL_IMAGE);
            if(null != mAlbumSelectedListener){
                mAlbumSelectedListener.onAlbumSelected(paths,is_check_original_image);
            }
            return;
        }
    }
    
    public interface AlbumSelectedListener{
        void onAlbumSelected(ArrayList<String> paths, String is_check_original_image);
    }
}
