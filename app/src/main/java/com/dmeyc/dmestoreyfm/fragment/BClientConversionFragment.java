package com.dmeyc.dmestoreyfm.fragment;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.base.BaseFragment;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import butterknife.Bind;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;

public class BClientConversionFragment extends BaseFragment {

    @Bind(R.id.et_search_title)
    EditText et_search_title;
    @Override
    protected int getLayoutRes() {
        return R.layout.frag_comver;
    }

    @Override
    protected void initData() {
        ConversationListFragment listFragment = (ConversationListFragment) ConversationListFragment.instantiate(getActivity(), ConversationListFragment.class.getName());
        Uri uri = Uri.parse("rong://" + getActivity().getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")
                .build();
        listFragment.setUri(uri);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        //将融云的Fragment界面加入到我们的页面。
        transaction.add(R.id.subconversationlist, listFragment);
        transaction.commitAllowingStateLoss();
    }

    @Override
    protected void initData(View view) {


        et_search_title.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                if(i == EditorInfo.IME_ACTION_SEARCH)
                {
                    /**隐藏软键盘**/
                    View view = getActivity().getWindow().peekDecorView();
                    if (view != null) {
                        InputMethodManager inputmanger = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                    if(TextUtils.isEmpty(et_search_title.getText().toString().trim())){
                        ToastUtil.show("请输入搜索词");

                    }else {
                        et_search_title.setText("");
//                        actionFragment.refreshSearch(et_search_title.getText().toString().trim());
                    }
                }
                return false;
            }
        });

//        HashMap<String, Boolean> hashMap = new HashMap<>();
//        //会话类型 以及是否聚合显示
////        hashMap.put(Conversation.ConversationType.PRIVATE.getName(),false);
////        hashMap.put(Conversation.ConversationType.PUSH_SERVICE.getName(),true);
////        hashMap.put(Conversation.ConversationType.SYSTEM.getName(),true);
//        hashMap.put(Conversation.ConversationType.GROUP.getName(),true);
//        RongIM.getInstance().startConversationList(getActivity(),hashMap);
    }
}

