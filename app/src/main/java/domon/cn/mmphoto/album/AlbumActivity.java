package domon.cn.mmphoto.album;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import domon.cn.mmphoto.Const;
import domon.cn.mmphoto.R;
import domon.cn.mmphoto.adapter.MultipleHorAdapter;
import domon.cn.mmphoto.callback.DialogCallback;
import domon.cn.mmphoto.callback.JsonCallback;
import domon.cn.mmphoto.data.AlbumData;
import domon.cn.mmphoto.data.BuyAlbumRep;
import domon.cn.mmphoto.data.MultipleItemCategory;
import domon.cn.mmphoto.data.PhotoData;
import domon.cn.mmphoto.profile.PayForCoinActivity;
import domon.cn.mmphoto.utils.GlideUtils;
import domon.cn.mmphoto.utils.SharedPreferenceUtil;

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
    private Unbinder mUnbinder;
    private AlbumData mAlbumData = new AlbumData();
    private MultipleHorAdapter mAdapter;
    private String mAlbumId;

    /**
     * input albumId
     *
     * @param context
     * @param albumId
     */
    public static void startActivity(Context context, String albumId) {
        Intent intent = new Intent(context, AlbumActivity.class);
        intent.putExtra(ALBUMID, albumId);
        context.startActivity(intent);
    }

    @OnClick(R.id.albumtitle_left_iv)
    void onBackClick() {
        this.finish();
    }

    @OnClick(R.id.album_main_iv)
    void onMainClick() {
        PhotoData photoData = mAlbumData.getAtlas();
        if (photoData != null) {
            if (Const.PAY_YET == photoData.getAtlasPaid()) {
                PhotoViewActivity.startActivity(this, mAlbumData.getAtlas().getAtlasID());
            } else {
                int balance = SharedPreferenceUtil.getIntegerValue("userBalance");
                if (balance > 0 && balance >= mAlbumData.getAtlas().getAtlasCost()) {
                    new MaterialDialog.Builder(this)
                            .backgroundColor(R.color.color_ff7b5b)
                            .title("购买图集")
                            .content("专辑暂不能查看,是否购买?")
                            .positiveText("购买")
                            .negativeText("再考虑一下")
                            .onPositive((dialog, which) -> {
                                //消费金币
                                requestPurchase(photoData.getID() + "");
                            })
                            .show();
                } else {
                    new MaterialDialog.Builder(this)
                            .backgroundColor(R.color.color_ff7b5b)
                            .title("金币充值")
                            .content("您的金币不足,是否进行充值?")
                            .positiveText("充值")
                            .negativeText("再考虑一下")
                            .onPositive((dialog, which) -> {
                                PayForCoinActivity.startActivity(AlbumActivity.this,PayForCoinActivity.PAYFORCOIN);
                            })
                            .show();
                }
            }
        } else {
            Toast.makeText(this, "数据有误,请稍后重试", Toast.LENGTH_SHORT).show();
        }


    }

    private void requestPurchase(String atlasID) {
        OkGo.<BuyAlbumRep>get(Const.REQ_BUY_ALBUM)
                .params("userId", SharedPreferenceUtil.getIntegerValue("userID"))
                .params("id", atlasID)
                .execute(new DialogCallback<BuyAlbumRep>(AlbumActivity.this) {
                    @Override
                    public void onSuccess(Response<BuyAlbumRep> response) {
                        reqAlbumInfo(mAlbumId);

                        BuyAlbumRep resp = response.body();
                        if (resp != null) {
                            if (resp.getStatus() >= 100) {
                                Toast.makeText(AlbumActivity.this, resp.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                PhotoViewActivity.startActivity(AlbumActivity.this, atlasID);
                            }
                        }
                    }
                });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        mUnbinder = ButterKnife.bind(this);

        mAlbumId = getIntent().getStringExtra(ALBUMID);

        initRecyclerView();

        //todo the sub view's width is wrong
        mAdapter = new MultipleHorAdapter(R.layout.item_multiple_vertical, MultipleItemCategory.CATEGORY_TABLE, mAlbumData.getRecommend());
        mRecyclerView.setAdapter(mAdapter);

        if (TextUtils.isEmpty(mAlbumId)) return;

        reqAlbumInfo(mAlbumId);
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void reqAlbumInfo(String albumId) {
        String reqUrl = Const.REQ_ALBUM_WITH_ID + albumId;

        OkGo.<AlbumData>get(reqUrl)
                .params("userId", SharedPreferenceUtil.getIntegerValue("userID"))
                .execute(new JsonCallback<AlbumData>() {
                    @Override
                    public void onSuccess(Response<AlbumData> response) {
                        mAlbumData = response.body();
                        runOnUiThread(() -> updateUI());
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
