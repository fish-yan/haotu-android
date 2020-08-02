package com.dmeyc.dmestoreyfm.bean.response;

import com.google.gson.annotations.SerializedName;

/**
 * create by cxg on 2019/12/5
 */
public class UploadImageCompressBean extends BaseRespBean {

    /**
     * code : 200
     * data : {"zoomImage":"http://101.44.2.178/group1/M00/00/25/wKgAaF32kMqAOVROAABh7in67s8401.png","originImage":"http://101.44.2.178/group1/M00/00/25/wKgAaF32kMqAaudWAAAKf8VJ6Q4956.png"}
     */

    private DataBean data;


    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * zoomImage : http://101.44.2.178/group1/M00/00/25/wKgAaF32kMqAOVROAABh7in67s8401.png
         * originImage : http://101.44.2.178/group1/M00/00/25/wKgAaF32kMqAaudWAAAKf8VJ6Q4956.png
         */

        private String zoomImage;
        private String originImage;
        private String originImageSize;

        public String getOriginImageSize() {
            return originImageSize;
        }

        public void setOriginImageSize(String originImageSize) {
            this.originImageSize = originImageSize;
        }

        public String getZoomImage() {
            return zoomImage;
        }

        public void setZoomImage(String zoomImage) {
            this.zoomImage = zoomImage;
        }

        public String getOriginImage() {
            return originImage;
        }

        public void setOriginImage(String originImage) {
            this.originImage = originImage;
        }
    }
}
