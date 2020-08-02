package com.dmeyc.dmestoreyfm.wedgit;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;


/**
 * Created by jockie on 2016/4/5.
 */
public class AutoGridView extends GridView{

        public AutoGridView(Context context) {
            super(context);
        }

        public AutoGridView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public AutoGridView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);
        }
}
