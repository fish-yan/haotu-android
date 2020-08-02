package com.dmeyc.dmestoreyfm.wedgit;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.adapter.TypeSelectorAdapter;

import java.util.List;

/**
 * create by cxg on 2019/12/27
 */
public class TypeSelectorDialog<T> extends Dialog {

    private RecyclerView mRecycleView;
    private List<T> mList;
    private List<String> mItemNames;
    private ItemClickListener mListener;
    private Context mContext;
    private TypeSelectorDialog(@NonNull Context context) {
        super(context, R.style.Dialog);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_type_selector);
        mRecycleView = findViewById(R.id.rv_types);
        mRecycleView.setLayoutManager(new LinearLayoutManager(mContext));
        TypeSelectorAdapter adapter = new TypeSelectorAdapter();
        adapter.bindToRecyclerView(mRecycleView);
        mRecycleView.setAdapter(adapter);
        adapter.replaceData(mItemNames);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mListener != null) {
                    mListener.onItemClick(mItemNames.get(position),mList.get(position));
                    dismiss();
                }
            }
        });
        findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        setCancelable(false);
        setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = mContext.getResources().getDisplayMetrics().widthPixels;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        getWindow().setAttributes(lp);
    }

    public static class Build<T> {
        private TypeSelectorDialog mDialog;

        public Build(Context context) {
            mDialog = new TypeSelectorDialog(context);
        }

        public <T> Build setData(List<T> list) {
            mDialog.mList = list;
            return this;
        }

        public Build setNameItems(List<String> names) {
            mDialog.mItemNames = names;
            return this;
        }

        public Build setOnItemClickListener(ItemClickListener listener) {
            mDialog.mListener = listener;
            return this;
        }

        public TypeSelectorDialog create() {
            return mDialog;
        }
    }

    public interface ItemClickListener<T> {
        void onItemClick(String s, T bean);
    }
}
