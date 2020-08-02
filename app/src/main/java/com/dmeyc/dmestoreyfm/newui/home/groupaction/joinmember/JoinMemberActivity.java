package com.dmeyc.dmestoreyfm.newui.home.groupaction.joinmember;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.JoinMemberAdatper;
import com.dmeyc.dmestoreyfm.bean.response.ActivityDetailBean;
import com.dmeyc.dmestoreyfm.constant.ExtraKey;
import com.dmeyc.dmestoreyfm.newbase.BaseActivity;

import java.io.Serializable;
import java.util.List;

/**
 * create by cxg on 2019/12/26
 */
public class JoinMemberActivity extends BaseActivity {
    public static void newInstance(Context context, List<ActivityDetailBean.DataBean.SignUp> list) {
        Intent intent = new Intent(context, JoinMemberActivity.class);
        intent.putExtra(ExtraKey.JOIN_ACTION_LIST, ((Serializable) list));
        context.startActivity(intent);
    }
    @Override
    protected int setContentView() {
        return R.layout.activity_join_member;
    }

    @Override
    protected void initViews() {
        setTitle("报名人员");
        RecyclerView recyclerView = findViewById(R.id.rv_member);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        JoinMemberAdatper adatper = new JoinMemberAdatper(this);
        recyclerView.setAdapter(adatper);
        adatper.bindToRecyclerView(recyclerView);
        adatper.replaceData(((List<ActivityDetailBean.DataBean.SignUp>) getIntent().getSerializableExtra(ExtraKey.JOIN_ACTION_LIST)));

    }
}
