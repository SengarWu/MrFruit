package com.xpple.fruits.cart.presenter;

import com.xpple.fruits.cart.model.bean.Cart;

import java.util.List;

/**
 * Created by Administrator on 2016/11/16.
 */

public interface CartPresenter {
    void GetList(int userId,int index,int size);
    void modifyCart(List<Cart> carts);
    void clearCarts(int userId);
    void deleteCart(List<Integer> cartsId);
    void onDestroy();
}
