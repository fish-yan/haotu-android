package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.BrandBean;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.HanyuPinyinHelper;
import com.makeramen.roundedimageview.RoundedImageView;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by jockie on 2018/1/9 shadyY reBuild on 2018/12/7
 * Email:jockie911@gmail.com
 */

public class BrandDesignAdapter extends BaseRvAdapter<BrandBean.DataBean.BrandDesignersBean> {

    /**
     * @param context
     * @param layoutId
     * @param datas    不能为null
     */
    public BrandDesignAdapter(Context context, int layoutId, List<BrandBean.DataBean.BrandDesignersBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, BrandBean.DataBean.BrandDesignersBean bean, int position) {
        TextView tvname = holder.getView(R.id.item_tv_name);
        tvname.setText(bean.getName());

        RoundedImageView view = holder.getView(R.id.item_avatar);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        GlideUtil.loadImage(mContext,bean.getLogo(),view);

        TextView tvTag = holder.getView(R.id.item_tv_tag);

        String firstSpellTag = HanyuPinyinHelper.getSingleFirstSpell(bean.getName());
        tvTag.setText(firstSpellTag);
        position -- ;
        if(position == 0){
            tvTag.setVisibility(View.VISIBLE);
        }else{
            tvTag.setVisibility(TextUtils.equals(getFirstSpellTag(position),getFirstSpellTag(position - 1 )) ? View.GONE : View.VISIBLE);
        }

        View vDivide = holder.getView(R.id.v_divide);
        if(position == mDatas.size() - 1){
            vDivide.setVisibility
                    (View.GONE);
        }else{
            vDivide.setVisibility(TextUtils.equals(getFirstSpellTag(position),getFirstSpellTag(position + 1)) ? View.VISIBLE : View.GONE);
        }
    }

    private String getFirstSpellTag(int position){
        return HanyuPinyinHelper.getSingleFirstSpell(mDatas.get(position).getName());
    }
}
