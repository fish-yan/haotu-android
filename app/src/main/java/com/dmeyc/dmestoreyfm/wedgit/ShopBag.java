package com.dmeyc.dmestoreyfm.wedgit;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.common.CommonBean;
import com.dmeyc.dmestoreyfm.bean.event.BagEvent;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.ui.ShopCarActivity;
import com.dmeyc.dmestoreyfm.utils.Util;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jockie on 2017/12/7
 * Email:jockie911@gmail.com
 */

public class ShopBag extends RelativeLayout {

    private static final int BAG_BLACK = R.drawable.shaopcar;
    private static final int BAG_WHITE = R.drawable.icon_shoppingbag_white;
    @Bind(R.id.iv_dot)
    ImageView ivDot;
    @Bind(R.id.iv_bag)
    ImageView ivBag;

    public ShopBag(Context context) {
        super(context);
        init();
    }

    public ShopBag(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.bag_shop, this, true);
        ButterKnife.bind(view);
        EventBus.getDefault().register(this);
        requestCount();
    }

    @OnClick({R.id.rel_bag})
    public void onClick(View v){
        if(v.getId() == R.id.rel_bag){
//            if(Util.checkLoginStatus(getContext())){
//                Intent intent = new Intent(getContext(), ShopCarActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                getContext().startActivity(intent);
//            }
        }
    }

    /**
     * change bag resource
     * @param drawableResId
     */
    public void setBagImage(int drawableResId){
        ivBag.setImageResource(drawableResId == BAG_BLACK ? BAG_BLACK : BAG_WHITE);
    }

    /**
     * 购物车是否有数据
     * @param visibility
     */
    public void setDotVisibility(boolean visibility){
        ivDot.setVisibility(visibility ? VISIBLE : INVISIBLE);
    }

    private void requestCount(){
        if(!Util.isLogin())
            return;
        RestClient.getNovate(getContext()).get(Constant.API.CAR_COUNT, new ParamMap.Build().build(), new DmeycBaseSubscriber<CommonBean>(getContext()) {
            @Override
            public void onSuccess(CommonBean bean) {
                Object data = bean.getData();
                String data1 = String.valueOf(data);
                setDotVisibility(!TextUtils.equals(data1,"0.0"));
            }
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void resetBagStatus(BagEvent event) {
        requestCount();
    }
}
