package domon.cn.mmphoto;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.RelativeLayout;

import com.apkfuns.logutils.LogUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.tbruyelle.rxpermissions2.RxPermissions;

import domon.cn.mmphoto.base.BaseActivity;
import domon.cn.mmphoto.callback.JsonCallback;
import domon.cn.mmphoto.data.UserProfileData;
import domon.cn.mmphoto.utils.PhoneUtil;
import domon.cn.mmphoto.utils.SharedPreferenceUtil;

public class SplashActivity extends BaseActivity {
    private Context mContext;

    private RelativeLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        mContext = this;

        mContainer = (RelativeLayout) findViewById(R.id.splash_container);

        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.READ_PHONE_STATE)
                .subscribe(granted -> {
                    if (granted) {
                        reqForUserId();
                    }
                });


        new Handler().postDelayed(() -> startMainActivity(), 2000);
    }

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
                            LogUtils.e("save userID");
                        }
                    }

                    @Override
                    public void onError(Response<UserProfileData> response) {
                        super.onError(response);
                        LogUtils.e(response);
                    }
                });
    }

    private void startMainActivity() {
        Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
