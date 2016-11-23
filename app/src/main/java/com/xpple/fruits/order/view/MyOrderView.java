package com.xpple.fruits.order.view;

import com.xpple.fruits.order.model.bean.MyOrder;
import com.xpple.fruits.order.model.bean.Order;

/**
 * Created by Administrator on 2016/11/13.
 */

public interface MyOrderView {
    void showProgress();

    void hideProgress();

    void getListFail(String message);

    void getListSuccess(MyOrder myOrder);

    void getDetailFail(String message);

    void getDetailSuccess(Order order);

    void deleteOrderSuccess(String message);

    void deleteOrderFail(String message);

    void cancelOrderSuccess(String message);

    void cancelOrderFail(String message);

    void evaluateOrderSuccess(String message);

    void evaluateOrderFail(String message);

    void getpayOrderSuccess(String message);

    void getpayOrderFail(String message);
}
