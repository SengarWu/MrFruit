package com.xpple.fruits.cart.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xpple.fruits.R;
import com.xpple.fruits.bean.CartEntity;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2016/8/12.
 */
public class AccountAdapter extends BaseQuickAdapter<CartEntity> {
    private DecimalFormat df   = new DecimalFormat("######0.0");

    public AccountAdapter(List<CartEntity> data) {
        super(R.layout.account_item,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, CartEntity cart) {
        baseViewHolder.setText(R.id.tv_fruit_name,cart.fruit_name)
                .setText(R.id.tv_fruit_num,String.valueOf(cart.number))
                .setText(R.id.tv_fruit_original_price,df.format(cart.fruit_price))
                .setText(R.id.tv_fruit_real_price,df.format(cart.fruit_discount <= 0 ? cart.fruit_price:cart.fruit_discount));
    }
}
