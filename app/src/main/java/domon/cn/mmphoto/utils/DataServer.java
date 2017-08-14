package domon.cn.mmphoto.utils;

import java.util.ArrayList;
import java.util.List;

import domon.cn.mmphoto.data.Atlas;
import domon.cn.mmphoto.data.MultipleItemCategory;
import domon.cn.mmphoto.data.MultipleItemHome;
import domon.cn.mmphoto.data.PhotoData;

/**
 * Author : Louis
 * Email  : liangjing@cnxad.com
 * Time   : 2017/8/11 14:47
 * Version: 1.0
 * Desc   :
 */

public class DataServer {

    public static List<MultipleItemHome> getMultipleItemData(Atlas atlas) {
        List<MultipleItemHome> result = new ArrayList<>();

        result.add(new MultipleItemHome(MultipleItemHome.BANNER, atlas.getBanner()));
        result.add(new MultipleItemHome(MultipleItemHome.IMG_HORIZONTAL_MULTIPLE, atlas.getRose()));
        result.add(new MultipleItemHome(MultipleItemHome.IMG_TABLE, atlas.getImiss()));
        result.add(new MultipleItemHome(MultipleItemHome.IMG_HORIZONTAL_SINGLE, atlas.getRose()));
        result.add(new MultipleItemHome(MultipleItemHome.IMG_HORIZONTAL_BIG, atlas.getImiss()));

        return result;
    }

    public static List<MultipleItemCategory> getMultipleCategoryItemData(List<PhotoData> list){
        List<MultipleItemCategory> result = new ArrayList<>();

        List<PhotoData> categoryList = new ArrayList<>();
        PhotoData item = new PhotoData();
        item.setAtlasID(1+"");
        item.setAtlasTitle("冷艳尤物");
        categoryList.add(item);

        item = new PhotoData();
        item.setAtlasID(2+"");
        item.setAtlasTitle("可爱萌妹");
        categoryList.add(item);

        item = new PhotoData();
        item.setAtlasID(3+"");
        item.setAtlasTitle("极品身材");
        categoryList.add(item);

        item = new PhotoData();
        item.setAtlasID(4+"");
        item.setAtlasTitle("唯美清新");
        categoryList.add(item);

        result.add(new MultipleItemCategory(MultipleItemCategory.CATEGORY_TABLE,categoryList));

        if (list != null && list.size() > 0) {
            result.add(new MultipleItemCategory(MultipleItemCategory.CATEGORY_LIST,list));
        }

        return result;

    }
}
