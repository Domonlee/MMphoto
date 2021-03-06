package domon.cn.mmphoto.category;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import domon.cn.mmphoto.Const;
import domon.cn.mmphoto.R;
import domon.cn.mmphoto.adapter.MultipleHorAdapter;
import domon.cn.mmphoto.base.BaseActivity;
import domon.cn.mmphoto.callback.JsonCallback;
import domon.cn.mmphoto.data.CategoryDetailData;
import domon.cn.mmphoto.data.PhotoData;
import domon.cn.mmphoto.utils.SharedPreferenceUtil;

/**
 * Created by Domon on 2017/8/13.
 */

public class CategoryDetailActivity extends BaseActivity {
    public static final String CATEGORY_TYPE = "category_type";

    @BindView(R.id.title_mid_tv)
    TextView mTitleTv;
    @BindView(R.id.recycler_detail)
    RecyclerView mRecyclerView;

    private MultipleHorAdapter mAdapter;

    private int mCategoryType;
    private List<String> mTitleList = Arrays.asList("ROSI肉丝", "IMISS爱蜜社", "BoLoli波萝社", "MiiTao蜜桃社", "推女神");

    /**
     * show input type list for more detail
     *
     * @param context
     * @param type
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

        mCategoryType = getIntent().getIntExtra(CategoryDetailActivity.CATEGORY_TYPE, 0);
        if (mCategoryType > 0) {
            mTitleTv.setText(mTitleList.get(mCategoryType - 1));
        }

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
                .params("userId", SharedPreferenceUtil.getIntegerValue("userID"))
                .execute(new JsonCallback<CategoryDetailData>() {
                    @Override
                    public void onSuccess(Response<CategoryDetailData> response) {
                        CategoryDetailData result = response.body();
                        if (result != null) {
                            List<CategoryDetailData.AtlasBean> list = result.getAtlas();
                            if (list != null && list.size() > 0) {
                                mAdapter.setNewData(list.get(0).getAtlasList());

                            }
                        }
                    }
                });
    }
}
