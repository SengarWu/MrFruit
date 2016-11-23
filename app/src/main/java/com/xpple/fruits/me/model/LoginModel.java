package com.xpple.fruits.me.model;

/**
 * Created by Administrator on 2016/8/18.
 */
public interface LoginModel {
    void login(String username, String password, OnLoginFinishedListener listener);
}
