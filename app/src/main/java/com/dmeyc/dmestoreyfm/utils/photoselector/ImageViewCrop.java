package com.dmeyc.dmestoreyfm.utils.photoselector;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.dmeyc.dmestoreyfm.utils.DimenUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


public class ImageViewCrop extends AppCompatImageView {

	//private final static int PRESS_LB = 0;//表示左下角矩形框
	//private final static int PRESS_LT = 1;//表示左上角矩形框
	//private final static int PRESS_RB = 2;//表示右下角矩形框
	//private final static int PRESS_RT = 3;//表示右上角矩形框
	
	private final static int LEFT_TOP_CORNER = 0;//左上角
	private final static int TOP_CENTER = 1;//表示上中间
	private final static int RIGHT_TOP_CORNER = 2;//表示右下角矩形框
	private final static int LEFT_CENTER = 3;
	private final static int LEFT_BOTTOM_CORNER = 4;//表示左下角矩形框
	private final static int BOTTOM_CENTER = 5;
	private final static int RIGHT_BOTTOM_CORNER = 6;//表示右下角矩形框
	private final static int RIGHT_CENTER = 7;//表示右上角矩形框
	
	private final static int widSmallRec = 10;//选择拖动放缩的矩形边
	//private final static int widInitChooseRec = 100;//选择拖动放缩的矩形边
	
	private int maxWid = 800;
	private int maxSize = 5*1024;
	private int minWid = 100;

	private Bitmap bitmapInit = null;				//原始图片
	private RectF src = null;					//经过比例转换后的裁剪区域
	private RectF dst = null;					//图片显示区域，也就是drawBitmap函数中的目标dst
	private RectF chooseArea = null;				//选择区域
	private Paint mPaint = null;				//画笔
	private Matrix matrix = null;				//矩阵
	
	private int mx = 0;							//存储触笔移动时，之前所在的触笔的x坐标
	private int my = 0;							//存储触笔移动时，之前所在的触笔的y坐标
	private boolean touchFlag = false;			//触笔是否在屏幕之上
	private boolean cutFlag = false;			//是否点击了menu上的裁剪按钮
	private int recFlag = -1;					//用来存储触笔点击了哪个小矩形框（改变选择区域大小的小矩形框）
	private boolean firstFlag = false;
	
	private RectF recLT = null;					//左上角的小矩形框
	private RectF recRT = null;					//右上角的小矩形框
	private RectF recLB = null;					//左下角的小矩形框
	private RectF recRB = null;					//右下角的小矩形框
	private static final int LEFT_AREA_ALPHA = 50 * 255 / 100;
	private RectF leftRectL = null;
	private RectF leftRectR = null;
	private RectF leftRectT = null;
	private RectF leftRectB = null;
	private Paint leftAreaPaint = null;
	
	private boolean isMove = false;
	private int screenWid;
	private int screenHei;
	private int statusHei;
	private int xStartCrop;
	private int xOverCrop;
	private int yStartCrop;
	private int yOverCrop;
	private Context mContext;
	
	public CropVO mCropVO;
	
	public ImageViewCrop(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		
		screenWid = DimenUtil.getScreenWidth();
		screenHei =  DimenUtil.getScreenHeight();
		if(screenWid > screenHei){
			screenWid = screenHei;
		}
		statusHei = DisplayUtils.getStatusHei((Activity) context);
		
		this.init();
	}
	
	public ImageViewCrop(Context context) {
		super(context);
		mContext = context;
		screenWid = DimenUtil.getScreenWidth();
		screenHei = DimenUtil.getScreenHeight();
		if(screenWid > screenHei){
			screenWid = screenHei;
		}
		statusHei = DisplayUtils.getStatusHei((Activity) context);
		this.init();
	}
	
	public void init(){
		cutFlag = true;
		
		recLT = new RectF();
		recLB = new RectF();
		recRT = new RectF();
		recRB = new RectF();
		dst = new RectF();
		mPaint = new Paint();
		mPaint.setColor(Color.WHITE);
		mPaint.setStyle(Style.STROKE);      //将画笔的风格改为空心
		chooseArea = new RectF();
		this.setPressRecLoc();
		src = null;
		firstFlag = true;

		//选择框之外的灰色区域，分成四个矩形框
		leftAreaPaint = new Paint();
		leftAreaPaint.setStyle(Style.FILL);
		leftAreaPaint.setAlpha(ImageViewCrop.LEFT_AREA_ALPHA);

		if(mCropVO == null){
			mCropVO = new CropVO();
			mCropVO.ratio = 1;
		}
		
	}
	
