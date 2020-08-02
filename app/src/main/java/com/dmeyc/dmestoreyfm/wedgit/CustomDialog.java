package com.dmeyc.dmestoreyfm.wedgit;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseApp;


public class CustomDialog {
	private Context context;
	private Dialog dialog;
	private LinearLayout lLayout_bg;
	private TextView txt_title;
	private TextView txt_msg;
	private TextView btn_neg;
	private TextView btn_pos;
	private View img_line;
	private Display display;
	private boolean showTitle = false;
	private boolean showMsg = false;
	private boolean showPosBtn = false;
	private boolean showNegBtn = false;

	public CustomDialog(Context context) {
		this.context = context;
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		display = windowManager.getDefaultDisplay();
	}

	public CustomDialog builder() {
		// 获取Dialog布局
		View view = LayoutInflater.from(context).inflate(
				R.layout.custom_dialog, null);
		// 获取自定义Dialog布局中的控件
		lLayout_bg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
		txt_title = (TextView) view.findViewById(R.id.txt_title);
		txt_title.setVisibility(View.GONE);
		txt_msg = (TextView) view.findViewById(R.id.txt_msg);
		txt_msg.setVisibility(View.GONE);
		btn_neg = (TextView) view.findViewById(R.id.btn_neg);
		btn_neg.setVisibility(View.GONE);
		btn_pos = (TextView) view.findViewById(R.id.btn_pos);
		btn_pos.setVisibility(View.GONE);
		img_line = view.findViewById(R.id.img_line);
		img_line.setVisibility(View.GONE);

		// 定义Dialog布局和参数
		dialog = new Dialog(context, R.style.dialog_style_top2bottom);
		dialog.setContentView(view);

		// 调整dialog背景大小
		lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display
				.getWidth() * 0.75), LayoutParams.WRAP_CONTENT));
		return this;
	}

	public CustomDialog setTitle(String title) {
		showTitle = true;
		if (TextUtils.isEmpty(title)) {
			txt_title.setText("提示");
		} else {
			txt_title.setText(title);
		}
		return this;
	}

	public CustomDialog showTitle() {
		showTitle = true;
		return this;
	}

	public CustomDialog setTitle(int resTitleID) {
		showTitle = true;
		txt_title.setText(resTitleID);
		return this;
	}

	public CustomDialog setMsg(String msg) {
		showMsg = true;
		if (TextUtils.isEmpty(msg)) {
			txt_msg.setText("内容");
		} else {
			txt_msg.setText(msg);
		}
		return this;
	}

	public CustomDialog setMsg(int resMsgId) {
		showMsg = true;
		txt_msg.setText(resMsgId);
		return this;
	}

	public CustomDialog setCancelable(boolean cancel) {
		dialog.setCancelable(cancel);
		return this;
	}

	public CustomDialog setPositiveButton(String text,
			final OnClickListener listener) {
		showPosBtn = true;
		if (TextUtils.isEmpty(text)) {
			btn_pos.setText("确定");
		} else {
			btn_pos.setText(text);
		}
		btn_pos.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(listener != null)
					listener.onClick(v);
				dialog.dismiss();
			}
		});
		return this;
	}

	public CustomDialog setPositiveButton(String text, int textColotRes,OnClickListener listener) {
		showPosBtn = true;
		btn_pos.setTextColor(BaseApp.getContext().getResources().getColor(textColotRes));
		return setPositiveButton(text,listener);
	}

	public CustomDialog setNegativeButton(String text, final OnClickListener listener) {
		showNegBtn = true;
		if (TextUtils.isEmpty(text)) {
			btn_neg.setText("取消");
		} else {
			btn_neg.setText(text);
		}
		btn_neg.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(listener != null)
					listener.onClick(v);
				dialog.dismiss();
			}
		});
		return this;
	}

	public CustomDialog setNegativeButton(String text, int textColotRes,OnClickListener listener) {
		showNegBtn = true;
		btn_neg.setTextColor(BaseApp.getContext().getResources().getColor(textColotRes));
		return setNegativeButton(text,listener);
	}

	private void setLayout() {
		if (!showTitle && !showMsg) {
			txt_title.setText("提示");
			txt_title.setVisibility(View.VISIBLE);
		}

		if (showTitle) {
			txt_title.setVisibility(View.VISIBLE);
		}

		if (showMsg) {
			txt_msg.setVisibility(View.VISIBLE);
		}

		if (!showPosBtn && !showNegBtn) {
			btn_pos.setText("确定");
			btn_pos.setVisibility(View.VISIBLE);
			btn_pos.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			});
		}

		if (showPosBtn && showNegBtn) {
			btn_pos.setVisibility(View.VISIBLE);
			btn_neg.setVisibility(View.VISIBLE);
			img_line.setVisibility(View.VISIBLE);
		}

		if (showPosBtn && !showNegBtn) {
			btn_pos.setVisibility(View.VISIBLE);
		}

		if (!showPosBtn && showNegBtn) {
			btn_neg.setVisibility(View.VISIBLE);
		}
	}

	public void show() {
		setLayout();
		dialog.show();
	}

	public boolean isShowing() {
		return dialog.isShowing();
	}
	public void dismiss(){
		dialog.dismiss();
	}
}
