package com.dmeyc.dmestoreyfm.utils.photoselector;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;


import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.utils.DimenUtil;

import java.util.ArrayList;
import java.util.List;


public class CustomDialogTools {

	/**
	 * 显示定制微信风格的选项对话框
	 * 
	 * @param context
	 * @param items
	 * @param titleId
	 * @param style
	 * @return
	 */
	public static Dialog createCustomDialog(Context context,
                                            List<CustomDialogItem> items, int titleId, int style) {
		LinearLayout dialogView = (LinearLayout) LayoutInflater.from(context)
				.inflate(R.layout.custom_dialog_layout, null);
		final Dialog customDialog = new Dialog(context, style);
		LinearLayout itemView;
		TextView textView;

		// 设置标题
		if (titleId > 0) {
			itemView = (LinearLayout) LayoutInflater.from(context).inflate(
					R.layout.custom_dialog_title, null);
			textView = (TextView) itemView.findViewById(R.id.popup_text);
			textView.setText(titleId);
			dialogView.addView(itemView);
		}
		for (CustomDialogItem item : items) {
			itemView = (LinearLayout) LayoutInflater.from(context).inflate(
					item.viewId, null);
			textView = (TextView) itemView.findViewById(R.id.popup_text);
			textView.setText(item.textId);
			textView.setOnClickListener(new CustomDialogItemClickListener(item,
					customDialog));// 设置监听
			dialogView.addView(itemView);
		}

		// 设置位置
		WindowManager.LayoutParams localLayoutParams = customDialog.getWindow()
				.getAttributes();
		localLayoutParams.x = 0;
		localLayoutParams.y = -1000;
		localLayoutParams.gravity = 80;
		dialogView.setMinimumWidth(10000);

		customDialog.onWindowAttributesChanged(localLayoutParams);
		customDialog.setCanceledOnTouchOutside(true);
		customDialog.setCancelable(true);
		customDialog.setContentView(dialogView);

		if (context instanceof Activity) {// 判断状态
			Activity activity = (Activity) context;
			if (!activity.isFinishing()) {
				customDialog.show();
			}
		}

		return customDialog;
	}

	/**
	 * 显示定制微信风格的选项对话框
	 * 
	 * @param context
	 * @param items
	 * @param title
	 * @param l
	 * @return
	 */
	public static Dialog createCustomDialog(Context context, String[] items, String title, OnClickListener l) {
		return createCustomDialog(context, items, null, title, l);
	}

	public static Dialog createCustomDialog(Context context, String[] items, int[] itemTags, String title, OnClickListener l) {
		LinearLayout dialogView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.custom_dialog_layout_menu, null);
		final Dialog customDialog = new Dialog(context, R.style.CustomDialog_Menu);
		RelativeLayout itemView;
		TextView textView;

