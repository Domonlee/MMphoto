package domon.cn.mmphoto.home;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

import domon.cn.mmphoto.Const;
import domon.cn.mmphoto.callback.JsonCallback;
import domon.cn.mmphoto.data.Atlas;
import domon.cn.mmphoto.data.PhotoData;

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
        OkGo.<Atlas>get(Const.REQ_MAIN)
                .execute(new JsonCallback<Atlas>() {
                    @Override
                    public void onSuccess(Response<Atlas> response) {
                        Atlas result = response.body();
                        if (result != null) {
                            mView.dataSuccess(result);
                        } else {
                            mView.showEmptyError();
                        }
                    }
                });


    }

    @Override
    public boolean isDataMissing() {
        return false;
    }
}
