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
            if (!TextUtils.isEmpty(getImei(context))) {
                return getImei(context);
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

    private static String getImei(Context context) {
        String imei = TelephoneUtil.getIMEI(context);
        if (!TextUtils.isEmpty(imei)) {
            return imei;
        }
        if (!TextUtils.isEmpty(switchTeleInfo(1, context))) {
            return switchTeleInfo(1, context);
        }
        if (!TextUtils.isEmpty(switchTeleInfo(2, context))) {
            return switchTeleInfo(2, context);
        }
        if (!TextUtils.isEmpty(switchTeleInfo(3, context))) {
            return switchTeleInfo(3, context);
        }
        if (!TextUtils.isEmpty(switchTeleInfo(4, context))) {
            return switchTeleInfo(4, context);
        }

        return imei;
    }

    private static String switchTeleInfo(int i, Context context) {
        TelephoneUtil.TeleInfo teleInfo = new TelephoneUtil.TeleInfo();
        switch (i) {
            case 1:
                teleInfo = TelephoneUtil.getMtkTeleInfo(context);
                if (!TextUtils.isEmpty(teleInfo.imei_1)) {
                    return teleInfo.imei_1;
                }
                if (!TextUtils.isEmpty(teleInfo.imei_2)) {
                    return teleInfo.imei_2;
                }
                break;
            case 2:
                teleInfo = TelephoneUtil.getMtkTeleInfo(context);
                if (!TextUtils.isEmpty(teleInfo.imei_1)) {
                    return teleInfo.imei_1;
                }
                if (!TextUtils.isEmpty(teleInfo.imei_2)) {
                    return teleInfo.imei_2;
                }
                break;
            case 3:
                teleInfo = TelephoneUtil.getMtkTeleInfo(context);
                if (!TextUtils.isEmpty(teleInfo.imei_1)) {
                    return teleInfo.imei_1;
                }
                if (!TextUtils.isEmpty(teleInfo.imei_2)) {
                    return teleInfo.imei_2;
                }
                break;
            case 4:
                teleInfo = TelephoneUtil.getMtkTeleInfo(context);
                if (!TextUtils.isEmpty(teleInfo.imei_1)) {
                    return teleInfo.imei_1;
                }
                if (!TextUtils.isEmpty(teleInfo.imei_2)) {
                    return teleInfo.imei_2;
                }
                break;
            default:
                break;
        }
        return "";
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
