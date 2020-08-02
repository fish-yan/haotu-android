package com.dmeyc.dmestoreyfm.adapter;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.View;

import io.rong.imkit.RongContext;
import io.rong.imkit.model.UIConversation;
import io.rong.imkit.widget.adapter.ConversationListAdapter;
import io.rong.imkit.widget.provider.GroupConversationProvider;

public class ConversationListAdapterEx extends ConversationListAdapter {
    public ConversationListAdapterEx(Context context) {
        super(context);
    }



//        public ConversationListAdapterEx(RongContext v){
//            super();
//
//        }
//    @Override
//    public void bindView(View view, int position, UIConversation data) {
//        deleteNameIfContains(data);
//
//        super.bindView(view, position, data);
//    }
//
//    /**
//     * If conversation data contains user's name, delete the name from the data
//     *
//     * @param data conversation ui data
//     */
//    private void deleteNameIfContains(UIConversation data) {
//        Spannable content = data.getConversationContent();
//
//        String separator = ": ";
//        int indexOf = content.toString().indexOf(separator);
//        if (indexOf == -1) {
//            return;
//        }
//
//        SpannableStringBuilder builder = new SpannableStringBuilder(content);
//        builder.delete(0, indexOf + separator.length());
//
//        data.setConversationContent(builder);
//    }
}
