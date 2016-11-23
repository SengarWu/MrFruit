package com.xpple.fruits.order.model;

import com.xpple.fruits.order.model.bean.MyOrder;
import com.xpple.fruits.order.model.bean.Order;
import com.xpple.fruits.order.model.server.OrderService;

import rx.Subscriber;

/**
 * Created by Administrator on 2016/11/12.
 */

public class OrderModelImpl implements OrderModel {

    @Override
    public void submit(Order order, final OnSubmitFinishedListener listener) {
        OrderService.getInstance().submit(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onSubmitError(e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                listener.onSubmitSuccess(integer);
            }
        },order);
    }

    @Override
    public void getOrderList(int userId, int index, int size, final OnGetListFinishedListener listener) {
        OrderService.getInstance().getOrderList(new Subscriber<MyOrder>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onGetOrderListError(e.getMessage());
            }

            @Override
            public void onNext(MyOrder myOrder) {
                listener.onGetOrderListSuccess(myOrder);
            }
        }, userId, index, size);
    }

    @Override
    public void getOrderDetail(int orderId, final OnGetOrderFinishedListener listener) {
        OrderService.getInstance().getOrderDetail(new Subscriber<Order>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.OnGetOrderError(e.getMessage());
            }

            @Override
            public void onNext(Order order) {
                listener.OnGetOrderSuccess(order);
            }
        },orderId);
    }

    @Override
    public void deleteOrder(int orderId, final OnDeleteFinishedListener listener) {
        OrderService.getInstance().deleteOrder(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.OnDeleteFail(e.getMessage());
            }

            @Override
            public void onNext(String s) {
                listener.OnDeleteSuccess(s);
            }
        },orderId);
    }

    @Override
    public void cancelOrder(int orderId, final OnCancelFinishedListener listener) {
        OrderService.getInstance().cancelOrder(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.OnCancelFail(e.getMessage());
            }

            @Override
            public void onNext(String s) {
                listener.OnCancelSuccess(s);
            }
        },orderId);
    }

    @Override
    public void evaluateOrder(int orderId, String content, final OnEvaluateFinishedListener listener) {
        OrderService.getInstance().evaluateOrder(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.OnEvaluateFail(e.getMessage());
            }

            @Override
            public void onNext(String s) {
                listener.OnEvaluateSuccess(s);
            }
        },orderId,content);
    }

    @Override
    public void getpayOrder(int orderId, final OnGetPayFinishedListener listener) {
        OrderService.getInstance().getpayOrder(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.OnGetPayError(e.getMessage());
            }

            @Override
            public void onNext(String s) {
                listener.OnGetPaySuccess(s);
            }
        },orderId);
    }
}
