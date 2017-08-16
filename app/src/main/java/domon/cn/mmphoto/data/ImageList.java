package domon.cn.mmphoto.data;

import java.util.List;

/**
 * Author : Louis
 * Email  : liangjing@cnxad.com
 * Time   : 2017/8/16 20:20
 * Version: 1.0
 * Desc   :
 */

public class ImageList {
    private List<ImglistBean> imglist;

    public List<ImglistBean> getImglist() {
        return imglist;
    }

    public void setImglist(List<ImglistBean> imglist) {
        this.imglist = imglist;
    }

    public static class ImglistBean {
        /**
         * ID : 926
         * ImgUrl : http://uuu.shafa5.com/ros/2308/rosmm-2055-1.jpg
         * AtlasID : 2308
         */

        private int ID;
        private String ImgUrl;
        private String AtlasID;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getImgUrl() {
            return ImgUrl;
        }

        public void setImgUrl(String ImgUrl) {
            this.ImgUrl = ImgUrl;
        }

        public String getAtlasID() {
            return AtlasID;
        }

        public void setAtlasID(String AtlasID) {
            this.AtlasID = AtlasID;
        }
    }
}
