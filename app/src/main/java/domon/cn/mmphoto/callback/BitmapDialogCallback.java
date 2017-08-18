package domon.cn.mmphoto.callback;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.view.Window;

import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.request.base.Request;

/**
 * Author : Louis
 * Email  : liangjing@cnxad.com
 * Time   : 2017/8/18 10:21
 * Version: 1.0
 * Desc   :
 */

public abstract class BitmapDialogCallback extends BitmapCallback {

    private ProgressDialog mDialog;

    public BitmapDialogCallback(Activity activity) {
        super(1000, 1000);
        mDialog = new ProgressDialog(activity);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setMessage("正在加载中...");
    }

    @Override
    public void onStart(Request<Bitmap, ? extends Request> request) {
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
    }

    @Override
    public void onFinish() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }
}
