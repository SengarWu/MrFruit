package com.xpple.fruits.main.model.server;

import com.xpple.fruits.base.HttpMethods;
import com.xpple.fruits.main.model.bean.Main;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/11/3.
 */

public class MainService extends HttpMethods {
    private MainApi mainApi;

    public MainService()
    {
        super();
        mainApi = retrofit.create(MainApi.class);
    }

    protected static class SingletonHolder{
        private static final MainService INSTANCE = new MainService();
    }

    //获取单例
    public static MainService getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public void loadData(Subscriber<Main> subscriber,int areaId,int userId)
    {
        Observable observable = mainApi.loadData(areaId,userId)
                .map(new HttpResultFunc<Main>());
        toSubscribe(observable,subscriber);
    }

    public void addCart(Subscriber<String> subscriber,int fruitId,int userId,int amount)
    {
        Observable observable = mainApi.addCart(fruitId,userId,amount)
                .map(new HttpResultFunc<String>());
        toSubscribe(observable,subscriber);
    }
}
