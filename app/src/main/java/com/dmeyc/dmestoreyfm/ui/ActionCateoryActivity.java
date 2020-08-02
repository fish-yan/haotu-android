package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;

import butterknife.Bind;
import butterknife.OnClick;

public class ActionCateoryActivity extends BaseActivity {
    @Bind(R.id.iv_chanman)
    ImageView iv_chanman;
    @Bind(R.id.iv_chanwoman)
    ImageView iv_chanwoman;
    @Bind(R.id.iv_pt)
    ImageView iv_pt;
    int type;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_actioncategory;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }
    @OnClick({R.id.iv_pt,R.id.iv_chanwoman,R.id.iv_chanman,R.id.tv_fatherson,R.id.tv_constrction,R.id.tv_orginal})
    public void onClick(View view){
        int viewid=view.getId();
        if(viewid==R.id.tv_fatherson){
            type=2;
            iv_chanman.setVisibility(View.GONE);
            iv_chanwoman.setVisibility(View.GONE);
            iv_pt.setVisibility(View.GONE);
            Intent intent=new Intent();
            intent.putExtra("category","团建");
            intent.putExtra("type",type);
            setResult(1234,intent);
            finish();
        }else if(viewid==R.id.tv_constrction){
            type=1;
            iv_chanman.setVisibility(View.GONE);
            iv_chanwoman.setVisibility(View.GONE);
            iv_pt.setVisibility(View.GONE);
            Intent intent=new Intent();
            intent.putExtra("category","亲子");
            intent.putExtra("type",type);
            setResult(1234,intent);
            finish();
        }else if(viewid==R.id.tv_orginal){
            type=0;
            iv_chanman.setVisibility(View.GONE);
            iv_chanwoman.setVisibility(View.GONE);
            iv_pt.setVisibility(View.GONE);
            Intent intent=new Intent();
            intent.putExtra("category","普通");
            intent.putExtra("type",type);
            setResult(1234,intent);
            finish();
        }
    }
}
