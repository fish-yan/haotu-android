package com.dmeyc.dmestoreyfm.ui;

import android.view.View;
import android.widget.EditText;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.PublishActionAfterBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import butterknife.Bind;
import butterknife.OnClick;

public class CommIntroEditeActivity extends BaseActivity {

    @Bind(R.id.et_backtext)
    EditText et_backtext;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_commintroedite;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
et_backtext.setText(getIntent().getStringExtra("remark"));
    }
    @OnClick(R.id.btn_next)
    public void onClick(View view){
        int viewid=view.getId();
        if(R.id.btn_next==viewid){
            submitcomment();
           }
       }

//    String strimage="";
    public void submitcomment(){


//        for (int i=0;i<imagurl.size();i++){
//            strimage=strimage+imagurl.get(i);
//            if(i!=imagurl.size()-1){
//                strimage=strimage+",";
//            }
//        }
//        if(strimage.endsWith(",")){
//            strimage=strimage.substring(0,strimage.length()-1);
//        }

        ParamMap.Build  pb=    new ParamMap.Build();
//        if("education".equals(getIntent().getStringExtra("education"))){
//            if("3".equals(getIntent().getStringExtra("grouptype"))){
//                pb .addParams("educateImg", strimage)
//                        .addParams("educateBackground", et_backtext.getText().toString().trim())
//                        .addParams("remark", "")
//                        .addParams("remarkImage", "");
//            }else if("5".equals(getIntent().getStringExtra("grouptype"))){
//                pb .addParams("educateImg", "")
//                        .addParams("educateBackground", "")
//                        .addParams("remark", et_backtext.getText().toString().trim())
//                        .addParams("remarkImage", strimage);
//            }
//        }else if("person".equals(getIntent().getStringExtra("education"))){
//            pb .addParams("educateImg", "")
//                    .addParams("educateBackground", "")
//                    .addParams("remark", et_backtext.getText().toString().trim())
//                    .addParams("remarkImage", strimage);
//        }

        RestClient.getYfmNovate(CommIntroEditeActivity.this).post(Constant.API.YFM_CHANGECOMBACK, pb
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("group_id", getIntent().getIntExtra("groupid",-1))
                .addParams("group_logo", "")
                .addParams("phoneNo", "")
              .addParams("educateImg", "")
                        .addParams("educateBackground", "")
                        .addParams("remark", et_backtext.getText().toString().trim())
                        .addParams("remarkImage", "")

                .build(), new DmeycBaseSubscriber<PublishActionAfterBean>() {
            @Override
            public void onSuccess(PublishActionAfterBean bean) {
                ToastUtil.show(bean.getMsg());
                finish();
            }

        });
    }
}
