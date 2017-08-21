package domon.cn.mmphoto;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.litesuits.common.utils.TelephoneUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yinglan.alphatabs.AlphaTabView;
import com.yinglan.alphatabs.AlphaTabsIndicator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import domon.cn.mmphoto.callback.JsonCallback;
import domon.cn.mmphoto.category.CategoryFragment;
import domon.cn.mmphoto.data.UserProfileData;
import domon.cn.mmphoto.home.HomeFragment;
import domon.cn.mmphoto.profile.ProfileFragment;
import domon.cn.mmphoto.utils.FragmentUtils;
import domon.cn.mmphoto.utils.SharedPreferenceUtil;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.container_fl)
    FrameLayout mContainer;

    @BindView(R.id.alphaIndicator)
    AlphaTabsIndicator mAlphaTabsIndicator;

    @BindView(R.id.main_atv)
    AlphaTabView mMainAlphaTabView;
    @BindView(R.id.categroy_atv)
    AlphaTabView mCategoryAlphaTabView;
    @BindView(R.id.profile_atv)
    AlphaTabView mProfileAlphaTabView;

    private Context mContext;
    private FragmentManager mFragmentManager;
    private Fragment mCurrentFragment;

    private Unbinder unbind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbind = ButterKnife.bind(this);

        mProfileAlphaTabView.showPoint();

        mAlphaTabsIndicator.setOnTabChangedListner(tabNum -> {
            switch (tabNum) {
                case 0:
                    switchFragment(HomeFragment.class);
                    break;
                case 1:
                    switchFragment(CategoryFragment.class);
                    break;
                case 2:
                    switchFragment(ProfileFragment.class);
                    break;
                default:
                    break;
            }
        });

        initFragment(savedInstanceState);

        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.READ_PHONE_STATE)
                .subscribe(granted -> {
                    if (granted) {
                        reqForUserId();
                    }
                });
    }


    /**
     * first time send imei , second time send id
     */
    private void reqForUserId() {
        final String reqUrl;

        if (SharedPreferenceUtil.getIntegerValue("userID") == -1) {
            reqUrl = Const.REQ_USER_ID + "code=" + TelephoneUtil.getIMEI(this);
        } else {
            reqUrl = Const.REQ_USER_ID + "id=" + SharedPreferenceUtil.getIntegerValue("userID");
        }

        OkGo.<UserProfileData>get(reqUrl)
                .execute(new JsonCallback<UserProfileData>() {
                    @Override
                    public void onSuccess(Response<UserProfileData> response) {
                        UserProfileData user = response.body();
                        if (user != null) {
                            SharedPreferenceUtil.setStringValue("userCode", user.getUser().getUserCode());
                            SharedPreferenceUtil.setIntegerValue("userVIPType", user.getUser().getVIPType());
                            SharedPreferenceUtil.setIntegerValue("userBalance", user.getUser().getBalance());
                            SharedPreferenceUtil.setIntegerValue("userID", user.getUser().getID());
                        }
                    }
                });
    }

    /**
     * use FragmentUtils to create new fragment.
     *
     * @param savedInstanceState
     */
    private void initFragment(Bundle savedInstanceState) {
        mFragmentManager = getSupportFragmentManager();
        mCurrentFragment = (Fragment) mFragmentManager.findFragmentById(R.id.container_fl);

        if (mCurrentFragment == null) {
            mCurrentFragment = FragmentUtils.createFragment(HomeFragment.class);
            mFragmentManager.beginTransaction().add(R.id.container_fl, mCurrentFragment).commit();
        }
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        if (savedInstanceState != null) {
            List<Fragment> fragments = mFragmentManager.getFragments();
            for (int i = 0; i < fragments.size(); i++) {
                transaction.hide(fragments.get(i));
            }
        }
        transaction.show(mCurrentFragment).commitAllowingStateLoss();
    }

    /**
     * switch current fragment.
     *
     * @param clazz
     */
    private void switchFragment(Class<?> clazz) {
        if (clazz == null) {
            return;
        }

        Fragment to = FragmentUtils.createFragment(clazz);
        if (to.isAdded()) {
            mFragmentManager.beginTransaction().hide(mCurrentFragment).show(to).commitAllowingStateLoss();
        } else {
            mFragmentManager.beginTransaction().hide(mCurrentFragment).add(R.id.container_fl, to)
                    .commitAllowingStateLoss();
        }

        mCurrentFragment = to;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbind.unbind();
    }
}
