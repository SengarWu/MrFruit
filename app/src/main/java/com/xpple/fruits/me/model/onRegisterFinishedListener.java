package com.xpple.fruits.me.model;

/**
 * Created by Administrator on 2016/8/19.
 */
public interface OnRegisterFinishedListener {
    void onError(String message);

    void onSuccess(String message);
}
