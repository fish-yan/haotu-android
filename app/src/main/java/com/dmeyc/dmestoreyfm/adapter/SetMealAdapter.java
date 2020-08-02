package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.SetMealBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.ui.ShowPicActivity;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class SetMealAdapter extends BaseRvAdapter<SetMealBean> {


private ImageView item_ivs,item_iv_bgs,iv_click;
private TextView item_tv_name1s;
    private List<SetMealBean> setMealBeans;
    private int clickPos=-1;
    private Context ctx;
    public SetMealAdapter(Context ctx,List<SetMealBean> setMealBeans){
        super(ctx,R.layout.adapter_setmeal,setMealBeans);
        this.setMealBeans=setMealBeans;
        this.ctx=ctx;
    }

    @Override
    protected void convert(ViewHolder holder, SetMealBean setMealBean, final int position) {
        iv_click=holder.getView(R.id.iv_click);
        iv_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, ShowPicActivity.class);
                intent.putExtra(Constant.Config.POSITION,position);
                intent.putStringArrayListExtra("pics",null);
                intent.putExtra("pic",setMealBeans.get(position).getImage());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(intent);
            }
        });
        item_ivs= holder.getView(R.id.item_ivs);
        item_tv_name1s=holder.getView(R.id.item_tv_name1s);
        item_iv_bgs=holder.getView(R.id.item_iv_bgs);

        if(position==clickPos||(clickPos==-1&&0==position)){
            item_iv_bgs.setVisibility(View.VISIBLE);
        }else {
            item_iv_bgs.setVisibility(View.INVISIBLE);
        }

        item_tv_name1s.setText(setMealBean.getName());
        GlideUtil.loadImage(ctx,setMealBean.getImage(),item_ivs);
        item_ivs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(ctx,"wwww"+setMealBeans.get(position).getDefaultChose(),Toast.LENGTH_LONG).show();
                mealLisenter.onMeal(setMealBeans.get(position).getDefaultChose());
                clickPos=position;
                notifyDataSetChanged();
            }
        });
    }

    public OnMealSetLisenter mealLisenter;

   public void setMealLisenter(OnMealSetLisenter mealLisenter){
        this.mealLisenter=mealLisenter;
   }

    public interface OnMealSetLisenter{

           void onMeal(String meal);
    }

}
