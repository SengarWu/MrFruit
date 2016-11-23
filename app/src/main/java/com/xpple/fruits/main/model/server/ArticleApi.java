package com.xpple.fruits.main.model.server;

import com.xpple.fruits.bean.HttpResult;
import com.xpple.fruits.main.model.bean.ArticleResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/11/16.
 */

public interface ArticleApi {
    @GET("article")
    Observable<HttpResult<ArticleResult>> getList(@Query("index") int index, @Query("size") int size);
}
