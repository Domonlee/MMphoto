package domon.cn.mmphoto.category;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import domon.cn.mmphoto.Const;
import domon.cn.mmphoto.R;
import domon.cn.mmphoto.adapter.MultipleHorAdapter;
import domon.cn.mmphoto.callback.JsonCallback;
import domon.cn.mmphoto.data.CategoryDetailData;
import domon.cn.mmphoto.data.PhotoData;

/**
 * Created by Domon on 2017/8/13.
 */

public class CategoryDetailActivity extends AppCompatActivity {
    public static final String CATEGORY_TYPE = "category_type";

    @BindView(R.id.title_mid_tv)
    TextView mTitleTv;
    @BindView(R.id.recycler_detail)
    RecyclerView mRecyclerView;

    private MultipleHorAdapter mAdapter;

    private Unbinder mUnbinder;
    private int mCategoryType;

    /**
     * show input type list for more detail
     *
     * @param context
     * @param type    category type(1 for rosi;2 for xxx)
     */
    public static void startActivity(Context context, int type) {
        Intent intent = new Intent(context, CategoryDetailActivity.class);
        intent.putExtra(CategoryDetailActivity.CATEGORY_TYPE, type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);

        mUnbinder = ButterKnife.bind(this);
        mTitleTv.setText(CategoryFragment.CATEGORY_ROSI);
        mCategoryType = getIntent().getIntExtra(CategoryDetailActivity.CATEGORY_TYPE, 1);

        initRecyclerView();
        reqCategoryDetail();
    }

    private void initRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        mAdapter = new MultipleHorAdapter(R.layout.item_multiple_vertical,
                new ArrayList<PhotoData>());
        mRecyclerView.setAdapter(mAdapter);
    }

    private void reqCategoryDetail() {
        OkGo.<CategoryDetailData>get(Const.REQ_CATEGORY_WITH_TYPE + mCategoryType)
                .execute(new JsonCallback<CategoryDetailData>() {
                    @Override
                    public void onSuccess(Response<CategoryDetailData> response) {
                        mAdapter.setNewData(response.body().getAtlas().get(mCategoryType - 1).getAtlasList());
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
