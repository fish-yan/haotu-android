package com.dmeyc.dmestoreyfm.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.photo.AbsActivity;
import com.dmeyc.dmestoreyfm.adapter.photo.AlbumActivity;
import com.dmeyc.dmestoreyfm.adapter.photo.CommonDefine;
import com.dmeyc.dmestoreyfm.adapter.photo.FileUtils;
import com.dmeyc.dmestoreyfm.adapter.photo.GridImageAdapter;
import com.dmeyc.dmestoreyfm.adapter.photo.ImageDelActivity;
import com.dmeyc.dmestoreyfm.adapter.photo.ImageUtils;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.CommRecordListBean;
import com.dmeyc.dmestoreyfm.bean.ImagePathBean;
import com.dmeyc.dmestoreyfm.bean.SorcerBean;
import com.dmeyc.dmestoreyfm.bean.SorcerIconBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.fragment.CommPKFragment;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.DensityUtil;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;
import com.dmeyc.dmestoreyfm.utils.GradeViewForScrollView;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.NoScrollListView;
import com.google.gson.Gson;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyItemDialogListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.tamic.novate.Throwable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


public class SorcerInActivity extends BaseActivity {
    int clickpos=-1;
    @Bind(R.id.rv_member)
    RecyclerView rv_member;

    @Bind(R.id.rv_gradview)
    RecyclerView rv_gradview;
    @Bind(R.id.btn_regist)
    Button btn_regist;
    @Bind(R.id.iv_right_title_bar)
    ImageView iv_right_title_bar;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_sockerin;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
                        LinearLayoutManager lm=  new LinearLayoutManager(SorcerInActivity.this);
                lm.setOrientation(OrientationHelper.HORIZONTAL);
                rv_member.setLayoutManager(lm);
                rv_member.addItemDecoration(new RecyclerView.ItemDecoration() {
                    @Override
                    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                        int pos = parent.getChildLayoutPosition(view);
//                        if(pos == 0){
                            outRect.left = DensityUtil.dip2px(8);
//                        }
                        outRect.right = DensityUtil.dip2px(8);
                    }
                });
                        rv_member.setAdapter(new SorcerInActivity.iconAdapter());


        rv_gradview.setLayoutManager(new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false));

        rv_gradview.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int pos = parent.getChildLayoutPosition(view);
//                        if(pos == 0){
                outRect.left = DensityUtil.dip2px(8);
//                        }
                outRect.right = DensityUtil.dip2px(8);
            }
        });
        rv_gradview.setAdapter(new SorcerInActivity.SorInGradViewAdapter());
    }

    class iconAdapter extends RecyclerView.Adapter<SorcerInActivity.iconAdapter.ViewHolder>{

        public iconAdapter(){

        }

        @Override
        public SorcerInActivity.iconAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view =getLayoutInflater().inflate(R.layout.adapter_settitlenumb,null);

            return new SorcerInActivity.iconAdapter.ViewHolder(view);
        }

        int clickpos=-1;
        @Override
        public void onBindViewHolder(final SorcerInActivity.iconAdapter.ViewHolder holder, final int position) {

            if((clickpos==-1&&0==position)||clickpos==position){
                holder.tv_settitle.setBackground(SorcerInActivity.this.getResources().getDrawable(R.drawable.shap_settitlebg));
            }else {
                holder.tv_settitle.setBackgroundColor(SorcerInActivity.this.getResources().getColor(R.color.gb));
            }

            holder.tv_settitle.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View view) {
                    clickpos=position;
                    notifyDataSetChanged();
                }

            });

        }



        @Override
        public int getItemCount() {
            return 10;
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView tv_settitle;

            public ViewHolder(View itemView) {
                super(itemView);
                tv_settitle=itemView.findViewById(R.id.tv_settitle);

            }
        }
    }

    public class SorInGradViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view =getLayoutInflater().inflate(R.layout.adapter_soucein,null);
            return new SorcerInActivity.SorinGradViewHoder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 5;
        }
    }

    class SorinGradViewHoder extends RecyclerView.ViewHolder{

        public SorinGradViewHoder(View itemView) {
            super(itemView);
        }
    }

    @OnClick({R.id.btn_regist,R.id.iv_right_title_bar})
    public void onClick(View view){
        int viewid=view.getId();
        if(viewid==R.id.btn_regist){
            startActivity(new Intent(SorcerInActivity.this,ReCharegeSetActivity.class));
        }else if(R.id.iv_right_title_bar==viewid){
            startActivity(new Intent(SorcerInActivity.this,SharePicInActivity.class));

        }

    }
}
