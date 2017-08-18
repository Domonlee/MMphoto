package domon.cn.mmphoto.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;
import java.util.List;

import domon.cn.mmphoto.R;
import domon.cn.mmphoto.data.Atlas;
import domon.cn.mmphoto.data.ImageList;
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
        result.add(new MultipleItemHome(MultipleItemHome.IMG_IMISS, atlas.getImiss()));
        result.add(new MultipleItemHome(MultipleItemHome.IMG_MILLTAO, atlas.getMiitao()));
        result.add(new MultipleItemHome(MultipleItemHome.IMG_ROSE, atlas.getRose()));
        result.add(new MultipleItemHome(MultipleItemHome.IMG_TGOD, atlas.getTgod()));

        return result;
    }

    public static List<MultipleItemCategory> getMultipleCategoryItemData(List<PhotoData> list) {
        List<MultipleItemCategory> result = new ArrayList<>();

        List<PhotoData> categoryList = new ArrayList<>();
        PhotoData item = new PhotoData();
        item.setAtlasID(1 + "");
        item.setAtlasTitle("冷艳尤物");
        item.setLocalResId(R.mipmap.fl1);
        categoryList.add(item);

        item = new PhotoData();
        item.setAtlasID(2 + "");
        item.setAtlasTitle("可爱萌妹");
        item.setLocalResId(R.mipmap.fl2);
        categoryList.add(item);

        item = new PhotoData();
        item.setAtlasID(3 + "");
        item.setAtlasTitle("极品身材");
        item.setLocalResId(R.mipmap.fl3);
        categoryList.add(item);

        item = new PhotoData();
        item.setAtlasID(4 + "");
        item.setAtlasTitle("唯美清新");
        item.setLocalResId(R.mipmap.fl4);
        categoryList.add(item);

        result.add(new MultipleItemCategory(MultipleItemCategory.CATEGORY_TABLE, categoryList));

        if (list != null && list.size() > 0) {
            result.add(new MultipleItemCategory(MultipleItemCategory.CATEGORY_LIST, list));
        }

        return result;

    }


    public static List<View> getImageViews(Context mContext, List<ImageList.ImglistBean> list) {
        List<View> views = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (ImageList.ImglistBean item : list) {
                PhotoView photoView = new PhotoView(mContext);
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                photoView.setLayoutParams(layoutParams);
                Glide.with(mContext)
                        .load(item.getImgUrl())
                        .placeholder(R.mipmap.album_background)
                        .into(new SimpleTarget<GlideDrawable>() {
                            @Override
                            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                                photoView.setImageDrawable(resource);
                            }
                        });
                views.add(photoView);
            }
        }
        return views;
    }
}
