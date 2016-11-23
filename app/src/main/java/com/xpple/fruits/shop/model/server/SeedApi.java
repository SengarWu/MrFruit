package com.xpple.fruits.shop.model.server;

import com.xpple.fruits.bean.HttpResult;
import com.xpple.fruits.shop.model.bean.SeedResult;
import com.xpple.fruits.shop.model.bean.Seeds;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/11/11.
 */

public interface SeedApi {
    @GET("seed")
    Observable<HttpResult<SeedResult>> loadData(@Query("userId") int userId, @Query("areaId") int areaId, @Query("index") int index, @Query("size") int size);
    @GET("details")
    Observable<HttpResult<Seeds>> getSeed(@Query("seedId") int seedId);
}
