package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.ChooseClothBean;
import com.dmeyc.dmestoreyfm.ui.CategoryListActivity;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jockie on 2018/1/8
 * Email:jockie911@gmail.com
 */

public class ShoppingAdapter extends BaseRvAdapter<ChooseClothBean.CategoryDataBean>{

    private boolean isWithHead;
    private Context ctx;
    private int itemid=-1;

    private List<GridView>gvs=new ArrayList<>();
    /**
     * @param context
     * @param layoutId
     * @param datas    不能为null
     */
    public ShoppingAdapter(Context context, int layoutId, List<ChooseClothBean.CategoryDataBean> datas) {
        super(context, layoutId, datas);

        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
//                Util.startProductActivity(mContext,mDatas.get(isWithHead ? position - 1 : position));
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }
private List<ChooseClothBean.CategoryDataBean> count;
    public ShoppingAdapter(Context context, int layoutId, List<ChooseClothBean.CategoryDataBean> datas, boolean isWithHead,int item) {
        this(context, layoutId, datas);
        this.isWithHead = isWithHead;
        ctx=context;
        itemid=item;
    }

    private int itempos;
String categoryChiled="";
    ChooseClothBean.CategoryDataBean gb =null;
//    List<ChooseClothBean.CategoryDataBean> gbs=new ArrayList<>();

    @Override
    protected void convert(ViewHolder holder, final ChooseClothBean.CategoryDataBean goodsBean, final int position) {
//        gb=goodsBean;
//        gbs.add(gb);
        if(1==position){
            categoryChiled="";
        }
        TextView textView= holder.getView(R.id.tv_category);
        textView.setVisibility(View.VISIBLE);
        textView.setText(goodsBean.getCategoryName());
        final GridView gv_item=holder.getView(R.id.gv_item);
//        gv_item.setTag(1,position);
//        gv_item.setTag(2,goodsBean);
//        holder.getView(R.id.item_ll_root).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                itempos=position;
//                Toast.makeText(ctx,"wo beiaole"+position,Toast.LENGTH_LONG).show();
//            }
//        });
//        gv_item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(ctx,"wo beiaole"+position,Toast.LENGTH_LONG).show();
//            }
//        });
//        gvs.add(gv_item);
//        gv_item.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
//
//            }
//
//            @Override
//            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
//                return false;
//            }
//        });
        gv_item.setVisibility(View.VISIBLE);
        gv_item.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return count.get(position-1).getProductCategoryChildren().size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int ii , View convertView, ViewGroup parent) {
                ViewHoder vh=null;
                if(convertView==null){
                    convertView=   LayoutInflater.from(ctx).inflate(R.layout.gv_adapteritem,null);
                    vh=new ViewHoder(convertView);
                    convertView.setTag(vh);
                }else {
                    vh=(ViewHoder) convertView.getTag();
                }
//                categoryChiled=categoryChiled+","+goodsBean.getProductCategoryChildren().get(position).getCategoryName();
//                categoryChiled=goodsBean.getProductCategoryChildren().get(position).getId()+"";
                vh.tv_itemtext.setText(goodsBean.getProductCategoryChildren().get(ii).getCategoryName());
                GlideUtil.loadImage(ctx,goodsBean.getProductCategoryChildren().get(ii).getImages(),vh.iv_picitem);
                return convertView;
            }
        });


        gv_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//              int pos= (int) gv_item.getTag(1);
//                ChooseClothBean.CategoryDataBean cc= (ChooseClothBean.CategoryDataBean) gv_item.getTag(2);
                ctx.startActivity(new Intent(ctx,CategoryListActivity.class).putExtra("categoryuid",goodsBean.getProductCategoryChildren().get(i).getId()).putExtra("itemid",itemid));
//                ctx.startActivity(new Intent(ctx,CategoryListActivity.class).putExtra("categoryuid",gb.getProductCategoryChildren().get(i).getId()).putExtra("itemid",itemid));
//                ToastUtil.show("我是"+position);
            }
        });

//        gvs.get(position-1).setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                ctx.startActivity(new Intent(ctx,CategoryListActivity.class).putExtra("categoryuid",gb.getProductCategoryChildren().get(position).getId()).putExtra("itemid",itemid));
////                ToastUtil.show("我是"+position);
//            }
//        });
    }
    public class ViewHoder{
        private TextView tv_itemtext;
        private ImageView iv_picitem;
        public ViewHoder(View view){
            tv_itemtext= view.findViewById(R.id.tv_itemtext);
            iv_picitem=view.findViewById(R.id.iv_picitem);
        }
    }
    public void count(List<ChooseClothBean.CategoryDataBean> num,int  itemid){
        count=num;
        this.itemid=itemid;
    }
}
