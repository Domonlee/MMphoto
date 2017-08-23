package domon.cn.mmphoto.utils;

import android.content.Context;
import android.text.TextUtils;

import com.litesuits.common.utils.TelephoneUtil;

import java.util.UUID;

/**
 * Author : Louis
 * Email  : liangjing@cnxad.com
 * Time   : 2017/8/23 19:53
 * Version: 1.0
 * Desc   :
 */

public class PhoneUtil {

    //获取手机的唯一标识
    public static String getPhoneSign(Context context) {
        StringBuilder deviceId = new StringBuilder();
        // 渠道标志
        try {
            //IMEI（imei）
            String imei = TelephoneUtil.getIMEI(context);
            if (!TextUtils.isEmpty(imei)) {
                return imei;
            }

            //如果上面都没有， 则生成一个id：随机码
            String uuid = getUUID();
            if (!TextUtils.isEmpty(uuid)) {
                return uuid;
            }
        } catch (Exception e) {
            e.printStackTrace();
            deviceId.append(getUUID());
        }
        return deviceId.toString();
    }

    //得到全局唯一UUID
    private static String getUUID() {
        String uuid = SharedPreferenceUtil.getStringValue("uuid");
        if (TextUtils.isEmpty(uuid)) {
            uuid = UUID.randomUUID().toString();
            SharedPreferenceUtil.setStringValue("uuid", uuid);
        }
        return uuid;
    }
}
