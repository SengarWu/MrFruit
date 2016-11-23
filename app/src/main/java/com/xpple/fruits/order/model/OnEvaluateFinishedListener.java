package com.xpple.fruits.order.model;

/**
 * Created by Administrator on 2016/11/15.
 */

public interface OnEvaluateFinishedListener {
    void OnEvaluateSuccess(String message);
    void OnEvaluateFail(String message);

}
