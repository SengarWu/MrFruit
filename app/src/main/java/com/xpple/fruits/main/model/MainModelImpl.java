package com.xpple.fruits.main.model;

import com.xpple.fruits.main.model.bean.Main;
import com.xpple.fruits.main.model.server.MainService;

import rx.Subscriber;

/**
 * Created by Administrator on 2016/11/3.
 */

public class MainModelImpl implements MainModel {

    @Override
    public void loadData(int areaId, int userId, final OnLoadDataFinishedListener listener) {
        MainService.getInstance().loadData(new Subscriber<Main>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onLoadDataError(e.getMessage());
            }

            @Override
            public void onNext(Main main) {
                listener.onLoadDataSuccess(main);
            }
        },areaId,userId);
    }

    @Override
    public void addCart(int fruitId, int userId, int amount, final OnAddCartFinishedListener listener) {
        MainService.getInstance().addCart(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onAddCartError(e.getMessage());
            }

            @Override
            public void onNext(String s) {
                listener.onAddCartSuccess(s);
            }
        },fruitId,userId,amount);
    }
}
