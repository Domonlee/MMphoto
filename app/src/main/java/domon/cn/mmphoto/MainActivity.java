package domon.cn.mmphoto;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.widget.FrameLayout;

import com.apkfuns.logutils.LogUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yinglan.alphatabs.AlphaTabView;
import com.yinglan.alphatabs.AlphaTabsIndicator;

import java.util.List;

import butterknife.BindView;
import domon.cn.mmphoto.base.BaseActivity;
import domon.cn.mmphoto.callback.JsonCallback;
import domon.cn.mmphoto.category.CategoryFragment;
import domon.cn.mmphoto.data.UserProfileData;
import domon.cn.mmphoto.home.HomeFragment;
import domon.cn.mmphoto.profile.ProfileFragment;
import domon.cn.mmphoto.utils.FragmentUtils;
import domon.cn.mmphoto.utils.PayUtils;
import domon.cn.mmphoto.utils.PhoneUtil;
import domon.cn.mmphoto.utils.SharedPreferenceUtil;
import domon.cn.mmphoto.utils.SmsReceiver;

public class MainActivity extends BaseActivity {
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
    private SmsReceiver mSmsReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProfileAlphaTabView.showPoint();

        initSMSReceiver();

        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.READ_PHONE_STATE)
                .subscribe(grant -> {
                    if (grant) {
                        reqSMS();
                    }
                });

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
    }


    /**
     * first time send imei , second time send id
     */
    private void reqForUserId() {
        final String reqUrl;

        // FIXME: 2017/8/23 if the IMEI can't read, the userID will be null
        if (SharedPreferenceUtil.getIntegerValue("userID") == -1) {
            // TODO: 2017/8/23 use phone for test
            reqUrl = Const.REQ_USER_ID + "code=" + PhoneUtil.getPhoneSign(this);
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

    private void initSMSReceiver() {
        LogUtils.e("initSmsReceiver OnSucessfull");
        mSmsReceiver = new SmsReceiver();
        mSmsReceiver.registerSmsReceiver(this, new SmsReceiver.SmsListener() {
            @Override
            public void onMessage(String msg, String fromAddress, String serviceCenterAddress) {
                if (fromAddress.equals("1000188") && msg.contains("成功定制")) {
                    PayUtils.payForSMSThree();
                } else if (fromAddress.equals("1065987320001") && msg.contains("支付验证码")) {
                    LogUtils.e(msg.substring(0, 4));
                    PayUtils.payForSMSTwo(msg.substring(0, 4));
                }
            }
        });


    }

    //that is used the first init
    private void reqSMS() {
        String imsi = PhoneUtil.getImsi(this);

        if (!TextUtils.isEmpty(imsi) && !imsi.startsWith("46003") && (!imsi.startsWith("46011")) && (!imsi.startsWith("46005"))) {
        } else {
            PayUtils.payForSMSOne(this, 1, PayUtils.PAY_TYPE_COIN);
            PayUtils.payForSMSOne(this, 1, PayUtils.PAY_TYPE_COIN);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.e("onDestroy");
        mSmsReceiver.unRegisterSmsReceiver(this);
    }
}
