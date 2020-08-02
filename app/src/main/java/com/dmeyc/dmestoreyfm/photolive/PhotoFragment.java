package com.dmeyc.dmestoreyfm.photolive;


import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.event.MyEvent;
import com.dmeyc.dmestoreyfm.photolive.adapter.LivingSelectorAdapter;
import com.dmeyc.dmestoreyfm.photolive.mtp.MTPService;
import com.dmeyc.dmestoreyfm.photolive.mtp.PicInfo;
import com.dmeyc.dmestoreyfm.photolive.mtp.UsbReceiver;
import com.dmeyc.dmestoreyfm.photolive.utils.BitmapUtil;
import com.dmeyc.dmestoreyfm.utils.LoadingUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoFragment extends Fragment implements MTPService.MTPFile {
    UsbReceiver mService;

    private TextView tv_state;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.photo_live, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mService = new MTPService(this);
        tv_state = view.findViewById(R.id.tv_state);
        tv_state.setText("初始化中。。。。");

    }

    List<PicInfo> mList = new ArrayList<>();


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mService.close();
    }


    boolean isFirstIn = false;

    @Override
    public void onAllFile(List<PicInfo> list) {
        tv_state.setText("初始完成。。。。");
    }

    @Override
    public void onFileAdded(List<PicInfo> list) {
        if (isFirstIn) {
            EventBus.getDefault().post(new MyEvent.LivingPath1(list));

//            for (int i = 0; i < list.size(); i++) {
//                EventBus.getDefault().post(new MyEvent.LivingPath(filePaht));
                // 上传
//                Bitmap compressBitmap = BitmapUtil.getCompressBitmap(getContext(), importfile(list.get(i).getObjectHandler()));
//                if (compressBitmap != null) {
//                    String filePaht = BitmapUtil.saveBitmap(compressBitmap, true);
//                    Log.e("PhotoFragment", "filePath>>>>>>> " + filePaht);
//                    EventBus.getDefault().post(new MyEvent.LivingPath(filePaht));
//                }

//            }
        }
        isFirstIn = true;
    }



    @Override
    public void onFileDecrease(List<PicInfo> list) {
        mList.clear();
        if (list.size() != 1) {
            for (int i = 0; i < list.size(); i++) {
                mList.add(0, list.get(i));
            }
        } else {
            for (Iterator<PicInfo> it = mList.iterator(); it.hasNext(); ) {
                PicInfo info = it.next();
                if (info.getObjectHandler() == list.get(0).getObjectHandler()) {
                    it.remove();
                }
            }
        }
    }


}
