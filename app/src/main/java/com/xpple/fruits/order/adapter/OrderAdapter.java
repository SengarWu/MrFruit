package com.xpple.fruits.order.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xpple.fruits.R;
import com.xpple.fruits.order.model.bean.OrderResult;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2016/8/15.
 */
public class OrderAdapter extends BaseQuickAdapter<OrderResult,BaseViewHolder> {

    private DecimalFormat df   = new DecimalFormat("######0.0");

    public OrderAdapter(List<OrderResult> data) {
        super(R.layout.order_item,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, OrderResult orderResult) {
        baseViewHolder.setText(R.id.tv_order_id,String.valueOf(orderResult.getId()))
                .setText(R.id.tv_order_state,orderResult.getState())
                .setText(R.id.tv_order_number,String.valueOf(orderResult.getCount()))
                .setText(R.id.tv_order_total,df.format(orderResult.getSum()))
                .addOnClickListener(R.id.ib_order_del);
    }
}
