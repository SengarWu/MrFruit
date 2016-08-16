package com.xpple.fruits.shop.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xpple.fruits.R;
import com.xpple.fruits.bean.FruitEntity;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2016/8/13.
 */
public class FruitAdapter extends BaseQuickAdapter<FruitEntity> {

    private DecimalFormat df   = new DecimalFormat("######0.0");

    public FruitAdapter(List<FruitEntity> data) {
        super(R.layout.fruit_item,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, FruitEntity fruitEntity) {
        baseViewHolder.setText(R.id.tv_fruit_name,fruitEntity.fruit_name)
                .setText(R.id.tv_fruit_price,df.format(fruitEntity.fruit_price))
                .setText(R.id.tv_fruit_unit,fruitEntity.fruit_unit)
                .setText(R.id.tv_fruit_original_price,df.format(fruitEntity.fruit_discount))
                .setText(R.id.tv_fruit_original_unit,fruitEntity.fruit_unit)
                .setText(R.id.tv_fruit_num,String.valueOf(fruitEntity.fruit_num))
                .setVisible(R.id.ib_fruit_sub,fruitEntity.visible)
                .setVisible(R.id.tv_fruit_num,fruitEntity.visible);

        SimpleDraweeView draweeView = baseViewHolder.getView(R.id.iv_fruit_pic);
        //测试图片地址
        draweeView.setImageURI("http://b.hiphotos.baidu.com/image/h%3D200/sign=8e8564a10c46f21fd6345953c6256b31/00e93901213fb80e22a34b8d30d12f2eb938947d.jpg");

        baseViewHolder.setOnClickListener(R.id.ib_fruit_sub,new OnItemChildClickListener())
                .setOnClickListener(R.id.ib_fruit_add,new OnItemChildClickListener());


    }
}
