package com.dmeyc.dmestoreyfm.wedgit;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jockie on 2017/11/17
 * Email:jockie911@gmail.com
 */

public class LikeView extends RelativeLayout{

    @Bind(R.id.tv_count)
    TextView tvCount;
    @Bind(R.id.iv_like)
    ImageView ivLike;

    private int count;
    private boolean isSelected;
    private int default_img_resource = R.drawable.ic_praise_gray;
    private int selected_img_resource = R.drawable.ic_praise_orange;
    private OnClickListener listener;

    public LikeView(Context context) {
        super(context);
        init();
    }

    public LikeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        View view = View.inflate(getContext(), R.layout.like_view, this);
        ButterKnife.bind(view);

        tvCount.setText(String.valueOf(count));
        tvCount.setTextColor(getResources().getColor(isSelected ? R.color.indicator_selected_color : R.color.color_c5c5c5));
    }

    @OnClick({R.id.rel_root})
    public void onClick(){
        if(listener != null)
            listener.onClick();
    }

    /**
     * 初始化数据
     * @param isSelected
     * @param count
     */
    public void setStatus(boolean isSelected,int count){
        this.count = count;
        this.isSelected = isSelected;
        ivLike.setImageResource(isSelected ? selected_img_resource : default_img_resource);
        tvCount.setTextColor(getResources().getColor(isSelected ? R.color.indicator_selected_color : R.color.color_c5c5c5));
        tvCount.setText(String.valueOf(count));
    }

    /**
     * 点赞获取取消点赞之后重置状态
     */
    public void clickReSetStatus(){
        if (isSelected && count == 0){
            ToastUtil.show("error");
            return;
        }
        //TODO add animation
        if(isSelected){
            count -- ;
        }else{
            count ++;
        }
        isSelected = !isSelected;
        ivLike.setImageResource(isSelected ? selected_img_resource : default_img_resource);
        tvCount.setText(String.valueOf(count));
        tvCount.setTextColor(getResources().getColor(isSelected ? R.color.indicator_selected_color : R.color.color_c5c5c5));
    }

    public void setOnClickListener(OnClickListener listener){
        this.listener = listener;
    }

    public interface OnClickListener{
        void onClick();
    }
}
