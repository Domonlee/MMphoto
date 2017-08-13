package domon.cn.mmphoto.category;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import domon.cn.mmphoto.R;
import domon.cn.mmphoto.adapter.CategoryAdapter;
import domon.cn.mmphoto.data.MultipleItemCategory;
import domon.cn.mmphoto.home.DataServer;

/**
 * Created by Domon on 2017/8/9.
 */

public class CategoryFragment extends Fragment {
    @BindView(R.id.title_mid_tv)
    TextView mTitleTv;
    @BindView(R.id.recycler_category)
    RecyclerView mRecyclerCategory;

    private Unbinder mUnbinder;
    private CategoryAdapter mCategoryAdapter;

    public static CategoryFragment newInstance() {

        Bundle args = new Bundle();

        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        mTitleTv.setText("分类");

        mRecyclerCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCategoryAdapter = new CategoryAdapter(new ArrayList<MultipleItemCategory>());
        mRecyclerCategory.setAdapter(mCategoryAdapter);

        //test start

        ArrayList<String> urls = new ArrayList<>();

        urls.add(0, "http://zer.sistershsmy.com/MTUzOQ15391539/0718/1500350147.jpg");
        urls.add(1, "http://zer.sistershsmy.com/MTcxNQ17151715/0706/1499321422.png");
        urls.add(2, "http://zer.sistershsmy.com/MTcyMA17201720/0705/1499228376.jpg");


        //test end


        List<MultipleItemCategory> list = DataServer.getMultipleCategoryItemData(urls);
        mCategoryAdapter.setNewData(list);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
