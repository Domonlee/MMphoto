package domon.cn.mmphoto.category;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import domon.cn.mmphoto.R;
import domon.cn.mmphoto.adapter.CategoryAdapter;
import domon.cn.mmphoto.base.BaseFragment;
import domon.cn.mmphoto.data.MultipleItemCategory;
import domon.cn.mmphoto.data.PhotoData;
import domon.cn.mmphoto.utils.DataServer;

/**
 * Created by Domon on 2017/8/9.
 */

public class CategoryFragment extends BaseFragment implements CategoryContract.View{

    @BindView(R.id.title_mid_tv)
    TextView mTitleTv;
    @BindView(R.id.recycler_category)
    RecyclerView mRecyclerCategory;

    private CategoryAdapter mCategoryAdapter;
    private CategoryContract.Presenter mPresenter;

    public static CategoryFragment newInstance() {
        Bundle args = new Bundle();

        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new CategoryPresenter(this);
    }

    @Override
    protected void onSetupView(View rootView) {
        mTitleTv.setText("分类");

        mRecyclerCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCategoryAdapter = new CategoryAdapter(new ArrayList<MultipleItemCategory>());
        mRecyclerCategory.setAdapter(mCategoryAdapter);

        mPresenter.requestCategory();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_category;
    }

    @Override
    public void setPresenter(CategoryContract.Presenter presenter) {
        this.mPresenter = presenter;
    }


    @Override
    public void dataSuccess(List<PhotoData> list) {
        List<MultipleItemCategory> result = DataServer.getMultipleCategoryItemData(list);
        mCategoryAdapter.setNewData(result);
    }
}
