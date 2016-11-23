package com.xpple.fruits.me.model.server;

import com.xpple.fruits.base.HttpMethods;
import com.xpple.fruits.me.model.bean.User;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/11/1.
 */

public class UserService extends HttpMethods {
    private UserApi userApi;


    public UserService(){
        super();
        userApi = retrofit.create(UserApi.class);
    }

    protected static class SingletonHolder{
        private static final UserService INSTANCE = new UserService();
    }

    //获取单例
    public static UserService getInstance(){
        return SingletonHolder.INSTANCE;
    }

    //注册方法
    public void register(Subscriber<String> subscriber, String name, String phone, String pass,String code)
    {
        Observable<String> observable = userApi.register(name,phone,pass,code)
                .map(new HttpResultFunc<String>());
        toSubscribe(observable,subscriber);
    }

    public void login(Subscriber<User> subscriber,String userkey,String password)
    {
        Observable observable = userApi.login("key "+userkey+"&"+password)
                .map(new HttpResultFunc<User>());
        toSubscribe(observable,subscriber);
    }
}
