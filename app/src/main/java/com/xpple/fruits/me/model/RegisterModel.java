package com.xpple.fruits.me.model;

/**
 * Created by Administrator on 2016/10/17.
 */

public interface RegisterModel {
    void register(String name, String phone, String pass,String code, OnRegisterFinishedListener listener);
}
