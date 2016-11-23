package com.xpple.fruits.me.model.server;

import com.xpple.fruits.bean.HttpResult;
import com.xpple.fruits.me.model.bean.User;

import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/10/29.
 */
//121.42.51.3/
public interface UserApi {
    @POST("my/addin")
    Observable<HttpResult<String>> register(@Query("username") String name,@Query("userphone") String phone,@Query("passkey") String pass,@Query("code") String code);

    @POST("my/start")
    Observable<HttpResult<User>> login(@Header("Authorization") String contentRange);
}
