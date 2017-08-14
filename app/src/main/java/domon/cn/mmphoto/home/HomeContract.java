package domon.cn.mmphoto.home;

import java.util.List;

import domon.cn.mmphoto.BasePresenter;
import domon.cn.mmphoto.BaseView;
import domon.cn.mmphoto.data.Atlas;
import domon.cn.mmphoto.data.AtlasList;
import domon.cn.mmphoto.data.PhotoData;

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
