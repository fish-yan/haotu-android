package com.dmeyc.dmestoreyfm.ui;

import android.app.Dialog;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.CommonPopupWindow;
import com.dmeyc.dmestoreyfm.wedgit.flowview.AutoFlowLayout;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class PublicizeActivity extends BaseActivity {
    @Bind(R.id.iv_left_title_bar)
    ImageView iv_left_title_bar;
    @Bind(R.id.rl_publicizesize)
    RelativeLayout rl_publicizesize;

    @Bind(R.id.gv_publize)
    GridView gv_publize;
    @Bind(R.id.iv_shopcar)
    ImageView iv_shopcar;
    @Bind(R.id.tv_foot)
    ImageView tv_foot;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_publicize;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        gv_publize.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 10;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }
            ViewHolder viewHolder;
            @Override
            public View getView(final int i, View view, ViewGroup viewGroup) {

                if(view==null){
                    view=getLayoutInflater().inflate(R.layout.adapter_gvpublicize,null);
                    viewHolder=new ViewHolder(view);
                    view.setTag(viewHolder);
                }else {
                    viewHolder=(ViewHolder) view.getTag();
                }
                viewHolder.gv_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gvItmeClick();
                    }
                });
                return view;
            }
        });
//        gv_publize.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//            }
//        });
    }

    @OnClick({R.id.iv_left_title_bar,R.id.rl_publicizesize,R.id.iv_shopcar,R.id.tv_foot})
    public void onClick(View view){
        int viewid=view.getId();
        if(viewid==R.id.iv_left_title_bar){
            finish();
        }else if(R.id.rl_publicizesize==viewid){
            initPopupWindow();
            PopupWindow win=window.getPopupWindow();
            win.setAnimationStyle(R.style.animAlpha);
            window.showAsDropDown(rl_publicizesize,  0, 30);
            WindowManager.LayoutParams lp=getWindow().getAttributes();
            lp.alpha=1f;
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            getWindow().setAttributes(lp);
        }else if(R.id.iv_shopcar==viewid){
            goShop();
        }else if(R.id.tv_foot==viewid){
            goFoot();
        }
    }
//    ArrayList<String> popdata=new ArrayList<>();
    private CommonPopupWindow window;
//    private Button btn_login;
//    AutoFlowLayout<String> hotFlowLayout;
    private void initPopupWindow() {
        // get the height of screen
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        window=new CommonPopupWindow(this, R.layout.pop_publicize, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT) {
            @Override
            protected void initView() {
                View view=getContentView();
//              ListView  data_list=view.findViewById(R.id.data_list);

            }

            @Override
            protected void initEvent() {

            }
            @Override
            protected void initWindow() {
                super.initWindow();
                PopupWindow instance=getPopupWindow();
                instance.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {

                    }
                });
            }
        };
    }

    public class ViewHolder{
        LinearLayout gv_item;
        public ViewHolder(View view){
            gv_item=view.findViewById(R.id.gv_item);
        }
    }
Dialog dialog;
    TextView tv_back,tv_add;
    public void gvItmeClick(){
        dialog  = new Dialog(PublicizeActivity.this, R.style.MyDialog);
        //设置它的ContentView
        dialog.setContentView(R.layout.dialog_bill);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        window.setGravity(Gravity.BOTTOM);
        WindowManager m = getWindowManager();
        final Display d = m.getDefaultDisplay(); // 获取屏幕宽、高
        WindowManager.LayoutParams params = window.getAttributes();
//        params.height = (int) (d.getHeight() * 0.5); // 高度设置为屏幕的0.5
        params.width = (int) (d.getWidth() * 1); // 宽度设置为屏幕的0.6
        window.setAttributes(params);
        tv_back=dialog.findViewById(R.id.tv_back);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        tv_add=dialog.findViewById(R.id.tv_add);
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.show("添加");
            }
        });
        dialog.show();
    }
    public void goShop(){
        dialog  = new Dialog(PublicizeActivity.this, R.style.MyDialog);
        //设置它的ContentView
        dialog.setContentView(R.layout.dialog_shopcar);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        window.setGravity(Gravity.BOTTOM);
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高
        WindowManager.LayoutParams params = window.getAttributes();
        params.height = (int) (d.getHeight() * 0.5); // 高度设置为屏幕的0.5
        params.width = (int) (d.getWidth() * 1); // 宽度设置为屏幕的0.6
        window.setAttributes(params);
        ListView lv_shop= dialog.findViewById(R.id.lv_shop);
        lv_shop.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }
            PublicizeActivity.ShopViewHorlder shopViewHorlder;
            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                if(view==null){
                    view=getLayoutInflater().inflate(R.layout.adapter_shopcar,null);
                    shopViewHorlder=new PublicizeActivity.ShopViewHorlder(view);
                    view.setTag(shopViewHorlder);
                }else {
                    shopViewHorlder=(PublicizeActivity.ShopViewHorlder) view.getTag();
                }
                return view;
            }
        });

        dialog.show();
    }
    public class ShopViewHorlder{
        public ShopViewHorlder(View view){
        }
    }

    public void goFoot(){
        dialog  = new Dialog(PublicizeActivity.this, R.style.MyDialog);
        //设置它的ContentView
        dialog.setContentView(R.layout.dialog_shopcar);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        window.setGravity(Gravity.BOTTOM);
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高
        WindowManager.LayoutParams params = window.getAttributes();
        params.height = (int) (d.getHeight() * 0.5); // 高度设置为屏幕的0.5
        params.width = (int) (d.getWidth() * 1); // 宽度设置为屏幕的0.6
        window.setAttributes(params);
        ListView lv_shop= dialog.findViewById(R.id.lv_shop);
        lv_shop.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }
            PublicizeActivity.ShopViewHorlder shopViewHorlder;
            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                if(view==null){
                    view=getLayoutInflater().inflate(R.layout.adapter_shopcar,null);
                    shopViewHorlder=new PublicizeActivity.ShopViewHorlder(view);
                    view.setTag(shopViewHorlder);
                }else {
                    shopViewHorlder=(PublicizeActivity.ShopViewHorlder) view.getTag();
                }
                return view;
            }
        });
        dialog.show();
    }

}
