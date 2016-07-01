package com.xpple.fruits.me.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseActivity;

public class AccountActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton ib_back;
    private TextView tv_title;
    private ImageButton ib_update;
    private RadioButton account_zhifubao;
    private RadioButton account_wechat;
    private RadioButton account_deliver;
    private TextView tv_name;
    private TextView tv_phone;
    private TextView tv_account_area;
    private TextView tv_account_address;
    private TextView tv_account_total_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

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
        tv_title.setText("结算");
        ib_update = $(R.id.ib_update);
        ib_update.setOnClickListener(this);
        account_zhifubao=$(R.id.account_zhifubao);
        account_zhifubao.setOnClickListener(this);
        account_wechat=$(R.id.account_wechat);
        account_wechat.setOnClickListener(this);
        account_deliver=$(R.id.account_deliver);
        account_deliver.setOnClickListener(this);
        tv_name = $(R.id.tv_name);
        tv_phone=$(R.id.tv_phone);
        tv_phone=$(R.id.tv_account_area);
        tv_phone=$(R.id.tv_account_address);
        tv_phone=$(R.id.tv_account_total_price);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.ib_update://修改个人信息

                break;
            case R.id.account_zhifubao://选择支付宝支付

                break;
            case R.id.account_wechat://选择微信支付

                break;
            case R.id.account_deliver://选择货到付款

                break;
        }
    }

}
