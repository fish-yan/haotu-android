package com.dmeyc.dmestoreyfm.newui.home.living.lookanchor;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.LookAnchorAdapter;
import com.dmeyc.dmestoreyfm.bean.response.ActivityDetailBean;
import com.dmeyc.dmestoreyfm.bean.response.UserListBean;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.newbase.BaseActivity;

import java.io.Serializable;
import java.util.List;

import butterknife.Bind;

/**
 * create by cxg on 2019/12/29
 */
public class LookAnchorActivity extends BaseActivity {
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    public static void newInstance(Context context, List<ActivityDetailBean.DataBean.SignUp> list) {
        Intent intent = new Intent(context, LookAnchorActivity.class);
        intent.putExtra(ExtraKey.SERIALIZABLE_DATA, ((Serializable) list));
        context.startActivity(intent);
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_look_anchor;
    }

    @Override
    protected void initViews() {
        setTitle("主播");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        LookAnchorAdapter adapter = new LookAnchorAdapter(this);
        mRecyclerView.setAdapter(adapter);
        adapter.bindToRecyclerView(mRecyclerView);
        adapter.replaceData((List<ActivityDetailBean.DataBean.SignUp>)getIntent().getSerializableExtra(ExtraKey.SERIALIZABLE_DATA));

    }
}
