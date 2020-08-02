package com.dmeyc.dmestoreyfm.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.RecommendBean;
import com.dmeyc.dmestoreyfm.bean.common.GoodsBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.dialog.ScreenDialog;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.present.CategoryPresenter;
import com.dmeyc.dmestoreyfm.utils.DataClass;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.Util;
import com.dmeyc.dmestoreyfm.wedgit.CommonPopupWindow;
import com.dmeyc.dmestoreyfm.wedgit.PriceView;
import com.dmeyc.dmestoreyfm.wedgit.ScreenView;
import com.tamic.novate.Throwable;
import com.zhy.adapter.recyclerview.base.ViewHolder;


import java.util.ArrayList;
import java.util.List;

public class spq extends BaseRvAdapter<GoodsBean>implements View.OnClickListener {
    private Context co;
    private List<GoodsBean> goodsBeans ;
    private RecyclerView rv;
   private TextView tv;
    private LinearLayout ll_filter,ll_syntheticals,ll_votes,ll_rocords;
    private ScreenView screenView;

    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;
    public static final int ITEM_TYPE_BOTTOM = 2;

    private ImageView iv_band;
    private int mHeaderCount=1;//头部View个数
//    private int mBottomCount=1;//底部View个数

    public spq(Context co, List<GoodsBean> goodsBeans, RecyclerView rv,TextView tv) {
        super(co,R.layout.adapter_goodsitem ,goodsBeans);
        this.co = co;
        this.goodsBeans = goodsBeans;
        this.rv = rv;
        this.tv=tv;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //想要item文字居中必须这样写
        if(viewType==ITEM_TYPE_HEADER){
            return new HeaderViewHolder(co,LayoutInflater.from(co).inflate(R.layout.header_mystore, parent, false));
        }else {
            return new BodyViewHolder(co,LayoutInflater.from(co).inflate(R.layout.adapter_goodsitem, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if(holder instanceof HeaderViewHolder){
            ll_filter=holder.getView(R.id.ll_filters);
            ll_filter.setOnClickListener(this);
            ll_syntheticals=holder.getView(R.id.ll_syntheticals);
            ll_syntheticals.setOnClickListener(this);
            ll_votes=holder.getView(R.id.ll_votes);
            ll_votes.setOnClickListener(this);
            ll_rocords=holder.getView(R.id.ll_rocords);
            ll_rocords.setOnClickListener(this);
        screenView = holder.getView(R.id.screen_view);
        iv_band=holder.getView(R.id.iv_band);
        if(TextUtils.isEmpty(logo)){
            iv_band.setVisibility(View.GONE);
        }else {
            iv_band.setVisibility(View.VISIBLE);
            GlideUtil.loadImage(co,logo,iv_band);
        }

            screenView.addData(DataClass.data);
        }else {
            String size = "";
            for (int ii = 0; ii < goodsBeans.get(position-mHeaderCount).getSizeList().size(); ii++) {
                size = size + goodsBeans.get(position-mHeaderCount).getSizeList().get(ii)+"";
            }
            TextView item_tv_name= holder.getView(R.id.item_tv_name);
               item_tv_name.setText(size);
            TextView item_tv_brand= holder.getView(R.id.item_tv_brand);
               item_tv_brand.setText(goodsBeans.get(position-mHeaderCount).getName());
            PriceView item_tv_priceview= holder.getView(R.id.item_tv_priceview);
               item_tv_priceview.setPrice(goodsBeans.get(position-mHeaderCount).getPrice());
            ImageView item_iv_istailor= holder.getView(R.id.item_iv_istailor);
                 item_iv_istailor.setVisibility(goodsBeans.get(position-mHeaderCount).isIsCustom() == 0 ? View.INVISIBLE : View.VISIBLE);
            ImageView item_iv_cover_pic= holder.getView(R.id.item_iv_cover_pic);
            GlideUtil.loadImage(co,goodsBeans.get(position-mHeaderCount).getImage(),item_iv_cover_pic);

            item_iv_cover_pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    int childAdapterPosition = rv.getChildAdapterPosition(view);
                    Util.startProductActivity(co,goodsBeans.get(position-1).getId(),"type");
//                    Toast.makeText(co,goodsBeans.get(childAdapterPosition).getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }




    @Override
    protected void convert(ViewHolder holder, GoodsBean goodsBean, int position) {

    }


//    @Override
//    protected void convert(ViewHolder holder, GoodsBean goodsBean, int position) {
//
//    }

//    @Override
//    public int getItemCount() {
//        if(goodsBeans!=null){
//           return goodsBeans.size();
//        }else {
//            return 0;
//        }
//    }

//    public class viewHolder extends ViewHolder {
//        TextView tv;
//        private PriceView item_tv_priceview;
//        private TextView item_tv_name;
//        private TextView item_tv_brand;
//        private ImageView item_iv_istailor,item_iv_cover_pic;
//
//        public viewHolder(Context context,View itemView) {
//            super(context,itemView);
//            tv=itemView.findViewById(R.id.tv);
//
//            item_tv_priceview=itemView.findViewById(R.id.item_tv_priceview);
//            item_tv_name=itemView.findViewById(R.id.item_tv_name);
//            item_tv_brand=itemView.findViewById(R.id.item_tv_brand);
//            item_iv_istailor=itemView.findViewById(R.id.item_iv_istailor);
//            item_iv_cover_pic=itemView.findViewById(R.id.item_iv_cover_pic);
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int childAdapterPosition = rv.getChildAdapterPosition(view);
//                    Util.startProductActivity(co,goodsBeans.get(childAdapterPosition).getId());
////                    Toast.makeText(co,goodsBeans.get(childAdapterPosition).getName(), Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//    }
private String logo;
    public void setData(List<GoodsBean> goodsBeans,String log){
        this.goodsBeans=goodsBeans;
        if(!TextUtils.isEmpty(log)){
            logo=log;
        }

        notifyDataSetChanged();
    }
    @Override
    public int getItemViewType(int position) {
        int dataItemCount = getContentItemCount();
       /* if (mBottomCount != 0 && position ==( dataItemCount)) {
//底部View
            return ITEM_TYPE_BOTTOM;
        }else*/ if(mHeaderCount!=0&&position==0){
            return ITEM_TYPE_HEADER;


        }else {
//内容View
            return ITEM_TYPE_CONTENT;
        }
    }

    public int getContentItemCount(){
        if(goodsBeans!=null){
           return goodsBeans.size();
        }else {
            return 0;
        }
    }

//    @Override
//    public void onBindViewHolder(com.zhy.adapter.recyclerview.base.ViewHolder holder, int position) {
//
//    }

    @Override
    public int getItemCount() {
        return  getContentItemCount() + mHeaderCount;
    }



    //底部 ViewHolder
    class HeaderViewHolder extends ViewHolder {
        public HeaderViewHolder(Context context, View view) {
            super(context,view);
        }
//    public BottomViewHolder(View itemView) {
////        super(itemView);
//    }
    }
    private static class BodyViewHolder extends ViewHolder{
        TextView body;
        public BodyViewHolder(Context context, View view) {
            super(context, view);
        }
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if(manager instanceof GridLayoutManager){   // 布局是GridLayoutManager所管理
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    // 如果是Header、Footer的对象则占据spanCount的位置，否则就只占用1个位置
//                    return (isHeader(position) || isFooter(position)) ? gridLayoutManager.getSpanCount() : 1;
                  if(getItemViewType(position) == ITEM_TYPE_HEADER){
                      return   gridLayoutManager.getSpanCount();// headerveiw时返回三个的单元格，从而占据整个一行的宽度
                    }else{
                      return   1  ;           // 正常情况下返回一个单元格
                    }
                }
            });
        }
    }

//    public boolean isHeader(int position){
//        return position >= 0 && position < mHeaderViews.size();
//    }
//
//    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
//        super.onAttachedToRecyclerView(recyclerView)
//        var layoutManager:RecyclerView.LayoutManager = recyclerView.layoutManager!!
//        if (layoutManager is GridLayoutManager){
//            layoutManager.spanSizeLookup = object :GridLayoutManager.SpanSizeLookup(){
//                override fun getSpanSize(position: Int): Int {
//                    return if(getItemViewType(position) == TYPE_HEAD){
//                        layoutManager.spanCount  // headerveiw时返回三个的单元格，从而占据整个一行的宽度
//                    }else{
//                        1          // 正常情况下返回一个单元格
//                    }
//                }
//            }
//        }
//    }
ScreenDialog dialog;
    private List<String> datas=new ArrayList<>();
    private CommonPopupWindow window;
    private ListView dataList;
    private DialogAdapter da;

        public void onClick(View view){

        switch (view.getId()){
            case R.id.ll_filters:
//                mPresenter.getDialogData();
                dialog  = new ScreenDialog(co, R.style.dialog_style_right,screenView.getmData());
                dialog.onSave(new ScreenDialog.SaveClickLisenter() {
                    @Override
                    public void onSave(String style, String season, String start,String priceend) {
//                        Toast.makeText(co,style+"11"+season+"222"+start+"333"+priceend,Toast.LENGTH_LONG).show();
                        requsSave(style,season,start,priceend);
                    }
                });
                dialog.show();
//                dialog.setSelectedData(screenView.selectMap);
//                dialog.setOnChooseClickListener(new ScreenDialog.OnChooseClickListener() {
//                    @Override
//                    public void onChooseClickListener(int position, boolean isSected, String value, int pos) {
//                        screenView.setChange(position,isSected,value,pos);
//                    }
//                });
                break;
            case R.id.ll_syntheticals:

                initPopupWindow();
                PopupWindow win=window.getPopupWindow();
                win.setAnimationStyle(R.style.animAlpha);
                datas.clear();
                datas.add("综合");
                datas.add("新品上架");
                datas.add("价格最低");
                datas.add("价格最高");
                window.showAsDropDown(ll_filter,  0, 0);
//                window.showAtLocation(ll_synthetical, Gravity.TOP, 30, 30);
                WindowManager.LayoutParams lp= ((Activity)co).getWindow().getAttributes();
//                lp.alpha=0.3f;
                lp.alpha=1f;
                ((Activity)co).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                ((Activity)co).getWindow().setAttributes(lp);
                break;
            case R.id.ll_votes:
                initPopupWindow();
                PopupWindow wins=window.getPopupWindow();
                wins.setAnimationStyle(R.style.animAlpha);
                datas.clear();

                datas.add("从高到低");
                datas.add("从低到高");
                window.showAsDropDown(ll_filter,  0, 0);
//                window.showAtLocation(ll_synthetical, Gravity.TOP, 30, 30);
                WindowManager.LayoutParams lps= ((Activity)co).getWindow().getAttributes();
//                lp.alpha=0.3f;
                lps.alpha=1f;
                ((Activity)co).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                ((Activity)co).getWindow().setAttributes(lps);
                break;
            case R.id.ll_rocord:
                break;
        }
    }


    private void initPopupWindow() {
        // get the height of screen
        DisplayMetrics metrics=new DisplayMetrics();
        ((Activity)co).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenHeight=metrics.heightPixels;
        // create popup window
//        window=new CommonPopupWindow(this, R.layout.popup_list, ViewGroup.LayoutParams.MATCH_PARENT, (int) (screenHeight*0.7)) {
        window=new CommonPopupWindow(co, R.layout.popup_list, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT) {
            @Override
            protected void initView() {
                View view=getContentView();
//                dataList=(ListView) view.findViewById(R.id.data_list);
//                dataList.setAdapter(new ArrayAdapter<String>(this, R.layout.item_popup_list, datas));
                da =new DialogAdapter();
                dataList.setAdapter(da);
                dataList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        Toast.makeText(getActivity(),"wozoule"+i,Toast.LENGTH_LONG).show();
//                        for (int i1=0;i1< rb_s.size();i1++){
//                            if(i1==i){
//                                rb_s.get(i).setVisibility(View.VISIBLE);
//                                rb_s.get(i).setBackground( ((Activity)co).getResources().getDrawable(R.drawable.screen_ic_right_orange));
//                            }else {
//                                rb_s.get(i).setVisibility(View.GONE);
//                            }
//                        }
                        da.notifyDataSetChanged();
                    }
                });

//                Toast.makeText(CategoryListActivity.this,"wozoule"+i,Toast.LENGTH_LONG).show();
//                for (int i1=0;i1< rb_s.size();i1++){
//                    if(i1==i){
//                        rb_s.get(i).setChecked(true);
//                    }else {
//                        rb_s.get(i).setChecked(false);
//                    }
//                }
//                da.notifyDataSetChanged();
            }

            @Override
            protected void initEvent() {
                dataList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        window.getPopupWindow().dismiss();
                    }
                });
            }

            @Override
            protected void initWindow() {
                super.initWindow();
                PopupWindow instance=getPopupWindow();
                instance.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp=((Activity)co).getWindow().getAttributes();
                        lp.alpha=1.0f;
                        ((Activity)co).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        ((Activity)co).getWindow().setAttributes(lp);
                    }
                });
            }
        };
    }

    public class DialogAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int i) {
            return datas.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            ViewHoders vh;
            if(view==null){
//                             view=getLayoutInflater().inflate(getc,R.layout.popup_list);
                view=LayoutInflater.from(co).inflate(R.layout.item_popup_list,null);
//                ll_items.add(ll_imte);
                vh=new ViewHoders(view);
                view.setTag(vh);
            }else {
                vh=(ViewHoders)view.getTag();
            }
//            rb_s.add(vh.rb_by);
            vh.item_text.setText(datas.get(i));

            return view;
        }
    }

    public class ViewHoders{
        TextView item_text;
        LinearLayout ll_imte;
        private ImageView rb_by;
        public ViewHoders(View view){
            item_text=view.findViewById(R.id.item_text);
            rb_by= view.findViewById(R.id.rb_by);
            ll_imte=view.findViewById(R.id.ll_imte);
        }
    }

    private int categoryuid;
    private int intseason=3;
    private int pricestar=0;
    private int priceend=100000;
    private int itemid=1;
    public void requsSave(String style, String season, String start,String end){
        if(!TextUtils.isEmpty(style)){
            for (int i=0;i<DataClass.data.size();i++){
                if(style.equals(DataClass.data.get(i).getCategoryName())){
                    categoryuid=DataClass.data.get(i).getId();
                }
            }
        }

        if("春".equals(season)){
            intseason=1;
        }else if("夏".equals(season)){
            intseason=2;
        }else if("秋".equals(season)){
            intseason=3;
        }else {
            intseason=4;
        }

        if(!TextUtils.isEmpty(start)){
            pricestar= Integer.parseInt(start);
            priceend=Integer.parseInt(end);
        }

        getDialogData(co, categoryuid, intseason, priceend, pricestar, 1,itemid, null );
//        mPresenter.getDialogData(this, categoryuid, intseason, priceend, pricestar, 1,itemid, new CategoryPresenter.OnDataGetLisener() {
//            @Override
//            public void onSuccess(RecommendBean bean) {
//                if(dialog!=null){
//                    dialog.dismiss();
//                }
//                RecommendBean sss=bean;
//                if(bean.getData().getGoods().size()>0){
//                    setData(bean.getData().getGoods());
//                    gv_item.setVisibility(View.VISIBLE);
//                    tv_desnodata.setVisibility(View.GONE);
//                }else {
//                    gv_item.setVisibility(View.GONE);
//                    tv_desnodata.setVisibility(View.VISIBLE);
//                }
//            }
//            @Override
//            public void onFailure(String errMsg) {
//                String sss=errMsg;
//            }
//        });

    }


    public void getDialogData(Context context, int categoryuid, int season,int endPrice,int startPrice,int sort,int gener, final CategoryPresenter.OnDataGetLisener lisener){
//        this.sendListener = lisener;

        ParamMap.Build b = new ParamMap.Build();

        if(endPrice!=100000000){
            b.addParams("gender", gener)
                    .addParams("season", season)
                    .addParams("startPrice", startPrice)
                    .addParams("endPrice", endPrice)
                    .addParams("sort", sort);
        }
        RestClient.getNovate(context).post(Constant.API.CHOOSE_DETAIL, b.addParams("categoryChildren", categoryuid)
                .build(), new DmeycBaseSubscriber<RecommendBean>() {
            @Override
            public void onSuccess(RecommendBean bean) {
                dialog.dismiss();
                RecommendBean sss=bean;
                if(bean.getData().getGoods().size()>0){
                    setData(bean.getData().getGoods(),"");
//                    setData(bean.getData().getGoods());
                    rv.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.GONE);
                }else {
                    rv.setVisibility(View.GONE);
                    tv.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onError(Throwable e) {
                dialog.dismiss();
//                if(sendListener != null)
//                    sendListener.onFailure(e.getMessage());
            }
        });
    }

}

