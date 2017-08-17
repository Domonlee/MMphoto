package domon.cn.mmphoto.album;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import domon.cn.mmphoto.Const;
import domon.cn.mmphoto.R;
import domon.cn.mmphoto.callback.JsonCallback;
import domon.cn.mmphoto.data.ImageList;
import domon.cn.mmphoto.utils.SharedPreferenceUtil;

/**
 * Created by Domon on 2017/8/10.
 */

public class PhotoViewActivity extends AppCompatActivity {
    public static final String ALBUMID = "albumId";
    @BindView(R.id.photo_view_vp)
    PhotoViewPager mPhotoViewPager;
    @BindView(R.id.photo_position_tv)
    TextView mPhotoPositionTv;
    private Unbinder mUnbinder;
    private String mAlbumID;
    private PhotoViewAdapter mAdapter;
    private List<View> mViews;

    public static void startActivity(Context context, String albumId) {
        Intent intent = new Intent(context, PhotoViewActivity.class);
        intent.putExtra(ALBUMID, albumId);

        context.startActivity(intent);
    }

    @OnClick(R.id.photo_download_iv)
    void onDownloadClick() {
        //todo download pic
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoview);

        mUnbinder = ButterKnife.bind(this);

        mAlbumID = getIntent().getStringExtra(ALBUMID);

        reqPhotos();

//        mAdapter = new PhotoViewAdapter(mPhotoUrls, this);
//        mPhotoViewPager.setAdapter(mAdapter);
////        mPhotoViewPager.setCurrentItem();
//
//        mPhotoViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//                //currentPositon = position;
//            }
//        });
    }

    private void reqPhotos() {
        final String reqUrl = Const.REQ_IMGLIST_WITH_ID + mAlbumID;

        OkGo.<ImageList>get(reqUrl)
                .params("userId", SharedPreferenceUtil.getIntegerValue("userID"))
                .execute(new JsonCallback<ImageList>() {
                    @Override
                    public void onSuccess(Response<ImageList> response) {
                        ImageList body = response.body();
                        if (body != null) {
                            List<ImageList.ImglistBean> list = body.getImglist();
//                            mViews = DataServer.getImageViews(PhotoViewActivity.this, list);
                            if (list != null && list.size() > 0) {
                                mAdapter = new PhotoViewAdapter(list);
                                mPhotoViewPager.setAdapter(mAdapter);
                            }

                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
