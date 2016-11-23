package com.xpple.fruits.shop.model.server;

import com.xpple.fruits.base.HttpMethods;
import com.xpple.fruits.shop.model.bean.FruitResult;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/11/7.
 */

public class FruitService extends HttpMethods {
    private FruitApi fruitApi;

    public FruitService()
    {
        super();
        fruitApi = retrofit.create(FruitApi.class);
    }

    protected static class SingletonHolder{
        private static final FruitService INSTANCE = new FruitService();
    }

    public static FruitService getInstance()
    {
        return SingletonHolder.INSTANCE;
    }

    public void loadData(Subscriber<FruitResult> subscriber,int userId,int areaId,int index,int size)
    {
        Observable observable = fruitApi.loadData(userId,areaId,index,size)
                .map(new HttpResultFunc<FruitResult>());
        toSubscribe(observable,subscriber);
    }
}
