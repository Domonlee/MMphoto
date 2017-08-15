package domon.cn.mmphoto.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.Arrays;
import java.util.List;

import domon.cn.mmphoto.R;

/**
 * Created by Domon on 2017/8/14.
 */

public class PayCoinAdapter extends BaseItemDraggableAdapter<String, BaseViewHolder> {

    public PayCoinAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        List<String> splitList = Arrays.asList(item.split(","));
        helper.setText(R.id.item_pay_coin_tv, splitList.get(0));
        helper.setText(R.id.item_pay_rmb_tv, splitList.get(1));

        helper.addOnClickListener(R.id.item_pay_action_btn);
    }
}
