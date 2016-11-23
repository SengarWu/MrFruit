package com.xpple.fruits.main.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xpple.fruits.R;
import com.xpple.fruits.main.model.bean.Area;

import java.util.List;

/**
 * Created by Administrator on 2016/11/6.
 */

public class AreaAdapter extends BaseQuickAdapter<Area,BaseViewHolder> {

    public AreaAdapter(List<Area> data)
    {
        super(R.layout.area_item,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Area area) {
        baseViewHolder.setText(R.id.btn_area,area.getName())
                .addOnClickListener(R.id.btn_area);
    }
}
