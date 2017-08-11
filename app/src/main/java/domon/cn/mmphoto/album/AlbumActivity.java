package domon.cn.mmphoto.album;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import domon.cn.mmphoto.R;
import domon.cn.mmphoto.callback.JsonCallback;
import domon.cn.mmphoto.data.AlbumData;
import domon.cn.mmphoto.utils.GlideUtils;

/**
 * Created by Domon on 2017/8/9.
 */

public class AlbumActivity extends AppCompatActivity {
    public static final String ALBUMID = "albumId";
    @BindView(R.id.albumtitle_mid_tv)
    TextView mTitleTv;
    @BindView(R.id.album_more_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.album_main_iv)
    ImageView mAlbumMainIv;

    @OnClick(R.id.albumtitle_left_iv)
    void onBackClick() {
        this.finish();
    }

    @OnClick(R.id.album_main_iv)
    void onMainClick() {
        PhotoViewActivity.startActivity(this, mAlbumData.getAtlas().getAtlasID());
    }

    private Unbinder mUnbinder;
    private AlbumData mAlbumData = new AlbumData();
    private AlbumRecyclerAdapter mAdapter;
    private int mAlbumId;

    /**
     * input albumId
     *
     * @param context
     * @param albumId
     */
    public static void startActivity(Context context, int albumId) {
        Intent intent = new Intent(context, AlbumActivity.class);
        intent.putExtra(ALBUMID, albumId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        mUnbinder = ButterKnife.bind(this);

        mAlbumId = getIntent().getIntExtra(ALBUMID, 0);

        initRecyclerView();

        //todo the sub view's width is wrong
        mAdapter = new AlbumRecyclerAdapter(mAlbumData.getRecommend());
        mRecyclerView.setAdapter(mAdapter);
        reqAlbumInfo();
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void reqAlbumInfo() {
        String testUrl = "http://uuu.shafa5.com/GetAtlas.ashx?id=2300";
        String reqUrl = "http://uuu.shafa5.com/GetAtlas.ashx?id=" + mAlbumId;

        OkGo.<AlbumData>get(testUrl)
                .execute(new JsonCallback<AlbumData>() {
                    @Override
                    public void onSuccess(Response<AlbumData> response) {
                        mAlbumData = response.body();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateUI();
                            }
                        });
                    }
                });
    }

    private void updateUI() {
        mTitleTv.setText(mAlbumData.getAtlas().getAtlasTitle());
        GlideUtils.loadImageView(this, mAlbumData.getAtlas().getAtlasImg(), mAlbumMainIv);

        mAdapter.setNewData(mAlbumData.getRecommend());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
