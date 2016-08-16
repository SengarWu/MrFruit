package com.xpple.fruits.cart.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseActivity;
import com.xpple.fruits.bean.CartEntity;
import com.xpple.fruits.cart.adapter.AccountAdapter;
import com.xpple.fruits.me.ui.AddOtherActivity;
import com.xpple.fruits.me.ui.AddressActivity;

import java.text.DecimalFormat;
import java.util.List;

public class AccountActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton ib_back;
    private TextView tv_title;
    private RecyclerView rv_account;
    private TextView tv_account_total_price;
    private Button account_pay;

    private LinearLayout ll_account_people;
    private RadioButton rb_account_zfb;
    private RadioButton rb_account_wechat;
    private RadioButton rb_account_cash;
    private LinearLayout ll_account_other;
    private TextView tv_account_other;
    private TextView tv_account_name;
    private TextView tv_account_phone;
    private TextView tv_account_area;
    private TextView tv_account_address;


    private DecimalFormat df   = new DecimalFormat("######0.00");

    private List<CartEntity> carts;
    private AccountAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        initView();
        initData();
        setupView();
    }

    private void setupView() {
        adapter = new AccountAdapter(carts);
        //设置动画效果
        adapter.openLoadAnimation();
        //取消下拉加载
        adapter.openLoadMore(false);
        addHeadView();
        rv_account.setAdapter(adapter);
        //计算合计
        tv_account_total_price.setText(getTotal(carts));
    }

    private void addHeadView() {
        View headView = getLayoutInflater().inflate(R.layout.account_header, (ViewGroup) rv_account.getParent(), false);
        ll_account_people = (LinearLayout) headView.findViewById(R.id.ll_account_people);
        ll_account_people.setOnClickListener(this);
        tv_account_name = (TextView) headView.findViewById(R.id.tv_account_name);
        tv_account_phone = (TextView) headView.findViewById(R.id.tv_account_phone);
        tv_account_area = (TextView) headView.findViewById(R.id.tv_account_area);
        tv_account_address = (TextView) headView.findViewById(R.id.tv_account_address);
        rb_account_zfb = (RadioButton) headView.findViewById(R.id.rb_account_zfb);
        rb_account_wechat = (RadioButton) headView.findViewById(R.id.rb_account_wechat);
        rb_account_cash = (RadioButton) headView.findViewById(R.id.rb_account_cash);
        tv_account_other = (TextView) headView.findViewById(R.id.tv_account_other);
        ll_account_other = (LinearLayout) headView.findViewById(R.id.ll_account_other);
        ll_account_other.setOnClickListener(this);
        adapter.addHeaderView(headView);
    }

    /**
     * 计算合计
     * @param carts
     * @return
     */
    private String getTotal(List<CartEntity> carts) {
        Double total = 0.0;
        for (CartEntity cart:carts
             ) {
            if (cart.fruit_discount > 0)
            {
                total += cart.fruit_discount;
            }
            else
            {
                total += cart.fruit_price;
            }
        }
        return df.format(total);
    }

    private void initData() {
        //读取用户信息

        //水果数据需要判断来源，如果是从购物车界面则从以客户端数据为准，
        // 如果是从水果购买界面，则读取网络购物车数据，

        //读取购物车传递的水果数据
        Intent intent = getIntent();
        if (intent != null)
        {
            if (intent.getSerializableExtra("carts") != null)
            {
                carts = (List<CartEntity>) getIntent().getSerializableExtra("carts");
            }
        }

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
        rv_account = $(R.id.rv_account);
        rv_account.setLayoutManager(new LinearLayoutManager(this));
        tv_account_total_price=$(R.id.tv_account_total_price);
        account_pay = $(R.id.account_pay);
        account_pay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.ll_account_people://修改个人信息
                startActivity(new Intent(AccountActivity.this,AddressActivity.class));
                break;
            case R.id.rb_account_zfb://选择支付宝支付

                break;
            case R.id.rb_account_wechat://选择微信支付

                break;
            case R.id.rb_account_cash://选择货到付款

                break;
            case R.id.ll_account_other://点击备注
                startActivity(new Intent(AccountActivity.this,AddOtherActivity.class));
                break;
            default:
                break;
        }
    }

}
