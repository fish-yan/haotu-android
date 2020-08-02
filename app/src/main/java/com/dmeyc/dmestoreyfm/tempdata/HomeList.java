package com.dmeyc.dmestoreyfm.tempdata;

import com.dmeyc.dmestoreyfm.utils.GsonTools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jockie on 2017/11/29
 * Email:jockie911@gmail.com
 */

public class HomeList {

    public static List<HomeBean> getHomeList(){
        List<HomeBean> list = new ArrayList<>();

        return list;
    }

    public static List<String> getJingXuanList() {
        return Arrays.asList(
                "http://www.icicle.com/assets/imgsupl/RG6.0_0.75030000_1500519999_85e5_news_images_image_1.jpg",
                "http://www.icicle.com/assets/imgsupl/RG6.0_0.04497200_1500520017_ee1c_news_images_image_3.jpg",
                "http://www.icicle.com/assets/imgsupl/RG6.0_0.36965800_1500520008_c970_news_images_image_2.jpg",
                "http://www.icicle.com/assets/imgsupl/RG6.0_0.93586600_1500520028_60db_news_images_image_4.jpg",
                "http://www.icicle.com/assets/imgsupl/RG6.0_0.58109300_1500520042_75ea_news_images_image_5.jpg"
        );
    }

    public static class HomeBean {

    }


    public static List<LookBean.LOOKBean> getLookList(){
        LookBean lookBean = GsonTools.changeGsonToBean(lookData, LookBean.class);
        return lookBean.getLOOK();
    }

    public static class LookBean {

        private List<LOOKBean> LOOK;

        public List<LOOKBean> getLOOK() {
            return LOOK;
        }

        public void setLOOK(List<LOOKBean> LOOK) {
            this.LOOK = LOOK;
        }

        public static class LOOKBean {
            private String nickname;
            private String pic;
            private String content;
            private String zan;
            private String tag;
            private List<String> images;

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getZan() {
                return zan;
            }

            public void setZan(String zan) {
                this.zan = zan;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }
        }
    }

    public static String lookData = "{\n" +
            "  \"LOOK\": [\n" +
            "    {\n" +
            "      \"nickname\": \"大萌\",\n" +
            "      \"pic\": \"https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike180%2C5%2C5%2C180%2C60/sign=50fc1bfbbd3eb13550cabfe9c777c3b6/8694a4c27d1ed21bde0219f6ac6eddc450da3fdd.jpg\",\n" +
            "      \"content\": \"很修身 设计感很好 很显瘦\",\n" +
            "      \"zan\": \"20\",\n" +
            "      \"tag\": \"欧范儿打底衫黑色\",\n" +
            "      \"images\": [\n" +
            "        \"https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike272%2C5%2C5%2C272%2C90/sign=3bdd6084ad64034f1bc0ca54ceaa1254/dbb44aed2e738bd4c33a1c68a78b87d6277ff954.jpg\",\n" +
            "        \"https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike150%2C5%2C5%2C150%2C50/sign=827c1a68b54543a9e116f29e7f7ee1e7/0b7b02087bf40ad153bc037f552c11dfa8ecced6.jpg\",\n" +
            "        \"https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike220%2C5%2C5%2C220%2C73/sign=09657edb5566d0166a14967af642bf62/eac4b74543a98226ff42d6318d82b9014a90eb21.jpg\"\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"nickname\": \"奔波儿灞\",\n" +
            "      \"pic\": \"https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=bc718acd402309f7f362a54013676796/48540923dd54564e61637b60b5de9c82d1584f61.jpg\",\n" +
            "      \"content\": \"我是奔波儿灞 我为自己代言 这衣服确实很不服来辩\uD83D\uDE15\",\n" +
            "      \"zan\": \"100\",\n" +
            "      \"tag\": \"非主流范儿打底衫黑色\",\n" +
            "      \"images\": [\n" +
            "        \"https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=d824ce31ba1bb0519b29bb7a5713b1d1/5882b2b7d0a20cf440fe3ccc7f094b36acaf99b3.jpg\",\n" +
            "        \"https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=2bbfb03ad739b60059c307e588395e4f/d000baa1cd11728b79613ffbcafcc3cec2fd2c9e.jpg\"\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"nickname\": \"霸波尔奔\",\n" +
            "      \"pic\": \"https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=f5da9a9a46086e067ea5371963611091/8435e5dde71190ef3b859474c41b9d16fdfa601a.jpg\",\n" +
            "      \"content\": \"我是霸波尔奔 我为我哥奔波儿灞代言 衣服不好你来我吃下去\",\n" +
            "      \"zan\": \"290\",\n" +
            "      \"tag\": \"打底衫黑色\",\n" +
            "      \"images\": [\n" +
            "        \"https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=2bbfb03ad739b60059c307e588395e4f/d000baa1cd11728b79613ffbcafcc3cec2fd2c9e.jpg\"\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}";
}
