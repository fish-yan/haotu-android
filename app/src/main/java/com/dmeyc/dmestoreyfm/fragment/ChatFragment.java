package com.dmeyc.dmestoreyfm.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;

import com.dmeyc.dmestoreyfm.R;
import com.dmeyc.dmestoreyfm.listener.MoreSendAction;
import com.dmeyc.dmestoreyfm.utils.DataClass;
import com.dmeyc.dmestoreyfm.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.actions.IClickActions;
import io.rong.imkit.fragment.ConversationFragment;

public class ChatFragment extends ConversationFragment {
//    public boolean showMoreClickItem(){
//        return true;
//    }
    @Override
    public boolean showMoreClickItem() {
//        if(DataClass.message==null){
//            return false;
//        }
//        if("RC:ImgMsg".equals(DataClass.message.getObjectName())||"RC:SightMsg".equals(DataClass.message.getObjectName())){
            return true;
//        }else {
//            return false;
//        }
    }
    ArrayList<IClickActions> clickActions=new ArrayList<>();
    @Override
    public List<IClickActions> getMoreClickActions() {
        if(DataClass.message!=null){

            List<IClickActions> actions = new ArrayList();
            actions.addAll(super.getMoreClickActions());
            actions.add(0,new MoreSendAction(getActivity()));
            return actions;

//            if("RC:ImgMsg".equals(DataClass.message.getObjectName())||"RC:SightMsg".equals(DataClass.message.getObjectName())){
//                MoreSendAction moreSendAction=new MoreSendAction(getActivity());
//                clickActions.add(moreSendAction);
//            }
        }

        return clickActions;
//        return super.getMoreClickActions();
    }
}
