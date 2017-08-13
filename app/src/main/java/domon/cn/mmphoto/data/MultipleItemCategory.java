package domon.cn.mmphoto.data;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class MultipleItemCategory implements MultiItemEntity {

    public static final int BIG_CATEGORY = 1;
    public static final int SMAlL_CATEGORY = 2;
    public static final int SPLIT_LINE = 3;
    public static final int LIST_KNOWYOU = 4;

    private int itemType;
    private List<String> urls;

    public MultipleItemCategory(int itemType, List<String> ulrs) {
        this.itemType = itemType;
        this.urls = ulrs;
    }

    public MultipleItemCategory(int itemType) {
        this.itemType = itemType;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
