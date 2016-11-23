package com.xpple.fruits.cart.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xpple.fruits.R;
import com.xpple.fruits.cart.model.bean.Cart;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2016/8/12.
 */
public class AccountAdapter extends BaseQuickAdapter<Cart,BaseViewHolder> {
    private DecimalFormat df   = new DecimalFormat("######0.0");

    public AccountAdapter(List<Cart> data) {
        super(R.layout.account_item,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Cart cart) {
        baseViewHolder.setText(R.id.tv_fruit_name,cart.getName())
                .setText(R.id.tv_fruit_num,String.valueOf(cart.getNum()))
                .setText(R.id.tv_fruit_original_price,df.format(cart.getPrice()))
                .setText(R.id.tv_fruit_real_price,df.format(cart.getDiscount() >= 0 ? cart.getDiscount():cart.getPrice()));
    }
}
