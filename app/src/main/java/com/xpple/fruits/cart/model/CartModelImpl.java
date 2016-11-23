package com.xpple.fruits.cart.model;

import com.xpple.fruits.cart.model.bean.Cart;
import com.xpple.fruits.cart.model.bean.CartResult;
import com.xpple.fruits.cart.model.server.CartService;

import java.util.List;

import rx.Subscriber;

/**
 * Created by Administrator on 2016/11/16.
 */

public class CartModelImpl implements CartModel {
    @Override
    public void getList(int userId, int index, int size, final OnGetCartListFinishedListener listener) {
        CartService.getInstance().getList(new Subscriber<CartResult>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.OnGetListFail(e.getMessage());
            }

            @Override
            public void onNext(CartResult result) {
                listener.OnGetListSuccess(result);
            }
        },userId,index,size);
    }

    @Override
    public void modifyCart(List<Cart> carts, final OnModifyCartFinishedListener listener) {
        CartService.getInstance().modifyCart(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onModifyCartFail(e.getMessage());
            }

            @Override
            public void onNext(String s) {
                listener.onModifyCartSuccess(s);
            }
        },carts);
    }

    @Override
    public void clearCarts(int userId, final OnClearCartsFinishedListener listener) {
        CartService.getInstance().ClearCarts(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.OnClearCartsFail(e.getMessage());
            }

            @Override
            public void onNext(String s) {
                listener.OnClearCartsSuccess(s);
            }
        },userId);
    }

    @Override
    public void deleteCart(List<Integer> cartsId, final OnDeleteCartFinishedListener listener) {
        CartService.getInstance().deleteCart(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.OnDeleteCartFail(e.getMessage());
            }

            @Override
            public void onNext(String s) {
                listener.OnDeleteCartSuccess(s);
            }
        }, cartsId);
    }
}
