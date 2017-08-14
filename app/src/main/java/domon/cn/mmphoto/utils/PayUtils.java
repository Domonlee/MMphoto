package domon.cn.mmphoto.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

/**
 * Created by Domon on 2017/8/14.
 */

public class PayUtils {

    public static void payForWexinPay(Context context) {
        // TODO: 2017/8/14 目前已经失效，需要重新设置流程
        try {
            //利用Intent打开微信
            Uri uri = Uri.parse("weixin://scanqrcode");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
        } catch (Exception e) {
            //若无法正常跳转，在此进行错误处理
            Toast.makeText(context, "无法跳转到微信，请检查您是否安装了微信！", Toast.LENGTH_SHORT).show();
        }
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
}
