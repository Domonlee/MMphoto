package domon.cn.mmphoto.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import domon.cn.mmphoto.MyApp;
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

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(MyApp.getAppContext(), "需要支付逻辑", Toast.LENGTH_LONG).show();

                final PayDetailDialog dialog = new PayDetailDialog(PayForCoinActivity.this);
                dialog.setListener(new PayDetailDialog.PayDetailDialogListener() {
                    @Override
                    public void onClick(View view) {
                        switch (view.getId()) {
                            case R.id.pay_alipay_tv:
                                PayUtils.payForAliPay(PayForCoinActivity.this);
                                break;
                            case R.id.pay_wechat_tv:
                                PayUtils.payForWexinPay(PayForCoinActivity.this);
                                break;
                            case R.id.pay_cancle_tv:
                                dialog.dismiss();
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