	public void setBitmap(final Bitmap bitmap){
		if(bitmap == null){
			Toast.makeText(getContext(), "图片损坏!", Toast.LENGTH_SHORT).show();
			return;
		}
		if(getHeight() == 0){
			Handler handler = getHandler();
			if(handler == null){
				handler = new Handler();
			}
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					setBitmap(bitmap);
				}
			},100);
			return;
		}
		//bitmap
		BitmapDrawable bd = new BitmapDrawable(bitmap);
		
		src = new RectF(0,0,bd.getIntrinsicWidth(),bd.getIntrinsicHeight());
		this.bitmapInit = bitmap;//bitmap.copy(Config.ARGB_8888, true);
		
		this.setImageBitmap(bitmapInit);
		
		src = new RectF(0,0,bitmapInit.getWidth(),bitmapInit.getHeight());
		
		leftRectB = new RectF();
		leftRectL = new RectF();
		leftRectR = new RectF();
		leftRectT = new RectF();

		if(((float)getWidth())/getHeight() > ((float)bitmap.getWidth())/bitmap.getHeight()){
			//竖图片
			int newWid = (int)(getHeight() * ((float)bitmap.getWidth())/bitmap.getHeight());
			xStartCrop = (getWidth() - newWid)/2;
			xOverCrop = xStartCrop + newWid;

			yStartCrop = 0;
			yOverCrop = getHeight();
		}else if(((float)getWidth())/getHeight() < ((float)bitmap.getWidth())/bitmap.getHeight()){
			//横图片
			int newHei = (int)(getWidth() * ((float)bitmap.getHeight())/bitmap.getWidth());
			yStartCrop = (getHeight() - newHei)/2;
			yOverCrop = yStartCrop + newHei;

			xStartCrop = 0;
			xOverCrop = getWidth();
		}else{
			yStartCrop = 0;
			yOverCrop = getHeight();

			xStartCrop = 0;
			xOverCrop = getWidth();
		}
