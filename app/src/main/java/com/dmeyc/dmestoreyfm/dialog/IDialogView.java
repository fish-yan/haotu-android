package com.dmeyc.dmestoreyfm.dialog;

import com.dmeyc.dmestoreyfm.bean.AttrBean;
import com.dmeyc.dmestoreyfm.bean.SelectInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by jockie on 2018/5/25
 * Email:jockie911@gmail.com
 */

public interface IDialogView {

    boolean isCuston();

    void showMsg(String msg); //用户错误操作的提示

    int getProductId(); //商品Id

    void requestDataSuccess(List<AttrBean.DataBean.AttributeDetailsBean> list);

    Map<String, String> checkPreStatus();

    int getQuantity();

    void dialogDismiss();

    SelectInfo getSelectSizeInfo();

    String getCustoms(); //定制模块 返回的id

    String getClickColor(); //定制模块 返回的id

    String getClickSize(); //定制模块 返回的id

    String getTailNme(); //定制模块 返回的id
}
