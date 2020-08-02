package com.dmeyc.dmestoreyfm.wedgit;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.common.ProductCategoryBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jockie on 2017/12/20
 * Email:jockie911@gmail.com
 */

public class ScreenView extends RelativeLayout {

    private RecyclerView mRecyclew;
    private List<List<String>> mData = new ArrayList<>();
    private List<String> data1 = new ArrayList<>();
    private List<String> data2 = new ArrayList<>();

    public Map<Integer,String> selectMap = new HashMap<>();
    private Myadapter adapter;
    private OnChooseClickListener listener;
    private List<List<Boolean>> statusLists = new ArrayList<>();
    private List<ProductCategoryBean> productCategoryData; //种类筛选list

    public ScreenView(Context context) {
        super(context);
        init();
    }

    public ScreenView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

    }

    public List<List<String>> getmData(){
        return mData;
    }

    public void addData(List<ProductCategoryBean> data){
        this.productCategoryData = data;
        List<String> cartLists = new ArrayList<>();
        for (ProductCategoryBean bean : data) {
            cartLists.add(bean.getCategoryName());
        }
        mData.clear();
        mData.add(cartLists);

        data1.add("春");
        data1.add("夏");
        data1.add("秋");
        data1.add("冬");

        data2.add("50-1000");
        data2.add("1000-2000");
        data2.add("2000-4000");
        data2.add("4000-5000");
        data2.add("5000-8000");
        mData.add(data1);
        mData.add(data2);
//DataClass .data0=cartLists;
//DataClass.data1=data1;
// DataClass.data2=data2;
        View view = LayoutInflater.from(getContext()).inflate(R.layout.screen_view,this,true);
        mRecyclew = (RecyclerView) view.findViewById(R.id.recycleview);
        mRecyclew.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new Myadapter(getContext(), R.layout.item_screen, mData);
        mRecyclew.setAdapter(adapter);
//        adapter.addData(mData);
    }

    public void removeSelectTag(String text) {
        int pos = 0;
        for (Map.Entry<Integer, String> entry : selectMap.entrySet()) {
            if(TextUtils.equals(entry.getValue(),text)){
                pos = entry.getKey();
                break;
            }
        }
        selectMap.remove(pos);
        List<Boolean> tempLists = statusLists.get(pos);
        for (int i = 0; i < tempLists.size(); i++) {
            tempLists.set(i,false);
        }
        statusLists.set(pos,tempLists);
        adapter.notifyDataSetChanged();
        if(listener != null)
            listener.onChooseClickListener(selectMap);
    }

    public void setChange(int position, boolean isSected, String value, int pos) {
        if(isSected){
            selectMap.put(position,value);
        }else{
            selectMap.remove(position);
        }
        List<Boolean> list = statusLists.get(position);
        for (int i = 0; i < list.size(); i++) {
            list.set(i,false);
        }
        if(isSected)
           list.set(pos,true);
        statusLists.set(position,list);
        adapter.notifyDataSetChanged();
        if(listener != null)
            listener.onChooseClickListener(selectMap);
    }


    class Myadapter extends CommonAdapter<List<String>>{

        public Myadapter(Context context, int layoutId, List datas) {
            super(context, layoutId, datas);
        }

        public void addData(List<List<String>> data){
            mDatas.clear();
            mData.addAll(data);
            notifyDataSetChanged();
        }

        @Override
        protected void convert(ViewHolder holder, final List<String> list,int position) {
            final RecyclerView recyclerView = holder.getView(R.id.recycleview);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
            recyclerView.setAdapter(new ItemAdapter(getContext(),R.layout.textview,list,position));
        }
    }

    private class ItemAdapter extends CommonAdapter<String>{
        List<Boolean> tagLists = new ArrayList<>();
        int position ;
        List<String> datas;

        public ItemAdapter(Context context, int layoutId, List<String> datas, int position) {
            super(context, layoutId, datas);
            this.position = position;
            this.datas = datas;
            for (String s : datas) {
                tagLists.add(false);
            }
            statusLists.add(tagLists);
        }

        @Override
        protected void convert(ViewHolder holder, final String s, final int pos) {
            holder.setText(R.id.textview,s);
            final TextView textView = holder.getView(R.id.textview);
            if(statusLists.get(position).get(pos)){
                textView.setTextColor(getResources().getColor(R.color.white));
                textView.setBackground(getResources().getDrawable(R.drawable.shape_15radius_1a));
            }else{
                textView.setTextColor(getResources().getColor(R.color.color_1a1a1a));
                textView.setBackground(null);
            }

            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(statusLists.get(position).get(pos)){  //当前已经选中，点击取消
                        selectMap.remove(position);
                        statusLists.get(position).set(pos,false);
                    }else{      //当前未选中 点击选中 改变状态
                        for (int i = 0; i < datas.size(); i++) {
                            statusLists.get(position).set(i,i == pos);
                        }
                        selectMap.put(position,s);
                    }
                    if(listener != null)
                        listener.onChooseClickListener(selectMap);
                    notifyDataSetChanged();
                }
            });
        }
    }

    public void setOnChooseClickListener(OnChooseClickListener listener) {
        this.listener = listener;
    }

    public interface OnChooseClickListener{
        void onChooseClickListener(Map<Integer,String> map);
    }

    /**
     * 类别筛选
     */
    public Map<String,Object> setCategoryParam(Map<String,Object> map){
        if(selectMap.containsKey(0)){
            for (ProductCategoryBean bean : productCategoryData) {
                if(TextUtils.equals(bean.getCategoryName(),selectMap.get(0))){
                    map.put("category",bean.getId());
                    break;
                }
            }
        }else{
            if(map.containsKey("category"))
                map.remove("category");
        }
        return map;
    }

    /**
     * 价格筛选
     */
    public Map<String,Object> setPriceParam(Map<String,Object> map){
        if(selectMap.containsKey(2)){
            String priceArea = selectMap.get(2);
            int startPrice = 0;
            int endPrice = 0;
            if(priceArea.contains("内")){
                endPrice = 1000;
            }else if(priceArea.contains("以上")){
                startPrice = 10000;
                endPrice = Integer.MAX_VALUE - 1;
            }else{
                String[] split = priceArea.split("-");
                startPrice = Integer.valueOf(split[0]);
                endPrice = Integer.valueOf(split[1]);
            }
            map.put("startPrice",startPrice);
            map.put("endPrice",endPrice);
        }else{
            if(map.containsKey("startPrice")){
                map.remove("startPrice");
                map.remove("endPrice");
            }
        }
        return map;
    }

    /**
     * 季节选择
     */
    public Map<String,Object> setSeasonParam(Map<String,Object> map){
        return map;
    }
}
