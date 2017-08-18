package domon.cn.mmphoto.profile;

import domon.cn.mmphoto.base.BasePresenter;
import domon.cn.mmphoto.base.BaseView;

/**
 * Created by Domon on 2017/8/8.
 */

public interface ProfileContract {
    interface View extends BaseView<Presenter> {
        void showEmptyError();

        void updateBalance(int balance,int vipType);
    }

    interface Presenter extends BasePresenter {
        void requestUserProfileData();

        boolean isDataMissing();
    }
}
