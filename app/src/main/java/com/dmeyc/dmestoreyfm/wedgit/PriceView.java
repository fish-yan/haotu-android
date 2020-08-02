package com.dmeyc.dmestoreyfm.wedgit;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jockie on 2017/12/7
 * Email:jockie911@gmail.com
 */

public class PriceView extends LinearLayout {

    @Bind(R.id.tv_sign)
    TextView tvSign;
    @Bind(R.id.tv_price)
    TextView tvPrice;
    @Bind(R.id.tv_desc)
    TextView tvdesc;

    private static final float DEFAULT_PRICE_TEXTSIZE = 12;
    private static final float DEFAULT_SING_TEXTSIZE = 12;
    private static final float DEFAULT_SPACE = 0;
    private static final int DEFAULT_PRICE_COLOR = 0xFFFD7E18;
    private static final int DEFAULT_SIGN_COLOR = 0xFFFD7E18;
    private int mPriceColor;
    private int mSignColor;
    private float mPriceTextSize = DEFAULT_PRICE_TEXTSIZE;
    private float mSignTextSize = DEFAULT_SING_TEXTSIZE;
    private float mSpace;

    public PriceView(Context context) {
        super(context);
    }

    public PriceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public PriceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }
    private void init(AttributeSet attrs) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.price_view, this, true);
        ButterKnife.bind(view);

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.priceview);
        mPriceColor = typedArray.getColor(R.styleable.priceview_price_color, DEFAULT_PRICE_COLOR);
        mSignColor = typedArray.getColor(R.styleable.priceview_sign_color, DEFAULT_SIGN_COLOR);
        if(typedArray.hasValue(R.styleable.priceview_price_textsize)){
            mPriceTextSize = typedArray.getDimension(R.styleable.priceview_price_textsize, DEFAULT_PRICE_TEXTSIZE);
            mPriceTextSize = px2sp(mPriceTextSize);
        }
        if(typedArray.hasValue(R.styleable.priceview_sign_textsize)){
            mSignTextSize =  typedArray.getDimension(R.styleable.priceview_sign_textsize, DEFAULT_SING_TEXTSIZE);
            mSignTextSize = px2sp(mSignTextSize);
        }
        if(typedArray.hasValue(R.styleable.priceview_space)){
            mSpace = typedArray.getDimension(R.styleable.priceview_space, DEFAULT_SPACE);
            mSpace = px2sp(mSpace);
        }
        if(typedArray.hasValue(R.styleable.priceview_isShow)){
            boolean isShow = typedArray.getBoolean(R.styleable.priceview_isShow, false);
            if(isShow){
                tvdesc.setVisibility(VISIBLE);
                tvdesc.setTextColor(mPriceColor);
                tvdesc.setTextSize(mPriceTextSize);
            }
        }
        typedArray.recycle();

//        tvSign.setTextColor(mSignColor);
//        tvSign.setTextSize(mSignTextSize);
        tvPrice.setTextSize(mPriceTextSize);
        tvPrice.setTextColor(mPriceColor);
        tvSign.setTextSize(mPriceTextSize);
        tvSign.setTextColor(mPriceColor);
        if(mSpace != 0){
            LinearLayout.LayoutParams layoutParams = (LayoutParams) tvPrice.getLayoutParams();
            layoutParams.setMargins((int) mSpace,0,0,0);
            tvPrice.setLayoutParams(layoutParams);
        }
    }

    public void setPrice(Object price){
        if(price instanceof Integer || price instanceof Double){
            tvPrice.setText(String.valueOf(price));
        }else if(price instanceof String){
            tvPrice.setText((String)price);
        }
    }

    public void setPriceColor(int color){
        tvPrice.setTextColor(color);
    }

    public int px2sp(float pxValue) {
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public String getPrice() {
        return tvPrice.getText().toString();
    }
}
