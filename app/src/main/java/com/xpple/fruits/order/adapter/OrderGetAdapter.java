package com.xpple.fruits.order.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xpple.fruits.R;
import com.xpple.fruits.order.model.bean.GetExtro;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2016/11/14.
 */

public class OrderGetAdapter extends BaseQuickAdapter<GetExtro,BaseViewHolder> {
    private DecimalFormat df   = new DecimalFormat("######0.0");

    public OrderGetAdapter(List<GetExtro> data) {
        super(R.layout.order_get_item,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, GetExtro getExtro) {
        baseViewHolder.setText(R.id.tv_get_num,getExtro.getNum())
                .setText(R.id.tv_get_price,df.format(getExtro.getPrice()));
    }
}
