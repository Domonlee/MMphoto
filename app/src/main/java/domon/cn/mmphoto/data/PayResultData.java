package domon.cn.mmphoto.data;

/**
 * Created by Domon on 2017/8/16.
 */

public class PayResultData {
    /**
     * result_code : 0
     * uuid : 1cc947e7daa8ae37c5a680e6d69af4025
     * mch_id : 102511181422
     * status : 0
     * code_url : weixin://wxpay/bizpayurl?pr=OaMhyFv
     * sign_type : MD5
     * code_img_url : https://pay.swiftpass.cn/pay/qrcode?uuid=weixin%3A%2F%2Fwxpay%2Fbizpayurl%3Fpr%3DOaMhyFv
     * nonce_str : vu6FWOIXbTtoR1MbI8tZLKGggiyb5mX1
     * sign : 769FC7312CC03B9A01ED87839C380C57
     * version : 2.0
     * charset : UTF-8
     * appid : wx290ce4878c94369d
     */

    private String result_code;
    private String uuid;
    private String mch_id;
    private String status;
    private String code_url;
    private String sign_type;
    private String code_img_url;
    private String nonce_str;
    private String sign;
    private String version;
    private String charset;
    private String appid;

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode_url() {
        return code_url;
    }

    public void setCode_url(String code_url) {
        this.code_url = code_url;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getCode_img_url() {
        return code_img_url;
    }

    public void setCode_img_url(String code_img_url) {
        this.code_img_url = code_img_url;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }
}
