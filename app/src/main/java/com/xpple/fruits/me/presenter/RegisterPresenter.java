package com.xpple.fruits.me.presenter;

/**
 * Created by Administrator on 2016/11/1.
 */

public interface RegisterPresenter {
    void register(String name,String phone,String pass,String code);

    void onDestroy();
}
