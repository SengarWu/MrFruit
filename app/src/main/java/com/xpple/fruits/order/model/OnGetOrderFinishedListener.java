package com.xpple.fruits.order.model;

import com.xpple.fruits.order.model.bean.Order;

/**
 * Created by Administrator on 2016/11/14.
 */

public interface OnGetOrderFinishedListener {
    void OnGetOrderError(String message);
    void OnGetOrderSuccess(Order order);
}
