package com.xpple.fruits.shop.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jude.utils.JUtils;
import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseActivity;
import com.xpple.fruits.order.model.bean.Order;
import com.xpple.fruits.order.model.bean.SeedExtro;
import com.xpple.fruits.order.ui.MyOrderActivity;
import com.xpple.fruits.order.view.BuySeedView;
import com.xpple.fruits.shop.model.bean.Seeds;
import com.xpple.fruits.shop.presenter.BuySeedPresenter;
import com.xpple.fruits.shop.presenter.BuySeedPresenterImpl;
import com.xpple.fruits.utils.SharedPreferencesHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BuySeedActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, BuySeedView {
    private ImageButton ib_back;
    private TextView tv_title;
    private ImageView iv_buy_seed_photo;
    private TextView tv_buy_seed_name;
    private TextView tv_buy_seed_unit_price;
    private TextView tv_buy_seed_unit_get_coin;
    private TextView tv_buy_seed_number;
    private TextView tv_buy_seed_account;
    private RadioGroup rg_buy_seed_select;
    private RadioButton rb_buy_seed_zhifubao;
    private RadioButton rb_buy_seed_wechat;
    private RadioButton rb_buy_seed_get_coin;
    private TextView tv_buy_seed_price;
    private Button bt_buy_seed_pay;

    private ProgressBar progressBar;

    private DecimalFormat df   = new DecimalFormat("######0.0");

    private Seeds seed;
    private String PaymentMethod;

    private BuySeedPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_seed);
        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null)
        {
            Bundle bundle = intent.getBundleExtra("seed");
            seed = (Seeds) bundle.getSerializable("seed");
            setupView();
        }
    }

    private void setupView() {
        tv_buy_seed_name.setText(seed.getName());
        tv_buy_seed_unit_price.setText(df.format(seed.getPrice()));
        tv_buy_seed_unit_get_coin.setText(getCoin(seed.getPrice()));
        tv_buy_seed_number.setText(seed.getSeed_num());
        SharedPreferencesHelper helper = new SharedPreferencesHelper(this);
        tv_buy_seed_account.setText(helper.getUserGetCoin());
        tv_buy_seed_price.setText(String.valueOf(seed.getPrice() * seed.getSeed_num()));
    }

    private int getCoin(double price) {
        return (int) (price * 10);
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
        rb_buy_seed_wechat=$(R.id.rb_buy_seed_wechat);
        rb_buy_seed_get_coin=$(R.id.rb_buy_seed_get_coin);
        iv_buy_seed_photo=$(R.id.iv_buy_seed_photo);
        tv_buy_seed_name=$(R.id.tv_buy_seed_name);
        tv_buy_seed_unit_price=$(R.id.tv_buy_seed_unit_price);
        tv_buy_seed_unit_get_coin=$(R.id.tv_buy_seed_unit_get_coin);
        tv_buy_seed_number=$(R.id.tv_buy_seed_number);
        tv_buy_seed_account=$(R.id.tv_buy_seed_account);
        tv_buy_seed_price=$(R.id.tv_buy_seed_price);
        rg_buy_seed_select = $(R.id.rg_buy_seed_select);
        rg_buy_seed_select.setOnCheckedChangeListener(this);
        progressBar = new ProgressBar(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_buy_seed_pay://点击提交订单
                presenter = new BuySeedPresenterImpl(this);
                SharedPreferencesHelper helper = new SharedPreferencesHelper(this);
                int userId = helper.getUserId();
                //userId，seed 不为空
                SeedExtro seedExtro = new SeedExtro();
                seedExtro.Name = seed.getName();
                seedExtro.Num = seed.getSeed_num();
                List<SeedExtro> seeds = new ArrayList<SeedExtro>();
                seeds.add(seedExtro);
                Order order = new Order();
                order.UserId = userId;
                order.PaymentMethod = PaymentMethod;
                order.seeds = seeds;
                presenter.submitSeedOrder(order);
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId)
        {
            case R.id.rb_buy_seed_zhifubao:
                PaymentMethod = "支付宝支付";
                if (seed != null)
                {
                    tv_buy_seed_price.setText(String.valueOf(seed.getPrice() * seed.getSeed_num()));
                }
                JUtils.Toast("非常抱歉，目前暂不支持该付款方式！");

                break;
            case R.id.rb_buy_seed_wechat:
                PaymentMethod = "微信支付";
                if (seed != null)
                {
                    tv_buy_seed_price.setText(String.valueOf(seed.getPrice() * seed.getSeed_num()));
                }
                JUtils.Toast("非常抱歉，目前暂不支持该付款方式！");
                break;
            case R.id.rb_buy_seed_get_coin:
                PaymentMethod = "Get币支付";
                if (seed != null)
                {
                    tv_buy_seed_price.setText(String.valueOf(seed.getPrice() * 10 * seed.getSeed_num()));
                }
                break;
        }
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void submitFail(String message) {
        JUtils.Toast(message);
    }

    @Override
    public void submitSuccess(int orderId) {
        JUtils.Toast("订单提交成功");
        Intent intent = new Intent(BuySeedActivity.this,MyOrderActivity.class);
        intent.putExtra("orderId",orderId);
        startActivity(intent);
    }
}
