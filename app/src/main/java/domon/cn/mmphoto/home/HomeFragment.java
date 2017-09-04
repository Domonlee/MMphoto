package domon.cn.mmphoto.home;

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
import domon.cn.mmphoto.adapter.HomeAdapter;
import domon.cn.mmphoto.base.BaseFragment;
import domon.cn.mmphoto.data.Atlas;
import domon.cn.mmphoto.data.MultipleItemHome;
import domon.cn.mmphoto.utils.DataServer;

/**
 * Created by Domon on 2017/8/9.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View {

    @BindView(R.id.title_mid_tv)
    TextView mTitleTv;
    @BindView(R.id.recycler_home)
    RecyclerView mRecyclerHome;

    private HomeContract.Presenter mPresenter;
    private HomeAdapter mHomeAdapter;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new HomePresenter(this);
    }

    @Override
    protected void onSetupView(View rootView) {
        mTitleTv.setText("推荐");

        mRecyclerHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHomeAdapter = new HomeAdapter(new ArrayList<MultipleItemHome>());
        mRecyclerHome.setAdapter(mHomeAdapter);

        mPresenter.requestHomeData();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showEmptyError() {

    }

    @Override
    public void dataSuccess(Atlas atlas) {
        List<MultipleItemHome> reslut = DataServer.getMultipleItemData(atlas);
        mHomeAdapter.setNewData(reslut);
    }
}
