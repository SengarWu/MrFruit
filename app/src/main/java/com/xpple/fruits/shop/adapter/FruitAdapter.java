package com.xpple.fruits.shop.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xpple.fruits.R;
import com.xpple.fruits.main.model.bean.Fruit;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2016/8/13.
 */
public class FruitAdapter extends BaseQuickAdapter<Fruit,BaseViewHolder> {

    private DecimalFormat df   = new DecimalFormat("######0.0");

    public FruitAdapter(List<Fruit> data) {
        super(R.layout.fruit_item,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Fruit fruit) {
        baseViewHolder.setText(R.id.tv_fruit_name,fruit.getName())
                .setText(R.id.tv_fruit_price,df.format(fruit.getPrice()))
                .setText(R.id.tv_fruit_unit,fruit.getUnit())
                .setText(R.id.tv_fruit_original_price,df.format(fruit.getDiscount()))
                .setText(R.id.tv_fruit_original_unit,fruit.getUnit())
                .setText(R.id.tv_fruit_num,String.valueOf(fruit.getNum()))
                .setVisible(R.id.ib_fruit_sub,fruit.isVisible())
                .setVisible(R.id.tv_fruit_num,fruit.isVisible())
                .addOnClickListener(R.id.ib_fruit_sub)
                .addOnClickListener(R.id.ib_fruit_add);

        SimpleDraweeView draweeView = baseViewHolder.getView(R.id.iv_fruit_pic);
        //加载图片
        if (!TextUtils.isEmpty(fruit.getPicture()))
        {
            //图片URL
            draweeView.setImageURI(fruit.getPicture());
        }
    }
}
