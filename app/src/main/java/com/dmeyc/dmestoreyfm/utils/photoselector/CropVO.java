package com.dmeyc.dmestoreyfm.utils.photoselector;


import java.io.Serializable;

/**
 * 裁剪属性类
 * 
 * @author jing
 * 
 */
public class CropVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CropVO(boolean isMoveMode, float fixWid, float ratio) {
		this.isMoveMode = isMoveMode;
		this.fixWid = fixWid;
		this.ratio = ratio;
	}

	public CropVO() {
	}

	/***
	 * 移动模式，只能移动不能缩放
	 */
	public boolean isMoveMode;
	public float fixWid;
	public float fixHei;
	public float ratio;//高宽的比例
}
