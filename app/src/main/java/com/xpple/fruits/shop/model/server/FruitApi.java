package com.xpple.fruits.shop.model.server;

import com.xpple.fruits.bean.HttpResult;
import com.xpple.fruits.shop.model.bean.FruitResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/11/7.
 */

public interface FruitApi {

    @GET("fruit")
    Observable<HttpResult<FruitResult>> loadData(@Query("userId") int userId, @Query("areaId") int areaId, @Query("index") int index, @Query("size") int size);

}
