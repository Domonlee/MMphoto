package domon.cn.mmphoto;

import android.app.Application;
import android.content.Context;

import com.lzy.okgo.OkGo;
import com.umeng.analytics.MobclickAgent;

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

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        OkGo.getInstance().init(this)
                .setOkHttpClient(builder.build())
                .setRetryCount(3);

        MobclickAgent.setScenarioType(mContext, MobclickAgent.EScenarioType.E_UM_NORMAL);
        //todo debug mode must close when the app is online
        MobclickAgent.setDebugMode(true);
    }
}
