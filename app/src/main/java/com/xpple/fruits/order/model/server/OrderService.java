package com.xpple.fruits.order.model.server;

import com.xpple.fruits.base.HttpMethods;
import com.xpple.fruits.order.model.bean.MyOrder;
import com.xpple.fruits.order.model.bean.Order;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/11/12.
 */

public class OrderService extends HttpMethods {

    private OrderApi orderApi;

    public OrderService()
    {
        super();
        orderApi = retrofit.create(OrderApi.class);
    }

    protected static class SingletonHolder
    {
        private static final OrderService INSTANCE = new OrderService();
    }

    public static OrderService getInstance()
    {
        return SingletonHolder.INSTANCE;
    }

    public void submit(Subscriber<Integer> subscriber, Order order)
    {
        Observable observable = orderApi.submit(order)
                .map(new HttpResultFunc<Integer>());
        toSubscribe(observable,subscriber);
    }

    public void getOrderList(Subscriber<MyOrder> subscriber, int userId, int index, int size)
    {
        Observable observable = orderApi.getOrderList(userId,index,size)
                .map(new HttpResultFunc<MyOrder>());
        toSubscribe(observable,subscriber);
    }

    public void getOrderDetail(Subscriber<Order> subscriber,int orderId)
    {
        Observable observable = orderApi.getOrderDetai(orderId)
                .map(new HttpResultFunc<Order>());
        toSubscribe(observable,subscriber);
    }

    public void deleteOrder(Subscriber<String> subscriber,int orderId)
    {
        Observable observable = orderApi.deleteOrder(orderId)
                .map(new HttpResultFunc<String>());
        toSubscribe(observable,subscriber);
    }

    public void cancelOrder(Subscriber<String> subscriber,int orderId)
    {
        Observable observable = orderApi.cancelOrder(orderId)
                .map(new HttpResultFunc<String>());
        toSubscribe(observable,subscriber);
    }

    public void evaluateOrder(Subscriber<String> subscriber,int orderId,String content)
    {
        Observable observable = orderApi.evaluateOrder(orderId,content)
                .map(new HttpResultFunc<String>());
        toSubscribe(observable,subscriber);
    }

    public void getpayOrder(Subscriber<String> subscriber,int orderId)
    {
        Observable observable = orderApi.getpayOrder(orderId)
                .map(new HttpResultFunc<String>());
        toSubscribe(observable,subscriber);
    }
}
