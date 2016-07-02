package com.xpple.fruits.main.ui;

import android.os.Bundle;
import android.widget.RadioGroup;

import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseActivity;
import com.xpple.fruits.cart.ui.CartFragment;
import com.xpple.fruits.me.ui.MeFragment;

import java.util.List;

public class MainActivity extends BaseActivity{

    private RadioGroup rg_select;

    MainFragment mainFragment;
    CartFragment cartFragment;
    MeFragment meFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mainFragment = new MainFragment();
        cartFragment = new CartFragment();
        meFragment = new MeFragment();

        if (savedInstanceState != null) {  // “内存重启”时调用
            List fragmentList = getSupportFragmentManager().getFragments();
            mainFragment = (MainFragment) getSupportFragmentManager()
                    .findFragmentByTag(mainFragment.getClass().getName());
            cartFragment = (CartFragment) getSupportFragmentManager()
                    .findFragmentByTag(cartFragment.getClass().getName());
            meFragment = (MeFragment) getSupportFragmentManager()
                    .findFragmentByTag(meFragment.getClass().getName());
            index = savedInstanceState.getInt(KEY_INDEX);
            switch (index)
            {
                case 0:

                    break;
                case 1:

                    break;
                case 2:
                default:

                    break;
            }
        }
        else
        {
            mainFragment = MainFragment.newInstance();

        }
    }

    private void initView() {
        rg_select = $(R.id.rg_select);
        rg_select.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.rb_cart:

                        break;
                    case R.id.rb_main:

                        break;
                    case R.id.rb_me:

                        break;
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //保存当前Fragment的下标
    }
}