package com.xpple.fruits.main.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioGroup;

import com.xpple.fruits.R;
import com.xpple.fruits.base.BaseActivity;
import com.xpple.fruits.cart.ui.CartFragment;
import com.xpple.fruits.me.ui.MeFragment;
import com.xpple.fruits.utils.ToastUtil;

public class MainActivity extends BaseActivity{

    private static final String TAG = "MainActivity";
    private RadioGroup rg_select;

    public static final int CART_FRAGMENT = 1;
    public static final int MAIN_FRAGMENT = 2;
    public static final int ME_FRAGMENT = 3;

    private CartFragment cartFragment;
    private MainFragment mainFragment;
    private MeFragment meFragment;

    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        rg_select = $(R.id.rg_select);
        rg_select.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switchTab(checkedId);
            }
        });
        showFragment(MAIN_FRAGMENT);
    }

    private void switchTab(int id) {
        switch (id) {
            case R.id.rb_cart:
                showFragment(CART_FRAGMENT);
                break;
            case R.id.rb_main:
                showFragment(MAIN_FRAGMENT);
                break;
            case R.id.rb_me:
                showFragment(ME_FRAGMENT);
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
            case CART_FRAGMENT:
                if (cartFragment != null)
                    ft.show(cartFragment);
                else {
                    cartFragment = new CartFragment();
                    ft.add(R.id.container, cartFragment);
                }
                break;
            case MAIN_FRAGMENT:
                if (mainFragment != null) {
                    ft.show(mainFragment);
                } else {
                    mainFragment = new MainFragment();
                    ft.add(R.id.container, mainFragment);
                }
                break;
            case ME_FRAGMENT:
                if (meFragment != null) {
                    ft.show(meFragment);
                } else {
                    meFragment = new MeFragment();
                    ft.add(R.id.container, meFragment);
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
        if (cartFragment != null)
            transaction.hide(cartFragment);
        if (mainFragment != null)
            transaction.hide(mainFragment);
        if (meFragment != null)
            transaction.hide(meFragment);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            //两秒之内按返回键就会退出
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtil.showLong(this,"再按一次退出程序哦");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}