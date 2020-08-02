package com.dmeyc.dmestoreyfm.video.releasedynamic;

import android.content.Context;

import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;
import java.util.List;

public class ReleaseDynamicRelationAdapter extends CommonAdapter<RelationBean>{


    public ReleaseDynamicRelationAdapter(Context context, int layoutId, List<RelationBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, RelationBean item, int position){
         
    }
}
