package domon.cn.mmphoto.home;

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

    public static List<MultipleItemCategory> getMultipleCategoryItemData(ArrayList<String> urls){

        List<MultipleItemCategory> result = new ArrayList<>();

        if (result != null && result.size() > 0){
            result.add(new MultipleItemCategory(MultipleItemCategory.BIG_CATEGORY,urls));
        }


        return result;

    }
}
