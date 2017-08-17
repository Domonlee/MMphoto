package domon.cn.mmphoto.album;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.List;

import domon.cn.mmphoto.R;
import domon.cn.mmphoto.data.ImageList;

/**
 * Created by Domon on 2017/8/10.
 */

public class PhotoViewAdapter extends PagerAdapter {
    private List<ImageList.ImglistBean> list;

    public PhotoViewAdapter(List<ImageList.ImglistBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageList.ImglistBean item = list.get(position);
        PhotoView photoView = new PhotoView(container.getContext());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        photoView.setLayoutParams(layoutParams);
        Glide.with(container.getContext())
                .load(item.getImgUrl())
                .placeholder(R.mipmap.album_background)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        photoView.setImageDrawable(resource);
                    }
                });

        container.addView(photoView);
        return photoView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
