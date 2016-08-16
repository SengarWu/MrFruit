package com.xpple.fruits.shop.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xpple.fruits.R;
import com.xpple.fruits.bean.SeedEntity;

import java.util.List;

/**
 * Created by Administrator on 2016/8/13.
 */
public class SeedAdapter extends BaseQuickAdapter<SeedEntity> {

    public SeedAdapter(List<SeedEntity> data) {
        super(R.layout.seed_item,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SeedEntity seedEntity) {
        baseViewHolder.setText(R.id.tv_seed_name,seedEntity.seed_name)
                .setText(R.id.tv_seed_coin,getCoin(seedEntity.seed_price))
                .setText(R.id.tv_seed_price,String.valueOf(seedEntity.seed_price))
                .setText(R.id.tv_seed_state,seedEntity.seed_state)
                .setBackgroundRes(R.id.tv_seed_state,getStateBackground(seedEntity.seed_state));

        SimpleDraweeView draweeView = baseViewHolder.getView(R.id.iv_seed_picture);
        //测试图片地址
        draweeView.setImageURI("http://b.hiphotos.baidu.com/image/h%3D200/sign=8e8564a10c46f21fd6345953c6256b31/00e93901213fb80e22a34b8d30d12f2eb938947d.jpg");

    }

    private int getStateBackground(String seed_state) {
        switch (seed_state)
        {
            case "免费":
                return R.mipmap.seed_free;
            case "已解锁":
                return R.mipmap.seed_unlock;
            case "未解锁":
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
