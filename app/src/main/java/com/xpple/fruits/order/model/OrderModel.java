package com.xpple.fruits.order.model;

import com.xpple.fruits.order.model.bean.Order;

/**
 * Created by Administrator on 2016/11/12.
 */

public interface OrderModel {
    void submit(Order order,OnSubmitFinishedListener listener);
    void getOrderList(int userId,int index,int size,OnGetListFinishedListener listener);
    void getOrderDetail(int orderId,OnGetOrderFinishedListener listener);
    void deleteOrder(int orderId,OnDeleteFinishedListener listener);
    void cancelOrder(int orderId, OnCancelFinishedListener listener);
    void evaluateOrder(int orderId,String content,OnEvaluateFinishedListener listener);
    void getpayOrder(int orderId,OnGetPayFinishedListener listener);
}
