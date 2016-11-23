package com.xpple.fruits.base;

import rx.Subscriber;

/**
 * Created by Administrator on 2016/11/1.
 */

public class BaseSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {

    }
}
