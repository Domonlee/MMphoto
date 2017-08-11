package domon.cn.mmphoto.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import domon.cn.mmphoto.R;
import domon.cn.mmphoto.album.AlbumActivity;
import domon.cn.mmphoto.data.MultipleItemHome;
import domon.cn.mmphoto.data.PhotoData;
import domon.cn.mmphoto.utils.BannerImageLoader;

/**
 * Author : Louis
 * Email  : liangjing@cnxad.com
 * Time   : 2017/8/11 11:06
 * Version: 1.0
 * Desc   :
 */

public class HomeAdapter extends BaseMultiItemQuickAdapter<MultipleItemHome, BaseViewHolder> {

    private List<String> images;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HomeAdapter(List<MultipleItemHome> data) {
        super(data);
        addItemType(MultipleItemHome.BANNER, R.layout.item_home_banner);
        addItemType(MultipleItemHome.IMG_HORIZONTAL_SINGLE, R.layout.item_home_horizontal_multiple);
        addItemType(MultipleItemHome.IMG_HORIZONTAL_MULTIPLE, R.layout.item_home_horizontal_multiple);
        addItemType(MultipleItemHome.IMG_HORIZONTAL_BIG, R.layout.item_home_horizontal_multiple);
        addItemType(MultipleItemHome.IMG_TABLE, R.layout.item_home_horizontal_multiple);

        images = new ArrayList<>();
    }

    @Override
    protected void convert(BaseViewHolder helper, final MultipleItemHome item) {
        switch (helper.getItemViewType()) {
            case MultipleItemHome.BANNER:
                final Banner mBanner = helper.getView(R.id.home_banner);
                mBanner.setImageLoader(new BannerImageLoader());
                final List<PhotoData> banner = item.getItem();
                for (PhotoData atlasBean : banner) {
                    images.add(atlasBean.getAtlasImg());
                }

                mBanner.setImages(images);
                mBanner.start();

                mBanner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        AlbumActivity.startActivity(mContext, banner.get(position).getAtlasID());
                    }
                });
                break;

            case MultipleItemHome.IMG_HORIZONTAL_SINGLE:
                helper.setText(R.id.tv_item_horizontal_multiple_title, "最近更新");
                helper.addOnClickListener(R.id.tv_item_horizontal_multiple_more);

                RecyclerView recyclerViewSingle = helper.getView(R.id.recycler_item_top);
                LinearLayoutManager layoutManagerSingle = new LinearLayoutManager(mContext);
                layoutManagerSingle.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewSingle.setLayoutManager(layoutManagerSingle);

                MultipleHorAdapter singleAdapter = new MultipleHorAdapter(R.layout.item_multiple_vertical, item.getItem());
                recyclerViewSingle.setAdapter(singleAdapter);
                break;

            case MultipleItemHome.IMG_HORIZONTAL_MULTIPLE:
                helper.setText(R.id.tv_item_horizontal_multiple_title, "rosimm");
                helper.addOnClickListener(R.id.tv_item_horizontal_multiple_more);

                //top
                RecyclerView recyclerViewTop = helper.getView(R.id.recycler_item_top);
                LinearLayoutManager layoutManagerTop = new LinearLayoutManager(mContext);
                layoutManagerTop.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewTop.setLayoutManager(layoutManagerTop);

                MultipleHorAdapter horAdapter = new MultipleHorAdapter(R.layout.item_multiple_vertical, item.getItem());
                recyclerViewTop.setAdapter(horAdapter);

                //bottom
                RecyclerView recyclerViewBottom = helper.getView(R.id.recycler_item_bottom);
                LinearLayoutManager layoutManagerBottom = new LinearLayoutManager(mContext);
                layoutManagerBottom.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewBottom.setLayoutManager(layoutManagerBottom);

                MultipleHorAdapter verticalAdapter = new MultipleHorAdapter(R.layout.item_multiple_horizontal, item.getItem());
                recyclerViewBottom.setAdapter(verticalAdapter);
                break;

            case MultipleItemHome.IMG_HORIZONTAL_BIG:
                helper.setText(R.id.tv_item_horizontal_multiple_title, "视频写真");
                helper.addOnClickListener(R.id.tv_item_horizontal_multiple_more);
                //top
                RecyclerView recyclerViewBigTop = helper.getView(R.id.recycler_item_top);
                LinearLayoutManager layoutManagerBigTop = new LinearLayoutManager(mContext);
                layoutManagerBigTop.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewBigTop.setLayoutManager(layoutManagerBigTop);

                MultipleHorAdapter bigAdapter = new MultipleHorAdapter(R.layout.item_multiple_vertical, item.getItem());
                recyclerViewBigTop.setAdapter(bigAdapter);

                //bottom
                RecyclerView recyclerViewBigBottom = helper.getView(R.id.recycler_item_bottom);
                LinearLayoutManager layoutManagerBigBottom = new LinearLayoutManager(mContext);
                layoutManagerBigBottom.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewBigBottom.setLayoutManager(layoutManagerBigBottom);

                MultipleHorAdapter bigBottomAdapter = new MultipleHorAdapter(R.layout.item_multiple_big, item.getItem().subList(0, 1));
                recyclerViewBigBottom.setAdapter(bigBottomAdapter);

                break;

            case MultipleItemHome.IMG_TABLE:
                helper.setText(R.id.tv_item_horizontal_multiple_title, "热门排行");
                helper.addOnClickListener(R.id.tv_item_horizontal_multiple_more);

                RecyclerView recyclerViewTable = helper.getView(R.id.recycler_item_top);
                StaggeredGridLayoutManager layoutManagerTable = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL);
                recyclerViewTable.setLayoutManager(layoutManagerTable);

                MultipleHorAdapter tableAdapter = new MultipleHorAdapter(R.layout.item_multiple_grid, item.getItem());
                recyclerViewTable.setAdapter(tableAdapter);
                break;
        }
    }
}
