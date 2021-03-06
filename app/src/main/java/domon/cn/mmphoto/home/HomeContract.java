package domon.cn.mmphoto.home;

import domon.cn.mmphoto.base.BasePresenter;
import domon.cn.mmphoto.base.BaseView;
import domon.cn.mmphoto.data.Atlas;

/**
 * Created by Domon on 2017/8/9.
 */

public interface HomeContract {
    interface View extends BaseView<Presenter> {
        void showEmptyError();

        void dataSuccess(Atlas list);
    }

    interface Presenter extends BasePresenter {
        void requestHomeData();

        boolean isDataMissing();
    }
}
