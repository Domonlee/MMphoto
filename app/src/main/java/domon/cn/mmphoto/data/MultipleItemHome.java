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
    public static final int IMG_HORIZONTAL_SINGLE = 2;
    public static final int IMG_HORIZONTAL_MULTIPLE = 3;
    public static final int IMG_HORIZONTAL_BIG = 4;
    public static final int IMG_TABLE = 5;

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
