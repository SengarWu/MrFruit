package com.xpple.fruits.order.model;

/**
 * Created by Administrator on 2016/11/12.
 */

public interface OnSubmitFinishedListener {
    void onSubmitError(String message);
    void onSubmitSuccess(int orderId);
}
