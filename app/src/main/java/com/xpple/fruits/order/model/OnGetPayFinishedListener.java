package com.xpple.fruits.order.model;

/**
 * Created by Administrator on 2016/11/14.
 */

public interface OnGetPayFinishedListener {
    void OnGetPayError(String message);
    void OnGetPaySuccess(String message);
}
