package domon.cn.mmphoto;

/**
 * Created by Domon on 2017/8/12.
 */

public class Const {

    public static final String BASE_URL = "http://uuu.shafa5.com/";
    public static final String REQ_MAIN = BASE_URL + "GetMain.ashx";
    public static final String REQ_ALBUM_WITH_ID = BASE_URL + "GetAtlas.ashx?id=";
    public static final String REQ_IMGLIST_WITH_ID = BASE_URL + "GetImgList.ashx?id=";

    //    http://uuu.shafa5.com/GetAtlasByType.ashx?type=1,2
    public static final String REQ_CATEGORY_WITH_TYPE = BASE_URL + "GetAtlasByType.ashx?type=";

    // first time, +imei=xxxx; second time , +id = yyy;
    public static final String REQ_USER_ID = BASE_URL + "pay/GetUserInfo.ashx?";
    public static final String REQ_PAY_IMG = BASE_URL + "pay/requestpay.ashx?";
    public static final String REQ_BUY_ALBUM = BASE_URL + "pay/requestpurchase.ashx";

    //http://uuu.shafa5.com/Pay/RequestSMSPay.ashx?userId=1&payType=1&payCode=5&imsi=460110584789765&deviceId=A000004579F118
    public static final String REQ_SMS_ONE = BASE_URL + "pay/RequestSMSPay.ashx?";
    public static final String REQ_SMS_TWO = BASE_URL + "Pay/RequestSMSPayCode.ashx?";
    public static final String REQ_SMS_THREE = BASE_URL + "Pay/RequestPayResult.ashx?orderNo=";


    public static final int PAY_YET = 1;
    public static final int PAY_VIP1 = 2;
    public static final int PAY_VIP2 = 3;

    public static final int BUY_SUCESS = 0;
    public static final int BUY_YET = 1;
}
