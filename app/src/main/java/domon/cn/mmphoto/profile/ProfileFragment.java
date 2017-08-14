package domon.cn.mmphoto.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import domon.cn.mmphoto.BaseFragment;
import domon.cn.mmphoto.R;

/**
 * Created by Domon on 2017/8/8.
 */

public class ProfileFragment extends BaseFragment {
    @BindView(R.id.title_mid_tv)
    TextView mTitleTv;

    @OnClick(R.id.buyGoldCoinsLayout)
    void onClick() {
        PayForCoinActivity.startActivity(getActivity(), PayForCoinActivity.PAYFORCOIN);
    }

    @OnClick(R.id.openMemberLayout)
    void onAliPayClick() {
        PayForCoinActivity.startActivity(getActivity(), PayForCoinActivity.PAYFORVIP);
    }

    private Unbinder mUnbinder;

    public static ProfileFragment newInstance() {

        Bundle args = new Bundle();

        ProfileFragment fragment = new ProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
