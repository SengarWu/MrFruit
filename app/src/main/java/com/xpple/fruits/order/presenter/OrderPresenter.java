package com.xpple.fruits.order.presenter;

import com.xpple.fruits.order.model.bean.Order;

/**
 * Created by Administrator on 2016/11/12.
 */

public interface OrderPresenter {
    void submit(Order order);
    void getList(int userId,int index,int size);
    void orderDetail(int orderId);
    void deleteOrder(int orderId);
    void cancelOrder(int orderId);
    void evaluateOrder(int orderId,String content);
    void getpayOrder(int orderId);
    void onDestory();
}
