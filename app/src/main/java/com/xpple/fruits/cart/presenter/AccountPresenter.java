package com.xpple.fruits.cart.presenter;

import com.xpple.fruits.order.model.bean.Order;

/**
 * Created by Administrator on 2016/11/18.
 */

public interface AccountPresenter {
    void GetCartList(int userId,int index,int size);
    void submitOrder(Order order);
}
