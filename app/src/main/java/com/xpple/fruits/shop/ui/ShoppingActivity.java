package com.xpple.fruits.shop.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseActivity;

public class ShoppingActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton ib_back;
    private TextView tv_title;
    private RadioGroup rg_shop;

    private FruitFragment fruitFragment;
    private SeedFragment seedFragment;

    public static final int FRUIT_FRAGMENT = 1;
    public static final int SEED_FRAGMENT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        initView();
        tv_title.setText("水果专区");
    }

    private void initView() {
        ib_back = $(R.id.ib_back);
        ib_back.setOnClickListener(this);
        tv_title = $(R.id.tv_title);
        rg_shop = $(R.id.rg_shop);
        rg_shop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switchTab(checkedId);
            }
        });
        showFragment(FRUIT_FRAGMENT);
    }

    private void switchTab(int id) {
        switch (id) {
            case R.id.rb_shop_fruit:
                showFragment(FRUIT_FRAGMENT);
                break;
            case R.id.rb_shop_seed:
                showFragment(SEED_FRAGMENT);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.ib_back:
                finish();
                break;
        }
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     */
    private void showFragment(int index) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        hideFragment(ft);
        switch (index) {
            case FRUIT_FRAGMENT:
                if (fruitFragment != null)
                    ft.show(fruitFragment);
                else {
                    fruitFragment = new FruitFragment();
                    ft.add(R.id.fl_shop, fruitFragment);
                }
                break;
            case SEED_FRAGMENT:
                if (seedFragment != null) {
                    ft.show(seedFragment);
                } else {
                    seedFragment = new SeedFragment();
                    ft.add(R.id.fl_shop, seedFragment);
                }
                break;
            default:
                break;
        }
        ft.commitAllowingStateLoss();
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragment(FragmentTransaction transaction) {
        if (fruitFragment != null)
            transaction.hide(fruitFragment);
        if (seedFragment != null)
            transaction.hide(seedFragment);
    }
}
