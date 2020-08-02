package com.dmeyc.dmestoreyfm.utils;

import com.dmeyc.dmestoreyfm.base.BaseApp;

public class DensityUtil {
	private static float scale;

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(float dpValue) {
		if (scale == 0)
			scale = BaseApp.getContext().getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(float pxValue) {
		if (scale == 0)
			scale = BaseApp.getContext().getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
}
