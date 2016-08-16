package com.xpple.fruits.main.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xpple.fruits.R;
import com.xpple.fruits.bean.DiscoverEntity;

import java.util.List;

/**
 * Created by Administrator on 2016/8/16.
 */
public class DiscoverAdapter extends BaseQuickAdapter<DiscoverEntity> {


    public DiscoverAdapter(List<DiscoverEntity> data) {
        super(R.layout.discover_item,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, DiscoverEntity discoverEntity) {
        baseViewHolder.setText(R.id.iv_discover_title,discoverEntity.title);

        SimpleDraweeView draweeView = baseViewHolder.getView(R.id.iv_discover_picture);
        //测试图片地址
        draweeView.setImageURI("http://mmbiz.qpic.cn/mmbiz/PY1lZ0Jrn5uYs1brrGBtqgWQddG56KdOgKJGGob1G2CkhhsrdbHjIwOMNe0DHgMwAqzV8w9XGdKx9oic7NU6wOg/640?wx_fmt=png&wxfrom=5&wx_lazy=1");
    }
}
