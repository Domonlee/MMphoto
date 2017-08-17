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
                        mView.updateBalance(response.body().getUser().getBalance());
                    }
                });
    }

    @Override
    public boolean isDataMissing() {
        return false;
    }
}
