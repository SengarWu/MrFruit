package com.xpple.fruits.shop.model.server;

import com.xpple.fruits.base.HttpMethods;
import com.xpple.fruits.shop.model.bean.SeedResult;
import com.xpple.fruits.shop.model.bean.Seeds;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/11/11.
 */

public class SeedService extends HttpMethods {
    private SeedApi seedApi;

    public SeedService()
    {
        super();
        seedApi = retrofit.create(SeedApi.class);
    }

    protected static class SingletonHolder{
        private static final SeedService INSTANCE = new SeedService();
    }

    public static SeedService getInstance()
    {
        return SingletonHolder.INSTANCE;
    }

    public void loadData(Subscriber<SeedResult> subscriber,int userId,int areaId,int index,int size)
    {
        Observable observable = seedApi.loadData(userId,areaId,index,size)
                .map(new HttpResultFunc<SeedResult>());
        toSubscribe(observable,subscriber);
    }

    public void getSeed(Subscriber<Seeds> subscriber,int seedId)
    {
        Observable observable = seedApi.getSeed(seedId)
                .map(new HttpResultFunc<Seeds>());
        toSubscribe(observable,subscriber);
    }
}
