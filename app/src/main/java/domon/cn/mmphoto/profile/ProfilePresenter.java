package domon.cn.mmphoto.profile;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import domon.cn.mmphoto.Const;
import domon.cn.mmphoto.callback.JsonCallback;
import domon.cn.mmphoto.data.UserProfileData;
import domon.cn.mmphoto.utils.SharedPreferenceUtil;

/**
 * Created by Domon on 2017/8/8.
 */

public class ProfilePresenter implements ProfileContract.Presenter {
    private ProfileContract.View mView;

    public ProfilePresenter(ProfileContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void requestUserProfileData() {

        OkGo.<UserProfileData>get(Const.REQ_USER_ID + "id=" + SharedPreferenceUtil.getIntegerValue("userID"))
                .execute(new JsonCallback<UserProfileData>() {
                    @Override
                    public void onSuccess(Response<UserProfileData> response) {
                        UserProfileData user = response.body();
                        if (user != null) {
                            switch (user.getUser().getVIPType()) {
                                case 1:
                                    mView.updateBalance(user.getUser().getBalance(), 1);
                                    break;
                                case 2:
                                    mView.updateBalance(user.getUser().getBalance(), 2);
                                    break;
                                case 3:
                                    mView.updateBalance(user.getUser().getBalance(), 3);
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                });
    }

    @Override
    public boolean isDataMissing() {
        return false;
    }
}
