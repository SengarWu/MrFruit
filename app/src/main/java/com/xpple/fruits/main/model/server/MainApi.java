package com.xpple.fruits.main.model.server;

import com.xpple.fruits.bean.HttpResult;
import com.xpple.fruits.main.model.bean.Main;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/11/3.
 */

public interface MainApi {

    /**
     * 主页请求
     * @param areaId
     * @param userId
     * @return
     */
    @GET("main")
    Observable<HttpResult<Main>> loadData(@Query("areaId") int areaId, @Query("userId") int userId);

    /**
     * 添加购物车
     * @param fruitId
     * @param userId
     * @param amount
     * @return
     */
    @POST("cart/add")
    Observable<HttpResult<String>> addCart(@Query("fruitId") int fruitId,@Query("userId") int userId,@Query("amount") int amount);
}
