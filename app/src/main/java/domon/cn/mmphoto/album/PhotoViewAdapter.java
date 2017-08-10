package domon.cn.mmphoto.album;

import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.github.chrisbanes.photoview.PhotoView;
import com.orhanobut.logger.Logger;

import java.util.List;

import domon.cn.mmphoto.utils.GlideUtils;

/**
 * Created by Domon on 2017/8/10.
 */

public class PhotoViewAdapter extends PagerAdapter {
    public static final String TAG = PhotoViewAdapter.class.getSimpleName();
    private List<String> urls;
    private AppCompatActivity mAppCompatActivity;

    public PhotoViewAdapter(List<String> urls, AppCompatActivity appCompatActivity) {
        this.urls = urls;
        this.mAppCompatActivity = appCompatActivity;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        String url = urls.get(position);
        PhotoView photoView = new PhotoView(mAppCompatActivity);
        GlideUtils.loadImageView(mAppCompatActivity, url, photoView);

        container.addView(photoView);

        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logger.d(TAG, "onClick photoView");
                mAppCompatActivity.finish();
            }
        });

        return photoView;
    }

    @Override
    public int getCount() {
        return urls != null ? urls.size() : 0;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}