package domon.cn.mmphoto.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import domon.cn.mmphoto.R;
import domon.cn.mmphoto.album.AlbumActivity;
import domon.cn.mmphoto.category.CategoryDetailActivity;
import domon.cn.mmphoto.data.MultipleItemCategory;
import domon.cn.mmphoto.data.PhotoData;
import domon.cn.mmphoto.utils.GlideUtils;

/**
 * Author : Louis
 * Email  : liangjing@cnxad.com
 * Time   : 2017/8/11 16:28
 * Version: 1.0
 * Desc   :
 */

public class MultipleHorAdapter extends BaseQuickAdapter<PhotoData, BaseViewHolder> {

    private final int type;

    public MultipleHorAdapter(@LayoutRes int layoutResId, @Nullable List<PhotoData> data) {
        super(layoutResId, data);
        type = -999;
    }

    public MultipleHorAdapter(@LayoutRes int layoutResId, int type, @Nullable List<PhotoData> data) {
        super(layoutResId, data);
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, final PhotoData item) {
        helper.setText(R.id.tv_item_multiple_horizontal, item.getAtlasTitle());
        helper.setText(R.id.tv_item_multiple_gold, "100");

        ImageView imageView = helper.getView(R.id.iv_item_multiple_horizontal);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == MultipleItemCategory.CATEGORY_TABLE) {
                    CategoryDetailActivity.startActivity(mContext, item.getAtlasID());
                } else if (type == MultipleItemCategory.CATEGORY_LIST) {
                    AlbumActivity.startActivity(mContext, item.getAtlasID());
                } else {
                    AlbumActivity.startActivity(mContext, item.getAtlasID());
                }
            }
        });
        GlideUtils.loadImageView(mContext, item.getAtlasImg(), imageView);
    }
}
