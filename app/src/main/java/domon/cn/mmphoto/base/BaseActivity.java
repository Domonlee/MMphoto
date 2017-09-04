package domon.cn.mmphoto.base;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Domon on 2017/8/14.
 */

public class BaseActivity extends AppCompatActivity{
    private Unbinder mUnbinder;

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
