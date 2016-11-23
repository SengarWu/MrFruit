package com.xpple.fruits.shop.model;

/**
 * Created by Administrator on 2016/11/7.
 */

public interface FruitModel {
    void loadData(int userId, int areaId, int index, int size,OnLoadFruitFinishedListener loadDataFinishedListener);
}
