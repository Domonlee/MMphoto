package domon.cn.mmphoto;

import android.app.Application;
import android.content.Context;

import com.lzy.okgo.OkGo;

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
    }
}
