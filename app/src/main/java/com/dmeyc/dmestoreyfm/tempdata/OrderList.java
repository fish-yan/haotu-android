package com.dmeyc.dmestoreyfm.tempdata;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jockie on 2017/11/22
 * Email:jockie911@gmail.com
 */

public class OrderList {

    public static List<Order> getOrderList(){
        List<Order> list = new ArrayList<>();
        list.add(new Order(3,248));
        list.add(new Order(1,140));
        list.add(new Order(2,105));
        list.add(new Order(3,140));
        list.add(new Order(4,1050));
        list.add(new Order(2,1040));
        list.add(new Order(1,1030));
        list.add(new Order(2,600));
        list.add(new Order(3,1600));
        list.add(new Order(4,1456));
        list.add(new Order(2,1340));
        list.add(new Order(1,1530));
        list.add(new Order(2,150));
        list.add(new Order(-2,1340,1));
        list.add(new Order(-2,1530,2));
        list.add(new Order(-2,150,3));
        list.add(new Order(-2,1340,4));
        list.add(new Order(-2,1530,5));
        return list;
    }
    public static class Order{

        private int status;
        private int price;
        private int type;

        public Order(int status, int price){
            this.status = status;
            this.price = price;
        }

        public Order(int status, int price,int type){
            this.status = status;
            this.price = price;
            this.type = type;
        }

        public int getStatus() {
            return status;
        }

        public int getPrice() {
            return price;
        }

        public int getType(){return type;}
    }
}
