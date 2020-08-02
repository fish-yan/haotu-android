package com.dmeyc.dmestoreyfm.photolive.adapter;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.photolive.mtp.PicInfo;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;

import java.io.File;

/**
 * create by cxg on 2019/12/29
 */
public class LivingSelectorAdapter extends BaseQuickAdapter<PicInfo, BaseViewHolder> {
    private Context mContext;

    public LivingSelectorAdapter(Context context) {
        super(R.layout.adapter_living_selector);
        mContext = context;
    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    @Override
    protected void convert(BaseViewHolder helper, final PicInfo item) {
        if (helper == null || item == null) {
            return;
        }
        ((CheckBox) helper.getView(R.id.cb_select)).setChecked(item.isCheck());
        GlideUtil.loadImage(mContext, new File(item.getmThumbnailPath()), (ImageView) helper.getView(R.id.iv_pic));
        ((CheckBox) helper.getView(R.id.cb_select)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setCheck(isChecked);
            }
        });
    }
}
