package domon.cn.mmphoto.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import domon.cn.mmphoto.R;
import domon.cn.mmphoto.data.MultipleItemCategory;

public class CategoryAdapter extends BaseMultiItemQuickAdapter<MultipleItemCategory, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public CategoryAdapter(List<MultipleItemCategory> data) {
        super(data);
        addItemType(MultipleItemCategory.CATEGORY_TABLE, R.layout.item_category);
        addItemType(MultipleItemCategory.CATEGORY_LIST, R.layout.item_category);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItemCategory item) {
        RecyclerView recyclerView = helper.getView(R.id.recycler_item_category);

        switch (helper.getItemViewType()) {
            case MultipleItemCategory.CATEGORY_TABLE:
                GridLayoutManager layoutManagerTable = new GridLayoutManager(mContext, 2);
                recyclerView.setLayoutManager(layoutManagerTable);

                MultipleCategoryAdapter tableAdapter = new MultipleCategoryAdapter(R.layout.item_multiple_horizontal, MultipleItemCategory.CATEGORY_TABLE, item.getList());
                recyclerView.setAdapter(tableAdapter);

                break;
            case MultipleItemCategory.CATEGORY_LIST:
                LinearLayoutManager layoutManagerList = new LinearLayoutManager(mContext);
                layoutManagerList.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManagerList);

                MultipleCategoryAdapter listAdapter = new MultipleCategoryAdapter(R.layout.item_multiple_big, MultipleItemCategory.CATEGORY_LIST, item.getList());
                recyclerView.setAdapter(listAdapter);

                break;
            default:
                break;
        }
    }
}
