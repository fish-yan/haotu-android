package com.dmeyc.dmestoreyfm.ui;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseActivity;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.bean.ProjectTypeBean;
import com.dmeyc.dmestoreyfm.bean.YFMUserBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;
import com.dmeyc.dmestoreyfm.wedgit.PopupMenu;
import com.tamic.novate.Throwable;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class BClientChoiceProjectActivity extends BaseActivity {

    @Bind(R.id.lv_projectlist)
    ListView lv_projectlist;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_bclientchoiceproject;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {

        getProjectTyoe();
    }

    ArrayList<String> ar=new ArrayList<>();
    private void getProjectTyoe() {
        ar.clear();
        RestClient.getYfmNovate(this).post(Constant.API.YFM_PROJECTLIST, new ParamMap.Build()
                .addParams("key","PROJECT_TYPE_COMMERCIAL")
                .addParams("user_token",SPUtils.getStringData(Constant.Config.TOKEN))
                .build(), new DmeycBaseSubscriber<ProjectTypeBean>() {
            @Override
            public void onSuccess(final ProjectTypeBean bean) {
//                ToastUtil.show(bean.getMsg());


                lv_projectlist.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return bean.getData().size();
                    }

                    @Override
                    public Object getItem(int i) {
                        return null;
                    }

                    @Override
                    public long getItemId(int i) {
                        return 0;
                    }
                    ProjectViewHolder projectViewHolder;
                    @Override
                    public View getView(final int i, View view, ViewGroup viewGroup) {
                        if(view==null){
                            view=getLayoutInflater().inflate(R.layout.adapter_bclientproject,null);
                            projectViewHolder=new ProjectViewHolder(view);
                            view.setTag(projectViewHolder);
                        }else {
                            projectViewHolder=(ProjectViewHolder)view.getTag();
                        }
                        projectViewHolder.et_name.setText(bean.getData().get(i).getItemName());
                        projectViewHolder.et_name.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent=new Intent();
                                intent.putExtra("projectname",bean.getData().get(i).getItemName());
                                intent.putExtra("projectid",bean.getData().get(i).getItemCode());
                                setResult(212,intent);
                                finish();
                            }
                        });
                        return view;
                    }
                });

            }
            @Override
            public void onError(Throwable e) {

            }
        });

    }
public class ProjectViewHolder{
        private TextView et_name;
        public ProjectViewHolder(View view){
            et_name=(TextView) view.findViewById(R.id.et_name);
        }
  }
}
