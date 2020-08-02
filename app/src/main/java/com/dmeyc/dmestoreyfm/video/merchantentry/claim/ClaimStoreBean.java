package com.dmeyc.dmestoreyfm.video.merchantentry.claim;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ClaimStoreBean implements Serializable {


    /**
     * msg : 操作成功
     * code : 200
     * data : [{"id":152,"name":"龙腾羽毛球馆","province":"上海市","city":"上海市","area":"","address":"高潮路228号","tel":"2159187335"},{"id":267,"name":"黄浦滑轮馆羽毛球馆","province":"上海市","city":"上海市","area":"黄浦","address":"黄浦区大林路235号黄浦轮滑馆4楼(近西藏南路)","tel":"(021)63450508-2111"},{"id":113,"name":"黄浦市民健身中心","province":"上海市","city":"上海市","area":"黄浦","address":"山东中路311号(九江路山东中路)","tel":"(021)63515223"},{"id":40,"name":"黄浦世博体育馆","province":"上海市","city":"上海市","area":"黄浦","address":"上海市黄浦区外马路1353号","tel":""},{"id":212,"name":"黄工乒羽运动馆","province":"上海市","city":"上海市","area":"黄浦","address":"黄浦区普育东路227号黄浦区工人文化宫3号楼","tel":"(021)63772104"},{"id":44,"name":"黄埔工人体育馆","province":"上海市","city":"上海市","area":"黄浦","address":"上海市黄浦区外马路1288号","tel":""},{"id":225,"name":"黄兴全民体育公园","province":"上海市","city":"上海市","area":"","address":"双阳北路395弄-1","tel":"(021)35322502"},{"id":184,"name":"黄兴体育公园羽毛球馆","province":"上海市","city":"上海市","area":"杨浦","address":"上海市杨浦区双阳北路395-1号(近国顺东路)","tel":"(021)51086896"},{"id":202,"name":"鸿飞羽毛球旗舰馆","province":"上海市","city":"上海市","area":"宝山","address":"长江西路581弄10号2层","tel":"13402130984"},{"id":176,"name":"高行羽毛球馆","province":"上海市","city":"上海市","area":"浦东","address":"金高路200号（近万安街）金高路上（长虹汽车修理厂院内）(万嘉商业广场对面)","tel":"(021)60517028"},{"id":28,"name":"馨佳园体育馆","province":"上海市","city":"上海市","area":"宝山","address":"上海市宝山区潘广路1595号","tel":""},{"id":303,"name":"飞炫羽毛球俱乐部","province":"上海市","city":"上海市","area":"闵行","address":"上海市闵行区顾戴路3351号D栋D01","tel":"13818019188"},{"id":158,"name":"飞德羽毛球馆","province":"上海市","city":"上海市","area":"金山","address":"亭卫南路88弄1号4楼，天天海鲜楼上。","tel":"(021)37288777,(021)37287777"},{"id":128,"name":"颛桥镇文体中心","province":"上海市","city":"上海市","area":"闵行","address":"上海市闵行区都市路2699号214","tel":""},{"id":41,"name":"韵颖体育","province":"上海市","city":"上海市","area":"静安","address":"上海市静安区灵石路709号媒体园44幢3层","tel":""},{"id":59,"name":"静安新体育中心（汶水路116号）","province":"上海市","city":"上海市","area":"闸北","address":"汶水路116号","tel":""},{"id":114,"name":"静安全民运动中心","province":"上海市","city":"上海市","area":"静安","address":"上海市闸北区中华新路475号","tel":""},{"id":188,"name":"静安体育馆羽毛球馆","province":"上海市","city":"上海市","area":"静安","address":"静安区南阳路123号3楼","tel":"(021)62587787"},{"id":87,"name":"青浦羽毛球馆","province":"上海市","city":"上海市","area":"青浦","address":"徐泾镇双联路378号7号楼","tel":"(021)39889876"},{"id":300,"name":"青浦区综合训练馆","province":"上海市","city":"上海市","area":"","address":"盈绿路附近","tel":"18116418640"}]
     */

    @SerializedName("msg")
    private String msg;
    @SerializedName("code")
    private String code;
    @SerializedName("data")
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * id : 152
         * name : 龙腾羽毛球馆
         * province : 上海市
         * city : 上海市
         * area :
         * address : 高潮路228号
         * tel : 2159187335
         */

        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;
        @SerializedName("province")
        private String province;
        @SerializedName("city")
        private String city;
        @SerializedName("area")
        private String area;
        @SerializedName("address")
        private String address;
        @SerializedName("tel")
        private String tel;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }
    }
}
