package com.dmeyc.dmestoreyfm.bean.event;

/**
 * Created by yc on 2017/5/10.
 */

public class PicPathEvent {

    private boolean isFrontTakePhoto;
    private String path;

    public PicPathEvent(String filePath) {
        path = filePath;
    }

    public PicPathEvent(boolean isFrontTakePhoto) {
        this.isFrontTakePhoto = isFrontTakePhoto;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isFrontTakePhoto() {
        return isFrontTakePhoto;
    }
}
