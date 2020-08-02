package com.dmeyc.dmestoreyfm.adapter.product;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.BaseRvAdapter;
import com.dmeyc.dmestoreyfm.adapter.SetMealAdapter;
import com.dmeyc.dmestoreyfm.bean.AttrBean;
import com.dmeyc.dmestoreyfm.bean.SetMealBean;
import com.dmeyc.dmestoreyfm.bean.TailorBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.decoration.SpaceItemDecoration;
import com.dmeyc.dmestoreyfm.ui.ShowPicActivity;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.dmeyc.dmestoreyfm.wedgit.PriceView;
import com.dmeyc.dmestoreyfm.wedgit.flowview.AutoFlowLayout;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by jockie on 2018/5/24
 * Email:jockie911@gmail.com
 */

public class TailorAdapter extends BaseRvAdapter<TailorBean.DataBean.ParentCustomProductBean> implements SetMealAdapter.OnMealSetLisenter {
    public Map<Integer,Integer> selectedMap = new TreeMap();
public    List<TailorBean.DataBean.ParentCustomProductBean> data;
private AttrBean bean;
private Context ctx;
private TextView textView;
private double priceorg;
private int clickPos;
private int clickPos1;
private double chickPrice;
    private String chickPricest="";
private      SetMealAdapter sp;
private List<SetMealBean> setMealBeans;
    public TailorAdapter(Context context, int layoutId, List<TailorBean.DataBean.ParentCustomProductBean> datas, List<SetMealBean> setMealBeans,AttrBean bean, Context ctx, TextView tv_total_price, double price) {

        super(context, layoutId, datas);
        this.data=datas;
        this.bean=bean;
        this.ctx=ctx;
        this.textView=tv_total_price;
        this.priceorg=price;
        this.setMealBeans=setMealBeans;
      mealSplit=  setMealBeans.get(0).getDefaultChose().split("-");
    }
    @Override
    protected void convert(ViewHolder holder, TailorBean.DataBean.ParentCustomProductBean parentCustomProductBean, final int position) {
    }
    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;
    public static final int ITEM_TYPE_BOTTOM = 2;

    private int mHeaderCount=1;//头部View个数

    @Override
    public int getItemViewType(int position) {
        int dataItemCount = getContentItemCount();
 if(mHeaderCount!=0&&position==0){
            return ITEM_TYPE_HEADER;
        }else {
//内容View
            return ITEM_TYPE_CONTENT;
        }
    }
    public int getContentItemCount(){
        return data.size();
    }
    AutoFlowLayout sizeFlowLayout,colorFlowLayout;
    RecyclerView rv_setmeal;
    private String sizeName;
    private double itemprice=0;
    private boolean isfirst=true;

    private ArrayList<String>price=new ArrayList<>();
    @Override
    public int getItemCount() {
        return  getContentItemCount() + mHeaderCount;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if(holder instanceof HeaderViewHolder){
            sizeFlowLayout= holder.getView(R.id.sizeFlowLayout);
            colorFlowLayout=   holder.getView(R.id.colorFlowLayout);
            sizeFlowLayout.clearViews();
            colorFlowLayout.clearViews();
            rv_setmeal=holder.getView(R.id.rv_setmeal);
            if(!Util.objEmpty(bean.getData().getAttributeDetails())){
                List<AttrBean.DataBean.AttributeDetailsBean> lists = bean.getData().getAttributeDetails();
                for (final AttrBean.DataBean.AttributeDetailsBean list : lists) {
                    if(TextUtils.equals(list.getAttributeKey(),"size")){
                        for (String s : list.getChildrenAttributeName()) {
                            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_flowlayout_size, null);
                            TextView textView = inflate.findViewById(R.id.item_tv_flowlayout);
                             textView.setText(s);
                            sizeFlowLayout .addView(inflate);
                        }
                            sizeFlowLayout.setOnItemClickListener(new AutoFlowLayout.OnItemClickListener() {
                                @Override
                                public void onItemClick(int position, View view) {
                                    setCommonSizeStatus(position);
                                    sizeName = list.getChildrenAttributeName().get(position);
                                }
                            });
                    }
                    if(TextUtils.equals(list.getAttributeKey(),"color")){
                        for (String s : list.getChildrenAttributeName()) {
                            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_flowlayout_size, null);
                            TextView textView = inflate.findViewById(R.id.item_tv_flowlayout);
                            textView.setText(s);
                            colorFlowLayout.addView(inflate);
                        }
                        colorFlowLayout.setOnItemClickListener(new AutoFlowLayout.OnItemClickListener() {
                                @Override
                                public void onItemClick(int position, View view) {
                                    setCommonColorStatus(position);
                                    sizeName = list.getChildrenAttributeName().get(position);
                                }
                            });
                    }
                }
            }

