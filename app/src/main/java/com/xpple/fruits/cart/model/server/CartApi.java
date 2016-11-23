package com.xpple.fruits.cart.model.server;

import com.xpple.fruits.bean.HttpResult;
import com.xpple.fruits.cart.model.bean.Cart;
import com.xpple.fruits.cart.model.bean.CartResult;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/11/16.
 */

public interface CartApi {
    /**
     * 获取订单列表
     * @param userId
     * @param index
     * @param size
     * @return
     */
    @GET("cart")
    Observable<HttpResult<CartResult>> getList(@Query("userId") int userId,@Query("index") int index,@Query("size") int size);

    /**
     * 购物车数量修改
     *@param carts
     * @return
     */
    @POST("cart/modify/all")
    Observable<HttpResult<String>> modifyCart(@Query("carts")List<Cart> carts);

    /**
     * 清空购物车
     * @return
     */
    @POST("cart/delete/all")
    Observable<HttpResult<String>> ClearCarts(@Query("userId") int userId);

    /**
     * 添加购物车
     * @param fruitId
     * @param userId
     * @param amount
     * @return
     */
    @POST("cart/add")
    Observable<HttpResult<String>> addCart(@Query("fruitId") int fruitId,@Query("userId") int userId,@Query("amount") int amount);

    /**
     * 批量删除购物车商品
     * @param cartsId
     * @return
     */
    @POST("cart/delete")
    Observable<HttpResult<String>> deleteCart(@Query("carts") List<Integer> cartsId);
}
