package com.xpple.fruits.me.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseActivity;

public class SeedDetailActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton ib_back;
    private TextView tv_title;
    private LinearLayout ll_seed_detail_fruit;//水果图片
    private ImageView iv_seed_detail_head;//果种图片
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seed_detail);
        initView();
    }

    private void initView() {
        ib_back = $(R.id.ib_back);
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_title = $(R.id.tv_title);
        tv_title.setText("果种详情");
        ib_seed_detail_sub=$(R.id.ib_seed_detail_sub);
        ib_seed_detail_sub.setOnClickListener(this);
        ib_seed_detail_add=$(R.id.ib_seed_detail_add);
        ib_seed_detail_add.setOnClickListener(this);
        bt_seed_detail_confirm=$(R.id.bt_seed_detail_confirm);
        bt_seed_detail_confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.ib_seed_detail_sub://点击减号

                 break;
             case R.id.ib_seed_detail_add://点击加号

                 break;
             case R.id.bt_seed_detail_confirm://点击确认购买

                 break;
         }


        }
}
