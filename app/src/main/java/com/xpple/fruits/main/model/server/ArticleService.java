package com.xpple.fruits.main.model.server;

import com.xpple.fruits.base.HttpMethods;
import com.xpple.fruits.main.model.bean.ArticleResult;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/11/16.
 */

public class ArticleService extends HttpMethods {
    private ArticleApi articleApi;

    public ArticleService()
    {
        super();
        articleApi  = retrofit.create(ArticleApi.class);
    }

    protected static class SingletonHolder{
        private static final ArticleService INSTANCE = new ArticleService();
    }

    public static ArticleService getInstance()
    {
        return SingletonHolder.INSTANCE;
    }

    public void getList(Subscriber<ArticleResult> subscriber, int index, int size)
    {
        Observable observable = articleApi.getList(index,size)
                .map(new HttpResultFunc<ArticleResult>());
        toSubscribe(observable,subscriber);
    }
}
