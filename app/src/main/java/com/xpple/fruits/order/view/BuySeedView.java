package com.xpple.fruits.order.view;

/**
 * Created by Administrator on 2016/11/15.
 */

public interface BuySeedView {
    void showProgress();

    void hideProgress();

    void submitFail(String message);

    void submitSuccess(int orderId);
}
