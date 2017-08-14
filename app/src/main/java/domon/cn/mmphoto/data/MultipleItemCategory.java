package domon.cn.mmphoto.data;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class MultipleItemCategory implements MultiItemEntity {

    public static final int CATEGORY_TABLE = 1;
    public static final int CATEGORY_LIST = 2;

    private int itemType;
    private List<PhotoData> list;

    public MultipleItemCategory(int itemType, List<PhotoData> list) {
        this.itemType = itemType;
        this.list = list;
    }

    public List<PhotoData> getList() {
        return list;
    }

    public void setList(List<PhotoData> list) {
        this.list = list;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
