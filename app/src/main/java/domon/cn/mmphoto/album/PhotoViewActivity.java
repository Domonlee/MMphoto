package domon.cn.mmphoto.album;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import domon.cn.mmphoto.Const;
import domon.cn.mmphoto.R;

/**
 * Created by Domon on 2017/8/10.
 */

public class PhotoViewActivity extends AppCompatActivity {
    public static final String ALBUMID = "albumId";
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
    private static List<String> mPhotoUrls = new ArrayList<>();
    private String mAlbumID;

    public static void startActivity(Context context, String albumId) {
        Intent intent = new Intent(context, PhotoViewActivity.class);
        intent.putExtra(ALBUMID, albumId);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoview);

        mUnbinder = ButterKnife.bind(this);

        mAlbumID = getIntent().getStringExtra(ALBUMID);

        reqPhotos();

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

    private void reqPhotos() {
        final String reqUrl = Const.REQ_IMGLIST_WITH_ID + mAlbumID;

        OkGo.<String>get(reqUrl)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        JsonObject jsonObject = new JsonParser().parse(response.body()).getAsJsonObject();
                        JsonArray jsonArray = jsonObject.getAsJsonArray("imglist");

                        for (int i = 0; i < jsonArray.size(); i++) {
                            mPhotoUrls.add(i, jsonArray.get(i).getAsJsonObject().get("ImgUrl").toString());
                        }

                        LogUtils.e(mPhotoUrls.size());
                        for (int i = 0; i < mPhotoUrls.size(); i++) {
                            LogUtils.e(mPhotoUrls.get(i));
                        }
                        mAdapter.setUrls(mPhotoUrls);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
