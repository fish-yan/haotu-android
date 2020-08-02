package com.dmeyc.dmestoreyfm.wedgit;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;

import java.util.ArrayList;

public class PopupMenu extends PopupWindow implements OnClickListener {

	public Activity activity;
	private View popView;

//	private View v_item1;
//	private View v_item2;
//	private View v_item3,v_item4,v_item5,v_item6,v_item7,v_item8,v_item9;

	private OnItemClickListener onItemClickListener;
       private ListView lv_type;
//	private TextView tv_about_us,tv_check_update,tv_feedback,tv_feedback4,tv_feedback5,tv_feedback6,tv_feedback7,tv_feedback8,tv_feedback9;

	/**
	 *
	 * @author ywl5320 枚举，用于区分选择了哪个选项
	 */
	public enum MENUITEM {
		ITEM1, ITEM2, ITEM3,ITEM4, ITEM5, ITEM6,ITEM7, ITEM8, ITEM9,
	}
	private  ArrayList <String>  tabs;

	public PopupMenu( Activity activity , final ArrayList<String> tabs) {
		super(activity);
		this.activity = activity;
		this.tabs = tabs;
		LayoutInflater inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		popView = inflater.inflate(R.layout.popup_menu, null);// 加载菜单布局文件
		this.setContentView(popView);// 把布局文件添加到popupwindow中
		this.setWidth(dip2px(activity, 70));// 设置菜单的宽度（需要和菜单于右边距的距离搭配，可以自己调到合适的位置）
//		this.setHeight(dip2px(activity, 230));
		this.setHeight(dip2px(activity, 230));
		this.setFocusable(true);// 获取焦点
		this.setTouchable(true); // 设置PopupWindow可触摸
		this.setOutsideTouchable(true); // 设置非PopupWindow区域可触摸
		ColorDrawable dw = new ColorDrawable(0x00000000);
		this.setBackgroundDrawable(dw);


		lv_type=popView.findViewById(R.id.lv_type);
		lv_type.setAdapter(new TypeAdapter());
		lv_type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				if (onItemClickListener != null) {
					onItemClickListener.onClick(tabs.get(i),i);
				}
			}
		});

	}


	/**
	 * 设置显示的位置
	 *
	 * @param resourId
	 *            这里的x,y值自己调整可以
	 */
	public void showLocation(int resourId) {
		showAsDropDown(activity.findViewById(resourId), dip2px(activity, -80),
				dip2px(activity, 10));
	}

	public void showComminLocation(int resourId) {
		showAsDropDown(activity.findViewById(resourId), dip2px(activity, 80),
				dip2px(activity, 10));
	}
	@Override
	public void onClick(View v) {

	}

	// dip转换为px
	public int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	// 点击监听接口
	public interface OnItemClickListener {
		void onClick(MENUITEM item, String str);
		void onClick( String str,int code);
	}

	// 设置监听
	public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
	}


	public  class TypeHolder{
		TextView tv_typeitem;
		public TypeHolder(View view){
			tv_typeitem=view.findViewById(R.id.tv_typeitem);
		}
	}

	public class TypeAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return tabs.size();
		}

		@Override
		public Object getItem(int i) {
			return null;
		}

		@Override
		public long getItemId(int i) {
			return 0;
		}
		TypeHolder typeHolder;
		@Override
		public View getView(int i, View view, ViewGroup viewGroup) {
			if(view==null){
				view=activity.getLayoutInflater().inflate(R.layout.pop_typeitem, null);
				typeHolder=new TypeHolder(view);
				view.setTag(typeHolder);
			}else {
				typeHolder=(TypeHolder) view.getTag();
			}
			typeHolder.tv_typeitem.setText(tabs.get(i));
			return view;
		}
	}
}
