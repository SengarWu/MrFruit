package com.xpple.fruits.shop.model;

/**
 * Created by Administrator on 2016/11/11.
 */

public interface SeedModel {
    void loadData(int userId, int areaId, int index, int size,OnLoadSeedFinishedListener loadSeedFinishedListener);
    void getSeed(int seedId,OnLoadSeedDetailListener listener);
}
