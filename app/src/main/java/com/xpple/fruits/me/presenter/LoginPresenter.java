package com.xpple.fruits.me.presenter;

/**
 * Created by Administrator on 2016/8/18.
 */
public interface LoginPresenter {
    String validateCredentials(String username, String password);

    void onDestroy();
}
