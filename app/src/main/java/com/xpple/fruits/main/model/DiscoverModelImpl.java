package com.xpple.fruits.main.model;

import com.xpple.fruits.main.model.bean.ArticleResult;
import com.xpple.fruits.main.model.server.ArticleService;

import rx.Subscriber;

/**
 * Created by Administrator on 2016/11/16.
 */

public class DiscoverModelImpl implements DiscoverModel {

    @Override
    public void getList(int index, int size, final OnGetListFinishedListener listener) {
        ArticleService.getInstance().getList(new Subscriber<ArticleResult>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.OnGetListError(e.getMessage());
            }

            @Override
            public void onNext(ArticleResult result) {
                listener.onGetListSuccess(result);
            }
        },index,size);
    }
}
