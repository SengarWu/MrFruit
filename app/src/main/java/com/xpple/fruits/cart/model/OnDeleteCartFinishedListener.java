package com.xpple.fruits.cart.model;

/**
 * Created by Administrator on 2016/11/17.
 */

public interface OnDeleteCartFinishedListener {
    void OnDeleteCartSuccess(String message);
    void OnDeleteCartFail(String message);
}
