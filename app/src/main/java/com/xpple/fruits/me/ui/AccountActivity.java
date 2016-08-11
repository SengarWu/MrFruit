package com.xpple.fruits.me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseActivity;
import com.xpple.fruits.bean.CartEntity;

import java.util.List;

public class AccountActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton ib_back;
    private TextView tv_title;
    private LinearLayout ll_people;
    private RadioButton account_zhifubao;
    private RadioButton account_wechat;
    private RadioButton account_deliver;
    private LinearLayout ll_account_other;
    private TextView tv_account_other;
    private TextView tv_name;
    private TextView tv_phone;
    private TextView tv_account_area;
    private TextView tv_account_address;
    private TextView tv_account_total_price;

    private List<CartEntity> carts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        initView();
        initData();
    }

    private void initData() {
        //读取用户信息

        //读取购物车传递的水果数据
        carts = (List<CartEntity>) getIntent().getSerializableExtra("carts");

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
        ll_people = $(R.id.ll_people);
        ll_people.setOnClickListener(this);
        account_zhifubao=$(R.id.account_zhifubao);
        account_zhifubao.setOnClickListener(this);
        account_wechat=$(R.id.account_wechat);
        account_wechat.setOnClickListener(this);
        account_deliver=$(R.id.account_deliver);
        account_deliver.setOnClickListener(this);
        ll_account_other=$(R.id.ll_account_other);
        ll_account_other.setOnClickListener(this);
        tv_name = $(R.id.tv_name);
        tv_phone=$(R.id.tv_phone);
        tv_account_area=$(R.id.tv_account_area);
        tv_account_address=$(R.id.tv_account_address);
        tv_account_total_price=$(R.id.tv_account_total_price);
        tv_account_other=$(R.id.tv_account_other);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.ll_people://修改个人信息
                startActivity(new Intent(AccountActivity.this,AddressActivity.class));
                break;
            case R.id.account_zhifubao://选择支付宝支付

                break;
            case R.id.account_wechat://选择微信支付

                break;
            case R.id.account_deliver://选择货到付款

                break;
            case R.id.ll_account_other://点击备注
                startActivity(new Intent(AccountActivity.this,AddOtherActivity.class));
                break;
            default:
                break;
        }
    }

}
