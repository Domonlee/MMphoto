package domon.cn.mmphoto.data;

import java.util.List;

/**
 * Author : Louis
 * Email  : liangjing@cnxad.com
 * Time   : 2017/8/14 15:58
 * Version: 1.0
 * Desc   :
 */

public class Category {


    private List<AtlasBean> atlas;

    public List<AtlasBean> getAtlas() {
        return atlas;
    }

    public void setAtlas(List<AtlasBean> atlas) {
        this.atlas = atlas;
    }

    public static class AtlasBean {

        private int type;
        private List<PhotoData> atlasList;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<PhotoData> getAtlasList() {
            return atlasList;
        }

        public void setAtlasList(List<PhotoData> atlasList) {
            this.atlasList = atlasList;
        }


    }
}