            GridLayoutManager st=new GridLayoutManager(ctx,2);

            rv_setmeal.setHasFixedSize(true);
            rv_setmeal.setLayoutManager(st);
            if(sp==null){
                sp = new SetMealAdapter(ctx,setMealBeans);
                sp.setMealLisenter(this);
                rv_setmeal.addItemDecoration(new SpaceItemDecoration(false));
                rv_setmeal.setAdapter(sp);
            }

        }else {
            TailorBean.DataBean.ParentCustomProductBean  parentCustomProductBean=data.get(position-1);
            holder.setText(R.id.item_tv_name,parentCustomProductBean.getCustomName());
            RecyclerView view = holder.getView(R.id.item_rv_tailor);
            GridLayoutManager layoutManager = new GridLayoutManager(mContext,3){
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
//        layoutManager.set
//        view.setLayoutManager(new GridLayoutManager(mContext,2));
            List<TailorBean.DataBean.ParentCustomProductBean.ChildrenCustomProductBean>    childrenCustomProductBeans= parentCustomProductBean.getChildrenCustomProduct();
            view.setLayoutManager(layoutManager);
            view.setAdapter(new CommonAdapter<TailorBean.DataBean.ParentCustomProductBean.ChildrenCustomProductBean>(mContext,R.layout.item_goods_tailor_detail,childrenCustomProductBeans) {

                @Override
                protected void convert(ViewHolder holder, final TailorBean.DataBean.ParentCustomProductBean.ChildrenCustomProductBean bean, final int pos) {
                    holder.setText(R.id.item_tv_name1,bean.getName());
                    PriceView priceView = holder.getView(R.id.item_price);
                    priceView.setPrice(bean.getPrice());
                    GlideUtil.loadImage(mContext,bean.getImageList().get(0).getThumbnail(),(ImageView) holder.getView(R.id.item_iv));

//                    selectedMap.put(position,1);
                    if(bean.getDefaultNumber()==Integer.parseInt(mealSplit[position-1])){
                                            if(isfirst){
                                                price.add(bean.getPrice()+"");
                                            }
//                        chickPrice+= bean.getPrice();
                        selectedMap.put(position,pos);
                          setprice();
                           }else {
//                        selectedMap.remove(position);
                    }
                    holder.setOnClickListener(R.id.item_iv, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            isfirst=false;
                            price.set(position-1,bean.getPrice()+"");
//                            chickPrice=0;
//                            clickPos=position;
//                             clickPos1=pos;
                           mealSplit[position-1]=  bean.getDefaultNumber()+"";
                            selectedMap.remove(position);
//                                isfirst=false;
//                            if(selectedMap.containsKey(position)){
//                                if(pos == selectedMap.get(position)){
//                                    selectedMap.remove(position);
//                                }else{
//                                    selectedMap.put(position,pos);
//                                }
//                            }else{
//                                selectedMap.put(position,pos);
//                            }
                            for (Map.Entry<Integer, Integer> integerEntry : selectedMap.entrySet()) {
                                System.out.println(integerEntry.getKey() + " --  " + integerEntry.getValue());
                            }
                            notifyDataSetChanged();
                        }
                    });
                    ImageView itemIvBg = holder.getView(R.id.item_iv_bg);
                    ImageView iv_click = holder.getView(R.id.iv_click);
                    iv_click.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(ctx, ShowPicActivity.class);
                            intent.putExtra(Constant.Config.POSITION,position);
                            intent.putStringArrayListExtra("pics",null);
                            intent.putExtra("pic",bean.getImageList().get(0).getThumbnail());
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            ctx.startActivity(intent);
                        }
                    });
                    if(selectedMap.containsKey(position)){
                        Integer integer = selectedMap.get(position);
                        itemIvBg.setVisibility(integer == pos ? View.VISIBLE : View.INVISIBLE);

                    }else{
                        itemIvBg.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                int ii=viewType;
      /*  if (viewType == ITEM_TYPE_BOTTOM) {
            return new BottomViewHolder(context,mLayoutInflater.inflate(R.layout.rv_fooder, parent, false));
        }else */if(viewType==ITEM_TYPE_HEADER){
            return new HeaderViewHolder(ctx,LayoutInflater.from(ctx).inflate(R.layout.tailor_rv_header, parent, false));
        }else {
            return new BodyViewHolder(ctx,LayoutInflater.from(ctx).inflate(R.layout.item_tailor_detail, parent, false));

        }
    }
       String mealSplit [];
    @Override
    public void onMeal(String meal) {
//        Toast.makeText(ctx,"wwww"+meal,Toast.LENGTH_LONG).show();
        selectedMap.clear();
        price.clear();
        chickPrice=0;
        isfirst=true;
        if(!TextUtils.isEmpty(meal)){
            mealSplit=  meal.split("-");
            notifyDataSetChanged();
        }
    }
    //底部 ViewHolder
    class HeaderViewHolder extends ViewHolder{
        protected HeaderViewHolder(Context context, View view) {
            super(context, view);
        }

    }
    private static class BodyViewHolder extends ViewHolder{
        TextView body;
        public BodyViewHolder(Context context, View view) {
            super(context, view);

        }
    }
    String sizeclick;
    String colorclick;
    public void setCommonSizeStatus(int pos){
        for (int i = 0; i < sizeFlowLayout.getChildCount(); i++) {
            TextView textView = sizeFlowLayout.getChildAt(i).findViewById(R.id.item_tv_flowlayout);
            textView.setBackgroundResource(i == pos ? R.drawable.shape_1radius_fd_stroke : R.drawable.shape_1radius_99_stroke);
            if(i==pos){
                sizeclick =  textView.getText().toString();
            }
            textView.setTextColor(ctx.getResources().getColor(i == pos ? R.color.indicator_selected_color : R.color.gray));
        }
//        setSubmitStatus(true);
        if(pos == -1){
            sizeName = null;
        }else{
        }
    }

    public void setCommonColorStatus(int pos){
        for (int i = 0; i < colorFlowLayout.getChildCount(); i++) {
            TextView textView = colorFlowLayout.getChildAt(i).findViewById(R.id.item_tv_flowlayout);
            textView.setBackgroundResource(i == pos ? R.drawable.shape_1radius_fd_stroke : R.drawable.shape_1radius_99_stroke);
            if(i==pos){
                colorclick=  textView.getText().toString();
            }
            textView.setTextColor(ctx.getResources().getColor(i == pos ? R.color.indicator_selected_color : R.color.gray));
        }
//        setSubmitStatus(true);
        if(pos == -1){
            sizeName = null;
        }else{
//            tvSelectTailor.setText("");
        }
    }



    public String getClickcolor(){
        return colorclick;
    }
    public String getClickSize(){
       return sizeclick;
    }
    public String getTailNmae(){
        tailorName="";
        Set<Map.Entry<Integer,Integer>> set =selectedMap.entrySet();
//                   for (int i=0;i<set.size();i++){
//                     if(i==set.size()-1){
//                         tailorName+= data.get(set.get.getKey()-1).getChildrenCustomProduct().get(vale.getValue()).getName();
//                                }else {
//                         tailorName+= data.get(vale.getKey()-1).getChildrenCustomProduct().get(vale.getValue()).getName();
//                     }
//                      }
        for (Map.Entry<Integer,Integer> vale:set){
            tailorName+= data.get(vale.getKey()-1).getChildrenCustomProduct().get(vale.getValue()).getName()+",";
        }
        return tailorName;
    }

    double totalprice;
    double totl=0;
    public void setprice(){
        totl=0;
        for (int i=0;i<price.size();i++){
            totl=totl+(Double.parseDouble(price.get(i)));
        }
        totalprice =priceorg+totl;
//        totalprice =priceorg+chickPrice;
        textView.setText("合计: "+totalprice+"元");
    }
    public double totalprice(){
     return totalprice;
    }

String tailorName;
  public   Map<Integer,Integer> getTreeMap(){

        return selectedMap;
  }
}
