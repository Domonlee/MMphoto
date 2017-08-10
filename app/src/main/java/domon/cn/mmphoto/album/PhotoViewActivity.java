package domon.cn.mmphoto.album;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import domon.cn.mmphoto.R;

/**
 * Created by Domon on 2017/8/10.
 */

public class PhotoViewActivity extends AppCompatActivity {
    @BindView(R.id.photo_view_vp)
    PhotoViewPager mPhotoViewPager;
    @BindView(R.id.photo_position_tv)
    TextView mPhotoPositionTv;

    @OnClick(R.id.photo_download_iv)
    void onDownloadClick() {
        //todo download pic
    }

    private Unbinder mUnbinder;
    private PhotoViewAdapter mAdapter;
    private List<String> mPhotoUrls = new ArrayList<>();

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, PhotoViewActivity.class);
        //todo get urls
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoview);

        mUnbinder = ButterKnife.bind(this);

        mPhotoUrls.add(0, "http://zer.sistershsmy.com/MTUzOQ15391539/0718/1500350147.jpg");
        mPhotoUrls.add(1, "http://zer.sistershsmy.com/MTcxNQ17151715/0706/1499321422.png");
        mPhotoUrls.add(2, "http://zer.sistershsmy.com/MTcyMA17201720/0705/1499228376.jpg");

        mAdapter = new PhotoViewAdapter(mPhotoUrls, this);
        mPhotoViewPager.setAdapter(mAdapter);
//        mPhotoViewPager.setCurrentItem();

        mPhotoViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                //currentPositon = position;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
