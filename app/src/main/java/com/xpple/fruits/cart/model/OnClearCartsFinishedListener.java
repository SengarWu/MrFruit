package com.xpple.fruits.cart.model;

/**
 * Created by Administrator on 2016/11/17.
 */

public interface OnClearCartsFinishedListener {
    void OnClearCartsSuccess(String message);
    void OnClearCartsFail(String message);
}
