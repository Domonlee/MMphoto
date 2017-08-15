package domon.cn.mmphoto;

import android.app.Application;
import android.content.Context;

import com.lzy.okgo.OkGo;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;

import domon.cn.mmphoto.utils.SharedPreferenceUtil;
import okhttp3.OkHttpClient;

/**
 * Created by Domon on 2017/8/8.
 */

public class MyApp extends Application {
    private static MyApp mInstance = null;

    private static Context mContext;

    public static MyApp getInstance() {
        return mInstance;
    }

    public static Context getAppContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mContext = getApplicationContext();

        CrashReport.initCrashReport(getApplicationContext(), "c78918b3e2", true);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        OkGo.getInstance().init(this)
                .setOkHttpClient(builder.build())
                .setRetryCount(3);

        MobclickAgent.setScenarioType(mContext, MobclickAgent.EScenarioType.E_UM_NORMAL);
        //todo debug mode must close when the app is online
        MobclickAgent.setDebugMode(true);

        SharedPreferenceUtil.initPreference(this);
    }
}