/*		if(((float)MyApplication.getInstance().getScreenWidth())/MyApplication.getInstance().getScreenHeight() > ((float)bitmap.getWidth())/bitmap.getHeight()){
			//竖图片
			int newWid = (int)(MyApplication.getInstance().getScreenHeight() * ((float)bitmap.getWidth())/bitmap.getHeight());
			xStartCrop = (MyApplication.getInstance().getScreenHeight() - newWid)/2;
			xOverCrop = xStartCrop + newWid;

			yStartCrop = 0;
			yOverCrop = MyApplication.getInstance().getScreenHeight();
		}else if(((float)MyApplication.getInstance().getScreenWidth())/MyApplication.getInstance().getScreenHeight() < ((float)bitmap.getWidth())/bitmap.getHeight()){
			//横图片
			int newHei = (int)(MyApplication.getInstance().getScreenWidth() * ((float)bitmap.getHeight())/bitmap.getWidth());
			yStartCrop = (MyApplication.getInstance().getScreenHeight() - newHei)/2;
			yOverCrop = xStartCrop + newHei;

			xStartCrop = 0;
			xOverCrop = MyApplication.getInstance().getScreenWidth();
		}else{
			yStartCrop = 0;
			yOverCrop = MyApplication.getInstance().getScreenHeight();

			xStartCrop = 0;
			xOverCrop = MyApplication.getInstance().getScreenWidth();
		}*/
	}
	
	
	
	/***
	 * 通过ImageMatrix获取到图片的边界点得到最多的边界
	 */
	public void imageScale(){
		matrix = this.getImageMatrix();
		matrix.mapRect(dst, src);
		//dst = new RectF(0, 0, this.getWidth(), this.getHeight());
		//int padding = DisplayUtils.dip2px(mContext, 10);
		//dst.set(dst.left+padding,dst.top+padding - 25 ,dst.right+padding,dst.bottom+padding - 25);
		
		//int statusHei = getStatusHei((Activity)mContext);
		//dst.set(0,0 ,screenWid,this.getHeight() - statusHei);
		dst.set(xStartCrop,yStartCrop ,xOverCrop,yOverCrop);
		//dst.set(,dst.top+padding - 25 ,dst.right+padding,dst.bottom+padding - 25);
		//ChooseArea = new RectF(dst);
		//int left = DisplayUtils.dip2px(mContext, 20);
		//if(mCropVO != null && mCropVO.isMoveMode){

			//System.out.println("mCropVO.ratio");
			/*float paddingHei = (screenHei - hei - statusHei) / 2;
			float paddingWid = (screenWid - mCropVO.fixWid) / 2;*/
			/*float paddingHei = (getHeight() - hei) / 2;
			float paddingWid = (getWidth() - mCropVO.fixWid) / 2;
		    chooseArea = new RectF(paddingWid, paddingHei, mCropVO.fixWid + paddingWid, getHeight() - paddingHei);*/
            float widCrop = xOverCrop - xStartCrop;
			float heiCrop = yOverCrop - yStartCrop;
			if(heiCrop >= widCrop * mCropVO.ratio){//竖图片(hei剩余)
				mCropVO.fixWid = xOverCrop - xStartCrop;
				float hei = mCropVO.ratio*mCropVO.fixWid;
				chooseArea = new RectF(xStartCrop, (yOverCrop - yStartCrop - hei) / 2  +  yStartCrop, xOverCrop, (yOverCrop - yStartCrop - hei) / 2  +  yStartCrop + hei);
			}else if(heiCrop < widCrop * mCropVO.ratio){//横图片(wid剩余)
				mCropVO.fixHei = yOverCrop - yStartCrop;
				float widInit = mCropVO.fixHei/mCropVO.ratio;
				chooseArea = new RectF(((xOverCrop - xStartCrop) - widInit)/2 + xStartCrop, yStartCrop, ((xOverCrop - xStartCrop)  - widInit)/2 + xStartCrop + widInit, yOverCrop);
			}/*else {
				chooseArea = new RectF((xOverCrop - xStartCrop) / 8, yStartCrop, (xOverCrop - xStartCrop) * 7 / 8, screenWid);
			}*/

		/*}else{
		    //chooseArea = new RectF(screenWid/8, screenWid*2/8, screenWid*7/8, screenWid);
			if(yOverCrop - yStartCrop >= xOverCrop - xStartCrop){//竖图片
				chooseArea = new RectF(xStartCrop, (yOverCrop - yStartCrop - (xOverCrop - xStartCrop))/2 + yStartCrop, xOverCrop, (yOverCrop - yStartCrop - (xOverCrop - xStartCrop))/2 + yStartCrop + xOverCrop - xStartCrop);
			}else if(yOverCrop - yStartCrop < xOverCrop - xStartCrop){//横图片
				chooseArea = new RectF((xOverCrop - xStartCrop - (yOverCrop - yStartCrop))/2 + xStartCrop, yStartCrop, (xOverCrop - xStartCrop - (yOverCrop - yStartCrop))/2 + xStartCrop + yOverCrop - yStartCrop , yOverCrop);
			}*//*else {
				chooseArea = new RectF((xOverCrop - xStartCrop) / 8, yStartCrop, (xOverCrop - xStartCrop) * 7 / 8, screenWid);
			}*//*
		}*/
		this.setPressRecLoc();
	}
	
	//裁剪出选择区域里的图片
	//之前要做比例转换，因为，你选择的区域是针对比例转换后的图片
	//所以要使用ChooseArea的数值做比例转换，然后，使用这些数值重新设置rec的大小
	/*public Bitmap getSubsetBitmap(){
		float ratioWidth = bitmapInit.getWidth()/(float)(dst.right-dst.left);
		float ratioHeight = bitmapInit.getHeight()/(float)(dst.bottom - dst.top);
		int left = (int)((ChooseArea.left - dst.left) * ratioWidth);
		int right = (int)(left + (ChooseArea.right - ChooseArea.left) * ratioWidth);
		int top = (int)((ChooseArea.top - dst.top) * ratioHeight);
		int bottom = (int)(top + (ChooseArea.bottom - ChooseArea.top) * ratioHeight);
		src = new RectF(left,top,right,bottom);
		firstFlag = true;
		set_LeftArea_Alpha();
		return Bitmap.createBitmap(bitmapInit, left, top, right-left, bottom-top);
	}*/
	
	/** 
     * 进行截取屏幕   
     * @param pActivity 
     * @return bitmap 
     */  
    public Bitmap takeScreenShot(Activity pActivity)
    {  
        Bitmap bitmap=null;
        View view= pActivity.getWindow().getDecorView();
        // 设置是否可以进行绘图缓存  
        view.setDrawingCacheEnabled(true);  
        // 如果绘图缓存无法，强制构建绘图缓存  
        view.buildDrawingCache();  
        // 返回这个缓存视图   
        bitmap=view.getDrawingCache();

        // 获取状态栏高度  
        Rect frame=new Rect();
        // 测量屏幕宽和高  
        view.getWindowVisibleDisplayFrame(frame);  
        int stautsHeight=frame.top;
        //LogUtils.d("jiangqq", "状态栏的高度为:"+stautsHeight);
          
        //int width=pActivity.getWindowManager().getDefaultDisplay().getWidth();  
        //int height=pActivity.getWindowManager().getDefaultDisplay().getHeight();  
        // 根据坐标点和需要的宽和高创建bitmap  
        //bitmap=Bitmap.createBitmap(bitmap, 0, stautsHeight, width, height-stautsHeight); 

        try{
        	if(bitmap != null && chooseArea!=null && chooseArea.width()>0){
             // bitmap=Bitmap.createBitmap(bitmap, (int)(chooseArea.left), (int)chooseArea.top + stautsHeight, (int)(chooseArea.width()), (int)(chooseArea.height()));
                //1px white edge
                bitmap= Bitmap.createBitmap(bitmap, (int)(chooseArea.left) + 1, (int)chooseArea.top + stautsHeight - 1, (int)(chooseArea.width()) - 1, (int)(chooseArea.height()) - 1);
        	}
        	bitmap = handleComprass(bitmap);
        }catch(OutOfMemoryError e){
        	bitmap = null;
            Log.i("OutOfMemoryError", e.getMessage() + "");
        }catch(IllegalArgumentException e){
        	bitmap = null;
			Log.i("IllegalArgumentExcepti", e.getMessage()+"");
        }
        //bitmap=Bitmap.createBitmap(bitmap, (int)(ChooseArea.left), (int)ChooseArea.top + stautsHeight, (int)(ChooseArea.width()), (int)(ChooseArea.height()));
        return bitmap;  
    }  
    
     private Bitmap handleComprass(Bitmap target) {
    	
    	if(target.getWidth() > maxWid){
    		return BitmapUtil.compressBmpByWid(target, maxWid, maxWid);
    	}else if(isNeedCompresssSize(target)){
    		return compressBmpBySize(target);
    	}else{
    		return target;
    	}

	}
    
     private boolean isNeedCompresssSize(Bitmap image) {
    	 ByteArrayOutputStream baos = new ByteArrayOutputStream();
 		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
 		if(baos.toByteArray().length / 1024 > maxSize){
 			return true;
 		}else{
 			return false;
 		}

	}
    /***
     * 将图片压缩到5m以下
     * @param image
     * @return
     */
    private Bitmap compressBmpBySize(Bitmap image) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int options = 100;
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		while (baos.toByteArray().length / 1024 > maxSize) { 
			baos.reset();
			options -= 10;
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
		return bitmap;
	}
    
   /* private int getStatusHei(Activity pActivity) {
    	
    	Rect rect = new Rect();
    	pActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);///取得整个视图部分,注意，如果你要设置标题样式，这个必须出现在标题样式之后，否则会出错
    	View v = pActivity.getWindow().findViewById(Window.ID_ANDROID_CONTENT);
    	////状态栏的高度，所以rect.height,rect.width分别是系统的高度的宽度
    	
         return v.getTop();
	}*/
	
	//获得ChooseArea对象
	public RectF getChooseArea(){
		return chooseArea;
	}
	
	//移动选择区域，选择区域是不能从图片区域里移出去的
	public void moveChooseArea(int move_x,int move_y){
		if(chooseArea.left + move_x >= dst.left && chooseArea.right + move_x <= dst.right
		&& chooseArea.top + move_y >= dst.top && chooseArea.bottom + move_y <= dst.bottom){
			chooseArea.set(chooseArea.left + move_x,chooseArea.top+move_y
					,chooseArea.right + move_x,chooseArea.bottom+move_y);
		}/*else if(mCropVO != null && mCropVO.isMoveMode){//移动上下
			if(chooseArea.top + move_y < dst.top){//上边越界
				return;
			}
			
			if(chooseArea.bottom + move_y > dst.bottom){//下边越界
				return;
			}
			chooseArea.set(chooseArea.left,chooseArea.top+move_y
					,chooseArea.right,chooseArea.bottom+move_y);
		}*/else{
			boolean isOutTop = false;
			boolean isOutBottom = false;
			boolean isOutLeft = false;
			boolean isOutRight = false;

			float left = 0;
			float right = 0;
			float top = 0;
			float bottom = 0;


			if(chooseArea.left + move_x < dst.left){//左边越界
				/*chooseArea.set(dst.left,chooseArea.top
						,chooseArea.right-chooseArea.left+dst.left,chooseArea.bottom);*/
				/*chooseArea.set(dst.left,chooseArea.top+move_y
						,chooseArea.right,chooseArea.bottom+move_y);*/
				left = dst.left;
				isOutLeft = true;
			}
			if(chooseArea.right + move_x > dst.right){//右边越界
				/*chooseArea.set(chooseArea.left+dst.right-chooseArea.right,chooseArea.top
						,dst.right,chooseArea.bottom);*/
				/*chooseArea.set(chooseArea.left ,chooseArea.top+move_y
						,dst.right,chooseArea.bottom+move_y);*/
				right = dst.right;
				isOutRight = true;
			}
			
			if(chooseArea.top + move_y < dst.top){//上边越界
				/*chooseArea.set(chooseArea.left,dst.top
						,chooseArea.right,chooseArea.bottom+dst.top-chooseArea.top);*/
				/*chooseArea.set(chooseArea.left,dst.top
						,chooseArea.right,chooseArea.bottom+dst.top-chooseArea.top);*/
				top = dst.top;
				isOutTop = true;
			}
			
			if(chooseArea.bottom + move_y > dst.bottom){//下边越界
				/*chooseArea.set(chooseArea.left,chooseArea.top+dst.bottom-chooseArea.bottom
						,chooseArea.right,dst.bottom);*/
				/*chooseArea.set(chooseArea.left + move_x,chooseArea.top
						,chooseArea.right + move_x,dst.bottom);*/
				isOutBottom = true;
			}

			if(isOutTop && !isOutBottom && move_y > 0 || !isOutTop && isOutBottom && move_y < 0 || !isOutTop && !isOutBottom){//上下走
				chooseArea.set(chooseArea.left ,chooseArea.top + move_y
						,chooseArea.right,chooseArea.bottom + move_y);
			}

			if(isOutLeft && !isOutRight && move_x > 0 || !isOutLeft && isOutRight && move_x < 0 || !isOutLeft && !isOutRight){//左右走
				chooseArea.set(chooseArea.left + move_x,chooseArea.top
						,chooseArea.right + move_x ,chooseArea.bottom);
			}

			/*if (isOutY && !isOutX){
				chooseArea.set(chooseArea.left + move_x,chooseArea.top
						,chooseArea.right + move_x,chooseArea.bottom);
			}else if (!isOutY && isOutX){
				chooseArea.set(chooseArea.left ,dst.top + move_y
						,chooseArea.right ,chooseArea.bottom + move_y);
			}else if (isOutY && isOutX){

			}*/
		}
		this.setPressRecLoc();
		//mPaint.setColor(Color.WHITE);
		this.invalidate();
	}
	
	public boolean onTouchEvent(MotionEvent event){
		//mPaint.setColor(Color.WHITE);
		
		//点击了裁剪按钮之后才会执行以下事件
    	if(event.getAction() == MotionEvent.ACTION_DOWN && cutFlag){
    		//判断触笔是否在裁剪区域内，也就是ChooseArea内
    		//如果是，整个区域随着鼠标移动而移动
    		mx = (int)event.getX();
			my = (int)event.getY();
    		if(this.judgeLocation(mx,my)){//是否在裁剪区域内
    			touchFlag = true;
    			//mPaint.setColor(Color.WHITE);
    			this.invalidate();
    			isMove = true;
    			return true;
    		}else{
    			//不在裁剪区域内，就判断触笔是否在改变区域大小的小矩形框之内
    			if(this.findPresseddst((int)event.getX(), (int)event.getY())){//是否在四角的小矩形内
    				isMove = false;
	    			touchFlag = true;
	    			//mPaint.setColor(Color.WHITE);
	    			return true;
    			}
    		}
    	}
    	
    	/*if(!isMove && mCropVO != null && mCropVO.isMoveMode){//移动模式
    		System.out.println("截断："+isMove+":mCropVO:"+mCropVO+":mCropVO.isMoveMode:"+mCropVO.isMoveMode);
    		return true;
    	}*/
    	
    	if(event.getAction() == MotionEvent.ACTION_MOVE && touchFlag){
    		//判断是否点击了哪个个小矩形框
    		if(!isMove&&this.isOutOfAreaAndDraw((int)event.getX(), (int)event.getY())){//点击在四角的小矩形内，进行处理
    			return true;
    		}
    		
    		//如果选择区域大小跟图像大小一样时，就不能移动
    		if(chooseArea.left == dst.left && chooseArea.top == dst.top &&
    		   chooseArea.right == dst.right && chooseArea.bottom == dst.bottom){
    		}else{
    			this.moveChooseArea((int)event.getX() - mx, (int)event.getY() - my);
    			mx = (int)event.getX();
    			my = (int)event.getY();
    		}
    	}
    	
    	//触笔移出屏幕时，将一些变量设回初值
    	if(event.getAction() == MotionEvent.ACTION_UP){
    		recFlag = -1;
    		this.invalidate();
    		touchFlag = false;
    	}
    	
    	return super.onTouchEvent(event);
    }
	
	
	
	/**
	 * 
	 * 判断是否要超出图片区域，这个函数会调用下面四个press打头的函数
	 * 这个函数也会重绘整个画布，也就是选择区域会随着鼠标的移动改变
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean isOutOfAreaAndDraw(int x,int y){
		/*switch(recFlag){
		case Crop_Canvas.PRESS_LB:
			this.pressLB(x - mx, y - my);
			break;
		case Crop_Canvas.PRESS_LT:
			this.pressLT(x - mx, y - my);
			break;
		case Crop_Canvas.PRESS_RB:
			this.pressRB(x - mx, y - my);
			break;
		case Crop_Canvas.PRESS_RT:
			this.pressRT(x - mx, y - my);
			break;
		default:return false;
		}*/
		
		switch (recFlag) {
		case LEFT_TOP_CORNER:
			pressLT(x - mx, y - my);
			break;
		case TOP_CENTER:
			//pressLT(x - mx, y - my);
			break;
		case RIGHT_TOP_CORNER:
			pressRT(x - mx, y - my);
			break;
		case RIGHT_CENTER:
			pressLT(x - mx, y - my);
			break;
		case RIGHT_BOTTOM_CORNER:
			pressRB(x - mx, y - my);
			break;
		case BOTTOM_CENTER:
			//pressLT(x - mx, y - my);
			break;
		case LEFT_BOTTOM_CORNER:
			pressLB(x - mx, y - my);
			break;
		case LEFT_CENTER:
			//pressLT(x - mx, y - my);
			break;
		
		default:
			break;
		}
		
		mx = x;
		my = y;
		this.invalidate();
		return true;
	}
	
	private boolean isInChooseRec(float x,float y) {
		if(x > chooseArea.left && x < chooseArea.right && y > chooseArea.top && y < chooseArea.bottom){
			return true;
		}else
		{
			return false;
		}

	}
	
	/***
	 * 
	 * 找到点击了哪个矩形框（改变选择区域大小的小矩形框）
	 * 这个是在MotionEvent.ACTION_DOWN这个动作时执行的
	 * 为了在MotionEvent.ACTION_MOVE的时候，知道应该移动哪个小矩形框
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean findPresseddst(int x,int y){
		boolean returnFlag = false;
		/*if(this.isInRect(x, y, recLB)){
			recFlag = Crop_Canvas.PRESS_LB;
			returnFlag = true;
		}else if(this.isInRect(x, y, recLT)){
			recFlag = Crop_Canvas.PRESS_LT;
			returnFlag = true;
		}else if(this.isInRect(x, y, recRB)){
			recFlag = Crop_Canvas.PRESS_RB;
			returnFlag = true;
		}else if(this.isInRect(x, y, recRT)){
			recFlag = Crop_Canvas.PRESS_RT;
			returnFlag = true;
		}*/
		float yCenter = (chooseArea.bottom - chooseArea.top)/2 +  chooseArea.top;
		float xCenter = (chooseArea.right - chooseArea.left)/2 +  chooseArea.left;
		
		if(y< yCenter ){
			if(x > xCenter){
				recFlag = RIGHT_TOP_CORNER;
			}else if(x == xCenter){
				recFlag = TOP_CENTER;
			}else{
				recFlag = LEFT_TOP_CORNER;
			}
		}else if(y > yCenter){
			if(x < xCenter){
				recFlag = LEFT_BOTTOM_CORNER;
			}else if(x == xCenter){
				recFlag = BOTTOM_CENTER;
			}else{
				recFlag = RIGHT_BOTTOM_CORNER;
			}
		}else if(y == yCenter){
			if(x < chooseArea.left){
				recFlag = LEFT_CENTER;
			}else{
				recFlag = RIGHT_CENTER;
			}
		}
		returnFlag = true;
		
		return returnFlag;
	}
	
	public boolean isInRect(int x,int y,RectF rect){
		if(x >= rect.left -100 && x <= rect.right + 100 && y > rect.top - 100 && y < rect.bottom + 100){
			return true;
		}
		return false;
	}
	
	//点击角落矩形框改变选择区域大小时，不能超过图片所在的区域
	//下面以press打头的四个函数就是判断是否超出图片区域
	//如果超出了，就移动不了
	//不超出按照触笔移动的距离来移动小矩形框
	
	//pressLB是当点击左下角小矩形框改变大小时是否超出图片区域
	//如果超出就将left与bottom的值设为图片区域的left和bottom
	private void pressLB(int x,int y){
		float rate = (float) Math.sqrt(x*x+y*y);
		
		float left = 0;
		float bottom = 0;
		if(x>=0&&y<=0){
			left = chooseArea.left + rate;
			bottom = chooseArea.bottom - rate * mCropVO.ratio;
		}else if(x<=0&&y>=0){
			if(chooseArea.left <= 0 || chooseArea.bottom >= dst.bottom){
				return;
			}
			left = chooseArea.left - rate;
			bottom = chooseArea.bottom + rate * mCropVO.ratio;
		}else{
			return;
		}
		
		/*float left;
		if(x>0){
			left = ChooseArea.left + rate;
		}else{
			 left = ChooseArea.left - rate;
		}*/
		float right = chooseArea.right;
		float top = chooseArea.top;
		
		/*float bottom;
		if(y>0){
			bottom = ChooseArea.bottom + rate;
		}else{
			bottom = ChooseArea.bottom - rate;
		}*/
		//float bottom = ChooseArea.bottom + y;
		if(left <= right - 30 && left >= dst.left && bottom <= dst.bottom && bottom >= top + 30){
				chooseArea.set(left,top,right,bottom);
		}else{
			if(left + x < dst.left){
				//left = dst.left;
				return;
			}
			
			if(bottom + y > dst.bottom){
				//bottom = dst.bottom;
				return;
			}
			
			if(chooseArea.left + x > chooseArea.right - 30){
				//left = chooseArea.right - 30;
				return;
			}
			
			if(chooseArea.bottom + y < chooseArea.top + 30){
				//bottom = chooseArea.top + 30;
				return;
			}
			chooseArea.set(left,top,right,bottom);
		}
		this.setPressRecLoc();
	}
	
	//pressLT是当点击左上角小矩形框改变大小时是否超出图片区域
	//如果超出就将left与top的值设为图片区域的left和top
	private void pressLT(int x,int y){
      float rate = (float) Math.sqrt(x*x+y*y);
		
		float left = 0;
		float top = 0;
		if(x>=0&&y>=0){
			left = chooseArea.left + rate ;
			top = chooseArea.top + rate * mCropVO.ratio;
		}else if(x<=0&&y<=0){
			if(chooseArea.left <= 0 || chooseArea.top <= 0){
				return;
			}
			left = chooseArea.left - rate;
			top = chooseArea.top - rate * mCropVO.ratio;
		}else{
			return;
		}
		
		//float left = ChooseArea.left + x;
		float right = chooseArea.right;
		//float top = ChooseArea.top + y;
		
		
		
		float bottom = chooseArea.bottom;
		if(left <= right - 30 && left >= dst.left && top <= bottom - 30 && top >= dst.top){
			chooseArea.set(left,top,right,bottom);
		}else{
			if(left < dst.left){
				//left = dst.left;
				return;
			}
			
			if(top < dst.top){
				//top = dst.top;
				return;
			}
			
			if(left > right - 30){
				//left = right - 30;
				return;
			}
			
			if(top > bottom - 30){
				//top = bottom - 30;
				return;
			}
			chooseArea.set(left,top,right,bottom);
		}
		this.setPressRecLoc();
	}
	
	//pressRT是当点击右上角小矩形框改变大小时是否超出图片区域
	//如果超出就将right与top的值设为图片区域的right和top
	private void pressRT(int x,int y){
		 float rate = (float) Math.sqrt(x*x+y*y);
		
		float right = 0;
		float top = 0;
		if(x>=0&&y<=0){
			if(chooseArea.top <= 0 || chooseArea.right >= dst.right){
				return;
			}
			right = chooseArea.right + rate;
			top = chooseArea.top - rate * mCropVO.ratio;
		}else if(x<=0&&y>=0){
			right = chooseArea.right - rate;
			top = chooseArea.top + rate * mCropVO.ratio;
		}else{
			return;
		}
		
		float left = chooseArea.left;
		//float right = ChooseArea.right + x;
		//float top = ChooseArea.top + y;
		float bottom = chooseArea.bottom;
		
		if(right <= dst.right && right >= left + 30 && top <= bottom - 30 && top >= dst.top){
			chooseArea.set(left,top,right,bottom);
		}else{
			if(right > dst.right){
				//right = dst.right;
				return;
			}
			
			if(top < dst.top){
				//top = dst.top;
				return;
			}
			
			if(right < left + 30){
				//right = left + 30;
				return;
			}
			
			if(top > bottom - 30){
				//top = bottom - 30;
				return;
			}
			chooseArea.set(left,top,right,bottom);
		}
		this.setPressRecLoc();
	}
	
	//pressRB是当点击右下角小矩形框改变大小时是否超出图片区域
	//如果超出就将right与bottom的值设为图片区域的right和bottom
	private void pressRB(int x,int y){
    float rate = (float) Math.sqrt(x*x+y*y);
		
		float right = 0;
		float bottom = 0;
		if(x<=0&&y<=0){
			right = chooseArea.right - rate;
			bottom = chooseArea.bottom - rate * mCropVO.ratio;
		}else if(x>=0&&y>=0){
			if(chooseArea.right >= dst.right || chooseArea.bottom >= dst.bottom){
				return;
			}
			right = chooseArea.right + rate;
			bottom = chooseArea.bottom + rate * mCropVO.ratio;
		}else{
			return;
		}
		
		float left = chooseArea.left;
		//float right = ChooseArea.right + x;
		float top = chooseArea.top;
		//float bottom = ChooseArea.bottom + y;
		
		if(right<= dst.right && right >= left + 30 && bottom <= dst.bottom && bottom >= top + 30){
			chooseArea.set(left,top,right,bottom);
		}else{
			if(right > dst.right){
				//right = dst.right;
				return;
			}
			
			if(bottom > dst.bottom){
				//bottom = dst.bottom;
				return;
			}
			
			if(right < left + 30){
				//right = left + 30;
				return;
			}
			
			if(bottom < top + 30){
				//bottom = top + 30;
				return;
			}
			chooseArea.set(left,top,right,bottom);
		}
		this.setPressRecLoc();
	}
	
	//每次改变选择区域矩形的大小或者移动，各角落上的小矩形也要改变它的Location
	private void setPressRecLoc(){
		recLT.set(chooseArea.left-widSmallRec,chooseArea.top-widSmallRec , chooseArea.left+widSmallRec, chooseArea.top+widSmallRec);
		recLB.set(chooseArea.left-widSmallRec,chooseArea.bottom-widSmallRec , chooseArea.left+widSmallRec, chooseArea.bottom+widSmallRec);
		recRT.set(chooseArea.right-widSmallRec,chooseArea.top-widSmallRec , chooseArea.right+widSmallRec, chooseArea.top+widSmallRec);
		recRB.set(chooseArea.right-widSmallRec,chooseArea.bottom-widSmallRec, chooseArea.right+widSmallRec, chooseArea.bottom+widSmallRec);
	}
	
	//判断触笔是否在选择区域内
	public boolean judgeLocation(float x,float y){
    	float start_x = this.getChooseArea().left;
    	float start_y = this.getChooseArea().top;
    	float last_x = this.getChooseArea().right;
    	float last_y = this.getChooseArea().bottom;
    	if(x > start_x+10 && x < last_x-10 && y > start_y+10 && y < last_y-10){
    		return true;
    	}
    	return false;
    }

	@Override
	public void onDraw(Canvas canvas){
		super.onDraw(canvas);
		if(bitmapInit == null){
			return;
		}
		if(firstFlag){
			
			this.imageScale();
			set_LeftArea_Alpha();
			firstFlag = false;
			 //mPaint.setColor(Color.WHITE);
		}else{
			set_LeftArea_Alpha();
		}
		mPaint.setStyle(Style.STROKE);
		canvas.drawRect(chooseArea, mPaint);
		//mPaint.setStyle(Style.FILL_AND_STROKE);
		//mPaint.setColor(Color.GRAY);
		//canvas.drawRect(recLT, mPaint);
		//canvas.drawRect(recLB, mPaint);
		//canvas.drawRect(recRT, mPaint);
		//canvas.drawRect(recRB, mPaint);
		
		canvas.drawRect(leftRectL, leftAreaPaint);
		canvas.drawRect(leftRectR, leftAreaPaint);
		canvas.drawRect(leftRectT, leftAreaPaint);
		canvas.drawRect(leftRectB, leftAreaPaint);
		
	}
	
	public void set_LeftArea_Alpha(){
		leftRectL.set(dst.left, dst.top, chooseArea.left, dst.bottom);
		leftRectR.set(chooseArea.right,dst.top,dst.right,dst.bottom);
		leftRectT.set(chooseArea.left, dst.top, chooseArea.right, chooseArea.top);
		leftRectB.set(chooseArea.left,chooseArea.bottom,chooseArea.right,dst.bottom);
	} 
	
	
   
    
}
