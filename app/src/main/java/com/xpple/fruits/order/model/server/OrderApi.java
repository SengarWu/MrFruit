package com.xpple.fruits.order.model.server;

import com.xpple.fruits.bean.HttpResult;
import com.xpple.fruits.order.model.bean.MyOrder;
import com.xpple.fruits.order.model.bean.Order;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/11/12.
 */

public interface OrderApi {
    @POST("order/submit")
    Observable<HttpResult<Integer>> submit(@Body Order order);

    @GET("order")
    Observable<HttpResult<MyOrder>> getOrderList(@Query("userId") int userId, @Query("index") int index, @Query("size") int size);

    @GET("order/details")
    Observable<HttpResult<Order>> getOrderDetai(@Query("orderId") int orderId);

    @POST("order/delete")
    Observable<HttpResult<String>>  deleteOrder(@Query("orderId") int orderId);

    @POST("order/cancel")
    Observable<HttpResult<String>> cancelOrder(@Query("orderId") int orderId);

    @POST("order/evaluate")
    Observable<HttpResult<String>> evaluateOrder(@Query("orderId") int orderId,@Query("content") String content);

    @POST("order/getpay")
    Observable<HttpResult<String>> getpayOrder(@Query("orderId") int orderId);
}
