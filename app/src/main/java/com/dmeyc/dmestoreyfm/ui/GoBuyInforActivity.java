package com.dmeyc.dmestoreyfm.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;

public class GoBuyInforActivity extends BaseActivity implements View.OnClickListener {
    private EditText et_number;
    private RelativeLayout rl_reduce,rl_plus;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_gobuyinfor;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        et_number=(EditText) mRootView.findViewById(R.id.et_number);
        rl_reduce=(RelativeLayout)mRootView.findViewById(R.id.rl_reduce);
        rl_plus=(RelativeLayout)mRootView.findViewById(R.id.rl_plus);
        rl_reduce.setOnClickListener(this);
        rl_plus.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int viewid=view.getId();
        if(viewid==R.id.rl_reduce){
           String number= et_number.getText().toString().trim();
            if(!TextUtils.isEmpty(number)){
              int num=  Integer.parseInt(number);
              if(num>0){
                  et_number.setText((num-1)+"");
              }
            }else {
                Toast.makeText(GoBuyInforActivity.this,"请输入数量",Toast.LENGTH_LONG).show();
            }
        }else if(viewid==R.id.rl_plus){
            String number= et_number.getText().toString().trim();
            if(!TextUtils.isEmpty(number)){
                int num=  Integer.parseInt(number);
                if(num<99){
                    et_number.setText((num+1)+"");
                }
            }else {
                Toast.makeText(GoBuyInforActivity.this,"请输入数量",Toast.LENGTH_LONG).show();
            }
        }

    }
}
