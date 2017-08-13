package domon.cn.mmphoto.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import domon.cn.mmphoto.R;
import domon.cn.mmphoto.data.MultipleItemCategory;
import domon.cn.mmphoto.utils.GlideUtils;

public class CategoryAdapter extends BaseMultiItemQuickAdapter<MultipleItemCategory, BaseViewHolder> {

    private List<String> images;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public CategoryAdapter(List<MultipleItemCategory> data) {
        super(data);
        addItemType(MultipleItemCategory.BIG_CATEGORY, R.layout.item_multiple_horizontal);
        addItemType(MultipleItemCategory.SMAlL_CATEGORY, R.layout.item_multiple_horizontal);
        addItemType(MultipleItemCategory.SPLIT_LINE, R.layout.item_multiple_horizontal);
        addItemType(MultipleItemCategory.LIST_KNOWYOU, R.layout.item_multiple_horizontal);

        images = new ArrayList<>();
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItemCategory item) {


        //todo change type
        switch (helper.getItemViewType()) {
            case MultipleItemCategory.BIG_CATEGORY:
                helper.getView(R.id.tv_item_horizontal_multiple_title).setVisibility(View.GONE);
                helper.getView(R.id.tv_item_horizontal_multiple_more).setVisibility(View.GONE);


                ImageView imageView = helper.getView(R.id.iv_item_multiple_horizontal);

                GlideUtils.loadImageView(mContext,item.getUrls().get(1),imageView);

                break;
            default:
                break;
        }
    }
}
