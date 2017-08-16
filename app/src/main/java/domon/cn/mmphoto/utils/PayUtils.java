package domon.cn.mmphoto.utils;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Response;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.FileNotFoundException;

import domon.cn.mmphoto.Const;
import domon.cn.mmphoto.MyApp;
import domon.cn.mmphoto.callback.JsonCallback;
import domon.cn.mmphoto.data.PayResultData;

/**
 * Created by Domon on 2017/8/14.
 */

public class PayUtils {
    public static final int PAY_CHANNLE_WETCHAT = 1;
    public static final int PAY_CHANNLE_ALIPAY = 2;
    public static final int PAY_TYPE_COIN = 1;
    public static final int PAY_TYPE_QUARTER = 2;
    public static final int PAY_TYPE_YEAR = 3;

    public static void payForWexinPay(Context context) {
        // TODO: 2017/8/14 目前已经失效，需要重新设置流程
        Intent intent = new Intent();
        ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setComponent(cmp);
        context.startActivity(intent);
    }

    public static void payForAliPay(Context context) {
        try {
            //利用Intent打开支付宝
            //支付宝跳过开启动画打开扫码和付款码的url scheme分别是alipayqr://platformapi/startapp?saId=10000007和
            //alipayqr://platformapi/startapp?saId=20000056
            Uri uri = Uri.parse("alipayqr://platformapi/startapp?saId=10000007");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        } catch (Exception e) {
            //若无法正常跳转，在此进行错误处理
            Toast.makeText(context, "无法跳转到支付宝，请检查您是否安装了支付宝！", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Init pay
     *
     * @param act
     * @param payCode    1:"50000金币,￥58元";2:"8000金币,￥38元";3:"4000金币,￥26元";4:"2000金币,￥18元";5:"40金币,￥3元
     * @param payType    1:金币支付 2:年度会员 3:季度会员(当传入2，3时，payCode参数不做处理)
     * @param payChannel 1:微富通微信支付 2:微富通支付宝支付 3:知名短代
     */
    public static void payInit(Activity act,int payCode, int payType, int payChannel) {
        String reqUrl = Const.REQ_PAY_IMG + "&payCode=" + payCode + "&payType=" + payType + "&payChannel=" + payChannel;

        RxPermissions rxPermissions = new RxPermissions(act);
        rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(grant -> {
                    if (grant) {
                        LogUtils.e(reqUrl);
                        OkGo.<PayResultData>get(reqUrl)
                                .execute(new JsonCallback<PayResultData>() {
                                    @Override
                                    public void onSuccess(Response<PayResultData> response) {
                                        LogUtils.e(response.body());
                                        downloadPic(response.body().getCode_img_url(), response.body().getAppid() + ".jpg"
                                                , payChannel);
                                    }
                                });
                    }
                });


    }

    private static void downloadPic(String url, String fileName, int payChannel) {
        String path = Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_PICTURES + "/";
        LogUtils.e("download path=" + path + fileName);

        OkGo.<File>get(url)
                .execute(new FileCallback(path, fileName) {
                    @Override
                    public void onSuccess(Response<File> response) {
                        LogUtils.e("download success,path=" + path + fileName);
                        ContentResolver contentResolver = MyApp.getAppContext().getContentResolver();

                        try {
                            MediaStore.Images.Media.insertImage(contentResolver, path, fileName, null);
                            MyApp.getAppContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(path, fileName))));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        if (payChannel == PAY_CHANNLE_ALIPAY) {
                            PayUtils.payForAliPay(MyApp.getAppContext());
                        } else if (payChannel == PAY_CHANNLE_WETCHAT) {
                            PayUtils.payForWexinPay(MyApp.getAppContext());
                        }
                    }

                    @Override
                    public void onError(Response<File> response) {
                        super.onError(response);
                        LogUtils.e(response.body());
                    }
                });
    }
}
