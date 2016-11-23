package com.xpple.fruits.cart.model;


import com.xpple.fruits.cart.model.bean.Cart;

import java.util.List;

/**
 * Created by Administrator on 2016/11/16.
 */

public interface CartModel {
    void getList(int userId,int index,int size,OnGetCartListFinishedListener listener);
    void modifyCart(List<Cart> carts, OnModifyCartFinishedListener listener);
    void clearCarts(int userId,OnClearCartsFinishedListener listener);
    void deleteCart(List<Integer> cartsId,OnDeleteCartFinishedListener listener);
}
