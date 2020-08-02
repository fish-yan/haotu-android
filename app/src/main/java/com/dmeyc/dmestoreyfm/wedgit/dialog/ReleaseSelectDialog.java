package com.dmeyc.dmestoreyfm.wedgit.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.ReleaseSelectAdapter;
import com.dmeyc.dmestoreyfm.bean.ReleaseItemBean;
import com.dmeyc.dmestoreyfm.config.ReleaseConfig;

import java.util.List;

/**
 * create by cxg on 2019/11/30
 */
public class ReleaseSelectDialog extends Dialog {

    private Context mContext;
    private ReleaseSelectorListener mListener;

    private ReleaseSelectDialog(@NonNull Context context) {
        super(context, R.style.dialog_transform);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View layout = LayoutInflater.from(mContext).inflate(R.layout.dialog_release, null, false);
        setContentView(layout);

        findViewById(R.id.ll_release).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        ListView lv = layout.findViewById(R.id.lv_release);
        List<ReleaseItemBean> mData = ReleaseConfig.getList();

        ReleaseSelectAdapter adapter = new ReleaseSelectAdapter(mContext, mData);
        adapter.setiCallBack(new ReleaseSelectAdapter.ICallBack() {
            @Override
            public void onItemClick(ReleaseConfig.Type type) {
                if (mListener != null) {
                    mListener.onItemClick(type);
                }
                dismiss();
            }
        });
        lv.setAdapter(adapter);

        setCancelable(true);
        setCanceledOnTouchOutside(true);

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        lp.width = mContext.getResources().getDisplayMetrics().widthPixels;
        lp.gravity = Gravity.BOTTOM;
        getWindow().setAttributes(lp);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mListener != null) {
            mListener.onShow();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mListener != null) {
            mListener.onHide();
        }
    }

    public static class Build {
        private ReleaseSelectDialog mDialog;

        public Build(Context context) {
            mDialog = new ReleaseSelectDialog(context);
        }

        public Build setListener(ReleaseSelectorListener listener) {
            mDialog.mListener = listener;
            return this;
        }

        public ReleaseSelectDialog create() {
            return mDialog;
        }
    }

    public interface ReleaseSelectorListener {
        void onShow();

        void onHide();

        void onItemClick(ReleaseConfig.Type type);
    }
}
