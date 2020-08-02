package com.dmeyc.dmestoreyfm.utils.photoselector;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;



public class KendalGridView extends GridView {

	public KendalGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	
	public KendalGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	
	public KendalGridView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);

		int count=this.getChildCount();
		for(int i=0;i<count;i++)
		{
			View v=this.getChildAt(i);
			LayoutParams lp=(LayoutParams) v.getLayoutParams();
			//LogUtils.i("onLayout", "i="+i+",w="+v.getMeasuredWidth()+",h="+v.getMeasuredHeight());
			lp.height=lp.width=v.getMeasuredWidth();
			//v.setLayoutParams(lp);			
		}
	}
	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
}
