package com.dmeyc.dmestoreyfm.newui.release.friend;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.text.TextUtils;

import com.dmeyc.dmestoreyfm.base.BaseApp;
import com.dmeyc.dmestoreyfm.bean.ContactBean;
import com.dmeyc.dmestoreyfm.newbase.BasePresenter;
import com.dmeyc.dmestoreyfm.newbase.IBaseRefreshView;

import java.util.ArrayList;
import java.util.List;

/**
 * create by cxg on 2019/12/15
 */
public class ContactPresenter extends BasePresenter<IBaseRefreshView> {
    private List<ContactBean> mContactList;

    public void locationRequest(String searchStr) {
        if (mContactList == null) {
            getContactList();
        }
        if (TextUtils.isEmpty(searchStr)) {
            mView.getDataListSucc(mContactList);
            return;
        }
        List<ContactBean> tempList = new ArrayList<>();
        for (ContactBean bean : mContactList) {
            if (bean.name.contains(searchStr) || bean.phoneNo.contains(searchStr)) {
                tempList.add(bean);
            }
        }
        mView.getDataListSucc(tempList);

    }

    private void getContactList() {
        mContactList = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = BaseApp.getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, null, null, null);
//moveToNext方法返回的是一个boolean类型的数据
            while (cursor.moveToNext()) {
                //读取通讯录的姓名
                String name = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                //读取通讯录的号码
                String number = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                ContactBean bean = new ContactBean(name, number);
                mContactList.add(bean);
            }
        } catch (Exception e) {
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
