package com.dmeyc.dmestoreyfm.bean;

import java.util.List;

/**
 * Created by jockie on 2018/3/9
 * Email:jockie911@gmail.com
 */

public class FaceBean {
    /**
     * image_id : DlvWQYwUwPolAtovFc/kSw==
     * request_id : 1520585639,2cfd45a5-2196-45dd-891a-3f35d7d842cd
     * time_used : 477
     * humanbodies : [{"humanbody_rectangle":{"width":241,"top":96,"height":340,"left":21},"confidence":99.798}]
     */

    private String image_id;
    private String request_id;
    private int time_used;
    private List<HumanbodiesBean> humanbodies;

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public int getTime_used() {
        return time_used;
    }

    public void setTime_used(int time_used) {
        this.time_used = time_used;
    }

    public List<HumanbodiesBean> getHumanbodies() {
        return humanbodies;
    }

    public void setHumanbodies(List<HumanbodiesBean> humanbodies) {
        this.humanbodies = humanbodies;
    }

    public static class HumanbodiesBean {
        /**
         * humanbody_rectangle : {"width":241,"top":96,"height":340,"left":21}
         * confidence : 99.798
         */

        private HumanbodyRectangleBean humanbody_rectangle;
        private double confidence;

        public HumanbodyRectangleBean getHumanbody_rectangle() {
            return humanbody_rectangle;
        }

        public void setHumanbody_rectangle(HumanbodyRectangleBean humanbody_rectangle) {
            this.humanbody_rectangle = humanbody_rectangle;
        }

        public double getConfidence() {
            return confidence;
        }

        public void setConfidence(double confidence) {
            this.confidence = confidence;
        }

        public static class HumanbodyRectangleBean {
            /**
             * width : 241
             * top : 96
             * height : 340
             * left : 21
             */

            private int width;
            private int top;
            private int height;
            private int left;

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getTop() {
                return top;
            }

            public void setTop(int top) {
                this.top = top;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public int getLeft() {
                return left;
            }

            public void setLeft(int left) {
                this.left = left;
            }
        }
    }
}
