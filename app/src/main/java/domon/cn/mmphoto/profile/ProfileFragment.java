package domon.cn.mmphoto.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import domon.cn.mmphoto.R;
import domon.cn.mmphoto.base.BaseFragment;
import domon.cn.mmphoto.utils.SharedPreferenceUtil;

/**
 * Created by Domon on 2017/8/8.
 */

public class ProfileFragment extends BaseFragment implements ProfileContract.View {
    @BindView(R.id.title_mid_tv)
    TextView mTitleTv;
    @BindView(R.id.userName)
    TextView mUserNameTv;
    @BindView(R.id.openVip)
    TextView mBalanceTv;

    @OnClick(R.id.buyGoldCoinsLayout)
    void onCoinClick() {
        PayForCoinActivity.startActivity(getActivity(), PayForCoinActivity.PAYFORCOIN);
    }

    @OnClick(R.id.openMemberLayout)
    void onVIPClick() {
        PayForCoinActivity.startActivity(getActivity(), PayForCoinActivity.PAYFORVIP);
    }

    @OnClick(R.id.mySettingsLayout)
    void onSettingPayClick() {
        Toast.makeText(getActivity(), "开发中", Toast.LENGTH_LONG).show();
    }

    private ProfileContract.Presenter mPresenter;

    public static ProfileFragment newInstance() {

        Bundle args = new Bundle();

        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new ProfilePresenter(this);
    }

    @Override
    protected void onSetupView(View rootView) {
        mPresenter.requestUserProfileData();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_profile;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.requestUserProfileData();
    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showEmptyError() {

    }

    @Override
    public void updateBalance(int balance, int vipType) {
        mUserNameTv.setText(SharedPreferenceUtil.getIntegerValue("userID") + "");
        switch (vipType) {
            case 1:
                SharedPreferenceUtil.setIntegerValue("userBalance", balance);
                mBalanceTv.setText("金币余额:" +
                        SharedPreferenceUtil.getIntegerValue("userBalance"));
                break;
            case 2:
                mBalanceTv.setText("年度会员");
                SharedPreferenceUtil.setIntegerValue("VIPType", 2);
                break;
            case 3:
                mBalanceTv.setText("季度会员");
                SharedPreferenceUtil.setIntegerValue("VIPType", 3);
                break;
            default:
                break;
        }
    }
}
