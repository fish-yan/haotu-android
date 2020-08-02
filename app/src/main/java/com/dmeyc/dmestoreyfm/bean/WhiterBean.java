package com.dmeyc.dmestoreyfm.bean;

import java.io.Serializable;

public class WhiterBean implements Serializable {

    /**
     * code : 200
     * data : {"city":"上海市","humidity":"31","province":"上海","reportTime":"2019-06-04 15:18:39","temperature":"33","userLogo":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eprJeCDDj1BzR9EkiczIdZ6DkyJYMYpSojXezNTwSnXzuibRafy0MJVFK3zSic4j0fwBAjIF5yCHfh5Q/132","weather":"多云","windDirection":"东北","windPower":"≤3"}
     * msg : 操作成功
     */

    private String code;
    private DataBean data;
    private String msg;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * city : 上海市
         * humidity : 31
         * province : 上海
         * reportTime : 2019-06-04 15:18:39
         * temperature : 33
         * userLogo : http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eprJeCDDj1BzR9EkiczIdZ6DkyJYMYpSojXezNTwSnXzuibRafy0MJVFK3zSic4j0fwBAjIF5yCHfh5Q/132
         * weather : 多云
         * windDirection : 东北
         * windPower : ≤3
         */

        private String city;
        private String humidity;
        private String province;
        private String reportTime;
        private String temperature;
        private String userLogo;
        private String weather;
        private String windDirection;
        private String windPower;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getReportTime() {
            return reportTime;
        }

        public void setReportTime(String reportTime) {
            this.reportTime = reportTime;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getUserLogo() {
            return userLogo;
        }

        public void setUserLogo(String userLogo) {
            this.userLogo = userLogo;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getWindDirection() {
            return windDirection;
        }

        public void setWindDirection(String windDirection) {
            this.windDirection = windDirection;
        }

        public String getWindPower() {
            return windPower;
        }

        public void setWindPower(String windPower) {
            this.windPower = windPower;
        }
    }
}
