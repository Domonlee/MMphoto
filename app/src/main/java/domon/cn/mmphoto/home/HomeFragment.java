package domon.cn.mmphoto.home;

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
import domon.cn.mmphoto.adapter.HomeAdapter;
import domon.cn.mmphoto.data.MultipleItemHome;
import domon.cn.mmphoto.data.PhotoData;

/**
 * Created by Domon on 2017/8/9.
 */

public class HomeFragment extends Fragment implements HomeContract.View {

    @BindView(R.id.title_mid_tv)
    TextView mTitleTv;
    @BindView(R.id.recycler_home)
    RecyclerView mRecyclerHome;

    private Unbinder mUnbinder;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        mTitleTv.setText("推荐");

        mRecyclerHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHomeAdapter = new HomeAdapter(new ArrayList<MultipleItemHome>());
        mRecyclerHome.setAdapter(mHomeAdapter);

        mPresenter.requestHomeData();

        return view;
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showEmptyError() {

    }

    @Override
    public void dataSuccess(List<PhotoData> list) {
        List<MultipleItemHome> reslut = DataServer.getMultipleItemData(list);
        mHomeAdapter.setNewData(reslut);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
