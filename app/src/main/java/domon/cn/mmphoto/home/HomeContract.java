package domon.cn.mmphoto.home;

import domon.cn.mmphoto.BasePresenter;
import domon.cn.mmphoto.BaseView;

/**
 * Created by Domon on 2017/8/9.
 */

public interface HomeContract {
    interface View extends BaseView<Presenter> {
        void showEmptyError();
    }

    interface Presenter extends BasePresenter {
        void requestHomeData();

        boolean isDataMissing();
    }
}
