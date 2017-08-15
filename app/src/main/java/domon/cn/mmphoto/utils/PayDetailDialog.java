package domon.cn.mmphoto.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import domon.cn.mmphoto.R;

/**
 * Created by Domon on 2017/8/15.
 */

public class PayDetailDialog extends Dialog implements View.OnClickListener {
    private Context mContext;
    private TextView mAliPay;
    private TextView mWeChat;
    private TextView mCancle;
    private PayDetailDialogListener mListener;

    public interface PayDetailDialogListener {
        public void onClick(View view);
    }

    public PayDetailDialog(@NonNull Context context) {
        super(context, R.style.MyDialog);
        mContext = context;
    }

    public PayDetailDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, R.style.MyDialog);
        mContext = context;
    }

    public PayDetailDialogListener getListener() {
        return mListener;
    }

    public void setListener(PayDetailDialogListener listener) {
        mListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_pay, null);


        this.setContentView(layout);
        initViews();
    }

    private void initViews() {
        mAliPay = (TextView) findViewById(R.id.pay_alipay_tv);
        mWeChat = (TextView) findViewById(R.id.pay_wechat_tv);
        mCancle = (TextView) findViewById(R.id.pay_cancle_tv);

        mAliPay.setOnClickListener(this);
        mWeChat.setOnClickListener(this);
        mCancle.setOnClickListener(this);

    }

    @Override
    public void show() {
        super.show();

        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        getWindow().getDecorView().setPadding(20, 0, 20, 20);

        getWindow().setAttributes(layoutParams);
    }

    @Override
    public void onClick(View view) {
        mListener.onClick(view);
    }
}
