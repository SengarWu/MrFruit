package com.xpple.fruits.shop.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseActivity;

public class BuySeedActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton ib_back;
    private TextView tv_title;
    private ImageView iv_buy_seed_photo;
    private TextView tv_buy_seed_name;
    private TextView tv_buy_seed_unit_price;
    private TextView tv_buy_seed_unit_get_coin;
    private TextView tv_buy_seed_number;
    private TextView tv_buy_seed_account;
    private RadioButton rb_buy_seed_zhifubao;
    private RadioButton rb_buy_seed_wechat;
    private RadioButton rb_buy_seed_get_coin;
    private TextView tv_buy_seed_price;
    private Button bt_buy_seed_pay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_seed);
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
        tv_title.setText("果种结算");
        bt_buy_seed_pay=$(R.id.bt_buy_seed_pay);
        bt_buy_seed_pay.setOnClickListener(this);
        rb_buy_seed_zhifubao=$(R.id.rb_buy_seed_zhifubao);
        rb_buy_seed_zhifubao.setOnClickListener(this);
        rb_buy_seed_wechat=$(R.id.rb_buy_seed_wechat);
        rb_buy_seed_wechat.setOnClickListener(this);
        rb_buy_seed_get_coin=$(R.id.rb_buy_seed_get_coin);
        rb_buy_seed_get_coin.setOnClickListener(this);
        iv_buy_seed_photo=$(R.id.iv_buy_seed_photo);
        tv_buy_seed_name=$(R.id.tv_buy_seed_name);
        tv_buy_seed_unit_price=$(R.id.tv_buy_seed_unit_price);
        tv_buy_seed_unit_get_coin=$(R.id.tv_buy_seed_unit_get_coin);
        tv_buy_seed_number=$(R.id.tv_buy_seed_number);
        tv_buy_seed_account=$(R.id.tv_buy_seed_account);
        tv_buy_seed_price=$(R.id.tv_buy_seed_price);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_buy_seed_pay://点击提交订单

                break;
            case R.id.rb_buy_seed_zhifubao://点击支付宝支付

                break;
            case R.id.rb_buy_seed_wechat://点击微信支付

                break;
            case R.id.rb_buy_seed_get_coin://点击Get币支付

                break;
        }
    }
}
