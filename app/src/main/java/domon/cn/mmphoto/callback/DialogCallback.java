package domon.cn.mmphoto.callback;

import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Window;

import com.lzy.okgo.request.base.Request;

/**
 * Author : Louis
 * Email  : liangjing@cnxad.com
 * Time   : 2017/8/16 18:13
 * Version: 1.0
 * Desc   :
 */

public abstract class DialogCallback<T> extends JsonCallback<T> {

    private ProgressDialog mProgressDialog;

    public DialogCallback(Activity activity) {
        super();
        initDialog(activity);
    }

    private void initDialog(Activity activity) {
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setMessage("请求网络中...");
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        if (mProgressDialog != null && !mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    @Override
    public void onFinish() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
