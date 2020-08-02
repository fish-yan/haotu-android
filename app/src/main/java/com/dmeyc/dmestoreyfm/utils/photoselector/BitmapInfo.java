package com.dmeyc.dmestoreyfm.utils.photoselector;

import android.graphics.Bitmap;

import java.io.Serializable;


public  class BitmapInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    public Bitmap bmp;
    public int inSampleSize;
    public String path;
    public boolean bNewSave;

    public int width,height;
    public long filesize;
}
