package com.xpple.fruits.shop.model;

import com.xpple.fruits.shop.model.bean.SeedResult;
import com.xpple.fruits.shop.model.bean.Seeds;
import com.xpple.fruits.shop.model.server.SeedService;

import rx.Subscriber;

/**
 * Created by Administrator on 2016/11/11.
 */

public class SeedModelImpl implements SeedModel {
    @Override
    public void loadData(int userId, int areaId, int index, int size,final OnLoadSeedFinishedListener listener) {
        SeedService.getInstance().loadData(new Subscriber<SeedResult>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onLoadDataError(e.getMessage());
            }

            @Override
            public void onNext(SeedResult seedResult) {
                listener.onLoadDataSuccess(seedResult);
            }
        },userId,areaId,index,size);
    }

    @Override
    public void getSeed(int seedId, final OnLoadSeedDetailListener listener) {
        SeedService.getInstance().getSeed(new Subscriber<Seeds>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onLoadDataError(e.getMessage());
            }

            @Override
            public void onNext(Seeds seeds) {
                listener.onLoadDataSuccess(seeds);
            }
        },seedId);
    }
}
