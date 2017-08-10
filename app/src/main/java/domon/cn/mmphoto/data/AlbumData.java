package domon.cn.mmphoto.data;

import java.util.List;

/**
 * Created by Domon on 2017/8/10.
 */

public class AlbumData {


    /**
     * atlas : {"ID":38,"AtlasID":2300,"AtlasTitle":"ROSI写真No.2047 浴室真空肉丝美臀玉腿写真","AtlasImg":"http://uuu.shafa5.com/ros/2300/2047.jpg","AtlasToUrl":"http://www.rosmm.com/rosimm/2017/07/15/2300.htm","AtlasCost":20}
     * recommend : [{"ID":12,"AtlasID":2326,"AtlasTitle":"ROSI写真No.2073 黑色连体开档皮衣翘臀","AtlasImg":"http://uuu.shafa5.com/ros/2326/2073.jpg","AtlasToUrl":"http://www.rosmm.com/rosimm/2017/08/10/2326.htm","AtlasCost":20},{"ID":13,"AtlasID":2325,"AtlasTitle":"ROSI写真No.2072 透视黑纱睡衣金色短裤","AtlasImg":"http://uuu.shafa5.com/ros/2325/2072.jpg","AtlasToUrl":"http://www.rosmm.com/rosimm/2017/08/09/2325.htm","AtlasCost":20},{"ID":14,"AtlasID":2324,"AtlasTitle":"ROSI写真No.2071 运动装一字马劈腿展示","AtlasImg":"http://uuu.shafa5.com/ros/2324/2071.jpg","AtlasToUrl":"http://www.rosmm.com/rosimm/2017/08/08/2324.htm","AtlasCost":20},{"ID":15,"AtlasID":2323,"AtlasTitle":"ROSI写真No.2070 黑色情趣睡衣吊带丝袜性感写真","AtlasImg":"http://uuu.shafa5.com/ros/2323/2070.jpg","AtlasToUrl":"http://www.rosmm.com/rosimm/2017/08/07/2323.htm","AtlasCost":20},{"ID":16,"AtlasID":2322,"AtlasTitle":"ROSI写真No.2069 巨乳红色包臀短裙性感撩人","AtlasImg":"http://uuu.shafa5.com/ros/2322/2069.jpg","AtlasToUrl":"http://www.rosmm.com/rosimm/2017/08/06/2322.htm","AtlasCost":20},{"ID":17,"AtlasID":2321,"AtlasTitle":"ROSI写真No.2068 红色背背短裙抹胸花内内","AtlasImg":"http://uuu.shafa5.com/ros/2321/2068.jpg","AtlasToUrl":"http://www.rosmm.com/rosimm/2017/08/05/2321.htm","AtlasCost":20},{"ID":18,"AtlasID":2320,"AtlasTitle":"ROSI写真No.2067 衬衣黑丝倒挂金钩表演","AtlasImg":"http://uuu.shafa5.com/ros/2320/2067.jpg","AtlasToUrl":"http://www.rosmm.com/rosimm/2017/08/04/2320.htm","AtlasCost":20},{"ID":19,"AtlasID":2319,"AtlasTitle":"ROSI写真No.2066 马尾萝莉内衣肉丝写真","AtlasImg":"http://uuu.shafa5.com/ros/2319/2066.jpg","AtlasToUrl":"http://www.rosmm.com/rosimm/2017/08/03/2319.htm","AtlasCost":20},{"ID":20,"AtlasID":2318,"AtlasTitle":"ROSI写真No.2065 黑色蕾丝背心紫色内内","AtlasImg":"http://uuu.shafa5.com/ros/2318/2065.jpg","AtlasToUrl":"http://www.rosmm.com/rosimm/2017/08/02/2318.htm","AtlasCost":20},{"ID":21,"AtlasID":2317,"AtlasTitle":"ROSI写真No.2064 半裸红色丝袜圆润翘臀秀","AtlasImg":"http://uuu.shafa5.com/ros/2317/2064.jpg","AtlasToUrl":"http://www.rosmm.com/rosimm/2017/08/01/2317.htm","AtlasCost":20}]
     */

    private AtlasEntity atlas;
    private List<RecommendEntity> recommend;

    public AtlasEntity getAtlas() {
        return atlas;
    }

    public void setAtlas(AtlasEntity atlas) {
        this.atlas = atlas;
    }

    public List<RecommendEntity> getRecommend() {
        return recommend;
    }

    public void setRecommend(List<RecommendEntity> recommend) {
        this.recommend = recommend;
    }

    public static class AtlasEntity {
        /**
         * ID : 38
         * AtlasID : 2300
         * AtlasTitle : ROSI写真No.2047 浴室真空肉丝美臀玉腿写真
         * AtlasImg : http://uuu.shafa5.com/ros/2300/2047.jpg
         * AtlasToUrl : http://www.rosmm.com/rosimm/2017/07/15/2300.htm
         * AtlasCost : 20
         */

        private int ID;
        private int AtlasID;
        private String AtlasTitle;
        private String AtlasImg;
        private String AtlasToUrl;
        private int AtlasCost;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public int getAtlasID() {
            return AtlasID;
        }

        public void setAtlasID(int AtlasID) {
            this.AtlasID = AtlasID;
        }

        public String getAtlasTitle() {
            return AtlasTitle;
        }

        public void setAtlasTitle(String AtlasTitle) {
            this.AtlasTitle = AtlasTitle;
        }

        public String getAtlasImg() {
            return AtlasImg;
        }

        public void setAtlasImg(String AtlasImg) {
            this.AtlasImg = AtlasImg;
        }

        public String getAtlasToUrl() {
            return AtlasToUrl;
        }

        public void setAtlasToUrl(String AtlasToUrl) {
            this.AtlasToUrl = AtlasToUrl;
        }

        public int getAtlasCost() {
            return AtlasCost;
        }

        public void setAtlasCost(int AtlasCost) {
            this.AtlasCost = AtlasCost;
        }
    }

    public static class RecommendEntity {
        /**
         * ID : 12
         * AtlasID : 2326
         * AtlasTitle : ROSI写真No.2073 黑色连体开档皮衣翘臀
         * AtlasImg : http://uuu.shafa5.com/ros/2326/2073.jpg
         * AtlasToUrl : http://www.rosmm.com/rosimm/2017/08/10/2326.htm
         * AtlasCost : 20
         */

        private int ID;
        private int AtlasID;
        private String AtlasTitle;
        private String AtlasImg;
        private String AtlasToUrl;
        private int AtlasCost;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public int getAtlasID() {
            return AtlasID;
        }

        public void setAtlasID(int AtlasID) {
            this.AtlasID = AtlasID;
        }

        public String getAtlasTitle() {
            return AtlasTitle;
        }

        public void setAtlasTitle(String AtlasTitle) {
            this.AtlasTitle = AtlasTitle;
        }

        public String getAtlasImg() {
            return AtlasImg;
        }

        public void setAtlasImg(String AtlasImg) {
            this.AtlasImg = AtlasImg;
        }

        public String getAtlasToUrl() {
            return AtlasToUrl;
        }

        public void setAtlasToUrl(String AtlasToUrl) {
            this.AtlasToUrl = AtlasToUrl;
        }

        public int getAtlasCost() {
            return AtlasCost;
        }

        public void setAtlasCost(int AtlasCost) {
            this.AtlasCost = AtlasCost;
        }
    }
}
