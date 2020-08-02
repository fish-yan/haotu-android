package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;

import com.zhy.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jockie on 2018/1/4
 * Email:jockie911@gmail.com
 */

public abstract class BaseRvAdapter<T> extends CommonAdapter<T>{

    /**
     * @param context
     * @param layoutId
     * @param datas 不能为null
     */
    public BaseRvAdapter(Context context, int layoutId, List<T> datas) {
        super(context, layoutId, datas);
    }

    /**
     * 重置数据，用于下拉刷新
     * @param data
     */
    public void addRefreshData(List<T> data){
        if(mDatas == null)
            mDatas = new ArrayList<>();
        if(mDatas.size() != 0)
            mDatas.clear();
        addData(data);
    }

    /**
     * 添加数据，用于上拉加载更多
     * @param data
     */
    public void addData(List<T> data){
        if(mDatas == null)
            mDatas = new ArrayList<>();
        if(data != null)
            mDatas.clear();
            mDatas.addAll(data);
        notifyDataSetChanged();
    }

//    public void addData(List<T> data){
//        if(mDatas == null)
//            mDatas = new ArrayList<>();
//        if(data != null)
//            mDatas.addAll(data);
//        notifyDataSetChanged();
//    }

    public void addData(List<T> data,boolean isRefresh){
        if(isRefresh){
            addRefreshData(data);
        }else{
            addData(data);
        }
    }

    public void clear(){
        if(mDatas == null)
            mDatas = new ArrayList<>();
        if(mDatas.size() > 0)
            mDatas.clear();
        notifyDataSetChanged();
    }

    public void delete(int positon){
        if(mDatas == null)
            mDatas = new ArrayList<>();
        if(mDatas.size() > positon)
            mDatas.remove(positon);
        notifyDataSetChanged();
    }

    public T getItem(int position){
        return mDatas.get(position);
    }




    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }
}
