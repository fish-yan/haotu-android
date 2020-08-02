package com.dmeyc.dmestoreyfm.tempdata;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jockie on 2017/11/23
 * Email:jockie911@gmail.com
 */

public class PicList {

    /**
     * 垂直
     * @return
     */
    public static List<Pic> getVPicList(){
        List<Pic> list = new ArrayList<>();
        list.add(new Pic("Lily","小短裙","288","http://www.icicle.com/assets/imgsupl/RG6.0_0.89707900_1505876258_1d5a_news_images_image_1-2.jpg"));
        list.add(new Pic("laxiabeier","大衣外套","4488","http://www.icicle.com/assets/imgsupl/RG6.0_0.36552900_1505704459_71e5_news_images_image_1-4.jpg"));
        list.add(new Pic("Gucci","花裙子","1288","http://www.icicle.com/assets/imgsupl/RG6.0_0.63890300_1505987349_21aa_news_images_image_2-3.jpg"));
        list.add(new Pic("路易","皮衣","458","http://www.icicle.com/assets/imgsupl/RG6.0_0.66019100_1505876360_9c4f_news_images_image_3-4.jpg"));
        list.add(new Pic("路易18","da皮衣","24558","http://www.icicle.com/assets/imgsupl/RG6.0_0.25291000_1505876418_fac8_news_images_image_5-4.jpg"));
        list.add(new Pic("路易20","飞飞皮衣","4458","http://www.icicle.com/assets/imgsupl/RG6.0_0.15926700_1505876436_7b2d_news_images_image_5-5.jpg"));
        return list;
    }

    public static List<Pic> getHPicList(){
        List<Pic> list = new ArrayList<>();
        list.add(new Pic("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2909064781,1892834277&fm=27&gp=0.jpg"));
        list.add(new Pic("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1249728661,1352919463&fm=27&gp=0.jpg"));
        list.add(new Pic("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=494184160,2456250609&fm=27&gp=0.jpg"));
        list.add(new Pic("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3700788301,160975094&fm=27&gp=0.jpg"));
        return list;
    }

    public static class Pic {

        private String url;
        private String brand;
        private String name;
        private String price;

        public Pic(String url) {
            this.url = url;
        }

        public Pic(String brand, String name, String price, String url) {
            this.brand = brand;
            this.name = name;
            this.price = price;
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public String getBrand() {
            return brand;
        }

        public String getName() {
            return name;
        }

        public String getPrice() {
            return price;
        }
    }
}
