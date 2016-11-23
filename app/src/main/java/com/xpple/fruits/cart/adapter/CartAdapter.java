package com.xpple.fruits.cart.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xpple.fruits.R;
import com.xpple.fruits.cart.model.bean.Cart;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2016/7/2.
 */
public class CartAdapter extends BaseQuickAdapter<Cart,BaseViewHolder> {

    private DecimalFormat df   = new DecimalFormat("######0.0");

    public CartAdapter(List<Cart> data) {
        super(R.layout.cart_item, data);
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, Cart cart) {
        double price = 0;
        if (cart.getDiscount() >= 0 && cart.getDiscount() < cart.getPrice())
        {
            price = cart.getDiscount();
        }
        else
        {
            price = cart.getPrice();
        }
        baseViewHolder.setText(R.id.tv_cart_fruit_name,cart.getName())
                .setChecked(R.id.cb_cart_check,cart.getIscheck())
                .setText(R.id.tv_cart_price,df.format(price))
                .setText(R.id.tv_cart_unit,cart.getUnit())
                .setText(R.id.tv_cart_num,String.valueOf(cart.getNum()))
                .setText(R.id.tv_cart_sum,df.format(cart.getSum()))
                .addOnClickListener(R.id.ib_cart_add)
                .addOnClickListener(R.id.ib_cart_sub)
                .addOnClickListener(R.id.cb_cart_check);

        SimpleDraweeView draweeView = baseViewHolder.getView(R.id.iv_cart_picture);
        if (TextUtils.isEmpty(cart.getPicture()))
        {
            //默认图片
            draweeView.setImageURI("http://b.hiphotos.baidu.com/image/h%3D200/sign=8e8564a10c46f21fd6345953c6256b31/00e93901213fb80e22a34b8d30d12f2eb938947d.jpg");
        }
        else
        {
            draweeView.setImageURI(cart.getPicture());
        }
    }
}
