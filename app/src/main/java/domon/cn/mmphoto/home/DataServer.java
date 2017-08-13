package domon.cn.mmphoto.home;

import java.util.ArrayList;
import java.util.List;

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

    public static List<MultipleItemHome> getMultipleItemData(List<PhotoData> list) {
        List<MultipleItemHome> result = new ArrayList<>();
        if (list != null && list.size() > 0) {
            result.add(new MultipleItemHome(MultipleItemHome.BANNER, list.subList(0, 5)));
            result.add(new MultipleItemHome(MultipleItemHome.IMG_HORIZONTAL_MULTIPLE, list.subList(0, 10)));
            result.add(new MultipleItemHome(MultipleItemHome.IMG_TABLE, list.subList(0, 10)));
            result.add(new MultipleItemHome(MultipleItemHome.IMG_HORIZONTAL_SINGLE, list.subList(0, 10)));
            result.add(new MultipleItemHome(MultipleItemHome.IMG_HORIZONTAL_BIG, list.subList(0, 10)));
        }
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
