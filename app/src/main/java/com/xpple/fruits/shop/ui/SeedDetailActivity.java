package com.xpple.fruits.shop.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseActivity;
import com.xpple.fruits.utils.StringUtil;

public class SeedDetailActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton ib_back;
    private TextView tv_title;
    private SimpleDraweeView iv_seed_detail_image;//水果图片
    private ImageView iv_seed_detail_head;//果种宝宝图片
    private TextView tv_seed_detail_name;//果种名称
    private TextView tv_seed_detail_inform;//果种故事概述
    private TextView tv_seed_detail_condition;//解锁条件
    private TextView tv_seed_detail_value;//成长值
    private TextView tv_seed_detail_days;//成长所需天数
    private TextView tv_seed_detail_money;//果种价格
    private TextView tv_seed_detail_fruit;//成熟兑换后的水果
    private TextView tv_seed_detail_price;//单价
    private TextView tv_seed_detail_get_price;//单价Get币
    private TextView tv_seed_detail_num;//购买数量
    private ImageButton ib_seed_detail_sub;
    private ImageButton ib_seed_detail_add;
    private Button bt_seed_detail_confirm;

    private String seedId;
    private int num = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seed_detail);
        initView();
        initData();
        setupView();
    }

    private void setupView() {
        //测试图片地址
        iv_seed_detail_image.setImageURI("http://b.hiphotos.baidu.com/image/h%3D200/sign=8e8564a10c46f21fd6345953c6256b31/00e93901213fb80e22a34b8d30d12f2eb938947d.jpg");
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null)
        {
            seedId = intent.getStringExtra("seedId");
        }
        if (StringUtil.isNotEmpty(seedId))
        {
            //网络请求操作

        }
    }

    private void initView() {
        ib_back = $(R.id.ib_back);
        ib_back.setOnClickListener(this);
        tv_title = $(R.id.tv_title);
        tv_title.setText("果种详情");
        ib_seed_detail_sub=$(R.id.ib_seed_detail_sub);
        ib_seed_detail_sub.setOnClickListener(this);
        ib_seed_detail_add=$(R.id.ib_seed_detail_add);
        ib_seed_detail_add.setOnClickListener(this);
        bt_seed_detail_confirm=$(R.id.bt_seed_detail_confirm);
        bt_seed_detail_confirm.setOnClickListener(this);
        iv_seed_detail_image=$(R.id.iv_seed_detail_image);
        iv_seed_detail_head=$(R.id.iv_seed_detail_head);
        tv_seed_detail_name=$(R.id.tv_seed_detail_name);
        tv_seed_detail_inform=$(R.id.tv_seed_detail_inform);
        tv_seed_detail_condition=$(R.id.tv_seed_detail_condition);
        tv_seed_detail_value=$(R.id.tv_seed_detail_value);
        tv_seed_detail_days=$(R.id.tv_seed_detail_days);
        tv_seed_detail_money=$(R.id.tv_seed_detail_money);
        tv_seed_detail_fruit=$(R.id.tv_seed_detail_fruit);
        tv_seed_detail_price=$(R.id.tv_seed_detail_price);
        tv_seed_detail_get_price=$(R.id.tv_seed_detail_get_price);
        tv_seed_detail_num=$(R.id.tv_seed_detail_num);
    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.ib_back://返回键
                 finish();
                 break;
             case R.id.ib_seed_detail_sub://点击减号
                 if (num > 1)
                 {
                     num--;
                     tv_seed_detail_num.setText(String.valueOf(num));
                 }
                 break;
             case R.id.ib_seed_detail_add://点击加号
                if (num < 99)
                {
                    num++;
                    tv_seed_detail_num.setText(String.valueOf(num));
                }
                 break;
             case R.id.bt_seed_detail_confirm://点击确认购买

                 break;
         }
    }
}