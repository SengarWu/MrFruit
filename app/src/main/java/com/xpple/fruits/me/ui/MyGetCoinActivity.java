package com.xpple.fruits.me.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseActivity;

public class MyGetCoinActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton ib_back;
    private TextView tv_title;
    private TextView tv_getcoin_price;
    private ImageButton ib_getcoin_sub;
    private ImageButton ib_getcoin_add;
    private RadioButton account_zhifubao;
    private RadioButton account_wechat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_get_coin);
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
        tv_title.setText("Get币充值");
        ib_getcoin_sub=$(R.id.ib_getcoin_sub);
        ib_getcoin_sub.setOnClickListener(this);
        ib_getcoin_add=$(R.id.ib_getcoin_add);
        ib_getcoin_add.setOnClickListener(this);
        account_zhifubao=$(R.id.account_zhifubao);
        account_zhifubao.setOnClickListener(this);
        account_wechat=$(R.id.account_wechat);
        account_wechat.setOnClickListener(this);
        tv_getcoin_price=$(R.id.tv_getcoin_price);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.ib_getcoin_sub://减少Get币数量

                break;
            case R.id.ib_getcoin_add://增加Get币数量

                break;
            case R.id.account_zhifubao://选择支付宝支付

                break;
            case R.id.account_wechat://选择微信支付

                break;
        }
    }
}
