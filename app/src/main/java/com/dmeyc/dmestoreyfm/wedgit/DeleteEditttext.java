package com.dmeyc.dmestoreyfm.wedgit;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dmeyc.dmestoreyfm.R;

/**
 * Created by jockie on 2017/11/7
 * Email:jockie911@gmail.com
 */

public class DeleteEditttext extends RelativeLayout implements TextWatcher, View.OnClickListener {

    private EditText etPhone;
    private ImageView ivDelete;
    private OnCompleteListener listener;

    public DeleteEditttext(Context context) {
        super(context);
        init();
    }

    public DeleteEditttext(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DeleteEditttext(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.delete_edittext, this, true);
        etPhone = (EditText) view.findViewById(R.id.tv_phone);
        ivDelete = (ImageView) view.findViewById(R.id.iv_delete);

        etPhone.addTextChangedListener(this);
        ivDelete.setOnClickListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    private static final char CUT = ' ';
    @Override
    public void onTextChanged(CharSequence text, int start, int before, int count) {
        if (text == null || text.length() == 0)
            return;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {//添加分割符
            if (i != 3 && i != 8 && text.charAt(i) == CUT) {
                continue;
            } else {
                sb.append(text.charAt(i));
                if ((sb.length() == 4 || sb.length() == 9)
                        && sb.charAt(sb.length() - 1) != CUT) {
                    sb.insert(sb.length() - 1, CUT);
                }
            }
        }
        //防止多次设置值
        if (!sb.toString().equals(text.toString())) {
            int index = start + 1;
            if (sb.charAt(start) == CUT) {
                if (before == 0) {
                    index++;
                } else {
                    index--;
                }
            } else {
                if (before == 1) {
                    index--;
                }
            }
            etPhone.setText(sb.toString());
            etPhone.setSelection(index);
        }else{//删除时候判断
            String line = text.subSequence(text.length() - 1, text.length()).toString();
            if (line.equals(String.valueOf(CUT))) {//如果删除碰到‘ '符号，则默认去除
                sb.deleteCharAt(text.subSequence(0, text.length() - 1).length());
                etPhone.setText(sb.toString());
                etPhone.setSelection(sb.length());
            }
        }
        if (text.length() == 13){
            listener.onComplete(true,text.toString().replace(" ",""));
            ivDelete.setVisibility(VISIBLE);
        }else{
            listener.onComplete(false,"");
            ivDelete.setVisibility(INVISIBLE);
        }
    }

    /**
     * 带空格的手机号码 eg: 180 1234 5678
     * @return
     */
    public String getPhoneNum(){
        return etPhone.getText().toString().replace(" ","");
    }

    public String getRelPhoneNum(){
        return getPhoneNum().replace(" ","");
    }

    @Override
    public void afterTextChanged(Editable s) {
        Log.d("TAG", " afterTextChanged " + s );
    }

    @Override
    public void onClick(View v) {
        etPhone.setText("");
        ivDelete.setVisibility(INVISIBLE);
        listener.onComplete(false,"");
    }

    public void setOnCompleteListener(OnCompleteListener listener){
        this.listener = listener;
    }

    public interface OnCompleteListener{
        void onComplete(boolean isComplete , String phoneNum);
    }
}
