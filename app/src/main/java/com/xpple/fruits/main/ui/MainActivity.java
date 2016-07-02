package com.xpple.fruits.main.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseActivity;
import com.xpple.fruits.cart.ui.CartFragment;
import com.xpple.fruits.me.ui.MeFragment;

public class MainActivity extends BaseActivity{

    private static final String TAG = "MainActivity";
    private RadioGroup rg_select;
    private RadioButton rb_cart;
    private RadioButton rb_main;
    private RadioButton rb_me;

    private CartFragment cartFragment;
    private MainFragment mainFragment;
    private MeFragment meFragment;

    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        if (savedInstanceState == null)
        {
            cartFragment = CartFragment.newInstance();
            mainFragment = MainFragment.newInstance();
            meFragment = MeFragment.newInstance();

            //默认显示主页面
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container,cartFragment,cartFragment.getClass().getName())
                    .add(R.id.container,mainFragment,mainFragment.getClass().getName())
                    .add(R.id.container,meFragment,meFragment.getClass().getName())
                    .hide(cartFragment)
                    .hide(meFragment)
                    .commit();
        }
    }

    private void initView() {
        rb_cart = $(R.id.rb_cart);
        rb_main = $(R.id.rb_main);
        rb_me = $(R.id.rb_me);
        rg_select = $(R.id.rg_select);
        rg_select.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.rb_cart:
                        index = 0;
                        setCurrentFragment(index);
                        break;
                    case R.id.rb_main:
                        index = 1;
                        setCurrentFragment(index);
                        break;
                    case R.id.rb_me:
                        index = 2;
                        setCurrentFragment(index);
                        break;
                }
            }
        });
    }

    private void setCurrentFragment(int index) {
        switch (index)
        {
            case 0:
                rb_cart.setChecked(true);
                getSupportFragmentManager().beginTransaction()
                        .show(cartFragment)
                        .hide(mainFragment)
                        .hide(meFragment)
                        .commit();
                break;
            case 1:
                rb_main.setChecked(true);
                getSupportFragmentManager().beginTransaction()
                        .show(mainFragment)
                        .hide(cartFragment)
                        .hide(meFragment)
                        .commit();
                break;
            case 2:
            default:
                rb_me.setChecked(true);
                getSupportFragmentManager().beginTransaction()
                        .show(meFragment)
                        .hide(mainFragment)
                        .hide(cartFragment)
                        .commit();
                    break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

    }
}