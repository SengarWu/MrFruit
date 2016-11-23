package com.xpple.fruits.main.model;

/**
 * Created by Administrator on 2016/11/6.
 */

public interface OnAddCartFinishedListener {
    void onAddCartSuccess(String message);

    void onAddCartError(String message);
}
