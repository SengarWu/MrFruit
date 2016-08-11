package com.xpple.fruits.cart.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xpple.fruits.R;
import com.xpple.fruits.bean.CartEntity;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2016/7/2.
 */
public class CartAdapter extends BaseQuickAdapter<CartEntity> {

    private DecimalFormat df   = new DecimalFormat("######0.00");
    private View parentView;

    public CartAdapter(List<CartEntity> data) {
        super(R.layout.cart_item, data);
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, CartEntity cart) {
        baseViewHolder.setText(R.id.tv_cart_fruit_name,cart.fruit_name)
                .setChecked(R.id.cb_cart_check,cart.ischeck)
                .setText(R.id.tv_cart_price,df.format(cart.fruit_price))
                .setText(R.id.tv_cart_unit,cart.fruit_unit)
                .setText(R.id.tv_cart_num,String.valueOf(cart.number))
                .setText(R.id.tv_cart_sum,df.format(cart.sum));

        SimpleDraweeView draweeView = baseViewHolder.getView(R.id.iv_cart_picture);
        //测试图片地址
        draweeView.setImageURI("http://b.hiphotos.baidu.com/image/h%3D200/sign=8e8564a10c46f21fd6345953c6256b31/00e93901213fb80e22a34b8d30d12f2eb938947d.jpg");

        baseViewHolder.setOnClickListener(R.id.ib_cart_add,new OnItemChildClickListener())
                .setOnClickListener(R.id.ib_cart_sub,new OnItemChildClickListener())
                .setOnClickListener(R.id.cb_cart_check,new OnItemChildClickListener());

        /*if (TextUtils.isEmpty(cart.fruit_Image))
        {
            //显示默认图片
            baseViewHolder.setImageResource(R.id.iv_cart_picture,R.mipmap.seeds_banana2);
        }
        else
        {
            SimpleDraweeView draweeView = baseViewHolder.getView(R.id.iv_cart_picture);
            //测试图片地址
            draweeView.setImageURI("http://b.hiphotos.baidu.com/image/h%3D200/sign=8e8564a10c46f21fd6345953c6256b31/00e93901213fb80e22a34b8d30d12f2eb938947d.jpg");
            //draweeView.setImageURI(cart.fruit_Image);
        }*/
    }
}
