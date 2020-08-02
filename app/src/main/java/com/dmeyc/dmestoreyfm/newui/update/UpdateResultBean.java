package com.dmeyc.dmestoreyfm.newui.update;

import com.dmeyc.dmestoreyfm.bean.response.BaseRespBean;
public class UpdateResultBean extends BaseRespBean {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private String versionName;
        private String versionCode;
        private String versionType;
        private String fileSize;
        private String versionNote;
        private String url;

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public String getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(String versionCode) {
            this.versionCode = versionCode;
        }

        public String getVersionType() {
            return versionType;
        }

        public void setVersionType(String versionType) {
            this.versionType = versionType;
        }

        public String getFileSize() {
            return fileSize;
        }

        public void setFileSize(String fileSize) {
            this.fileSize = fileSize;
        }

        public String getVersionNote() {
            return versionNote;
        }

        public void setVersionNote(String versionNote) {
            this.versionNote = versionNote;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
