package com.dmeyc.dmestoreyfm.tempdata;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jockie on 2017/11/24
 * Email:jockie911@gmail.com
 */

public class CouponList {

    public static List<Coupon> getCouponList(){
        List<Coupon> list = new ArrayList<>();
        list.add(new Coupon(true,"新用户专享优惠券","满80可用","有效期: 2017/11/5-2017/12/5","20"));
        list.add(new Coupon(true,"邀请新用户专享优惠券","满120可用","有效期: 2017/11/5-2017/12/5","30"));
        list.add(new Coupon(true,"随机赠送优惠券","满5.010可用","有效期: 2017/11/5-2017/12/5","5"));
        list.add(new Coupon(false,"长得帅赠送优惠券","满120可用","有效期: 2017/09/8-2017/10/7","40"));
        list.add(new Coupon(false,"新用户专享优惠券","满50可用","有效期: 2017/10/5-2017/11/4","10"));
        return list;
    }

    public static class Coupon {
        private boolean status;
        private String title;
        private String subtitle;
        private String time;
        private String price;

        public Coupon(boolean status, String title, String subtitle, String time, String price) {
            this.status = status;
            this.title = title;
            this.subtitle = subtitle;
            this.time = time;
            this.price = price;
        }

        public boolean isStatus() {
            return status;
        }

        public String getTitle() {
            return title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public String getTime() {
            return time;
        }

        public String getPrice() {
            return price;
        }
    }
}
