package com.SuperKotlin.pictureviewer;

/**
 * Created by ytq on 2018/11/7
 */

public class upDataEvent {
    private String message;

    public upDataEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
