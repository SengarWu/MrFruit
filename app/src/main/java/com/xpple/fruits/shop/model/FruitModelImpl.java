package com.xpple.fruits.shop.model;

import com.xpple.fruits.shop.model.bean.FruitResult;
import com.xpple.fruits.shop.model.server.FruitService;

import rx.Subscriber;

/**
 * Created by Administrator on 2016/11/7.
 */

public class FruitModelImpl implements FruitModel {

    @Override
    public void loadData(int userId, int areaId, int index, int size, final OnLoadFruitFinishedListener listener) {
        FruitService.getInstance().loadData(new Subscriber<FruitResult>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onLoadDataError(e.getMessage());
            }

            @Override
            public void onNext(FruitResult result) {
                listener.onLoadDataSuccess(result);
            }
        }, userId, areaId, index, size);
    }
}
