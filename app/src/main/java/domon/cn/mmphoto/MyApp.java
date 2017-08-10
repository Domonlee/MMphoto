package domon.cn.mmphoto;

import android.app.Application;
import android.content.Context;

import com.lzy.okgo.OkGo;

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

        OkGo.getInstance().init(this);
    }
}
