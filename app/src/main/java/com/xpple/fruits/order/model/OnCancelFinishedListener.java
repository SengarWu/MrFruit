package com.xpple.fruits.order.model;

/**
 * Created by Administrator on 2016/11/15.
 */

public interface OnCancelFinishedListener {
    void OnCancelSuccess(String message);
    void OnCancelFail(String message);
}
