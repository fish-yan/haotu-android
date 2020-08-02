package com.dmeyc.dmestoreyfm.utils.photoselector;



import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class AlbumVO implements Serializable {
    public ArrayList<String> albumIamges;
    public String albumName;
    
    public AlbumVO(String name){
        this.albumName = name;
        albumIamges = new ArrayList<String>();
    }
}
