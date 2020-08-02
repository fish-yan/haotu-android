package com.dmeyc.dmestoreyfm.bean;

public class VersionUpBean {
    /**
     * msg : 操作成功
     * code : 200
     * data : {"versionName":"1.0.8","versionCode":20190819,"versionType":"1","fileSize":"44.7M","versionNote":"我们自己的测试安装包","url":"http://192.168.0.104/group1/M00/00/22/wKgAaF1n9JGATaoOAszJJKK4agg60447.1"}
     */

    private String msg;
    private String code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * versionName : 1.0.8
         * versionCode : 20190819
         * versionType : 1
         * fileSize : 44.7M
         * versionNote : 我们自己的测试安装包
         * url : http://192.168.0.104/group1/M00/00/22/wKgAaF1n9JGATaoOAszJJKK4agg60447.1
         */

        private String versionName;
        private int versionCode;
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

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
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

//    /**
//     * code : 200
//     * msg : 操作成功
//     * data : {"id":1,"createDate":"2018-12-24 11:00:05","modifyDate":"2018-12-24 11:00:09","version":1,"url":"http://storeapi.dmeyc.com:8080/images/me_ic_head.png","updateMsg":" 修复了部分已知问题,提升了App性能","fileLength":"10000","isForceUpdate":false,"type":1}
//     */
//
//    private int code;
//    private String msg;
//    private DataBean data;
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public DataBean getData() {
//        return data;
//    }
//
//    public void setData(DataBean data) {
//        this.data = data;
//    }
//
//    public static class DataBean {
//        /**
//         * id : 1
//         * createDate : 2018-12-24 11:00:05
//         * modifyDate : 2018-12-24 11:00:09
//         * version : 1
//         * url : http://storeapi.dmeyc.com:8080/images/me_ic_head.png
//         * updateMsg :  修复了部分已知问题,提升了App性能
//         * fileLength : 10000
//         * isForceUpdate : false
//         * type : 1
//         */
//
//        private int id;
//        private String createDate;
//        private String modifyDate;
//        private int version;
//        private String url;
//        private String updateMsg;
//        private String fileLength;
//        private boolean isForceUpdate;
//        private int type;
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public String getCreateDate() {
//            return createDate;
//        }
//
//        public void setCreateDate(String createDate) {
//            this.createDate = createDate;
//        }
//
//        public String getModifyDate() {
//            return modifyDate;
//        }
//
//        public void setModifyDate(String modifyDate) {
//            this.modifyDate = modifyDate;
//        }
//
//        public int getVersion() {
//            return version;
//        }
//
//        public void setVersion(int version) {
//            this.version = version;
//        }
//
//        public String getUrl() {
//            return url;
//        }
//
//        public void setUrl(String url) {
//            this.url = url;
//        }
//
//        public String getUpdateMsg() {
//            return updateMsg;
//        }
//
//        public void setUpdateMsg(String updateMsg) {
//            this.updateMsg = updateMsg;
//        }
//
//        public String getFileLength() {
//            return fileLength;
//        }
//
//        public void setFileLength(String fileLength) {
//            this.fileLength = fileLength;
//        }
//
//        public boolean isIsForceUpdate() {
//            return isForceUpdate;
//        }
//
//        public void setIsForceUpdate(boolean isForceUpdate) {
//            this.isForceUpdate = isForceUpdate;
//        }
//
//        public int getType() {
//            return type;
//        }
//
//        public void setType(int type) {
//            this.type = type;
//        }
//    }
}