		int i = 0;
		for (String item : items) {
			itemView = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.custom_dialog_item_new, null);
			textView = (TextView) itemView.findViewById(R.id.popup_text);
			textView.setText(item);
			if (itemTags == null || itemTags.length < items.length) {
				itemView.setTag(i++);
			} else {
				itemView.setTag(itemTags[i++]);
			}
			itemView.setTag(R.id.tag_first, customDialog);
			itemView.setOnClickListener(l);// 设置监听
			dialogView.addView(itemView);
		}

		// 设置位置
		WindowManager.LayoutParams localLayoutParams = customDialog.getWindow().getAttributes();

		WindowManager m = ((Activity) context).getWindowManager();
		Display d = m.getDefaultDisplay();

		dialogView.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));

		int height = dialogView.getMeasuredHeight();

		ScrollView scrView = new ScrollView(context);
		scrView.addView(dialogView);

		Rect frame = new Rect();
		customDialog.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		int statusBarHeight = frame.top;

		localLayoutParams.width = LayoutParams.MATCH_PARENT;
		localLayoutParams.height = Math.min(height, DimenUtil.getScreenHeight()-statusBarHeight);
		localLayoutParams.x = 0;
		localLayoutParams.y = d.getHeight() - height;
		customDialog.getWindow().setAttributes(localLayoutParams);
		customDialog.setCanceledOnTouchOutside(true);
		customDialog.setCancelable(true);
		customDialog.setContentView(scrView);

		if (context instanceof Activity) {// 判断状态
			Activity activity = (Activity) context;
			if (!activity.isFinishing()) {
				customDialog.show();
			}
		}
		return customDialog;
	}


	public static RelativeLayout mImageWallContainer = null;

	/**
	 * 向上弹出menu对话框
	 * 
	 * @param context
	 * @param drawableids
	 *            图片资源id
	 * @param items
	 *            文字字符
	 * @param l
	 *            监听器 返回index值
	 * @return
	 */

	public static Dialog createCustomMenuDialog(Context context,
                                                int[] drawableids, String[] items, final MenuItemClickListener l) {

		LinearLayout dialogView = (LinearLayout) LayoutInflater.from(context)
				.inflate(R.layout.custom_dialog_layout_menu, null);
		final Dialog customDialog = new Dialog(context,
				R.style.CustomDialog_Menu);
		RelativeLayout itemView;
		TextView textView;

		OnClickListener mClickListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Dialog dlg = (Dialog) v.getTag(R.id.tag_first);
				dlg.dismiss();
				int idx = (Integer) v.getTag();
				l.onItemClicked(idx);
			}
		};

		int i = 0;
		for (String item : items) {
			itemView = (RelativeLayout) LayoutInflater.from(context).inflate(
					R.layout.custom_dialog_menu_item, null);
			textView = (TextView) itemView.findViewById(R.id.popup_text);

			textView.setText(item);
			ImageView imageView = (ImageView) itemView
					.findViewById(R.id.popup_icon);
			imageView.setImageResource(drawableids[i]);
			itemView.setTag(i++);
			itemView.setTag(R.id.tag_first, customDialog);
			itemView.setOnClickListener(mClickListener);// 设置监听
			dialogView.addView(itemView);
		}

		// 设置位置
		WindowManager.LayoutParams localLayoutParams = customDialog.getWindow()
				.getAttributes();

		WindowManager m = ((Activity) context).getWindowManager();
		Display d = m.getDefaultDisplay();

		dialogView.measure(
				MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
				MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));

		int height = dialogView.getMeasuredHeight();

		// int height = d.getHeight()*2/3;
		localLayoutParams.width = LayoutParams.MATCH_PARENT;
		localLayoutParams.height = height;
		localLayoutParams.x = 0;
		localLayoutParams.y = d.getHeight() - height;
		customDialog.getWindow().setAttributes(localLayoutParams);
		customDialog.setCanceledOnTouchOutside(true);
		customDialog.setCancelable(true);
		customDialog.setContentView(dialogView);

		if (context instanceof Activity) {// 判断状态
			Activity activity = (Activity) context;
			if (!activity.isFinishing()) {
				customDialog.show();
			}
		}
		return customDialog;
	}

	@SuppressWarnings("deprecation")
	public static Dialog createCustomMenuDialogWithTag(Context context,
                                                       ArrayList<DlgItem> itemList, final OnClickListener l,
                                                       final OnLongClickListener ll) {

		LinearLayout dialogView = (LinearLayout) LayoutInflater.from(context)
				.inflate(R.layout.custom_dialog_layout_menu, null);
		final Dialog customDialog = new Dialog(context,
				R.style.CustomDialog_Menu);
		RelativeLayout itemView;
		TextView textView;

		for (DlgItem item : itemList) {
			itemView = (RelativeLayout) LayoutInflater.from(context).inflate(
					R.layout.custom_dialog_menu_item, null);
			textView = (TextView) itemView.findViewById(R.id.popup_text);

			if (item.showNewFlag) {
				itemView.findViewById(R.id.new_icon)
						.setVisibility(View.VISIBLE);
			} else {
				itemView.findViewById(R.id.new_icon).setVisibility(View.GONE);
			}

			textView.setText(item.mTilte);
			ImageView imageView = (ImageView) itemView
					.findViewById(R.id.popup_icon);
			imageView.setImageResource(item.mResId);
			itemView.setTag(item.mTag);
			itemView.setTag(R.id.tag_first, customDialog);
			itemView.setOnClickListener(l);// 设置监听
			itemView.setOnLongClickListener(ll);
			dialogView.addView(itemView);
		}

		// 设置位置
		WindowManager.LayoutParams localLayoutParams = customDialog.getWindow()
				.getAttributes();

		WindowManager m = ((Activity) context).getWindowManager();
		Display d = m.getDefaultDisplay();

		dialogView.measure(
				MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
				MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));

		int height = dialogView.getMeasuredHeight();

		ScrollView scrView = new ScrollView(context);
		scrView.addView(dialogView);

		Rect frame = new Rect();
		customDialog.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		int statusBarHeight = frame.top;

		localLayoutParams.width = LayoutParams.MATCH_PARENT;
		localLayoutParams.height = Math.min(height,DimenUtil.getScreenHeight()-statusBarHeight);
		localLayoutParams.x = 0;
		localLayoutParams.y = d.getHeight() - height;
		customDialog.getWindow().setAttributes(localLayoutParams);
		customDialog.setCanceledOnTouchOutside(true);
		customDialog.setCancelable(true);
		customDialog.setContentView(scrView);

		if (context instanceof Activity) {// 判断状态
			Activity activity = (Activity) context;
			if (!activity.isFinishing()) {
				customDialog.show();
			}
		}
		return customDialog;
	}


	
	public interface MenuItemClickListener {
		void onItemClicked(int index);
	}

	public static class DlgItem {
		String mTilte;
		int mResId;
		Object mTag;
		boolean showNewFlag;

		public DlgItem(String title, int resId, Object tag, boolean showNewFlag) {
			this.mResId = resId;
			this.mTilte = title;
			this.mTag = tag;
			this.showNewFlag = showNewFlag;
		}
	}

}
