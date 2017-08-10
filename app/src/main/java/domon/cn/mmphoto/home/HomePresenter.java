package domon.cn.mmphoto.home;

import com.apkfuns.logutils.LogUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * Created by Domon on 2017/8/9.
 */

public class HomePresenter implements HomeContract.Presenter {


    private HomeContract.View mView;

    public HomePresenter(HomeContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void requestHomeData() {
        OkGo.<String>get("http://uuu.shafa5.com/GetAtlas.ashx?id=2300")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.e(response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        LogUtils.e(response);
                    }
                });


    }

    @Override
    public boolean isDataMissing() {
        return false;
    }
}
