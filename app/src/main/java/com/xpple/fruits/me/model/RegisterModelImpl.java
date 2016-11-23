package com.xpple.fruits.me.model;

import com.xpple.fruits.me.model.server.UserService;

import rx.Subscriber;

/**
 * Created by Administrator on 2016/11/1.
 */

//只与数据访问有关
public class RegisterModelImpl implements RegisterModel{

    @Override
    public void register(String name, String phone, String pass,String code, final OnRegisterFinishedListener listener) {
        UserService.getInstance().register(new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                listener.onError(e.getMessage());
            }

            @Override
            public void onNext(String s) {
                listener.onSuccess(s);
            }
        }, name, phone, pass,code);
    }
}
