package com.xpple.fruits.order.model;

/**
 * Created by Administrator on 2016/11/15.
 */

public interface OnDeleteFinishedListener {
    void OnDeleteSuccess(String message);
    void OnDeleteFail(String message);
}
