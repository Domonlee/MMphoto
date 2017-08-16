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
}
