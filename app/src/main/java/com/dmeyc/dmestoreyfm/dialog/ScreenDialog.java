package com.dmeyc.dmestoreyfm.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.utils.DataClass;
import com.dmeyc.dmestoreyfm.utils.DensityUtil;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jockie on 2017/11/17
 * Email:jockie911@gmail.com
 */

public class ScreenDialog extends Dialog{

    @Bind(R.id.recycleview)
    RecyclerView mRecycleView;

    ExpandView expandView;
    private List<ExpandView> expandlist = new ArrayList<>();
    private List<List<String>> mData = new ArrayList<>();
    private List<String> data1 = new ArrayList<>();
    private List<String> data2 = new ArrayList<>();
    private OnChooseClickListener listener;
    private MyAdapter myAdapter;

    public ScreenDialog(@NonNull Context context) {
        super(context);
    }

    public ScreenDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    public ScreenDialog(@NonNull Context context, @StyleRes int themeResId, List<List<String>> lists) {
        super(context, themeResId);
        this.mData = lists;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_screen);
        ButterKnife.bind(this);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (dialogWindow.getWindowManager().getDefaultDisplay().getWidth() * 0.85);
        lp.height = dialogWindow.getWindowManager().getDefaultDisplay().getHeight() - DensityUtil.dip2px(20);
        lp.gravity = Gravity.RIGHT | Gravity.BOTTOM;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialogWindow.setAttributes(lp);
        initData();
    }

    private void initData() {

        for (List<String> list : mData) {
            ArrayList<Boolean> itemStatusList = new ArrayList<>();
            for (String s : list) {
                itemStatusList.add(false);
            }
//            statusList.add(itemStatusList);
        }

        mRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        myAdapter = new MyAdapter(getContext(), R.layout.item_rv_dialog_screen, mData);
        mRecycleView.setAdapter(myAdapter);
    }

    Map<Integer, String> selectMap;
    public void setSelectedData(Map<Integer, String> selectMap) {
        this.selectMap = selectMap;
        if(mData.size() == 0)
            return;
        for (Map.Entry<Integer, String> entry : selectMap.entrySet()) {
            for (int i = 0; i < mData.get(entry.getKey()).size(); i++) {
                if(TextUtils.equals(mData.get(entry.getKey()).get(i),entry.getValue())){
//                    statusList.get(entry.getKey()).set(i,true);
                    break;
                }
            }
        }
        myAdapter.notifyDataSetChanged();
    }
private int enab=0;
    private class MyAdapter extends CommonAdapter<List<String>>{
private int parepos;
        private String[] titles = {"品类","季节","价格"};

        public MyAdapter(Context context, int layoutId, List<List<String>> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(final ViewHolder holder, final List<String> list, final int position) {

         TextView title=   holder.getView(R.id.item_tv_title);
         LinearLayout ll_cate= holder.getView(R.id.ll_cate);
         ImageView iv_arrow= holder.getView(R.id.iv_arrow);
            title.setText(titles[position]);
            if(0==position){
                title.setVisibility(View.GONE);
                ll_cate.setVisibility(View.VISIBLE);
            }else {
                title.setVisibility(View.VISIBLE);
                ll_cate.setVisibility(View.GONE);
            }
            expandView =  holder.getView(R.id.tv);
            expandlist.add(expandView);
            expandlist.get(position).setTextItems(list);
            expandView.setOnItemClickListener(new ExpandView.OnItemClickListener() {
                @Override
                public void onItemClick(View view, ViewGroup parent, int position) {
//                    view.setBackground(getContext().getResources().getDrawable(R.drawable.conner_black));
                    DataClass.data.get(position).getId();
                }
            });

            iv_arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(0==enab){
                        expandlist.get(0).expandItems();
                        enab=1;
                    }else {
                        expandlist.get(0).packUpItems();
                        enab=0;
                        expandlist.get(0).setTextBgColor(R.drawable.conner_catechild);
                    }
                }
            });
        }
    }

//    private List<List<Boolean>> statusList = new ArrayList<>();
//    private class ItemAdapter extends com.zhy.adapter.abslistview.CommonAdapter<String>{
//
//        private int position;
//        private List<String> datas;
//
//        public ItemAdapter(Context context, int layoutId, List<String> datas, int position) {
//            super(context, layoutId, datas);
//            this.position = position;
//            this.datas = datas;
//        }
//
//        @Override
//        protected void convert(com.zhy.adapter.abslistview.ViewHolder viewHolder, final String item, final int pos) {
//            TextView tv = viewHolder.getView(R.id.tv);
//            tv.setText(item);
//            tv.setTextSize(12);
//            tv.setTextColor(getContext().getResources().getColor(R.color.color_1a1a1a));
//            tv.setBackgroundResource(R.drawable.conner_catechild);
//            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(35));
//            tv.setLayoutParams(params);
//            tv.setGravity(Gravity.CENTER);
//
//            if(statusList.get(position).get(pos)){
////                tv.setBackgroundResource(R.drawable.shape_1radius_1a);
//                tv.setTextColor(getContext().getResources().getColor(R.color.white));
//            }else{
////                tv.setBackground(null);
//                tv.setTextColor(getContext().getResources().getColor(R.color.color_1a1a1a));
//            }

//            tv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(statusList.get(position).get(pos)){
//                        statusList.get(position).set(pos,false);
//                        selectMap.remove(position);
//                        if(listener != null)
//                            listener.onChooseClickListener(position,false,"",pos);
//                    }else{
//                        for (int i = 0; i < datas.size(); i++) {
//                            statusList.get(position).set(i,i == pos);
//                        }
//                        selectMap.put(position,item);
//                        if(listener != null)
//                            listener.onChooseClickListener(position,true,item,pos);
//                    }
//                    notifyDataSetChanged();
//                }
//            });

//        }
//    }

    public void setOnChooseClickListener(OnChooseClickListener listener) {
        this.listener = listener;
    }

    public interface OnChooseClickListener{
        void onChooseClickListener(int position, boolean isSected, String value, int pos);
    }

    @OnClick(R.id.tv_reset)
    public void onClickreset(){
        for (int i=0;i<expandlist.size();i++){
            expandlist.get(i).ClearItem();
        }
    }
    String chitem0;
    String chitem1;
    String chitem2;
    String pricestar;
    String priceend;
    @OnClick(R.id.tv_save)
    public void onSaveClick(){
        chitem0 =  expandlist.get(0).checkItem0();
        chitem1  =  expandlist.get(1).checkItem1();
        chitem2  =  expandlist.get(2).checkItem2();
        if(!TextUtils.isEmpty(chitem2)){
            pricestar=chitem2.split("-")[0];
            priceend=chitem2.split("-")[1];
        }
        saveClickLisenter.onSave(chitem0,chitem1,pricestar,priceend);
    }
public SaveClickLisenter saveClickLisenter;
    public void onSave(SaveClickLisenter save ){
        saveClickLisenter=save;
    }


    public interface SaveClickLisenter{
      void   onSave(String style,String season,String pricestar,String priceend);
    }
}
