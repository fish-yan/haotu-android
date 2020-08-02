package com.dmeyc.dmestoreyfm.newui.menu.apply.merchant;

import com.dmeyc.dmestoreyfm.newbase.IBaseView;

import java.util.Map;

/**
 * create by cxg on 2019/11/30
 */
public interface IApplyMerchantView extends IBaseView {
    Map<String, String> getParams();

    void httpRequestDataSucc();
}
