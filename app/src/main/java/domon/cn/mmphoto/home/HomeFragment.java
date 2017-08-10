package domon.cn.mmphoto.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import domon.cn.mmphoto.R;
import domon.cn.mmphoto.album.AlbumActivity;
import domon.cn.mmphoto.utils.BannerImageLoader;

/**
 * Created by Domon on 2017/8/9.
 */

public class HomeFragment extends Fragment implements HomeContract.View {

    @BindView(R.id.title_mid_tv)
    TextView mTitleTv;
    @BindView(R.id.home_banner)
    Banner mBanner;
    @BindView(R.id.recycler_home)
    RecyclerView mRecyclerHome;

    private HomeContract.Presenter mPresenter;

    private Unbinder mUnbinder;

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

        //banner test start
        final List<String> images = new ArrayList<>();
        images.add(0, "http://zer.sistershsmy.com/MTUzOQ15391539/0718/1500350147.jpg");
        images.add(1, "http://zer.sistershsmy.com/MTcxNQ17151715/0706/1499321422.png");
        images.add(2, "http://zer.sistershsmy.com/MTcyMA17201720/0705/1499228376.jpg");


        mBanner.setImageLoader(new BannerImageLoader());
        mBanner.setImages(images);
        mBanner.start();

        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                //test goto album
                AlbumActivity.startActivity(getActivity());

            }
        });
        //banner test end

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
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
