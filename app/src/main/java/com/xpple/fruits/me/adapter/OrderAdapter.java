package com.xpple.fruits.me.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xpple.fruits.R;
import com.xpple.fruits.bean.OrderEntity;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2016/8/15.
 */
public class OrderAdapter extends BaseQuickAdapter<OrderEntity> {

    private DecimalFormat df   = new DecimalFormat("######0.0");

    public OrderAdapter(List<OrderEntity> data) {
        super(R.layout.order_item,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, OrderEntity orderEntity) {
        baseViewHolder.setText(R.id.tv_order_id,String.valueOf(orderEntity.id))
                .setText(R.id.tv_order_state,orderEntity.state)
                .setText(R.id.tv_order_number,String.valueOf(orderEntity.num))
                .setText(R.id.tv_order_total,df.format(orderEntity.total));
        baseViewHolder.setOnClickListener(R.id.ib_order_del,new OnItemChildClickListener());
    }
}
