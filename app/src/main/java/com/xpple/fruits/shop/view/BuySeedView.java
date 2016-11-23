package com.xpple.fruits.shop.view;

/**
 * Created by Administrator on 2016/11/18.
 */

public interface BuySeedView {
    void showProgress();

    void hideProgress();

    void submitOrderSuccess(int orderId);

    void submitOrderFail(String s);
}
