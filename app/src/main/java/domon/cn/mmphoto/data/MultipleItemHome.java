package domon.cn.mmphoto.data;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Author : Louis
 * Email  : liangjing@cnxad.com
 * Time   : 2017/8/11 11:39
 * Version: 1.0
 * Desc   :
 */

public class MultipleItemHome implements MultiItemEntity {

    public static final int BANNER = 1;
    public static final int IMG_IMISS = 2;
    public static final int IMG_MILLTAO = 3;
    public static final int IMG_ROSE = 4;
    public static final int IMG_TGOD = 5;
    public static final int IMG_BOLOLI = 6;

    private int spanSize;
    private int itemType;
    private List<PhotoData> item;

    public MultipleItemHome(int itemType, List<PhotoData> item) {
        this.itemType = itemType;
        this.item = item;
    }

    public MultipleItemHome(int itemType) {
        this.itemType = itemType;
    }

    public List<PhotoData> getItem() {
        return item;
    }

    public void setItem(List<PhotoData> item) {
        this.item = item;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
