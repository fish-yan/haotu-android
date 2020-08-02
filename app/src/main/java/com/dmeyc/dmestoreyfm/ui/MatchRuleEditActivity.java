package com.dmeyc.dmestoreyfm.ui;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.utils.city.ToastUtils;


import butterknife.Bind;
import butterknife.OnClick;
import io.rong.imkit.widget.adapter.BaseAdapter;

public class MatchRuleEditActivity extends BaseActivity {
    @Bind(R.id.et_machrule)
    EditText et_machrule;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_machrule;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }
    @OnClick(R.id.tv_right_title_bar)
    public void onClick(View view){
        int viewie=view.getId();
        if(viewie==R.id.tv_right_title_bar){
            if(TextUtils.isEmpty(et_machrule.getText().toString().trim())){
                com.dmeyc.dmestoreyfm.utils.ToastUtil.show("请输入比赛规则");
                return;
            }
            Intent intent=new Intent();
            intent.putExtra("machrule",et_machrule.getText().toString().trim());
            setResult(888,intent);
            finish();
        }
    }
}
