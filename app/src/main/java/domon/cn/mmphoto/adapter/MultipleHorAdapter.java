package domon.cn.mmphoto.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import domon.cn.mmphoto.R;
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


    public MultipleHorAdapter(@LayoutRes int layoutResId, @Nullable List<PhotoData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PhotoData item) {
        helper.setText(R.id.tv_item_multiple_horizontal, item.getAtlasTitle());

        ImageView imageView = helper.getView(R.id.iv_item_multiple_horizontal);
        GlideUtils.loadImageView(mContext, item.getAtlasImg(), imageView);
    }
}
