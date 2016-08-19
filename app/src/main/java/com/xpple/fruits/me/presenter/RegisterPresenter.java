package com.xpple.fruits.me.presenter;

/**
 * Created by Administrator on 2016/8/19.
 */
public interface RegisterPresenter {
    String onRegister(String username, String phone, String password, String code);

    void onDestroy();
}
