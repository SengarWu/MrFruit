package com.xpple.fruits.cart.model.server;

import com.xpple.fruits.base.HttpMethods;
import com.xpple.fruits.cart.model.bean.Cart;
import com.xpple.fruits.cart.model.bean.CartResult;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/11/16.
 */

public class CartService extends HttpMethods {
    private CartApi cartApi;

    public CartService()
    {
        super();
        cartApi = retrofit.create(CartApi.class);
    }

    protected static class SingletonHolder{
        private static final CartService INSTANCE = new CartService();
    }

    public static CartService getInstance()
    {
        return SingletonHolder.INSTANCE;
    }

    public void getList(Subscriber<CartResult> subscriber,int userId,int index,int size)
    {
        Observable observable = cartApi.getList(userId,index,size)
                .map(new HttpResultFunc<CartResult>());
        toSubscribe(observable,subscriber);
    }

    public void modifyCart(Subscriber<String> subscriber, List<Cart> carts)
    {
        Observable observable = cartApi.modifyCart(carts)
                .map(new HttpResultFunc<String>());
        toSubscribe(observable,subscriber);
    }

    public void ClearCarts(Subscriber<String> subscriber,int userId)
    {
        Observable observable = cartApi.ClearCarts(userId)
                .map(new HttpResultFunc<String>());
        toSubscribe(observable,subscriber);
    }

    public void addCart(Subscriber<String> subscriber,int fruitId,int userId,int amount)
    {
        Observable observable = cartApi.addCart(fruitId,userId,amount)
                .map(new HttpResultFunc<String>());
        toSubscribe(observable,subscriber);
    }

    public void deleteCart(Subscriber<String> subscriber, List<Integer> cartsId)
    {
        Observable observable = cartApi.deleteCart(cartsId)
                .map(new HttpResultFunc<String>());
        toSubscribe(observable,subscriber);
    }
}
