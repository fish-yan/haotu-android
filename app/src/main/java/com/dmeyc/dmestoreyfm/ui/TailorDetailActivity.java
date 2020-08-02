package com.dmeyc.dmestoreyfm.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.base.BasePresenter;
import com.dmeyc.dmestoreyfm.base.BaseTabActivity;
import com.dmeyc.dmestoreyfm.bean.TailorListBean;
import com.dmeyc.dmestoreyfm.bean.TailorSuccessBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.fragment.TailorDetailFragment;
import com.dmeyc.dmestoreyfm.fragment.TailorPictureFragment;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.DensityUtil;
import com.dmeyc.dmestoreyfm.utils.DimenUtil;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.Util;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TailorDetailActivity extends BaseTabActivity {

    public static final String SAVE = "save";
    @Bind(R.id.tv_save)
    TextView tvSave;
    private boolean isShowSave;//
    private TailorListBean.DataBean item;

    @Override
    protected int getLayoutRes() {
        return R.layout.ac_tailor_detail;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        super.initData();
        isShowSave = getIntent().getBooleanExtra(SAVE, false);
        tvSave.setVisibility(isShowSave ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void getFragmentLists(List<BaseFragment> mFragmentLists) {
        item = (TailorListBean.DataBean) getIntent().getSerializableExtra(Constant.Config.ITEM);
        mFragmentLists.add(new TailorDetailFragment(item));
        mFragmentLists.add(new TailorPictureFragment(item));
//        mFragmentLists.add(new TailorSizeFragment(item));
    }

    @Override
    protected void getTitleList(List<String> mTitleLists) {
        mTitleLists.add("详情尺寸");
        mTitleLists.add("尺码详解");
//        mTitleLists.add("号型匹配");
    }

    @Override
    protected void reSetTabBlockWidth() {
        Util.reflex(mTabLayout, DensityUtil.dip2px(15));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_save)
    public void onViewClicked() {
        final Dialog dialog = new Dialog(TailorDetailActivity.this, R.style.DialogStyle);

        View view = (TailorDetailActivity.this).getLayoutInflater().inflate(R.layout.dialog_tailor_rename, null);
        final EditText ed_save = view.findViewById(R.id.ed_save);
        TextView text_cancel = view.findViewById(R.id.text_cancel);
        TextView text_sure = view.findViewById(R.id.text_sure);
        text_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        text_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ed_save.getText().toString().equals("")) {
                    Toast.makeText(TailorDetailActivity.this, "输入量身记录名称", Toast.LENGTH_SHORT).show();
                    return;
                }
                HashMap<String, Object> map = new HashMap<>();
                map.put("id", SPUtils.getStringData(Constant.Config.USER_ID, ""));
                map.put("memo", ed_save.getText().toString());
                map.put("ankle", item.getAnkle());
                map.put("arm", item.getArm());
                map.put("bust", item.getBust());
                map.put("downSide", item.getDownSide());
                map.put("shoulder", item.getShoulder());
                map.put("wrist", item.getWrist());
                map.put("waistline", item.getWaistline());
                map.put("hipline", item.getHipline());
                map.put("pHeight", item.getPheight());
                map.put("pWeight", item.getPweight());
                RestClient.getNovate(TailorDetailActivity.this).get(Constant.API.SAVE_MEASURE_INFO, map, new DmeycBaseSubscriber<TailorSuccessBean>() {
                    @Override
                    public void onSuccess(TailorSuccessBean bean) {
                         if(bean.getCode() == 200){
                             Toast.makeText(TailorDetailActivity.this,bean.getMsg(),Toast.LENGTH_SHORT).show();
                             finish();
                         }
                    }
                });
            }
        });


        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(view);
//        dialog.setOnKeyListener(this);
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = DimenUtil.getScreenWidth();
            window.setAttributes(lp);
        }
        dialog.show();
    }
}
