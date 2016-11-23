package com.xpple.fruits.shop.adapter;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xpple.fruits.R;
import com.xpple.fruits.shop.model.bean.Seeds;

import java.util.List;

/**
 * Created by Administrator on 2016/8/13.
 */
public class SeedAdapter extends BaseQuickAdapter<Seeds,BaseViewHolder> {

    public SeedAdapter(List<Seeds> data) {
        super(R.layout.seed_item,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Seeds seed) {
        baseViewHolder.setText(R.id.tv_seed_name,seed.Name)
                .setText(R.id.tv_seed_coin,getCoin(seed.Price))
                .setText(R.id.tv_seed_price,String.valueOf(seed.Price))
                .setText(R.id.tv_seed_state,seed.state)
                .setBackgroundRes(R.id.tv_seed_state,getStateBackground(seed.state));

        SimpleDraweeView draweeView = baseViewHolder.getView(R.id.iv_seed_picture);
        if (!TextUtils.isEmpty(seed.Picture))
        {
            draweeView.setImageURI(seed.Picture);
        }

    }

    private int getStateBackground(int seed_state) {
        switch (seed_state)
        {
            case 0:
                return R.mipmap.seed_free;
            case 1:
                return R.mipmap.seed_free;
            case 2:
                return R.mipmap.seed_unlock;
            case 3:
                return R.mipmap.seed_lock;
            default:
                break;
        }
        return R.mipmap.seed_free;
    }

    private String getCoin(double seed_price) {
        //1Get币 = 0.1 元
        return String.valueOf(seed_price/0.1);
    }
}
