package com.dmeyc.dmestoreyfm.fragment;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.utils.GlideUtil;

import butterknife.Bind;

@SuppressLint("ValidFragment")
public class EquipInfromationFragment extends BaseFragment {
@Bind(R.id.tv_bandname)
    TextView tv_bandname;
@Bind(R.id.iv_ballurl)
    ImageView iv_ballurl;
    String bandname,bandurl;
    @SuppressLint("ValidFragment")
    public EquipInfromationFragment(){
//        this.bandurl=bandurl;
//        this.bandname=bandname;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_qquipinfro;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initData(View view) {

    }

    public void setdata(String bandname,String bandurl){
        tv_bandname.setText(bandname);
        GlideUtil.loadImage(getActivity(),bandurl,iv_ballurl);
    }
}
