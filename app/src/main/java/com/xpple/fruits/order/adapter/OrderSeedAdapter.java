package com.xpple.fruits.order.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xpple.fruits.R;
import com.xpple.fruits.order.model.bean.SeedExtro;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2016/11/14.
 */

public class OrderSeedAdapter extends BaseQuickAdapter<SeedExtro,BaseViewHolder> {
    private DecimalFormat df   = new DecimalFormat("######0.0");

    public OrderSeedAdapter(List<SeedExtro> data) {
        super(R.layout.order_seed_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SeedExtro seedExtro) {
        baseViewHolder.setText(R.id.tv_seed_name,seedExtro.getName())
                .setText(R.id.tv_seed_num,seedExtro.getNum())
                .setText(R.id.tv_seed_price,df.format(seedExtro.getPrice()));
    }
}
