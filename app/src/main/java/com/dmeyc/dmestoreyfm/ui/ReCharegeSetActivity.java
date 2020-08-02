package com.dmeyc.dmestoreyfm.ui;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.utils.DensityUtil;

import butterknife.Bind;

public class ReCharegeSetActivity extends BaseActivity {
    @Bind(R.id.rv_gradview)
    RecyclerView rv_gradview;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_rechagerset;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

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
        rv_gradview.setAdapter(new ReCharegeSetActivity.SorInGradViewAdapter());

    }


    public class SorInGradViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view =getLayoutInflater().inflate(R.layout.adapter_soucein,null);
            return new ReCharegeSetActivity.SorinGradViewHoder(view);
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

}
