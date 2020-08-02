package com.dmeyc.dmestoreyfm.bean.event;

/**
 * Created by jockie on 2018/3/22
 * Email:jockie911@gmail.com
 */

public class EditMobileEvent {

    private String newMobile;

    public EditMobileEvent(String newMobile){
        this.newMobile = newMobile;
    }

    public String getNewMobile() {
        return newMobile;
    }
}
