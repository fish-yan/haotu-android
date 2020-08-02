package com.dmeyc.dmestoreyfm.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.bean.MyCommListBean;
import com.dmeyc.dmestoreyfm.bean.RoCluldCommBean;
import com.dmeyc.dmestoreyfm.constant.Constant;
import com.dmeyc.dmestoreyfm.net.DmeycBaseSubscriber;
import com.dmeyc.dmestoreyfm.net.ParamMap;
import com.dmeyc.dmestoreyfm.net.RestClient;
import com.dmeyc.dmestoreyfm.utils.SPUtils;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import java.util.ArrayList;

import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imkit.model.UIConversation;
import io.rong.imkit.widget.adapter.ConversationListAdapter;
import io.rong.imlib.model.Conversation;

/**
 * author : zhangxiaoxiang
 * date : 2019-08-13 18:43
 * description :
 */
public class my extends ConversationListFragment {
    EditText et_search_title;
    ConversationListAdapter conversationListAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rc_fr_conversationlist, container, false);//在rc_fr_conversationlist 添加您自己的控件
        et_search_title= view. findViewById(R.id.et_search_title);
//        conversationListAdapter=onResolveAdapter(getActivity());
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
                        roCluldCommBeans.clear();
//                        et_search_title.setText("");
                        getCommList();
//                        actionFragment.refreshSearch(et_search_title.getText().toString().trim());
                    }
                }
                return false;
            }
        });
        return view;
    }
    private  ArrayList<RoCluldCommBean> roCluldCommBeans=new ArrayList<>();
    RoCluldCommBean roCluldCommBean;
    public void getCommList(){
        RestClient.getYfmNovate(getActivity()).post(Constant.API.YFM_MYCOMMlist, new ParamMap.Build()
                .addParams("user_token", SPUtils.getStringData(Constant.Config.TOKEN))
                .addParams("status", "0")
                .build(), new DmeycBaseSubscriber<MyCommListBean>() {
            @Override
            public void onSuccess(MyCommListBean bean) {
                for (int i=0;i<bean.getData().size();i++){
                    if(bean.getData().get(i).getGroup_name()!=null){
                        if(bean.getData().get(i).getGroup_name().contains(et_search_title.getText().toString().trim())){
                            roCluldCommBean=new RoCluldCommBean();
                            roCluldCommBean.setGroup_id(bean.getData().get(i).getGroup_id());
                            roCluldCommBean.setGroup_name(bean.getData().get(i).getGroup_name());
                            roCluldCommBean.setGroup_logo(bean.getData().get(i).getGroup_logo());
                            roCluldCommBeans.add(roCluldCommBean);
                        }
                    }
                }
                setRongUserInfo("");
            }
        });
    }

    //设置容云用户信息
    private void setRongUserInfo(final String targetid) {
        if (RongIM.getInstance()!=null){
//          for (int i=0;i<roCluldCommBeans.size();i++){
//              listFragment.shouldFilterConversation(Conversation.ConversationType.GROUP,roCluldCommBeans.get(i).getGroup_id()+"");
//
//                  RongIM.getInstance().refreshGroupInfoCache(new Group(roCluldCommBeans.get(i).getGroup_id()+"",roCluldCommBeans.get(i).getGroup_name(),Uri.parse(roCluldCommBeans.get(i).getGroup_logo())));
//
//          }

//            RongIM.getInstance().getConversationList(new RongIMClient.ResultCallback<List<Conversation>>() {
//                @Override
//                public void onSuccess(List<Conversation> conversations) {
//                    System.out.print("ccccccc"+conversations);
//                    for (int i = 0; i < conversations.size(); i++) {
//                        getListUserInfo(TotalMessageActivity.this, conversations.get(i).getTargetId());
//                        System.out.print(conversations.get(i).getTargetId()+"aaaaaaaa");
//                        System.out.print(conversations.get(i).getPortraitUrl()+"bbbbbbbbbbb");
//                        System.out.print(conversations.get(i).getTargetId()+"ddddddddddd");
//                    }
//
//                }
//                @Override
//                public void onError(RongIMClient.ErrorCode errorCode) {
//
//                }
//            });

            conversationListAdapter.clear();
            Conversation conversation=new Conversation();
            conversation.setPortraitUrl(roCluldCommBeans.get(0).getGroup_logo());
            conversation.setObjectName(roCluldCommBeans.get(0).getGroup_name());
            conversation.setConversationType(Conversation.ConversationType.GROUP);
            conversation.setTargetId(roCluldCommBeans.get(0).getGroup_id()+"");
            UIConversation uiConversation = UIConversation.obtain(getActivity(), conversation, true);

            conversationListAdapter.add(uiConversation);
            conversationListAdapter.notifyDataSetChanged();
        }
    }
}
