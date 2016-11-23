package com.xpple.fruits.order.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xpple.fruits.R;
import com.xpple.fruits.order.model.bean.FruitExtro;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2016/11/14.
 */

public class OrderFruitAdapter extends BaseQuickAdapter<FruitExtro,BaseViewHolder> {
    private DecimalFormat df   = new DecimalFormat("######0.0");

    public OrderFruitAdapter(List<FruitExtro> data) {
        super(R.layout.order_fruit_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, FruitExtro fruitExtro) {
        baseViewHolder.setText(R.id.tv_fruit_name,fruitExtro.getName())
                .setText(R.id.tv_fruit_num,String.valueOf(fruitExtro.getNum()))
                .setText(R.id.tv_fruit_original_price,df.format(fruitExtro.getOriginalPrice()))
                .setText(R.id.tv_fruit_real_price,df.format(fruitExtro.getRealPay() <= 0 ));
    }
}
